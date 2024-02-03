package controller.medicalStaff;

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
import org.apache.http.impl.client.CloseableHttpClient;
import utils.HttpClientSingleton;

public class ConfirmPresencePatientServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MembrePersonnel membrePersonnel = (MembrePersonnel) session.getAttribute("membrePersonnel");
        
        String numeroNational = request.getParameter("numeroNational");
        String validePresence = request.getParameter("validePresence");

        if (numeroNational != null && membrePersonnel != null) {
            Map<String, String> requestData = new HashMap<>();
            
            requestData.put("role", membrePersonnel.getRole());
            requestData.put("adresseMail", membrePersonnel.getAdresseMail());
            requestData.put("password",  membrePersonnel.getPassword());
            requestData.put("numeroNational", numeroNational);
            requestData.put("validePresence", validePresence);
            
            validerPresencePatientListe(requestData);
           
            request.getRequestDispatcher("/accEntreServlet").forward(request, response);

                       
        } else {
            // Gérer le cas où les données nécessaires ne sont pas disponibles
            response.sendRedirect("login.jsp"); // Redirection vers la page de connexion ou une autre page appropriée
        }
    }
    
    private void validerPresencePatientListe(Map<String, String> requestData) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
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
}
