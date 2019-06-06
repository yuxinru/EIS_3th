package com.broker.serviceImpl;

import com.broker.dao.OrderblotterDAO;
import com.broker.entity.*;
import com.broker.handler.OrderHandler;
import com.broker.service.OrderService;
import com.broker.handler.ActiveMQHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

@Service("OrderService")
@Slf4j
@EnableScheduling
@EnableAsync
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderHandler orderHandler;

    @Resource
    OrderblotterDAO orderblotterDAO;

    @Resource
    private ActiveMQHandler activeMQHandler;

    @Scheduled(fixedRate=5000)
    @Async
    public void sendOrderBlotter(){
//        List<Object> objects = orderHandler.getOrderBlotter();
//        LinkedList<Orderblotter> orderblotters = new LinkedList<>();
//        ListIterator<Object> listIterator=objects.listIterator();
//
//        Object o;
//        while(listIterator.hasNext()){
//            o = listIterator.next();
//            orderblotters.add((Orderblotter)o);
//        }
//        log.info("定时发送orderblotters: " );
//        activeMQHandler.send("OrderBlotter", orderblotters);

    }
    @Async
    @Scheduled(fixedDelay = 5000)
    public void sendSellMarketDepth(){
//        SellMarketDepth sellMarketDepth = orderHandler.getSellMarketDepth();
//        log.info("定时发送sellMarketDepth: " );
//        activeMQHandler.send("SellMarketDepth", sellMarketDepth);
    }
    @Async
    @Scheduled(fixedDelay = 5000)
    public void sendBuyMarketDepth(){
//        BuyMarketDepth buyMarketDepth = orderHandler.getBuyMarketDepth();
        log.info("定时发送buyMarketDepth: " );
//        activeMQHandler.send("SellMarketDepth", buyMarketDepth);
    }

    private boolean makeBlotter(Order IniOrder, Order CplOrder, int quantity){
        Orderblotter orderblotter = new Orderblotter();
        orderblotter.setBroker(IniOrder.getBroker());
        orderblotter.setProductid(IniOrder.getProductId());
        orderblotter.setPeriod(IniOrder.getPeriod());
        orderblotter.setPrice(IniOrder.getPrice());
        orderblotter.setQuantity(quantity);
        orderblotter.setIniTrader("");
        orderblotter.setIniSide(IniOrder.getSide());
        orderblotter.setCplTrader("");
        orderblotter.setCplSide(CplOrder.getSide());
        orderblotterDAO.insert(orderblotter);
        orderHandler.setOrderBlotter(orderblotter);
        return true;
    }

    private boolean buy(Order order){
        int quantity = order.getQuantity();
        SellMarketDepth sellMarketDepth = orderHandler.getSellMarketDepth();
        System.out.println("buyMarketDepth.total " + sellMarketDepth.total);
        if(sellMarketDepth.total < quantity)
            return false;
        LinkedList<Order> orderList;
        sellMarketDepth.total -= quantity;
        Integer curKey;
        Order order1;
        T1:
        while(true){
            orderList = sellMarketDepth.map.firstEntry().getValue();
            curKey = orderList.peek().getPrice();
            while(orderList.peek() != null){
                order1 = orderList.removeFirst();
                if(order1.getQuantity() > quantity){
                    //部分成交
                    order1.setQuantity(order1.getQuantity() - quantity);
                    orderList.addFirst(order1);
                    makeBlotter(order1, order, quantity);
                    break T1;
                }
                //全部成交
                quantity -= order1.getQuantity();
                makeBlotter(order1, order, order1.getQuantity());
            }
            sellMarketDepth.map.remove(curKey);
        }
        sellMarketDepth.map.put(curKey, orderList);
        orderHandler.setSellMarketDepth(sellMarketDepth);
        return true;
    }

    private boolean sell(Order order){
        int quantity = order.getQuantity();
        BuyMarketDepth buyMarketDepth = orderHandler.getBuyMarketDepth();
        System.out.println("buyMarketDepth.total " + buyMarketDepth.total);
        if(buyMarketDepth.total < quantity)
            return false;
        LinkedList<Order> orderList;
        buyMarketDepth.total -= quantity;
        Order order1;
        Integer curKey;
        T2:
        while(true){
            orderList = buyMarketDepth.map.lastEntry().getValue();
            curKey = orderList.peek().getPrice();
            while(orderList.peek() != null){
                order1 = orderList.removeFirst();
                if(order1.getQuantity() > quantity){
                    //部分成交
                    order1.setQuantity(order1.getQuantity() - quantity);
                    orderList.addFirst(order1);
                    makeBlotter(order1, order, quantity);
                    break T2;
                }
                //全部成交
                quantity -= order1.getQuantity();
                makeBlotter(order1, order, order1.getQuantity());
            }
            buyMarketDepth.map.remove(curKey);
        }
        buyMarketDepth.map.put(curKey, orderList);
        orderHandler.setBuyMarketDepth(buyMarketDepth);
        return true;
    }

    private void checkBuyStopOrder(){
        BuyStopOrder buyStopOrder = orderHandler.getBuyStopOrder();
        if(buyStopOrder.stopOrderMap.size() == 0)
            return;
        SellMarketDepth sellMarketDepth = orderHandler.getSellMarketDepth();
        int curSellPrice = sellMarketDepth.map.firstEntry().getValue().peek().getPrice();
        int stopPrice = buyStopOrder.stopOrderMap.firstEntry().getValue().peek().getPrice();
        if(stopPrice > curSellPrice){
            return;
        }

        LinkedList<Order> orderList;
        Order order;
        int curKey;
        T:
        while (stopPrice <= curSellPrice){
            orderList = buyStopOrder.stopOrderMap.firstEntry().getValue();
            curKey = orderList.peek().getPrice();
            while(orderList.peek() != null){
                order = orderList.removeFirst();
                if(!buy(order)){
                    orderList.addFirst(order);
                    break T;
                }
                //成交
            }
            buyStopOrder.stopOrderMap.remove(curKey);
            if(buyStopOrder.stopOrderMap.size() == 0)
                break;
            stopPrice = buyStopOrder.stopOrderMap.firstEntry().getValue().peek().getPrice();
        }
        orderHandler.setBuyStopOrder(buyStopOrder);

    }

    private void checkSellStopOrder(){
        SellStopOrder sellStopOrder = orderHandler.getSellStopOrder();
        if(sellStopOrder.stopOrderMap.size() == 0)
            return;
        BuyMarketDepth buyMarketDepth = orderHandler.getBuyMarketDepth();
        int curBuyPrice = buyMarketDepth.map.lastEntry().getValue().peek().getPrice();
        int stopPrice = sellStopOrder.stopOrderMap.lastEntry().getValue().peek().getPrice();
        System.out.println("curBuyPrice "+curBuyPrice+" stopPrice " + stopPrice);
        if(stopPrice < curBuyPrice){
            return;
        }
        LinkedList<Order> orderList;
        Order order;
        int curKey;
        T:
        while (stopPrice >= curBuyPrice){
            orderList = sellStopOrder.stopOrderMap.lastEntry().getValue();
            curKey = orderList.peek().getPrice();
            while(orderList.peek() != null){
                order = orderList.removeFirst();
                //System.out.println("stoporder "+order.toString());
                boolean flag = sell(order);
                //System.out.println("flag "+ flag );
                if(!flag){
                    orderList.addFirst(order);
                    break T;
                }
                //成交
            }
            sellStopOrder.stopOrderMap.remove(curKey);
            if(sellStopOrder.stopOrderMap.size() == 0)
                break;
            stopPrice = sellStopOrder.stopOrderMap.lastEntry().getValue().peek().getPrice();
        }
        orderHandler.setSellStopOrder(sellStopOrder);
    }




    @Override
    public Integer buyMarketOrder(Order order) {
        order.setOrderId(orderHandler.getBuyOrderId());
        if(!buy(order)){
            return -1;
        }

        //查看stoporder
        checkBuyStopOrder();
        return 1;
    }

    @Override
    public Integer buyLimitOrder(Order order){
        LinkedList<Order> orderList;
        order.setOrderId(orderHandler.getBuyOrderId());
        BuyMarketDepth buyMarketDepth = orderHandler.getBuyMarketDepth();

        if(buyMarketDepth.map.get(order.getPrice()) == null){
            orderList = new LinkedList<>();
        }else{
            orderList = buyMarketDepth.map.get(order.getPrice());
        }

        orderList.add(order);

        buyMarketDepth.map.put(order.getPrice(), orderList);
        buyMarketDepth.total += order.getQuantity();

        orderHandler.setBuyMarketDepth(buyMarketDepth);

        return 1;
    }

    @Override
    public Integer buyStopOrder(Order order){
        order.setOrderId(orderHandler.getBuyOrderId());
        BuyStopOrder buyStopOrder = orderHandler.getBuyStopOrder();
        LinkedList<Order> stopOrderList;
        Integer price = order.getPrice();
        if(buyStopOrder.stopOrderMap.get(price) == null){
            stopOrderList = new LinkedList<>();
        }else{
            stopOrderList = buyStopOrder.stopOrderMap.get(price);
        }
        stopOrderList.add(order);
        buyStopOrder.stopOrderMap.put(price, stopOrderList);

        orderHandler.setBuyStopOrder(buyStopOrder);
        return 1;
    }

    @Override
    public Integer buyCancelOrder(Order order){
        order.setOrderId(orderHandler.getBuyOrderId());
        BuyMarketDepth buyMarketDepth = orderHandler.getBuyMarketDepth();
        if(buyMarketDepth.map.get(order.getPrice()) == null){
            return -1;
        }
        LinkedList<Order> orderList = buyMarketDepth.map.get(order.getPrice());

        ListIterator<Order> listIterator=orderList.listIterator();

        Order order1;
        boolean flag = false;
        int ret = 0;
        while(listIterator.hasNext()){
            order1 = listIterator.next();
            if(order1.getOrderId().equals(order.getCancelId())){
                flag = true;
                ret = order1.getQuantity();
                listIterator.remove();
                break;
            }
        }
        if(!flag)
            return -1;
        buyMarketDepth.map.put(order.getPrice(), orderList);
        orderHandler.setBuyMarketDepth(buyMarketDepth);
        buyMarketDepth.total -= ret;
        return ret;
    }

    @Override
    public Integer sellMarketOrder(Order order){
        order.setOrderId(orderHandler.getSellOrderId());
        if(!sell(order)){
            return  -1;
        }

        //查看stoporder
        checkSellStopOrder();
        return 1;
    }

    @Override
    public Integer sellLimitOrder(Order order){
        LinkedList<Order> orderList;
        order.setOrderId(orderHandler.getSellOrderId());
        SellMarketDepth sellMarketDepth = orderHandler.getSellMarketDepth();
        Integer price = order.getPrice();
        if(sellMarketDepth.map.get(price) == null){
            orderList = new LinkedList<>();
        }else{
            orderList = sellMarketDepth.map.get(price);
        }

        orderList.add(order);

        sellMarketDepth.map.put(price, orderList);
        sellMarketDepth.total += order.getQuantity();

        orderHandler.setSellMarketDepth(sellMarketDepth);

        return 1;
    }

    @Override
    public Integer sellStopOrder(Order order){
        SellStopOrder sellStopOrder = orderHandler.getSellStopOrder();
        order.setOrderId(orderHandler.getSellOrderId());
        LinkedList<Order> stopOrderList;
        Integer price = order.getPrice();
        assert(sellStopOrder != null);
        assert(sellStopOrder.stopOrderMap != null);
        if(sellStopOrder.stopOrderMap.get(price) == null){
            stopOrderList = new LinkedList<>();
        }else{
            stopOrderList = sellStopOrder.stopOrderMap.get(price);
        }
        stopOrderList.add(order);
        sellStopOrder.stopOrderMap.put(price, stopOrderList);

        orderHandler.setSellStopOrder(sellStopOrder);

        return 1;
    }

    @Override
    public Integer sellCancelOrder(Order order){
        SellMarketDepth sellMarketDepth = orderHandler.getSellMarketDepth();
        order.setOrderId(orderHandler.getSellOrderId());
        if(sellMarketDepth.map.get(order.getPrice()) == null){
            return -1;
        }
        LinkedList<Order> orderList = sellMarketDepth.map.get(order.getPrice());

        ListIterator<Order> listIterator=orderList.listIterator();

        Order order1;
        boolean flag = false;
        int ret = 0;
        while(listIterator.hasNext()){
            order1 = listIterator.next();
            if(order1.getOrderId().equals(order.getCancelId())){
                flag = true;
                ret = order1.getQuantity();
                listIterator.remove();
                break;
            }
        }
        if(!flag)
            return -1;
        sellMarketDepth.map.put(order.getPrice(), orderList);
        orderHandler.setSellMarketDepth(sellMarketDepth);
        sellMarketDepth.total -= ret;
        return ret;
    }

    @Override
    public List<BuyMarketDepth> getMarketDepth(){
        return null;
    }

}
