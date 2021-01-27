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
    const locations = []
    const parkingNames = []
    const spotsTotal = []
    const allServices = []
</script>

<c:forEach var="parking" items="${allParkings}">

    <script>
        locations.push({lat: ${parking.getCoordinateLatitude()}, lng: ${parking.getCoordinateLongitude()}})
        parkingNames.push("${parking.getName()}")
        spotsTotal.push(${parking.getSpotsTotal()})
    </script>

</c:forEach>

<c:forEach var="services" items="${allServices}">
    <script>
        allServices.push(${services.getFence()}, ${services.getSecurityCameras()}, ${services.getWc()}, ${services.getShower()},
            ${services.getGuardedParking()}, ${services.getLighting()}, ${services.getElectricity()}, ${services.getWater()},
            ${services.getGasStation()}, ${services.getWifi()}, ${services.getLodging()}, ${services.getTruckService()},
            ${services.getTruckWash()}, ${services.getStore()}, ${services.getFood()})
    </script>
</c:forEach>

<script>

    function initMap(img) {
        map = new google.maps.Map(document.getElementById("map"), {
            center: {lat: 53.89955, lng: 27.54606},
            zoom: 10,
            mapTypeId: "roadmap",
        });

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


        const markersMap = new Map();
        for (let i = 0; i < locations.length; i++) {
            const marker = new google.maps.Marker({
                position: locations[i],
                map: map,
                title: parkingNames.shift(i),
                icon: {
                    url: "http://maps.google.com/mapfiles/ms/icons/blue-dot.png"
                },
                animation: google.maps.Animation.DROP
            });
            const parkingName = parkingNames.shift();
            const totalSpot = spotsTotal.shift();
            const serv = allServices.shift();
            var img1;
            var arr = allServices.splice(0, 15);
            var imageArr = ['<img src="../serv/electricity2.png" alt="tt">',
                '<img src="../serv/fence2.png" alt="tt">',
                '<img src="../serv/food2.png" alt="tt">',
                '<img src="../serv/gasStation2.png" alt="tt">',
                '<img src="../serv/guard2.png" alt="tt">',
                '<img src="../serv/light2.png" alt="tt">',
                '<img src="../serv/lodging2.png" alt="tt">',
                '<img src="../serv/securityCameras2.png" alt="tt">',
                '<img src="../serv/service2.png" alt="tt">',
                '<img src="../serv/shower2.png" alt="tt">',
                '<img src="../serv/store2.png" alt="tt">',
                '<img src="../serv/truckWash2.png" alt="tt">',
                '<img src="../serv/water2.png" alt="tt">',
                '<img src="../serv/wc2.png" alt="tt">',
                '<img src="../serv/wifi2.png" alt="tt">']
            var resulImageArr = []
            for (let a = 0; a < arr.length; a++) {
                if (arr[a].toString() == true) {
                    resulImageArr.push(imageArr[a]);
                }
            }
            const contentString =

                '<div id="infoWindowContainer">' +
                '<div id ="formName">' + parkingName + " " + totalSpot + "/" + totalSpot + '</div>' +
                '<div id ="formCoordinate">' + marker.getPosition() + '</div>' +
                '<div id="formMidl">' + imageArr.toString()

                + '</div>'
            '</div>'

            function showMessage() {
                write(imageArr);
            }

            // function showMessage() {
            //     alert( marker.lat );
            // }

            //     parkingName +
            // '<div id="content">' +
            //     '<div id="siteNotice">' +
            //
            //     serv +
            //     "</div>" +  + totalSpot +
            //     '<h1 id="firstHeading" class="firstHeading"> </h1>' +
            //     '<div id="bodyContent">' +
            //     "<p>Here will be information about my parking and </p> <p>the services that the driver can get here</p>" +
            //     '<p><a href=https://ru.wikipedia.org/wiki/%D0%A1%D1%82%D0%BE%D1%8F%D0%BD%D0%BA%D0%B0_' +
            //     '(%D1%81%D0%BE%D0%BE%D1%80%D1%83%D0%B6%D0%B5%D0%BD%D0%B8%D0%B5)>' +
            //     "what is parking?</a> " +
            //     "(last visited June 22, 2009).</p>" +
            //     "</div>" +
            //     "</div>";
            const infowindow = new google.maps.InfoWindow({
                content: contentString

            });
            marker.addListener("click", () => {
                infowindow.open(map, marker);
            });
            markersMap.set(i + 1, marker);
        }


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