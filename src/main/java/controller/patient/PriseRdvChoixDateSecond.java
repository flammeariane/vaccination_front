package controller.patient;

import bean.ListDateDispoBean;
import bean.SaveRendezVousBeanOut;
import facade.RendezVousFacade;
import facade.impl.RendezVousFacadeImpl;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Patient;

public class PriseRdvChoixDateSecond extends HttpServlet {

    private RendezVousFacade rendezVousFacade = RendezVousFacadeImpl.INSTANCE;

     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Patient patient = (Patient) session.getAttribute("patient");
        SaveRendezVousBeanOut rendezVousBeanOut = new SaveRendezVousBeanOut();

        String firstRdvDate = (String) request.getAttribute("firstRdvdate");
    
        rendezVousBeanOut.setDateRdv(firstRdvDate);        
        rendezVousBeanOut.setNomCentre((String) session.getAttribute("selectedCentreNom"));
        rendezVousBeanOut.setNomVaccin((String) session.getAttribute("selectedVaccinNom"));
        rendezVousBeanOut.setConfirmParEmail("1");
        rendezVousBeanOut.setEmailConfirmation(patient.getEmail());
        rendezVousBeanOut.setStatutConfirmer("oui");
        rendezVousBeanOut.setNumeroNational(patient.getNumeroNational());
        rendezVousBeanOut.setNomFamille(patient.getNomFamille());
        rendezVousBeanOut.setPrenom(patient.getPrenom()); 

//        ListDateDispoBean secondRendezVous = rendezVousFacade.getAgendaSecondRendezVous(rendezVousBeanOut);
//        request.setAttribute("secondRendezVous", secondRendezVous);
//        request.getRequestDispatcher("/WEB-INF/choix_date_second.jsp").forward(request, response);
    }

}
