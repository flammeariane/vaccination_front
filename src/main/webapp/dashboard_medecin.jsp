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

        <title>dashboard Medecin</title>
        <%@ include file="header.jsp" %>
        <style>
            .control-buttons {
                display: flex;
                gap: 10px;
            }
            .comment-section {
                margin-top: 15px;
            }

            .search-section {
                background-color: #f2f2f2; /* Couleur de fond */
                padding: 20px; /* Espacement int�rieur */
                margin-top: 20px; /* Espacement ext�rieur en haut */
                border-radius: 10px; /* Coins arrondis */
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .search-box {
                position: relative;
                width: 100%;
                max-width: 500px; /* Largeur maximale du champ de recherche */
            }
            .search-input {
                width: 100%;
                padding: 10px 20px;
                padding-right: 40px; /* Espace pour l'ic�ne de recherche */
                font-size: 16px; /* Taille de la police */
                border: 1px solid #ccc;
                border-radius: 20px; /* Coins arrondis */
                box-shadow: 0 2px 4px rgba(0,0,0,0.1); /* Ombre l�g�re */
            }
            .search-icon {
                position: absolute;
                right: 10px;
                top: 50%;
                transform: translateY(-50%);
                color: #777;
            }

            .custom-header {
                background-color: #6c757d; /* Bootstrap gris fonc�, mais plus clair que le noir */
                color: #ffffff; /* Texte blanc pour contraste */
            }

            .table th, .table td {
                max-width: 150px; /* Limite la largeur maximale de toutes les cellules */
                overflow: hidden; /* Emp�che le d�bordement du contenu */
                text-overflow: ellipsis; /* Ajoute des points de suspension si le texte d�borde */
                white-space: nowrap; /* Emp�che le texte de passer � la ligne automatiquement */
            }

            .table th {
                word-break: break-all; /* Permet de casser les mots trop longs pour �viter le d�bordement */
            }

            .btn-group {
                width: 100%; /* Permet aux boutons de remplir l'espace horizontal disponible */
            }
            .btn-group .btn {
                width: 50%; /* Chaque bouton remplit la moiti� de l'espace de la btn-group */
            }
            .btn-custom, .btn-custom-discard {
                white-space: normal; /* Permet au texte du bouton de passer � la ligne */
                word-break: break-word; /* Casse les mots si n�cessaire pour �viter le d�bordement */
            }
        </style>
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
                        <th>Niss</th>
                        <th>Date de Naissance</th>
                        <th>commentaires pr�c�dents</th>
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
                                <a href="#" data-toggle="modal" data-target="#commentModal${patient.numeroNational}">voir les commentaires</a>
                                <div id="commentSection${patient.numeroNational}" class="collapse comment-section">
                                    <ul>
                                        <li>Commentaire 1 pour ${patient.prenom}</li>
                                        <li>Commentaire 2 pour ${patient.prenom}</li>
                                    </ul>
                                </div>
                            </td>

                            <td>
                                <!-- Champ numeroLot �ditable sans bouton de validation suppl�mentaire -->
                                <input type="text" name="commentaire" value="" class="form-control"  />
                            </td>
                            <td>
                                <form action="confirmVaccinationPatientServlet" method="post">
                                    <input type="hidden" name="numeroNational" value="${patient.numeroNational}" />
                                    <input type="hidden" name="numeroLot" id="hiddenNumeroLot${patient.numeroNational}" />
                                    <input type="hidden" name="commentaire" class="form-control" />

                                    <!-- Les boutons pour confirmer ou refuser la pr�sence -->


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
                                    <ul>
                                        <li>Commentaire 1 pour ${patient.prenom}</li>
                                        <li>Commentaire 2 pour ${patient.prenom}</li>
                                    </ul>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
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
            <!-- Inclure les scripts n�cessaires -->
            <!-- jQuery, Popper.js, et Bootstrap JS -->
            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/static/js/search.js"></script>



            <script>
                                        function cancelAction(numeroNational) {
                                            // Logique pour g�rer l'annulation
                                            console.log("Annulation pour le patient avec le num�ro national: " + numeroNational);
                                            // Vous pouvez ajouter ici une redirection ou une autre logique selon le besoin
                                        }
            </script>



    </body>
</html>


