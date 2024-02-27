
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Centre de Vaccination</title>

        <!-- CSS de Bootstrap -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

        <style>

            body {
                font-family: 'Arial', sans-serif;
                background-color: #f4f4f4;
            }

            .hero-section {
                background: linear-gradient(rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.6)), url('https://cdn.pixabay.com/photo/2021/01/17/22/03/vaccine-5926664_1280.jpg') no-repeat center center;
                background-size: cover;
                height: 100vh;
            }

            .hero-card {
                background-color: rgba(255, 255, 255, 0.8);
                padding: 2rem;
                border-radius: 10px;
            }
        </style>

        <!-- JS, Popper.js, et jQuery pour Bootstrap -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body>

        <!-- Section -->
        <div class="hero-section d-flex justify-content-center align-items-center">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-6">
                        <div class="hero-card">
                            <h1>Bienvenue au Centre de Vaccination</h1>
                            <p>Veuillez choisir votre mode d'accès :</p>

                            <!-- Alignement horizontal des boutons -->
                            <div class="d-flex justify-content-between">
                                <!-- Bouton pour la page de login des patients -->
                               <form action="showLoginPatient" method="post" class="me-2">
                                    <input type="submit" value="Login Patient" class="btn btn-primary btn-lg" />
                                </form>

                                <!-- Bouton pour la page de login des professionnels -->
                                  <form action="showLoginPro" method="post">
                                    <input type="submit" value="Login Professionnel" class="btn btn-secondary btn-lg" />
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!-- Pied de page -->
        <footer class="text-center py-4">
            <p>&copy; 2023 Centre de Vaccination. Tous les droits sont réservés.</p>
        </footer>
    </body>
</html>
