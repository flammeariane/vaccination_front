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
        <h3 class="text-center mt-5">Bienvenue <b>${membrePersonnel.prenom}</b> dans le tableau de bord pour les <b>${membrePersonnel.role}  </b></h3>
        <div class="container mt-4">
            <table class="table mt-4">
                <thead>
                    <tr>
                        <th>Date rendez vous </th>
                        <th>Prénom</th>
                        <th>Nom</th>
                        <th>Numéro National</th>
                        <th>Date de Naissance</th>

                        <th>Numéro de dose</th>
                        <th>Statut rendez vous</th>
                        <th>Numero de lot</th>

                        <c:choose>
                            <c:when test="${userType == 'Medecin'}">
                                <th>Commentaires</th>
                                </c:when>
                            </c:choose>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="patient" items="${patientList.listPatient}" >
                        <tr>
                            <td>${patient.dateRdv}</td>
                            <td>${patient.prenom}</td>
                            <td>${patient.nomFamille}</td>
                            <td>${patient.numeroNational}</td>
                          <td> <fmt:formatDate value="${patient.dateNaissance}" pattern="dd-MM-yyyy" />  </td>
                            <td>${patient.numeroDose}</td>
                            <td>${patient.statutRdv}</td>
                            <td>${patient.numeroLot}</td>

                            <c:choose>
                                <c:when test="${membrePersonnel.role == 'Medecin'}">
                                    <td>
                                        Section de commentaires pour Médecin 
                                        <textarea class="form-control" rows="2" placeholder="Ajouter un commentaire..."></textarea>
                                        Afficher les précédents commentaires ici 
                                        <c:forEach var="comment" items="${patient.commentaires}">
                                            <p>${comment}</p>
                                        </c:forEach>
                                    </td>
                                </c:when>
                            </c:choose>
                            <td>
                              
                             
                                        <button type="button" class="btn btn-primary">Confirmer la présence</button>
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

    </body>
</html>
