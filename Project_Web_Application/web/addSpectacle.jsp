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

        <jsp:include page="jsp/head.jsp"/>
        <link rel="stylesheet" type="text/css" href="css/style.css"> 

        <script>
            // Fonction à compléter par Hoël pour la vérif des champs
            function isValid() {
                if (document.getElementById("nomS").value !== "" &&
                        document.getElementById("auteurS").value !== "" &&
                        document.getElementById("mesS").value !== "" &&
                        document.getElementById("dureeS").value !== "" &&
                        document.getElementById("infoS").value !== "")
                        {
                        
                    return true; // Si on ne veut pas lancer la servlet
                }
                return false;
            }
        </script>

    </head>
    <body>
        <jsp:include page="jsp/navbar.jsp"/>

        <div class="container" >
            <form action="<%=request.getContextPath()%>/controleur" method="get">
                <div class="row">
                    <div class="col-md-2">
                        <p><b>Nom</b></p>
                        <p><b>Auteur</b></p>
                        <p><b>Metteur en scéne</b></p> 
                        <p><b>Informations</b></p>
                        <p><b>Durée</b></p>
                        <p><b>Image </b></p>
                        
                    </div>
                    <div class="col-md-2"> 
                        <input type="text" id="nomS" name="nomS"> 
                        <input type="text" id="auteurS" name="auteurS"> 
                        <input type="text" id="mesS" name="mesS"> 
                        <input type="text" id="infoS" name="infoS"> 
                        <input type="number" id="dureeS" name="dureeS">
                        <input type="file" id="fileS" name="fileS">
                    </div>
                </div>
                <div class="row">
                    <input type="SUBMIT">
                </div>
                <input hidden="true" name="action" value="addS">
            </form>
        </div>
    </body>

    <jsp:include page="jsp/footer.jsp"/>

</html>
