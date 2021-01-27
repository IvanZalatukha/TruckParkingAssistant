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
        <h2 class="inactive underlineHover"><a class="underlineHover" href="<c:url value="/controller?command=goSignInPage"/>">SIGN IN</a></h2>
        <h2 class="active">REGISTARION</h2>

        <!-- Login Form -->
        <form action="controller" method="post">

            <input type="text" class="fadeIn third" placeholder="First Name" name="firstName">
            <input type="text" class="fadeIn third" placeholder="Last Name" name="lastName">
            <input type="text" class="fadeIn third" placeholder="Email" name="email">
            <input type="text" class="fadeIn third" placeholder="Phone number" name="phoneNumber">
            <input type="text" class="fadeIn second" placeholder="Login" name="login">
            <input type="password" class="fadeIn third" placeholder="Password" name="password">
            <input type="password" class="fadeIn third" placeholder="Confirm password" name="confirmPassword">
            <input type="hidden" name="command" value="registrationUser">
            <input type="submit" class="fadeIn fourth" value="Register">
        </form>
        <div>
            <c:if test="${wrongInput == true}">
                <p id="invalidInput">All fields except the phone number are required. Please enter your data.</p>
                <c:remove var="wrongInput" scope="session" />
            </c:if>
            <c:if test="${wrongFirstNameInput == true}">
                <p id="invalidInput">The first name must be alphanumeric and at least 4 characters long</p>
                <c:remove var="wrongFirstNameInput" scope="session" />
            </c:if>
            <c:if test="${wrongLastNameInput == true}">
                <p id="invalidInput">The last name must be alphanumeric and at least 4 characters long</p>
                <c:remove var="wrongLastNameInput" scope="session" />
            </c:if>
            <c:if test="${wrongEmailInput == true}">
                <p id="invalidInput">Incorrect email adress</p>
                <c:remove var="wrongEmailInput" scope="session" />
            </c:if>
            <c:if test="${wrongPhoneNumberInput == true}">
                <p id="invalidInput">Phone number must be 11 digits long</p>
                <c:remove var="wrongPhoneNumberInput" scope="session" />
            </c:if>
            <c:if test="${wrongLoginInput == true}">
                <p id="invalidInput">The login must be alphanumeric and at least 4 characters long</p>
                <c:remove var="wrongLoginInput" scope="session" />
            </c:if>
            <c:if test="${loginAlredyHas == true}">
                <p id="invalidInput">Such a login already exists, please create another one</p>
                <c:remove var="loginAlredyHas" scope="session" />
            </c:if>
            <c:if test="${passwordAndConfirmNotEqualsInput == true}">
                <p id="invalidInput">Password and password confirmation must be equal</p>
                <c:remove var="passwordAndConfirmNotEqualsInput" scope="session" />
            </c:if>
            <c:if test="${wrongPasswordInput == true}">
                <p id="invalidInput">password must be at least 5 characters long and no special characters</p>
                <c:remove var="wrongPasswordInput" scope="session" />
            </c:if>

        </div>


    </div>
</div>
<jsp:include page="header.jsp">
    <jsp:param name="list" value="true" />
</jsp:include>
</body>
</html>
