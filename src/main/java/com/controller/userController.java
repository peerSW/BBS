package com.controller;

import com.tool.UserReg;
import com.service.UserService;
import com.tool.LoginCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yedaran on 2018/2/6.
 */
@Controller
public class userController {
    @Autowired
    UserService userService;

    //登陆管理
    @RequestMapping("/login")
    public  String login(HttpServletRequest request, Model model, HttpServletResponse response)throws IOException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String userID=userService.checkPW(username,password);
        if(userID==null){
            model.addAttribute("username",username);
            model.addAttribute("msg_password","用户名不存在或密码错误");
            return "login";
        }
        else{
            LoginCookie.addLoginCookie(response,request,username,userID);
            request.getSession().setAttribute("userID",userID);
            request.getSession().setAttribute("username",username);
            return "redirect:/index";
        }
    }

    @RequestMapping("/regist")
    public String regist(HttpServletRequest request, Model model)throws IOException{
        request.setCharacterEncoding("utf-8");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String rppw=request.getParameter("rppw");
        UserReg Rg=new UserReg();               //使用正则表达式检测用户名
        if( !Rg.validateUserName(username) ){
            model.addAttribute("username",username);
            model.addAttribute("msg","用户名支持中英文（包括全角字符）、数字、下划线和减号 ，长度为4-20位");
            return "regist";
        }
        if(!rppw.equals(password)){
            model.addAttribute("username",username);
            model.addAttribute("msg","两次密码输入不一致，请重新输入");
            return "regist";
        }
        boolean result=userService.addUser(username,password);
        if(result){
            model.addAttribute("username",username);
            model.addAttribute("msg","恭喜！用户名："+username+" 注册成功！");
            return "login";
        }
        else {
            model.addAttribute("username",username);
            model.addAttribute("msg","对不起，该用户名已被注册，请使用其他用户名");
            return "regist";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, Model model, HttpServletResponse response)throws IOException{
        request.setCharacterEncoding("utf-8");
        LoginCookie.deleteLoginCookie(response,request);
        request.getSession().removeAttribute("userID");
        request.getSession().removeAttribute("username");
        model.addAttribute("msg","注销成功");
        return "login";

    }
}
