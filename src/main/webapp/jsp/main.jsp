<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main2.css">
<jsp:include page="header.jsp">
    <jsp:param name="list" value="true" />
</jsp:include>

<div id="container">
    <h1>Safety.</h1>
    <h1>Simplicity.</h1>
    <h1>Reliability.</h1>
</div>

                <form action="controller" method="post">
                    <input type="hidden" name="command" value="goToMapPage">
                    <input type="submit"  name="submit" value="map" >
                </form>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="goSignInPage">
                    <input type="submit"  name="submit" value="Sign in" >
                </form>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="goToRegistrationPage">
                    <input type="submit"  name="submit" value="Registration" >
                </form>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="error">
                    <input type="submit"  name="submit" value="error" >
                </form>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="goToAdiminPage">
                    <input type="hidden" name="currentPage" value="1">
                    <input type="submit"  name="submit" value="AdiminPage" >
                </form>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="adminMap">
                    <input type="submit"  name="submit" value="AdiminMap" >
                </form>




</body>
</html>
