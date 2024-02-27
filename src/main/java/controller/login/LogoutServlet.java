
package controller.login;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupération de la session sans en créer une nouvelle
        HttpSession session = request.getSession(false);

        if (session != null) {
            String userType = (String) session.getAttribute("userType");
            // Invalidation de la session pour déconnecter l'utilisateur
            session.invalidate();

            if ("patient".equals(userType)) {
                // Redirection vers la page de login des patients
                request.getRequestDispatcher("/WEB-INF/login-patient.jsp").forward(request, response);
            } else {
                // Redirection vers la page de login des professionnels
                request.getRequestDispatcher("/WEB-INF/login-pro.jsp").forward(request, response);
            }
        } else {
            // Redirection vers la page d'accueil si aucune session n'existe
            response.sendRedirect("index.jsp");
        }
    }
}