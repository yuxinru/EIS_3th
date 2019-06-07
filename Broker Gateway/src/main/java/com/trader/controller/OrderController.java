package com.trader.controller;

import com.trader.entity.BuyMarketDepth;
import com.trader.entity.Order;
import com.trader.entity.OrderBlotter;
import com.trader.parameter.Resp;
import com.trader.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Resource
    private OrderService orderService;


    @RequestMapping(value="/test", method= RequestMethod.GET)
    public Resp test(HttpServletRequest request, Model model) throws JMSException {
        System.out.println("enter marketOrder");


        int i = orderService.sendOrder(null);

        if (i == 1) {
            return new Resp("success", "下单成功!");
        }
        return new Resp("error", "下单失败!");
    }

    @RequestMapping(value="/send", method= RequestMethod.POST)
    public Resp sendOrder(HttpServletRequest request, Model model) throws JMSException {

        Order order = new Order();
        order.setOrderId(Integer.parseInt(request.getParameter("orderId")));
        order.setType(request.getParameter("type"));
        order.setProductId(Integer.parseInt(request.getParameter("productId")));
        order.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        order.setBroker(request.getParameter("broker"));
        order.setSide(request.getParameter("side"));
        order.setBroker("broker");

        log.info(order.toString());
        int i = orderService.sendOrder(order);

        if (i == 1) {
            return new Resp("success", "下单成功!");
        }
        return new Resp("error", "下单失败!");
    }

    @RequestMapping(value="/buyMarketDepth", method= RequestMethod.GET)
    public List<BuyMarketDepth> getBuyMarketDepth(HttpServletRequest request, Model model){
        return orderService.getBuyMarketDepth();
    }

    @RequestMapping(value="/orderBlotter", method= RequestMethod.GET)
    public List<OrderBlotter> getOrderBlotter(HttpServletRequest request, Model model){
        return orderService.getOrderBlotter();
    }
}
