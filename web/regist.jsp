<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!Doctype HTML>
<head>
    <base href="<%=basePath%>">

    <title>注册账号</title>
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
<body style="background: url(res/222.png);background-size:cover;font-family: 微软雅黑;">
    <div id="main">
        <center><h3>用户注册</h3></center>
        <form action="RegistServlet" method="post">
            <table>
                <tr>
                    <td align="right">用户名：</td>
                    <td><input type="text" name="username" value="${username}"/></td>
                </tr>
                <tr>
                    <td align="right">输入密码：</td>
                    <td><input type="password" name="password" /></td>
                </tr>
                <tr>
                    <td align="right">重复密码：</td>
                    <td><input type="password" name="rppw" /></td>
                    <td><font color="red"></font></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td align="center"><input type="submit" value="注册"/></td>
                </tr>
            </table>
        </form>
        <font color="red" size="2"> ${msg }</font>
    </div>
</body>
</head>
