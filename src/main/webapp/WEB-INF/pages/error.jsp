<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
<h2>Error page</h2>

${requestScope['javax.servlet.error.message']}
</body>
</html>
