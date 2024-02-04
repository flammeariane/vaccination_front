package controller.patient;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PriseRdvDispatch extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String selectedVaccinNbrDoseTotal = (String) session.getAttribute("selectedVaccinNbrDoseTotal");
        int nbrDosesTotal = Integer.parseInt(selectedVaccinNbrDoseTotal);

        String selectedIndex = request.getParameter("selectedDate");

        if (selectedIndex != null) {
            String dateInputName = "selectedDateAgenda_" + selectedIndex;
            String selectedDate = request.getParameter(dateInputName);
            session.setAttribute("selectedDate", selectedDate);
        }

// Convertir le nombre de doses en entier pour pouvoir faire des comparaisons
        if (nbrDosesTotal == 1) {
            // Rediriger vers le servlet de résumé
            request.getRequestDispatcher("/resume").forward(request, response);
            // response.sendRedirect(request.getContextPath() + "/resume");
        } else if (nbrDosesTotal == 2) {
            // Rediriger vers le servlet de choix de la seconde date
            request.getRequestDispatcher("/choixDatesecond").forward(request, response);
            //response.sendRedirect(request.getContextPath() + "/choixdateSecond");
        }
    }
}
