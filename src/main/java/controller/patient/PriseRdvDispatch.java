package controller.patient;

import bean.ListDateDispoBean;
import bean.SaveRendezVousBeanIn;
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

public class PriseRdvDispatch extends HttpServlet {

    private RendezVousFacade rendezVousFacade = RendezVousFacadeImpl.INSTANCE;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Patient patient = (Patient) session.getAttribute("patient");
        SaveRendezVousBeanOut rendezVousBeanOut = new SaveRendezVousBeanOut();
        String selectedVaccinNbrDoseTotal = (String) session.getAttribute("selectedVaccinNbrDoseTotal");
        int nbrDosesTotal = Integer.parseInt(selectedVaccinNbrDoseTotal);

        rendezVousBeanOut.setNomCentre((String) session.getAttribute("selectedCentreNom"));
        rendezVousBeanOut.setNomVaccin((String) session.getAttribute("selectedVaccinNom"));

        rendezVousBeanOut.setConfirmParEmail("1");
        rendezVousBeanOut.setEmailConfirmation(patient.getEmail());
        rendezVousBeanOut.setStatutConfirmer("oui");
        rendezVousBeanOut.setNumeroNational(patient.getNumeroNational());
        rendezVousBeanOut.setNomFamille(patient.getNomFamille());
        rendezVousBeanOut.setPrenom(patient.getNomFamille());

        String selectedDate = request.getParameter("selectedDate");

        DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        ZonedDateTime dateTime = ZonedDateTime.parse(selectedDate, formatterInput);
        String formattedDate = dateTime.toLocalDateTime().toString(); // Convertit en format ISO_LOCAL_DATE_TIME

        rendezVousBeanOut.setDateRdv(formattedDate);

        // Rediriger vers le servlet approprié en fonction du nombre de doses total
        if (nbrDosesTotal == 1) {
            // Enregistrer les informations de rendez-vous
            SaveRendezVousBeanIn rendezVousResume = rendezVousFacade.saveRendezVous(rendezVousBeanOut);
            session.setAttribute("rendezVousResume", rendezVousResume);
            // Rediriger vers le servlet de résumé
            request.getRequestDispatcher("/WEB-INF/resume.jsp").forward(request, response);
        } else if (nbrDosesTotal == 2) {
            ListDateDispoBean listDateDispoBean = rendezVousFacade.saveRdvAndGetAgendaSecondRendezVous(rendezVousBeanOut);
            session.setAttribute("firstRdv" , rendezVousBeanOut);
            request.setAttribute("secondRendezVous", listDateDispoBean);
            // Rediriger vers le servlet de choix de la seconde date
            request.getRequestDispatcher("/WEB-INF/choix_date_second.jsp").forward(request, response);
        }
    }
}
