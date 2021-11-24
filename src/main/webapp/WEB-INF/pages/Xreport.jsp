<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>X report</title>
</head>
<body>
<%@include file="headerAdmin.html" %>
<h2>X report</h2>
<p>Open shift №<b> <c:out value="${openShift.id}"/>
</b></p>
<p>is opened at<b> <c:out value="${openShift.openTime}"/>
</b></p>

<h4>Checks</h4>
<c:forEach items="${mapNotReturned}" var="mapNotReturned">
    <p>Casher<b> <c:out value="${mapNotReturned.key.name}"/> <c:out value="  ${mapNotReturned.key.surname}"/>
    </b></p>

    <table border="2">
        <tr>
            <th>No.</th>
            <th>CheckTime</th>
            <th>CheckSum</th>

        </tr>
        <c:forEach items="${mapNotReturned.value}" var="check">
            <tr>
                <td>${check.id}
                </td>
                <td>${check.timestamp}
                </td>
                <td>${check.sum}
                </td>
            </tr>
        </c:forEach>
    </table>
    Sum of checks current Casher: <c:out value="${mapNotReturned.getValue().stream().map(x -> x.getSum()).reduce( (a, b) -> a + b).get()}"/>
</c:forEach>

<c:out value="${mapNotReturned.key.name}"/>
<h2></h2>
Total sum of checks: <c:out value="${sumNotReturned}"/>

<h4>Returned Checks</h4>
<c:forEach items="${mapReturned}" var="mapReturned">
    <p>Casher<b> <c:out value="${mapReturned.key.name}"/> <c:out value="  ${mapReturned.key.surname}"/>
    </b></p>

    <table border="2">
        <tr>
            <th>No.</th>
            <th>CheckTime</th>
            <th>CheckSum</th>

        </tr>
        <c:forEach items="${mapReturned.value}" var="check">
            <tr>
                <td>${check.id}
                </td>
                <td>${check.timestamp}
                </td>
                <td>${check.sum}
                </td>
            </tr>
        </c:forEach>
    </table>
</c:forEach>

Total sum of returned checks: <c:out value="${sumReturned}"/>
<h2></h2>
<a href="/returnCheck"><input type="button" value="Make Z report"></a>

</body>
</html>
