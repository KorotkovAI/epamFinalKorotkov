<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Current Check</title>
</head>
<body>
<h2>Check № ${check.id}</h2>

  <table border="2">
    <tr>
      <th>id</th>
      <th>Name</th>
      <th>Amount</th>
      <th>Price</th>
      <th>Total</th>


    </tr>
    <c:forEach items="${goods}" var="goods">
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
      </tr>
    </c:forEach>
    <tr>
      <th colspan="4">Total price</th>
      <th>${check.sum}</th>
    </tr>
  </table>
</body>
</html>

