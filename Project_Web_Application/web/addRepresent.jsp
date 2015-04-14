<%-- 
    Document   : addRepresent
    Created on : 13 avr. 2015, 16:13:40
    Author     : oswald
--%>

<%@page import="modele.Salle"%>
<%@page import="dao.SalleDAO"%>
<%@page import="modele.Spectacle"%>
<%@page import="java.util.List"%>
<%@page import="dao.SpectacleDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title> 

        <script>
            $(function () {
                $("#datepicker").datepicker();
            });
        </script>
    </head>
    <body>
        <jsp:include page="jsp/navbar.jsp"/>

        <div class="container" >
            <form action="<%=request.getContextPath()%>/controleur" method="get">
                <div class="row">
                    <div class="col-md-2">
                        <label>Spectacle</label><br>
                        <label>Salle</label><br>
                        <label>Date</label><br> 
                        <label>Heure</label><br>
                        <label>Nombre de place</label>
                    </div>
                    <div class="col-md-2"> 

                        <select id="selectSpect" onchange="
                                select_menu = document.getElementById('selectSpect');
                                document.getElementById('valueSpect').value = select_menu.options[select_menu.selectedIndex].value;" >
                            <% SpectacleDAO spectacle = new SpectacleDAO();
                                List<Spectacle> spectacles = spectacle.getListeSpectaclesMenu();%>
                            <% if (!spectacles.isEmpty()) {
                                    for (int i = 0; i < spectacles.size(); i++) {%>
                            <option value="<%=spectacles.get(i).getId()%>"><%=spectacles.get(i).getTitre()%></option>
                            <%}
                                }%>
                        </select> 
                        <input type="text" id ="valueSpect" name="valueSpect" hidden="true"> <br>

                        <select id="selectSalle" onchange="
                                select_menu = document.getElementById('selectSalle');
                                document.getElementById('valueSalle').value = select_menu.options[select_menu.selectedIndex].value;">
                            <% SalleDAO salle = new SalleDAO();
                                List<Salle> salles = salle.getListeSalles();%>
                            <% if (!salles.isEmpty()) {
                                    for (int i = 0; i < salles.size(); i++) {%>
                            <option value="<%=salles.get(i).getNSa()%>"><%=salles.get(i).getNSa()%></option>
                            <%}
                                }%>
                        </select> 
                        <input id="valueSalle" name="valueSalle" hidden="true">

                        <input type="text" id="datepicker" name="date">

                        <select id="selectHeure" onchange="
                                select_menu = document.getElementById('selectHeure');
                                document.getElementById('valueHeure').value = select_menu.options[select_menu.selectedIndex].value;">
                            <% for (int i = 10; i < 24; i++) {%>
                            <option value="<%=i%>:00"><%=i%>:00</option>
                            <option value="<%=i%>:30"><%=i%>:30</option>
                            <%}%>
                        </select> 
                        <input id="valueHeure" name="valueHeure" hidden="true">

                        <input type="text" name="nbP">
                    </div>
                </div>
                <div class="row">
                    <input type="SUBMIT">
                </div>
                <input hidden="true" name="action" value="addRepres"/>
            </form>
    </body>

    <jsp:include page="jsp/footer.jsp"/>

    <jsp:include page="jsp/head.jsp"/>

    <link rel="stylesheet" type="text/css" href="css/style.css"> 
</html>
