package com.trader.controller;

import com.trader.entity.*;
import com.trader.parameter.Resp;
import com.trader.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Resource
    private OrderService orderService;

    @RequestMapping(value="/myOrder", method= RequestMethod.GET)
    public List<Order> getMyOrder(HttpServletRequest request, Model model) throws InterruptedException {
        String product = request.getParameter("product");
        String period = request.getParameter("period").toUpperCase();

        log.info("getMyOrder");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(product.equals("gold")){
            if(period.equals("SEP16")){
                return orderService.getMyOrder(user.getUsername(),1);
            }
            if(period.equals("OCT10")){
                return orderService.getMyOrder(user.getUsername(),2);
            }
            if(period.equals("FEB1")){
                return orderService.getMyOrder(user.getUsername(),3);
            }
        }
        else if(product.equals("petro")){
            if(period.equals("JUL5")){
                return orderService.getMyOrder(user.getUsername(),4);
            }
            if(period.equals("JUN12")){
                return orderService.getMyOrder(user.getUsername(),5);
            }
            if(period.equals("SEP16")){
                return orderService.getMyOrder(user.getUsername(),6);
            }
        }
        else if(product.equals("steel")){
            if(period.equals("FEB1")){
                return orderService.getMyOrder(user.getUsername(),7);
            }
            if(period.equals("AUG13")){
                return orderService.getMyOrder(user.getUsername(),8);
            }
            if(period.equals("APR7")){
                return orderService.getMyOrder(user.getUsername(),9);
            }
            if(period.equals("NOV12")){
                return orderService.getMyOrder(user.getUsername(),10);
            }
        }
        return null;
    }

    @RequestMapping(value="/send", method= RequestMethod.POST)
    public Resp sendOrder(@RequestBody Order1 order1, HttpServletRequest request) throws JMSException {
        Order order = new Order();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        order.setUsername(user.getUsername());
        String product = order1.getProduct();
        String period = order1.getPeriod().toUpperCase();
        log.info("product "+product);
        log.info("period "+period);
        if(product.equals("gold")){
            order.setBroker("M");
            if(period.equals("SEP16")){
                order.setProductId(1);
            }
            if(period.equals("OCT10")){
                order.setProductId(2);
            }
            if(period.equals("FEB1")){
                order.setProductId(3);
            }
        }
        else if(product.equals("petro")){
            order.setBroker("M");
            if(period.equals("JUL5")){
                order.setProductId(4);
            }
            if(period.equals("JUN12")){
                order.setProductId(5);
            }
            if(period.equals("SEP16")){
                order.setProductId(6);
            }
        }
        else if(product.equals("steel")){
            order.setBroker("N");
            if(period.equals("FEB1") || period.equals("Feb1")){
                order.setProductId(7);
            }
            if(period.equals("AUG13")|| period.equals("Aug13")){
                order.setProductId(8);
            }
            if(period.equals("APR7")|| period.equals("Apr7")){
                order.setProductId(9);
            }
            if(period.equals("NOV12")|| period.equals("Nov12")){
                order.setProductId(10);
            }
        }
        else{
            return new Resp("error", "订单格式有误!");
        }


        order.setType(order1.getType());
        order.setQuantity(order1.getQuantity());
        //order.setBroker(request.getParameter("broker"));
        order.setSide(order1.getSide());
        order.setBroker("broker");
        order.setPrice(order1.getPrice());
        order.setStrategy(order1.getStrategy());
        order.setProduct(product);
        order.setPeriod(period);
        order.setCancelId(order1.getCancelId());

        log.info(order.toString());
        int i = orderService.sendOrder(order);
        if (i == 1) {
            return new Resp("success", "下单成功!");
        }
        return new Resp("error", "下单失败!");
    }

    @RequestMapping(value="/marketDepth", method= RequestMethod.GET)
    public List<MarketDepth> getMarketDepth(HttpServletRequest request, Model model){
        log.info("getMarketDepths");

        String product = request.getParameter("product");
        String period = request.getParameter("period").toUpperCase();
        if(product.equals("gold")){
            if(period.equals("SEP16")){
                return orderService.getMarketDepths(1);
            }
            if(period.equals("OCT10")){
                return orderService.getMarketDepths(2);
            }
            if(period.equals("FEB1")){
                return orderService.getMarketDepths(3);
            }
        }
        else if(product.equals("petro")){
            if(period.equals("JUL5")){
                return orderService.getMarketDepths(4);
            }
            if(period.equals("JUN12")){
                return orderService.getMarketDepths(5);
            }
            if(period.equals("SEP16")){
                return orderService.getMarketDepths(6);
            }
        }
        else if(product.equals("steel")){
            if(period.equals("FEB1")){
                return orderService.getMarketDepths(7);
            }
            if(period.equals("AUG13")){
                return orderService.getMarketDepths(8);
            }
            if(period.equals("APR7")){
                return orderService.getMarketDepths(9);
            }
            if(period.equals("NOV12")){
                return orderService.getMarketDepths(10);
            }
        }

        return null;
    }

    @RequestMapping(value="/orderBlotter", method= RequestMethod.GET)
    public List<OrderBlotter> getOrderBlotter(HttpServletRequest request, Model model){
        return orderService.getOrderBlotters();
    }
}
