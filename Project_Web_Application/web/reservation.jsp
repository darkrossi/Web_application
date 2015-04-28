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
                $("#genre").multiselect();
                $("select").multiselect();
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
                <div class="col-md-4">
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
                <div class="col-md-3"> 
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


                    <script>
                        function onChangeRang() {
                            select_menu = document.getElementById('selectRang');
                            select_menu_value = select_menu.options[select_menu.selectedIndex].value;
                            document.getElementById('valueRang').value = select_menu_value;
                            changeHiddenSelectPlace('selectPlace' + select_menu_value);
                        }
                        function onChangePlace(nra) {
                            select_menu = document.getElementById('selectPlace' + nra);
                            document.getElementById('valuePlace' + nra).value = select_menu.options[select_menu.selectedIndex].value;
                        }
                        function changeHiddenSelectPlace(idS) {
                            selects = document.getElementsByClassName("selectP");
                            for (i = 0; i < selects.length; i++) {
                                id = selects[i].id;
                                if (id === idS) {
                                    $('#' + idS).show();
                                } else {
                                    $('#' + id).hide();
                                }
                            }
                        }
                    </script>

                    <!-- LISTE PLACES -->
                    <% if (request.getAttribute("rangs") != null) { %>
                    <div class="row">
                        <div class="large-2 columns">
                            <h3 align="center"> Choisissez vos places </h3>
                            <ul>
                                <li>                                    
                                    Rang : <select id="selectRang" onchange="onChangeRang()" >
                                        <% Hashtable<Rang, List<Place>> hashRangs = (Hashtable<Rang, List<Place>>) request.getAttribute("rangs");
                                            if (!hashRangs.isEmpty()) { // Si il y a des rangs
                                                Enumeration keys2 = hashRangs.keys();
                                                int counter = 1;
                                                while (keys2.hasMoreElements()) { // On affiche le menu déroulant avec les rangs
                                                    Rang rang = (Rang) keys2.nextElement();%>
                                        <option value="<%=rang.getNRa()%>"><%=rang.getNomCT()%><%=counter++%></option>
                                        <%}%>
                                    </select><br>
                                    <input id="valueRang" name="valueRang" value="1" hidden="true">

                                    <% keys2 = hashRangs.keys();
                                        boolean isFirst = true;
                                        while (keys2.hasMoreElements()) {
                                            Rang rang = (Rang) keys2.nextElement();
                                            Integer NRa = rang.getNRa();
                                            List<Place> places = (List<Place>) hashRangs.get(rang);
                                            if (isFirst) {%>
                                    Place : <select id="selectPlace<%=NRa%>" class="selectP" onchange="onChangePlace('<%=NRa%>')" >
                                        <% isFirst = false;
                                        } else {%>
                                        <select hidden="true" id="selectPlace<%=NRa%>" class="selectP" onchange="onChangePlace('<%=NRa%>')" >
                                            <%}
                                                for (int j = 0; j < places.size(); j++) {
                                                    Place place = places.get(j);
                                                    if (place.getIsTaken() == 0) {%>
                                            <option value="<%=places.get(j).getNP()%>"><%=j + 1%></option>
                                            <%}
                                                }%>
                                        </select>
                                        <input id="valuePlace<%=NRa%>" name="valuePlace<%=NRa%>" value="<%=places.get(0).getNP()%>" hidden="true">
                                        <%
                                                }
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

                            <% HttpSession session2 = request.getSession(false);
                                String userName = (String) session2.getAttribute("utilisateur");%>

                            <script>
                                function onclickFinalize(bool) {
                                <% Representation representation = (Representation) request.getAttribute("represPicked");%>
                                    NR = <%=representation.getNR()%>;
                                    $('#NR' + bool).attr("value", NR);

                                    NSP = <%=representation.getNSp()%>;
                                    $('#NSp' + bool).attr("value", NSP);

                                    selectRang = document.getElementById('selectRang');
                                    selectRangValue = selectRang.options[selectRang.selectedIndex].value;
                                    $('#NRa' + bool).attr("value", selectRangValue);

                                    selectPlace = document.getElementById('selectPlace' + selectRangValue);
                                    selectPlaceValue = selectPlace.options[selectPlace.selectedIndex].value;
                                    $('#NP' + bool).attr("value", selectPlaceValue);
                                }
                            </script>

                            <form action="<%=request.getContextPath()%>/controleur" onsubmit="onclickFinalize(1);
                                    return true;" method="post">
                                <button type="submit">
                                    Réserver <span class="glyphicon glyphicon-arrow-down"></span>
                                </button>
                                <input id="NR1" name="NR" value="0" hidden="true">
                                <input id="NRa1" name="NRa" value="0" hidden="true">
                                <input id="NP1" name="NP" value="0" hidden="true">
                                <input id="NSp1" name="NSp" value="0" hidden="true">
                                <input name="login" value="<%=userName%>" hidden="true">
                                <input name="boolResa" value="1" hidden="true">
                                <input name="action" value="addAchat" hidden="true">
                            </form>

                            <form action="<%=request.getContextPath()%>/controleur" onsubmit="onclickFinalize(0);
                                    return true;" method="post">
                                <button type="submit">
                                    Finaliser la commande <span class="glyphicon glyphicon-euro"></span>
                                </button>
                                <input id="NR0" name="NR" value="0" hidden="true">
                                <input id="NRa0" name="NRa" value="0" hidden="true">
                                <input id="NP0" name="NP" value="0" hidden="true">
                                <input id="NSp0" name="NSp" value="0" hidden="true">
                                <input name="login" value="<%=userName%>" hidden="true">
                                <input name="boolResa" value="0" hidden="true">
                                <input name="action" value="addAchat" hidden="true">
                            </form>

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