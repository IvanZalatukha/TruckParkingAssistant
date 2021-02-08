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
<jsp:include page="header.jsp"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/signInPage.css">

<body>

<div class="fadeInDown">
    <div id="formContent">
        <h2 class="active"><fmt:message key="link.signIn"/></h2>
        <h2 class="inactive underlineHover">
            <a class="underlineHover" href="<c:url value="/jsp/controller?command=goToRegistrationPage"/>"><fmt:message
                    key="link.registration"/> </a>
        </h2>

        <form action="<c:url value="/jsp/controller"/>" method="post">
            <input type="text" class="fadeIn second" placeholder="<fmt:message key="input.login"/>" name="login">
            <input type="password" class="fadeIn third" placeholder="<fmt:message key="input.password"/>"
                   name="password">
            <input type="hidden" name="command" value="login">
            <input type="submit" class="fadeIn fourth" value="<fmt:message key="button.logIn"/>">
        </form>
        <div>
            <c:if test="${wrongInput == true}">
                <p id="invalidInput"><fmt:message key="text.incorrect"/></p>
                <c:remove var="wrongInput" scope="session"/>
            </c:if>
        </div>
        <div id="formFooter">
            <a class="underlineHover" href="<c:url value="/jsp/controller?command=forgotPasswordPage"/>"><fmt:message
                    key="link.forgot"/></a>
        </div>
    </div>
</div>

</body>
</html>
