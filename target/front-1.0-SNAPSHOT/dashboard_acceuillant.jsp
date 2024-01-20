<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tableau de Bord de l'Accueillant</title>
        <%@ include file="header.jsp" %>
        <style>
            .control-buttons {
                display: flex;
                gap: 10px;
            }
            .edit-icon {
                cursor: pointer;
                color: #007bff; 
            }
        </style>
    </head>
    <body>
        <h3 class="text-center mt-5">Bienvenue <b>${username} </b>dans le tableau de bord de l'accueillant!</h3>

        <div class="container mt-4">
            <table class="table mt-4">
                <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Registre national</th>
                        <th>Date de Rendez-vous</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="patient" items="${patients}">
                        <tr>
                            <td>${patient.nom}</td>
                            <td>${patient.prenom}</td>
                            <td>
                                ${patient.numRegNat}
                                <c:if test="${patient.numRegNat == null || patient.numRegNat == ''}">
                                    <i class="fas fa-edit edit-icon" onclick="openEditModal('${patient.numRegNat}')"></i>
                                </c:if>

                            </td>
                            <td>${patient.heureRendezVous}</td>
                            <td class="control-buttons">
                                <button type="button" class="btn btn-outline-success confirm-presence-btn" data-toggle="modal" data-target="#confirmModal" data-patientid="${patient.numRegNat}">Confirmer la présence</button>
                                <form method="post" action="cancelAppointment">
                                    <input type="hidden" name="patientId" value="${patient.numRegNat}" />
                                    <button type="submit" class="btn btn-outline-danger">Annuler</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <%@ include file="footer.jsp" %>
<!-- Modale pour éditer le numéro de Registre National -->
<div class="modal fade" id="editNumRegNatModal" tabindex="-1" role="dialog" aria-labelledby="editNumRegNatModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editNumRegNatModalLabel">Modifier le numéro de registre national</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Fermer">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="editNumRegNatForm">
                    <div class="form-group">
                        <label for="numRegNatInput">Numéro de registre national</label>
                        <input type="text" class="form-control" id="numRegNatInput" placeholder="Entrez le numéro de registre national">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                <button type="submit" class="btn btn-primary" onclick="saveChanges()">Sauvegarder</button>
            </div>
        </div>
    </div>
</div>



        <!-- Modale de confirmation -->
        <div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="confirmModalLabel">Confirmer la présence</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Êtes-vous sûr de vouloir confirmer la présence de ce patient ?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                        <button type="button" class="btn btn-primary" id="confirmPresenceBtn">Confirmer</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- jQuery, Popper.js, et Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

        <script>
                                        function openEditModal(numRegNat) {
                                            var inputField = document.getElementById('numRegNatInput');
                                            inputField.value = numRegNat ? numRegNat : '';
                                            $('#editNumRegNatModal').modal('show');
                                        }


                                        function saveChanges() {
                                            // Logique pour sauvegarder les modifications
                                            $('#editNumRegNatModal').modal('hide');
                                        }

                                        function setupConfirmPresenceModal() {
                                            var patientId;
                                            var confirmButton;

                                            $('#confirmModal').on('show.bs.modal', function (event) {
                                                confirmButton = $(event.relatedTarget);
                                                patientId = confirmButton.data('patientid');
                                            });

                                            $('#confirmPresenceBtn').click(function (e) {
                                                e.preventDefault();
                                                $.post("confirmPresence", {patientId: patientId}, function (data) {
                                                    confirmButton.prop('disabled', true).addClass('btn-secondary').removeClass('btn-outline-success');
                                                });
                                                $('#confirmModal').modal('hide');
                                            });
                                        }

                                        $(document).ready(function () {
                                            setupConfirmPresenceModal();
                                        });
        </script>
    </body>
</html>
