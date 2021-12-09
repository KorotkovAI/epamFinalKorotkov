<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <title>User Update</title>
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
                            <h4></h4>
                            <b style="color: red;"><c:out value="${wrongMining}"/></b>
                            <b style="color: red;"><c:out value="${notFoundUser}"/></b>

                            <div class="form-outline mb-4">
                                <tr>
                                    <td><label for="idUser">Id: </label></td>
                                    <td><input type="text" id="idUser" name="idUser" readonly="readonly"
                                               value="${userForUpdate.getId()}"></td>
                                </tr>
                            </div>
                            <div class="form-outline mb-4">
                                <tr>
                                    <td><label for="loginUser"><fmt:message key="userlist.text.login"/>: </label></td>
                                    <td><input type="text" id="loginUser" value="${userForUpdate.getLogin()}"
                                               name="loginUser"></td>
                                </tr>
                            </div>
                            <div class="form-outline mb-4">
                                <tr>
                                    <td><label for="nameUser"><fmt:message key="userlist.text.name"/>: </label></td>
                                    <td><input type="text" id="nameUser" value="${userForUpdate.getName()}"
                                               name="nameUser"></td>
                                </tr>
                            </div>
                            <div class="form-outline mb-4">
                                <tr>
                                    <td><label for="surnameUser"><fmt:message key="userlist.text.surname"/>: </label>
                                    </td>
                                    <td><input type="text" id="surnameUser" value="${userForUpdate.getSurname()}"
                                               name="surnameUser"></td>
                                </tr>
                            </div>
                            <div class="form-outline mb-4">
                                <tr>
                                    <td><label for="passUser"><fmt:message key="login.text.password"/>: </label></td>
                                    <td><input type="password" id="passUser" value="${userForUpdate.getPassword()}"
                                               name="passUser"></td>
                                </tr>
                            </div>
                            <div class="form-outline mb-4">
                                <td><fmt:message key="userlist.text.role"/>:</td>
                                <td><select name="role">
                                    <c:forEach items="${roles}" var="role">
                                        <option selected="${role.name.equals(userForUpdate.getRoleName().getName())}">
                                                ${role.name}
                                        </option>
                                    </c:forEach>
                                </select></td>
                            </div>
                            <tr>
                                <td>
                                    <button class="btn btn-primary btn-lg" type="submit"><fmt:message
                                            key="expertstart.text.update"/></button>
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
        crossorigin="anonymous">

</script>
</body>
</html>
