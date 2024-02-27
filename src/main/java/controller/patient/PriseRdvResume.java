package controller.patient;

import bean.SaveRendezVousBeanIn;
import bean.SaveRendezVousBeanOut;
import facade.RendezVousFacade;
import facade.impl.RendezVousFacadeImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Patient;

public class PriseRdvResume extends HttpServlet {

     private RendezVousFacade rendezVousFacade = RendezVousFacadeImpl.INSTANCE;
     
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Patient patient = (Patient) session.getAttribute("patient");
        SaveRendezVousBeanOut rendezVousBeanOut = new SaveRendezVousBeanOut();

 
        rendezVousBeanOut.setNomCentre((String) session.getAttribute("selectedCentreNom"));
        rendezVousBeanOut.setNomVaccin((String) session.getAttribute("selectedVaccinNom"));
        rendezVousBeanOut.setDateRdv((String) session.getAttribute("selectedDateAgenda"));
        rendezVousBeanOut.setConfirmParEmail("1");
        rendezVousBeanOut.setEmailConfirmation(patient.getEmail());
        rendezVousBeanOut.setStatutConfirmer("oui");
        rendezVousBeanOut.setNumeroNational(patient.getNumeroNational());
        rendezVousBeanOut.setNomFamille(patient.getNomFamille());
        rendezVousBeanOut.setPrenom(patient.getNomFamille());

        SaveRendezVousBeanIn rendezVousResume = rendezVousFacade.saveRendezVous(rendezVousBeanOut);
        session.setAttribute("rendezVousResume", rendezVousResume);

        request.getRequestDispatcher("resume.jsp").forward(request, response);

    }
 
}
