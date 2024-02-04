package facade;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface UserOperationsFacade {
    void logoutUser(HttpSession session, HttpServletResponse response) throws IOException;
}