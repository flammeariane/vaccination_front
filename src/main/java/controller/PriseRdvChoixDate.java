package controller;

import bean.CentreInfoBeanOut;
import bean.ListDateDispoBean;
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

public class PriseRdvChoixDate extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Patient patient = (Patient) session.getAttribute("patient");
      
        // Récupérez l'objet centreOut de la session
        CentreInfoBeanOut centreOut = (CentreInfoBeanOut) request.getSession().getAttribute("selectedCentreOut");


        // Récupérer l'index du centre sélectionné depuis la requête
        String selectedVaccinIndex = request.getParameter("selectedVaccin");

        if (selectedVaccinIndex != null) {
            int index = Integer.parseInt(selectedVaccinIndex);

            String selectedVaccinNom = request.getParameter("selectedVaccinNom_" + index);
            String selectedVaccinNbrDoseTotal = request.getParameter("selectedVaccinNbrDoseTotal_" + index);
            String selectedVaccinDureeEntreDose = request.getParameter("selectedVaccinDureeEntreDose_" + index);

            // Stockez les valeurs dans la session 
            session.setAttribute("selectedVaccinNom", selectedVaccinNom);
            session.setAttribute("selectedVaccinNbrDoseTotal", selectedVaccinNbrDoseTotal);
            session.setAttribute("selectedVaccinDureeEntreDose", selectedVaccinDureeEntreDose);

            ListDateDispoBean listInfoAgenda = getDateByCenter(centreOut);
            request.setAttribute("listInfoAgenda", listInfoAgenda);

            request.getRequestDispatcher("choix_date.jsp").forward(request, response);

        } else {
            // Rediriger vers la page de connexion si aucun patient n'est en session
            request.setAttribute("errorMessage", "Informations de login incorrectes ou problème de connexion");
            request.getRequestDispatcher("login-patient.jsp").forward(request, response);

        }
    }

    private ListDateDispoBean getDateByCenter(CentreInfoBeanOut centreOut) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(centreOut);

        HttpPost postRequest = new HttpPost(ApiUrls.RDV_AFFICHAGE_AGENDA);
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse httpResponse = HttpClientSingleton.getInstance().execute(postRequest)) {
            if (httpResponse.getStatusLine().getStatusCode() == 200) {

                String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                return objectMapper.readValue(responseBody, ListDateDispoBean.class);

            } else {
                // Gérer les réponses autres que 200 OK
                return null;
            }
        }
    }

}
