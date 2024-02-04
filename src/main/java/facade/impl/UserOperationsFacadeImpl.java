
package facade.impl;

import facade.UserOperationsFacade;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserOperationsFacadeImpl implements UserOperationsFacade {

    @Override
    public void logoutUser(HttpSession session, HttpServletResponse response) throws IOException {
        if (session != null) {
            String userType = (String) session.getAttribute("userType");
            session.invalidate();

            if ("patient".equals(userType)) {
                response.sendRedirect("login-patient.jsp");
            } else {
                response.sendRedirect("login-pro.jsp");
            }
        } else {
            response.sendRedirect("index.jsp"); 
        }
    }
}
