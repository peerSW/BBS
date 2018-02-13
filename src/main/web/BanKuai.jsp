<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!Doctype HTML>
<head>
    <base href="<%=basePath%>">

    <title>${topic}</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
<div>
    <div><a href="/index"><input type="button" value="返回主页"></a> </div>
    <div style="float:right"><a href="/logout" onclick="return(confirm('您确认要注销嘛？') )"><input type="button" value="注销"></a></div>
    <div>用户：<%=session.getAttribute("username")%></div>
</div>
<font color="red">${msg_post}</font>
<div>
    <h3>板块：${topic}</h3>
    <c:forEach items="${TieZiList}" var="TieZi">
            <div>
                <p><a href="TieZi?TieZiID=${TieZi.tieZiID}&pageNum=1&BanKuaiID=${BanKuaiID}">标题：${TieZi.title}
                </a></p>
                <p>作者：${TieZi.username}
                </p>
                <p>最新回复时间：${TieZi.updateTime}   发帖时间：${TieZi.createTime}    回复数：${TieZi.count}
                </p>
                <hr color="blue"/>
            </div>
    </c:forEach>
</div>
<div>

</div>
<font color="red">${msg_post}</font>
<form action="/PostTieZi" method="post">
<div>
    <p>标题<input type="text" name="title" size="50" maxlength="30" value="${title}"/></p>
</div>
<div>内容
    <p><textarea name="content" style="resize: none;width: 1000px;height: 400px;">${content}</textarea></p>
</div>
<div>
    <input type="hidden" name="BanKuaiID" value="${BanKuaiID}">
    <input type="submit" value="发帖"/>
</div>
</form>
<div>
    <p>
        <c:if test="${pageNum!=1}">
            <a href="BanKuai?pageNum=1&BanKuaiID=${BanKuaiID}">首页</a>
            <a href="BanKuai?pageNum=${pageNum-1}&BanKuaiID=${BanKuaiID}">前一页</a>
        </c:if>
        ${pageNum}
        <c:if test="${pageNum!=pageCount}">
            <a href="BanKuai?pageNum=${pageNum+1}&BanKuaiID=${BanKuaiID}">下一页</a>
            <a href="BanKuai?pageNum=${pageCount}&BanKuaiID=${BanKuaiID}">尾页</a>
        </c:if>

    </p>
</div>
</body>
</html>
