<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <%@ include file="/WEB-INF/bootstrap.jsp" %>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/custom-styles.css">





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
        <div class="container mt-4">

            <div class="d-flex justify-content-end align-items-center mb-3">
                <form action="logout" method="post" > <button class="btn btn-custom-delete" type="submit">Déconnexion <i class="fa-solid fa-arrow-right-from-bracket"></i> </button></form>
            </div>

            <h3 class="text-center mt-5">Bienvenue <b>${membrePersonnel.prenom}</b> dans le tableau de bord pour les <b>${membrePersonnel.role}  </b></h3>


            <table id="tableauPatient" class="table mt-4">

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
                        <th>Présence patient</th>
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
                <form action="confirmPresencePatientServlet" method="post">
                    <input type="hidden" name="numeroNational" value="${patient.numeroNational}" />
                    <input type="hidden" name="validePresence" value="" id="validePresence${patient.numeroNational}" />
                    <div class="input-group">
                        <span class="lot-display">${patient.numeroLot}</span>
                        <input type="text" name="numeroLot" value="${patient.numeroLot}" class="form-control lot-input" style="display: none;" />
                        <div class="input-group-append">
                            <button type="button" class="btn btn-toggle-lot" onclick="toggleLotInput('${patient.numeroNational}')">
                                Modifier
                            </button>
                        </div>
                    </div>
                </form>
            </td>
            <td>
                <button type="button" onclick="submitForm(true, '${patient.numeroNational}')" class="btn btn-custom">
                    Confirmer
                </button>
                <button type="button" onclick="submitForm(false, '${patient.numeroNational}')" class="btn btn-custom-discard">
                    Annuler
                </button>
            </td>
        </tr>
    </c:forEach>
</tbody>



            </table>

            <!-- TODO fix search -->
            <div class="row">
                <label>Rechercher : </label>
                <input type="text" id="searchByNumeroNational" placeholder="Rechercher par numéro national">
            </div>

            <%@ include file="footer.jsp" %>
            <!-- Inclure les scripts nécessaires -->
            <!-- jQuery, Popper.js, et Bootstrap JS -->
            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

            <script>
                            function submitForm(isConfirmed, numeroNational) {
                                // Sélectionner le champ caché par son ID unique et ajuster la valeur
                                var validePresenceField = document.getElementById('validePresence' + numeroNational);
                                validePresenceField.value = isConfirmed ? 'OUI' : 'NON';

                                // Soumettre le formulaire
                                validePresenceField.form.submit();
                            }
            </script>


            <script>
                function myFunction() {
                    var input, filter, table, tr, td, i, txtValue;
                    input = document.getElementById("searchByNumeroNational");
                    filter = input.value.toUpperCase();
                    table = document.getElementById("tableauPatient");
                    tr = table.getElementsByTagName("tr");

                    for (i = 0; i < tr.length; i++) {
                        td = tr[i].getElementsByTagName("td")[3]; // Index 3 pour la colonne "Numéro National"
                        if (td) {
                            txtValue = td.textContent || td.innerText;
                            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                                tr[i].style.display = "";
                            } else {
                                tr[i].style.display = "none";
                            }
                        }
                    }
                }
            </script>

            <script>
                function toggleLotInput(numeroNational) {
                    var lotInput = document.querySelector(`.lot-input`);
                    var confirmButton = document.querySelector(`.btn-custom`);
                    var cancelButton = document.querySelector(`.btn-custom-discard`);
                    var toggleButton = document.querySelector(`.btn-toggle-lot`);

                    if (lotInput.style.display === "none") {
                        lotInput.style.display = "inline-block";
                        confirmButton.style.display = "inline-block";
                        cancelButton.style.display = "inline-block";
                        toggleButton.style.display = "none";
                    } else {
                        lotInput.style.display = "none";
                        confirmButton.style.display = "none";
                        cancelButton.style.display = "none";
                        toggleButton.style.display = "inline-block";
                    }
                }
            </script>


    </body>
</html>
