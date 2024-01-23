<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        <h3 class="text-center mt-5">Bienvenue <b>${membrePersonnel.prenom}</b> dans le tableau de bord pour les <b>${membrePersonnel.role}</b></h3>


        <h2>Liste des Rendez-Vous du Jour</h2>

        <c:if test="${not empty listeRendezVous}">
            <p>Des rendez-vous sont disponibles.</p>
        </c:if>
        <c:if test="${empty listeRendezVous}">
            <p>Aucun rendez-vous disponible.</p>
        </c:if>

        <table border="1">
      
            <c:forEach items="${listeRendezVous}" var="rdv">
                <tr>
                    <td><fmt:formatDate value="${rdv.dateRdv}" pattern="dd/MM/yyyy HH:mm" /></td>
                    <td>${rdv.prenom}</td>
                    <td>${rdv.nomFamille}</td>
                    <td>${rdv.numeroNational}</td>
                    <td><fmt:formatDate value="${rdv.dateNaissance}" pattern="dd/MM/yyyy" /></td>
                    <td>${rdv.numeroDose}</td>
                    <td>${rdv.statutRdv}</td>
                    <td>${rdv.numeroLot}</td>
                </tr>
            </c:forEach>
        </table>


        <div class="container mt-4">
            <table class="table mt-4">
                <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Date de Naissance</th>
                        <th>Numéro National</th>
                        <th>Heure du Rendez-vous</th>
                            <c:choose>
                                <c:when test="${role == 'Medecin'}">
                                <th>Commentaires</th>
                                </c:when>
                            </c:choose>
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

                            <c:choose>
                                <c:when test="${role == 'Medecin'}">
                                    <td>
                                        <!-- Section de commentaires pour Médecin -->
                                        <textarea class="form-control" rows="2" placeholder="Ajouter un commentaire..."></textarea>
                                        <!-- Afficher les précédents commentaires ici -->
                                        <c:forEach var="comment" items="${patient.commentaires}">
                                            <p>${comment}</p>
                                        </c:forEach>
                                    </td>
                                </c:when>
                            </c:choose>

                            <td>
                                <!-- Boutons différents selon le rôle -->
                                <c:choose>
                                    <c:when test="${role == 'Accueillant en entree'}">
                                        <button type="button" class="btn btn-primary">Confirmer la présence</button>
                                        <button type="button" class="btn btn-secondary">Annuler</button>
                                    </c:when>
                                    <c:when test="${role == 'Accueillant de sortie'}">
                                        <button type="button" class="btn btn-primary">Sélectionner</button>
                                        <button type="button" class="btn btn-secondary">Annuler</button>
                                    </c:when>
                                    <c:when test="${role == 'Medecin'}">
                                        <button type="button" class="btn btn-primary">Enregistrer</button>
                                        <button type="button" class="btn btn-secondary">Annuler</button>
                                    </c:when>
                                </c:choose>
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

    </body>
</html>
