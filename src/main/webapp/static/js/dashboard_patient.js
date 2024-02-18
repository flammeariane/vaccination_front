

$(document).ready(function () {
    $('.vaccination-record-toggle').click(function () {
        $(this).find('i.fas').toggleClass('fa-chevron-down fa-chevron-up');
        $(this).next('.vaccination-record-details').slideToggle();
    });
});


function initMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 14,
        center: {lat: -34.397, lng: 150.644} // CoordonnÃ©es par dÃ©faut
    });

    window.map = map;
}


// Déclenchez la mise à jour de la carte lorsque la sélection du centre change
document.getElementById('centreSelect').addEventListener('change', function () {
    var selectedOption = this.options[this.selectedIndex];
    var adresse = selectedOption.getAttribute('data-adresse');
    updateMap(map, adresse);
});

function geocodeAddress(address, callback) {
    var geocoder = new google.maps.Geocoder();
    geocoder.geocode({'address': address}, function (results, status) {
        if (status === 'OK') {
            callback(results[0].geometry.location);
        } else {
            alert('Geocode was not successful for the following reason: ' + status);
        }
    });
}

