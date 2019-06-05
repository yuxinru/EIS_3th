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

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
    @Resource
    private OrderServiceImpl orderService;

    @Resource
    RedisHandler redisHandler;


    @Before
    public void before() throws Exception {

//        redisHandler.set("buyMarketDepth", null);
//        redisHandler.set("sellStopOrder", null);
//        redisHandler.set("sellMarketDepth", null);
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
        order.setProductId(22);
        order.setQuantity(63);
        order.setBroker("broker");

        orderService.buyMarketOrder(order);
    }

    @Test
    public void buyLimitOrder() {
        Order order = new Order();
        order.setType("market");
        order.setProductId(22);
        order.setQuantity(90);
        order.setBroker("broker");
        order.setPrice(1248);
        orderService.buyLimitOrder(order);

        order = new Order();
        order.setType("market");
        order.setProductId(22);
        order.setQuantity(340);
        order.setBroker("broker");
        order.setPrice(1246);
        orderService.buyLimitOrder(order);

        order = new Order();
        order.setType("market");
        order.setProductId(22);
        order.setQuantity(187);
        order.setBroker("broker");
        order.setPrice(1244);
        orderService.buyLimitOrder(order);


    }

    @Test
    public void buyStopOrder() {
        Order order = new Order();
        order.setType("market");
        order.setProductId(22);
        order.setQuantity(100);
        order.setBroker("broker");
        order.setPrice(1253);
        orderService.buyStopOrder(order);
    }

    @Test
    public void buyCancelOrder() {
        Order order = new Order();
        order.setCancelId(4);
        order.setPrice(1250);
        orderService.buyCancelOrder(order);
    }

    @Test
    public void sellMarketOrder() {
        Order order = new Order();
        order.setType("market");
        order.setProductId(22);
        order.setQuantity(90);
        order.setBroker("broker");
        orderService.sellMarketOrder(order);
    }

    @Test
    public void sellLimitOrder() {
        Order order = new Order();
        order.setType("market");
        order.setProductId(22);
        order.setQuantity(50);
        order.setBroker("broker");
        order.setPrice(1250);
        orderService.sellLimitOrder(order);

        order = new Order();
        order.setType("market");
        order.setProductId(22);
        order.setQuantity(32);
        order.setBroker("broker");
        order.setPrice(1252);
        orderService.sellLimitOrder(order);

        order = new Order();
        order.setType("market");
        order.setProductId(22);
        order.setQuantity(127);
        order.setBroker("broker");
        order.setPrice(1254);
        orderService.sellLimitOrder(order);

    }

    @Test
    public void sellStopOrder() {
        Order order = new Order();
        order.setType("market");
        order.setProductId(22);
        order.setQuantity(20);
        order.setBroker("broker");
        order.setPrice(1246);
        orderService.sellStopOrder(order);
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
        orderService.sendOrderBlotter();
    }
}