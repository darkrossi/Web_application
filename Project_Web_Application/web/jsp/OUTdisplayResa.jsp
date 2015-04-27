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
        <p>  <i> <font color=#787878><%= spect.getInfos()%></font></i> </p>

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
<% }%>