package controller;
import modele.Patient;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class LoginPatientServlet extends HttpServlet {

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numeroNational = request.getParameter("numeroNational");
        String codeSecret = request.getParameter("codeSecret");
        
        Patient patient = loginPatient(numeroNational, codeSecret);
        if (patient != null) {
        HttpSession patientSession = request.getSession(true);
        patientSession.setAttribute("patient", patient); // Stockage de l'objet patient dans la session
        patientSession.setAttribute("userType", "patient");// set du userType pour la redirection du logout 
        response.sendRedirect("dashboard_patient.jsp"); 
   } else {
            request.setAttribute("errorMessage", "Informations de login incorrectes ou problème de connexion");
            request.getRequestDispatcher("login-patient.jsp").forward(request, response);
        }
    }

    private Patient loginPatient(String numeroNational, String codeSecret) throws IOException {
        Map<String, String> loginData = new HashMap<>();
        loginData.put("numeroNational", numeroNational);
        loginData.put("codeSecret", codeSecret);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(loginData);

        HttpPost postRequest = new HttpPost("http://localhost:8080/CentreVaccinationFrontEnd/login");
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse httpResponse = httpClient.execute(postRequest)) {

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                return objectMapper.readValue(responseBody, Patient.class);
            }
        }
        return null;
    }
    
   
}
