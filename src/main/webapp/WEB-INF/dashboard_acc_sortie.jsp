
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
                        <th>Date rendez vous </th>
                        <th>Pr�nom</th>
                        <th>Nom</th>
                        <th>Num�ro National</th>
                        <th>Date de Naissance</th>
                        <th>Num�ro de dose</th>
                        <th>Statut rendez vous</th>
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
                            <td>
                                <!-- Champ numeroLot �ditable sans bouton de validation suppl�mentaire -->
                                <input type="text" name="numeroLot" value="${patient.numeroLot}" class="form-control" required onkeyup="validateButton('${patient.numeroNational}', this.value)" />
                            </td>
                            <td>
                                <form action="confirmVaccinationPatientServlet" method="post">
                                    <input type="hidden" name="numeroNational" value="${patient.numeroNational}" />
                                    <input type="hidden" name="numeroLot" id="hiddenNumeroLot${patient.numeroNational}" />
                                    <!-- Les boutons pour confirmer ou refuser la pr�sence -->
                                    <button type="button" onclick="submitForm(true, '${patient.numeroNational}')" class="btn btn-custom">
                                        <i class="fas fa-check"></i>
                                    </button>
                                    <button type="button" onclick="submitForm(false, '${patient.numeroNational}')" class="btn btn-custom-discard">
                                        <i class="fas fa-times"></i>
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <c:if test="${empty patientList.listPatient}">
                <div class="alert alert-warning" role="alert" style="margin-top: 20px; color: orange;">
                    Il n'y a plus de vaccination � valider � la date du jour.
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

            <script>
                function validateButton(numeroNational, numeroLot) {
                    // Mettre � jour la valeur cach�e de numeroLot � envoyer avec le formulaire
                    document.getElementById('hiddenNumeroLot' + numeroNational).value = numeroLot;
                }

                function submitForm(isConfirmed, numeroNational) {
                    // R�cup�rer le champ cach� et le formulaire pour soumettre
                    var validePresenceField = document.getElementById('hiddenNumeroLot' + numeroNational).form;
                    validePresenceField.submit();
                }
            </script>

    </body>
</html>


