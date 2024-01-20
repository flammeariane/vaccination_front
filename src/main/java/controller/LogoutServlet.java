
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class LogoutServlet extends HttpServlet {

   
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Invalider la session
        HttpSession session = request.getSession(false);
        session.invalidate();

        // Rediriger vers la page de connexion 
   
          request.getRequestDispatcher("login-patient.jsp").forward(request, response);
      
    }
 

}
