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

        <script>
            $(function () {
                dateToday = new Date();
                $("#datepicker").datepicker({
                    dateFormat: "dd-mm-yy",
                    minDate: dateToday
                });
            });
        </script>
    </head>
    <body>
        <jsp:include page="jsp/navbar.jsp"/>

        <div class="container" >
            <form action="<%=request.getContextPath()%>/controleur" method="get">
                <div class="row">
                    <div class="col-md-2">
                        <p><b>Spectacle</b></p>
                        <p><b>Salle</b></p>
                        <p><b>Date</b></p> 
                        <p><b>Heure</b></p>
                    </div>
                    <div class="col-md-2"> 

                        <select id="selectSpect" onchange="
                                select_menu = document.getElementById('selectSpect');
                                document.getElementById('valueSpect').value = select_menu.options[select_menu.selectedIndex].value;" >
                            <% if (request.getAttribute("spectacles") != null) {
                                    List<Spectacle> spectacles = (List<Spectacle>) request.getAttribute("spectacles");
                                    if (!spectacles.isEmpty()) {
                                        for (int i = 0; i < spectacles.size(); i++) {%>
                            <option value="<%=spectacles.get(i).getId()%>"><%=spectacles.get(i).getTitre()%></option>
                            <%}%>
                        </select><br>
                        <input id="valueSpect" name="valueSpect" value="<%=spectacles.get(0).getId()%>" hidden="true">
                        <%
                                }
                            }%>


                        <select id="selectSalle" onchange="
                                select_menu = document.getElementById('selectSalle');
                                document.getElementById('valueSalle').value = select_menu.options[select_menu.selectedIndex].value;">
                            <% if (request.getAttribute("salles") != null) {
                                    List<Salle> salles = (List<Salle>) request.getAttribute("salles");
                            %>
                            <% if (!salles.isEmpty()) {
                                    for (int i = 0; i < salles.size(); i++) {%>
                            <option value="<%=salles.get(i).getNSa()%>"><%=salles.get(i).getNomSa()%></option>
                            <%}%>
                        </select> 
                        <input id="valueSalle" name="valueSalle" value="<%=salles.get(0).getNSa()%>" hidden="true">
                        <%
                                }
                            }%>


                        <input type="text" id="datepicker" name="date">

                        <select id="selectHeure" onchange="
                                select_menu = document.getElementById('selectHeure');
                                document.getElementById('valueHeure').value = select_menu.options[select_menu.selectedIndex].value;">
                            <% for (int i = 10; i < 24; i++) {%>
                            <option value="<%=i%>:00"><%=i%>:00</option>
                            <option value="<%=i%>:30"><%=i%>:30</option>
                            <%}%>
                        </select> 
                        <input id="valueHeure" name="valueHeure" value="10:00" hidden="true">
                    </div>
                </div>
                <div class="row">
                    <input type="SUBMIT">
                </div>
                <input hidden="true" name="action" value="addRepres"/>
            </form>
        </div>
    </body>

    <jsp:include page="jsp/footer.jsp"/>

</html>
