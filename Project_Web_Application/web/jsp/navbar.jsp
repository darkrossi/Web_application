<%-- 
    Document   : navbar
    Created on : 8 avr. 2015, 09:17:04
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
                <li><a href="addSpectacle.jsp">Ajouter un spectacle</a></li>
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
                    if (userName == null) {
                        out.write("<li><a href=\"login.jsp\"><span class=\"glyphicon glyphicon-log-in\"></span> Login</a></li>");
                    } else {
                        out.write("<li><a href=\"/Project_Web_Application/logout\"> Log out</a></li>");
                        out.print("<font size=\"3\" color=\"white\"> Bienvenue " + userName + "</font>");
                    }
                %>
            </ul>
        </div>
    </div>
</nav>

<!-- POPUP SIGN OUT -->
<div id="myModal" class="reveal-modal">
    <div class="container" >
        <h1>Créer un compte</h1>
        <FORM ACTION="/Project_Web_Application/checkNewAccount" onsubmit="return isValid()" METHOD="POST">
            <div class="row">
                <label class="col-md-3"> Prénom (2 à 12 caractères)</label><input class="col-md-2" id="prenom" name="prenom"><br>
            </div>
            <div id="prenom_error"> </div>
            <div class="row">
                <label class="col-md-3"> Nom (2 à 12 caractères)</label><input class="col-md-2" id="nom" name="nom"><br>
            </div>
            <div id="nom_error"> </div>
            <div class="row">
                <label class="col-md-3"> Login (2 à 12 caractères)</label><input class="col-md-2" id="login" name="login"><br>
            </div>
            <div id="login_error"> </div>

            <div class="row">
                <label class="col-md-3"> Mot de passe (5 à 12 caractères)</label><input class="col-md-2" id="password" name="password" type="PASSWORD"><br>
            </div>
            <div id="password_error"> </div>
            <div class="row">
                <label class="col-md-3"> E-mail</label><input class="col-md-2" id="email" name="mail"><br>
            </div>
            <input type="SUBMIT">
        </FORM>
        <br>
        ajouter un captcha pour la sécu ! 
        <br>
        <a href="index.jsp">Retour à l'accueil</a>
        <a class="close-reveal-modal">&#215;</a>
    </div>
</div>