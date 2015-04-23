<%-- 
    Document   : navbar
    Created on : 8 avr. 2015, 09:17:04
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% HttpSession session2 = request.getSession(false);
    String userName = (String) session2.getAttribute("utilisateur");%>

<script>
    $(document).ready(function () {
        if (<%= request.getAttribute("logBool")%>) {
            $("#myModalLog div p").html("<%=request.getAttribute("logText")%>");

            var myModalLog = $('#myModalLog');

            myModalLog.queue('queue', function () {
                $(this).reveal($(this).data());
                $(this).dequeue('queue');
            }).delay(1500, 'queue');

            myModalLog.queue('queue', function () {
                $(this).trigger('reveal:close');
                $(this).dequeue('queue');
            });

            myModalLog.dequeue('queue');
        }
    });
</script>

<!-- NAVBAR -->
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.jsp"><font id="comedimag">Comed'Imag <span class="glyphicon glyphicon-home"></span></font></a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <!--                <li class="active"><a href="index.jsp">Home</a></li>-->
                <% if (userName != null && userName.equals("admin")) {%>
                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Menu<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="pieces.jsp">Catalogue</a></li>
                        <li>
                            <a href="javascript:document.inputForm1.submit();">Réserver une place</a>
                            <form action="<%=request.getContextPath()%>/controleur" method="get" name="inputForm1">
                                <input name="action" value="displayAddBooking" hidden="true">
                            </form>
                        </li>

                        <li><a href="addSpectacle.jsp">Ajouter un spectacle</a></li>
                        <li>
                            <a href="javascript:document.inputForm2.submit();">Ajouter une représentation</a>
                            <form action="<%=request.getContextPath()%>/controleur" method="get" name="inputForm2">
                                <input name="action" value="displayAddRepres" hidden="true">
                            </form>
                        </li>
                        <li>
                            <a href="javascript:document.inputForm4.submit();">Ajouter une salle</a>
                            <form action="<%=request.getContextPath()%>/controleur" method="get" name="inputForm4">
                                <input name="action" value="displayAddSalle" hidden="true">
                            </form>
                        </li>


                    </ul>
                </li>
                <% } else {%>
                <li><a href="pieces.jsp">Catalogue</a></li>
                <li>
                    <a href="javascript:document.inputForm1.submit();">Réserver une place</a>
                    <form action="<%=request.getContextPath()%>/controleur" method="get" name="inputForm1">
                        <input name="action" value="displayAddBooking" hidden="true">
                    </form>
                </li>
                <%}%>

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

                <li><a href="#" data-reveal-id="myModalLogin"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>

                <% } else {%>
                <!--<li ondrop="drop(event)" ondragover="allowDrop(event)"><a href="#"><span class="glyphicon glyphicon-shopping-cart"></span> Mon Panier</a></li>
                -->
                <li>
                    <a href="javascript:document.inputForm3.submit();"><span class="glyphicon glyphicon-user"></span> Mon Compte</a>
                    <form action="<%=request.getContextPath()%>/controleur" method="get" name="inputForm3">
                        <input name="action" value="displayAccount" hidden="true">
                        <input name="login" value="<%= userName%>" hidden="true">
                    </form>
                </li>
                <li><a href="/Project_Web_Application/logout"><span class="glyphicon glyphicon-log-out"></span> Log out</a></li>
                <li><font size="5" color="white" style="padding-right: 15px;"> <b>Bienvenue</b> <%= userName%></font></li>
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
    </div>
</div>

<!-- POPUP LOGIN -->
<div id="myModalLogin" class="reveal-modal">
    <div class="container" >
        <h1>Connectez-vous à votre compte</h1>    
        <FORM ACTION="<%=request.getContextPath()%>/controleur" METHOD="get">
            <div class="row">
                <label class="col-md-3"> Indiquez votre login : </label><input class="col-md-2" name="loginU" id="loginU" autofocus="autofocus"><br>
            </div>
            <div class="row">
                <label class="col-md-3"> Indiquez votre mot de passe : </label><input class="col-md-2" name="mdpU" type="PASSWORD"><br>
            </div>
            <input type="SUBMIT">
            <input hidden="true" name="action" value="verifUser">

        </FORM>
    </div>
</div>           

<!-- POPUP LOG -->
<div id="myModalLog" class="reveal-modal">
    <div class="container" >
        <p></p>
    </div>
</div>