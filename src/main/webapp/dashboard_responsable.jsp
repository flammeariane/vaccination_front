<html>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored="false" %>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Tableau de Bord du Médecin</title>
            <%@ include file="header.jsp" %>
            <style>
                .control-buttons {
                    display: flex;
                    gap: 10px;
                }
                .comment-section {
                    margin-top: 15px;
                }
            </style>
        </head>
        <body>
            <h3 class="text-center mt-5">Bienvenue <b>${username}</b> dans le tableau de bord du responsable!</h3>

            <div class="container mt-4">
                <!-- Logique conditionnelle en fonction du rôle -->
                <c:choose>
                    <c:when test="${role == 'Responsable general'}">
                        <!-- Liste des centres -->
                        <h4>Liste des Centres</h4>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Localité</th>
                                    <th>Heures d'ouverture</th>
                                    <th>Adresse</th>
                                    <th>Code Postal</th>
                                    <th>Statut</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="centre" items="${centres}">
                                    <tr>
                                        <td>${centre.localite}</td>
                                        <td>${centre.heuresOuverture}</td>
                                        <td>${centre.adresse}</td>
                                        <td>${centre.codePostal}</td>
                                        <td>${centre.statut}</td>
                                        <td>
                                            <button class="btn btn-primary">Ajouter Effectif</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                        <!-- Recherche de Centre -->
                        <h4>Recherche de Centre</h4>
                        <form action="rechercheCentre" method="get">
                            <div class="form-group">
                                <label for="codePostal">Code Postal:</label>
                                <input type="text" class="form-control" id="codePostal" name="codePostal">
                            </div>
                            <button type="submit" class="btn btn-primary">Rechercher</button>
                        </form>

                        <!-- Statistiques des Centres -->
                        <h4>Statistiques des Centres</h4>
                        <!-- Ici, insérez la logique pour afficher les statistiques des centres -->
                    </c:when>
                    <c:when test="${role == 'Responsable de centre'}">
                        <!-- Affichage des membres du personnel -->
                        <h4>Membres du Personnel</h4>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Nom</th>
                                    <th>Rôle</th>
                                    <th>Heures de Travail</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="membre" items="${membres}">
                                    <tr>
                                        <td>${membre.nom}</td>
                                        <td>${membre.role}</td>
                                        <td>
                                            <!-- Ici, insérez un système pour sélectionner les heures de travail -->
                                        </td>
                                        <td>
                                            <button class="btn btn-primary">Sélectionner</button>
                                            <button class="btn btn-secondary">Annuler</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                        <!-- Validation des sélections -->
                        <button class="btn btn-success">Valider les Sélections</button>
                    </c:when>
                </c:choose>
            </div>





            <%@ include file="footer.jsp" %>
            <!-- Inclure les scripts nécessaires -->
            <!-- jQuery, Popper.js, et Bootstrap JS -->
            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        </body>
    </html>
