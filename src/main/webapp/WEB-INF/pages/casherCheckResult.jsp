<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Check</title>
</head>
<body>
<h2>Check Goods</h2>
<a href="/checkAdd"><input type="button" value="Add to check"></a>

<%
    String mes = (String) request.getSession().getAttribute("not availible params");

%>

<p><h4><%=mes != null ? mes : ""%>

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
                <a href="/deleteFromCheck?id=${check.id}">Cancel</a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <th colspan="4">Total price</th>
        <th>${totalPrice}</th>
    </tr>
</table>
<tr>
    <td><a href="/createCheck"><input type="button" value="Create"></a></td>
    <td><a href="/casherStart"><input type="button" value="Reset"></a></td>
</tr>
</body>
</html>
