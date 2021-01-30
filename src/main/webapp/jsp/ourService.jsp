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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ourServicePage.css">
<jsp:include page="header.jsp" />
<body>


<div id="container">
    <div id="topHeader">
        <h1><fmt:message key="h1.provide"/></h1>
        <h2><fmt:message key="h2.tpa"/></h2>
        <h2><fmt:message key="h2.tpa2"/></h2>
        <h2><fmt:message key="h2.tpa3"/></h2>
        <h2><fmt:message key="h2.tpa4"/></h2>
        <h2><fmt:message key="h2.tpa5"/></h2>
    </div>
    <div class="advantages">
        <h1><fmt:message key="h1.safety"/></h1>
        <h3><fmt:message key="h3.providing"/>.</h3>
    </div>
    <div class="advantages">
        <h1><fmt:message key="h1.simplicity"/></h1>
        <h3><fmt:message key="h3.less"/></h3>
    </div>
    <div class="advantagesLastElement">
        <h1><fmt:message key="h1.reliability"/></h1>
        <h3><fmt:message key="h3.notOnle"/></h3>
    </div>

</div>
</body>
</html>
