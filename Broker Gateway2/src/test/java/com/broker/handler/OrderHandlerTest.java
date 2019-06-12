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

    }

    @Test
    public void getSellOrderId() {
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
    }

    @Test
    public void getOrderBlotter() {

    }
}