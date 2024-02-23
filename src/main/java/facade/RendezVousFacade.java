package facade;

import bean.CentreInfoBean;
import bean.CentreInfoBeanOut;
import bean.ListDateDispoBean;
import bean.SaveRendezVousSecondBeanIn;
import bean.VaccinInfoBean;
import bean.VaccinationHistoryBean;
import bean.SaveRendezVousBeanIn;
import bean.SaveRendezVousBeanOut;
import bean.SaveRendezVousSecondBeanOut;
import java.io.IOException;
import java.util.Map;
import modele.Patient;

public interface RendezVousFacade {

    VaccinationHistoryBean getVaccinationHistory(String numeroNational) throws IOException;

    CentreInfoBean getCentresNear(Map<String, Object> requestData) throws IOException;

    ListDateDispoBean getDateByCenter(CentreInfoBeanOut centreOut) throws IOException;

    VaccinInfoBean getVaccineList(Patient patient) throws IOException;

    SaveRendezVousSecondBeanIn saveRendezVousSecond(SaveRendezVousSecondBeanOut rendezVousSecondBeanOut) throws IOException;

    ListDateDispoBean saveRdvAndGetAgendaSecondRendezVous(SaveRendezVousBeanOut rendezVousBeanOut) throws IOException;

    SaveRendezVousBeanIn saveRendezVous(SaveRendezVousBeanOut rendezVousBeanOut) throws IOException;

}
