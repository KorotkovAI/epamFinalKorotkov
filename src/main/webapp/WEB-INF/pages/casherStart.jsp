<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Casher Start Page</title>
</head>
<body>
<h2>Casher Start Page</h2>

<p>Hello <b><c:out value="${user.getName()} "/> <c:out value="${user.getSurname()}"/>
</b></p>
<p>Today is <b> <c:out value="${localDate}"/>
</b></p>
<p>Open shift is № <b> <c:out value="${openshift.id}"/>
</b></p>
<a href="/checkAdd"><input type="button" value="Create new check"></a>

<h2>List of Checks today</h2>
<%
    String mes = (String) request.getSession().getAttribute("check returned");
%>

<p><h4><%=mes != null ? mes : ""%></h4> </p>

<table border="2">
    <tr>
        <th>No.</th>
        <th>CheckTime</th>
        <th>CheckSum</th>
        <th>Operation</th>

    </tr>
    <c:forEach items="${checksOfCasher}" var="check">
        <tr>
            <td>${check.id}
            </td>
            <td>${check.timestamp}
            </td>
            <td>${check.sum}
            </td>
            <td>
                <a href="/returnCheck?id=${check.id}"><input type="button" value="Return"></a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
