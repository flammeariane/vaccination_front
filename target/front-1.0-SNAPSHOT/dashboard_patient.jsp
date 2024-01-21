<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Espace Patient</title>
        <%@ include file="/WEB-INF/bootstrap.jsp" %>
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
               <script src="https://maps.googleapis.com/maps/api/js?key=??????Y&callback=initMap" async defer></script>

        <style>
            .card-header {
                background-color: #007bff;
                color: white;
            }
            .container {
                padding-top: 20px;
            }
            .card {
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>
        <%@ include file="header.jsp" %>

        <div class="container">
            <!-- Vos Informations -->
            <div class="row">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header text-info">Vos Informations</div>
                        <div class="card-body">
                            <img src="static/img/jeune.jpg" alt="Photo du patient" class="img-fluid mb-2 rounded-circle" style="width: 150px; height: 150px; object-fit: cover;">
                            <p class="card-text">Nom: <span class="font-weight-bold">${patient.nomFamille}</span></p>
                            <p class="card-text">Pr�nom: <span class="font-weight-bold">${patient.prenom}</span></p>
                            <p class="card-text">Num�ro National: <span class="font-weight-bold">${patient.numeroNational}</span></p>
                            <p class="card-text">Adresse: <span class="font-weight-bold">${patient.adresse} ${patient.numeroAdresse}, ${patient.ville}, ${patient.codePostal}</span></p>
                            <p class="card-text">Date de Naissance: <span class="font-weight-bold">${formattedDateNaissance}</span></p>
                            <p class="card-text">Email: <span class="font-weight-bold">${patient.email}</span></p>
                        </div>
                    </div>
                </div>

                <!-- Consulter �tat Vaccinal -->
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header text-info">Consulter mon �tat Vaccinal</div>
                        <div class="card-body">

                            <p>Informations sur l'�tat vaccinal du patient.</p>
                            <c:forEach var="da" items="${historique}">
                                <p>${da.nom}</p>
                            </c:forEach>
                            <c:out value="${historique.numeroNational}" escapeXml="false" />
                            <button class="btn btn-primary">Voir D�tails</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card-body">

                <c:forEach var="rendezVous" items="${centresVaccination.centres.centreInfo}">
                    <div class="card mb-2">

                        <div class="card-header">
                            Rendez-Vous Vaccin: <span class="font-weight-bold">${rendezVous.nomCentre}</span>
                        </div>
                        <div class="card-body">
                            <p>Date: <span class="font-weight-bold">${rendezVous.dateRdv}</span></p>
                            <p>Centre: <span class="font-weight-bold">${rendezVous.nomCentre}</span></p>
                            <p>Statut: <span class="font-weight-bold">${rendezVous.libelleStatut}</span></p>
                            <p>Num�ro de dose: <span class="font-weight-bold">${rendezVous.numeroDose}</span> sur ${rendezVous.nbrDoseTotal}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!-- Prendre un Rendez-Vous -->
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header text-info">Prendre un Rendez-Vous</div>
                        <div class="card-body">
                            <label for="centreSelect">Centre de Vaccination:</label>
                            <select id="centreSelect" class="form-control">
                                <c:forEach var="centre" items="${centres.centreInfo}">
                                    <option value="${centre.nomCentre}"
                                            data-adresse="${centre.adresse}"
                                            data-codepostal="${centre.codePostal}"
                                            data-numero="${centre.numero}"
                                            data-telephone="${centre.telephone}"
                                            data-joursemaineouverture="${centre.jourSemaineOuverture}"
                                            data-heurefermeture="${centre.heureFermeture}">
                                        ${centre.nomCentre} - ${centre.adresse}
                                    </option>
                                </c:forEach>
                            </select>
                            <label for="vaccineSelect">Type de Vaccin:</label>
                            <select id="vaccineSelect" class="form-control">
                                <c:forEach var="vaccin" items="${vaccins.vaccin}">
                                    <option value="${vaccin.nom}">${vaccin.nom}</option>
                                </c:forEach>
                            </select>

                            <label for="datePicker">Date du Rendez-Vous:</label>
                            <input type="text" id="datePicker" class="form-control datepicker">
                            <button class="btn btn-primary mt-2">R�server</button>

                            <!-- Nouvelle section pour les d�tails du centre -->
                            <div id="centreDetails" style="display: none;" class="row">
                                <div class="col-md-5">
                                    <h5 class="mt-3">D�tails du Centre</h5>
                                    <p id="centreAdresse"></p>
                                    <p id="centreTelephone"></p>
                                    <p id="centreJourSemaineOuverture"></p>
                                    <p id="centreHeureFermeture"></p>
                                </div>
                                <div class="col-md-7">
                                    <!--  section pour la carte Google Maps -->
                                    <div id="map" style="height: 300px;"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@ include file="footer.jsp" %>

        <script>
            $(document).ready(function () {
                // Appel AJAX pour charger � la fois les centres et les vaccins
                $.get("centresVaccination", function (data) {


                    var historique = data.vaccins.vaccin
                    // Traitement pour les centres de vaccination
                    var centres = data.centres.centreInfo;
                    var selectHtmlCentres = "";



                    if (centres) {
                        centres.forEach(function (centre) {
                            selectHtmlCentres += "<option value='" + centre.nomCentre + "' "
                                    + "data-adresse='" + centre.adresse + "' "
                                    + "data-codepostal='" + centre.codePostal + "' "
                                    + "data-numero='" + centre.numero + "' "
                                    + "data-telephone='" + centre.telephone + "' "
                                    + "data-joursemaineouverture='" + centre.jourSemaineOuverture + "' "
                                    + "data-heurefermeture='" + centre.heureFermeture + "'>"
                                    + centre.nomCentre + " - " + centre.adresse + "</option>";
                        });
                    } else {
                        selectHtmlCentres = "<option>Aucun centre disponible</option>";
                    }
                    $('#centreSelect').html(selectHtmlCentres);

                    // Traitement pour les vaccins
                    var vaccins = data.vaccins.vaccin;
                    var selectHtmlVaccins = "";

                    vaccins.forEach(function (vaccin) {
                        selectHtmlVaccins += "<option value='" + vaccin.nom + "'>" + vaccin.nom + "</option>";
                    });

                    $('#vaccineSelect').html(selectHtmlVaccins);
                });

                // Initialisez le datepicker
                $('#datePicker').datepicker({
                    format: 'dd/mm/yyyy',
                    autoclose: true,
                    todayHighlight: true
                });

                // Gestionnaire d'�v�nements pour la s�lection du centre
                $('#centreSelect').on('change', function () {
                    var selectedCentre = $(this).find('option:selected');
                    var lat = parseFloat(selectedCentre.data('lat'));
                    var lng = parseFloat(selectedCentre.data('lng'));
                    var adresseComplete = selectedCentre.data('adresse') + ', ' + selectedCentre.data('codepostal');
                    var adresse = "Adresse: " + selectedCentre.data('adresse') + ', ' + selectedCentre.data('numero');

                    $('#centreAdresse').text(adresse);
                    $('#centreCodePostal').text("Code Postal: " + selectedCentre.data('codepostal'));
                    $('#centreNumero').text("Num�ro: " + selectedCentre.data('numero'));
                    $('#centreTelephone').text("T�l�phone: " + selectedCentre.data('telephone'));
                    $('#centreJourSemaineOuverture').text("Jours d'ouverture: " + selectedCentre.data('joursemaineouverture'));
                    $('#centreHeureFermeture').text("Heure de Fermeture: " + selectedCentre.data('heurefermeture'));

                    $('#centreDetails').show();



                    // Mise � jour de la carte

                    if (!isNaN(lat) && !isNaN(lng)) {
                        var latLng = new google.maps.LatLng(lat, lng);
                        window.map.setCenter(latLng);
                    } else {
                        console.error('Invalid coordinates:', lat, lng);
                    }

                    geocodeAddress(adresseComplete, function (location) {
                        window.map.setCenter(location);
                    });

                    var latLng = new google.maps.LatLng(selectedCentre.data('latitude'), selectedCentre.data('longitude'));
                    window.map.setCenter(latLng);
                });
            });

            function initMap() {
                var map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 14,
                    center: {lat: -34.397, lng: 150.644} // Coordonn�es par d�faut
                });

                window.map = map;
            }

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


        </script>
    </body>
</html>