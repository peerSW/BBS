package com.Login;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;

import com.User.UserDao;
import  com.regular.UserReg;

public class RegistServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String rppw=request.getParameter("rppw");

        UserReg Rg=new UserReg();               //使用正则表达式检测用户名
        if( !UserReg.validateUserName(username) ){
            request.setAttribute("username",username);
            request.setAttribute("msg","用户名支持中英文（包括全角字符）、数字、下划线和减号 （全角及汉字算两位）,长度为4-20位,中文按二位计数");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }

        if(!rppw.equals(password)){
            request.setAttribute("username",username);
            request.setAttribute("msg","两次密码输入不一致，请重新输入");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }

        boolean result=new UserDao().addUser(username,password);

        if(result){
            request.setAttribute("username",username);
            request.setAttribute("msg","恭喜！用户名："+username+" 注册成功！");
            request.getRequestDispatcher("/index.jsp").forward(request,response);
            return;
        }
        else {
            request.setAttribute("username",username);
            request.setAttribute("msg","对不起，该用户名已被注册，请使用其他用户名");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
    }
}