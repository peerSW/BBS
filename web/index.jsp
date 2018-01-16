<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!Doctype HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>">

        <title>登陆账号</title>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="expires" content="0">
        <style type="text/css">
            #main{
                position:fixed;
                width: 400px;
                height: 300px;
                top:100%;
                left:50%;
                margin-top: -300px;
                margin-left:-200px;

            }
            td{
                padding: 5px;
            }
            input{
                width:100%;
                height:30px;
            }
        </style>
    <body style="background: url(res/login10.png);background-size:cover;font-family: 微软雅黑;">
        <div id="main">
            <center><h3>用户登录</h3></center>
            <form action="LoginServlet" method="post">
                <table>
                    <tr>
                        <td align="right">账号：</td>
                        <td><input type="text" name="username" value="${username}"/> </td>
                        <td><font color="red">${msg_username}</font></td>
                    </tr>
                    <tr>
                        <td align="right">密码：</td>
                        <td><input type="password" name="password"/> </td>
                        <td><font color="red">${msg_password}</font> </td>
                    </tr>
<%--                    <tr>
                        <td align="right">验证码：</td>
                        <td valigin="middle"><input type="text" name="verifycode"/></td>
                        <td valigin="middle"><img src="VerifyCodeServlet" id="verify" onclick="document.getElementById('verify').src='VerifyCodeServlet?'+Math.random();"> </td>
                        <td><font color="red">${msg_verify}</font> </td>
                    </tr>--%>
                    <tr>
                        <td>&nbsp;</td>
                        <td align="center"><input type="submit" value="登陆"/> </td>
                    </tr>
                </table>
            </form>
            <a href="regist.jsp" >没有账号？点击注册</a>
        </div>
    </body>
    </head>
</html>