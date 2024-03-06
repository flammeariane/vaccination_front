
package controller.patient;


import bean.SaveRendezVousSecondBeanIn;
import bean.SaveRendezVousSecondBeanOut;
import facade.RendezVousFacade;
import facade.impl.RendezVousFacadeImpl;
import java.io.IOException;
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
        rendezVousSecondBeanOut.setDateRdv("2024-03-01T09:30:00");
        rendezVousSecondBeanOut.setConfirmParEmail("1");
        rendezVousSecondBeanOut.setEmailConfirmation(patient.getEmail());
        rendezVousSecondBeanOut.setStatutConfirmer("oui");
        rendezVousSecondBeanOut.setNumeroNational(patient.getNumeroNational());
        rendezVousSecondBeanOut.setNomFamille(patient.getNomFamille());
        rendezVousSecondBeanOut.setPrenom(patient.getPrenom());
        
 
        SaveRendezVousSecondBeanIn rendezVousResumeSecond = rendezVousFacade.saveRendezVousSecond(rendezVousSecondBeanOut);
        request.setAttribute("rendezVousResumeSecond", rendezVousResumeSecond);

        request.getRequestDispatcher("/WEB-INF/resume_second.jsp").forward(request, response);

    }
     
     
}
