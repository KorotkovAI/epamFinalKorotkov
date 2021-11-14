
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cash Register</title>
</head>
<body>
<div id="start" center>
<h3>Final project</h3>
<h3>Korotkov Oleksandr</h3>
<h2>Cash Register</h2>
</div>
<%
    String mes = (String) request.getAttribute("not found user");
%>

<p><h4><%=mes != null ? mes : ""%></h4> </p>
<form action="" method="post">
    <table center>
        <tr>
            <td><label for="login">Login: </label></td>
            <td><input type="text" id="login" name="login" autofocus></td>
        </tr>
        <tr>
            <td><label for="password">Password: </label></td>
            <td><input type="password" id="password" name="password" autofocus></td>
        </tr>

        <tr>
            <td><input type="submit" value="Login"></td>
            <td><input type="reset" value="Reset"></td>
        </tr>
    </table>
</form>

</body>
</html>
