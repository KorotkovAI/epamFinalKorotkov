<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Open Shift</title>
</head>
<body>
<%@include file="headerAdmin.html" %>
<p>Shift №<b> <c:out value="${openShift.id}"/>
</b></p>
<p>successfully opened at<b> <c:out value="${openShift.openTime}"/>
</b></p>

<h2></h2>
<h2>Do you want to make X report?</h2>
<a href="/adminTodayChecks"><input type="button" value="Yes"></a>
<a href="/"><input type="button" value="No"></a>


</body>
</html>
