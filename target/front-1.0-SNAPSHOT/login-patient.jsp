
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Authentification patient</title>
        <%@ include file="/WEB-INF/bootstrap.jsp" %>
        
          <style>
        body {
            background: url('static/img/patient.jpg') no-repeat center center fixed;
            
            background-size: cover;
        }
        .card {
            background: rgba(255, 255, 255, 0.8);
            border-radius: 10px;
        }
        .form-control, .btn-primary {
            border-radius: 5px;
        }
        .btn-primary {
            width: 100%;
            background-color: #007bff;
            border: none;
        }
    </style>

    </head>
    <body>
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Login</h4>
                            <form action="login" method="post">
                                <div class="form-group">
                                    <label for="numeroNational">Numéro de registre national:</label>
                                    <input type="text" class="form-control" id="numeroNational" name="numeroNational" required>
                                </div>
                                <div class="form-group">
                                    <label for="codeSecret">Mot de passe:</label>
                                    <input type="password" class="form-control" id="codeSecret" name="codeSecret" required>
                                </div>
                                <button type="submit" class="btn btn-primary" value="Login">Connexion</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
      <c:if test="${not empty errorMessage and errorMessage != ''}">
    <div class="alert alert-danger">${errorMessage}</div>
</c:if>


    </body>
</html>
