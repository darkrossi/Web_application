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
        
        <link rel="stylesheet" type="text/css" href="css/style.css"> 

        <jsp:include page="jsp/head.jsp"/>

    </head>
    <body>

        <jsp:include page="jsp/navbar.jsp"/>

        <!-- CONTAINER BOOTSTRAP -->
        <div class="container" >

            <div class="jumbotron">
                <h1>Connectez-vous à votre compte</h1>    
                <FORM ACTION="<%=request.getContextPath()%>/controleur" METHOD="get">
                    <label> Indiquez votre login : </label><input name="loginU"><br>
                    <label> Indiquez votre mot de passe : </label><input name="mdpU" type="PASSWORD"><br>
                    <input type="SUBMIT" name="action" value="verifUser">
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

        <a href="error.jsp">Error</a>
        <a href="monCompte.html">Mon Compte</a>

    </body>
</html>
