<%-- 
    Document   : monCompte
    Created on : 9 avr. 2015, 16:14:29
    Author     : oswald
--%>

<%@page import="java.util.List"%>
<%@page import="modele.Dossier"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% HttpSession session2 = request.getSession(false);
    String userName = (String) session2.getAttribute("utilisateur");%>

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

        <div class="container" >
            <div class="row">
                <div class="col-md-2">
                    <label>Spectacle</label><br>
                    <label>Date</label><br>
                    <label>Heure</label><br>
                    <label>Nombre de places</label><br>
                </div>

                <% if (request.getAttribute("dossiers") != null) {
                        List<Dossier> dossiers = (List<Dossier>) request.getAttribute("dossiers");
                        if (!dossiers.isEmpty()) {
                            for (int i = 0; i < dossiers.size(); i++) {%>
                <div class="col-md-2">
                    <p><%=dossiers.get(i).getNomSpectacle()%></p>
                    <p><%=dossiers.get(i).getDate()%></p>
                    <p><%=dossiers.get(i).getHeure()%></p>
                    <p><%=dossiers.get(i).getNbP()%></p>
                </div>
                <%}
                        }
                    }%>

            </div>
        </div>
        <h2>Mes Places réservées : </h2><br>
        *liste des places réservées avec le 'timer' de fin de réservation, et un bouton 'payer la réservation'*
    </body>
    <jsp:include page="jsp/footer.jsp"/>

    <jsp:include page="jsp/head.jsp"/>

    <link rel="stylesheet" type="text/css" href="css/style.css">
</html>
