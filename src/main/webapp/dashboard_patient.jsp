<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <div class="col-md-6 mb-4">
            <div class="card h-100">
                <div class="card-header text-info">Vos Informations</div>
                <div class="card-body">
                    <img src="static/img/jeune.jpg" alt="Photo du patient" class="img-fluid mb-3 rounded-circle" style="width: 150px; height: 150px; object-fit: cover;">
                    <p class="card-text"><strong>Nom:</strong> ${patient.nomFamille}</p>
                    <p class="card-text"><strong>Prénom:</strong> ${patient.prenom}</p>
                    <p class="card-text"><strong>Numéro National:</strong> ${patient.numeroNational}</p>
                    <p class="card-text"><strong>Adresse:</strong> ${patient.adresse} ${patient.numeroAdresse}, ${patient.ville}, ${patient.codePostal}</p>
                    <p class="card-text"><strong>Date de Naissance:</strong> <fmt:formatDate value="${patient.dateNaissance}" pattern="dd-MM-yyyy" /></p>
                    <p class="card-text"><strong>Email:</strong> ${patient.email}</p>
                </div>
            </div>
        </div>

        <!-- Consulter Etat Vaccinal -->
        <div class="col-md-6 mb-4">
            <div class="card h-100">
                <div class="card-header text-info">Consulter mon état Vaccinal</div>
                <div class="card-body">
                    <ul>
                        <c:forEach var="historique" items="${history.listeRendezVous}">
                            <li>Date: ${historique.dateRdv} - Vaccin: ${historique.nomVaccin}</li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>

        <!-- Prendre un Rendez-Vous -->
        <div class="col-md-12">
            <div class="card">
                <div class="card-header text-info">Prendre un Rendez-vous</div>
                <form action="choixcentre" method="post">
                <button class="btn btn-primary" type="submit">Prendre un rendez-vous</button>
            </form>
            </div>
        </div>
    </div>
</div>


           
            
     
        </div>
        <%@ include file="footer.jsp" %>
        <script>
             
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