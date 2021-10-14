<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <title>TO-DO application</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}\resources\bootstrap\css\bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}\resources\bootstrap\css\custom.css"/>
</head>

<body class="bodyBack">

<div class="header">
    <span class="headerLogo">User: guest</span>
    <div class="header-right">
        <a class="active" href="<c:url value="/login.jsp"/>">Login</a>
        <a href="<c:url value="/reg.jsp"/>">Registrate</a>
    </div>
</div>

<main>
    <c:if test="${not empty param.errorDAO or not empty errorDAO}">
        <p class="alert-danger">
            Sorry, critical error on the server: ${param.errorDAO}${errorDAO}..
            Please, repeat operation in several minutes
        </p>
    </c:if>
    <div>
        <h1 class="projTitle">Welcome to toDoList app</h1>
    </div>
</main>


<footer class="fixed-bottom">
    <jsp:include page="footer.jsp"/>
</footer>

</body>
</html>