package controller.patient;

import bean.ListDateDispoBean;
import bean.saveRendezVousBeanOut;
import facade.impl.RendezVousFacadeImpl;
import java.io.IOException;
import java.time.LocalDateTime;
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

    private RendezVousFacadeImpl rendezVousFacade = new RendezVousFacadeImpl();

     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Patient patient = (Patient) session.getAttribute("patient");
        saveRendezVousBeanOut rendezVousBeanOut = new saveRendezVousBeanOut();

        String dateFromSession = (String) session.getAttribute("selectedDateAgenda");
        // Utilisez le format correct pour la date récupérée de la session
        DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        // Pas besoin de formatterOutput si on convertit directement au format ISO_LOCAL_DATE_TIME
        ZonedDateTime dateTime = ZonedDateTime.parse(dateFromSession, formatterInput);
        String formattedDate = dateTime.toLocalDateTime().toString(); // Convertit en format ISO_LOCAL_DATE_TIME
        rendezVousBeanOut.setDateRdv(formattedDate);
        
        rendezVousBeanOut.setNomCentre((String) session.getAttribute("selectedCentreNom"));
        rendezVousBeanOut.setNomVaccin((String) session.getAttribute("selectedVaccinNom"));
        rendezVousBeanOut.setConfirmParEmail("1");
        rendezVousBeanOut.setEmailConfirmation(patient.getEmail());
        rendezVousBeanOut.setStatutConfirmer("oui");
        rendezVousBeanOut.setNumeroNational(patient.getNumeroNational());
        rendezVousBeanOut.setNomFamille(patient.getNomFamille());
        rendezVousBeanOut.setPrenom(patient.getPrenom()); // Correction pour utiliser getPrenom au lieu de getNomFamille pour le prénom

        ListDateDispoBean secondRendezVous = rendezVousFacade.getAgendaSecondRendezVous(rendezVousBeanOut);
        request.setAttribute("secondRendezVous", secondRendezVous);
        request.getRequestDispatcher("choix_date_second.jsp").forward(request, response);
    }

}
