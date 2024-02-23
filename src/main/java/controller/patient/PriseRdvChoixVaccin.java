package controller.patient;

import bean.VaccinInfoBean;
import bean.CentreInfoBeanOut;

import facade.RendezVousFacade;
import facade.impl.RendezVousFacadeImpl;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Patient;

public class PriseRdvChoixVaccin extends HttpServlet {
     private RendezVousFacade rendezVousFacade = RendezVousFacadeImpl.INSTANCE;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Patient patient = (Patient) session.getAttribute("patient");
        CentreInfoBeanOut centreOut = new CentreInfoBeanOut();

        //Récuperation de l'index du centre sélectionné depuis la requête
        String selectedCentreIndex = request.getParameter("selectedCentre");

        // Si l'index n'est pas null, convertion en entier pour accéder aux autres propriétés
        if (selectedCentreIndex != null) {
            int index = Integer.parseInt(selectedCentreIndex);

     
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

            VaccinInfoBean vaccins = rendezVousFacade.getVaccineList(patient);
            request.setAttribute("vaccins", vaccins);

            request.getRequestDispatcher("choix_vaccin.jsp").forward(request, response);

        } else {
            // Rediriger vers la page de connexion si aucun patient n'est en session
            request.setAttribute("errorMessage", "Informations de login incorrectes ou problème de connexion");
            request.getRequestDispatcher("login-patient.jsp").forward(request, response);

        }
    }


}
