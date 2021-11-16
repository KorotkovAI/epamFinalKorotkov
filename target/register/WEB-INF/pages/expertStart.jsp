<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Comodity expert start page</title>
    <link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
<body>
<body>
<h2>Comodity expert start page</h2>

<p>Hello <b><c:out value = "${user.getName()} " /> <c:out value = "${user.getSurname()}" />
</b></p>
<p>Today is <b> <c:out value = "${localDate}" />
</b></p>

<a href="/goodsAdd" id="button">Add new position</a>

<h2>List of Goods</h2>

<table border="2">
    <tr>
        <th>No.</th>
        <th>Name</th>
        <th>Amount</th>
        <th>Price</th>
        <th colspan="2">Operation</th>

    </tr>
    <c:forEach items="${goodsList}" var="goods">
        <tr>
            <td>${goods.getId()}
            </td>
            <td>${goods.getName()}
            </td>
            <td>${goods.getAmount()}
            </td>
            <td>${goods.getPrice()}
            </td>
            <td>
                <a href="/goodsUpdate?id=${goods.getId()}">Update</a>
            </td>
            <td>
                <a href="/deleteGoods?goods=${goods.getName()}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>

</body>
</html>
