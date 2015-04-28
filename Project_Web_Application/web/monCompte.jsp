<%-- 
    Document   : monCompte
    Created on : 9 avr. 2015, 16:14:29
    Author     : oswald
--%>

<%@page import="java.util.List"%>
<%@page import="modele.Dossier"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% HttpSession session2 = request.getSession(false);
    String userName = (String) session2.getAttribute("utilisateur");
    if (userName == null){
        String site = new String("./index.jsp");
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site); 
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <jsp:include page="jsp/head.jsp"/>

        <link rel="stylesheet" type="text/css" href="css/style.css">

    </head>
    <body>
        <jsp:include page="jsp/navbar.jsp"/>
        <h2>Mes Places achetées : </h2><br>

        <div class="container" >
            <div class="row">
                <div class="col-md-2">
                    <p><b>Spectacle</b></b></p>
                    <p><b>Date</b></p>
                    <p><b>Heure</b></p>
                    <p><b>Nombre de places</b></p>
                </div>

                <% if (request.getAttribute("dossiers") != null) {
                        List<Dossier> dossiers = (List<Dossier>) request.getAttribute("dossiers");
                        if (!dossiers.isEmpty()) {
                            for (int i = 0; i < dossiers.size(); i++) {%>
                                    <div class="col-md-2">
                                        <p><%=dossiers.get(i).getNomSpectacle()%></p>
                                        <p><%=dossiers.get(i).getDate()%></p>
                                        <p><%=dossiers.get(i).getHeure()%></p>
                                        <p><%=dossiers.get(i).getNbP()%></p>
                                        <form method="post" action="<%=request.getContextPath()%>/printPDF">
                                            <input name="login" value=<%=dossiers.get(i).getLoginU()%> hidden="true">
                                            <input name="ND" value=<%=dossiers.get(i).getND()%> hidden="true">
                                            <input name="NR" value=<%=dossiers.get(i).getNR()%> hidden="true">
                                            <input name="NT" value=<%=dossiers.get(i).getNT()%> hidden="true">
                                            <input name="NbP" value=<%=dossiers.get(i).getNbP()%> hidden="true">
                                            <button type="submit">Imprimer en PDF</button>
                                        </form>
                                    </div>
                                <%
                            }
                        }
                    }%>

            </div>
        </div>
        <h2>Mes Places réservées : </h2><br>
        
        <div class="container" >
            <div class="row">
                <div class="col-md-2">
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
                                        <p><%=resas.get(i).getNomSpectacle()%></p>
                                        <p><%=resas.get(i).getDate()%></p>
                                        <p><%=resas.get(i).getHeure()%></p>
                                        <p><%=resas.get(i).getNbP()%></p>
                                        <form method="post" action="<%=request.getContextPath()%>/controleur">
                                            <button type="submit">Confirmer la réservation</button>
                                            <input name="action" value="confirmResa" hidden="true">
                                            <input name="login" value="<%=userName%>" hidden="true">
                                            <input name="ND" value="<%=resas.get(i).getND() %>" hidden="true">
                                        </form>
                                        <form method="post" action="<%=request.getContextPath()%>/controleur">
                                            <button type="submit">Annuler la réservation</button>
                                            <input name="admin" value="false" hidden="true">
                                            <input name="action" value="annuleResa" hidden="true">
                                            <input name="login" value="<%=userName%>" hidden="true">
                                            <input name="ND" value="<%=resas.get(i).getND() %>" hidden="true">
                                        </form>
                                    </div>
                                <%
                            }
                        }
                    }%>

            </div>
        </div>
    </body>
    <jsp:include page="jsp/footer.jsp"/>

</html>
