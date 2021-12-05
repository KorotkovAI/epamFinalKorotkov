<!doctype html>
<html lang="${language}">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Z report</title>
</head>

<body>
<%@include file="headerAdmin.jsp" %>
<h2><fmt:message key="header.text.zReport"/></h2>
<p><fmt:message key="zreport.text.shift"/> №<b> <c:out value="${closeShift.id}"/>
</b></p>
<p><fmt:message key="xreport.text.openedAt"/><b> <c:out value="${closeShift.openTime}"/>
</b></p>
<p><fmt:message key="zreport.text.sucClosed"/><b> <c:out value="${closeShift.closeTime}"/>
</b></p>

<h4><fmt:message key="xreport.text.checks"/></h4>
<c:forEach items="${mapNotReturned}" var="mapNotReturned">
    <p><fmt:message key="xreport.text.casher"/><b> <c:out value="${mapNotReturned.key.name}"/> <c:out
            value="  ${mapNotReturned.key.surname}"/>
    </b></p>

    <table border="2">
        <tr>
            <th>No.</th>
            <th><fmt:message key="casherstart.text.checkTime"/></th>
            <th><fmt:message key="casherstart.text.checkSum"/></th>

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
    <fmt:message key="xreport.text.sumOfChecks"/>:
    <c:out value="${mapNotReturned.getValue().stream().map(x -> x.getSum()).reduce( (a, b) -> a + b).get()}"/>
</c:forEach>

<c:out value="${mapNotReturned.key.name}"/>
<h2></h2>
<fmt:message key="xreport.text.totalsumchecks"/>: <c:out value="${sumNotReturned}"/>

<h4><fmt:message key="xreport.text.returnedChecks"/></h4>
<c:forEach items="${mapReturned}" var="mapReturned">
    <p><fmt:message key="xreport.text.casher"/><b> <c:out value="${mapReturned.key.name}"/> <c:out
            value="  ${mapReturned.key.surname}"/>
    </b></p>

    <table border="2">
        <tr>
            <th>No.</th>
            <th><fmt:message key="casherstart.text.checkTime"/></th>
            <th><fmt:message key="casherstart.text.checkSum"/></th>

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

<fmt:message key="xreport.text.sumOfNotReturned"/>: <c:out value="${sumReturned}"/>
<h2></h2>
<h2><fmt:message key="zreport.text.toOpenShift"/></h2>
<a href="/openShift"><input type="button" value="<fmt:message key="zreport.text.yes"/>"></a>
<a href="/"><input type="button" value="<fmt:message key="zreport.text.no"/>"></a>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
