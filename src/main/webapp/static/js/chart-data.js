
// Convertir les données JSON en structure nécessaire pour Chart.js
// Graphique pour le taux de présence/absence du personnel par jour
const labels = chartData.map(item => new Date(item.dateRdvSsTime).toLocaleDateString());
const dataPresence = chartData.map(item => item.tauxPresentPersonnel);
const dataAbsence = chartData.map(item => item.tauxAbsentPersonnel);

const graphData = {
    labels: labels,
    datasets: [{
            label: 'Taux de présence du personnel',
            backgroundColor: 'rgb(54, 162, 235)',
            borderColor: 'rgb(54, 162, 235)',
            data: dataPresence,
            fill: false,
        }, {
            label: 'Taux d\'absence du personnel',
            backgroundColor: 'rgb(255, 99, 132)',
            borderColor: 'rgb(255, 99, 132)',
            data: dataAbsence,
            fill: false,
        }]
};

// Création du graphique
const ctx = document.getElementById('personnelPresenceAbsenceChart').getContext('2d');
const personnelPresenceAbsenceChart = new Chart(ctx, {
    type: 'line',
    data: graphData,
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});



// Graphique pour le taux de présence/absence du personnel par mois
const labelsMois = chartDataPersonnelMois.map(item => `${item.month}/${item.year}`);
const dataPresenceMois = chartDataPersonnelMois.map(item => item.tauxPresentPersonnel);
const dataAbsenceMois = chartDataPersonnelMois.map(item => item.tauxAbsentPersonnel);

const graphDataMois = {
    labels: labelsMois,
    datasets: [{
            label: 'Taux de présence du personnel par mois',
            backgroundColor: 'rgb(75, 192, 192)',
            borderColor: 'rgb(75, 192, 192)',
            data: dataPresenceMois,
            fill: false,
        }, {
            label: 'Taux d\'absence du personnel par mois',
            backgroundColor: 'rgb(255, 159, 64)',
            borderColor: 'rgb(255, 159, 64)',
            data: dataAbsenceMois,
            fill: false,
        }]
};

const ctxMois = document.getElementById('personnelPresenceAbsenceMonthChart').getContext('2d');
const personnelPresenceAbsenceMonthChart = new Chart(ctxMois, {
    type: 'line',
    data: graphDataMois,
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});



// Graphique pour le taux de remplissage par jour
const labelsTauxRemplissageJour = chartDataTauxRemplissageJour.map(item => new Date(item.dateJour).toLocaleDateString());
const dataTauxRemplissage = chartDataTauxRemplissageJour.map(item => item.tauxRemplissage);

const graphDataTauxRemplissageJour = {
    labels: labelsTauxRemplissageJour,
    datasets: [{
        label: 'Taux de remplissage par jour',
        backgroundColor: 'rgb(153, 102, 255)',
        borderColor: 'rgb(153, 102, 255)',
        data: dataTauxRemplissage,
        fill: false,
    }]
};

const ctxTauxRemplissageJour = document.getElementById('tauxRemplissageJourChart').getContext('2d');
const tauxRemplissageJourChart = new Chart(ctxTauxRemplissageJour, {
    type: 'line',
    data: graphDataTauxRemplissageJour,
    options: {
        scales: {
            y: {
                beginAtZero: true,
                suggestedMax: 100 
            }
        }
    }
});



// Graphique pour le taux de présence/absence des patients par jour
const labelsTxPresAbsePatienJour = chartDataTxPresAbsePatienJour.map(item => new Date(item.dateRdvSsTime).toLocaleDateString());
const dataTauxPresentPatient = chartDataTxPresAbsePatienJour.map(item => item.tauxPresentPatient);
const dataTauxAbsentPatient = chartDataTxPresAbsePatienJour.map(item => item.tauxAbsentPatient);

const graphDataTxPresAbsePatienJour = {
    labels: labelsTxPresAbsePatienJour,
    datasets: [{
            label: 'Taux de présence des patients',
            backgroundColor: 'rgb(102, 205, 170)', 
            borderColor: 'rgb(102, 205, 170)', 
            data: dataTauxPresentPatient,
            fill: false,
        }, {
            label: 'Taux d\'absence des patients',
            backgroundColor: 'rgb(250, 128, 114)', 
            borderColor: 'rgb(250, 128, 114)', 
            data: dataTauxAbsentPatient,
            fill: false,
        }]
};

const ctxTxPresAbsePatienJour = document.getElementById('txPresAbsePatienJourChart').getContext('2d');
const txPresAbsePatienJourChart = new Chart(ctxTxPresAbsePatienJour, {
    type: 'line', 
    data: graphDataTxPresAbsePatienJour,
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});


S