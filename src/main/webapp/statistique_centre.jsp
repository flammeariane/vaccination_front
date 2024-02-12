<html>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored="false" %>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Tableau de Bord du Responsable</title>
            <%@ include file="header.jsp" %>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/table-styles.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/button-styles.css">

        </head>
        <body>
            <div class="container mt-4">

         
                <body>
                    <div class="container mt-4">

                        <div class="d-flex justify-content-end align-items-center mb-3">
                            <form action="logout" method="post" > <button class="btn btn-custom-delete" type="submit">Déconnexion <i class="fa-solid fa-arrow-right-from-bracket"></i> </button></form>
                        </div>
                        <h3 class="text-center mt-5">Bienvenue <b>${membrePersonnel.prenom}</b> dans le tableau de bord du responsable!</h3>


                        <!-- TODO FIX THIS -->
                        <div class="search-section">
                            <div class="search-box">
                                <input type="text" id="searchByNumeroNational" placeholder="Rechercher par code postal" class="search-input">
                                <i class="fa fa-search search-icon"></i> <!-- Assurez-vous d'inclure FontAwesome pour l'icône -->
                            </div>
                        </div>

                        <h4>Liste des Centres</h4>
                        <table id="tableauPatient" class="table table-hover table-striped table-bordered mt-4">
                            <thead class="custom-header">
                                <tr>
                                    <th>Localité</th>
                                    <th>Adresse</th>
                                    <th>Code Postal</th>
                                    <th>Jours</th>
                                    <th>Horaires</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="centre" items="${centres.listCentre}">
                                    <tr>
                                        <td>${centre.nomCentre}</td>
                                        <td>${centre.adresse}</td>
                                        <td>${centre.codePostal}</td>
                                        <<td>${centre.jourSemaineOuverture}</td>
                                        <td>  de ${centre.heureOuverture} à ${centre.heureFermeture}</td>
                                        <td>
                                            <button class="btn btn-primary">Voir les stats</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                    </div>
                </body>
    </html>
