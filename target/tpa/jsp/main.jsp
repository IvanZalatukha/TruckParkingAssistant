<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mainPage.css">
<jsp:include page="header.jsp">
    <jsp:param name="list" value="true"/>
</jsp:include>

<div id="container">

    <div id="aboutUs">
        <h1>Safety.</h1>
        <h1>Simplicity.</h1>
        <h1>Reliability.</h1>
        <h2>Find out what you and your company get from using the WEB Truck Parking Assistant tool.
            Stay in touch with your drivers and inform them about available parking spaces.
            Check out our solutions and use all functionalities.</h2>
    </div>
    <a class="b-ghost" href="<c:url value="/controller?command=connectWithUs"/>">CONNECT WITH US</a>
    <a class="b-ghost" href="<c:url value="/controller?command=goToMapPage"/>">GO TO MAP</a>
</div>

</body>
</html>
