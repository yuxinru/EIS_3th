package com.broker.controller;

import com.broker.entity.MarketDepth;
import com.broker.entity.Order;
import com.broker.entity.Orderblotter;
import com.broker.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Resource
    OrderService orderService;

    @RequestMapping(value="/orderBlotter", method= RequestMethod.GET)
    public List<Orderblotter> getOrderBlotter(HttpServletRequest request, Model model){
        log.info("getOrderBlotter");
        return orderService.getOrderBlotter();
    }

    @RequestMapping(value="/marketDepth", method= RequestMethod.GET)
    public List<MarketDepth> getMarketDepth(HttpServletRequest request, Model model){
        log.info("getMarketDepth");

        String product = request.getParameter("product");
        String period = request.getParameter("period");
        if(product.equals("steel")){
            if(period.equals("FEB1")){
                return orderService.getMarketDepth(7);
            }
            if(period.equals("AUG13")){
                return orderService.getMarketDepth(8);
            }
            if(period.equals("APR7")){
                return orderService.getMarketDepth(9);
            }
            if(period.equals("NOV12")){
                return orderService.getMarketDepth(10);
            }
        }
        return null;
    }
}
