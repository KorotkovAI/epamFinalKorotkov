<!doctype html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Casher Start Page</title>
</head>
<body>
<h2 align="center">Casher Start Page</h2>
<div align="center">
<p>Hello <b><c:out value="${user.getName()} "/> <c:out value="${user.getSurname()}"/>
</b></p>
<p>Today is <b> <c:out value="${localDate}"/>
</b></p>
<p>Open shift is № <b> <c:out value="${openshift.id}"/>
</b></p>
<a class="btn btn-primary" href="/checkAdd" role="button">Create new check</a>
</div>
<h2 align="center">List of Checks today</h2>
<%
    String mes = (String) request.getSession().getAttribute("check returned");
%>

<p><h4 style="color: red;" align="center"><%=mes != null ? mes : ""%>
</h4> </p>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">No.</th>
        <th scope="col">CheckTime</th>
        <th scope="col">CheckSum</th>
        <th scope="col">Operation</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${checksOfCasher}" var="check">
        <tr>
            <th scope="row">${check.id}
            </th>
            <td>${check.timestamp}
            </td>
            <td>${check.sum}
            </td>
            <td>
                <a class="btn btn-primary" href="/returnCheck?id=${check.id}" role="button">Return</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

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
