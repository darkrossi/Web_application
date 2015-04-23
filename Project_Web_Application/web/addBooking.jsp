<%-- 
    Document   : addBooking
    Created on : 18 avr. 2015, 09:02:56
    Author     : oswald
--%>

<%@page import="modele.Place"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@page import="modele.Representation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% HttpSession session2 = request.getSession(false);
    String userName = (String) session2.getAttribute("utilisateur");%>

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
            <form action="<%=request.getContextPath()%>/controleur" method="get">
                <% if (request.getAttribute("repres") != null) {
                        Hashtable<String, Representation> hashRepres = (Hashtable<String, Representation>) request.getAttribute("repres");
                        if (!hashRepres.isEmpty()) {
                            Enumeration keys = hashRepres.keys();
                            while (keys.hasMoreElements()) {
                                String nomS = (String) keys.nextElement();
                                List<Representation> repres = (List<Representation>) hashRepres.get(nomS);%>
                <div class="row" style="margin-top: 10px;">
                    <div class="col-md-2">
                        <img src="img/<%=repres.get(0).getUrlImg()%>" data-large="img/<%=repres.get(0).getUrlImg()%>" alt=""
                             style="width:150px;"/>
                    </div>
                    <div class="col-md-6"> 
                        <h1> <%= nomS%> </h1>
                        <ul>
                            <% for (int i = 0; i < repres.size(); i++) {%>                                       
                            <li>
                                <%= repres.get(i).getDate()%> - <%= repres.get(i).getHeure()%>
                                <input type="number" name="nbP" value="0">
                                <input type="checkbox" name="cbNR" value="<%=repres.get(i).getNR()%>">
                                <% if (request.getAttribute("rangs") != null) {%>
                                Rang : <select id="selectRang" onchange="
                                        select_menu = document.getElementById('selectRang');
                                        select_menu_value = select_menu.options[select_menu.selectedIndex].value;
                                        document.getElementById('valueRang').value = select_menu_value;
                                        $('#place' + select_menu_value).show();" >
                                    <% Hashtable<Integer, Place> hashRangs = (Hashtable<Integer, Place>) request.getAttribute("repres");
                                        if (!hashRangs.isEmpty()) { // Si il y a des rangs
                                            Enumeration keys2 = hashRangs.keys();
                                            int counter = 1;
                                            while (keys2.hasMoreElements()) { // On affiche le menu déroulant avec les rangs
                                                Integer NRa = (Integer) keys2.nextElement();%>
                                    <option value="<%=NRa%>"><%=counter++%></option>
                                    <%}%>
                                </select><br>
                                <input id="valueRang" name="valueRang" value="1" hidden="true">


                                <% keys2 = hashRangs.keys();
                                    while (keys2.hasMoreElements()) {
                                        Integer NRa = (Integer) keys2.nextElement();
                                        List<Place> places = (List<Place>) hashRangs.get(NRa);%>
                                Place : <select id="selectPlace<%=NRa%>" onchange="
                                        select_menu = document.getElementById('selectPlace<%=NRa%>');
                                        document.getElementById('valuePlace<%=NRa%>').value = select_menu.options[select_menu.selectedIndex].value;" >
                                    <% for (int j = 0; j < places.size(); j++) {%>
                                    <option value="<%=places.get(j).getNP()%>"<%=j+1%></option>
                                    <%}%>
                                </select><br>
                                <input id="valuePlace<%=NRa%>" name="valuePlace<%=NRa%>" value="<%=places.get(0).getNP()%>" hidden="true">
                                <%
                                            }
                                        }
                                    }%>
                            </li>
                            <%}%>
                        </ul>
                    </div>
                </div>
                <% }
                        }
                    }%>

                <button type="submit" >Réserver <span class="glyphicon glyphicon-arrow-down"></span></button>
                <input hidden="true" name="action" value="addBooking"/>
                <input name="login" value="<%= userName%>" hidden="true">
            </form>
        </div>
    </body>

    <jsp:include page="jsp/footer.jsp"/>

</html>
