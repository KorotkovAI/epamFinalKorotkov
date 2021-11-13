<%@ page import="com.cashRegister.model.User" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cashRegister.model.Goods" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Comodity expert start page</title>
</head>
<body>
<body>
<h2>Comodity expert start page</h2>
<%
    User user = (User) request.getSession().getAttribute("user");
    List<Goods> goodsList = (List<Goods>)request.getSession().getAttribute("goodsList");
%>

<p>Hello <b><%=user.getName()%> <%=user.getSurname()%></b> </p>
<p>Today is <b><%=LocalDate.now()%></b> </p>

<h2>List of Tasks</h2>
<table border="2">
    <tr>
        <th>No.</th>
        <th>Name</th>
        <th>Priority</th>
        <th>Priority</th>
    </tr>
    <%
            for (Goods goods : goodsList) { %>
<tr>
    <td><%=goods.getId()%></td>
    <td><%=goods.getName()%></td>
    <td><%=goods.getAmount()%></td>
    <td><%=goods.getPrice()%></td>
    </tr>
<%
    }
%>
</table>

</body>

</body>
</html>
