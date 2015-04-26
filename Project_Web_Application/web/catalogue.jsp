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
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/stylePieces.css" />

        <script>
            function place()
            {
                document.write('<form method="post" action="traitement.php"><select name="NombreDePlace" id="NombreDePlace"><option value="0">0</option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option></select> </form>');
            }
            function prix(){
                
            }
        </script>

    </head>
    <body>
        <div class="container-fluid" >

            <jsp:include page="jsp/navbar.jsp"/>

            <div class="row">
                <div class="col-md-4">
                    <jsp:include page="jsp/OUTfiltreCatalogue.jsp"/>
                </div>   
                
                <div class="col-md-5">
                    <jsp:include page="jsp/OUTdisplayCatalogue.jsp"/>
                </div>
                
                <div class="texte">
                    <div class="col-md-3"> 
<!--                        <h2 align="center"> Mes Réservations </h2>
                        <p>
                            <input type="text" name="datepicker_input3" class="datepicker"  style="display:block; margin:auto;" value placeholder ="Choisissez votre date">              
                        </p>
                        <%-- <input type="button" class="boutton1" value="Réserver ma place" > --%>
                        <a href="reservation.jsp"> Reserver
                            <p>
                            </p>-->
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
