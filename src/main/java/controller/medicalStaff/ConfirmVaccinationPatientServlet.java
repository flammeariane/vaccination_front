package controller.medicalStaff;

import facade.MedicalUserFacade;
import facade.impl.MedicalUserFacadeImpl;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.MembrePersonnel;

public class ConfirmVaccinationPatientServlet extends HttpServlet {

    private MedicalUserFacade medicalUserFacade = MedicalUserFacadeImpl.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MembrePersonnel membrePersonnel = (MembrePersonnel) session.getAttribute("membrePersonnel");

        String numeroNational = request.getParameter("numeroNational");

        if (numeroNational != null && membrePersonnel != null) {
            Map<String, String> requestData = new HashMap<>();

            requestData.put("role", membrePersonnel.getRole());
            requestData.put("adresseMail", membrePersonnel.getAdresseMail());
            requestData.put("password", membrePersonnel.getPassword());
            requestData.put("numeroNational", numeroNational);
            requestData.put("numeroLot", request.getParameter("numeroLot"));
            requestData.put("statutConfirmer", "OUI");

            medicalUserFacade.validerVaccinationPatientUpdateStatut(requestData);

            request.getRequestDispatcher("/accSortieServlet").forward(request, response);

        } else {
           
            response.sendRedirect("login.jsp"); 
        }
    }

}
