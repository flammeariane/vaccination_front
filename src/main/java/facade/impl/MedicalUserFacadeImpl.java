package facade.impl;

import bean.*;
import facade.MedicalUserFacade;
import java.io.IOException;
import java.util.Map;
import utils.ApiUrls;
import utils.HttpClient;

public class MedicalUserFacadeImpl implements MedicalUserFacade {



    public static MedicalUserFacade INSTANCE = new MedicalUserFacadeImpl();

    private MedicalUserFacadeImpl() {
    }

    public PatientListJourBean validerPresencePatientListe(Map<String, Object> requestData) throws IOException {
        return HttpClient.post(requestData, ApiUrls.VALIDER_PRESENCE_LIST_PATIENT, PatientListJourBean.class);
    }

    public PatientListJourBean validerVaccinationPatientListe(Map<String, Object> requestData) throws IOException {
        return HttpClient.post(requestData, ApiUrls.VALIDER_VACCIN_LIST_PATIENT, PatientListJourBean.class);
    }

    public void validerPresencePatientUpdateStatut(Map<String, String> requestData) throws IOException {
        HttpClient.post(requestData, ApiUrls.VALIDER_PRESENCE_PATIENT_UPDATE_STATUT, Void.class);
    }

    public void validerVaccinationPatientUpdateStatut(Map<String, String> requestData) throws IOException {
        HttpClient.post(requestData, ApiUrls.VALIDER_VACCINATION_INSERT_LOT, Void.class);
    }

    public PatientListJourBean incidentSurvenuListPatient(Map<String, Object> requestData) throws IOException {
        return HttpClient.post(requestData, ApiUrls.INCIDENT_SURVENU_LIST_PATIENT, PatientListJourBean.class);
    }

    public IncidentBeanIn incidentSurvenuSelectPatient(Map<String, Object> requestData) throws IOException {
        return HttpClient.post(requestData, ApiUrls.INCIDENT_SURVENU_SELECT_PATIENT, IncidentBeanIn.class);
    }

}
