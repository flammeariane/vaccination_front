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
    public static final String RDV_SAVE = BASE_URL+"/rendezVousInsertRendezVous";
    public static final String RDV_SAVE_SECOND = BASE_URL+"/rendezVousInsertDeuxiemerendezVous";

   // public static final String RDV_CHOIX_VACCIN = BASE_URL + "/rendezVousChoixVaccin";
    

    // ResponsableServlet
    public static final String STAT_AFFICHER_CENTRE = BASE_URL + "/consulterStatistiqueAfficherCentre";
    // MedicalPersonnelServlet
    public static final String VALIDER_PRESENCE_LIST_PATIENT = BASE_URL + "/validerPresencePatientListe";
   
}
