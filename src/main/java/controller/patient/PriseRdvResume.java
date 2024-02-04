package controller.patient;

import bean.saveRendezVousBeanIn;
import bean.saveRendezVousBeanOut;
import facade.impl.RendezVousFacadeImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Patient;

public class PriseRdvResume extends HttpServlet {

     private RendezVousFacadeImpl rendezVousFacade = new RendezVousFacadeImpl();
     
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

        saveRendezVousBeanIn rendezVousResume = rendezVousFacade.saveRendezVous(rendezVousBeanOut);
        session.setAttribute("rendezVousResume", rendezVousResume);

        request.getRequestDispatcher("resume.jsp").forward(request, response);

    }
 
}
