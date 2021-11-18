<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Check</title>
</head>
<body>
<h2>Check Goods</h2>
<a href="/checkAdd">Add position to check</a>
<table border="2">
    <tr>
        <th>No.</th>
        <th>Name</th>
        <th>Amount</th>
        <th>Price</th>
        <th>Total</th>
        <th>Operation</th>

    </tr>
    <c:forEach items="${goodsForCheck}" var="goods">
        <tr>
            <td>${goods.id}
            </td>
            <td>${goods.name}
            </td>
            <td>${goods.amount}
            </td>
            <td>${goods.price}
            </td>
            <td>${goods.amount*goods.price}
            </td>
            <td>
                <a href="/returnCheck?id=${check.id}">Cancel</a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <th colspan="4">Total price</th>
        <th>${totalPrice}</th>
    </tr>
</table>
<tr>
    <td><input type="submit" value="Create"></td>
    <td><input type="reset" value="Reset"></td>
</tr>
</body>
</html>
