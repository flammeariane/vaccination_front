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
        <h3 class="text-center mt-5">Bienvenue <b>${username}</b> dans le tableau de bord du médecin!</h3>

        <div class="container mt-4">
            <table class="table mt-4">
                <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Date de Naissance</th>
                        <th>Numéro National</th>
                        <th>Heure du Rendez-vous</th>
                        <th>Commentaires</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="patient" items="${patients}">
                        <tr>
                            <td>${patient.nom}</td>
                            <td>${patient.prenom}</td>
                            <td>${patient.dateNaissance}</td>
                            <td>${patient.numRegNat}</td>
                            <td>${patient.heureRendezVous}</td>
                            <td>
                                <textarea class="form-control" rows="2" placeholder="Ajouter un commentaire..."></textarea>
                                <div class="comment-section">
                                    <!-- Afficher les précédents commentaires ici -->
                                    <c:forEach var="comment" items="${patient.commentaires}">
                                        <p>${comment}</p>
                                    </c:forEach>
                                </div>
                            </td>
                            <td class="control-buttons">
                                <button type="button" class="btn btn-primary">Enregistrer</button>
                                <button type="button" class="btn btn-secondary">Annuler</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <%@ include file="footer.jsp" %>
        <!-- Inclure les scripts nécessaires -->
        <!-- jQuery, Popper.js, et Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

        <script>
            // TODO Ajouter les scripts  pour la gestion des commentaires
        </script>
    </body>
</html>
