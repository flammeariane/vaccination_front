package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.MembrePersonnel;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import utils.ApiUrls;
import utils.HttpClientSingleton;

@WebServlet(name = "MedicalPersonnelServlet", urlPatterns = {"/MedicalPersonnelServlet"})
public class MedicalPersonnelServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        MembrePersonnel membrePersonnel = (MembrePersonnel) session.getAttribute("membrePersonnel");

    }
    // envoyer le membre de personnel type acceuillant en entre 

    private String getDayliAppointmentList(MembrePersonnel membrePersonnel) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(membrePersonnel);

        HttpPost postRequest = new HttpPost(ApiUrls.VALIDER_VACCINATION_LIST_PATIENT);
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
}
