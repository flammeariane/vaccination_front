package controller.medicalStaff;

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

public class ConfirmPresencePatientServlet extends HttpServlet {
     private MedicalUserFacadeImpl medicalUserFacade = new MedicalUserFacadeImpl();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MembrePersonnel membrePersonnel = (MembrePersonnel) session.getAttribute("membrePersonnel");
        
        String numeroNational = request.getParameter("numeroNational");
        String validePresence = request.getParameter("validePresence");

        if (numeroNational != null && membrePersonnel != null) {
            Map<String, String> requestData = new HashMap<>();
            
            requestData.put("role", membrePersonnel.getRole());
            requestData.put("adresseMail", membrePersonnel.getAdresseMail());
            requestData.put("password",  membrePersonnel.getPassword());
            requestData.put("numeroNational", numeroNational);
            requestData.put("validePresence", validePresence);
            
            medicalUserFacade.validerPresencePatientUpdateStatut(requestData);
           
            request.getRequestDispatcher("/accEntreServlet").forward(request, response);

                       
        } else {
            // Gérer le cas où les données nécessaires ne sont pas disponibles
            response.sendRedirect("login.jsp"); // Redirection vers la page de connexion ou une autre page appropriée
        }
    }
    
}
