package controller;

import bean.ListDateDispoBean;
import bean.saveRendezVousBeanIn;
import bean.saveRendezVousBeanOut;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import utils.ApiUrls;
import utils.HttpClientSingleton;

public class PriseRdvChoixDateSecond extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        saveRendezVousBeanOut rendezVousBeanOut = new saveRendezVousBeanOut();

        // rendezVousBeanOut.setNomCentre(session.getAttribute(string));
        rendezVousBeanOut.setNomCentre("Family Care");
        rendezVousBeanOut.setNomVaccin("Pfizer");
        rendezVousBeanOut.setDateRdv("2024-02-01T09:30:00");
        rendezVousBeanOut.setConfirmParEmail("1");
        rendezVousBeanOut.setEmailConfirmation("JeuneKoenig@dayrep.com");
        rendezVousBeanOut.setStatutConfirmer("oui");
        rendezVousBeanOut.setNumeroNational("00022621447");
        rendezVousBeanOut.setNomFamille("Koenig");
        rendezVousBeanOut.setPrenom("Jeune");

        ListDateDispoBean secondRendezVous = getAgendaSecondRendezVous(rendezVousBeanOut);
        request.setAttribute("secondRendezVous", secondRendezVous);
        request.getRequestDispatcher("choix_date_second.jsp").forward(request, response);

    }

    private ListDateDispoBean getAgendaSecondRendezVous(saveRendezVousBeanOut rendezVousBeanOut) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(rendezVousBeanOut);

        HttpPost postRequest = new HttpPost(ApiUrls.RDV_SAVE);
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse httpResponse = HttpClientSingleton.getInstance().execute(postRequest)) {
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                return objectMapper.readValue(responseBody, ListDateDispoBean.class);

            } else {
                // Gérer les réponses autres que 200 OK
                return null;
            }
        }
    }

}
