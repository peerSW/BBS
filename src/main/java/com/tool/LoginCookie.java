package com.tool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yedaran on 2018/1/24.
 */
public class LoginCookie {
    private static int DefaultMaxAge = 60 * 60 * 24 * 1;
    private static String autoLogin = "autoLogin";

    public static void addLoginCookie(HttpServletResponse response, HttpServletRequest request, String useranme, String userID) {
        deleteLoginCookie(response,request);
        addCookie(response, request, autoLogin, useranme + "。" + userID, DefaultMaxAge);
    }

    public static void addCookie(HttpServletResponse response, HttpServletRequest request, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(request.getContextPath());
        if (maxAge >= 0) cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static void deleteLoginCookie(HttpServletResponse response, HttpServletRequest request){
        Cookie[] cs = request.getCookies();
        if (cs != null) {
            for (Cookie c : cs) {
                if (c.getName().equals(autoLogin)) {
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
        }
    }

    public static List<String> getLoginCookie(HttpServletRequest request) {
        Cookie[] cs = request.getCookies();
        List<String> ID_username = new ArrayList<String>();
        String username;
        String userID;
        if (cs != null) {
            for (Cookie c : cs) {
                if (c.getName().equals("autoLogin")) {
                    username = c.getValue().split("。")[0];
                    userID = c.getValue().split("。")[1];
                    ID_username.add(0, userID);
                    ID_username.add(1, username);
                }
            }
        }
        return ID_username;
    }
}
