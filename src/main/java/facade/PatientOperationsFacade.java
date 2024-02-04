package facade;

import java.io.IOException;
import modele.Patient;

public interface PatientOperationsFacade {
    Patient loginPatient(String numeroNational, String codeSecret) throws IOException;
    
}