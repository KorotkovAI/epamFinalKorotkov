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
    <title>Open Shift</title>
</head>
<body>
<%@include file="headerAdmin.jsp" %>
<p><fmt:message key="zreport.text.shift"/> №<b> <c:out value="${openShift.id}"/>
</b></p>
<p><fmt:message key="openshift.text.suc"/> <fmt:message key="xreport.text.openedAt"/><b> <c:out value="${openShift.openTime}"/>
</b></p>

<h2></h2>
<h2><fmt:message key="openshift.text.toOpenShift"/></h2>
<a href="/adminTodayChecks" class="btn btn-primary" role="button"><fmt:message key="zreport.text.yes"/></a>
<a href="/" class="btn btn-primary" role="button"><fmt:message key="zreport.text.no"/></a>

</body>
</html>
