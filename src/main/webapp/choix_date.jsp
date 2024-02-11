<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Espace Patient</title>
        <%@ include file="/WEB-INF/bootstrap.jsp" %>
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/button-styles.css">

        <script src="//ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=??????Y&callback=initMap" async defer></script>
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

        <%@ include file="header.jsp" %>
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
            <h1>veuillez choisir la date de vaccination  </h1>

            <%
                // Récupérer le nom du centre sélectionné de la session
                String selectedCentre = (String) session.getAttribute("selectedCentreNom");
            %>


            <form action="dispatch" method="post">
                <div class="row">
                    <c:forEach var="date" items="${listInfoAgenda.listInfoAgenda}" varStatus="loop">
                        <c:if test="${date.nbrPlaceRestante > 0}">

                            <div class="col-md-4 mb-4">
                                <div class="card">
                                    <div class="card-header text-info">Centre de Vaccination: <%= selectedCentre%></div>
                                    <div class="card-body">
                                          <input type="hidden" name="selectedDateAgenda_${loop.index}" value="${date.dateAgenda}">
                                        <p> <fmt:formatDate value="${date.dateAgenda}" pattern="EEEE d MMMM yyyy HH:mm" /></p>
                                        <p>nombre de place  :   ${date.nbrMaxPlace}</p>   
                                       <button class="btn btn-custom mt-2" type="submit" name="selectedDate" value="${loop.index}">
                                        Choisir cette date
                                    </button>
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
            </form>
    </body>
</html>
