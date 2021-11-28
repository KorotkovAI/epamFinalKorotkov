<!doctype html>
<html lang="en">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>Cash Register</title>
</head>
<body>
<section class="vh-100" style="background-color: #508bfc;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card shadow-2-strong" style="border-radius: 1rem;">
                    <form action="" method="post">

                        <div class="card-body p-5 text-center">
                            <h3 class="mb-5">Final project</h3>
                            <h3 class="mb-5">Korotkov Oleksandr</h3>
                            <h2 class="mb-5">Cash Register</h2>
                            <%
                                String mes = (String) request.getSession().getAttribute("not found user");
                            %>

                            <p><h6 style="color: red;"><%=mes != null ? mes : ""%>
                        </h6> </p>
                            <div class="form-outline mb-4">
                                <tr>
                                    <td><label for="login">Login: </label></td>
                                    <td><input type="text" id="login" name="login" autofocus></td>
                                </tr>
                            </div>

                            <div class="form-outline mb-4">
                                <tr>
                                    <td><label for="password">Password: </label></td>
                                    <td><input type="password" id="password" name="password" autofocus></td>
                                </tr>
                            </div>

                            <tr>
                                <td>
                                    <button class="btn btn-primary btn-lg" type="submit">Login</button>
                                </td>
                                <td>
                                    <button class="btn btn-primary btn-lg" type="reset">Reset</button>
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
