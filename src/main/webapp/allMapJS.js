/* global google */

var map;
var userLocation;
var templeCenter;
var trucks= [];

function initMap() {
    var temp = {
      name: "Cha Cha",
      lat: 39.979858,
      lng: -75.154957,
    };
    trucks.push(temp);
    temp = {
      name: "Mexican Grill Stand",
      lat: 39.980964,
      lng: -75.155242,
    };
    trucks.push(temp);
    temp = {
      name: "Chicken Heaven",
      lat: 39.980000,
      lng:  -75.156046,
    };
    trucks.push(temp);
    
    //MAP SETUP
    templeCenter = {lat: 39.981478, lng: -75.155124};
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 16,
        center: templeCenter
    });

    //GET USER LOCATION
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            userLocation = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            addUserMarker();
        }, function (error) {
            //USER LOCATION NOT FOUND
            //userLocation = {lat: 39.981478, lng: -75.155124};
            console.log(error);
        });
    } else {
        //BROWSER DOESN'T SUPPORT GEOLOCATION
        //userLocation = {lat: 39.981478, lng: -75.155124};
    }

    var infowindow = new google.maps.InfoWindow();
    for (var i = 0; i < trucks.length; i++) {
        var marker = createMarker(trucks[i]);
        //HAVE TO PASS THE LAT AND LONG IN THE GET DIRECTIONS STRING
        var contentString = "<h5 style='color: black'>" + trucks[i].name + "</h5>" +
        "<button type='button' class ='btn btn-primary-outline btn-sm' onclick='getDirections(" + trucks[i].lat + ", " + trucks[i].lng + ")'>Directions</button> <button type='button' onclick='toMenu' class='btn btn-sm btn-danger-outline'>View Menu</button>";
        //ADD INFO WINDOW TO MARKER
        bindwindow(marker, map, infowindow, contentString);
        marker.setMap(map);
    }
}
;

function addUserMarker() {
    //USER MARKER
    var image = 'https://cdn0.iconfinder.com/data/icons/project-management-1-1/24/46-48.png';
    var userMarker = new google.maps.Marker({
        position: {lat: userLocation.lat, lng: userLocation.lng},
        animation: google.maps.Animation.DROP,
        title: "Your Location",
        icon: image
    });
    userMarker.setMap(map);
    map.setCenter(userLocation);
}
;

function createMarker(truck) {
    //MARKER
    var marker = new google.maps.Marker({
        position: {lat: truck.lat, lng: truck.lng},
        animation: google.maps.Animation.DROP,
        title: truck.name
    });
    return marker;
}
;

function bindwindow(marker, map, infowindow, content) {
    marker.addListener('click', function () {
        infowindow.setContent(content);
        infowindow.open(map, marker);
    });
}
;

function getDirections(endLat, endLng) {
    if (userLocation == null) {
        alert("Sorry, your location could not be found or was denied.");
    }
    else {
        var link = "http://maps.google.com/maps?saddr=" + userLocation.lat + "," + userLocation.lng + "&daddr=" + endLat + "," + endLng;
         window.open(link);
    }
}