package controller.patient;

import bean.VaccinationHistoryBean;
import modele.Patient;
import facade.impl.RendezVousFacadeImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class PatientServlet extends HttpServlet {
    
    private RendezVousFacadeImpl rendezVousFacade = new RendezVousFacadeImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Patient patient = (Patient) session.getAttribute("patient");

// Informations nécessaires pour l'appel API
        if (patient != null) {

            // Utilisez l'objet patient pour récupérer les informations nécessaires
            String numeroNational = patient.getNumeroNational();

            VaccinationHistoryBean v = rendezVousFacade.getVaccinationHistory(numeroNational);
            session.setAttribute("history", v);

            request.getRequestDispatcher("dashboard_patient.jsp").forward(request, response);

        } else {
            // Rediriger vers la page de connexion si aucun patient n'est en session
            request.setAttribute("errorMessage", "Informations de login incorrectes ou problème de connexion");
            request.getRequestDispatcher("login-patient.jsp").forward(request, response);
        }

    }
}
