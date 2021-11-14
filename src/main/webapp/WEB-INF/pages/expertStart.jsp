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
    List<Goods> goodsList = (List<Goods>) request.getSession().getAttribute("goodsList");
%>

<p>Hello <b><%=user.getName()%> <%=user.getSurname()%>
</b></p>
<p>Today is <b><%=LocalDate.now()%>
</b></p>

<h2>List of Goods</h2>
<table border="2">
    <tr>
        <th>No.</th>
        <th>Name</th>
        <th>Amount</th>
        <th>Price</th>
        <th colspan="2">Operation</th>

    </tr>
    <%
        for (Goods goods : goodsList) { %>
    <tr>
        <td><%=goods.getId()%>
        </td>
        <td><%=goods.getName()%>
        </td>
        <td><%=goods.getAmount()%>
        </td>
        <td><%=goods.getPrice()%>
        </td>
        <td>
            <a href="/goodsUpdate?id=<%=goods.getId()%>">Update</a>
        </td>
        <td>
            <a href="/deleteGoods?id=<%=goods.getId()%>">Delete</a>
        </td>
    </tr>
    <%
        }
    %>
</table>

</body>

</body>
</html>
