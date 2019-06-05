package com.broker.controller;

import com.broker.entity.User;
import com.broker.parameter.Resp;
import com.broker.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@CrossOrigin
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/login_p")
    @ResponseBody
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
    @RequestMapping("/login")
    @ResponseBody
    public Resp login(@RequestBody User user, HttpServletRequest request) {
        log.info(user.toString());
        int i = userService.login(user);
        if (i == 1) {
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getUsername());
            return new Resp("success", "登陆成功!");
        } else if (i == -1) {
            return new Resp("error", "密码错误!");
        }
        return new Resp("error", "用户名不存在!");
    }
    //用户注册
    @RequestMapping(value="/register",method= RequestMethod.POST)
    @ResponseBody
    public Resp register(@RequestBody User user){

        int i = userService.registerUser(user);
        if (i == 1) {
            return new Resp("success", "注册成功!");
        } else if (i == -1) {
            return new Resp("error", "用户名重复，注册失败!");
        }
        return new Resp("error", "注册失败!");
    }
}
