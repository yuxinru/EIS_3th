package com.trader.controller;

import com.trader.entity.User;
import com.trader.parameter.Resp;
import com.trader.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/login_p")
    public void login_p(HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.write("{\"status\":\"error\",\"msg\":\"尚未登录，请登录!\"}");
        out.flush();
        out.close();
        //        return new RespBean("error", "尚未登录，请登录!");
    }

    //登陆
    @RequestMapping(value="/login", method= RequestMethod.POST )
    @ResponseBody
    public Resp login(@RequestBody User user, HttpServletRequest request) {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        log.info(username);
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
        log.info(user.toString());
        int i = userService.login(user);
        if (i == 1) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            log.info("登陆成功!");
            return new Resp("success", "登陆成功!");
        } else if (i == -1) {
            log.info("密码错误!");
            return new Resp("error", "密码错误!");
        }
        return new Resp("error", "用户名不存在!");
    }
    //用户注册
    @RequestMapping(value="/register",method= RequestMethod.POST)
    public Resp register(@RequestBody User user){
        log.info(user.toString());
        int i = userService.registerUser(user);
        if (i == 1) {
            return new Resp("success", "注册成功!");
        } else if (i == -1) {
            return new Resp("error", "用户名重复，注册失败!");
        }
        return new Resp("error", "注册失败!");
    }
    //用户信息
    @RequestMapping(value="/information",method= RequestMethod.GET)
    public User information(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        log.info(user.toString());
        return userService.getInfo(user.getUsername());

    }
    //用户注销
    @RequestMapping(value="/logout",method= RequestMethod.GET)
    public Resp logout(HttpServletRequest request){
        log.info("logout");
        HttpSession session = request.getSession();
        session.setAttribute("username", null);

        return new Resp("success", "注销成功!");
    }
}
