<!doctype html>
<html lang="${language}">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup" >
            <div class="navbar-nav">
                <a class="nav-link" href="/adminTodayChecks"><fmt:message key="header.text.xReport"/></a>
                <a class="nav-link" href="/adminTodayClose"><fmt:message key="header.text.zReport"/></a>
                <a class="nav-link" href="/users"><fmt:message key="header.text.users"/></a>
            </div>
        </div>
    </div>
</nav>
</html>