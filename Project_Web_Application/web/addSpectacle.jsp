<%-- 
    Document   : addSpectacle
    Created on : 8 avr. 2015, 09:21:20
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajout Spectacle</title>

        <link rel="stylesheet" type="text/css" href="css/style.css"> 
        
        <jsp:include page="jsp/head.jsp"/>
        
        <script>
            // Fonction à compléter par Hoël pour la vérif des champs
            function isValid() {
                if (document.getElementById("nomS").value != "" &&
                        document.getElementById("auteurS").value != "" &&
                        document.getElementById("mesS").value != "" &&
                        document.getElementById("dureeS").value != "") {

                    return true; // Si on ne veut pas lancer la servlet
                }
                return false;
            }
        </script>

    </head>
    <body>
        <jsp:include page="jsp/navbar.jsp"/>

        <div class="container" >
            <form action="<%=request.getContextPath()%>/controleur" onsubmit="return isValid()" method="get">
                <div class="row">
                    <div class="col-md-2">
                        <label>Nom</label><br>
                        <label>Auteur</label><br>
                        <label>Metteur en scéne</label><br> 
                        <label>Durée</label><br>
                        <label>Image </label>
                    </div>
                    <div class="col-md-2"> 
                        <input id="nomS" name="nomS"> 
                        <input id="auteurS" name="auteurS"> 
                        <input id="mesS" name="mesS"> 
                        <input id="dureeS" name="dureeS"> 
                        <input type="file" id="fileS" name="fileS">
                    </div>
                </div>
                <div class="row">
                    <input type="SUBMIT">
                </div>
                <input hidden="true" name="action" value="addS"/>
            </form>
    </body>
</html>
