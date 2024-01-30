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

        <%@ include file="header.jsp" %>

        <div class="container">    
            <div class="row mb-5">
                <div class="col-6">
                    <button class="btn btn-custom-back" onclick="history.back()"> 
                        <i class="fas fa-arrow-left"></i> Retour
                    </button>
                </div>
                <div class="col-2 offset-4">
                    <form action="" method="post">
                        <button class="btn btn-custom-discard">
                            <i class="fas fa-times"></i> Annuler
                        </button>
                    </form>
                </div>
            </div>

            <h1>veuillez choisir votre centre</h1>
            <form action="choixVaccin" method="post">
                <div class="row">
                    <c:forEach var="centre" items="${centres.centreInfo}" varStatus="loop">
                        <div class="col-md-4 mb-4">
                            <div class="card">
                                <div class="card-header text-info">Centre de Vaccination: ${centre.nomCentre}</div>
                                <div class="card-body">
                                    <!-- Utilisation de varStatus.index pour rendre les noms de champs uniques -->
                                    <input type="hidden" name="selectedCentreNom_${loop.index}" value="${centre.nomCentre}">
                                    <input type="hidden" name="selectedCentreCodePostal_${loop.index}" value="${centre.codePostal}">
                                    <input type="hidden" name="selectedCentreAdresse_${loop.index}" value="${centre.adresse}">
                                    <input type="hidden" name="selectedCentreNom_${loop.index}" value="${centre.numero}">
                                    <input type="hidden" name="selectedCentreTelephone_${loop.index}" value="${centre.telephone}">
                                    <input type="hidden" name="selectedCentreJourOuverture_${loop.index}" value="${centre.jourSemaineOuverture}">
                                    <input type="hidden" name="selectedCentreHeureOuverture_${loop.index}" value="${centre.heureOuverture}">
                                    <input type="hidden" name="selectedCentreHeureFermeture_${loop.index}" value="${centre.heureFermeture}">

                                    <p>Adresse:  ${centre.adresse} ${centre.numero}</p>
                                    <p>Téléphone: ${centre.telephone}</p>
                                    <p>Jours d'ouverture: ${centre.jourSemaineOuverture}</p>
                                    <p>Heure d'ouverture:  de ${centre.heureOuverture} à ${centre.heureFermeture}</p>

                                    <!-- Utilisation de loop.index comme valeur pour le bouton submit -->
                                    <button class="btn btn-custom mt-2" type="submit" name="selectedCentre" value="${loop.index}">
                                        Choisir ce centre
                                    </button>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </form>
        </div>
    </body>
</html>
