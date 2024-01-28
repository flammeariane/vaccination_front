package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.MembrePersonnel;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import utils.ApiUrls;
import utils.HttpClientSingleton;


public class LoginPersonnelServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adresseMail = request.getParameter("adresseMail");
        String password = request.getParameter("password");

        MembrePersonnel membrePersonnel = loginPro(adresseMail, password);

        if (membrePersonnel != null) {
            HttpSession personnelSession = request.getSession();
            personnelSession.setAttribute("membrePersonnel", membrePersonnel);
            personnelSession.setAttribute("userType", "personnel"); // set du userType pour la redirection du logout 

            // Redirection basée sur le rôle
            switch (membrePersonnel.getRole()) {
                case "Accueillant en entree":
                case "Accueillant de sortie":
                case "Infirmier":
                case "Medecin":
                   
                     request.getRequestDispatcher("medicalPersonnelServlet").forward(request, response);
                    break;
                case "Responsable de centre":
                case "Responsable general":
                      //TODO check this
                    request.getRequestDispatcher("responsableServlet").forward(request, response);
                    break;
                default:
                    // Gérer les cas où le rôle n'est pas reconnu
                    response.sendRedirect("login-pro.jsp");
                    break;
            }
        } else {
            request.setAttribute("errorMessage", "Identifiants incorrects ou membre du personnel non trouvé");
            request.getRequestDispatcher("login-pro.jsp").forward(request, response);
        }
    }

    private MembrePersonnel loginPro(String adresseMail, String password) throws IOException {
        Map<String, String> loginData = new HashMap<>();
        loginData.put("adresseMail", adresseMail);
        loginData.put("password", password);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(loginData);

        HttpPost postRequest = new HttpPost(ApiUrls.LOGIN_PERSONNEL);
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

       try (CloseableHttpResponse httpResponse = HttpClientSingleton.getInstance().execute(postRequest)) {
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                return objectMapper.readValue(responseBody, MembrePersonnel.class);
            }
        }
        return null;
    }
}

