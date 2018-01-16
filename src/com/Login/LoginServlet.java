package com.Login;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;

import com.User.UserDao;

public class LoginServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String result=new UserDao().checkPW(username,password);

        if(result==null){
            request.setAttribute("username",username);
            request.setAttribute("msg_username","用户名不存在");
            request.getRequestDispatcher("/index.jsp").forward(request,response);
            return;
        }
        if(result.equals("false")){
            request.setAttribute("username",username);
            request.setAttribute("msg_password","密码错误");
            request.getRequestDispatcher("/index.jsp").forward(request,response);
            return;
        }
        if(result.equals("true")){
            request.setAttribute("msg_welcome","用户"+username+"登陆成功");
            request.getRequestDispatcher("/welcome.jsp").forward(request,response);
            return;
        }
    }
}