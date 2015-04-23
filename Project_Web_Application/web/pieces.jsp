<%-- 
    Document   : pieces
    Created on : 8 avr. 2015, 10:39:44
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Pieces</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

    </head>
    <body>
        <div class="container-fluid" >

            <jsp:include page="jsp/navbar.jsp"/>

            <div class="row">
                <div class="col-md-3">
                    <h2>Date</h2>
                    <input type="text" name="datepicker_input1" class="datepicker" value placeholder="Du">
                    <input type="text" name="datepicker_input2" class="datepicker" value placeholder="Au"> 
                    <h2>Prix</h2>
                    <h2>Genre</h2>
                    <h2>Note</h2>
                    <h2>Ventes</h2>

                </div>
                <div class="col-md-6">
                    <div class="fondBlock">
                        <div id="DescriptionColonne1">
                            <img src="img/affiche1.jpg" alt="Affiche1" class="affiche"  />
                        </div>
                        <div id="DescriptionColonne2">
                            <ul>
                                <li><h4>Nom</h4></li>
                                <li><h4>Genre</h4></li>
                                <li><h4>Date</h4></li>
                                <li><h4>Notes</h4></li>
                                <li><h4>J'aime</h4></li>
                            </ul>
                        </div>
                        <div id="DescriptionColonne3">                 
                            <input type="button" style="margin: 0px auto 0px auto;" class="boutton" value="Passer à la commande" >

                        </div>
                    </div>
                </div>
                <div class="col-md-3"> 
                    <h2 align="center"> Mes Réservations </h2>
                    <div id="colonneDate">
                        <h3 align="center">Date</h3>
                    </div>
                    <div id="colonneHeure">
                        <h3 align="center">Heure</h3>
                    </div>

                    <div class="large-2 columns">

                        <!-- Proposition : Faire un menu déroulant pour catégorie et un autre pour les types (orchestre, corbeille..) -->

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
                            <input  type="submit" class="Réserver ma place" />
                            <div class="clearfix visible-lg"></div>
                    </div>
                </div>
            </div>
    </body>
    <jsp:include page="jsp/footer.jsp"/>
    <script>
        $(function() {
            $( ".datepicker" ).datepicker();
        });
    </script>
    <jsp:include page="jsp/head.jsp"/>

    <script src="js/place.js" type="text/javascript"></script>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="css/stylePieces.css" />

</html>
