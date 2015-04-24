<%-- 
    Document   : pieces
    Created on : 8 avr. 2015, 10:39:44
    Author     : oswald
--%>

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
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/stylePieces.css" />

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

        <div class="container-fluid" >
            <div class="row">
                <div class="col-md-3">
                    <h2>Date</h2>
                    <input type="text" name="datepicker_input1" class="datepicker" value placeholder="Du">
                    <input type="text" name="datepicker_input2" class="datepicker" value placeholder="Au">
                    <h2>Prix</h2>
                    <form method="post">
                        <label for="genre">Genre</label>
                        <select name="genre" id="genre" multiple value placeholder="Genre">
                            <option selected name="Peu importe" id="Peu importe" value="ToutGenre">Tous les genres</option>
                            <option name="Concert" id="Concert" value="C">Concert</option>
                            <option value="Spectacle">Spectacle</option>
                        </select>
                    </form>
                    <h2>Note</h2>
                    <h2>Ventes</h2>

                </div>

                <div class="col-md-6">
                    <jsp:include page="jsp/OUTaddBooking.jsp"/>
                </div>

                <div class="col-md-3"> 
                    <h2 align="center"> Mes Réservations </h2>
                    <div id="colonneDate">
                        <% if (request.getAttribute("representation") != null) {
                                    List<Representation> representation = (List<Representation>) request.getAttribute("representation");
                                    if (!representation.isEmpty()) {
                                        for (int i = 0; i < representation.size(); i++) {%>
                                            <option value="<%=representation.get(i).getDate()%>"><%=representation.get(i).getHeure()%></option>
                            <%}%>
                    </div>

                    <div class="large-2 columns">

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
                                <a href="php/panier.php?action=ajout&amp;l=LIBELLEPRODUIT&amp;q=QUANTITEPRODUIT&amp;
                                   p=PRIXPRODUIT" onclick="window.open(this.href, '', 'toolbar=no, location=no, \n\
                                   directories=no, status=yes, scrollbars=yes, resizable=yes, copyhistory=no, width=600,\n\
                                     height=350'); return false;">Ajouter au panier
                                </a>
                            <div class="clearfix visible-lg"></div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <jsp:include page="jsp/footer.jsp"/>

</html>
