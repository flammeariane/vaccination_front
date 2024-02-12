package facade.impl;

import bean.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import facade.MedicalUserFacade;
import java.io.IOException;
import java.util.Map;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import utils.HttpClientSingleton;

public class MedicalUserFacadeImpl implements MedicalUserFacade {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public PatientListJourBean validerPresencePatientListe(Map<String, Object> requestData) throws IOException {

        String requestBody = objectMapper.writeValueAsString(requestData);

        HttpPost postRequest = new HttpPost("http://localhost:8080/CentreVaccinationFrontEnd/validerPresencePatientListe");
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse httpResponse = HttpClientSingleton.getInstance().execute(postRequest)) {
            if (httpResponse.getStatusLine().getStatusCode() == 200) {

                String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                return objectMapper.readValue(responseBody, PatientListJourBean.class);
            }
        }
        return null;
    }

    public PatientListJourBean validerVaccinationPatientListe(Map<String, Object> requestData) throws IOException {

        String requestBody = objectMapper.writeValueAsString(requestData);

        HttpPost postRequest = new HttpPost("http://localhost:8080/CentreVaccinationFrontEnd/validerVaccinationPatientListe");
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse httpResponse = HttpClientSingleton.getInstance().execute(postRequest)) {
            if (httpResponse.getStatusLine().getStatusCode() == 200) {

                String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                return objectMapper.readValue(responseBody, PatientListJourBean.class);
            }
        }
        return null;
    }

    public void validerPresencePatientUpdateStatut(Map<String, String> requestData) throws IOException {

        String requestBody = objectMapper.writeValueAsString(requestData);

        HttpPost postRequest = new HttpPost("http://localhost:8080/CentreVaccinationFrontEnd/validerPresencePatientUpdateStatut");
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        CloseableHttpClient httpClient = HttpClientSingleton.getInstance();
        try (CloseableHttpResponse httpResponse = httpClient.execute(postRequest)) {
            // Vérifiez le code de statut de la réponse
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                // Si vous avez besoin de traiter la réponse, faites-le ici
                // Comme vous avez mentionné, aucun mappage de réponse n'est nécessaire
            } else {
                // Gérer les cas d'erreur, par exemple, en loggant ou en affichant un message d'erreur
            }
        } catch (IOException e) {
            // Gérer les exceptions
            e.printStackTrace();
        }
    }

    @Override
    public void validerVaccinationPatientUpdateStatut(Map<String, String> requestData) throws IOException {
        String requestBody = objectMapper.writeValueAsString(requestData);

        HttpPost postRequest = new HttpPost("http://localhost:8080/CentreVaccinationFrontEnd/validerVaccinationPatientInsertNumLot");
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        CloseableHttpClient httpClient = HttpClientSingleton.getInstance();
        try (CloseableHttpResponse httpResponse = httpClient.execute(postRequest)) {
            // Vérifiez le code de statut de la réponse
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                // Si vous avez besoin de traiter la réponse, faites-le ici
                // Comme vous avez mentionné, aucun mappage de réponse n'est nécessaire
            } else {
                // Gérer les cas d'erreur, par exemple, en loggant ou en affichant un message d'erreur
            }
        } catch (IOException e) {
            // Gérer les exceptions
            e.printStackTrace();
        }
    }


    public PatientListJourBean incidentSurvenuListPatient(Map<String, Object> requestData) throws IOException {

        String requestBody = objectMapper.writeValueAsString(requestData);

        HttpPost postRequest = new HttpPost("http://localhost:8080/CentreVaccinationFrontEnd/incidentSurvenuListPatient");
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse httpResponse = HttpClientSingleton.getInstance().execute(postRequest)) {
            if (httpResponse.getStatusLine().getStatusCode() == 200) {

                String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                return objectMapper.readValue(responseBody, PatientListJourBean.class);
            }
        }
        return null;
    }

    @Override
    public IncidentBeanIn incidentSurvenuSelectPatient(Map<String, Object> requestData) throws IOException {
         String requestBody = objectMapper.writeValueAsString(requestData);

        HttpPost postRequest = new HttpPost("http://localhost:8080/http://localhost:8080/CentreVaccinationFrontEnd/incidenSurvenuSelectPatient");
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse httpResponse = HttpClientSingleton.getInstance().execute(postRequest)) {
            if (httpResponse.getStatusLine().getStatusCode() == 200) {

                String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                return objectMapper.readValue(responseBody, IncidentBeanIn.class);
            }
        }
        return null;
    }
}
