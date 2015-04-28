<%-- 
    Document   : pieces
    Created on : 8 avr. 2015, 10:39:44
    Author     : oswald
--%>

<%@page import="modele.Rang"%>
<%@page import="modele.Spectacle"%>
<%@page import="java.util.Enumeration"%>
<%@page import="modele.Place"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.Hashtable"%>
<%@page import="modele.Representation"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <jsp:include page="jsp/head.jsp"/>

        <script src="js/place.js" type="text/javascript"></script>
        <!--<script src="js/multiple.js" type="text/javascript"></script>-->
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/stylePieces.css" />

        <script>
            function init() {
                $(".datepicker").datepicker({
                    dateFormat: "dd-mm-yy"
                });
//                $("#genre").multiselect();
//                $("select").multiselect();
            }
        </script>

    </head>
    <body onload="init()">
        <?php
        include("php/panier.php");
        ?>
        <jsp:include page="jsp/navbar.jsp"/>

        <div class="container-fluid">
            <div class="row">
                <!-- FILTRES -->
                <div class="col-md-3">
                    <form name="form" action="<%=request.getContextPath()%>/controleur" method="post">
                        <% Spectacle spectacle = (Spectacle) request.getAttribute("spectacle");%>

                        <jsp:include page="jsp/OUTfiltreResa.jsp"/>
                        <input type="submit" value="Filtrer">
                        <input name="action" value="filtrerResa" hidden="true">
                        <input name="NSp" value="<%=spectacle.getId()%>" hidden="true">

                    </form>

                </div>

                <!-- SPECTACLE AVEC LISTE REPRESENTATIONS -->
                <div class="col-md-5">
                    <jsp:include page="jsp/OUTdisplayResa.jsp"/>
                </div>                

                <!-- LISTE PLACES DISPONIBLES POUR UNE REPRESENTATION -->
                <div class="col-md-4"> 
                    <h2 align="center"> Date choisie </h2>

                    <!-- DATE/HEURE -->
                    <div class="row">
                        <div class="col-md-3">
                            <% if (request.getAttribute("represPicked") != null) {

                                    Representation representation = (Representation) request.getAttribute("represPicked");%>
                            <p><font color="black"><%= representation.getDate()%></font></p>
                                <% }%>
                        </div>
                        <div class="col-md-3">
                            <% if (request.getAttribute("represPicked") != null) {
                                    Representation representation = (Representation) request.getAttribute("represPicked");%>
                            <p><font color="black"><%=representation.getHeure()%></font></p>
                                <% }%>
                        </div>
                    </div>

                    <!-- NB PLACES -->
                    <% if (request.getAttribute("nbPlRest") != null) {%>
                    <div class="row">
                        <div class="col-md-10">
                            <form action="<%=request.getContextPath()%>/controleur" method="get">
                                <p>Nombre de places : 
                                    <% if (request.getAttribute("nbPl") != null) {%>
                                    <input type="number" name="nbPl" value="<%=request.getAttribute("nbPl")%>">
                                    <% } else { %>
                                    <input type="number" name="nbPl" value="0">
                                    <%}%>
                                </p>
                                <p>(Nombre de places restantes : <%=(Integer) request.getAttribute("nbPlRest") - 70%>)</p>    
                                <input name="action" value="displayResaPlaces" hidden="true">
                                <input name="datepicker1" value="<%= request.getAttribute("datepicker1")%>" hidden="true">
                                <input name="datepicker2" value="<%= request.getAttribute("datepicker2")%>" hidden="true">
                                <input name="NR" value="<%= request.getAttribute("NR")%>" hidden="true">
                                <input name="NSa" value="<%= request.getAttribute("NSa")%>" hidden="true">
                                <input name="NSp" value="<%= request.getAttribute("NSp")%>" hidden="true">
                                <input name="NbPlRest" value="<%= request.getAttribute("nbPlRest")%>" hidden="true">
                                <button type="submit">Valider</button>
                            </form>
                        </div>
                    </div>
                    <%}%>


                    <script>
                        function onChangeRang(k) {
                            select_menu = document.getElementById('selectRang' + k);
                            select_menu_value = select_menu.options[select_menu.selectedIndex].value;
                            changeHiddenSelectPlace('selectPlace' + select_menu_value + "" + k, k);
                        }

                        function changeHiddenSelectPlace(idS, k) {
                            selects = document.getElementsByClassName("selectP" + k);
                            for (i = 0; i < selects.length; i++) {
                                id = selects[i].id;
                                if (id === idS) {
                                    $('#' + idS).show();
                                    $('#' + idS + "p").show();
                                } else {
                                    $('#' + id).hide();
                                    $('#' + id + "p").hide();
                                }
                            }
                        }
                    </script>

                    <!-- LISTE PLACES -->
                    <% if (request.getAttribute("rangs") != null) {
                            HttpSession session2 = request.getSession(false);
                            String userName = (String) session2.getAttribute("utilisateur");
                            int nbPlTemp = (Integer) request.getAttribute("nbPl"); %>
                    <script>
                        function afficheNext(nbPlTotal, nbPlTemp) {
                            $('#li' + (nbPlTotal - nbPlTemp)).hide();
                            $('#li' + (nbPlTotal - nbPlTemp + 1)).show();
                        <% nbPlTemp--; %>
                        }

                        function afficheBefore(nbPlTotal, nbPlTemp) {
                            $('#li' + (nbPlTotal - nbPlTemp)).hide();
                            $('#li' + (nbPlTotal - nbPlTemp - 1)).show();
                        <% nbPlTemp++; %>
                        }

                        function onclickFinalize(bool, nbPl) {
                        <% Representation representation = (Representation) request.getAttribute("represPicked");%>
                            NR = <%=representation.getNR()%>;
                            $('#NR' + bool).attr("value", NR);
                            NSP = <%=representation.getNSp()%>;
                            $('#NSp' + bool).attr("value", NSP);

                            for (i = 0; i < nbPl; i++) {
                                selectRang = document.getElementById('selectRang' + i);
                                selectRangValue = selectRang.options[selectRang.selectedIndex].value;
                                $('#NRa' + bool + "" + i).attr("value", selectRangValue);
                                selectPlace = document.getElementById('selectPlace' + selectRangValue + i);
                                selectPlaceValue = selectPlace.options[selectPlace.selectedIndex].value;
                                $('#NP' + bool + "" + i).attr("value", selectPlaceValue);
                            }
                        }
                    </script>
                    <div class="row">
                        <div class="large-2 columns">
                            <h3 align="center"> Choisissez vos places </h3>
                            <ul>
                                <% for (int k = 0; k < (Integer) request.getAttribute("nbPl"); k++) {
                                        if (k == 0) {%>
                                <li id="li<%=k%>">  
                                    <%} else {%>
                                <li hidden="true" id="li<%=k%>">  
                                    <%}%>
                                    <p> Place n° <%=k + 1%> sur <%=request.getAttribute("nbPl")%></p>
                                    N° de Rang : <select id="selectRang<%=k%>" onchange="onChangeRang(<%=k%>)" >
                                        <% Hashtable<Rang, List<Place>> hashRangs = (Hashtable<Rang, List<Place>>) request.getAttribute("rangs");
                                            if (!hashRangs.isEmpty()) { // Si il y a des rangs
                                                Enumeration keys2 = hashRangs.keys();
                                                int counter = 1;
                                                while (keys2.hasMoreElements()) { // On affiche le menu déroulant avec les rangs
                                                    Rang rang = (Rang) keys2.nextElement();%>
                                        <option value="<%=rang.getNRa()%>"><%=rang.getNomCT()%><%=counter++%></option>
                                        <%}%>
                                    </select><br>

                                    <% keys2 = hashRangs.keys();
                                        boolean isFirst = true;
                                        while (keys2.hasMoreElements()) {
                                            Rang rang = (Rang) keys2.nextElement();
                                            Integer NRa = rang.getNRa();
                                            List<Place> places = (List<Place>) hashRangs.get(rang);
                                            if (isFirst) {%>
                                    N° de Place : <select id="selectPlace<%=NRa%><%=k%>" class="selectP<%=k%>">
                                        <%
                                        } else {%>
                                        <select hidden="true" id="selectPlace<%=NRa%><%=k%>" class="selectP<%=k%>">
                                            <%}
                                                for (int j = 0; j < places.size(); j++) {
                                                    Place place = places.get(j);%>
                                            <option value="<%=place.getNP()%>"><%=place.getNumPl()%></option>
                                            <% }%>
                                        </select> 
                                        <%if (isFirst) {
                                                isFirst = false;%>
                                        <div id="selectPlace<%=NRa%><%=k%>p">(Prix : <%=rang.getPrixCT()%>€) (Places restantes : <%=places.size()%>)</div>
                                        <% } else {%>
                                        <div hidden="true" id="selectPlace<%=NRa%><%=k%>p">(Prix : <%=rang.getPrixCT()%>€) (Places restantes : <%=places.size()%>)</div>
                                        <%}%>
                                        <%
                                                }
                                            }%>

                                        <br>
                                        <% if (k == (Integer) request.getAttribute("nbPl") - 1) {%>

                                        <form action="<%=request.getContextPath()%>/controleur" onsubmit="onclickFinalize(1, <%=(Integer) request.getAttribute("nbPl")%>);
                                                return true;" method="post">
                                            <button type="submit">
                                                Réserver <span class="glyphicon glyphicon-arrow-down"></span>
                                            </button>

                                            <input id="NR1" name="NR" value="0" hidden="true">
                                            <%for (int l = 0; l < (Integer) request.getAttribute("nbPl"); l++) {%>
                                            <input id="NP1<%=l%>" name="NP<%=l%>" value="0" hidden="true">
                                            <%}%>
                                            <input id="NSp1" name="NSp" value="0" hidden="true">

                                            <input name="nbPl" value="<%=(Integer) request.getAttribute("nbPl")%>" hidden="true">
                                            <input name="login" value="<%=userName%>" hidden="true">
                                            <input name="boolResa" value="1" hidden="true">
                                            <input name="action" value="addAchat" hidden="true">
                                        </form>

                                        <form action="<%=request.getContextPath()%>/controleur" onsubmit="onclickFinalize(0, <%=(Integer) request.getAttribute("nbPl")%>);
                                                return true;" method="post">
                                            <button type="submit">
                                                Finaliser la commande <span class="glyphicon glyphicon-euro"></span>
                                            </button>

                                            <input id="NR0" name="NR" value="0" hidden="true">
                                            <%for (int l = 0; l < (Integer) request.getAttribute("nbPl"); l++) {%>
                                            <input id="NP0<%=l%>" name="NP<%=l%>" value="0" hidden="true">
                                            <%}%>
                                            <input id="NSp0" name="NSp" value="0" hidden="true">

                                            <input name="nbPl" value="<%=(Integer) request.getAttribute("nbPl")%>" hidden="true">
                                            <input name="login" value="<%=userName%>" hidden="true">
                                            <input name="boolResa" value="0" hidden="true">
                                            <input name="action" value="addAchat" hidden="true">
                                        </form>

                                        <% } else if (k == 0) {%>
                                        <button onclick="afficheNext(<%=(Integer) request.getAttribute("nbPl")%>, <%=(Integer) request.getAttribute("nbPl") - k%>)">Place suivante</button>

                                        <% } else {%>
                                        <button onclick="afficheBefore(<%=(Integer) request.getAttribute("nbPl")%>, <%=(Integer) request.getAttribute("nbPl") - k%>)">Place précédente</button>
                                        <button onclick="afficheNext(<%=(Integer) request.getAttribute("nbPl")%>, <%=(Integer) request.getAttribute("nbPl") - k%>)">Place suivante</button>
                                        <%}
                                            }%>
                                </li>


                                <li>

                                </li>
                            </ul>
                            <!--                            <a href="php/panier.php?action=ajout&amp;l=LIBELLEPRODUIT&amp;q=QUANTITEPRODUIT&amp;
                                                           p=PRIXPRODUIT" onclick="window.open(this.href, '', 'toolbar=no, location=no, \n\
                                                               directories=no, status=yes, scrollbars=yes, resizable=yes, copyhistory=no, width=600,\n\
                                                                 height=350');
                                                                   return false;">Ajouter au panier
                                                        </a>-->

                            <div class="clearfix visible-lg"></div>
                        </div>
                    </div>
                    <%}%>
                </div>
            </div>
        </div>
    </body>
    <jsp:include page="jsp/footer.jsp"/>

</html>
