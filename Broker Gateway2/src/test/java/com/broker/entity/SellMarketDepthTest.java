package com.broker.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class SellMarketDepthTest {

    private SellMarketDepth sellMarketDepth;
    private TreeMap<Integer, SellMarketDepth> map;

    @Before
    public void before() throws Exception {
        sellMarketDepth = new SellMarketDepth();
        map = new TreeMap<>();
    }

    @After
    public void after() throws Exception {
    }
    @Test
    public void getList() {
    }

    @Test
    public void setList() {
    }
}