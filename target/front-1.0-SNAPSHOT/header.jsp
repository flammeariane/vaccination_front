<!DOCTYPE html>
<html>
    <head>
        <title>Votre Application - Centre de Vaccination</title>
        <%@ include file="/WEB-INF/bootstrap.jsp" %>
        <style>
            #header {
                background: url('static/img/banner1.jpg') no-repeat center center;

                background-size: cover;
                background-position: top;
                color: black;
                text-align: center;
                padding: 5rem 0;
                position: relative;
            }

            #header h1 {
                color: whitesmoke;
                margin: 0;
                font-size: 2.5em;
            }

            .logout-button {
                position: absolute;
                bottom: 10px;
                right: 10px;
            }
        </style>
    </head>
    <body>

        <div id="header">
            <h1>Centre de Vaccination</h1>
            <form action="logout" method="post" class="logout-button">
                <button type="submit" class="btn btn-danger">Déconnexion</button>
            </form>
            <p class="">Nous somme ouvert tous les jours de 09h00 à 17h00</p>
        </div>

    </body>
</html>
