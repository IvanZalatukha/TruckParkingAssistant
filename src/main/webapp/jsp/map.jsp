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
    const parkingNames = []
    const spotsTotal = []
</script>

<c:forEach var="parking" items="${allParkings}">

<script>
        locations.push({lat: ${parking.getCoordinateLatitude()}, lng: ${parking.getCoordinateLongitude()}})
        parkingNames.push(${parking.getName()})
        spotsTotal.push(${parking.getSpotsTotal()})
</script>

</c:forEach>

    <script>

        function initMap() {
            map = new google.maps.Map(document.getElementById("map"), {
                center: {lat: 53.89955, lng: 27.54606},
                zoom: 10,
                mapTypeId: "roadmap",
            });


                // Create the search box and link it to the UI element.
                const input = document.getElementById("pac-input");
                const searchBox = new google.maps.places.SearchBox(input);
                map.controls[google.maps.ControlPosition.TOP_CENTER].push(input);
                // Bias the SearchBox results towards current map's viewport.
                map.addListener("bounds_changed", () => {
                    searchBox.setBounds(map.getBounds());
                });
                let markers = [];
                // Listen for the event fired when the user selects a prediction and retrieve
                // more details for that place.
                searchBox.addListener("places_changed", () => {
                    const places = searchBox.getPlaces();

                    if (places.length == 0) {
                        return;
                    }
                    // Clear out the old markers.
                    markers.forEach((marker) => {
                        marker.setMap(null);
                    });
                    markers = [];
                    // For each place, get the icon, name and location.
                    const bounds = new google.maps.LatLngBounds();
                    places.forEach((place) => {
                        if (!place.geometry) {
                            console.log("Returned place contains no geometry");
                            return;
                        }
                        const icon = {
                            url: place.icon,
                            size: new google.maps.Size(71, 71),
                            origin: new google.maps.Point(0, 0),
                            anchor: new google.maps.Point(17, 34),
                            scaledSize: new google.maps.Size(25, 25),
                        };
                        // Create a marker for each place.
                        markers.push(
                            new google.maps.Marker({
                                map,
                                icon,
                                title: place.name,
                                position: place.geometry.location,
                            })
                        );

                        if (place.geometry.viewport) {
                            // Only geocodes have viewport.
                            bounds.union(place.geometry.viewport);
                        } else {
                            bounds.extend(place.geometry.location);
                        }
                    });
                    map.fitBounds(bounds);
                });





            infoWindow = new google.maps.InfoWindow();
            const locationButton = document.createElement("button");
            locationButton.textContent = "Show my location";
            locationButton.classList.add("custom-map-control-button");
            map.controls[google.maps.ControlPosition.TOP_RIGHT].push(locationButton);

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
                    title: parkingNames.shift(i),
                    icon: {
                        url: "http://maps.google.com/mapfiles/ms/icons/blue-dot.png"
                    }
                });
                const parkingName = parkingNames.shift(i);
                const totalSpot = spotsTotal.shift(i);
                const contentString =
                    parkingName +
                '<div id="content">' +
                    '<div id="siteNotice">' +

                    "</div>" +  + totalSpot +
                    '<h1 id="firstHeading" class="firstHeading"> </h1>' +
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
<input
        id="pac-input"
        class="controls"
        type="text"
        placeholder="Search Box"
/>
<style>
    #map {
        height: 800px;
        width: 70%;
    }
</style>

<div id="map">
</div>

<script defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAkjeJ2RVdg225f2paPwjcgVOusnmH2-TQ&callback=initMap&libraries=places&v=weekly">
</script>

</body>
</html>