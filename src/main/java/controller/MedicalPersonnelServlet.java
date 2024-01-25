package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.MembrePersonnel;
import modele.PatientsListResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import utils.ApiUrls;
import utils.HttpClientSingleton;


public class MedicalPersonnelServlet extends HttpServlet {

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération des paramètres depuis le formulaire
        String role = request.getParameter("role");
        String adresseMail = request.getParameter("adresseMail");
        String password = request.getParameter("password");

        // Appel de la fonction pour envoyer la requête et obtenir la réponse
        PatientsListResponse patientsListResponse = validerPresencePatientListe(role, adresseMail, password);
        

        if (patientsListResponse != null) {
            // Stockage de la liste des patients dans la session
            HttpSession session = request.getSession();
            session.setAttribute("listPatient", patientsListResponse.getListPatient());
            RequestDispatcher disp = request.getRequestDispatcher("dashboard_personnel_medical.jsp");
                    disp.forward(request, response);
            response.sendRedirect("dashboard_personnel_medical.jsp");
        } else {
            // Gestion de l'erreur
            request.setAttribute("errorMessage", "Problème de connexion ou de récupération des données");
            request.getRequestDispatcher("login_personnel_medical.jsp").forward(request, response);
        }
    }

    private PatientsListResponse validerPresencePatientListe(String role, String adresseMail, String password) throws IOException {
        Map<String, String> personnelData = new HashMap<>();
        personnelData.put("role", role);
        personnelData.put("adresseMail", adresseMail);
        personnelData.put("password", password);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(personnelData);

        HttpPost postRequest = new HttpPost("http://localhost:8080/CentreVaccinationFrontEnd/validerPresencePatientListe");
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse httpResponse = HttpClientSingleton.getInstance().execute(postRequest)) {
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                return objectMapper.readValue(responseBody, PatientsListResponse.class);
            }
        }
        return null;
    }
    }

    // Use case n°2 : Valider la présence du patient
    // TODO insert numero NISS 
    // TODO Confirm presence patient
    // Use case n°3 : Valider la vaccination du patient
    // TODO insert numero de lot 
    // TODO save confirm vaccination 
    // Use case n°4 : Indiquer les incidents survenus chez le patient
    // get list rdv confirmer du jours
    // TODO get commentaire
    // TODO insert commentaire 

