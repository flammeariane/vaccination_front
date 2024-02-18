
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="common-includes.jsp" %>
        <title>dashboard Medecin</title>
        <%@ include file="header.jsp" %>
    </head>

    <body>
        <div class="container mt-4">
            <div class="d-flex justify-content-end align-items-center mb-3">
                <form action="logout" method="post" > <button class="btn btn-custom-delete" type="submit">Déconnexion <i class="fa-solid fa-arrow-right-from-bracket"></i> </button></form>
            </div>

            <h3 class="text-center mt-5">Bienvenue <b>${membrePersonnel.prenom}</b> dans le tableau de bord pour les <b>${membrePersonnel.role}  </b></h3>

            <div class="search-section">
                <div class="search-box">
                    <input type="text" id="searchByNumeroNational" placeholder="Rechercher par numéro national" class="search-input">
                    <i class="fa fa-search search-icon"></i> <!-- Assurez-vous d'inclure FontAwesome pour l'icône -->
                </div>
            </div>

            <table id="tableauPatient" class="table table-hover table-striped table-bordered mt-4">
                <thead class="custom-header">
                    <tr>
                        <th>Date rendez vous </th>
                        <th>Prénom</th>
                        <th>Nom</th>
                        <th>Niss</th>
                        <th>Date de Naissance</th>
                        <th>commentaires précédents</th>
                        <th>Incident</th>
                        <th>Valider</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="patient" items="${patientList.listPatient}">
                        <tr>
                            <td><fmt:formatDate value="${patient.dateRdv}" pattern="dd-MM-yyyy HH:mm" /></td>
                            <td>${patient.prenom}</td>
                            <td>${patient.nomFamille}</td>
                            <td>${patient.numeroNational}</td>
                            <td><fmt:formatDate value="${patient.dateNaissance}" pattern="dd-MM-yyyy" /></td>
                            <td>
                                <!-- Lien pour ouvrir la modal des commentaires -->
                                <a href="#" onclick="ouvrirModalIncident('${patient.numeroNational}')">voir les commentaires</a>
                            </td>
                            <td>                          
                                <input type="text" name="commentaire" value="" class="form-control"  />
                            </td>
                            <td>
                                <form action="confirmVaccinationPatientServlet" method="post">
                                    <input type="hidden" name="numeroNational" value="${patient.numeroNational}" />
                                    <input type="hidden" name="numeroLot" id="hiddenNumeroLot${patient.numeroNational}" />
                                    <input type="hidden" name="commentaire" class="form-control" />                        
                                    <button type="submit" class="btn btn-custom"> <i class="fas fa-check"></i></button>
                                    <button type="button" class="btn btn-custom-discard" onclick="cancelAction('${patient.numeroNational}')"> <i class="fas fa-times"></i></button>
                                </form>
                            </td>
                        </tr>

                        <!-- Modal pour afficher les commentaires -->
                    <div class="modal fade" id="commentModal${patient.numeroNational}" tabindex="-1" role="dialog" aria-labelledby="commentModalLabel${patient.numeroNational}" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="commentModalLabel${patient.numeroNational}">Commentaires pour ${patient.prenom}</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">    
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-custom-discard" data-dismiss="modal">Fermer</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                </tbody>
            </table>

            <c:if test="${empty patientList.listPatient}">
                <div class="alert alert-warning" role="alert" style="margin-top: 20px; color: orange;">
                    Il n'y a plus de patients auquel vous pouvez ajouter un incident.
                </div>
            </c:if>

            <%@ include file="footer.jsp" %>

            <script src="${pageContext.request.contextPath}/static/js/searchByNumeroNational.js"></script>
            <script type="module" src="${pageContext.request.contextPath}/static/js/dashboard_medecin.js"></script>


    </body>
</html>


