<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ourServicePage.css">
<body>
<jsp:include page="header.jsp">
    <jsp:param name="list" value="true"/>
</jsp:include>

<div id="container">
    <div id="topHeader">
        <h1>We provide</h1>
        <h2>Truck Parking Assistant offers you an easy to use platform to find</h2>
        <h2>and book secure truck parking locations.</h2>
        <h2>Helping you limit cargo-theft, minimise search traffic and contribute to a</h2>
        <h2>safer working environment for drivers.</h2>
        <h2>Find a secure parking spot in 155 countries</h2>
    </div>
    <div class="advantages">
        <h1>Safety.</h1>
        <h3>Providing your driver with secure parking areas along their route will not only provide them with comfort,
            but will also give you the peace of mind knowing your cargo and driver are safe. A win-win for the both of
            you.</h3>
    </div>
    <div class="advantages">
        <h1>Simplicity.</h1>
        <h3>Less time searching, more time driving. No longer waste fuel or precious driving time looking for a secure
            parking spot.
            You can send them directly to an en-route location that has all facilities that you and your driver
            require.</h3>
    </div>
    <div class="advantagesLastElement">
        <h1>Reliability.</h1>
        <h3>Not only streamline your administration but offer your drivers a safe and comfortable work environment.
            Be the top-notch employer you need to be to attract talented drivers.</h3>
    </div>

</div>
</body>
</html>
