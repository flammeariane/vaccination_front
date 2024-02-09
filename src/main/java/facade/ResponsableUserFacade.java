
package facade;

import bean.ListCentreBean;
import java.io.IOException;
import modele.MembrePersonnel;


public interface ResponsableUserFacade {
    
    ListCentreBean getListCentre(MembrePersonnel membrePersonnel ) throws IOException;
    
}
