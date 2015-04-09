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
