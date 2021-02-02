<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="locale"
       value="${not empty sessionScope.locale ? sessionScope.locale : 'en_US'}"/>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="content"/>
<html>
<head>
    <title>TPA</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mainPage.css">
<jsp:include page="header.jsp" />

<div id="container">

    <div id="aboutUs">
        <h1><fmt:message key="h1.safety"/></h1>
        <h1><fmt:message key="h1.simplicity"/></h1>
        <h1><fmt:message key="h1.reliability"/></h1>
        <h2><fmt:message key="h2.findOut"/></h2>
    </div>
    <a class="b-ghost" href="<c:url value="/jsp/controller?command=goToAdminPage"/>"><fmt:message key="button.connect"/></a>
    <a class="b-ghost" href="<c:url value="/jsp/controller?command=goToMapPage"/>"><fmt:message key="button.goToMap"/></a>
    <c:if test="${isAdmin}">
        <a class="b-ghost" href="<c:url value="/jsp/controller?command=goToAdminPage"/>">Admin</a>
    </c:if>
</div>

</body>
</html>
