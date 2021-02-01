<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="locale"
       value="${not empty sessionScope.locale ? sessionScope.locale : 'en_US'}"/>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="content"/>

<html>
<head>
    <title>header</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/locale.css">
    <div id="logo">
        <a href="<c:url value="/controller"/>">Truck Parking Assistant</a>
    </div>

    <ul id="navbar">
        <li><a href="<c:url value="/controller"/>"><fmt:message key="link.home"/></a></li>
        <li><a href="<c:url value="/controller?command=ourService"/>"><fmt:message key="link.ourService"/></a></li>
        <li><a href="<c:url value="/controller?command=connectWithUs"/>"><fmt:message key="link.contact"/></a></li>
        <c:if test="${isUser}">
            <c:remove var="isUserWithoutLogin" scope="application"/>
            <li><a href="<c:url value="/controller?command=goSignInPage"/>"><fmt:message key="link.signOut"/></a></li>
        </c:if>
        <c:if test="${isUserWithoutLogin}">
            <li><a href="<c:url value="/controller?command=goSignInPage"/>"><fmt:message key="link.signIn"/></a></li>
        </c:if>


        <li><a>
            <div class="containerForLocal">
                <div class="switch">
                    <form action="controller" method="post" id="forLanguageForm">
                        <input type="hidden" name="flag" value="ready">
                        <input type="radio" class="switch-input" onclick="this.form.submit();" name="command"
                               value="switchLocale" id="en" checked>

                        <label for="en" class="switch-label switch-label-off">EN</label>
                        <input type="radio" class="switch-input" onclick="this.form.submit();" name="command"
                               value="switchLocale" id="ru"
                        <c:if test="${locale eq 'ru_RU'}">
                               checked
                        </c:if>>
                        <label for="ru" class="switch-label switch-label-on">RU</label>
                        <span class="switch-selection"></span>
                    </form>
                </div>
            </div>
        </a>
        </li>
    </ul>


</head>
<body>
</body>
</html>
