<%-- 
    Document   : index
    Created on : 3 avr. 2015, 11:39:42
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html lang="en">
    <head>
        <title>Site du théâtre ***</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" type="text/css" href="css/style.css"> 
        <!--<script type="text/javascript" src="js/typeahead.bundle.js"></script>-->

        <!-- JQuery -->
        <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui.min.js"></script>
        <script src="js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="css/jquery-ui.theme.min.css"> 
        <link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css">
        <link rel="stylesheet" type="text/css" href="css/jquery-ui.structure.min.css"> 
        <!-- -->    

        <!-- PopUp -->
        <script src="js/jquery.reveal.js" type="text/javascript"></script>
        <link href="css/reveal.css" rel="stylesheet" type="text/css"/>
        <!-- -->

        <!-- Carousel -->
        <link href="css/elastislide.css" rel="stylesheet" type="text/css"/>
        <link href="css/reset.css" rel="stylesheet" type="text/css"/>
        <link href="css/styleCar.css" rel="stylesheet" type="text/css"/>
        <script src="js/gallery.js" type="text/javascript"></script>
        <script src="js/jquery.easing.1.3.js" type="text/javascript"></script>
        <script src="js/jquery.elastislide.js" type="text/javascript"></script>
        <script src="js/jquery.tmpl.min.js" type="text/javascript"></script>
        <!-- -->

        <!-- Bootstrap -->
        <script type="text/javascript" src="js/bootstrap.js"></script>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <!-- -->

        <script id="img-wrapper-tmpl" type="text/x-jquery-tmpl">	
            <div class="rg-image-wrapper">
            {{if itemsCount > 1}}
            <div class="rg-image-nav">
            <a href="#" class="rg-image-nav-prev">Previous Image</a>
            <a href="#" class="rg-image-nav-next">Next Image</a>
            </div>
            {{/if}}
            <div class="rg-image"></div>
            <div class="rg-loading"></div>
            <div class="rg-caption-wrapper">
            <div class="rg-caption" style="display:none;">
            <p></p>
            </div>
            </div>
            </div>
        </script>
        <!-- -->

        <script>
            function allowDrop(ev) {
                ev.preventDefault();
            }

            function drag(ev) {
                ev.dataTransfer.setData("text", ev.target.src);
            }

            function drop(ev) {
                ev.preventDefault();
                var data = ev.dataTransfer.getData("text");
                sessionStorage.setItem("panier" + sessionStorage.getItem("count"), data);
                sessionStorage.setItem("count", sessionStorage.getItem("count") + 0);
//                var p = document.createElement("p");
//                p.innerHTML = sessionStorage.getItem("count").getName();
//                document.getElementById("div1").appendChild(p);
            }

            $(document).ready(function () {
                sessionStorage.setItem("count", "0");
//                $("img").attr("draggable", "true");
//                $("img").attr("ondragstart", "drag(event)");
            });</script>

        <script>
            // Fonction à compléter par Hoël pour la vérif des champs
            function isValid() {
                var prenom = document.getElementById("prenom").value;
                var nom = document.getElementById("nom").value;
                var password = document.getElementById("password").value;
                var email = document.getElementById("email").value;
                if(prenom.length < 2 || prenom.length > 12 ){
                   document.getElementById("prenom_error").innerHTML = "ERREUR - ce champ contient entre 2 et 12 caractères";
                   return false;
                }
                if(nom.length < 2 || nom.length > 12 ){
                    document.getElementById("nom_error").innerHTML = "ERREUR - ce champ contient entre 2 et 12 caractères";
                    return false;
                }
                if(password.length < 5 || password.length > 12 ){
                    document.getElementById("password_error").innerHTML = "ERREUR - ce champ contient entre 5 et 12 caractères";
                    return false;
                }
                return true;
            }
        </script>

    </head>
    <body>

        <!-- NAVBAR -->
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#"><font id="comedimag">Comed'Imag</font></a>
                </div>
                <div>
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="index.jsp">Home</a></li>
                        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Page 1-1</a></li>
                                <li><a href="#">Page 1-2</a></li>
                                <li><a href="#">Page 1-3</a></li>
                            </ul>
                        </li>
                        <li><a href="#">Page 2</a></li>
                        <li><a href="#">Page 3</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <!--                        <li>
                        
                                                    <form class="input-append" style="margin-top:4px;margin-bottom:5px;" action="/search" method="GET">
                                                        <input id="kw" class="span2" type="text" maxlength="40" name="kw" title="Enter keyword(s) to find"></input>
                                                        <button class="btn" type="submit">
                                                            <i class="icon-search" title="Search">
                                                            </i>
                                                        </button>
                                                    </form>
                        
                                                </li>-->
                        <li ondrop="drop(event)" ondragover="allowDrop(event)"><a href="#"><span class="glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
                        <li><a href="#" data-reveal-id="myModal"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>

                        <%@ page import="javax.servlet.http.HttpSession"%>
                        <%
                            HttpSession session2 = request.getSession(false);
                            String userName = (String) session2.getAttribute("utilisateur");
                            if (session2.isNew()) {
                                out.write("<li><a href=\"login.html\"><span class=\"glyphicon glyphicon-log-in\"></span> Login</a></li>");
                            } else {
                                out.write("<li><a href=\"/Project_Web_Application/logout\"> Log out</a></li>");
                                out.print("<font size=\"3\" color=\"white\"> Bienvenue " + userName + "</font>");
                            }
                        %>
                    </ul>
                </div>
            </div>
        </nav>
                    
        <!-- Test pour affichage des affiches -->
        <form name="input" action="<%=request.getContextPath()%>/controleur" method="get">
            <input type="submit" value="Modifier" />
        </form>
            
        <!-- POPUP SIGN OUT -->
        <div id="myModal" class="reveal-modal">
            <h1>Créer un compte</h1>
            <FORM ACTION="/Project_Web_Application/checkNewAccount" onsubmit="return isValid()" METHOD="POST">
                <label> Prénom (2 à 12 caractères)      </label><input id="prenom" name="prenom"><br>
                <div id="prenom_error"> </div>
                <label> Nom (2 à 12 caractères)         </label><input id="nom" name="nom"><br>
                <div id="prenom_error"> </div>
                <label> Mot de passe (5 à 12 caractères) </label><input id="password" name="password" type="PASSWORD"><br>
                <div id="prenom_error"> </div>
                <label> E-mail       </label><input id="email" name="mail"><br>
                <input type="SUBMIT">            
            </FORM>
            <br>
            ajouter un captcha pour la sécu ! 
            <br>
            <a href="index.jsp">Retour à l'accueil</a>
            <a class="close-reveal-modal">&#215;</a>
        </div>

        <!-- CAROUSEL -->
        <div id="rg-gallery" class="rg-gallery">
            <div class="rg-thumbs">
                <!-- Elastislide Carousel Thumbnail Viewer -->
                <div class="es-carousel-wrapper">
                    <div class="es-nav">
                        <span class="es-nav-prev">Previous</span>
                        <span class="es-nav-next">Next</span>
                    </div>
                    <div class="es-carousel">
                        <ul>
                            <li><a href="#"><img src="img/affiche1.jpg" data-large="img/affiche1.jpg" alt="image01" data-description="" /></a></li>
                            <li><a href="#"><img src="img/affiche2.jpg" data-large="img/affiche2.jpg" alt="image02" data-description="" /></a></li>
                            <li><a href="#"><img src="img/affiche3.jpg" data-large="img/affiche3.jpg" alt="image03" data-description="" /></a></li>
                            <li><a href="#"><img src="img/affiche4.jpg" data-large="img/affiche4.jpg" alt="image04" data-description="" /></a></li>
                            <li><a href="#"><img src="img/affiche5.jpg" data-large="img/affiche5.jpg" alt="image05" data-description="" /></a></li>
                        </ul>
                    </div>
                </div>
                <!-- End Elastislide Carousel Thumbnail Viewer -->
            </div><!-- rg-thumbs -->
        </div><!-- rg-gallery -->

        <!-- CONTAINER BOOTSTRAP -->
        <div class="container" >

            <div class="jumbotron">
                <h1>FermeDesAnimaux</h1>      
                <p>Ceci est notre site où vous pouvez acheter des places pour toutes nos représentations</p>      
                <a href="#" class="btn btn-info btn-lg"><span class="glyphicon glyphicon-search"></span> Go !</a>
            </div>

            <div class="row">
                <div class="col-md-3">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                </div>
                <div class="col-md-3"> 
                    <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                </div>
                <div class="col-md-3"> 
                    <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
                </div>
                <div class="col-md-3">
                    <ul class="nav nav-pills nav-stacked">
                        <li class="active"><a href="#">Home</a></li>
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">Menu 1 <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Submenu 1-1</a></li>
                                <li><a href="#">Submenu 1-2</a></li>
                                <li><a href="#">Submenu 1-3</a></li>                        
                            </ul>
                        </li>
                        <li><a href="#">Menu 2</a></li>
                        <li><a href="#">Menu 3</a></li>
                    </ul>
                </div>
                <div class="clearfix visible-lg"></div>
            </div>

            <div class="btn-group">
                <button type="button" class="btn btn-primary">Apple</button>
                <button type="button" class="btn btn-primary">Samsung</button>
                <button type="button" class="btn btn-primary">Sony</button>
            </div>
        </div>

        <a href="error.jsp">Error</a>
        <a href="monCompte.html">Mon Compte</a>
        <a href="pieces.html">Pièces</a>
        <a href="tests.html">Tests</a>
        <a href="jsp/afficheAffiches.jsp">Tests</a>
    </body>
</html>