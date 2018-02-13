<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!Doctype HTML>
<head>
    <base href="<%=basePath%>">
</head>
    <title>主页</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
<body>
<div>
    <div style="float:right"><a href="/logout" onclick="return(confirm('您确认要注销嘛？') )"><input type="button" value="注销"></a></div>
    <div>用户：<%=session.getAttribute("username")%></div>
</div>
<font color="red">${msg_post}</font>
<div>板块列表</div>
    <c:forEach items="${BanKuaiList}" var="BanKuai">
            <div>
                <p><a href="BanKuai?BanKuaiID=${BanKuai.banKuaiID}&pageNum=1">板块名：${BanKuai.topic}
                </a></p>
                <p>描述：${BanKuai.description}
                </p>
                <p>帖子数：${BanKuai.count}
                </p>
                <hr color="blue"/>
            </div>
    </c:forEach>
</body>
