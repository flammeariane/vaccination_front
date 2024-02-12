package controller.medicalStaff;

import bean.PatientListJourBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.MembrePersonnel;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import utils.HttpClientSingleton;

public class MedicalPersonnelServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        MembrePersonnel membrePersonnel = (MembrePersonnel) session.getAttribute("membrePersonnel");
       
       
        if (membrePersonnel != null) {
            Map<String, Object> requestData = new HashMap<>();
            String role = membrePersonnel.getRole();
            String adresseMail = membrePersonnel.getAdresseMail();
            String password = membrePersonnel.getPassword();

            requestData.put("role", role);
            requestData.put("adresseMail", adresseMail);
            requestData.put("password", password);

            PatientListJourBean patientList = validerPresencePatientListe(requestData);
            request.setAttribute("patientList", patientList);

            request.getRequestDispatcher("dashboard_personnel_medical.jsp").forward(request, response);
            
        } else {
            // Gestion de l'erreur
            request.setAttribute("errorMessage", "Problème de connexion ou de récupération des données");
            request.getRequestDispatcher("login_personnel_medical.jsp").forward(request, response);
        }
    }

    private PatientListJourBean validerPresencePatientListe(Map<String, Object> requestData) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
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
}

// Use case n°2 : Valider la présence du patient
// TODO insert numero NISS 
// Confirm presence patient
// Use case n°3 : Valider la vaccination du patient
// TODO insert numero de lot 
// TODO save confirm vaccination 
// Use case n°4 : Indiquer les incidents survenus chez le patient
// get list rdv confirmer du jours
// TODO get commentaire
// TODO insert commentaire 

