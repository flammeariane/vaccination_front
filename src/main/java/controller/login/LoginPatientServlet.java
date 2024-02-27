package controller.login;

import facade.UserOperationsFacade;
import facade.impl.UserOperationsFacadeImpl;
import modele.Patient;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginPatientServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numeroNational = request.getParameter("numeroNational");
        String codeSecret = request.getParameter("codeSecret");

       UserOperationsFacade patientFacade = UserOperationsFacadeImpl.INSTANCE;
        Patient patient = patientFacade.loginPatient(numeroNational, codeSecret);

        if (patient != null) {
            HttpSession patientSession = request.getSession(true);
            patientSession.setAttribute("patient", patient); // Stockage de l'objet patient dans la session
            patientSession.setAttribute("userType", "patient");// set du userType pour la redirection du logout
            request.getRequestDispatcher("patientServlet").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Informations de login incorrectes ou problème de connexion");
            request.getRequestDispatcher("login-patient.jsp").forward(request, response);
        }
    }

   
}
