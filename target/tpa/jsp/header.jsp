<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>header</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <div id="logo">
        <a href="<c:url value="/controller"/>">Truck Parking Assistant</a>
    </div>

    <ul id="navbar">
        <li><a href="<c:url value="/controller"/>">HOME</a></li>
        <li><a href="<c:url value="/controller?command=ourService"/>">OUR SEVICE</a></li>
        <li><a href="<c:url value="/controller?command=connectWithUs"/>">CONTACT US</a></li>
        <li><a id="lastOne" href="<c:url value="/controller?command=goSignInPage"/>">SIGN IN</a></li>
    </ul>

</head>
<body>
</body>
</html>
