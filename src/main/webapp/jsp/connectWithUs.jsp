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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/connectWithUsPage2.css">

<body>

    <div id="wrapper">
        <div id="formContent">
            <h2 class="active"><fmt:message key="title.canWrite"/></h2>

            <form action="<c:url value="/jsp/controller"/>" method="post">
                <input type="text" placeholder="<fmt:message key="title.name"/>" name="name">
                <input type="text" placeholder="<fmt:message key="title.email"/>" name="email">
                <input type="text" placeholder="<fmt:message key="title.topic"/>" name="topic">
                <textarea maxlength="650" placeholder="<fmt:message key="title.text"/>" name="text"></textarea>
                <input type="hidden" name="command" value="messageFromUser">
                <input type="submit" value="<fmt:message key="button.sendMessage"/>">
            </form>
            <div>
                <c:if test="${validInput == true}">
                    <p id="validInput"><fmt:message key="text.thanks"/></p>
                    <c:remove var="validInput" scope="session"/>
                </c:if>
                <c:if test="${wrongInput == true}">
                    <p id="invalidInput"><fmt:message key="text.wrong"/></p>
                    <c:remove var="wrongInput" scope="session"/>
                </c:if>
                <c:if test="${wrongEmailInput == true}">
                    <p id="invalidInput"><fmt:message key="text.invalid"/></p>
                    <c:remove var="wrongEmailInput" scope="session"/>
                </c:if>

            </div>
        </div>
        <div id="wrapperForSocialMedia">
            <h2><fmt:message key="text.ourAddress"/></h2>
            <img src="../PicturesOfParkingServices/socialNetworks.png">
        </div>
    </div>




<div id="formMap"></div>
<script>
    function initMap() {
        map = new google.maps.Map(document.getElementById("formMap"), {
            center: {lat: 53.89955, lng: 27.54606},
            zoom: 10,
        });
        const marker = new google.maps.Marker({
            position: {lat: 53.90755891444916, lng: 27.54958891873584},
            map: map,
            animation: google.maps.Animation.DROP
        });
        const contentString = '<fmt:message key="text.marker"/>'

        const infowindow = new google.maps.InfoWindow({
            content: contentString,
        });
        infowindow.open(map, marker)

    }
</script>
<script defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAkjeJ2RVdg225f2paPwjcgVOusnmH2-TQ&callback=initMap">
</script>
</body>
</html>
