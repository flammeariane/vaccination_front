<%@page import="bean.StatCentreBeanIn.TxPresAbsePersonnelJour"%>
<%@page import="java.util.List"%>
<%@page import="bean.StatCentreBeanIn.NbrPatientVaccineJour"%>
<%@page import="bean.StatCentreBeanIn"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>

<!DOCTYPE html>
<html>
    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tableau de Bord du Responsable</title>
        <%@ include file="header.jsp" %>
        <%@ include file="common-includes.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    
    <body>
        <div class="container mt-4">
            <div class="d-flex justify-content-end align-items-center mb-3">
                <form action="logout" method="post">
                    <button class="btn btn-custom-delete" type="submit">Déconnexion <i class="fa-solid fa-arrow-right-from-bracket"></i></button>
                </form>
            </div>
            <h3 class="text-center mt-5">Statistique pour le centre : <b>NOM DU CENTRE</b> </h3>

            <div>
                <canvas id="personnelPresenceAbsenceChart"></canvas>
            </div>

            <div>
                <canvas id="personnelPresenceAbsenceMonthChart"></canvas>
            </div>

            <div>
                <canvas id="tauxRemplissageJourChart"></canvas>
            </div>
            
            <div>
    <canvas id="txPresAbsePatienJourChart"></canvas>
</div>

        </div>

        <%
            ObjectMapper objectMapper = new ObjectMapper();
            StatCentreBeanIn statistiquesData = (StatCentreBeanIn) request.getAttribute("statistiquesData");

            String dataJson = "{}"; /* la valeur par défaut permet d éviter les null*/
            try {
                dataJson = objectMapper.writeValueAsString(statistiquesData.getTxPresAbsePersonnelJour());
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>

        <%
            String dataJsonPersonnelJour = "{}";
            String dataJsonPersonnelMois = "{}";
            try {
                dataJsonPersonnelJour = objectMapper.writeValueAsString(statistiquesData.getTxPresAbsePersonnelJour());
                dataJsonPersonnelMois = objectMapper.writeValueAsString(statistiquesData.getTxPresAbsePersonnelMois());
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>

        <%
            String dataJsonTauxRemplissageJour = "{}";
            try {
               
                dataJsonTauxRemplissageJour = objectMapper.writeValueAsString(statistiquesData.getTauxremplissageJour());
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>

        <%
            String dataJsonTxPresAbsePatienJour = "{}";
            try {
                dataJsonTxPresAbsePatienJour = objectMapper.writeValueAsString(statistiquesData.getTxPresAbsePatienJour());
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>



        <script>
            var chartData = JSON.parse('<%= dataJson%>');
            var chartDataPersonnelJour = JSON.parse('<%= dataJsonPersonnelJour%>');
            var chartDataPersonnelMois = JSON.parse('<%= dataJsonPersonnelMois%>');
            var chartDataTauxRemplissageJour = JSON.parse('<%= dataJsonTauxRemplissageJour%>');
            var chartDataTxPresAbsePatienJour = JSON.parse('<%= dataJsonTxPresAbsePatienJour%>');

        </script>

        <script src="${pageContext.request.contextPath}/static/js/chart-data.js"></script>

    </body>
</html>
