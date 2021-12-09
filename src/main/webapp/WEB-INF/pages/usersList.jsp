<!doctype html>
<html lang="${language}">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
    <title>Users list</title>
</head>
<body>
<%@include file="headerAdmin.jsp"%>

<h2 align="center"><fmt:message key="userlist.text.listUsers"/></h2>
<div align="center">
    <a class="btn btn-primary" href="/userAdd" role="button"><fmt:message key="userlist.text.addUser"/></a>
</div>

<table class="table" id="goodsListTable">
    <thead class="thead-dark">
    <tr>
        <th scope="col">No.</th>
        <th scope="col"><fmt:message key="userlist.text.login"/></th>
        <th scope="col"><fmt:message key="userlist.text.name"/></th>
        <th scope="col"><fmt:message key="userlist.text.surname"/></th>
        <th scope="col"><fmt:message key="userlist.text.role"/></th>
        <th class="text-center" scope="col" colspan="2"><fmt:message key="expertstart.text.operations"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${usersList}" var="user">
        <tr>
            <td>${user.getId()}
            </td>
            <td>${user.getLogin()}
            </td>
            <td>${user.getName()}
            </td>
            <td>${user.getSurname()}
            </td>
            <td>${user.getRoleName().getName()}
            </td>
            <td>
                <a class="btn btn-primary" href="/userUpdate?id=${user.getId()}" role="button"><fmt:message
                        key="expertstart.text.update"/></a>
            </td>
            <td>
                <a class="btn btn-primary" href="/deleteUser?id=${user.getId()}" role="button"><fmt:message
                        key="expertstart.text.delete"/></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>


    <%--For displaying Previous link except for the 1st page --%>
    <c:if test="${currentPage != 1}">
        <td><a href="/users?page=${currentPage - 1}">Previous</a></td>
    </c:if>

    <%--For displaying Page numbers.
    The when condition does not display a link for the current page--%>
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="/users?page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>

    <%--For displaying Next link --%>
    <c:if test="${currentPage lt noOfPages}">
        <td><a href="/users?page=${currentPage + 1}">Next</a></td>
    </c:if>


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

</body>
</html>
