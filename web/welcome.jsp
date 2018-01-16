<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!Doctype HTML>
<head>
    <base href="<%=basePath%>">

    <title>欢迎界面</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

</head>

<body>
<%session.putValue("username",request.getParameter("username"));%>
<h1>这是主页</h1>
<h2>${msg_welcome}</h2>
<h3><%=session.getAttribute("username")%></h3>
</body>
</html>
