package controller;

import bean.VaccinInfoBean;
import bean.saveRendezVousBeanIn;
import bean.saveRendezVousBeanOut;
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

public class PriseRdvResume extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Patient patient = (Patient) session.getAttribute("patient");
        saveRendezVousBeanOut rendezVousBeanOut = new saveRendezVousBeanOut();

        // rendezVousBeanOut.setNomCentre(session.getAttribute(string));
        rendezVousBeanOut.setNomCentre((String) session.getAttribute("selectedCentreNom"));
        rendezVousBeanOut.setNomVaccin((String) session.getAttribute("selectedVaccinNom"));
        rendezVousBeanOut.setDateRdv((String) request.getAttribute("selectedDate"));
        rendezVousBeanOut.setConfirmParEmail("1");
        rendezVousBeanOut.setEmailConfirmation(patient.getEmail());
        rendezVousBeanOut.setStatutConfirmer("oui");
        rendezVousBeanOut.setNumeroNational(patient.getNumeroNational());
        rendezVousBeanOut.setNomFamille(patient.getNomFamille());
        rendezVousBeanOut.setPrenom(patient.getNomFamille());

        saveRendezVousBeanIn rendezVousResume = saveRendezVous(rendezVousBeanOut);
        session.setAttribute("rendezVousResume", rendezVousResume);

        request.getRequestDispatcher("resume.jsp").forward(request, response);

    }

    private saveRendezVousBeanIn saveRendezVous(saveRendezVousBeanOut rendezVousBeanOut) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(rendezVousBeanOut);

        HttpPost postRequest = new HttpPost(ApiUrls.RDV_SAVE);
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse httpResponse = HttpClientSingleton.getInstance().execute(postRequest)) {
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                return objectMapper.readValue(responseBody, saveRendezVousBeanIn.class);

            } else {
                // Gérer les réponses autres que 200 OK
                return null;
            }
        }
    }
}
