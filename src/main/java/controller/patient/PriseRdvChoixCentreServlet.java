package controller.patient;

import bean.CentreInfoBean;
import facade.RendezVousFacade;
import facade.impl.RendezVousFacadeImpl;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Patient;

public class PriseRdvChoixCentreServlet extends HttpServlet {

    private RendezVousFacade rendezVousFacade = RendezVousFacadeImpl.INSTANCE;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Patient patient = (Patient) session.getAttribute("patient");

        if (patient != null) {
            Map<String, Object> requestData = new HashMap<>();
            // Créez un objet pour les données de requête
            requestData.put("numeroNational", patient.getNumeroNational());
            requestData.put("codePostal", patient.getCodePostal());
            requestData.put("prenom", patient.getPrenom());
            requestData.put("nomFamille", patient.getNomFamille());
            requestData.put("codeSecret", patient.getCodeSecret());

            CentreInfoBean centres = rendezVousFacade.getCentresNear(requestData);
            request.setAttribute("centres", centres);

            request.getRequestDispatcher("choix_centre.jsp").forward(request, response);

        } else {
            // Rediriger vers la page de connexion si aucun patient n'est en session
            request.setAttribute("errorMessage", "Informations de login incorrectes ou problème de connexion");
            request.getRequestDispatcher("login-patient.jsp").forward(request, response);
        }
    }

}
