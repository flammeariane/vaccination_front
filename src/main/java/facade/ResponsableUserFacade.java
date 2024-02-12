package facade;

import bean.ListCentreBean;
import bean.StatCentreBeanIn;
import bean.StatCentreBeanOut;
import java.io.IOException;
import modele.MembrePersonnel;

public interface ResponsableUserFacade {

    ListCentreBean getListCentre(MembrePersonnel membrePersonnel) throws IOException;

    StatCentreBeanIn getStatCentre(StatCentreBeanOut statCentreBeanIn) throws IOException;

}
