package com.broker.receiver;

import com.alibaba.fastjson.JSONObject;
import com.broker.entity.Order;
import com.broker.parameter.MyOrder;
import com.broker.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import java.util.concurrent.Semaphore;

@EnableJms
@Slf4j
@Component
public class MQReceiver {
    @Resource
    private OrderService orderService;

//    private Semaphore semaphore1=new Semaphore(1, true);
//    private Semaphore semaphore2=new Semaphore(1, true);
//    private Semaphore semaphore3=new Semaphore(1, true);
//    private Semaphore semaphore4=new Semaphore(1, true);
//    private Semaphore semaphore5=new Semaphore(1, true);
//    private Semaphore semaphore6=new Semaphore(1, true);
    private Semaphore semaphore7=new Semaphore(1, true);
    private Semaphore semaphore8=new Semaphore(1, true);
    private Semaphore semaphore9=new Semaphore(1, true);
    private Semaphore semaphore10=new Semaphore(1, true);


    private void dealOrder(Order order) throws JMSException {
        if(order.getSide()==null){
            orderService.cancelOrder(order);
            return;
        }
        if(order.getStrategy()==null){
            order.setStrategy("none");
        }
        if(order.getSide().equals("buy")){
            if(order.getType().equals("market")){
                System.out.println("market");
                orderService.buyMarketOrder(order);
            }
            if(order.getType().equals("limit") ){
                orderService.buyLimitOrder(order);
            }
            if(order.getType().equals("cancel") ){
                orderService.cancelOrder(order);
            }
            if(order.getType().equals("stop")){
                orderService.buyStopOrder(order);
            }
        }
        if (order.getSide().equals("sell")){
            if(order.getType().equals("market")){
                System.out.println("market");
                orderService.sellMarketOrder(order);
            }
            if(order.getType().equals("limit") ){
                orderService.sellLimitOrder(order);
            }
            if(order.getType().equals("cancel") ){
                orderService.cancelOrder(order);
            }
            if(order.getType().equals("stop")){
                orderService.sellStopOrder(order);
            }
        }
    }
//    @JmsListener(destination = "order1")
//    public void listen1(String msg) throws JMSException, InterruptedException {
//        Order order = JSONObject.parseObject(msg, Order.class);
//        log.info(order.toString());
//        semaphore1.acquire();
//        dealOrder(order);
//        semaphore1.release();
//    }
//    @JmsListener(destination = "order2")
//    public void listen2(String msg) throws JMSException, InterruptedException {
//        Order order = JSONObject.parseObject(msg, Order.class);
//        log.info(order.toString());
//        semaphore2.acquire();
//        dealOrder(order);
//        semaphore2.release();
//    }
//    @JmsListener(destination = "order3")
//    public void listen3(String msg) throws JMSException, InterruptedException {
//        Order order = JSONObject.parseObject(msg, Order.class);
//        log.info(order.toString());
//        semaphore3.acquire();
//        dealOrder(order);
//        semaphore3.release();
//    }
//    @JmsListener(destination = "order4")
//    public void listen4(String msg) throws JMSException, InterruptedException{
//        Order order = JSONObject.parseObject(msg, Order.class);
//        log.info(order.toString());
//        semaphore4.acquire();
//        dealOrder(order);
//        semaphore4.release();
//    }
//    @JmsListener(destination = "order5")
//    public void listen5(String msg) throws JMSException, InterruptedException{
//        Order order = JSONObject.parseObject(msg, Order.class);
//        log.info(order.toString());
//        semaphore5.acquire();
//        dealOrder(order);
//        semaphore5.release();
//    }
//
//    @JmsListener(destination = "order6")
//    public void listen6(String msg) throws JMSException, InterruptedException{
//        Order order = JSONObject.parseObject(msg, Order.class);
//        log.info(order.toString());
//        semaphore6.acquire();
//        dealOrder(order);
//        semaphore6.release();
//    }

    @JmsListener(destination = "order7")
    public void listen7(String msg) throws JMSException, InterruptedException{
        Order order = JSONObject.parseObject(msg, Order.class);
        log.info(order.toString());
        semaphore7.acquire();
        dealOrder(order);
        semaphore7.release();
    }

    @JmsListener(destination = "order8")
    public void listen8(String msg) throws JMSException, InterruptedException{
        Order order = JSONObject.parseObject(msg, Order.class);
        log.info(order.toString());
        semaphore8.acquire();
        dealOrder(order);
        semaphore8.release();
    }

    @JmsListener(destination = "order9")
    public void listen9(String msg) throws JMSException, InterruptedException{
        Order order = JSONObject.parseObject(msg, Order.class);
        log.info(order.toString());
        semaphore9.acquire();
        dealOrder(order);
        semaphore9.release();
    }

    @JmsListener(destination = "order10")
    public void listen10(String msg) throws JMSException, InterruptedException{
        Order order = JSONObject.parseObject(msg, Order.class);
        log.info(order.toString());
        semaphore10.acquire();
        dealOrder(order);
        semaphore10.release();
    }
    @JmsListener(destination = "myOrder")
    public void listenMyOrder(String msg) throws JMSException, InterruptedException{
        MyOrder myOrder = JSONObject.parseObject(msg, MyOrder.class);
        orderService.getMyOrder(myOrder.username, myOrder.productId, myOrder.trader);
    }
}
