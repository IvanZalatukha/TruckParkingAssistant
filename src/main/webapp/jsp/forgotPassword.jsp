<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/signInPage.css">

<body>

<div class="wrapper fadeInDown">
    <div id="formContent">
        <h2 class="active">Please enter your login and email address</h2>

        <form action="controller" method="post">
            <input type="text" class="fadeIn second" placeholder="Login" name="login">
            <input type="text" class="fadeIn third" placeholder="Email" name="email">
            <input type="hidden" name="command" value="forgotPassword">
            <input type="submit" class="fadeIn fourth" value="Send password">
        </form>
        <div>
            <c:if test="${validInput == true}">
                <p id="validInput">The password was sent to the specified email</p>
                <c:remove var="validInput" scope="session"/>
            </c:if>
            <c:if test="${wrongInput == true}">
                <p id="invalidInput">Incorrect login or email</p>
                <c:remove var="wrongInput" scope="session"/>
            </c:if>
            <c:if test="${InvalidInput == true}">
                <p id="invalidInput">User with such a login and email does not exist</p>
                <c:remove var="InvalidInput" scope="session"/>
            </c:if>

        </div>

    </div>
</div>
<jsp:include page="header.jsp"/>

</body>
</html>
