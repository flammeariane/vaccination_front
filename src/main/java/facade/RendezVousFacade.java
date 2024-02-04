package facade;

import bean.CentreInfoBean;
import bean.CentreInfoBeanOut;
import bean.ListDateDispoBean;
import bean.SaveRendezVousSecondBeanIn;
import bean.VaccinInfoBean;
import bean.VaccinationHistoryBean;
import bean.saveRendezVousBeanIn;
import bean.saveRendezVousBeanOut;
import bean.saveRendezVousSecondBeanOut;
import java.io.IOException;
import java.util.Map;
import modele.Patient;

public interface RendezVousFacade {

    VaccinationHistoryBean getVaccinationHistory(String numeroNational) throws IOException;

    CentreInfoBean getCentresNear(Map<String, Object> requestData) throws IOException;

    ListDateDispoBean getDateByCenter(CentreInfoBeanOut centreOut) throws IOException;

    VaccinInfoBean getVaccineList(Patient patient) throws IOException;

    SaveRendezVousSecondBeanIn saveRendezVousSecond(saveRendezVousSecondBeanOut rendezVousSecondBeanOut) throws IOException;

    ListDateDispoBean getAgendaSecondRendezVous(saveRendezVousBeanOut rendezVousBeanOut) throws IOException;

    saveRendezVousBeanIn saveRendezVous(saveRendezVousBeanOut rendezVousBeanOut) throws IOException;

}
