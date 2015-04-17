<%-- 
    Document   : monCompte
    Created on : 9 avr. 2015, 16:14:29
    Author     : oswald
--%>

<%@page import="java.util.List"%>
<%@page import="modele.Dossier"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Site du théâtre ***</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

    </head>
    <body>
        <jsp:include page="jsp/navbar.jsp"/>
        <h2>Mes Places achetées : </h2><br>
        *liste des places achetées avec leurs caractéristiques (n° place, spectacle, etc)*

        <div class="container" >
            <div class="row">
                <div class="col-md-2">
                    <label>Spectacle</label><br>
                    <label>Nombre de places</label><br>
                </div>
                <div class="col-md-2"> 
                    <% if (request.getAttribute("dossiers") != null) {
                            List<Dossier> dossiers = (List<Dossier>) request.getAttribute("dossiers");
                    %>
                    <% if (!dossiers.isEmpty()) {
                            for (int i = 0; i < dossiers.size(); i++) {%>
                            <p value="<%=dossiers.get(i).getNSP()%>"><%=dossiers.get(i).getNSP()%></p>
                            <p value="<%=dossiers.get(i).getNbP()%>"><%=dossiers.get(i).getNbP()%></p>
                    <%}
                            }
                        }%>
                </div>
            </div>
        </div>
        <h2>Mes Places réservées : </h2><br>
        *liste des places réservées avec le 'timer' de fin de réservation, et un bouton 'payer la réservation'*
    </body>
    <jsp:include page="jsp/footer.jsp"/>

    <jsp:include page="jsp/head.jsp"/>

    <link rel="stylesheet" type="text/css" href="css/style.css">
</html>
