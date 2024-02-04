package controller;

import bean.ListDateDispoBean;
import bean.saveRendezVousBeanOut;
import facade.RendezVousFacade;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Patient;

public class PriseRdvChoixDateSecond extends HttpServlet {
    
     private RendezVousFacade rendezVousFacade = new RendezVousFacade();

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

        ListDateDispoBean secondRendezVous = rendezVousFacade.getAgendaSecondRendezVous(rendezVousBeanOut);
        request.setAttribute("secondRendezVous", secondRendezVous);
        request.getRequestDispatcher("choix_date_second.jsp").forward(request, response);

    }

}
