<%-- 
    Document   : index
    Created on : 3 avr. 2015, 11:39:42
    Author     : oswald
--%>

<%@page import="dao.DAOException"%>
<%@page import="dao.AfficheDAO"%>
<%@page import="java.util.List"%>
<%@page import="modele.Affiche"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <jsp:include page="jsp/head.jsp"/>

        <link rel="stylesheet" type="text/css" href="css/style.css"> 
        <script src="js/functions.js" type="text/javascript"></script>

    </head>
    <body>

        <jsp:include page="jsp/navbar.jsp"/>

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
                            <% List<Affiche> affiches = (List<Affiche>)request.getAttribute("affiches");
                                if (!affiches.isEmpty()) {
                                    for (int i = 0; i < affiches.size(); i++) {%>
                            <li><a href="#"><img src="img/<%=affiches.get(i).toString()%>" data-large="img/<%=affiches.get(i).toString()%>" alt="image01" data-description="" /></a></li>
                                    <%}
                                        }%>
                        </ul>
                    </div>
                </div>
                <!-- End Elastislide Carousel Thumbnail Viewer -->
            </div><!-- rg-thumbs -->
        </div><!-- rg-gallery -->
    </body>

    <jsp:include page="jsp/footer.jsp"/>

</html>