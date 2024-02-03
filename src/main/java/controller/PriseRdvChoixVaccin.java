package controller;

import bean.VaccinInfoBean;
import bean.CentreInfoBeanOut;

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

public class PriseRdvChoixVaccin extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Patient patient = (Patient) session.getAttribute("patient");
        CentreInfoBeanOut centreOut = new CentreInfoBeanOut();

        //Récuperation de l'index du centre sélectionné depuis la requête
        String selectedCentreIndex = request.getParameter("selectedCentre");

        // Si l'index n'est pas null, convertion en entier pour accéder aux autres propriétés
        if (selectedCentreIndex != null) {
            int index = Integer.parseInt(selectedCentreIndex);

    

            // var set pour etre utiliser dans la jsp TODO remplacer a un usebean au code cleaning
            session.setAttribute("selectedCentreNom", request.getParameter("selectedCentreNom_" + index));
    

            centreOut.setNomCentre(request.getParameter("selectedCentreNom_" + index));
            centreOut.setAdresse(request.getParameter("selectedCentreAdresse_" + index));
            centreOut.setTelephone(request.getParameter("selectedCentreTelephone_" + index));
            centreOut.setJourSemaineOuverture(request.getParameter("selectedCentreJourOuverture_" + index));
            centreOut.setHeureOuverture(request.getParameter("selectedCentreHeureOuverture_" + index));
            centreOut.setHeureFermeture(request.getParameter("selectedCentreHeureFermeture_" + index));
            centreOut.setCodePostal(request.getParameter("selectedCentreCodePostal_" + index));
            centreOut.setNumero(request.getParameter("selectedCentreNumero_" + index));

            // Stockez l'objet centreOut dans la session
            session.setAttribute("selectedCentreOut", centreOut);

            VaccinInfoBean vaccins = getVaccineList(patient);
            request.setAttribute("vaccins", vaccins);

            request.getRequestDispatcher("choix_vaccin.jsp").forward(request, response);

        } else {
            // Rediriger vers la page de connexion si aucun patient n'est en session
            request.setAttribute("errorMessage", "Informations de login incorrectes ou problème de connexion");
            request.getRequestDispatcher("login-patient.jsp").forward(request, response);

        }
    }

    private VaccinInfoBean getVaccineList(Patient patient) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(patient);

        HttpPost postRequest = new HttpPost(ApiUrls.RDV_LISTE_VACCIN);
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse httpResponse = HttpClientSingleton.getInstance().execute(postRequest)) {
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                return objectMapper.readValue(responseBody, VaccinInfoBean.class);

            } else {
                // Gérer les réponses autres que 200 OK
                return null;
            }
        }
    }
}
