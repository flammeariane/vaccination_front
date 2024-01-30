package controller;

import bean.VaccinationHistoryBean;
import modele.Patient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
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

            // Utilisez l'objet patient pour récupérer les informations nécessaires
            String numeroNational = patient.getNumeroNational();

            VaccinationHistoryBean v = getVaccinationHistory(numeroNational);
            session.setAttribute("history", v);

            request.getRequestDispatcher("dashboard_patient.jsp").forward(request, response);

        } else {
            // Rediriger vers la page de connexion si aucun patient n'est en session
            request.setAttribute("errorMessage", "Informations de login incorrectes ou problème de connexion");
            request.getRequestDispatcher("login-patient.jsp").forward(request, response);
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
}
