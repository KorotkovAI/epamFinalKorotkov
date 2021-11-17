<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Casher Start Page</title>
</head>
<body>
<h2>Casher Start Page</h2>

<p>Hello <b><c:out value = "${user.getName()} " /> <c:out value = "${user.getSurname()}" />
</b></p>
<p>Today is <b> <c:out value = "${localDate}" />
</b></p>
<p>Open shift is <b> <c:out value = "${openshift.id}" />
</b></p>

</body>
</html>
