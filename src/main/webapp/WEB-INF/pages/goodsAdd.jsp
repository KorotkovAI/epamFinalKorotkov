<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Goods Add</title>
</head>
<body>
<h2>Goods Add</h2>
<a href="/expertStart"><input type="button" value="Back to list"></a>
<form action="" method="post">
    <%
        String mes = (String) request.getSession().getAttribute("not save goods");

    %>
    <p><h4><%=mes != null ? mes : ""%>
</h4> </p>
    <table>
        <tr>
            <td><label for="nameGoods">Name: </label></td>
            <td><input type="text" id="nameGoods" name="nameGoods"></td>
        </tr>
        <tr>
            <td><label for="amountGoods">Amount: </label></td>
            <td><input type="number" id="amountGoods" name="amountGoods"></td>
        </tr>
        <tr>
            <td><label for="priceGoods">Price: </label></td>
            <td><input type="text" id="priceGoods" name="priceGoods"></td>
        </tr>

        <tr>
            <td><input type="submit" value="Add"></td>
            <td><input type="reset" value="Clear"></td>
        </tr>
    </table>
</form>

</body>
</html>
