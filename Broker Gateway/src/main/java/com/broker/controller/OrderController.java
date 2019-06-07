package com.broker.controller;

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
        return orderService.getOrderBlotter();
    }
}
