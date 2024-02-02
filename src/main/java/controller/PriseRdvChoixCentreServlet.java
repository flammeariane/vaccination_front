package controller;

import bean.CentreInfoBean;
import bean.VaccinInfoBean;
import bean.VaccinationHistoryBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Patient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import utils.ApiUrls;
import utils.HttpClientSingleton;



public class PriseRdvChoixCentreServlet extends HttpServlet {
    
    
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


            CentreInfoBean centres = getCentresNear(requestData);
            request.setAttribute("centres", centres);

            request.getRequestDispatcher("choix_centre.jsp").forward(request, response);

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

        
        

}
