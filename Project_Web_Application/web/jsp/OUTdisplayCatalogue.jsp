<%-- 
    Document   : OUTdisplayBooking
    Created on : 24 avr. 2015, 14:52:09
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

<script>
    $(".idButton").click(function () {
        $('#' + $(this).attr('data-hide-id')).reveal($(this).data()).trigger('reveal:close');
    });
</script>

<form name="form" action="<%=request.getContextPath()%>/controleur" method="get">
    <% if (request.getAttribute("spectacles") != null) {
            List<Spectacle> spectacles = (List<Spectacle>) request.getAttribute("spectacles");
            if (!spectacles.isEmpty()) {
                for (int i = 0; i < spectacles.size(); i++) {
                    String nomS = spectacles.get(i).getTitre();%>
    <div class="row" style="margin-top: 10px;">
        <div class="col-md-4">
            <img data-reveal-id="myModal<%=spectacles.get(i).getId()%>" src="img/<%=spectacles.get(i).getUrl()%>" data-large="img/<%=spectacles.get(i).getUrl()%>" alt=""
                 style="width:150px;"/>
        </div>
        <div class="col-md-6"> 
            <h1> <%= nomS%> </h1>
            <ul>
                <li>Quelques infos sur le spectacle</li>
                <li>
                    <button onclick="$('#NSp').attr('value', '<%=spectacles.get(i).getId()%>'); document.form.submit();"> 
                        Réserver <span class="glyphicon glyphicon-arrow-down"></span>
                    </button>
                </li>
            </ul>
        </div>
    </div>
                        
    <!-- POPUP INFOS SPECTACLE -->
    <div id="myModal<%=spectacles.get(i).getId()%>" class="reveal-modal">
        <div class="container" >
            <div class="row" style="margin-top: 10px;">
                <div class="col-md-2">
                    <img src="img/<%=spectacles.get(i).getUrl()%>" data-large="img/<%=spectacles.get(i).getUrl()%>" alt=""
                         style="width:150px;"/>
                </div>
                <div class="col-md-6"> 
<!--                    <a href="#" data-hide-id="myModal<%=spectacles.get(i).getId()%>" class="idButton"><span class="glyphicon glyphicon-remove"></span> QUITTER CETTE PAGE</a>-->
                    <h1> <%= nomS%> </h1>
                    <!--Mettre les infos détaillées du spectacle-->
                    <ul>
                        <li>Auteur : <%=spectacles.get(i).getAuteur()%></li>
                        <li>Metteur en scéne : <%=spectacles.get(i).getMetteurEnScene()%></li>
                        <li>Durée : <%=spectacles.get(i).getDuree()%></li>
                        <li><button onclick="$('#NSp').attr('value', '<%=spectacles.get(i).getId()%>');
                                document.form.submit();">
                                Réserver <span class="glyphicon glyphicon-arrow-down"></span>
                            </button>
                        </li>
                        <!--Quelques infos sur le spectacle-->
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <% }
            }
        }%>

    <input hidden="true" name="action" value="displayResa">
    <input id="NSp" name="NSp" value="0" hidden="true">
    <input name="login" value="<%= userName%>" hidden="true">
</form>

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
