
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>dashboard acceuillant</title>
        <%@ include file="/WEB-INF/common-includes.jsp" %>
        <%@ include file="/WEB-INF/header.jsp" %>
    </head>


    <body>
        <div class="container mt-4">

            <div class="d-flex justify-content-end align-items-center mb-3">
                <form action="logout" method="post" > <button class="btn btn-custom-delete" type="submit">D�connexion <i class="fa-solid fa-arrow-right-from-bracket"></i> </button></form>
            </div>

            <h3 class="text-center mt-5">Bienvenue <b>${membrePersonnel.prenom}</b> dans le tableau de bord pour les <b>${membrePersonnel.role}  </b></h3>

            <div class="search-section">
                <div class="search-box">
                    <input type="text" id="searchByNumeroNational" placeholder="Rechercher par num�ro national" class="search-input">
                    <i class="fa fa-search search-icon"></i> <!-- Assurez-vous d'inclure FontAwesome pour l'ic�ne -->
                </div>
            </div>

            <table id="tableauPatient" class="table table-hover table-striped table-bordered mt-4">
                <thead class="custom-header">
                    <tr>
                        <th>Date </th>
                        <th>Pr�nom</th>
                        <th>Nom</th>
                        <th>Num�ro Niss</th>
                        <th>Date de Naissance</th>
                        <th>Num�ro de dose</th>
                        <th>Statut </th>
                        <th>Numero de lot</th>
                        <th>Pr�sence patient</th>
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
                            <td>${patient.numeroDose}</td>
                            <td>${patient.statutRdv}</td>
                            <td>${patient.numeroLot}</td>
                            <td>
                                <form action="confirmPresencePatientServlet" method="post">
                                    <input type="hidden" name="numeroNational" value="${patient.numeroNational}" />
                                    <input type="hidden" name="validePresence" value="" id="validePresence${patient.numeroNational}" />
                                    <div class="btn-group" role="group" aria-label="Basic example">
                                        <button type="button" onclick="submitForm(true, '${patient.numeroNational}')" class="btn btn-custom">
                                            <i class="fas fa-check"></i>
                                        </button>
                                        <button type="button" onclick="submitForm(false, '${patient.numeroNational}')" class="btn btn-custom-discard">
                                            <i class="fa-regular fa-calendar-xmark"></i>
                                        </button>
                                    </div>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <c:if test="${empty patientList.listPatient}">
                <div class="alert alert-warning" role="alert" style="margin-top: 20px; color: orange;">
                    Il n'y a plus de pr�sence patient � valider � la date du jour.
                </div>
            </c:if>

            <%@ include file="/WEB-INF/footer.jsp" %>


            <script src="${pageContext.request.contextPath}/static/js/searchByNumeroNational.js"></script>

            <script>
                                            function submitForm(isConfirmed, numeroNational) {
                                                // S�lectionner le champ cach� par son ID unique et ajuster la valeur
                                                var validePresenceField = document.getElementById('validePresence' + numeroNational);
                                                validePresenceField.value = isConfirmed ? 'OUI' : 'NON';

                                                // Soumettre le formulaire
                                                validePresenceField.form.submit();
                                            }
            </script>

    </body>
</html>
