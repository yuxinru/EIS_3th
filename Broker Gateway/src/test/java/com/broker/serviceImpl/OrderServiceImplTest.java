package com.broker.serviceImpl;

import com.broker.parameter.Order;
import com.broker.handler.RedisHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(value = SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
    @Resource
    private OrderServiceImpl orderServiceImpl;

    @Resource
    RedisHandler redisHandler;


    @Before
    public void before() throws Exception {
   //  redisHandler.set("buyMarketDepth1", null);
//        redisHandler.set("sellStopOrder", null);
 //       redisHandler.set("sellMarketDepth1", null);
//        redisHandler.set("buyStopOrder", null);
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void getBuyMarketDepth() {
    }

    @Test
    public void getOrderBlotter() {

    }

    @Test
    public void buyMarketOrder() {
        Order order = new Order();
        order.setType("market");
        order.setProductId(1);
        order.setQuantity(144);
        order.setBroker("broker");

        orderServiceImpl.buyMarketOrder(order);
    }

    @Test
    public void buyLimitOrder() {
        Order order = new Order();
        order.setType("limit");
        order.setProductId(1);
        order.setQuantity(30);
        order.setBroker("broker");
        order.setPrice(1248);
        orderServiceImpl.buyLimitOrder(order);
        order = new Order();
        order.setType("limit");
        order.setProductId(1);
        order.setQuantity(30);
        order.setBroker("broker");
        order.setPrice(1248);
        orderServiceImpl.buyLimitOrder(order);
        order = new Order();
        order.setType("limit");
        order.setProductId(1);
        order.setQuantity(30);
        order.setBroker("broker");
        order.setPrice(1248);
        orderServiceImpl.buyLimitOrder(order);

        order = new Order();
        order.setType("limit");
        order.setProductId(1);
        order.setQuantity(120);
        order.setBroker("broker");
        order.setPrice(1246);
        orderServiceImpl.buyLimitOrder(order);
        order = new Order();
        order.setType("limit");
        order.setProductId(1);
        order.setQuantity(220);
        order.setBroker("broker");
        order.setPrice(1246);
        orderServiceImpl.buyLimitOrder(order);

        order = new Order();
        order.setType("limit");
        order.setProductId(1);
        order.setQuantity(97);
        order.setBroker("broker");
        order.setPrice(1244);
        orderServiceImpl.buyLimitOrder(order);
        order = new Order();
        order.setType("limit");
        order.setProductId(1);
        order.setQuantity(90);
        order.setBroker("broker");
        order.setPrice(1244);
        orderServiceImpl.buyLimitOrder(order);


//
//        order = new Order();
//        order.setType("market");
//        order.setProductId(1);
//        order.setQuantity(500);
//        order.setBroker("broker");
//        orderServiceImpl.sellMarketOrder(order);
    }

    @Test
    public void buyStopOrder() {
        Order order = new Order();
        order.setType("market");
        order.setProductId(1);
        order.setQuantity(100);
        order.setBroker("broker");
        order.setPrice(1253);
        orderServiceImpl.buyStopOrder(order);
    }

    @Test
    public void buyCancelOrder() {
        Order order = new Order();
        order.setCancelId(4);
        order.setPrice(1250);
        orderServiceImpl.buyCancelOrder(order);
    }

    @Test
    public void sellMarketOrder() {
        Order order = new Order();
        order.setType("market");
        order.setProductId(1);
        order.setQuantity(370);
        order.setBroker("broker");
        orderServiceImpl.sellMarketOrder(order);
    }

    @Test
    public void sellLimitOrder() {
        Order order = new Order();
        order.setType("limit");
        order.setProductId(1);
        order.setQuantity(25);
        order.setBroker("broker");
        order.setPrice(1250);
        orderServiceImpl.sellLimitOrder(order);
        order = new Order();
        order.setType("limit");
        order.setProductId(1);
        order.setQuantity(25);
        order.setBroker("broker");
        order.setPrice(1250);
        orderServiceImpl.sellLimitOrder(order);

        order = new Order();
        order.setType("limit");
        order.setProductId(1);
        order.setQuantity(30);
        order.setBroker("broker");
        order.setPrice(1252);
        orderServiceImpl.sellLimitOrder(order);
        order = new Order();
        order.setType("limit");
        order.setProductId(1);
        order.setQuantity(2);
        order.setBroker("broker");
        order.setPrice(1252);
        orderServiceImpl.sellLimitOrder(order);

        order = new Order();
        order.setType("limit");
        order.setProductId(1);
        order.setQuantity(30);
        order.setBroker("broker");
        order.setPrice(1254);
        orderServiceImpl.sellLimitOrder(order);
        order = new Order();
        order.setType("limit");
        order.setProductId(1);
        order.setQuantity(30);
        order.setBroker("broker");
        order.setPrice(1254);
        orderServiceImpl.sellLimitOrder(order);
        order = new Order();
        order.setType("limit");
        order.setProductId(1);
        order.setQuantity(30);
        order.setBroker("broker");
        order.setPrice(1254);
        orderServiceImpl.sellLimitOrder(order);
        order.setProductId(1);
        order.setQuantity(30);
        order.setBroker("broker");
        order.setPrice(1254);
        orderServiceImpl.sellLimitOrder(order);
        order = new Order();
        order.setType("limit");
        order.setProductId(1);
        order.setQuantity(7);
        order.setBroker("broker");
        order.setPrice(1254);
        orderServiceImpl.sellLimitOrder(order);

    }

    @Test
    public void sellStopOrder() {
        Order order = new Order();
        order.setType("market");
        order.setProductId(1);
        order.setQuantity(20);
        order.setBroker("broker");
        order.setPrice(1246);
        orderServiceImpl.sellStopOrder(order);
    }

    @Test
    public void sellCancelOrder() {
    }

    @Test
    public void getBuyMarketDepth1() {
    }

    @Test
    public void getOrderBlotter1() {
    }

    @Test
    public void sendOrderBlotter() {
    }

    @Test
    public void getMarketDepth() {
        orderServiceImpl.getMarketDepth(1);
    }
}