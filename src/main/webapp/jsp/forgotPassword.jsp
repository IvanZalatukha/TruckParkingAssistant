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
        <h2 class="active"><fmt:message key="text.please"/></h2>

        <form action="controller" method="post">
            <input type="text" class="fadeIn second" placeholder="<fmt:message key="input.login"/>" name="login">
            <input type="text" class="fadeIn third" placeholder="<fmt:message key="input.email"/>" name="email">
            <input type="hidden" name="command" value="forgotPassword">
            <input type="submit" class="fadeIn fourth" value="<fmt:message key="button.serdPassword"/>">
        </form>
        <div>
            <c:if test="${validInput == true}">
                <p id="validInput"><fmt:message key="text.passwordWasSend"/></p>
                <c:remove var="validInput" scope="session"/>
            </c:if>
            <c:if test="${wrongInput == true}">
                <p id="invalidInput"><fmt:message key="error.loginOremail"/></p>
                <c:remove var="wrongInput" scope="session"/>
            </c:if>
            <c:if test="${InvalidInput == true}">
                <p id="invalidInput"><fmt:message key="error.doesntExist"/></p>
                <c:remove var="InvalidInput" scope="session"/>
            </c:if>

        </div>

    </div>
</div>


</body>
</html>
