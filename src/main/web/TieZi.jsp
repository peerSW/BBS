<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false"%>
<%--
  Created by IntelliJ IDEA.
  User: yedaran
  Date: 2018/1/25
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!Doctype HTML>
<head>
    <base href="<%=basePath%>">
    <title>${title}</title>
</head>
<body>
<div>
    <div style="float:right"><a href="/logout" onclick="return(confirm('您确认要注销嘛？') )"><input type="button" value="注销"></a></div>
    <a href="/BanKuai?BanKuaiID=${BanKuaiID}"><input type="button" value="返回板块"></a>
    <div>用户：<%=session.getAttribute("username")%></div>
</div>
<div>
    <p>
        <c:if test="${pageNum!=1}">
            <a href="TieZi?TieZiID=${TieZiID}&pageNum=1&BanKuaiID=${BanKuaiID}"><input type="button" value="首页"></a>
            <a href="TieZi?TieZiID=${TieZiID}&pageNum=${pageNum-1}&BanKuaiID=${BanKuaiID}"><input type="button" value="前一页"></a>
        </c:if>
        ${pageNum}
        <c:if test="${pageNum!=pageCount}">
            <a href="TieZi?TieZiID=${TieZiID}&pageNum=${pageNum+1}&BanKuaiID=${BanKuaiID}"><input type="button" value="下一页"></a>
            <a href="TieZi?TieZiID=${TieZiID}&pageNum=${pageCount}&BanKuaiID=${BanKuaiID}"><input type="button" value="尾页"></a>
        </c:if>
    </p>
</div>
<font color="red">${msg_post}</font>
<h2>${title}</h2>
<h3>用户：<%=session.getAttribute("username")%></h3>
<div>
    <c:forEach items="${HuiFuList}" var="HuiFu" varStatus="status">
        <div>
            <div>
                <p>${HuiFu.username}：</p>
                <p>${HuiFu.content}</p>
                <p>${(pageNum-1)*30+status.count}楼     回复时间：${HuiFu.createTime}</p>
                <hr color="blue"/>
            </div>
        </div>
    </c:forEach>
</div>

<font color="red">${msg_post}</font>
<form action="postHuiFu" method="post">
    <div>内容
        <p><textarea name="content" style="resize: none;width: 1000px;height: 400px;">${content}</textarea></p>
    </div>
    <div>
        <input type="hidden" name="TieZiID" value="${TieZiID}">
        <input type="hidden" name="BanKuaiID" value="${BanKuaiID}">
        <input type="submit" value="回帖"/>
    </div>
</form>
</body>
</html>
