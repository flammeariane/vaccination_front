package controller;

import bean.CentreInfoBean;
import bean.VaccinInfoBean;
import bean.VaccinationHistoryBean;
import modele.Patient;
import modele.CentreVaccination;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import org.apache.http.util.EntityUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import utils.ApiUrls;
import utils.HttpClientSingleton;


public class PatientServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Patient patient = (Patient) session.getAttribute("patient");

// Informations nécessaires pour l'appel API
        if (patient != null) {
            Map<String, Object> requestData = new HashMap<>();
            // Utilisez l'objet patient pour récupérer les informations nécessaires
            String numeroNational = patient.getNumeroNational();

            int codePostal = patient.getCodePostal();
            String prenom = patient.getPrenom();
            String nomFamille = patient.getNomFamille();
            String codeSecret = patient.getCodeSecret(); // TODO CHECK SECURITE

            // Créez un objet pour les données de requête

            requestData.put("numeroNational", numeroNational);
            requestData.put("codePostal", codePostal);
            requestData.put("prenom", prenom);
            requestData.put("nomFamille", nomFamille);
            requestData.put("codeSecret", codeSecret);

            VaccinInfoBean vaccins = getVaccineList(patient);
            request.setAttribute("vaccins", vaccins);

            CentreInfoBean centres = getCentresNear(requestData);
            request.setAttribute("centres", centres);

            VaccinationHistoryBean v = getVaccinationHistory(numeroNational);
            request.setAttribute("history", v);

            request.getRequestDispatcher("dashboard_patient.jsp").forward(request, response);


        } else {
            // Rediriger vers la page de connexion si aucun patient n'est en session
            request.setAttribute("errorMessage", "Informations de login incorrectes ou problème de connexion");
            request.getRequestDispatcher("login-patient.jsp").forward(request, response);


        }

    }

    private CentreInfoBean getCentresNear(Map<String, Object> requestData) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
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

    private VaccinInfoBean getVaccineList(Patient patient) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(patient);

        HttpPost postRequest = new HttpPost(ApiUrls.RDV_LISTE_VACCIN);
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse httpResponse = HttpClientSingleton.getInstance().execute(postRequest)) {
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            System.out.println("Response Status: " + statusCode);

            if (statusCode == 200) {
                String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                System.out.println("Response Body: " + responseBody);
                return objectMapper.readValue(responseBody, VaccinInfoBean.class);

            } else {
                // Gérer les réponses autres que 200 OK
                return null;
            }
        }
    }

    private VaccinationHistoryBean getVaccinationHistory(String numeroNational) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

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

    private String getAvailableAppointments(CentreVaccination centreVaccination) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(centreVaccination);

        HttpPost postRequest = new HttpPost(ApiUrls.RDV_AFFICHAGE_AGENDA);
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse httpResponse = HttpClientSingleton.getInstance().execute(postRequest)) {
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            } else {
                // Gérer les réponses autres que 200 OK
                return "[]";
            }
        }
    }

    public void savedAppointment(Map<String, String> formData) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost postRequest = new HttpPost(ApiUrls.RDV_SAVE);

            // Convertir les données du formulaire en JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(formData);

            // Définir le corps de la requête
            postRequest.setEntity(new StringEntity(json, "UTF-8"));
            postRequest.setHeader("Content-Type", "application/json");

            // Exécuter la requête
            HttpResponse response = client.execute(postRequest);
            String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");

            // Traiter la réponse ici
            System.out.println(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
