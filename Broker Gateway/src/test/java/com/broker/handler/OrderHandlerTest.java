package com.broker.handler;

import com.broker.entity.Orderblotter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderHandlerTest {

    @Resource
    OrderHandler orderHandler;

    @Resource
    RedisHandler redisHandler;

    @Before
    public void before() throws Exception {
//        redisHandler.set("buyOrderId", null);
//        redisHandler.set("sellOrderId", null);
    }

    @After
    public void after() throws Exception {
    }
    @Test
    public void getBuyOrderId() {

        assertEquals(1,orderHandler.getBuyOrderId(1) );
        assertEquals(2,orderHandler.getBuyOrderId(1) );
        assertEquals(3,orderHandler.getBuyOrderId(1) );
        assertEquals(4,orderHandler.getBuyOrderId(1) );
    }

    @Test
    public void getSellOrderId() {
        assertEquals(1,orderHandler.getSellOrderId(1) );
        assertEquals(2,orderHandler.getSellOrderId(1) );
        assertEquals(3,orderHandler.getSellOrderId(1) );
        assertEquals(4,orderHandler.getSellOrderId(1) );
    }



    @Test
    public void getBuyMarketDepth() {
        orderHandler.getBuyMarketDepth(1);
    }

    @Test
    public void getSellMarketDepth() {
        orderHandler.getSellMarketDepth(1);
    }

    @Test
    public void setBuyMarketDepth() {
    }

    @Test
    public void setSellMarketDepth() {
    }

    @Test
    public void getSellStopOrder() {
        orderHandler.getSellStopOrder(1);
    }

    @Test
    public void setSellStopOrder() {
    }

    @Test
    public void getBuyStopOrder() {
        orderHandler.getBuyStopOrder(1);
    }

    @Test
    public void setBuyStopOrder() {
    }

    @Test
    public void setOrderBlotter() {
        Orderblotter orderblotter = new Orderblotter(1, "broker", 1, new Date(), 123, 123, "trader", "buy", "rrrr", "sell");
        orderHandler.setOrderBlotter(orderblotter, 1);
    }

    @Test
    public void getOrderBlotter() {
        orderHandler.getOrderBlotter(1);
    }
}