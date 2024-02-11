<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <%@ include file="/WEB-INF/bootstrap.jsp" %>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/button-styles.css">
           <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/table-styles.css">

        <title>dashboard acceuillant</title>
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
                        <th>Date </th>
                        <th>Prénom</th>
                        <th>Nom</th>
                        <th>Numéro Niss</th>
                        <th>Date de Naissance</th>
                        <th>Numéro de dose</th>
                        <th>Statut </th>
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
        Il n'y a plus de présence patient à valider à la date du jour.
    </div>
</c:if>



            <%@ include file="footer.jsp" %>
            <!-- Inclure les scripts nécessaires -->
            <!-- jQuery, Popper.js, et Bootstrap JS -->
            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
             <script src="${pageContext.request.contextPath}/static/js/search.js"></script>

            <script>
                                            function submitForm(isConfirmed, numeroNational) {
                                                // Sélectionner le champ caché par son ID unique et ajuster la valeur
                                                var validePresenceField = document.getElementById('validePresence' + numeroNational);
                                                validePresenceField.value = isConfirmed ? 'OUI' : 'NON';

                                                // Soumettre le formulaire
                                                validePresenceField.form.submit();
                                            }
            </script>

    </body>
</html>
