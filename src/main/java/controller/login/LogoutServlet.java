
package controller.login;

import facade.UserOperationsFacade;
import facade.impl.UserOperationsFacadeImpl;

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
        HttpSession session = request.getSession(false);
        UserOperationsFacade userFacade = new UserOperationsFacadeImpl();
        userFacade.logoutUser(session, response);
    }
}
