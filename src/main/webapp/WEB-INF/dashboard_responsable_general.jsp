
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tableau de Bord du Responsable</title>

        <%@ include file="/WEB-INF/common-includes.jsp" %>

    </head>
    <body>
        <%@ include file="/WEB-INF/header.jsp" %>
        <div class="container mt-4">

            <div class="d-flex justify-content-end align-items-center mb-3">
                <form action="logout" method="post" > <button class="btn btn-custom-delete" type="submit">Déconnexion <i class="fa-solid fa-arrow-right-from-bracket"></i> </button></form>
            </div>
            <h3 class="text-center mt-5">Bienvenue <b>${membrePersonnel.prenom}</b> dans le tableau de bord du responsable générale!</h3>


            <!-- TODO FIX THIS -->
            <div class="search-section">
                <div class="search-box">
                    <input type="text" id="searchByPostCode" placeholder="Rechercher par code postal" class="search-input">
                    <i class="fa fa-search search-icon"></i> 
                </div>
            </div>

            <h4>Liste des Centres</h4>
            <form action="statistique" method="post">
                <table id="tableauPatient" class="table table-hover table-striped table-bordered mt-4">
                    <thead class="custom-header">
                        <tr>
                            <th>Localité</th>
                            <th>Adresse</th>
                            <th>Code Postal</th>
                            <th>Jours</th>
                            <th>Horaires</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="centre" items="${centres.listCentre}">
                        <input type="hidden" name="nomCentre" value="${centre.nomCentre}" />
                        <input type="hidden" name="codePostal" value="${centre.codePostal}" />
                        <input type="hidden" name="role" value="Responsable general" />
                        <tr>
                            <td>${centre.nomCentre}</td>
                            <td>${centre.adresse}</td>
                            <td>${centre.codePostal}</td>
                            <td>${centre.jourSemaineOuverture}</td>
                            <td>de ${centre.heureOuverture} à ${centre.heureFermeture}</td>
                            <td>
                                <button class="btn btn-primary">Voir les stats</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                    </form>

        </div>     
    <%@ include file="/WEB-INF/footer.jsp" %>

</body>
</html>
