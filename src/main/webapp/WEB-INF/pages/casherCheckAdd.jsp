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

    <title>Check Add</title>
</head>
<body style="background-color: #508bfc;">
<section class="vh-100">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card shadow-2-strong" style="border-radius: 1rem;">
                    <form action="" method="post">
                        <div class="card-body p-7 text-center">
                            <h2><fmt:message key="cashercheckadd.text.newCheck"/></h2>
                            <%
                                String mes = (String) request.getSession().getAttribute("not availible params");
                            %>

                            <p><h6 style="color: red;">
                            <%=mes != null ? mes : ""%>
                        </h6> </p>

                            <a class="btn btn-primary" href="/casherStart" role="button"><fmt:message
                                    key="goodsadd.text.backtolist"/></a>
                            <table>
                                <td><fmt:message key="expertstart.text.name"/>:</td>
                                <td><select name="namePos">
                                    <c:forEach items="${goodsList}" var="goods">
                                        <option value="${goods.name}"> ${goods.name}
                                        </option>
                                    </c:forEach>
                                </select></td>
                                <tr>
                                    <td><fmt:message key="expertstart.text.amount"/>:</td>
                                    <td><input type="number" name="amountPos"></td>
                                </tr>
                                <div class="center">
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
                                </div>
                                <hr class="my-4">
                            </table>
                        </div>
                    </form>
                </div>
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
