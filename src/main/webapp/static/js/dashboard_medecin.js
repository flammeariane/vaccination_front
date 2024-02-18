import { apiConfig } from './ajaxApiConfig.js';

window.ouvrirModalIncident = function(numeroNational)  {
    $.ajax({
       url: apiConfig.baseUrl + apiConfig.endpoints.incidenSurvenuSelectPatient,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            numeroNational: numeroNational,
            adresseMail: "medecin@gmail.com",
            password: "81dc9bdb52d04dc20036dbd8313ed055",
            role: "Medecin"
        }),
        dataType: 'json',
        success: function (response) {
            var contenuModal = '';
             if (response && response.listIncident && response.listIncident.length > 0) {
                   var commentaires = response.listIncident;
                for (var i = 0; i < commentaires.length; i++) {
                    contenuModal += '<li>' +
                            'Date Rdv: ' + new Date(commentaires[i].dateRdv).toLocaleString() + '<br>' +
                            'Numéro Dose: ' + commentaires[i].numeroDose + '<br>' +
                            'Vaccin: ' + commentaires[i].nomVaccin + '<br>' +
                            'Remarques: ' + commentaires[i].remarques + '<br>' +
                            '</li>';
                }
            } else {
                contenuModal = "<li>Aucun incident trouvé </li>";
            }

            // Mettre à jour le contenu de la modal et l'afficher
            $('#commentModal' + numeroNational + ' .modal-body').html('<ul>' + contenuModal + '</ul>');
            $('#commentModal' + numeroNational).modal('show');
        },
        error: function (xhr, status, error) {
            console.error("Erreur lors de la récupération des incidents: ", status, error);
        }
    });
}
     