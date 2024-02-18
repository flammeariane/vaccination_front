
$(document).ready(function () {

    function checkDatePickers() {
        var startDate = $('#datetimepicker1 input').val();
        var endDate = $('#datetimepicker2 input').val();

        // Si les deux champs de date sont remplis, activer le bouton
        if (startDate !== "" && endDate !== "") {
            $('#saveButton').prop('disabled', false);
        } else {
            $('#saveButton').prop('disabled', true);
        }
    }

    $('#datetimepicker1 input, #datetimepicker2 input').on('change.datetimepicker', function () {
        checkDatePickers();
    });

    // Appel initial au cas où les valeurs sont pré-remplies (utile si vous avez une logique de pré-remplissage)
    checkDatePickers();

    $('#datetimepicker1').datetimepicker({
        format: 'YYYY-MM-DD HH:mm',
    });

    $('#datetimepicker1').on("change.datetimepicker", function (e) {
        $('#datetimepicker2').datetimepicker('minDate', e.date);
    });

    $('#datetimepicker2').datetimepicker({
        format: 'YYYY-MM-DD HH:mm',
    });

    // Intercepter la soumission du formulaire
    $("#horaireForm").on("submit", function (e) {
        e.preventDefault(); // Empêcher la soumission normale du formulaire

        // Construire l'objet JSON
        var dataToSend = {
            listDateStartEndMembre: [{
                    dateStart: [$("#datetimepicker1 input").val()],
                    dateEnd: [$("#datetimepicker2 input").val()]
                }],
            roleCentre: "Responsable de centre",
            listMembrePersonnelResp: [{
                    // Remplacer ces valeurs par les entrées de formulaire réelles si nécessaire
                    nomFamille: "Romain",
                    prenom: "Alexandre",
                    role: "Responsable de centre",
                    localite: 1000,
                    numTelephone: "0487 99 98 14"
                }]
        };

        // Envoyer les données au serveur
        $.ajax({
            url: "http://localhost:8080/CentreVaccinationFrontEnd/gererPlanningChoixHoraire", 
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(dataToSend),
            success: function (response) {
                console.log("Réponse reçue: ", response);
                var message = "<strong>Vous avez bien ajouté l'horaire suivant :</strong><br>";

                $.each(response.mapMembreHoraire, function (nomMembre, horaires) {
                    $.each(horaires, function (index, horaire) {
                        var startHoraire = moment(horaire.startHoraire).format('YYYY-MM-DD HH:mm:ss');
                        var endHoraire = moment(horaire.endHoraire).format('YYYY-MM-DD HH:mm:ss');

                        message += "Nom: " + nomMembre + "<br>" +
                                "Heure de début: " + startHoraire + "<br>" +
                                "Heure de fin: " + endHoraire + "<br><br>";
                    });
                });

                // Mettre à jour le conteneur de réponse avec le style désiré et l'afficher
                $("#responseContainer").html(message).show();

                // Fermer le modal si nécessaire
                $("#horaireModal").modal('hide');
            },
            error: function (xhr, status, error) {
                // Gérer les erreurs ici
                console.error("Erreur lors de l'envoi: ", error);
            }
        });
    });
});
     