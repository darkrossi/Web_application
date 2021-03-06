<%-- 
    Document   : addSalle
    Created on : 13 avr. 2015, 17:23:24
    Author     : oswald
--%>

<%@page import="modele.Salle"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% HttpSession session2 = request.getSession(false);
    String userName = (String) session2.getAttribute("utilisateur");
    if (userName == null) {
        String site = new String("./loading.jsp");
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <jsp:include page="jsp/head.jsp"/>

        <link rel="stylesheet" type="text/css" href="css/style.css"> 
    </head>
    <body>
        <jsp:include page="jsp/navbar.jsp"/>


        <div class="container" >
            <div class="row">
                <div class="col-md-3">
                    <p><b>Nom de la salle</b></p>
                    <p><b>Nombre de rang</b></p>
                    <p><b>Nombre de places par rang</b></p> 
                </div>
                <% if (request.getAttribute("salles") != null) {
                        List<Salle> salles = (List<Salle>) request.getAttribute("salles");
                        if (!salles.isEmpty()) {
                            for (int i = 0; i < salles.size(); i++) {%>
                <div class="col-md-2">

                    <p><%=salles.get(i).getNomSa() %></p>
                    <p><%=salles.get(i).getNbRa()%></p>
                    <p><%=salles.get(i).getNbP()%></p>

                </div>
                <%}
                        }
                    }%>


            </div>

            <hr>

            <div class="row">
                <div class="col-md-3">
                    <p><b>Nom de la salle </b></p>
                    <p><b>Nombre de rang Poulailler </b></p>
                    <p><b>Nombre de rang Balcon </b></p>
                    <p><b>Nombre de rang Orchestre </b></p>
                    <p><b>Nombre de places par rang </b></p>
                </div>
                <div class="col-md-4">
                    <form action="<%=request.getContextPath()%>/controleur" method="get">
                        <input type="text" name="nomSalle" value=""><br>
                        <input type="number" name="nbRaP" value="0"><br>
                        <input  type="number" name="nbRaB" value="0"><br>
                        <input type="number" name="nbRaO" value="0"><br>
                        <input type="number" name="nbP" value="1"><br>
                        <button type="submit "> Ajouter salle </button><br>
                        <input hidden="true" name="action" value="addSalle"><br>
                    </form>
                </div>
            </div>
        </div>
    </body>

    <jsp:include page="jsp/footer.jsp"/>

</html>
