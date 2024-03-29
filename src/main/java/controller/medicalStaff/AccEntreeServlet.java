
package controller.medicalStaff;

import bean.PatientListJourBean;
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


public class AccEntreeServlet extends HttpServlet {

    private MedicalUserFacade medicalUserFacade = MedicalUserFacadeImpl.INSTANCE;
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        MembrePersonnel membrePersonnel = (MembrePersonnel) session.getAttribute("membrePersonnel");
       
       
        if (membrePersonnel != null) {
            Map<String, Object> requestData = new HashMap<>();
         
            requestData.put("role", membrePersonnel.getRole());
            requestData.put("adresseMail", membrePersonnel.getAdresseMail());
            requestData.put("password",  membrePersonnel.getPassword());

            PatientListJourBean patientList = medicalUserFacade.validerPresencePatientListe(requestData);
            request.setAttribute("patientList", patientList);

            request.getRequestDispatcher("/WEB-INF/dashboard_acc_entree.jsp").forward(request, response);
            
        } else {
            request.setAttribute("errorMessage", "Problème de connexion ou de récupération des données");
            request.getRequestDispatcher("/WEB-INF/login_personnel_medical.jsp").forward(request, response);
        }
    }

}
