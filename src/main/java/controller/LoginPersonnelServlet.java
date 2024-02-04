package controller;

import facade.PersonnelOperationsFacade;
import facade.impl.PersonnelOperationsFacadeImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.MembrePersonnel;


public class LoginPersonnelServlet extends HttpServlet {
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adresseMail = request.getParameter("adresseMail");
        String password = request.getParameter("password");

        PersonnelOperationsFacade personnelFacade = new PersonnelOperationsFacadeImpl();
        MembrePersonnel membrePersonnel = personnelFacade.loginPersonnel(adresseMail, password);

        if (membrePersonnel != null) {
            HttpSession personnelSession = request.getSession();
            personnelSession.setAttribute("membrePersonnel", membrePersonnel);
            personnelSession.setAttribute("userType", "personnel");

            personnelFacade.redirectPersonnelBasedOnRole(membrePersonnel, request, response);
        } else {
            request.setAttribute("errorMessage", "Identifiants incorrects ou membre du personnel non trouvé");
            request.getRequestDispatcher("login-pro.jsp").forward(request, response);
        }
    }
   
}

