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
        <script src="js/display_hid.js" type="text/javascript"></script>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/stylePieces.css" />

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
<<<<<<< HEAD
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
                            <input type="button" style="margin: 0px auto 0px auto;" onclick="display_hid('texte')" class="boutton" value="Passer à la commande" >
                        </div>
                    </div>
=======
                    <jsp:include page="jsp/OUTdisplayBooking.jsp"/>
>>>>>>> 13ddf45f0331a5cea7b9f4a2ecceb2e94d4f6f8b
                </div>
                <div class="texte">
                    <div class="col-md-3"> 
                        <h2 align="center"> Mes Réservations </h2>
                        <p>
                            <input type="text" name="datepicker_input3" class="datepicker"  style="display:block; margin:auto;" value placeholder ="Choisissez votre date">              
                        </p>
                        <p>
                            <%-- <input type="button" class="boutton1" value="Réserver ma place" > --%>
                            <a href="pieces.jsp"> Reserver
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
    <jsp:include page="jsp/footer.jsp"/>

</html>
