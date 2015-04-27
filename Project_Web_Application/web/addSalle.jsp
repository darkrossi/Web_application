<%-- 
    Document   : addSalle
    Created on : 13 avr. 2015, 17:23:24
    Author     : oswald
--%>

<%@page import="modele.Salle"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <p><b>N° salle</b></p>
                    <p><b>Nombre de rang</b></p>
                    <p><b>Prix du rang</b></p>
                    <p><b>Nombre de places par rang</b></p> 
                </div>
                <% if (request.getAttribute("salles") != null) {
                        List<Salle> salles = (List<Salle>) request.getAttribute("salles");
                        if (!salles.isEmpty()) {
                            for (int i = 0; i < salles.size(); i++) {%>
                <div class="col-md-2">

                    <p><%=salles.get(i).getNSa()%></p>
                    <p><%=salles.get(i).getNbRa()%></p>
                    <p><%=salles.get(i).getCatTarif()%></p>
                    <p><%=salles.get(i).getNbP()%></p>

                </div>
                <%}
                        }
                    }%>


            </div>

            <div class="row">

                <div class="col-md-6">
                    <div class="col-md-4">
                        <p><b>Nombre de rang</b></p>
                        <p><b>Tarif du rang</b></p>
                        <p><b>Nombre de places par rang</b></p>
                    </div>
                    <div class="col-md-2">
                        <form action="<%=request.getContextPath()%>/controleur" method="get">
                            <input type="number" name="nbRa" value="1"><br>
                            <input type="number" name="catTarif" value="1"><br>
                            <input type="number" name="nbP" value="1"><br>
                            <button type="submit "> Ajouter salle </button><br>
                            <input hidden="true" name="action" value="addSalle"><br>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>

    <jsp:include page="jsp/footer.jsp"/>

</html>
