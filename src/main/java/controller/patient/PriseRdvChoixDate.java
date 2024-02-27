package controller.patient;

import bean.CentreInfoBeanOut;
import bean.ListDateDispoBean;
import facade.RendezVousFacade;
import facade.impl.RendezVousFacadeImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PriseRdvChoixDate extends HttpServlet {
     private RendezVousFacade rendezVousFacade = RendezVousFacadeImpl.INSTANCE;

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Récupérez l'objet centreOut de la session
        CentreInfoBeanOut centreOut = (CentreInfoBeanOut) request.getSession().getAttribute("selectedCentreOut");

        // Récupérer l'index du centre sélectionné depuis la requête
        String selectedVaccinIndex = request.getParameter("selectedVaccin");

        if (selectedVaccinIndex != null) {
            int index = Integer.parseInt(selectedVaccinIndex);

            String selectedVaccinNom = request.getParameter("selectedVaccinNom_" + index);
            String selectedVaccinNbrDoseTotal = request.getParameter("selectedVaccinNbrDoseTotal_" + index);
            String selectedVaccinDureeEntreDose = request.getParameter("selectedVaccinDureeEntreDose_" + index);

            // Stockez les valeurs dans la session 
            session.setAttribute("selectedVaccinNom", selectedVaccinNom);
            session.setAttribute("selectedVaccinNbrDoseTotal", selectedVaccinNbrDoseTotal);
            session.setAttribute("selectedVaccinDureeEntreDose", selectedVaccinDureeEntreDose);

            ListDateDispoBean listInfoAgenda = rendezVousFacade.getDateByCenter(centreOut);
            request.setAttribute("listInfoAgenda", listInfoAgenda);

            request.getRequestDispatcher("/WEB-INF/choix_date.jsp").forward(request, response);

        } else {
            // Rediriger vers la page de connexion si aucun patient n'est en session
            request.setAttribute("errorMessage", "Informations de login incorrectes ou problème de connexion");
            request.getRequestDispatcher("/WEB-INF/login-patient.jsp").forward(request, response);

        }
    }

}
