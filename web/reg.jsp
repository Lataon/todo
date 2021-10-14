<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--Registration-->
<html>
<head>
    <title>
        Reg page
    </title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}\resources\bootstrap\css\bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}\resources\bootstrap\css\custom.css"/>
    <script>
        function checkInput() {
            validate.uname.value === validate.uname.value.trim();
            if (validate.uname.value === "") {
                document.getElementById("errMsg").innerHTML = "User name field is empty";
                return false;
            }

            if (validate.psw.value === "") {
                document.getElementById("errMsg").innerHTML = "Password field is empty";
                return false;
            }

            if (validate.psw.value.length < 5 || validate.psw.value.length > 8) {
                document.getElementById("errMsg").innerHTML = "Not enough symbols in password field. Require more than 5 and less then 8";
                return false;
            }

            if (validate.psw.value !== validate.pswConfirm.value) {
                document.getElementById("errMsg").innerHTML = "Passwords do not match";
                return false;
            }

            validate.submit();
        }
    </script>
</head>
<body class="bodyBack">
<header class="header">
    <span class="headerLogo">User: guest</span>
    <div class="header-right">
        <a class="active" href="<c:url value="/"/>">Start Page</a>
        <a href="<c:url value="/login.jsp"/>">Login</a>
    </div>
</header>
<main>
        <div class="container regLogWidth">
            <div class="row mgTp">
                <form class="col-md-6 offset-md-3" name="validate" method="post" action="<c:url value='/reg'/>">
                    <h3 class="title"> Please register</h3>
                    <hr class="divisor">
                    <div class="form-group">
                        <label for="loginInput">Username</label>
                        <input type="text" class="form-control" name="uname" id="loginInput"
                               placeholder="Enter your login">
                    </div>
                    <div class="form-group">
                        <label for="inputPassword">Password</label>
                        <input type="password" class="form-control" name="psw" id="inputPassword"
                               placeholder="Input more than 5 and less then 8 symbol">
                    </div>
                    <div class="form-group">
                        <label for="pswConfirm">Password</label>
                        <input type="password" class="form-control" name="pswConfirm" id="pswConfirm"
                               placeholder="Repeat Password">
                    </div>
                    <button type="submit" class="btn btn-primary topBtn" onclick="return checkInput()">
                        <i class="fa fa-sign-in"></i> Registrate
                    </button>
                </form>
            </div>
        </div>

        <p id="errMsg" class="errorMsgStyle"></p>

        <c:if test="${not empty errorValidation}">
            <p class="errorMsgStyle" style="color: red">
                    ${errorValidation}
            </p>
        </c:if>
</main>

<footer class="fixed-bottom">
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>