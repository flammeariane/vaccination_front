
package controller;


import bean.saveRendezVousBeanOut;
import bean.SaveRendezVousSecondBeanIn;
import bean.saveRendezVousBeanIn;
import bean.saveRendezVousSecondBeanOut;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.ServletException;
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


public class PriseRdvResumeSecond extends HttpServlet {
    
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
         Patient patient = (Patient) session.getAttribute("patient");
         
  
        saveRendezVousSecondBeanOut rendezVousSecondBeanOut = new saveRendezVousSecondBeanOut();


        rendezVousSecondBeanOut.setNomCentre((String) session.getAttribute("selectedCentreNom"));
        rendezVousSecondBeanOut.setNomVaccin((String) session.getAttribute("selectedVaccinNom"));
        rendezVousSecondBeanOut.setDateRdv("2024-03-01T09:30:00");
        rendezVousSecondBeanOut.setConfirmParEmail("1");
        rendezVousSecondBeanOut.setEmailConfirmation(patient.getEmail());
        rendezVousSecondBeanOut.setStatutConfirmer("oui");
        rendezVousSecondBeanOut.setNumeroNational(patient.getNumeroNational());
        rendezVousSecondBeanOut.setNomFamille(patient.getNomFamille());
        rendezVousSecondBeanOut.setPrenom(patient.getPrenom());
        
 
        SaveRendezVousSecondBeanIn rendezVousResumeSecond = saveRendezVousSecond(rendezVousSecondBeanOut);
        request.setAttribute("rendezVousResumeSecond", rendezVousResumeSecond);

        request.getRequestDispatcher("resume_second.jsp").forward(request, response);

    }
     
     
      private SaveRendezVousSecondBeanIn saveRendezVousSecond(saveRendezVousSecondBeanOut rendezVousSecondBeanOut) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
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
}
