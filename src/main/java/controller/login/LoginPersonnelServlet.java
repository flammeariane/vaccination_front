package controller.login;

import facade.UserOperationsFacade;
import facade.impl.UserOperationsFacadeImpl;
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

        UserOperationsFacade personnelFacade = UserOperationsFacadeImpl.INSTANCE;
        MembrePersonnel membrePersonnel = personnelFacade.loginPersonnel(adresseMail, password);

        if (membrePersonnel != null) {
            HttpSession personnelSession = request.getSession();
            personnelSession.setAttribute("membrePersonnel", membrePersonnel);
            personnelSession.setAttribute("userType", "personnel");

            personnelFacade.redirectPersonnelBasedOnRole(membrePersonnel, request, response);
        } else {
            request.setAttribute("errorMessage", "Identifiants incorrects ou membre du personnel non trouvé");
           
            request.getRequestDispatcher("/WEB-INF/login-pro.jsp").forward(request, response);
        }
    }
   
}

