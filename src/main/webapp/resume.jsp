<%-- 
    Document   : resume
    Created on : 1 févr. 2024, 10:12:25
    Author     : flamm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>HResume!</h1>
        
        ${rendezVousResume}
        

   <c:if test="${empty rendezVousResume}">
            <div class="col-md-12">
                <p class="text-center">vide.</p>
            </div>
        </c:if> 

   
        <div class="col-md-4 mb-4">
            <div class="card">

                <div class="card-body">



                    <p>Nom:  ${rendezVousResume.nom}</p>
                    <p>Nom:  ${rendezVousResume.prenom}</p>

                </div>
            </div>
        </div>
 
</body>
</html>
