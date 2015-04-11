<%-- 
    Document   : login
    Created on : 9 avr. 2015, 10:12:00
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Site du théâtre *** - Login page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <jsp:include page="jsp/head.jsp"/>

        <link rel="stylesheet" type="text/css" href="css/style.css"> 

    </head>
    <body>

        <jsp:include page="jsp/navbar.jsp"/>

        <!-- CONTAINER BOOTSTRAP -->
        <div class="container" >

            <div class="jumbotron">
                <h1>Connectez-vous à votre compte</h1>    
                <FORM ACTION="<%=request.getContextPath()%>/controleur" METHOD="get">
                    <div class="row">
                        <label class="col-md-3"> Indiquez votre login : </label><input class="col-md-2" name="loginU"><br>
                    </div>
                    <div class="row">
                        <label class="col-md-3"> Indiquez votre mot de passe : </label><input class="col-md-2" name="mdpU" type="PASSWORD"><br>
                    </div>
                    <input type="SUBMIT">
                    <input hidden="true" name="action" value="verifUser">

                </FORM>
                <br>
                <a href="index.jsp">Retour à l'accueil</a>   
            </div>
            <div class="btn-group">
                <button type="button" class="btn btn-primary">Apple</button>
                <button type="button" class="btn btn-primary">Samsung</button>
                <button type="button" class="btn btn-primary">Sony</button>
            </div>
        </div>
    </body>
    
    <jsp:include page="jsp/footer.jsp"/>

</html>
