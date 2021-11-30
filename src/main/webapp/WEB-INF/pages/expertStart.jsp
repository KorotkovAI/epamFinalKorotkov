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
    <title>Comodity expert start page</title>
</head>
<body>
<body>
<h2 align="center">Comodity expert start page</h2>
<div align="center">
    <p>Hello <b><c:out value="${user.getName()} "/> <c:out value="${user.getSurname()}"/>
    </b></p>
    <p>Today is <b> <c:out value="${localDate}"/>
    </b></p>
    <a class="btn btn-primary" href="/goodsAdd" role="button">Add new position</a>
</div>

<h2 align="center">List of Goods</h2>

<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">No.</th>
        <th scope="col">Name</th>
        <th scope="col">Amount</th>
        <th scope="col">Price</th>
        <th class="text-center" scope="col" colspan="2">Operation</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${goodsList}" var="goods">
        <tr>
            <td>${goods.getId()}
            </td>
            <td>${goods.getName()}
            </td>
            <td>${goods.getAmount()}
            </td>
            <td>${goods.getPrice()}
            </td>
            <td>
                <a class="btn btn-primary" href="/goodsUpdate?id=${goods.getId()}" role="button">Update</a>
            </td>
            <td>
                <a class="btn btn-primary" href="/deleteGoods?goods=${goods.getName()}" role="button">Delete</a>
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

</body>
</html>
