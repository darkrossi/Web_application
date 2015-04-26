<%-- 
    Document   : addBooking
    Created on : 18 avr. 2015, 09:02:56
    Author     : oswald
--%>

<%@page import="modele.Spectacle"%>
<%@page import="modele.Place"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@page import="modele.Representation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% HttpSession session2 = request.getSession(false);
    String userName = (String) session2.getAttribute("utilisateur");%>

<% if (request.getAttribute("spectacle") != null) {
        Spectacle spect = (Spectacle) request.getAttribute("spectacle");
        String nomS = spect.getTitre();
%>

<div class="row" style="margin-top: 10px;">
    <div class="col-md-4">
        <img src="img/<%=spect.getUrl()%>" data-large="img/<%=spect.getUrl()%>" alt="" style="width:150px;"/>
    </div>
    <div class="col-md-6"> 
        <h1> <%= nomS%> </h1>
        <p>  <%= spect.getInfos()%> </p>
        <p>  <%= spect.getNote()%> </p>

        <!--Mettre les infos détaillées du spectacle-->
        <ul>
            <li>Auteur : <%=spect.getAuteur()%></li>
            <li>Metteur en scène : <%=spect.getMetteurEnScene()%></li>
            <li>Durée : <%=spect.getDuree()%> mins</li>
            <li>Comédiens : <%=spect.getComediens()%> </li>
            <!--Quelques infos sur le spectacle-->
        </ul>
        <ul>
            <% if (request.getAttribute("repres") != null) {
                    List<Representation> repres = (List<Representation>) request.getAttribute("repres");
                    if (!repres.isEmpty()) {
                        for (int i = 0; i < repres.size(); i++) {%>                                       
            <li>
                <%= repres.get(i).getDate()%> - <%= repres.get(i).getHeure()%>
                <input type="checkbox" onclick="document.inputForm6<%=repres.get(i).getNR()%>.submit();">
                <form action="<%=request.getContextPath()%>/controleur" method="get" name="inputForm6<%=repres.get(i).getNR()%>">
                    <input name="action" value="displayResaPlaces" hidden="true">
                    <input name="NR" value="<%= repres.get(i).getNR()%>" hidden="true">
                    <input name="NSa" value="<%= repres.get(i).getNSa()%>" hidden="true">
                    <input name="NSp" value="<%= repres.get(i).getNSp()%>" hidden="true">
                </form>
            </li>
            <%}
                    }
                }%>
        </ul>
    </div>
</div>

<button type="submit" >Réserver <span class="glyphicon glyphicon-arrow-down"></span></button>
<input hidden="true" name="action" value="addBooking">
<input name="login" value="<%= userName%>" hidden="true">
<% }%>

<%-- <% if (request.getAttribute("rangs") != null) {%>
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
                        <option value="<%=places.get(j).getNP()%>"<%=j + 1%></option>
                        <%}%>
                    </select><br>
                    <input id="valuePlace<%=NRa%>" name="valuePlace<%=NRa%>" value="<%=places.get(0).getNP()%>" hidden="true">
                    <%
                                }
                            }
                        }%> --%>