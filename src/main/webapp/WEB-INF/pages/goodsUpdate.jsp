<%@ page import="com.cashRegister.model.Goods" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Goods Update</title>
</head>
<body>
<h2>Goods Update</h2>
<form action="" method="post">
    <%
        Goods goods = (Goods) request.getSession().getAttribute("goods");
        String mes = (String) request.getAttribute("not found goods");

    %>
    <p><h4><%=mes != null ? mes : ""%></h4> </p>
    <table>
        <tr>
            <td><label for="idGoods">Id: </label></td>
            <td><input type="text" id="idGoods" name="idGoods" value="<%=goods.getId()%>" readonly="readonly"></td>
            <td></td>
        </tr>
        <tr>
            <td><label for="nameGoods">Name: </label></td>
            <td><input type="text" id="nameGoods" name="nameGoods" value="<%=goods.getName()%>"></td>
        </tr>
        <tr>
            <td><label for="amountGoods">Amount: </label></td>
            <td><input type="number" id="amountGoods" name="amountGoods" value="<%=goods.getAmount()%>"></td>
        </tr>
        <tr>
            <td><label for="priceGoods">Price: </label></td>
            <td><input type="text" id="priceGoods" name="priceGoods" value="<%=goods.getPrice()%>"></td>
        </tr>

        <tr>
            <td><input type="submit" value="Update"></td>
            <td><input type="reset" value="Clear"></td>
        </tr>
    </table>
    <p><h4><%=mes != null ? mes : ""%></h4> </p>
</form>
</body>
</html>
