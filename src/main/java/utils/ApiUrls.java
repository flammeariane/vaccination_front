package utils;

public class ApiUrls {

    public static final String BASE_URL = "http://localhost:8080/CentreVaccinationFrontEnd";
    // login 
    public static final String LOGIN_PERSONNEL = BASE_URL + "/loginPersonnel";
    public static final String LOGIN_PATIENT = BASE_URL + "/login";
    // PatientServlet
    public static final String RDV_INFO = BASE_URL + "/rendezVousInfoGeneral";
    public static final String RDV_LISTE_VACCIN = BASE_URL + "/rendezVousListeVaccin";
    public static final String RDV_CONSULTATION = BASE_URL + "/consultationRdv";
    public static final String RDV_AFFICHAGE_AGENDA = BASE_URL + "/rendezVousAffichageAgenda";
     public static final String RDV_AFFICHAGE_AGENDA_2 = BASE_URL + "/rendezVousAffichageAgenda2";
    public static final String RDV_SAVE = BASE_URL + "/rendezVousInsertRendezVous";
    public static final String RDV_SAVE_SECOND = BASE_URL + "/rendezVousInsertDeuxiemerendezVous";
   

    // Responsable general
    public static final String STAT_AFFICHER_CENTRE = BASE_URL + "/consulterStatistiqueAfficherCentre";
    public static final String STAT_CENTRE = BASE_URL + "/consulterStatistiqueChoisirCentre";

    // Responsable de centre
    public static final String LIST_MEMBERS = BASE_URL + "/gererPlanningListMembre";
    public static final String AFFICHER_AGENDA = BASE_URL + "/gererPlanningAfficherAgenda";
    public static final String CONFIRM_PLANNING = BASE_URL + "/gererPlanningConfirmInsert";
    public static final String CONSULTER_SON_HORAIRE = BASE_URL + "/gererPlanningConsulterSonHoraire";

    // MedicalPersonnelServlet
    public static final String VALIDER_PRESENCE_LIST_PATIENT = BASE_URL + "/validerPresencePatientListe";
    public static final String VALIDER_VACCIN_LIST_PATIENT = BASE_URL + "/validerVaccinationPatientListe";
    public static final String VALIDER_PRESENCE_PATIENT_UPDATE_STATUT = BASE_URL + "/validerPresencePatientUpdateStatut";
    public static final String VALIDER_VACCINATION_INSERT_LOT = BASE_URL + "/validerVaccinationPatientInsertNumLot";
    public static final String INCIDENT_SURVENU_LIST_PATIENT = BASE_URL + "/incidentSurvenuListPatient";
    public static final String INCIDENT_SURVENU_SELECT_PATIENT = BASE_URL + "/incidenSurvenuSelectPatient";

}
