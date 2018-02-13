package com.filter;

import com.tool.LoginCookie;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by yedaran on 2018/1/18.
 */


@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    public FilterConfig config;
    public void destroy() {
        this.config=null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        List<String> ID_username = LoginCookie.getLoginCookie(httpRequest);
        String path = httpRequest.getRequestURI();
        String userID=null;
        if(((HttpServletRequest) request).getSession().getAttribute("userID")!=null)
        {userID=((HttpServletRequest) request).getSession().getAttribute("userID").toString();}
        //不过滤登陆界面
        if (isPass(path)){
            chain.doFilter(request, response);
        }
        else {
            //如果检测到cookies或者session中的username则放行
            if(userID!=null){
                chain.doFilter(request, response);
                return;
            }
            if ( (ID_username != null && !ID_username.isEmpty())) {
                HttpSession session = ((HttpServletRequest) request).getSession();
                session.setAttribute("userID", ID_username.get(0));
                session.setAttribute("username", ID_username.get(1));
                chain.doFilter(request, response);
                return;
            }
            //没检测到cookies就返回登陆界面
            else {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
                return;
            }
        }
        //测试时一律放行
//        chain.doFilter(request,response);
    }

    public void init(FilterConfig config) throws ServletException {
        this.config=config;
    }

    private boolean isPass(String path){
        String urlPass = this.config.getInitParameter("passUrl");
        for(String url:urlPass.split(";") ){
            if(path.contains(url) ){
                return true;
            }
        }
        return false;
    }

}


