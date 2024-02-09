<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Espace Patient</title>
        <%@ include file="/WEB-INF/bootstrap.jsp" %>
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/custom-styles.css">
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
        <%@ page import="utils.DateUtils" %>

        <%@ include file="header.jsp" %>

        <div class="container">

            <!-- Prendre un Rendez-Vous -->

            <div class="d-flex justify-content-end align-items-center mb-3">
                <form action="choixcentre" method="post"> <button class="btn btn-custom mr-2" type="submit"> Prendre un rendez-vous <i class="fa-solid fa-calendar-days"></i></button></form>
                <form action="logout" method="post" > <button class="btn btn-custom-delete" type="submit">Déconnexion <i class="fa-solid fa-arrow-right-from-bracket"></i> </button></form>
            </div>


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
                            <c:if test="${empty history.listeRendezVous}">
                                <p>Vous n'avez aucun rendez-vous passé ou actif.</p>
                            </c:if>
                            <c:forEach var="historique" items="${history.listeRendezVous}">
                                <div class="vaccination-record-toggle mb-2 d-flex justify-content-between align-items-center" style="cursor: pointer;">
                                    <div>
                                        ${DateUtils.formatTimestamp(historique.dateRdv, "dd-MM-yyyy")} - ${historique.nomVaccin}
                                    </div>
                                    <div>
                                        <span class="text-info">voir le détail</span>
                                        <i class="fas fa-chevron-down ml-2"></i>
                                    </div>
                                </div>


                                <div class="vaccination-record-details mb-3" style="display: none;">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <img src="static/img/${historique.nomVaccin}.jpg" alt="Vaccin ${historique.nomVaccin}" class="img-fluid rounded-circle" style="width: 100px; height: 100px;">
                                        </div>
                                        <div class="col-md-8">
                                            <p><strong>Centre de vaccination:</strong> ${historique.nomCentre}</p>
                                            <p><strong>Statut:</strong> ${historique.libelleStatut}</p>
                                            <p><strong>Dose:</strong> ${historique.numeroDose} sur ${historique.nbrDoseTotal}</p>
                                            <p><strong>Doses restantes:</strong> ${historique.nbrDoseRestant}</p>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
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

    <script>
        $(document).ready(function () {
            $('.vaccination-record-toggle').click(function () {
                $(this).find('i.fas').toggleClass('fa-chevron-down fa-chevron-up');
                $(this).next('.vaccination-record-details').slideToggle();
            });
        });
    </script>


</body>
</html>