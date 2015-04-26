<%-- 
    Document   : pieces
    Created on : 8 avr. 2015, 10:39:44
    Author     : oswald
--%>

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
        <script src="js/multiple.js" type="text/javascript"></script>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/stylePieces.css" />
        <link rel="stylesheet" href="css/multiple.css" />

        <script>
            $("document").ready(function () {
                $(".datepicker").datepicker();
                dateFormat: "dd-mm-yy";
            });
        </script>
        <script>
            $(function () {
                $("select").multiselect();
            });
        </script>
        <script>
            $(document).ready(function () {
                $("#genre").multiselect();
            });
        </script>

    </head>
    <body>
        <?php
        include("php/panier.php");
        ?>
        <jsp:include page="jsp/navbar.jsp"/>

        <div class="container-fluid">
            <div class="row">
                <!-- FILTRES -->
                <div class="col-md-4">
                    <h2>Recherche</h2>
                    <form>
                        <input type="text" name="BarreRecherche" value placeholder="Mots clés">
                        <input type="submit" value="Rechercher">
                    </form>
                    <h2>Date</h2>
                    <input type="text" name="datepicker_input1" class="datepicker" value placeholder="Du">
                    <input type="text" name="datepicker_input2" class="datepicker" value placeholder="Au">
                    <h2>Prix</h2>
                    <h2>Genre</h2>
                    <dl class="dropdown">
                        <dt>
                        <a href="#">
                            <span class="hida">Genre</span>   
                            <p class="multiSel"></p> 
                        </a>
                        </dt>

                        <dd>
                            <div class="mutliSelect">
                                <ul>
                                    <li>
                                        <input type="checkbox" value="GrandSpectacle" />Grand spectacle</li>
                                    <li>
                                        <input type="checkbox" value="ComedieMusicale" />Comédie musicale</li>
                                    <li>
                                        <input type="checkbox" value="SpectacleMagie" />Spectacle de magie</li>
                                </ul>
                            </div>
                        </dd>
                        <button>Filter</button>
                    </dl>
                    <h2>Note</h2>
                    <h2>Ventes</h2>

                </div>

                <!-- SPECTACLE AVEC LISTE REPRESENTATIONS -->
                <div class="col-md-5">
                    <jsp:include page="jsp/OUTaddBooking.jsp"/>
                </div>                

                <!-- LISTE PLACES DISPONIBLES POUR UNE REPRESENTATION -->
                <div class="col-md-3"> 
                    <h2 align="center"> Mes Réservations </h2>

                    <!-- DATE/HEURE -->
                    <div class="row">
                        <div class="col-md-3">
                            <% if (request.getAttribute("represPicked") != null) {

                                    Representation representation = (Representation) request.getAttribute("represPicked");%>
                            <p><%= representation.getDate()%></p>
                            <% }%>
                        </div>
                        <div class="col-md-3">
                            <% if (request.getAttribute("represPicked") != null) {
                                    Representation representation = (Representation) request.getAttribute("represPicked");%>
                            <p><%=representation.getHeure()%></p>
                            <% }%>
                        </div>
                    </div>


                    <script>
                        function onChangeRang() {
                            select_menu = document.getElementById('selectRang');
                            select_menu_value = select_menu.options[select_menu.selectedIndex].value;
                            document.getElementById('valueRang').value = select_menu_value;
                            changeHiddenSelectPlace('selectPlace'+select_menu_value);
                        }
                        function onChangePlace(nra) {
                            select_menu = document.getElementById('selectPlace' + nra);
                            document.getElementById('valuePlace' + nra).value = select_menu.options[select_menu.selectedIndex].value;
                        }
                        function changeHiddenSelectPlace(idS) {
                            selects = document.getElementsByClassName("selectP");
                            for (i = 0; i < selects.length; i++) {
                                id = selects[i].id;
                                if (id === idS){
                                    $('#' + idS).show();
                                } else {
                                    $('#' + id).hide();
                                }
                            }
                        }
                    </script>

                    <!-- LISTE PLACES -->
                    <div class="row">
                        <div class="large-2 columns">
                            <h3 align="center"> Choisissez vos places </h3>
                            <ul>
                                <li>
                                    <h5>Offre Adhérent</h5>
                                    <% if (request.getAttribute("rangs") != null) { %>
                                    Rang : <select id="selectRang" onchange="onChangeRang()" >
                                        <% Hashtable<Integer, List<Place>> hashRangs = (Hashtable<Integer, List<Place>>) request.getAttribute("rangs");
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
                                        boolean isFirst = true;
                                        while (keys2.hasMoreElements()) {
                                            Integer NRa = (Integer) keys2.nextElement();
                                            List<Place> places = (List<Place>) hashRangs.get(NRa);
                                            if (isFirst) {%>
                                    Place : <select id="selectPlace<%=NRa%>" class="selectP" onchange="onChangePlace('<%=NRa%>')" >
                                        <% isFirst = false;
                                        } else {%>
                                        <select hidden="true" id="selectPlace<%=NRa%>" class="selectP" onchange="onChangePlace('<%=NRa%>')" >
                                            <%}
                                                for (int j = 0; j < places.size(); j++) {%>
                                            <option value="<%=places.get(j).getNP()%>"><%=j + 1%></option>
                                            <%}%>
                                        </select>
                                        <input id="valuePlace<%=NRa%>" name="valuePlace<%=NRa%>" value="<%=places.get(0).getNP()%>" hidden="true">
                                        <%
                                                    }
                                                }
                                            }%>
                                        </li>



                                        <li>
                                            <h5>Offre Normal

                                            </h5>
                                        </li>
                            </ul>
                            <a href="php/panier.php?action=ajout&amp;l=LIBELLEPRODUIT&amp;q=QUANTITEPRODUIT&amp;
                               p=PRIXPRODUIT" onclick="window.open(this.href, '', 'toolbar=no, location=no, \n\
                                   directories=no, status=yes, scrollbars=yes, resizable=yes, copyhistory=no, width=600,\n\
                                     height=350');
                                       return false;">Ajouter au panier
                            </a>

                            <div class="clearfix visible-lg"></div>
                        </div>
                    </div>
                </div>
            </div>
    </body>
    <jsp:include page="jsp/footer.jsp"/>

