<%-- 
    Document   : afficheBdd
    Created on : 14 avr. 2015, 18:55:36
    Author     : oswald
--%>

<%@page import="modele.Representation"%>
<%@page import="dao.RepresentationDAO"%>
<%@page import="modele.Spectacle"%>
<%@page import="java.util.List"%>
<%@page import="dao.SpectacleDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <jsp:include page="jsp/head.jsp"/>

        <link rel="stylesheet" type="text/css" href="css/style.css"> 
        <script src="js/functions.js" type="text/javascript"></script>

        <script>
            $(document).ready(function () {
                $("button").click(function () {
                    $("#" + this.value).toggle();
                });
            });
        </script>
    </head>
    <body>
        <jsp:include page="jsp/navbar.jsp"/>

        <div class="container">
            <div class="row">
                <button value="spect">Liste des spectacles</button>
                <button value="repres">Liste des représentations par spectacle</button>
                <button value="salles">Liste des salles</button>
                <button value="users">Liste des utilisateurs</button>
                <button value="fields">Liste des dossiers</button>
            </div>
        </div>

        <div class="container" id="spect" hidden="true">
            <div class="row">
                <% SpectacleDAO spectacle = new SpectacleDAO();
                    List<Spectacle> spectacles = spectacle.getListeSpectaclesMenu();%>
                <% if (!spectacles.isEmpty()) {
                        for (int i = 0; i < spectacles.size(); i++) {%>
                <p><%=spectacles.get(i).getTitre()%></p>
                <%}
                    }%>
            </div>
        </div>

        <div class="container" id="repres" hidden="true">
            <div class="row">
<%--                <select id="selectSpect" onchange="
                        select_menu = document.getElementById('selectSpect');
                        document.getElementById('valueSpect').value = select_menu.options[select_menu.selectedIndex].value;" >
                    <% spectacle = new SpectacleDAO();
                        spectacles = spectacle.getListeSpectaclesMenu();%>
                    <% if (!spectacles.isEmpty()) {
                            for (int i = 0; i < spectacles.size(); i++) {%>
                    <option value="<%=spectacles.get(i).getId()%>"><%=spectacles.get(i).getTitre()%></option>
                    <%}
                        }%>
                </select> --%>
                <input type="text" id ="valueSpect" name="valueSpect" hidden="true"><br>

<%--                <% RepresentationDAO representation = new RepresentationDAO();
                    List<Representation> representations = representation.getListeRepres(1);%>
                <% if (!spectacles.isEmpty()) {
                        for (int i = 0; i < spectacles.size(); i++) {%>
                <p><%=spectacles.get(i).getTitre()%></p>
                <%}
                    }%> --%>
            </div>
        </div>

    </body>

    <jsp:include page="jsp/footer.jsp"/>

</html>
