package facade;

import bean.AgendaBeanIn;
import bean.AgendaPlanningInfoBeanOut;
import bean.ListCentreBean;
import bean.ListMembreBean;
import bean.ListMembrePersoHoraireBeanIn;
import bean.ListMembreSonHoraireBeanIn;
import bean.PlanningBeanOut;
import bean.StatCentreBeanIn;
import bean.StatCentreBeanOut;
import java.io.IOException;
import modele.MembrePersonnel;

public interface MembrePersonnelFacade {

    ListCentreBean getListCentre(MembrePersonnel membrePersonnel) throws IOException;

    StatCentreBeanIn getStatCentre(StatCentreBeanOut statCentreBeanIn) throws IOException;

    ListMembreBean getListMembre(MembrePersonnel membrePersonnel) throws IOException;

    AgendaBeanIn getAgenda(AgendaPlanningInfoBeanOut agendaPlanningInfoBeanOut) throws IOException;

    ListMembrePersoHoraireBeanIn confirmPlanning(PlanningBeanOut planningBeanOut) throws IOException;

    ListMembreSonHoraireBeanIn consulterSonHoraire(MembrePersonnel membrePersonnel) throws IOException;

}
