<%-- 
    Document   : navbar
    Created on : 8 avr. 2015, 09:17:04
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% HttpSession session2 = request.getSession(false);
    String userName = (String) session2.getAttribute("utilisateur");%>

<!-- NAVBAR -->
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.jsp"><font id="comedimag">Comed'Imag <span class="glyphicon glyphicon-home"></span></font></a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <!--                <li class="active"><a href="index.jsp">Home</a></li>-->
                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Menu<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="pieces.jsp">Catalogue</a></li>
                        <li>
                            <a href="javascript:document.inputForm1.submit();">Réserver une place</a>
                            <form action="<%=request.getContextPath()%>/controleur" method="get" name="inputForm1">
                                <input name="action" value="displayAddBooking" hidden="true">
                            </form>
                        </li>
                        <% if (userName != null) {
                                if (userName.equals("admin")) {%>
                        <li><a href="addSpectacle.jsp">Ajouter un spectacle</a></li>
                        <li>
                            <a href="javascript:document.inputForm2.submit();">Ajouter une représentation</a>
                            <form action="<%=request.getContextPath()%>/controleur" method="get" name="inputForm2">
                                <input name="action" value="displayAddRepres" hidden="true">
                            </form>
                        </li>
                        <li><a href="addSalle.jsp">Ajouter une salle</a></li>
                            <% }
                                }%>
                    </ul>
                </li>

                <li><a href="#">Qui sommes-nous ?</a></li>
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
                <%@ page import="javax.servlet.http.HttpSession"%>
                <% if (userName == null) { %>
                <li><a href="#" data-reveal-id="myModal"><span class="glyphicon glyphicon-pencil"></span> Sign Up</a></li>
                <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                    <% } else {%>
                <li ondrop="drop(event)" ondragover="allowDrop(event)"><a href="#"><span class="glyphicon glyphicon-shopping-cart"></span> Mon Panier</a></li>
                <li>
                    <form action="<%=request.getContextPath()%>/controleur" method="get">
                        <a href="monCompte.jsp"><span class="glyphicon glyphicon-user"></span> Mon Compte</a>
                        <input name="action" value="displayAccount" hidden="true">
                        <input name="login" value="<%= userName%>" hidden="true">
                    </form>
                </li>
                <li><a href="/Project_Web_Application/logout"><span class="glyphicon glyphicon-log-out"></span> Log out</a></li>
                <font size="3" color="white" style="padding-top: 15px; padding-bottom: 15px; padding-right: 15px;"> Bienvenue <%= userName%></font>
                <% }%>
            </ul>
        </div>
    </div>
</nav>

<script>
    // Fonction à compléter par Hoël pour la vérif des champs
    function isValid() {
        var prenom = document.getElementById("prenom").value;
        var nom = document.getElementById("nom").value;
        var login = document.getElementById("login").value;
        var password = document.getElementById("password").value;
        var email = document.getElementById("email").value;

        //clear des champs 'erreur'
        document.getElementById("prenom_error").innerHTML = "";
        document.getElementById("nom_error").innerHTML = "";
        document.getElementById("password_error").innerHTML = "";
        document.getElementById("login_error").innerHTML = "";

        if (prenom.length < 2 || prenom.length > 12) {
            document.getElementById("prenom_error").innerHTML = "ERREUR - ce champ contient entre 2 et 12 caractères";
            return false;
        }
        if (nom.length < 2 || nom.length > 12) {
            document.getElementById("nom_error").innerHTML = "ERREUR - ce champ contient entre 2 et 12 caractères";
            return false;
        }
        if (login.length < 2 || login.length > 12) {
            document.getElementById("login_error").innerHTML = "ERREUR - ce champ contient entre 2 et 12 caractères";
            return false;
        }
        if (password.length < 5 || password.length > 12) {
            document.getElementById("password_error").innerHTML = "ERREUR - ce champ contient entre 5 et 12 caractères";
            return false;
        }

        return true;
    }
</script>

<!-- POPUP SIGN OUT -->
<div id="myModal" class="reveal-modal">
    <div class="container" >
        <h1>Créer un compte</h1>
        <FORM ACTION="<%=request.getContextPath()%>/controleur" onsubmit="return isValid()" METHOD="get">
            <div class="row">
                <label class="col-md-3"> Prénom</label><input class="col-md-2" id="prenom" name="prenom"><br>
            </div>
            <div id="prenom_error"> </div>
            <div class="row">
                <label class="col-md-3"> Nom</label><input class="col-md-2" id="nom" name="nom"><br>
            </div>
            <div id="nom_error"> </div>
            <div class="row">
                <label class="col-md-3"> Login</label><input class="col-md-2" id="login" name="login"><br>
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
            <input hidden="true" name="action" value="addUser">

        </FORM>
        <br>
        <a href="index.jsp">Retour à l'accueil</a>
        <a class="close-reveal-modal">&#215;</a>
    </div>
</div

<!-- CONTAINER BOOTSTRAP -->
<div class="container" hidden="true" >

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