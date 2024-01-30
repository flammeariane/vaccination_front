<!DOCTYPE html>
<html>
    <head>
        <title>Votre Application - Centre de Vaccination</title>
        <%@ include file="/WEB-INF/bootstrap.jsp" %>
        <style>
            #header {
                background: url('static/img/banner1.jpg') no-repeat center center;
                background-size: cover;
                background-position: center; /* Centré pour voir le milieu de l'image */
                color: black;
                text-align: center;
                padding: 10rem 0; /* Augmente la hauteur */
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
            <h1>Réseau des Centres de Vaccination National</h1>
          

        </div>

    </body>
</html>
