<%-- 
    Document   : managerResas
    Created on : 28 avr. 2015, 21:51:13
    Author     : oswald
--%>

<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@page import="modele.Representation"%>
<%@page import="modele.Dossier"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="jsp/head.jsp"/>

        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <jsp:include page="jsp/navbar.jsp"/>

        <div class="container" >
            <h2>Les Places réservées : </h2><br>
            <div class="row">
                <div class="col-md-2">
                    <p><b>Client</b></b></p>
                    <p><b>Spectacle</b></b></p>
                    <p><b>Date</b></p>
                    <p><b>Heure</b></p>
                    <p><b>Nombre de places</b></p>
                </div>

                <% if (request.getAttribute("resas") != null) {
                        List<Dossier> resas = (List<Dossier>) request.getAttribute("resas");
                        if (!resas.isEmpty()) {
                            for (int i = 0; i < resas.size(); i++) {%>
                <div class="col-md-2">
                    <p><%=resas.get(i).getLoginU()%></p>
                    <p><%=resas.get(i).getNomSpectacle()%></p>
                    <p><%=resas.get(i).getDate()%></p>
                    <p><%=resas.get(i).getHeure()%></p>
                    <p><%=resas.get(i).getNbP()%></p>
                    <form method="post" action="<%=request.getContextPath()%>/controleur">
                        <button type="submit">Annuler la réservation</button>
                        <input name="action" value="annuleResa" hidden="true">
                        <input name="admin" value="true" hidden="true">
                        <input name="login" value="<%=resas.get(i).getLoginU()%>" hidden="true">
                        <input name="ND" value="<%=resas.get(i).getND()%>" hidden="true">
                    </form>
                </div>
                <%
                            }
                        }
                    }%>

            </div>

            <h2>Les représentations : </h2><br>
            <div class="row">
                <div class="col-md-2">
                    <p><b>Spectacle</b></b></p>
                    <p><b>Date</b></p>
                    <p><b>Heure</b></p>
                    <p><b>Nombre de places restantes</b></p>
                </div>

                <% if (request.getAttribute("repres") != null) {
                        Hashtable<String, List<Representation>> hashRepres = (Hashtable<String, List<Representation>>) request.getAttribute("repres");
                        if (!hashRepres.isEmpty()) { // Si il y a des rangs
                            Enumeration keys2 = hashRepres.keys();
                            while (keys2.hasMoreElements()) {
                                String NomR = (String) keys2.nextElement();
                                List<Representation> repres = (List<Representation>) hashRepres.get(NomR);
                                for (int i = 0; i < repres.size(); i++) {%>
                <div class="col-md-2">
                    <p><%=repres.get(i).getNomSpect()%></p> 
                    <p><%=repres.get(i).getDate()%></p>
                    <p><%=repres.get(i).getHeure()%></p>
                    <p><%=repres.get(i).getNbP()%></p>
                    <form method="post" action="<%=request.getContextPath()%>/controleur">
                        <button type="submit">Annuler la représentation</button>
                        <input name="action" value="annuleRepres" hidden="true">
                        <input name="NR" value="<%=repres.get(i).getNR() %>" hidden="true">
                    </form>
                </div>
                <%
                                }
                            }
                        }
                    }%>

            </div> 
        </div>
    </body>
    <jsp:include page="jsp/footer.jsp"/>
</html>
