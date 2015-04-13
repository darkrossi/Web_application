<%-- 
    Document   : addSalle
    Created on : 13 avr. 2015, 17:23:24
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Ajout Salle</title>

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
                <form action="<%=request.getContextPath()%>/controleur" method="get">
                    <button type="submit "> Ajouter salle </button>
                    <input hidden="true" name="action" value="addSalle"/>
                </form>
        </body>

        <jsp:include page="jsp/footer.jsp"/>

    </html>
