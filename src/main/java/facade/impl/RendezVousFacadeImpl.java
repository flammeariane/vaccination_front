package facade.impl;

import facade.RendezVousFacade;
import bean.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import modele.Patient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import utils.ApiUrls;
import utils.HttpClientSingleton;

public class RendezVousFacadeImpl implements RendezVousFacade {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public static RendezVousFacade INSTANCE = new RendezVousFacadeImpl();

    private RendezVousFacadeImpl() {
    }

    @Override
    public VaccinationHistoryBean getVaccinationHistory(String numeroNational) throws IOException {
        // Création d'une Map pour encapsuler le numeroNational
        Map<String, String> payload = new HashMap<>();
        payload.put("numeroNational", numeroNational);

        // Sérialisation de la Map en JSON
        String requestBody = objectMapper.writeValueAsString(payload);

        HttpPost postRequest = new HttpPost(ApiUrls.RDV_CONSULTATION);
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse httpResponse = HttpClientSingleton.getInstance().execute(postRequest)) {
            if (httpResponse.getStatusLine().getStatusCode() == 200) {

                String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                return objectMapper.readValue(responseBody, VaccinationHistoryBean.class);

            } else {
                // Gérer les réponses autres que 200 OK
                return null;
            }
        }

    }

    @Override
    public CentreInfoBean getCentresNear(Map<String, Object> requestData) throws IOException {
        String requestBody = objectMapper.writeValueAsString(requestData);

        HttpPost postRequest = new HttpPost(ApiUrls.RDV_INFO);
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse httpResponse = HttpClientSingleton.getInstance().execute(postRequest)) {
            if (httpResponse.getStatusLine().getStatusCode() == 200) {

                String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                return objectMapper.readValue(responseBody, CentreInfoBean.class);

            } else {
                // Gérer les réponses autres que 200 OK
                return null;
            }
        }
    }

    @Override
    public ListDateDispoBean getDateByCenter(CentreInfoBeanOut centreOut) throws IOException {
        String requestBody = objectMapper.writeValueAsString(centreOut);

        HttpPost postRequest = new HttpPost(ApiUrls.RDV_AFFICHAGE_AGENDA);
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse httpResponse = HttpClientSingleton.getInstance().execute(postRequest)) {
            if (httpResponse.getStatusLine().getStatusCode() == 200) {

                String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                return objectMapper.readValue(responseBody, ListDateDispoBean.class);

            } else {
                // Gérer les réponses autres que 200 OK
                return null;
            }
        }
    }

    @Override
    public VaccinInfoBean getVaccineList(Patient patient) throws IOException {
        String requestBody = objectMapper.writeValueAsString(patient);

        HttpPost postRequest = new HttpPost(ApiUrls.RDV_LISTE_VACCIN);
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse httpResponse = HttpClientSingleton.getInstance().execute(postRequest)) {
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                return objectMapper.readValue(responseBody, VaccinInfoBean.class);

            } else {
                // Gérer les réponses autres que 200 OK
                return null;
            }
        }
    }

    @Override
    public SaveRendezVousSecondBeanIn saveRendezVousSecond(SaveRendezVousSecondBeanOut rendezVousSecondBeanOut) throws IOException {
        String requestBody = objectMapper.writeValueAsString(rendezVousSecondBeanOut);

        HttpPost postRequest = new HttpPost(ApiUrls.RDV_SAVE_SECOND);
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse httpResponse = HttpClientSingleton.getInstance().execute(postRequest)) {
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                return objectMapper.readValue(responseBody, SaveRendezVousSecondBeanIn.class);

            } else {
                // Gérer les réponses autres que 200 OK
                return null;
            }
        }
    }

    @Override
    public ListDateDispoBean getAgendaSecondRendezVous(SaveRendezVousBeanOut rendezVousBeanOut) throws IOException {
        String requestBody = objectMapper.writeValueAsString(rendezVousBeanOut);

        HttpPost postRequest = new HttpPost(ApiUrls.RDV_SAVE);
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse httpResponse = HttpClientSingleton.getInstance().execute(postRequest)) {
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
          
                    return objectMapper.readValue(responseBody, ListDateDispoBean.class);
                
            } else {
                // Gérer les réponses autres que 200 OK
                return null;
            }
        }
    }

    @Override
    public SaveRendezVousBeanIn saveRendezVous(SaveRendezVousBeanOut rendezVousBeanOut) throws IOException {

        String requestBody = objectMapper.writeValueAsString(rendezVousBeanOut);

        HttpPost postRequest = new HttpPost(ApiUrls.RDV_SAVE);
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse httpResponse = HttpClientSingleton.getInstance().execute(postRequest)) {
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                return objectMapper.readValue(responseBody, SaveRendezVousBeanIn.class);

            } else {
                // Gérer les réponses autres que 200 OK
                return null;
            }
        }
    }

}
