<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Goods Update</title>
</head>
<body>
<h2>Goods Update</h2>
<a href="/expertStart">Back to list</a>
<form action="" method="post">

    <table>
        <tr>
            <td><label for="idGoods">Id: </label></td>
            <td><input type="text" id="idGoods" name="idGoods" value="${goodsForUpdate.getId()}" readonly="readonly">
            </td>
            <td></td>
        </tr>
        <tr>
            <td><label for="nameGoods">Name: </label></td>
            <td><input type="text" id="nameGoods" name="nameGoods" value="${goodsForUpdate.getName()}"></td>
        </tr>
        <tr>
            <td><label for="amountGoods">Amount: </label></td>
            <td><input type="number" id="amountGoods" name="amountGoods" value="${goodsForUpdate.getAmount()}"></td>
        </tr>
        <tr>
            <td><label for="priceGoods">Price: </label></td>
            <td><input type="text" id="priceGoods" name="priceGoods" value="${goodsForUpdate.getPrice()}"></td>
        </tr>

        <tr>
            <td><input type="submit" value="Update"></td>
            <td><input type="reset" value="Clear"></td>
        </tr>
    </table>
    <b><c:out value="${wrongMining}"/></b>
    <b><c:out value="${notFoundGoods}"/></b>
</form>
</body>
</html>
