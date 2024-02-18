package controller.responsables;

import bean.StatCentreBeanIn;
import bean.StatCentreBeanOut;
import facade.impl.ResponsableUserFacadeImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.MembrePersonnel;

public class statistiqueServlet extends HttpServlet {

    private ResponsableUserFacadeImpl responsableUserFacade = new ResponsableUserFacadeImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        MembrePersonnel membrePersonnel = (MembrePersonnel) session.getAttribute("membrePersonnel");

        //String nomCentre = request.getParameter("nomCentre");
        //int codePostal = Integer.parseInt(request.getParameter("codePostal"));
        //String role = request.getParameter("role");

        StatCentreBeanOut centreRequest = new StatCentreBeanOut();
        centreRequest.setCodePostal(1000);
        centreRequest.setNomCentre("Family Care");
        centreRequest.setRole("Responsable general");

        StatCentreBeanIn statistiquesData = responsableUserFacade.getStatCentre(centreRequest);
        request.setAttribute("statistiquesData", statistiquesData);

        request.getRequestDispatcher("statistique_centre.jsp").forward(request, response);

    }
}

// Use case n°7 : Consulter les statistiques par centre => responsable general
//  getListCentres
    // Le système affiche, pour le centre un graphique avec le taux d absence et presence
