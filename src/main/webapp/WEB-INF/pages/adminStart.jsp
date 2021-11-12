<%@ page import="com.cashRegister.model.User" %>
<%@ page import="java.time.LocalDate" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin start page</title>
</head>
<body>
<%@include file="headerAdmin.html"%>
<h2>Admin start page</h2>
<%
    User user = (User) request.getAttribute("user");
%>

<p>Hello <b><%=user.getName()%> <%=user.getSurname()%></b> </p>
<p>Today is <b><%=LocalDate.now()%></b> </p>
</body>
</html>
