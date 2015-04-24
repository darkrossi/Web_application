<%-- 
    Document   : pieces
    Created on : 8 avr. 2015, 10:39:44
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <jsp:include page="jsp/head.jsp"/>
        <script src="js/place.js" type="text/javascript"></script>
        <script src="js/prix.js" type="text/javascript"></script>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/stylePieces.css" />
        <link rel="stylesheet" href="css/multiple.css" />

    </head>
    <body>
        <div class="container-fluid" >

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
                    <%--<input type="text" name="PrixMin" value placeholder="De"> --%>
                    <span> De </span>
                    <script type="text/javascript">prix();</script>
                    <%-- <input type="text" name="PrixMax" value placeholder="à"> --%>
                    <span> à </span>
                    <script type="text/javascript">prix();</script>

                    <dl class="dropdown">
                        <dt>
                            <a href="#">
                                <span class="hida" ><h4>Genre</h4></span>   
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
                                    <li>
                                        <input type="checkbox" value="TheatreContemporain" />Théâtre contemporain</li>
                                    <li>
                                        <input type="checkbox" value="TheatreEnfant" />Théâtre pour enfants</li>
                                    <li>
                                        <input type="checkbox" value="TheatreEquestre" />Théâtre équestre</li>
                                    <li>
                                        <input type="checkbox" value="SeulEnScene" />Seul en scène</li>

                                </ul>
                            </div>
                        </dd>
                    </dl>
                    <h2>Note</h2>
                    <h2>Ventes</h2>
                    <button>Filtrer</button>
                </div>         
                <div class="col-md-5">
                    <jsp:include page="jsp/OUTdisplayBooking.jsp"/>
                </div>
                <div class="texte">
                    <div class="col-md-3"> 
                        <h2 align="center"> Mes Réservations </h2>
                        <p>
                            <input type="text" name="datepicker_input3" class="datepicker"  style="display:block; margin:auto;" value placeholder ="Choisissez votre date">              
                        </p>
                        <%-- <input type="button" class="boutton1" value="Réserver ma place" > --%>
                        <a href="pieces.jsp"> Reserver
                            <p>
                            </p>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script>
        $(function () {
            $(".datepicker").datepicker();
        });
    </script>
    <script src="js/multiple.js" type="text/javascript"></script>
    <jsp:include page="jsp/footer.jsp"/>

</html>
