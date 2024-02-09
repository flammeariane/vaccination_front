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

            .card {
                margin-bottom: 15px; /* Réduire la marge en bas de la carte */
            }
            .card-body {
                padding: 10px; /* Réduire l'espacement interne */
            }

            .card-img-container {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 150px; /* Hauteur fixe pour le conteneur de l'image */
                background-color: #f8f8f8; /* Couleur de fond optionnelle */
            }


        </style>

    </style>
</head>
<body>
    <%@ include file="header.jsp" %>
    <div class="container">
        <div class="row mb-5 mt-3">
            <div class="col-6">
                <button class="btn btn-custom-back" onclick="history.back()"> 
                    <i class="fas fa-arrow-left"></i> Retour
                </button>
            </div>

            <div class="col-2 offset-4">
                <button class="btn btn-custom-discard">
                    <i class="fas fa-times"></i> Annuler
                </button>
            </div>
        </div>
        <h2>veuillez choisir votre vaccin </h2>

        <%
            // Récupérer le nom du centre sélectionné de la session
            String selectedCentre = (String) session.getAttribute("selectedCentreNom");
        %>

        <form action="choixDate" method="post">
            <div class="row">
                <c:forEach var="vaccin" items="${vaccins.vaccin}" varStatus="loop">
                    <div class="col-md-4 mb-3">
                        <div class="card h-100 "> <!-- Utilisez h-100 pour que toutes les cartes aient la même hauteur -->
                            <div class="card-header text-info">Centre de Vaccination: <%= selectedCentre%></div> 
                            <div class="card-img-container bg-white">
                                <img src="static/img/${vaccin.nom}.jpg" alt="Image du vaccin ${vaccin.nom}" style="max-height: 100%; max-width: 100%; object-fit: contain;">
                            </div>

                            <div class="card-body d-flex flex-column"> 

                                <p class="card-title">Nom: ${vaccin.nom}</p> 
                                <p class="card-text">Nombre de doses: ${vaccin.nbrDoseTotal}</p> 
                                <c:if test="${vaccin.nbrDoseTotal >= 2}">
                                    <p class="card-text">Durée entre 2 doses: ${vaccin.dureeEntreDose} semaines</p>
                                </c:if>
                                <input type="hidden" name="selectedVaccinNom_${loop.index}" value="${vaccin.nom}">
                                <input type="hidden" name="selectedVaccinNbrDoseTotal_${loop.index}" value="${vaccin.nbrDoseTotal}">
                                <input type="hidden" name="selectedVaccinDureeEntreDose_${loop.index}" value="${vaccin.dureeEntreDose}">

                                <button class="btn btn-custom mt-auto" type="submit" name="selectedVaccin" value="${loop.index}">Choisir ce vaccin</button> <!-- mt-auto pour pousser le bouton en bas de la carte -->
                            </div>
                        </div>
                    </div>

                </c:forEach>
            </div>
        </form>
</body>
</html>
