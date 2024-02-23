package facade.impl;

import facade.RendezVousFacade;
import bean.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import modele.Patient;
import utils.ApiUrls;
import utils.HttpClient;

public class RendezVousFacadeImpl implements RendezVousFacade {


    public static RendezVousFacade INSTANCE = new RendezVousFacadeImpl();

    private RendezVousFacadeImpl() {
    }

    @Override
    public VaccinationHistoryBean getVaccinationHistory(String numeroNational) throws IOException {
        Map<String, String> payload = new HashMap<>();
        payload.put("numeroNational", numeroNational);
        return HttpClient.post(payload, ApiUrls.RDV_CONSULTATION, VaccinationHistoryBean.class);
    }

    @Override
    public CentreInfoBean getCentresNear(Map<String, Object> requestData) throws IOException {
        return HttpClient.post(requestData, ApiUrls.RDV_INFO, CentreInfoBean.class);
    }

    public ListDateDispoBean getDateByCenter(CentreInfoBeanOut centreOut) throws IOException {
        return HttpClient.post(centreOut, ApiUrls.RDV_AFFICHAGE_AGENDA, ListDateDispoBean.class);
    }

    public VaccinInfoBean getVaccineList(Patient patient) throws IOException {
        return HttpClient.post(patient, ApiUrls.RDV_LISTE_VACCIN, VaccinInfoBean.class);
    }

    public SaveRendezVousSecondBeanIn saveRendezVousSecond(SaveRendezVousSecondBeanOut rendezVousSecondBeanOut) throws IOException {
        return HttpClient.post(rendezVousSecondBeanOut, ApiUrls.RDV_SAVE_SECOND, SaveRendezVousSecondBeanIn.class);
    }

    @Override
    public ListDateDispoBean saveRdvAndGetAgendaSecondRendezVous(SaveRendezVousBeanOut rendezVousBeanOut) throws IOException {
        return HttpClient.post(rendezVousBeanOut, ApiUrls.RDV_SAVE, ListDateDispoBean.class);

    }

    @Override
    public SaveRendezVousBeanIn saveRendezVous(SaveRendezVousBeanOut rendezVousBeanOut) throws IOException {
        return HttpClient.post(rendezVousBeanOut, ApiUrls.RDV_SAVE, SaveRendezVousBeanIn.class);

    }

}
