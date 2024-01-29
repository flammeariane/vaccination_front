package controller;

import bean.CentreInfoBean;
import bean.VaccinInfoBean;
import bean.VaccinationHistoryBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

        // Récupérer l'index du centre sélectionné depuis la requête
        String selectedCentreIndex = request.getParameter("selectedCentre");

        // Afficher l'index récupéré (pour le débogage)
        System.out.println("Index du centre sélectionné : " + selectedCentreIndex);

        // Si l'index n'est pas null, convertissez-le en entier pour accéder aux autres propriétés
        if (selectedCentreIndex != null) {
            int index = Integer.parseInt(selectedCentreIndex);

            // Utilisez l'index pour récupérer les autres propriétés du centre
            String selectedCentreNom = request.getParameter("selectedCentreNom_" + index);
            String selectedCentreAdresse = request.getParameter("selectedCentreAdresse_" + index);
            String selectedCentreTelephone = request.getParameter("selectedCentreTelephone_" + index);
            String selectedCentreJourOuverture = request.getParameter("selectedCentreJourOuverture_" + index);
            String selectedCentreHeureOuverture = request.getParameter("selectedCentreHeureOuverture_" + index);
            String selectedCentreHeureFermeture = request.getParameter("selectedCentreHeureFermeture_" + index);
            String selectedCentreCodePostal = request.getParameter("selectedCentreCodePostal_" + index);
            String selectedCentreNumero = request.getParameter("selectedCentreNumero_" + index);

            // Afficher les valeurs récupérées (pour le débogage)
            System.out.println("Nom du centre sélectionné : " + selectedCentreNom);
            System.out.println("Adresse du centre sélectionné : " + selectedCentreAdresse);
            System.out.println("Téléphone du centre sélectionné : " + selectedCentreTelephone);
            // ... (affichez les autres propriétés de la même manière)

            // Stockez les valeurs dans la session 
            session.setAttribute("selectedCentreNom", selectedCentreNom);
            session.setAttribute("selectedCentreAdresse", selectedCentreAdresse);
            session.setAttribute("selectedCentreTelephone", selectedCentreTelephone);
            session.setAttribute("selectedCentreJourOuverture", selectedCentreJourOuverture);
            session.setAttribute("selectedCentreHeureOuverture", selectedCentreHeureOuverture);
            session.setAttribute("selectedCentreHeureFermeture", selectedCentreHeureFermeture);
            session.setAttribute("selectedCentreCodePostal", selectedCentreCodePostal);
            session.setAttribute("selectedCentreNumero", selectedCentreNumero);

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