</html>

<!-- Proposition : Faire un menu déroulant pour catégorie et un autre pour les types (orchestre, corbeille..) -->
<%--
<h3 align="center"> Choisissez vos places </h3>
<h4 class= "ligne"> Choix automatique </h4><form method="post" action="traitement.php">
    <h4> Choix sur plan </h4>
    <h3> Carré VIP </h3>
    <h4> CORBEILLE </h4>
    <ul>
        <li><h5>Offre Adhérent
                <script type="text/javascript">place();</script>
            </h5></li>
        <li><h5>Offre Normal
                <script type="text/javascript">place();</script>
            </h5></li>
    </ul>
    <h4> ORCHESTRE </h4>
    <ul>
        <li><h5> Offre Adhérent 
                <script type="text/javascript">place();</script>
            </h5></li>
        <li><h5> Offre Normal
                <script type="text/javascript">place();</script>
            </h5></li>
    </ul>
    <h3 > Catégorie 1 </h3>
    <h4> CORBEILLE </h4>
    <ul>
        <li><h5> Offre Adhérent 
                <script type="text/javascript">place();</script>
            </h5></li>
        <li><h5> Offre Normal
                <script type="text/javascript">place();</script>
            </h5></li>
    </ul>
    <h4> ORCHESTRE</h4>
    <ul>
        <li><h5> Offre Adhérent 
                <script type="text/javascript">place();</script>
            </h5></li>
        <li><h5> Offre Normal
                <script type="text/javascript">place();</script>
            </h5></li>
    </ul>
    <h4> ORCHESTRE COTE </h4>
    <ul>
        <li><h5> Offre Adhérent 
                <script type="text/javascript">place();</script>
            </h5></li>
        <li><h5> Offre Normal
                <script type="text/javascript">place();</script>
            </h5></li>
    </ul>


                            <h3 > Catégorie 2 </h3>
                            <h4> CORBEILLE </h4>
                            <ul>
                                <li><h5> Offre Adhérent 
                                        <script type="text/javascript">place();</script>
                                    </h5></li>
                                <li><h5> Offre Normal
                                        <script type="text/javascript">place();</script>
                                    </h5></li>
                            </ul>
                            <h4> BALCON </h4>
                            <ul>
                                <li><h5> Offre Adhérent 
                                        <script type="text/javascript">place();</script>
                                    </h5></li>
                                <li><h5> Offre Normal
                                        <script type="text/javascript">place();</script>
                                    </h5></li>
                            </ul>

                            <h3 > Catégorie 3 </h3>
                            <h4> BALCON </h4>
                            <ul>
                                <li><h5> Offre Adhérent 
                                        <script type="text/javascript">place();</script>
                                    </h5></li>
                                <li><h5> Offre Normal
                                        <script type="text/javascript">place();</script>
                                    </h5></li>
                            </ul>
                            <h4> Total </h4>
--%>
<%--<input  type="submit" class="Réserver mes places" /> --%>