
package controller.patient;


import bean.SaveRendezVousBeanOut;
import bean.SaveRendezVousSecondBeanIn;
import bean.SaveRendezVousSecondBeanOut;
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


public class PriseRdvResumeSecond extends HttpServlet {
     private RendezVousFacade rendezVousFacade = RendezVousFacadeImpl.INSTANCE;
    
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         HttpSession session = request.getSession();
         Patient patient = (Patient) session.getAttribute("patient");
         session.getAttribute("rendezVousResume");


         SaveRendezVousSecondBeanOut rendezVousSecondBeanOut = new SaveRendezVousSecondBeanOut();


         rendezVousSecondBeanOut.setNomCentre((String) session.getAttribute("selectedCentreNom"));
         rendezVousSecondBeanOut.setNomVaccin((String) session.getAttribute("selectedVaccinNom"));
         rendezVousSecondBeanOut.setConfirmParEmail("1");
         rendezVousSecondBeanOut.setEmailConfirmation(patient.getEmail());
         rendezVousSecondBeanOut.setStatutConfirmer("oui");
         rendezVousSecondBeanOut.setNumeroNational(patient.getNumeroNational());
         rendezVousSecondBeanOut.setNomFamille(patient.getNomFamille());
         rendezVousSecondBeanOut.setPrenom(patient.getPrenom());
         // selected date
         String selectedDate = request.getParameter("selectedDate");
         DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
         ZonedDateTime dateTime = ZonedDateTime.parse(selectedDate, formatterInput);
         String formattedDate = dateTime.toLocalDateTime().toString(); // Convertit en format ISO_LOCAL_DATE_TIME

         rendezVousSecondBeanOut.setDateRdv(formattedDate);


         SaveRendezVousSecondBeanIn rendezVousResumeSecond = rendezVousFacade.saveRendezVousSecond(rendezVousSecondBeanOut);

         SaveRendezVousSecondBeanIn.ListRecapInfoRendezVous listRecapFirstInfoRendezVous = new SaveRendezVousSecondBeanIn.ListRecapInfoRendezVous();

         SaveRendezVousBeanOut rendezVousFirstBeanOut =  (SaveRendezVousBeanOut) session.getAttribute("firstRdv");
         session.setAttribute("firstRdv", null);
         listRecapFirstInfoRendezVous.setDateRdv(rendezVousFirstBeanOut.getDateRdv());
         listRecapFirstInfoRendezVous.setNomCentre(rendezVousFirstBeanOut.getNomCentre());
         listRecapFirstInfoRendezVous.setNomVaccin(rendezVousFirstBeanOut.getNomVaccin());
         listRecapFirstInfoRendezVous.setConfrmationParEmail(rendezVousFirstBeanOut.getConfirmParEmail());

         rendezVousResumeSecond.getListRecapInfoRendezVous().add(0, listRecapFirstInfoRendezVous);

         request.setAttribute("rendezVousResumeSecond", rendezVousResumeSecond);

         request.getRequestDispatcher("/WEB-INF/resume_second.jsp").forward(request, response);

    }
     
     
}
