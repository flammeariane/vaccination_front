package controller.medicalStaff;

import bean.IncidentBeanIn;
import com.fasterxml.jackson.databind.ObjectMapper;
import facade.MedicalUserFacade;
import facade.impl.MedicalUserFacadeImpl;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.MembrePersonnel;

public class IncidentServlet extends HttpServlet {

    private MedicalUserFacade medicalUserFacade = MedicalUserFacadeImpl.INSTANCE;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MembrePersonnel membrePersonnel = (MembrePersonnel) session.getAttribute("membrePersonnel");
        String numeroNational = request.getParameter("numeroNational");

        Map<String, Object> requestData = new HashMap<>();

        requestData.put("role", membrePersonnel.getRole());
        requestData.put("adresseMail", membrePersonnel.getAdresseMail());
        requestData.put("password", membrePersonnel.getPassword());
        requestData.put("numeroNational", numeroNational);

        IncidentBeanIn commentaires = medicalUserFacade.incidentSurvenuSelectPatient(requestData);

        // Convertir l'objet IncidentBeanIn en JSON
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(commentaires);

        // Définir le type de contenu et écrire la réponse JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        request.getSession().setAttribute("commentaires", commentaires);

    }

}
