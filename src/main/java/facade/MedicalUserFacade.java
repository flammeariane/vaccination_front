
package facade;

import bean.PatientListJourBean;
import java.io.IOException;
import java.util.Map;

public interface MedicalUserFacade {
    
    PatientListJourBean validerPresencePatientListe(Map<String, Object> requestData) throws IOException;
    
    PatientListJourBean validerVaccinationPatientListe(Map<String, Object> requestData) throws IOException;
    
    void validerPresencePatientUpdateStatut(Map<String, String> requestData) throws IOException;
    
}
