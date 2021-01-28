<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/parkingCheckboxes4.css">
<body>
<div id="conteiner">
    <form action="controller" method="post">
        <input type="text" id="inputLat" placeholder="Latitude" name="latitude">
        <input type="text" id="inputLong" placeholder="Longitude" name ="longitude">
        <input type="text" id="totalSpots" placeholder="Spots" name ="spots">
        <input type="text" id="parkingName" placeholder="Parking Name" name ="name">
        <label><input id="electricity" type="checkbox" value="electricity" name="electricity"><span></span></label>
        <label><input id="fence" type="checkbox" value="fence" name="fence"><span></span></label>
        <label><input id="food" type="checkbox" value="food" name="food"><span></span></label>
        <label><input id="gasStation" type="checkbox" value="gasStation" name="gasStation"><span></span></label>
        <label><input id="guard" type="checkbox" value="guard" name="guard"><span></span></label>
        <label><input id="light" type="checkbox" value="light" name="light"><span></span></label>
        <label><input id="lodging" type="checkbox" value="lodging" name="lodging"><span></span></label>
        <label><input id="securityCameras" type="checkbox" value="securityCameras" name="securityCameras"><span></span></label>
        <label><input id="service" type="checkbox" value="service" name="service"><span></span></label>
        <label><input id="shower" type="checkbox" value="shower" name="shower"><span></span></label>
        <label><input id="store" type="checkbox" value="store" name="store"><span></span></label>
        <label><input id="truckWash" type="checkbox" value="truckWash" name="truckWash"><span></span></label>
        <label><input id="water" type="checkbox" value="water" name="water"><span></span></label>
        <label><input id="wc" type="checkbox" value="wc" name="wc"><span></span></label>
        <label><input id="wifi" type="checkbox" value="wifi" name="wifi"><span></span></label>
        <input type="hidden" name="command" value="goToCheckBoxes">
        <p><input id="createButton" type="submit" value="Create parking"></p>
    </form>

    <c:if test="${wrongInput == true}">
        <p id="invalidInput">Сoordinates, number of places and name fields should not be empty</p>
        <c:remove var="wrongInput" scope="session"/>
    </c:if>
    <c:if test="${wrongCoordinate == true}">
        <p id="invalidInput">Coordinates must be a number and be of the form 33.3333333</p>
        <c:remove var="wrongCoordinate" scope="session"/>
    </c:if>
    <c:if test="${wrongSpots == true}">
        <p id="invalidInput">The number of spots must be a number from 1 to 999</p>
        <c:remove var="wrongSpots" scope="session"/>
    </c:if>
    <c:if test="${wrongName == true}">
        <p id="invalidInput">The number of spots must be a number from 1 to 999</p>
        <c:remove var="wrongName" scope="session"/>
    </c:if>

</div>
</body>
</html>
