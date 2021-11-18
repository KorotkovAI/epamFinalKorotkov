<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Check Add</title>

</head>
<body>
<h2>New Check</h2>
<%
    String mes = (String) request.getSession().getAttribute("not availible params");

%>
<p><h4><%=mes != null ? mes : ""%>
</h4> </p>
<a href="/casherStart">Back to list</a>
<form action="" method="post">

    <p><h4>${mes}</h4> </p>
    <table>
        <td>Name:</td>
        <td><select name="namePos">
            <c:forEach items="${goodsList}" var="goods">
                <option value="${goods.name}"> ${goods.name}
                </option>
            </c:forEach>
        </select></td>
        </tr>
        <tr>
            <td>Amount:
            </td>
            <td><input type="number" name="amountPos"></td>
        </tr>

        <tr>
            <td><input type="submit" value="Add"></td>
            <td><input type="reset" value="Clear"></td>
        </tr>
    </table>
</form>

</body>
</html>
