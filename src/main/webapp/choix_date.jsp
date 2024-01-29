<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Espace Patient</title>
        <%@ include file="/WEB-INF/bootstrap.jsp" %>
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
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
                  
                         <button class="btn btn-outline-primary" onclick="history.back()"> 
                            <i class="fas fa-arrow-left"></i> Retour
                        </button>
                   
                </div>

                <div class="col-2 offset-4">
                    <form action="" method="post">
                        <button class="btn btn-outline-danger">
                            <i class="fas fa-times"></i> Annuler
                        </button>
                    </form>
                </div>
            </div>
            <h1>veuillez choisir la date de vaccination  </h1>
            vous avez voici le centre ..... et le vaccin    


            <form action="choixDate" method="post">
                <div class="row">
                    <c:forEach var="date" items="${listInfoAgenda.listInfoAgenda}">
                        <div class="col-md-4 mb-4">
                            <div class="card">
                                <div class="card-header text-info">Centre de Vaccination: ${centre.nomCentre}</div>
                                <div class="card-body">
                                    <p>date :   ${date.dateAgenda}</p>                                                                                                                             
                                    <button class="btn btn-primary mt-2">Choisir cette date</button>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </form>






    </body>
</html>
