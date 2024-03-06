package controller.patient;

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

        String selectedDateIndex = request.getParameter("selectedDate");

        rendezVousBeanOut.setNomCentre((String) session.getAttribute("selectedCentreNom"));
        rendezVousBeanOut.setNomVaccin((String) session.getAttribute("selectedVaccinNom"));

        rendezVousBeanOut.setConfirmParEmail("1");
        rendezVousBeanOut.setEmailConfirmation(patient.getEmail());
        rendezVousBeanOut.setStatutConfirmer("oui");
        rendezVousBeanOut.setNumeroNational(patient.getNumeroNational());
        rendezVousBeanOut.setNomFamille(patient.getNomFamille());
        rendezVousBeanOut.setPrenom(patient.getNomFamille());

  

        if (selectedDateIndex != null) {
            // Construisez la clé pour récupérer la date sélectionnée à partir de la session
            String dateKey = "selectedDateAgenda_" + selectedDateIndex;
            String dateFromSession = (String) session.getAttribute(dateKey);

            if (dateFromSession != null) {
                DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                ZonedDateTime dateTime = ZonedDateTime.parse(dateFromSession, formatterInput);
                String formattedDate = dateTime.toLocalDateTime().toString(); // Convertit en format ISO_LOCAL_DATE_TIME
                rendezVousBeanOut.setDateRdv(formattedDate);

                // Le reste de votre logique...
            } else {
                // Gérez le cas où la date sélectionnée n'est pas trouvée ou est null
            }
        }

        // Enregistrer les informations de rendez-vous
        SaveRendezVousBeanIn rendezVousResume = rendezVousFacade.saveRendezVous(rendezVousBeanOut);
        session.setAttribute("rendezVousResume", rendezVousResume);

        // Rediriger vers le servlet approprié en fonction du nombre de doses total
        if (nbrDosesTotal == 1) {
            // Rediriger vers le servlet de résumé
            request.getRequestDispatcher("/WEB-INF/resume.jsp").forward(request, response);
        } else if (nbrDosesTotal == 2) {
            // Rediriger vers le servlet de choix de la seconde date
            request.getRequestDispatcher("/choixDatesecond").forward(request, response);
        }
    }
}
