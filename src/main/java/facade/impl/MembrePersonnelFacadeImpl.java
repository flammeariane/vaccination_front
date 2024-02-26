package facade.impl;

import bean.AgendaBeanIn;
import bean.AgendaPlanningInfoBeanOut;
import bean.ListCentreBean;
import bean.ListMembreBean;
import bean.ListMembrePersoHoraireBeanIn;
import bean.ListMembreSonHoraireBeanIn;
import bean.PlanningBeanOut;
import bean.StatCentreBeanIn;
import bean.StatCentreBeanOut;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import facade.MembrePersonnelFacade;

import java.io.IOException;

import modele.MembrePersonnel;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import utils.ApiUrls;
import utils.HttpClient;
import utils.HttpClientSingleton;

public class MembrePersonnelFacadeImpl implements MembrePersonnelFacade {

    private MembrePersonnelFacadeImpl() {
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    public static MembrePersonnelFacade INSTANCE = new MembrePersonnelFacadeImpl();

    public ListCentreBean getListCentre(MembrePersonnel membrePersonnel) throws IOException {
        return HttpClient.post(membrePersonnel, ApiUrls.STAT_AFFICHER_CENTRE, ListCentreBean.class);
    }

    public StatCentreBeanIn getStatCentre(StatCentreBeanOut statCentreBeanOut) throws IOException {
        return HttpClient.post(statCentreBeanOut, ApiUrls.STAT_CENTRE, StatCentreBeanIn.class);
    }

    public ListMembreBean getListMembre(MembrePersonnel membrePersonnel) throws IOException {
        return HttpClient.post(membrePersonnel, ApiUrls.LIST_MEMBERS, ListMembreBean.class);
    }

    public AgendaBeanIn getAgenda(AgendaPlanningInfoBeanOut agendaPlanningInfoBeanOut) throws IOException {
        return HttpClient.post(agendaPlanningInfoBeanOut, ApiUrls.AFFICHER_AGENDA, AgendaBeanIn.class);
    }

    public ListMembrePersoHoraireBeanIn confirmPlanning(PlanningBeanOut planningBeanOut) throws IOException {
        return HttpClient.post(planningBeanOut, ApiUrls.CONFIRM_PLANNING, ListMembrePersoHoraireBeanIn.class);
    }

    public ListMembreSonHoraireBeanIn consulterSonHoraire(MembrePersonnel membrePersonnel) throws IOException {
        return HttpClient.post(membrePersonnel, ApiUrls.CONSULTER_SON_HORAIRE, ListMembreSonHoraireBeanIn.class);
    }

}
