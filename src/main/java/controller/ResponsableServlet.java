package controller;

import bean.ListCentreBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
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
import utils.ApiUrls;
import utils.HttpClientSingleton;


public class ResponsableServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        HttpSession session = request.getSession();
        MembrePersonnel membrePersonnel = (MembrePersonnel) session.getAttribute("membrePersonnel");
        
         if (membrePersonnel != null) {
            //Map<String, Object> requestData = new HashMap<>();
            //String role = membrePersonnel.getRole();
           // String adresseMail = membrePersonnel.getAdresseMail();
           // String password = membrePersonnel.getPassword();

            //requestData.put("role", role);
            //requestData.put("adresseMail", adresseMail);
           // requestData.put("password", password);

            ListCentreBean centres = getListCentre(membrePersonnel);
            request.setAttribute("centres", centres);

            request.getRequestDispatcher("dashboard_responsable.jsp").forward(request, response);
            
        } else {
            // Gestion de l'erreur
            request.setAttribute("errorMessage", "Problème de connexion ou de récupération des données");
            request.getRequestDispatcher("login_personnel_medical.jsp").forward(request, response);
        }
    }
    
    
     private ListCentreBean getListCentre(MembrePersonnel membrePersonnel ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(membrePersonnel);

        HttpPost postRequest = new HttpPost(ApiUrls.STAT_AFFICHER_CENTRE);
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse httpResponse = HttpClientSingleton.getInstance().execute(postRequest)) {
            if (httpResponse.getStatusLine().getStatusCode() == 200) {

                String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                return objectMapper.readValue(responseBody, ListCentreBean.class);
            }
        }
        return null;
    }
    }
    // USECASE 5: Déterminer les pools de professionnels par centre => responbale general
    // TODO getListCentres
    // TODO getListMenbrePersonnel
    // TODO SavePoolPersonnelcentre

    // Use case 6 : Gérer le planning des pools de professionnels =>responsable de centre
    // TODO getPoolBycenter 
    // TODO getHoraire
    // TODO Save horaire for center
   
    
    // Use case n°7 : Consulter les statistiques par centre => responsable general
    // TODO getListCentres
    // Le système affiche, pour le centre un graphique avec le taux d absence et presence


