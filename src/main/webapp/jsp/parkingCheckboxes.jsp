<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/parkingCheckboxes.css">
<body>
<div id="conteiner">
    <form action="<c:url value="/jsp/controller"/>" method="post">
        <input type="text" id="inputLat" placeholder="Latitude" name="latitude">
        <input type="text" id="inputLong" placeholder="Longitude" name ="longitude">
        <input type="text" id="totalSpots" placeholder="Spots" name ="spots">
        <input type="text" id="parkingName" placeholder="Parking Name" name ="name">

        <%--@elvariable id="electricity" type="boolean"--%>
        <c:if test="${electricity == true}">
            <label><input id="electricity" type="checkbox" value="electricity" name="electricity" checked><span></span></label>

        </c:if>
        <c:if test="${electricity == null}">
            <label><input id="electricity2" type="checkbox" value="electricity" name="electricity" ><span></span></label>
        </c:if>
        <c:if test="${fence == true}">
            <label><input id="fence" type="checkbox" value="fence" name="fence" checked><span></span></label>

        </c:if>
        <c:if test="${fence == null}">
            <label><input id="fence2" type="checkbox" value="fence" name="fence"><span></span></label>
        </c:if>
        <c:if test="${food == true}">
            <label><input id="food" type="checkbox" value="food" name="food" checked><span></span></label>
        </c:if>
        <c:if test="${food == null}">
            <label><input id="food2" type="checkbox" value="food" name="food" ><span></span></label>
        </c:if>
        <c:if test="${gasStation == true}">
            <label><input id="gasStation" type="checkbox" value="gasStation" name="gasStation" checked><span></span></label>
        </c:if>
        <c:if test="${gasStation == null}">
            <label><input id="gasStation2" type="checkbox" value="gasStation" name="gasStation" ><span></span></label>
        </c:if>
        <c:if test="${guard == true}">
            <label><input id="guard" type="checkbox" value="guard" name="guard" checked><span></span></label>
        </c:if>
        <c:if test="${guard eq null}">
            <label><input id="guard2" type="checkbox" value="guard" name="guard" ><span></span></label>
        </c:if>
        <c:if test="${light == true}">
            <label><input id="light" type="checkbox" value="light" name="light" checked><span></span></label>
        </c:if>
        <c:if test="${light eq null}">
            <label><input id="light2" type="checkbox" value="light" name="light" ><span></span></label>
        </c:if>
        <c:if test="${lodging == true}">
            <label><input id="lodging" type="checkbox" value="lodging" name="lodging" checked><span></span></label>
        </c:if>
        <c:if test="${lodging eq null}">
            <label><input id="lodging2" type="checkbox" value="lodging" name="lodging" ><span></span></label>
        </c:if>
        <c:if test="${securityCameras == true}">
            <label><input id="securityCameras" type="checkbox" value="securityCameras" name="securityCameras" checked><span></span></label>
        </c:if>
        <c:if test="${securityCameras eq null}">
            <label><input id="securityCameras2" type="checkbox" value="securityCameras" name="securityCameras" ><span></span></label>
        </c:if>
        <c:if test="${truckService == true}">
            <label><input id="service" type="checkbox" value="service" name="service" checked><span></span></label>
        </c:if>
        <c:if test="${truckService eq null}">
            <label><input id="service2" type="checkbox" value="service" name="service" ><span></span></label>
        </c:if>
        <c:if test="${shower == true}">
            <label><input id="shower" type="checkbox" value="shower" name="shower" checked><span></span></label>
        </c:if>
        <c:if test="${shower eq null}">
            <label><input id="shower2" type="checkbox" value="shower" name="shower"><span></span></label>
        </c:if>
        <c:if test="${store == true}">
            <label><input id="store" type="checkbox" value="store" name="store" checked><span></span></label>
        </c:if>
        <c:if test="${store eq null}">
            <label><input id="store2" type="checkbox" value="store" name="store" ><span></span></label>
        </c:if>
        <c:if test="${truckWash == true}">
            <label><input id="truckWash" type="checkbox" value="truckWash" name="truckWash" checked><span></span></label>
        </c:if>
        <c:if test="${truckWash eq null}">
            <label><input id="truckWash2" type="checkbox" value="truckWash" name="truckWash"><span></span></label>
        </c:if>
        <c:if test="${water == true}">
            <label><input id="water" type="checkbox" value="water" name="water" checked><span></span></label>
        </c:if>
        <c:if test="${water eq null}">
            <label><input id="water2" type="checkbox" value="water" name="water" ><span></span></label>
        </c:if>
        <c:if test="${wc == true}">
            <label><input id="wc" type="checkbox" value="wc" name="wc" checked><span></span></label>
        </c:if>
        <c:if test="${wc eq null}">
            <label><input id="wc2" type="checkbox" value="wc" name="wc" ><span></span></label>
        </c:if>
        <c:if test="${wifi == true}">
            <label><input id="wifi" type="checkbox" value="wifi" name="wifi" checked><span></span></label>
        </c:if>
        <c:if test="${wifi eq null}">
            <label><input id="wifi2" type="checkbox" value="wifi" name="wifi" ><span></span></label>
        </c:if>
        <c:remove var="electricity" scope="session"/>
        <c:remove var="fence" scope="session"/>
        <c:remove var="food" scope="session"/>
        <c:remove var="gasStation" scope="session"/>
        <c:remove var="guard" scope="session"/>
        <c:remove var="light" scope="session"/>
        <c:remove var="lodging" scope="session"/>
        <c:remove var="securityCameras" scope="session"/>
        <c:remove var="truckService" scope="session"/>
        <c:remove var="shower" scope="session"/>
        <c:remove var="store" scope="session"/>
        <c:remove var="truckWash" scope="session"/>
        <c:remove var="water" scope="session"/>
        <c:remove var="wifi" scope="session"/>
        <c:remove var="wc" scope="session"/>

        <input type="hidden" name="command" value="goToCheckBoxes">

        <c:if test="${updateButton == true}">
            <p><input id="createButton2" type="submit" value="UPDATE"></p>
            <c:set var="updateService" value="updateService" scope="session"/>
        </c:if>
        <c:if test="${updateButton == null}">
            <p><input id="createButton" type="submit" value="Create parking"></p>
        </c:if>
    </form>
    <c:remove var="updateButton" scope="session"/>

    <c:if test="${wrongInput == true}">
        <p id="invalidInput">Coordinates, number of places and name fields should not be empty</p>
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
