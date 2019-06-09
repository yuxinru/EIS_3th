package com.broker.serviceImpl;

import com.broker.dao.OrderblotterDAO;
import com.broker.entity.*;
import com.broker.handler.OrderHandler;
import com.broker.parameter.Order;
import com.broker.service.OrderService;
import com.broker.handler.ActiveMQHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("OrderService")
@Slf4j
@EnableScheduling
@EnableAsync
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderHandler orderHandler;

    //@Resource
    //OrderblotterDAO orderblotterDAO;

    @Resource
    private ActiveMQHandler activeMQHandler;


//    @Scheduled(fixedRate=5000)
//    @Async
    public void sendOrderBlotters(){
        OrderBlotters orderBlotters = orderHandler.getOrderBlotters();

        log.info("定时发送orderBlotters" + ": " );
        activeMQHandler.send("OrderBlotters", orderBlotters);
    }

//    @Async
//    @Scheduled(fixedDelay = 5000)
    public void sendMarketDepths(){
        MarketDepths marketDepths = new MarketDepths();
        for(int i = 1;i<=10;i++){
            TreeMap<Integer, Integer> buyMarketDepth = orderHandler.getBuyMarketDepth(i).amountMap;
            TreeMap<Integer, Integer> sellMarketDepth = orderHandler.getSellMarketDepth(i).amountMap;
            for(int j =0; j<3&&sellMarketDepth.size()!=0; j++){
                marketDepths.marketDepths.add(0,new MarketDepth(null, null,sellMarketDepth.firstKey(), sellMarketDepth.firstEntry().getValue() , j+1));
                sellMarketDepth.remove(sellMarketDepth.firstKey());
            }
            for(int j =0; j<3&&buyMarketDepth.size()!=0; j++){
                marketDepths.marketDepths.add(new MarketDepth(j+1, buyMarketDepth.lastEntry().getValue(),buyMarketDepth.lastKey(), null, null));
                buyMarketDepth.remove(buyMarketDepth.lastKey());
            }

            log.info("定时发送MarketDepthS"+ i + ": " );
            activeMQHandler.send("MarketDepthS"+i, marketDepths);
        }
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
        //orderblotterDAO.insert(orderblotter);
        orderHandler.setOrderBlotter(orderblotter);
        return true;
    }
    private boolean buy(Order order){
        int quantity = order.getQuantity();
        SellMarketDepth sellMarketDepth = orderHandler.getSellMarketDepth(order.getProductId());
        if(sellMarketDepth.total < quantity)
            return false;
        LinkedList<Order> orderList;
        sellMarketDepth.total -= quantity;
        Integer curKey;
        Order order1;
        Integer curAmount;
        ListIterator<Order> listIterator;
        T1:
        while(quantity > 0){
            orderList = sellMarketDepth.map.firstEntry().getValue();
            curAmount = sellMarketDepth.amountMap.firstEntry().getValue();
            assert (orderList.peek().getPrice().equals(sellMarketDepth.amountMap.firstEntry().getKey()));
            curKey = orderList.peek().getPrice();
            listIterator=orderList.listIterator();

            if(quantity < curAmount){//此价格数量足够
                curAmount -= quantity;
                while(listIterator.hasNext()){
                    order1 = listIterator.next();
                    if(order1.getQuantity() > quantity){
                        order1.setQuantity(order1.getQuantity() - quantity);
                        makeBlotter(order1, order, quantity);
                        listIterator.set(order1);
                        sellMarketDepth.map.put(curKey, orderList);
                        sellMarketDepth.amountMap.put(curKey, curAmount);
                        break T1;
                    }
                    else if(order1.getQuantity().equals(quantity)) {
                        listIterator.remove();
                        makeBlotter(order1, order, quantity);
                        sellMarketDepth.amountMap.put(curKey, curAmount);
                        break T1;
                    }
                    else{
                        listIterator.remove();
                        makeBlotter(order1, order, order1.getQuantity());
                        quantity -= order1.getQuantity();
                    }
                }
            }
            else{
                quantity -= curAmount;
                while(listIterator.hasNext()){//此价格数量不足或相等
                    order1 = listIterator.next();
                    makeBlotter(order1, order, order1.getQuantity());
                }
            }
            sellMarketDepth.map.remove(curKey);
            sellMarketDepth.amountMap.remove(curKey);
        }

        orderHandler.setSellMarketDepth(sellMarketDepth, order.getProductId());
        return true;
    }

    private boolean sell(Order order){
        int quantity = order.getQuantity();
        BuyMarketDepth buyMarketDepth = orderHandler.getBuyMarketDepth(order.getProductId());
        if(buyMarketDepth.total < quantity)
            return false;
        LinkedList<Order> orderList;
        buyMarketDepth.total -= quantity;
        Order order1;
        Integer curKey;
        Integer curAmount;
        ListIterator<Order> listIterator;
        T2:
        while(quantity > 0){
            orderList = buyMarketDepth.map.lastEntry().getValue();
            curAmount = buyMarketDepth.amountMap.lastEntry().getValue();
            assert (orderList.peek().getPrice().equals(buyMarketDepth.amountMap.lastEntry().getKey()));
            curKey = orderList.peek().getPrice();
            listIterator=orderList.listIterator();
            if(quantity < curAmount){//此价格数量足够
                curAmount -= quantity;
                while(listIterator.hasNext()){
                    order1 = listIterator.next();
                    if(order1.getQuantity() > quantity){//如果此订单满足数量
                        makeBlotter(order1, order, quantity);
                        order1.setQuantity(order1.getQuantity() - quantity);
                        listIterator.set(order1);
                        buyMarketDepth.map.put(curKey, orderList);
                        buyMarketDepth.amountMap.put(curKey, curAmount);
                        break T2;
                    }
                    else if(order1.getQuantity().equals(quantity)){
                        listIterator.remove();
                        buyMarketDepth.amountMap.put(curKey, curAmount);
                        makeBlotter(order1, order, quantity);
                        break T2;
                    }else{
                        listIterator.remove();
                        makeBlotter(order1, order, order1.getQuantity());
                        quantity -= order1.getQuantity();
                    }
                }
            }
            else{  //此价格数量不足或相等
                quantity -= curAmount;
                while(listIterator.hasNext()){
                    order1 = listIterator.next();
                    makeBlotter(order1, order, order1.getQuantity());
                }
            }
            buyMarketDepth.map.remove(curKey);
            buyMarketDepth.amountMap.remove(curKey);
        }

        orderHandler.setBuyMarketDepth(buyMarketDepth, order.getProductId());
        return true;
    }

    private void checkBuyStopOrder(Order order){
        BuyStopOrder buyStopOrder = orderHandler.getBuyStopOrder(order.getProductId());
        if(buyStopOrder.stopOrderMap.size() == 0)
            return;
        SellMarketDepth sellMarketDepth = orderHandler.getSellMarketDepth(order.getProductId());
        int curSellPrice = sellMarketDepth.map.firstEntry().getValue().peek().getPrice();
        int stopPrice = buyStopOrder.stopOrderMap.firstEntry().getValue().peek().getPrice();
        if(stopPrice > curSellPrice){
            return;
        }

        LinkedList<Order> orderList;
        Order order1;
        int curKey;
        T:
        while (stopPrice <= curSellPrice){
            orderList = buyStopOrder.stopOrderMap.firstEntry().getValue();
            curKey = orderList.peek().getPrice();
            while(orderList.peek() != null){
                order1 = orderList.removeFirst();
                if(!buy(order1)){
                    orderList.addFirst(order1);
                    break T;
                }
                //成交
            }
            buyStopOrder.stopOrderMap.remove(curKey);
            if(buyStopOrder.stopOrderMap.size() == 0)
                break;
            stopPrice = buyStopOrder.stopOrderMap.firstEntry().getValue().peek().getPrice();
            curSellPrice = sellMarketDepth.map.firstEntry().getValue().peek().getPrice();
        }
        orderHandler.setBuyStopOrder(buyStopOrder, order.getProductId());
    }

    private void checkSellStopOrder(Order order){
        SellStopOrder sellStopOrder = orderHandler.getSellStopOrder(order.getProductId());
        if(sellStopOrder.stopOrderMap.size() == 0)
            return;
        BuyMarketDepth buyMarketDepth = orderHandler.getBuyMarketDepth(order.getProductId());
        int curBuyPrice = buyMarketDepth.map.lastEntry().getValue().peek().getPrice();
        int stopPrice = sellStopOrder.stopOrderMap.lastEntry().getValue().peek().getPrice();
        System.out.println("curBuyPrice "+curBuyPrice+" stopPrice " + stopPrice);
        if(stopPrice < curBuyPrice){
            return;
        }
        LinkedList<Order> orderList;
        Order order1;
        int curKey;
        T:
        while (stopPrice >= curBuyPrice){
            orderList = sellStopOrder.stopOrderMap.lastEntry().getValue();
            curKey = orderList.peek().getPrice();
            while(orderList.peek() != null){
                order1 = orderList.removeFirst();
                //System.out.println("stoporder "+order.toString());
                boolean flag = sell(order1);
                //System.out.println("flag "+ flag );
                if(!flag){
                    orderList.addFirst(order1);
                    break T;
                }
                //成交
            }
            sellStopOrder.stopOrderMap.remove(curKey);
            if(sellStopOrder.stopOrderMap.size() == 0)
                break;
            stopPrice = sellStopOrder.stopOrderMap.lastEntry().getValue().peek().getPrice();
            curBuyPrice = buyMarketDepth.map.lastEntry().getValue().peek().getPrice();
        }
        orderHandler.setSellStopOrder(sellStopOrder, order.getProductId());
    }

    @Override
    public Integer buyMarketOrder(Order order) {
        order.setOrderId(orderHandler.getBuyOrderId(order.getProductId()));
        if(!buy(order)){
            return -1;
        }
        //查看stoporder
        checkBuyStopOrder(order);
        return 1;
    }

    @Override
    public Integer buyLimitOrder(Order order){
        order.setOrderId(orderHandler.getBuyOrderId(order.getProductId()));
        SellMarketDepth sellMarketDepth = orderHandler.getSellMarketDepth(order.getProductId());
        if(sellMarketDepth.map.firstEntry() != null){
            int curSellPrice = sellMarketDepth.map.firstEntry().getValue().peek().getPrice();
            if(order.getPrice() > curSellPrice){
                if(buy(order))
                    return 1;
            }
        }

        LinkedList<Order> orderList;

        BuyMarketDepth buyMarketDepth = orderHandler.getBuyMarketDepth(order.getProductId());

        if(buyMarketDepth.map.get(order.getPrice()) == null){
            orderList = new LinkedList<>();
        }else{
            orderList = buyMarketDepth.map.get(order.getPrice());
        }

        orderList.add(order);
        buyMarketDepth.map.put(order.getPrice(), orderList);
        buyMarketDepth.total += order.getQuantity();

        Integer qty;
        if(buyMarketDepth.amountMap.get(order.getPrice()) == null){
            qty = new Integer(0);
        }else{
            qty= buyMarketDepth.amountMap.get(order.getPrice());
        }
        qty += order.getQuantity();
        buyMarketDepth.amountMap.put(order.getPrice(), qty);

        orderHandler.setBuyMarketDepth(buyMarketDepth, order.getProductId());

        return 1;
    }

    @Override
    public Integer buyStopOrder(Order order){
        order.setOrderId(orderHandler.getBuyOrderId(order.getProductId()));
        BuyStopOrder buyStopOrder = orderHandler.getBuyStopOrder(order.getProductId());
        LinkedList<Order> stopOrderList;
        Integer price = order.getPrice();
        if(buyStopOrder.stopOrderMap.get(price) == null){
            stopOrderList = new LinkedList<>();
        }else{
            stopOrderList = buyStopOrder.stopOrderMap.get(price);
        }
        stopOrderList.add(order);
        buyStopOrder.stopOrderMap.put(price, stopOrderList);
        buyStopOrder.total -= order.getQuantity();
        orderHandler.setBuyStopOrder(buyStopOrder, order.getProductId());
        return 1;
    }

    @Override
    public Integer buyCancelOrder(Order order){
        order.setOrderId(orderHandler.getBuyOrderId(order.getProductId()));
        BuyMarketDepth buyMarketDepth = orderHandler.getBuyMarketDepth(order.getProductId());
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
        if(flag){
            buyMarketDepth.map.put(order.getPrice(), orderList);
            buyMarketDepth.total -= ret;
            orderHandler.setBuyMarketDepth(buyMarketDepth, order.getProductId());
            return ret;
        }
        BuyStopOrder buyStopOrder = new BuyStopOrder();
        if(buyStopOrder.stopOrderMap.get(order.getPrice()) == null)
            return -1;
        orderList = buyStopOrder.stopOrderMap.get(order.getPrice());
        listIterator= orderList.listIterator();
        flag = false;
        ret = 0;
        while(listIterator.hasNext()){
            order1 = listIterator.next();
            if(order1.getOrderId().equals(order.getCancelId())){
                flag = true;
                ret = order1.getQuantity();
                listIterator.remove();
                break;
            }
        }
        if(flag){
            buyStopOrder.stopOrderMap.put(order.getPrice(), orderList);
            buyStopOrder.total -= ret;
            orderHandler.setBuyStopOrder(buyStopOrder, order.getProductId());
            return ret;
        }
        return -1;

    }

    @Override
    public Integer sellMarketOrder(Order order){
        order.setOrderId(orderHandler.getSellOrderId(order.getProductId()));
        if(!sell(order)){
            return  -1;
        }

        //查看stoporder
        checkSellStopOrder(order);
        return 1;
    }

    @Override
    public Integer sellLimitOrder(Order order){
        order.setOrderId(orderHandler.getSellOrderId(order.getProductId()));
        BuyMarketDepth buyMarketDepth = orderHandler.getBuyMarketDepth(order.getProductId());
        if(buyMarketDepth.map.firstEntry() != null){
            int curBuyPrice = buyMarketDepth.map.lastEntry().getValue().peek().getPrice();
            if(order.getPrice() < curBuyPrice){
                if(sell(order))
                    return 1;
            }
        }

        LinkedList<Order> orderList;
        SellMarketDepth sellMarketDepth = orderHandler.getSellMarketDepth(order.getProductId());
        Integer price = order.getPrice();
        if(sellMarketDepth.map.get(price) == null){
            orderList = new LinkedList<>();
        }else{
            orderList = sellMarketDepth.map.get(price);
        }

        orderList.add(order);

        sellMarketDepth.map.put(price, orderList);
        sellMarketDepth.total += order.getQuantity();

        Integer qty;
        if(sellMarketDepth.amountMap.get(order.getPrice()) == null){
            qty = new Integer(0);
        }else{
            qty= sellMarketDepth.amountMap.get(order.getPrice());
        }
        qty += order.getQuantity();
        sellMarketDepth.amountMap.put(order.getPrice(), qty);

        orderHandler.setSellMarketDepth(sellMarketDepth, order.getProductId());

        return 1;
    }

    @Override
    public Integer sellStopOrder(Order order){
        SellStopOrder sellStopOrder = orderHandler.getSellStopOrder(order.getProductId());
        order.setOrderId(orderHandler.getSellOrderId(order.getProductId()));
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
        sellStopOrder.total -= order.getQuantity();
        orderHandler.setSellStopOrder(sellStopOrder, order.getProductId());

        return 1;
    }

    @Override
    public Integer sellCancelOrder(Order order){
        SellMarketDepth sellMarketDepth = orderHandler.getSellMarketDepth(order.getProductId());
        order.setOrderId(orderHandler.getSellOrderId(order.getProductId()));

        if(sellMarketDepth.map.get(order.getPrice()) == null){
            return -1;
        }
        LinkedList<Order> orderList = sellMarketDepth.map.get(order.getPrice());

        ListIterator<Order> listIterator= orderList.listIterator();

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
        if(flag){
            sellMarketDepth.map.put(order.getPrice(), orderList);
            sellMarketDepth.total -= ret;
            orderHandler.setSellMarketDepth(sellMarketDepth, order.getProductId());
            return ret;
        }
        SellStopOrder sellStopOrder = new SellStopOrder();
        if(sellStopOrder.stopOrderMap.get(order.getPrice()) == null)
            return -1;
        orderList = sellStopOrder.stopOrderMap.get(order.getPrice());
        listIterator= orderList.listIterator();
        flag = false;
        ret = 0;
        while(listIterator.hasNext()){
            order1 = listIterator.next();
            if(order1.getOrderId().equals(order.getCancelId())){
                flag = true;
                ret = order1.getQuantity();
                listIterator.remove();
                break;
            }
        }
        if(flag){
            sellStopOrder.stopOrderMap.put(order.getPrice(), orderList);
            sellStopOrder.total -= ret;
            orderHandler.setSellStopOrder(sellStopOrder, order.getProductId());
            return ret;
        }
        return -1;
    }
    @Override
    public List<Orderblotter> getOrderBlotter(){
        List<Orderblotter> orderblotters = orderHandler.getOrderBlotters().orderblotters;
        return orderblotters;
    }

    @Override
    public List<MarketDepth> getMarketDepth(int productId){
        List<MarketDepth> marketDepths = new LinkedList<>();
        TreeMap<Integer, Integer> buyMarketDepth = orderHandler.getBuyMarketDepth(productId).amountMap;
        TreeMap<Integer, Integer> sellMarketDepth = orderHandler.getSellMarketDepth(productId).amountMap;
        for(int j =0; j<3&&sellMarketDepth.size()!=0; j++){
            marketDepths.add(0,new MarketDepth(null, null,sellMarketDepth.firstKey(), sellMarketDepth.firstEntry().getValue() , j+1));
            sellMarketDepth.remove(sellMarketDepth.firstKey());
        }
        for(int j =0; j<3&&buyMarketDepth.size()!=0; j++){
            marketDepths.add(new MarketDepth(j+1, buyMarketDepth.lastEntry().getValue(),buyMarketDepth.lastKey(), null, null));
            buyMarketDepth.remove(buyMarketDepth.lastKey());
        }
        log.info(marketDepths.toString());
        return marketDepths;
    }

}
