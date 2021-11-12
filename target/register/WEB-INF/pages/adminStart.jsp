<%@ page import="com.cashRegister.model.User" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 011 11.11.21
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin start page</title>
</head>
<body>
<h2>Admin start page</h2>
<%
    User user = (User) request.getAttribute("user");
%>

<p>id: <b><%=user.getId()%></b> </p>

</body>
</html>
