<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TPA</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/infoWindowOnTheMap.css">

<script>
    var parkings = []
</script>

<c:forEach var="parking" items="${allParkings}">
    <script>
            parkings.push(
                {
                    "coordinates":{lat: ${parking.getCoordinateLatitude()}, lng: ${parking.getCoordinateLongitude()}},
                    "parkingName": "${parking.getName()}",
                    "spotsTotal":"${parking.getSpotsTotal()}",
                    "services": {fence: ${parking.getParkingServices().getFence()},
                        securityCameras: ${parking.getParkingServices().getSecurityCameras()},
                        wc: ${parking.getParkingServices().getWc()}, shower: ${parking.getParkingServices().getShower()},
                        guard: ${parking.getParkingServices().getGuardedParking()}, light: ${parking.getParkingServices().getLighting()},
                        electricity: ${parking.getParkingServices().getElectricity()}, water: ${parking.getParkingServices().getWater()},
                        gasStation: ${parking.getParkingServices().getGasStation()}, wifi: ${parking.getParkingServices().getWifi()},
                        lodging: ${parking.getParkingServices().getLodging()}, truckService: ${parking.getParkingServices().getTruckService()},
                        truckWash: ${parking.getParkingServices().getTruckWash()}, store: ${parking.getParkingServices().getStore()},
                        food: ${parking.getParkingServices().getFood()}},
                    "spotsCurrently": "${parking.getSpotsCurrently()}"
                }

            )
    </script>
</c:forEach>

<c:if test="${showParking != null}">
    <script>
        var showPark = []
        showPark.push(
            {
                "coordinates": {lat: ${showParking.getCoordinateLatitude()}, lng: ${showParking.getCoordinateLongitude()}},
                "parkingName": "${showParking.getName()}",
                "spotsTotal":"${showParking.getSpotsTotal()}",
                "spotsCurrently": "${showParking.getSpotsCurrently()}"
            }
        )
    </script>
</c:if>

<script>

    function initMap() {
        map = new google.maps.Map(document.getElementById("map"), {
            center: {lat: 53.89955, lng: 27.54606},
            zoom: 10,
            mapTypeId: "roadmap",
        });
        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        let infoWindow = new google.maps.InfoWindow({});
        infoWindow.open(map);
        // Configure the click listener.
        map.addListener("dblclick", (mapsMouseEvent) => {
            // Close the current InfoWindow.
            infoWindow.close();
            // Create a new InfoWindow.
            infoWindow = new google.maps.InfoWindow({
                position: mapsMouseEvent.latLng,
            });
            infoWindow.setContent(
                JSON.stringify(mapsMouseEvent.latLng.toJSON(), null, 2)
            );
            infoWindow.open(map);
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


        const homeButton = document.createElement("button");
        homeButton.textContent = "HOME";
        homeButton.classList.add("custom-map-control-button");
        map.controls[google.maps.ControlPosition.TOP_RIGHT].push(homeButton);


        const signInButton = document.createElement("button");
        signInButton.textContent = "SIGN IN";
        signInButton.classList.add("custom-map-control-button");
        map.controls[google.maps.ControlPosition.TOP_RIGHT].push(signInButton);

        infoWindow = new google.maps.InfoWindow();
        const locationButton = document.createElement("button");
        locationButton.textContent = "Show my location";
        locationButton.classList.add("custom-map-control-button");
        map.controls[google.maps.ControlPosition.TOP_LEFT].push(locationButton);

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
        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



        const markersArray = []

        for (var i = 0; i < parkings.length; i++) {
            const marker = new google.maps.Marker({
                position: parkings[i].coordinates,
                map: map,
                title: parkings[i].parkingName,
                icon: {
                    url: "http://maps.google.com/mapfiles/ms/icons/blue-dot.png"
                },
                animation: google.maps.Animation.DROP
            });

            var img =[]
            if(parkings[i].services.electricity === true) {
                img.push('<img src="../PicturesOfParkingServices/electricity2.png" alt="electricity">')
            }
            if(parkings[i].services.fence === true) {
                img.push('<img src="../PicturesOfParkingServices/fence2.png" alt="fence">')
            }
            if(parkings[i].services.food === true) {
                img.push('<img src="../PicturesOfParkingServices/food2.png" alt="food">')
            }
            if(parkings[i].services.gasStation === true) {
                img.push('<img src="../PicturesOfParkingServices/gasStation2.png" alt="gasStation">')
            }
            if(parkings[i].services.guard === true) {
                img.push('<img src="../PicturesOfParkingServices/guard2.png" alt="guard">')
            }
            if(parkings[i].services.light === true) {
                img.push('<img src="../PicturesOfParkingServices/light2.png" alt="light">')
            }
            if(parkings[i].services.lodging === true) {
                img.push('<img src="../PicturesOfParkingServices/lodging2.png" alt="lodging">')
            }
            if(parkings[i].services.securityCameras === true) {
                img.push('<img src="../PicturesOfParkingServices/securityCameras2.png" alt="securityCameras">')
            }
            if(parkings[i].services.truckService === true) {
                img.push('<img src="../PicturesOfParkingServices/service2.png" alt="truckService">')
            }
            if(parkings[i].services.shower === true) {
                img.push('<img src="../PicturesOfParkingServices/shower2.png" alt="shower">')
            }
            if(parkings[i].services.store === true) {
                img.push('<img src="../PicturesOfParkingServices/store2.png" alt="store">')
            }
            if(parkings[i].services.truckWash === true) {
                img.push('<img src="../PicturesOfParkingServices/truckWash2.png" alt="truckWash">')
            }
            if(parkings[i].services.water === true) {
                img.push('<img src="../PicturesOfParkingServices/water2.png" alt="water">')
            }
            if(parkings[i].services.wc === true) {
                img.push('<img src="../PicturesOfParkingServices/wc2.png" alt="wc">')
            }
            if(parkings[i].services.wifi === true) {
                img.push('<img src="../PicturesOfParkingServices/wifi2.png" alt="wifi">')
            }

            const contentString =

                '<div id="infoWindowContainer">' +
                '<div id ="formName">' + parkings[i].parkingName + " " + parkings[i].spotsCurrently + "/" + parkings[i].spotsTotal + '</div>' +
                '<div id ="formCoordinate">' + marker.getPosition() + '</div>' +
                '<div id="formMidl">' + img.toString() + '</div>'
            '</div>'

            const infowindow = new google.maps.InfoWindow({
                content: contentString

            });
            marker.addListener("click", () => {
                infowindow.open(map, marker);
            });
            markersArray.push(marker)
        }
        var pinIcon = new google.maps.MarkerImage(
            "http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|FFFF00",
            null, /* size is determined at runtime */
            null, /* origin is 0,0 */
            null, /* anchor is bottom center of the scaled image */
            new google.maps.Size(42, 68)
        );
        const showMarker = new google.maps.Marker({
            position: showPark[0].coordinates,
            map: map,
            title: showPark[0].parkingName,
            animation: google.maps.Animation.DROP

        });
        showMarker.setIcon(pinIcon);



    }

</script>
<script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>

</body>
<input
        id="pac-input"
        class="controls"
        type="text"
        placeholder="Search Box"
/>
<style>
    #map {
        height: 940px;
        width: 100%;
    }
</style>

<div id="map">
</div>

<script defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAkjeJ2RVdg225f2paPwjcgVOusnmH2-TQ&callback=initMap&libraries=places&v=weekly">
</script>

</body>
</html>