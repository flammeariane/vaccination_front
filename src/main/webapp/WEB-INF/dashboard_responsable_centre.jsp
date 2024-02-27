<html>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Tableau de Bord du Responsable de centre</title>
           
            <%@ include file="/WEB-INF/common-includes.jsp" %>
        </head>
        <body>
             <%@ include file="/WEB-INF/header.jsp" %>
            <div class="container mt-4">
                <div class="d-flex justify-content-end align-items-center mb-3">
                    <form action="logout" method="post" > <button class="btn btn-custom-delete" type="submit">Déconnexion <i class="fa-solid fa-arrow-right-from-bracket"></i> </button></form>
                </div>
                <h3 class="text-center mt-5">Bienvenue <b>${membrePersonnel.prenom}</b> dans le tableau de bord du responsable de centre!</h3>

                <!-- TODO FIX THIS -->
                <div class="search-section">
                    <div class="search-box">
                        <input type="text" id="searchByPostCode" placeholder="Rechercher par code postal" class="search-input">
                        <i class="fa fa-search search-icon"></i> 
                    </div>
                </div>

                <div id="responseContainer" class="alert alert-warning" role="alert" style="margin-top: 20px; color: orange; display:none;"></div>

                <form action="statistique" method="post">
                    <table id="tableauPatient" class="table table-hover table-striped table-bordered mt-4">
                        <thead class="custom-header">
                            <tr>
                                <th>Nom de Famille</th>
                                <th>Prénom</th>
                                <th>Rôle</th>
                                <th>Localité</th>
                                <th>Numéro de Téléphone</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="membre" items="${members.members}">
                                <tr>
                                    <td>${membre.nomFamille}</td>
                                    <td>${membre.prenom}</td>
                                    <td>${membre.role}</td>
                                    <td>${membre.localite}</td> 
                                    <td>${membre.numTelephone}</td>
                                    <td>
                                        <button type="button" class="btn btn-custom" data-toggle="modal" data-target="#horaireModal">Ajouter un horaire</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
            </div>     
        </form>



        <!-- Modal pour la sélection de l'horaire -->
        <div class="modal fade" id="horaireModal" tabindex="-1" role="dialog" aria-labelledby="horaireModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="horaireModalLabel">Sélectionner un Horaire</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <!-- Formulaire pour sélectionner date et heure -->
                        <form id="horaireForm">
                            <div class="form-group">
                                <label for="date">Date de début  :</label>
                                <div class="input-group date" id="datetimepicker1" data-target-input="nearest">
                                    <input type="text" class="form-control datetimepicker-input" data-target="#datetimepicker1"/>
                                    <div class="input-group-append" data-target="#datetimepicker1" data-toggle="datetimepicker">
                                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="time">Date de fin : </label>
                                <div class="input-group date" id="datetimepicker2" data-target-input="nearest">
                                    <input type="text" class="form-control datetimepicker-input" data-target="#datetimepicker2"/>
                                    <div class="input-group-append" data-target="#datetimepicker2" data-toggle="datetimepicker">
                                        <div class="input-group-text"><i class="fa fa-clock"></i></div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-custom-delete" data-dismiss="modal">Fermer</button>
                        <button type="submit" form="horaireForm" class="btn btn-custom" id="saveButton" disabled>Sauvegarder</button>

                    </div>
                </div>
            </div>
        </div>

        <%@ include file="/WEB-INF/footer.jsp" %>

        <script src="${pageContext.request.contextPath}/static/js/dashboard_responsable_centre.js"></script>
     
    </body>
</html>
