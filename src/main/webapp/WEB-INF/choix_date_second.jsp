<%@ page import="bean.SaveRendezVousBeanOut" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Espace Patient</title>
        <%@ include file="/WEB-INF/common-includes.jsp" %>

        <style>
            .card-header {
                background-color: #007bff;
                color: white;
            }
            .container {
                padding-top: 20px;
            }
            .card {
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>

        <%@ include file="/WEB-INF/header.jsp" %>
        <div class="container">        
            <div class="row mb-5">
                <div class="col-6">
                    <button class="btn btn-custom-back" onclick="history.back()"> 
                        <i class="fas fa-arrow-left"></i> Retour
                    </button> 
                </div>

                <div class="col-2 offset-4">
                    <form action="" method="post">
                        <button class="btn btn-custom-discard">
                            <i class="fas fa-times"></i> Annuler
                        </button>
                    </form>
                </div>
            </div>
            <h1>veuillez choisir la date de votre second rendez vous  </h1>

            <%
                // R�cup�rer le nom du centre s�lectionn� de la session
                String selectedCentre = (String) session.getAttribute("selectedCentreNom");
            %>

                <div class="row">
                    <c:forEach var="date" items="${secondRendezVous.listInfoAgenda}">
                        <c:if test="${date.nbrPlaceRestante > 0}">

                            <div class="col-md-4 mb-4">
                                <div class="card">
                                    <div class="card-header text-info">Centre de Vaccination: <%= selectedCentre%></div>
                                    <div class="card-body">
                                        <form action="resumeSecond" method="post">
                                          <input type="hidden" name="selectedDate" value="${date.dateAgenda}">
                                          <p> <fmt:formatDate value="${date.dateAgenda}" pattern="EEEE d MMMM yyyy HH:mm" /></p>
                                          <p>nombre de place  :   ${date.nbrMaxPlace}</p>
                                          <button class="btn btn-custom mt-2" type="submit">
                                            Choisir cette date
                                          </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>

                    <c:if test="${empty listInfoAgenda}">
                        <div class="col-md-12">
                            <p class="text-center">Il n'y a plus de rendez-vous disponible pour ce centre. Veuillez choisir un autre centre.</p>
                        </div>
                    </c:if> 
                </div>
    </body>
</html>
