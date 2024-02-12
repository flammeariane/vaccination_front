package facade;

import bean.IncidentBeanIn;
import bean.PatientListJourBean;
import java.io.IOException;
import java.util.Map;

public interface MedicalUserFacade {

    PatientListJourBean validerPresencePatientListe(Map<String, Object> requestData) throws IOException;

    PatientListJourBean validerVaccinationPatientListe(Map<String, Object> requestData) throws IOException;

    void validerPresencePatientUpdateStatut(Map<String, String> requestData) throws IOException;

    void validerVaccinationPatientUpdateStatut(Map<String, String> requestData) throws IOException;

    IncidentBeanIn incidentSurvenuSelectPatient(Map<String, Object> requestData) throws IOException;

    PatientListJourBean incidentSurvenuListPatient(Map<String, Object> requestData) throws IOException;

}
