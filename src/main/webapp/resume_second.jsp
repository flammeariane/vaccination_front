<%@page import="bean.SaveRendezVousBeanIn"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Espace Patient</title>
      <%@ include file="common-includes.jsp" %>
    </head>
    <body>

        <%@ include file="header.jsp" %>

        <div class="row justify-content-center">
            <div class="col-md-8 mt-5">
                <div class="card">
                    <div class="card-header text-info">
                        Confirmation 
                    </div>
                    <div class="card-body">

                        <h5 class="text-center mb-4"> <i class="fas fa-check-circle" style="color: green;"></i> Vos rendez-vous sont bien confirmé !  </h5>     


                        <c:if test="${not empty rendezVousResumeSecond.listRecapInfoRendezVous}">
                          
                            <c:forEach var="rendezVous" items="${rendezVousResumeSecond.listRecapInfoRendezVous}">
                                <p>Date du rendez-vous: <strong>${rendezVous.dateRdv}</strong></p>
                                <p>Vaccin: <strong>${rendezVous.nomVaccin}</strong></p>
                                <p>Centre de vaccination: <strong>${rendezVous.nomCentre}</strong></p>
                                <p>Numï¿½ro de dose: <strong>${rendezVous.numeroDose}</strong></p>
                                <p>Confirmation par email: <strong>${rendezVous.confrmationParEmail ? 'Oui' : 'Non'}</strong></p>
                                <p>Email de confirmation: <strong>${rendezVous.emailConfirmation}</strong></p>
                            </c:forEach>
                        </c:if>

                        <div class="row mt-3 ml-1">
                            <!--  TODO prevoir form quand le back aura implementer la focntion -->.
                            <form action="" method="post">
                                <div class="form-group">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" id="recevoirEmail" name="recevoirEmail" value="oui" onclick="toggleEmailInput()">
                                        <label class="form-check-label" for="recevoirEmail">
                                            Je souhaite recevoir un résumé de mes rendez-vous par email
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group" id="emailInput" style="display: none;">
                                    <label for="email">Adresse Email de confirmation:</label>
                                    <input type="email" class="form-control" id="email" name="email" placeholder="Entrez votre adresse email">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div class="row justify-content-center mt-3">
            <form action="patientServlet" method="post">
                <button type="submit" class="btn btn-custom-discard">Retour sur mon Espace</button>
            </form>
        </div>

        <script  src="${pageContext.request.contextPath}/static/js/toggleEmailInput.js"></script>

    </body>
</html>

