<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/newSignInPage.css">

<body>

<div class="wrapper fadeInDown">
    <div id="formContent">
        <!-- Tabs Titles -->
        <h2 class="active">SIGN IN</h2>
        <h2 class="inactive underlineHover">
            <a class="underlineHover" href="<c:url value="/controller?command=goToRegistrationPage"/>">REGISTARION </a></h2>

        <!-- Login Form -->
        <form action="controller" method="post">
            <input type="text" class="fadeIn second" placeholder="Login" name="login">
            <input type="password" class="fadeIn third" placeholder="Password" name="password">
            <input type="hidden" name="command" value="login">
            <input type="submit" class="fadeIn fourth" value="Log In">
        </form>
        <div>
            <c:if test="${wrongInput == true}">
                <p id="invalidInput">Incorrect login or password</p>
                <c:remove var="wrongInput" scope="session" />
            </c:if>

        </div>
<%--        <form action="controller" method="post">--%>
<%--            <input type="hidden" name="command" value="goToMapPage">--%>
<%--            <input type="submit"  name="submit" value="map" >--%>
<%--        </form>--%>

        <!-- Remind Passowrd -->
        <div id="formFooter">
            <a class="underlineHover" href="#">Forgot Password?</a>
        </div>

    </div>
</div>
<jsp:include page="header.jsp" />

</body>
</html>
