package facade.impl;

import facade.UserOperationsFacade;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import modele.MembrePersonnel;
import modele.Patient;
import utils.ApiUrls;
import utils.HttpClient;

public class UserOperationsFacadeImpl implements UserOperationsFacade {

    public static UserOperationsFacade INSTANCE = new UserOperationsFacadeImpl();

    private UserOperationsFacadeImpl() {
    }

    @Override
    public Patient loginPatient(String numeroNational, String codeSecret) throws IOException {
        Map<String, String> loginData = new HashMap<>();
        loginData.put("numeroNational", numeroNational);
        loginData.put("codeSecret", codeSecret);

        return HttpClient.post(loginData, ApiUrls.LOGIN_PATIENT, Patient.class);
    }

    @Override
    public MembrePersonnel loginPersonnel(String adresseMail, String password) throws IOException {
        Map<String, String> loginData = new HashMap<>();
        loginData.put("adresseMail", adresseMail);
        loginData.put("password", password);

        return HttpClient.post(loginData, ApiUrls.LOGIN_PERSONNEL, MembrePersonnel.class);
    }

  

    @Override
    public void redirectPersonnelBasedOnRole(MembrePersonnel membrePersonnel, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Redirection basée sur le rôle
        switch (membrePersonnel.getRole()) {
            case "Accueillant en entree":
                request.getRequestDispatcher("accEntreServlet").forward(request, response);
                break;
            case "Accueillant de sortie":
                request.getRequestDispatcher("accSortieServlet").forward(request, response);
                break;
            case "Infirmier":
                request.getRequestDispatcher("infirmierServlet").forward(request, response);
                break;
            case "Medecin":
                request.getRequestDispatcher("medecinServlet").forward(request, response);
                break;
            case "Responsable de centre":
            case "Responsable general":
                request.getRequestDispatcher("responsableServlet").forward(request, response);
                break;
            default:
                // Gérer les cas où le rôle n'est pas reconnu
                request.getRequestDispatcher("/WEB-INF/login-pro.jsp").forward(request, response);
               
                break;
        }
    }


    public void logoutUser(HttpServletRequest request ,HttpSession session, HttpServletResponse response) throws IOException {
        if (session != null) {
            String userType = (String) session.getAttribute("userType");
            session.invalidate();

            if ("patient".equals(userType)) {
               request.getRequestDispatcher("/WEB-INF/login-patient.jsp");
            } else {
                request.getRequestDispatcher("/WEB-INF/login-pro.jsp");
            }
        } else {
            request.getRequestDispatcher("/WEB-INF/index.jsp");
        }
    }



  

}
