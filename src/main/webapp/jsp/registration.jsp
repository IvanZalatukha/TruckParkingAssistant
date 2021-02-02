<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="locale"
       value="${not empty sessionScope.locale ? sessionScope.locale : 'en_US'}"/>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="content"/>
<html>
<head>
    <title>Title</title>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/signInPage.css">
<jsp:include page="header.jsp"/>
<body>

<div class="fadeInDown">
    <div id="formContent">
        <h2 class="inactive underlineHover"><a class="underlineHover"
                                               href="<c:url value="/jsp/controller?command=goSignInPage"/>"><fmt:message key="link.signIn"/></a></h2>
        <h2 class="active"><fmt:message key="link.registration"/></h2>

        <form action="<c:url value="/jsp/controller"/>" method="post">

            <input type="text" class="fadeIn third" placeholder="<fmt:message key="input.firstName"/>" name="firstName">
            <input type="text" class="fadeIn third" placeholder="<fmt:message key="input.lastName"/>" name="lastName">
            <input type="text" class="fadeIn third" placeholder="<fmt:message key="input.email"/>" name="email">
            <input type="text" class="fadeIn third" placeholder="<fmt:message key="input.phone"/>" name="phoneNumber">
            <input type="text" class="fadeIn second" placeholder="<fmt:message key="input.login"/>" name="login">
            <input type="password" class="fadeIn third" placeholder="<fmt:message key="input.password"/>" name="password">
            <input type="password" class="fadeIn third" placeholder="<fmt:message key="input.confPassword"/>" name="confirmPassword">
            <input type="hidden" name="command" value="registrationUser">
            <input type="submit" class="fadeIn fourth" value="<fmt:message key="button.register"/>">
        </form>
        <div>
            <c:if test="${wrongInput == true}">
                <p id="invalidInput"><fmt:message key="error.allFields"/></p>
                <c:remove var="wrongInput" scope="session"/>
            </c:if>
            <c:if test="${wrongFirstNameInput == true}">
                <p id="invalidInput"><fmt:message key="error.firstName"/></p>
                <c:remove var="wrongFirstNameInput" scope="session"/>
            </c:if>
            <c:if test="${wrongLastNameInput == true}">
                <p id="invalidInput"><fmt:message key="error.lastName"/></p>
                <c:remove var="wrongLastNameInput" scope="session"/>
            </c:if>
            <c:if test="${wrongEmailInput == true}">
                <p id="invalidInput"><fmt:message key="error.enail"/></p>
                <c:remove var="wrongEmailInput" scope="session"/>
            </c:if>
            <c:if test="${wrongPhoneNumberInput == true}">
                <p id="invalidInput"><fmt:message key="error.phone"/></p>
                <c:remove var="wrongPhoneNumberInput" scope="session"/>
            </c:if>
            <c:if test="${wrongLoginInput == true}">
                <p id="invalidInput"><fmt:message key="error.login"/></p>
                <c:remove var="wrongLoginInput" scope="session"/>
            </c:if>
            <c:if test="${loginAlredyHas == true}">
                <p id="invalidInput"><fmt:message key="error.loginExi"/></p>
                <c:remove var="loginAlredyHas" scope="session"/>
            </c:if>
            <c:if test="${passwordAndConfirmNotEqualsInput == true}">
                <p id="invalidInput"><fmt:message key="error.passs"/></p>
                <c:remove var="passwordAndConfirmNotEqualsInput" scope="session"/>
            </c:if>
            <c:if test="${wrongPasswordInput == true}">
                <p id="invalidInput"><fmt:message key="error.password"/></p>
                <c:remove var="wrongPasswordInput" scope="session"/>
            </c:if>

        </div>


    </div>
</div>

</body>
</html>
