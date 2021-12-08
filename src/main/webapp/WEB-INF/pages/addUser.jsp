<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<html lang="${language}">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Goods Add</title>
</head>
<body style="background-color: #508bfc;">
<section class="vh-100">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card shadow-2-strong" style="border-radius: 1rem;">
                    <form action="" method="post">

                        <div class="card-body p-5 text-center">
                            <h2><fmt:message key="userlist.text.addUser"/></h2>
                            <a class="btn btn-primary" href="/users" role="button"><fmt:message
                                    key="goodsadd.text.backtolist"/></a>

                            <%
                                String mes = (String) request.getSession().getAttribute("not save user");

                            %>
                            <p><h6 style="color: red;"><%=mes != null ? mes : ""%>
                        </h6> </p>
                            </h6> </p>
                            <div class="form-outline mb-4">
                                <tr>
                                    <td><label for="login"><fmt:message key="userlist.text.login"/>: </label></td>
                                    <td><input type="text" id="login" name="login" autofocus></td>
                                </tr>
                            </div>

                            <div class="form-outline mb-4">
                                <tr>
                                    <td><label for="name"><fmt:message key="userlist.text.name"/>: </label></td>
                                    <td><input type="text" id="name" name="name" autofocus></td>
                                </tr>
                            </div>
                            <div class="form-outline mb-4">
                                <tr>
                                    <td><label for="surname"><fmt:message key="userlist.text.surname"/>: </label></td>
                                    <td><input type="text" id="surname" name="surname" autofocus></td>
                                </tr>
                            </div>
                            <div class="form-outline mb-4">
                                <tr>
                                    <td><label for="password"><fmt:message key="login.text.password"/>: </label></td>
                                    <td><input type="password" id="password" name="password" autofocus></td>
                                </tr>
                            </div>
                            <div class="form-outline mb-4">
                                <td><fmt:message key="userlist.text.role"/>:</td>
                                <td><select name="role">
                                <c:forEach items="${roles}" var="role">
                                    <option value="${role.name}"> ${role.name}
                                    </option>
                                </c:forEach>
                            </select></td>
                            </div>

                            <tr>
                                <td>
                                    <button class="btn btn-primary btn-lg" type="submit"><fmt:message
                                            key="goodsadd.text.add"/></button>
                                </td>
                                <td>
                                    <button class="btn btn-primary btn-lg" type="reset"><fmt:message
                                            key="goodsadd.text.clear"/></button>
                                </td>
                            </tr>
                            <hr class="my-4">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
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
