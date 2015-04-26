<%-- 
    Document   : pieces
    Created on : 8 avr. 2015, 10:39:44
    Author     : oswald
--%>

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

        <div class="row">
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

            <div class="col-md-5">
                <jsp:include page="jsp/OUTaddBooking.jsp"/>
            </div>                <div class="col-md-3"> 
                <h2 align="center"> Mes Réservations </h2>
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

                <div class="large-2 columns">
                    <h3 align="center"> Choisissez vos places </h3>
                    <ul>
                        <li><h5>Offre Adhérent
                                <% if(request.getAttribute("rangs") != null ){
                                    Hashtable<Integer, List<Place>> rangs = (Hashtable<Integer, List<Place>>) request.getAttribute("rangs");
                                } %>
                            </h5></li>
                        <li><h5>Offre Normal
                                
                            </h5></li>
                    </ul>
                    <a href="php/panier.php?action=ajout&amp;l=LIBELLEPRODUIT&amp;q=QUANTITEPRODUIT&amp;
                       p=PRIXPRODUIT" onclick="window.open(this.href, '', 'toolbar=no, location=no, \n\
                                   directories=no, status=yes, scrollbars=yes, resizable=yes, copyhistory=no, width=600,\n\
                                     height=350');
                               return false;">Ajouter au panier
                    </a>

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
                    <div class="clearfix visible-lg"></div>
                </div>
            </div>
        </div>
    </div>
</body>
<jsp:include page="jsp/footer.jsp"/>

</html>
