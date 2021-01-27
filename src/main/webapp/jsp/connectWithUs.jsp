<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/connectWithUsPage.css">

<body>

<div class="wrapper">
    <div id="formContent">
        <h2 class="active">You can write to us</h2>

        <form action="controller" method="post">
            <input type="text" placeholder="Name" name="name">
            <input type="text" placeholder="Email" name="email">
            <input type="text" placeholder="Topic" name="topic">
            <textarea maxlength="650" placeholder="Text" name="text"></textarea>
            <input type="hidden" name="command" value="messageFromUser">
            <input type="submit" value="Send a message">
        </form>
        <div>
            <c:if test="${validInput == true}">
                <p id="validInput">Thank you for your message</p>
                <c:remove var="validInput" scope="session"/>
            </c:if>
            <c:if test="${wrongInput == true}">
                <p id="invalidInput">Please fill in the fields name and email to send a message</p>
                <c:remove var="wrongInput" scope="session"/>
            </c:if>
            <c:if test="${wrongEmailInput == true}">
                <p id="invalidInput">Incorrect email adress</p>
                <c:remove var="wrongEmailInput" scope="session"/>
            </c:if>

        </div>
    </div>
</div>


<jsp:include page="header.jsp"/>

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
        const contentString = 'We are here'

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
