<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TPA</title>
</head>
<body>

<form action="controller" method="post">
    <input type="hidden" name="command" value="goToMainPage">
    <input type="submit"  name="submit" value="main" >
</form>

<script>
    const locations = []

</script>

<c:forEach var="park" items="${parkings}">

<script>

        locations.push({lat: ${park.getCoordinateLatitude()}, lng: ${park.getCoordinateLongitude()}},)

</script>
</c:forEach>

    <script>

        function initMap() {
            map = new google.maps.Map(document.getElementById("map"), {
                center: {lat: 53.89955, lng: 27.54606},
                zoom: 10,
            });
            infoWindow = new google.maps.InfoWindow();
            const locationButton = document.createElement("button");
            locationButton.textContent = "Show my location";
            locationButton.classList.add("custom-map-control-button");
            map.controls[google.maps.ControlPosition.TOP_CENTER].push(locationButton);

            locationButton.addEventListener("click", () => {
                // Try HTML5 geolocation.
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(
                        (position) => {
                            const pos = {
                                lat: position.coords.latitude,
                                lng: position.coords.longitude,
                            };
                            infoWindow.setPosition(pos);
                            infoWindow.setContent("You are here.");
                            infoWindow.open(map);
                            map.setCenter(pos);
                        },
                        () => {
                            handleLocationError(true, infoWindow, map.getCenter());
                        }
                    );
                } else {
                    // Browser doesn't support Geolocation
                    handleLocationError(false, infoWindow, map.getCenter());
                }
            });

            function handleLocationError(browserHasGeolocation, infoWindow, pos) {
                infoWindow.setPosition(pos);
                infoWindow.setContent(
                    browserHasGeolocation
                        ? "Error: The Geolocation service failed."
                        : "Error: Your browser doesn't support geolocation."
                );
                infoWindow.open(map);
            }


            for (let i = 0; i < locations.length; i++) {
                const marker = new google.maps.Marker({
                    position: locations[i],
                    map: map,
                    title: "Hello World!",
                });
                const contentString =
                    '<div id="content">' +
                    '<div id="siteNotice">' +
                    "</div>" +
                    '<h1 id="firstHeading" class="firstHeading">spasibo Anite za eto!</h1>' +
                    '<div id="bodyContent">' +
                    "<p>Here will be information about my parking and </p> <p>the services that the driver can get here</p>" +
                    '<p><a href=https://ru.wikipedia.org/wiki/%D0%A1%D1%82%D0%BE%D1%8F%D0%BD%D0%BA%D0%B0_' +
                    '(%D1%81%D0%BE%D0%BE%D1%80%D1%83%D0%B6%D0%B5%D0%BD%D0%B8%D0%B5)>' +
                    "what is parking?</a> " +
                    "(last visited June 22, 2009).</p>" +
                    "</div>" +
                    "</div>";
                const infowindow = new google.maps.InfoWindow({
                    content: contentString
                });
                marker.addListener("click", () => {
                    infowindow.open(map, marker);
                });
            }

        }
    </script>




</body>
<style>
    #map {
        height: 800px;
        width: 70%;
    }
</style>

<div id="map">
</div>

<script defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCzkCePvlWi-7nmwwcT-D3UzfwX5gWnyAY&callback=initMap">
</script>

</body>
</html>