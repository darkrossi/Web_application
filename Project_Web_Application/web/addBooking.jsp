<%-- 
    Document   : addBooking
    Created on : 18 avr. 2015, 09:02:56
    Author     : oswald
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@page import="modele.Representation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% HttpSession session2 = request.getSession(false);
    String userName = (String) session2.getAttribute("utilisateur");%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title> 

        <jsp:include page="jsp/head.jsp"/>

        <link rel="stylesheet" type="text/css" href="css/style.css">

    </head>
    <body>
        <jsp:include page="jsp/navbar.jsp"/>

        <div class="container" >
            <form action="<%=request.getContextPath()%>/controleur" method="get">
                <% if (request.getAttribute("repres") != null) {
                        Hashtable<String, Representation> hashRepres = (Hashtable<String, Representation>) request.getAttribute("repres");
                        if (!hashRepres.isEmpty()) {
                            Enumeration keys = hashRepres.keys();
                            while (keys.hasMoreElements()) {
                                String nomS = (String) keys.nextElement();
                                List<Representation> repres = (List<Representation>) hashRepres.get(nomS);%>
                <div class="row" style="margin-top: 10px;">
                    <div class="col-md-2">
                        <img src="img/<%=repres.get(0).getUrlImg()%>" data-large="img/<%=repres.get(0).getUrlImg()%>" alt=""
                             style="width:150px;"/>
                    </div>
                    <div class="col-md-6"> 
                        <h1> <%= nomS%> </h1>
                        <ul>
                            <% for (int i = 0; i < repres.size(); i++) {%>                                       
                            <li>
                                <%= repres.get(i).getDate()%> - <%= repres.get(i).getHeure()%>
                                <input type="number" name="nbP" value="0">
                                <input type="checkbox" name="cbNR" value="<%=repres.get(i).getNR()%>">
                            </li>
                            <%} %>
                        </ul>
                    </div>
                </div>
                <% }
                        }
                    }%>

                <button type="submit" >Réserver</button>
                <input hidden="true" name="action" value="addBooking"/>
                <input name="login" value="<%= userName%>" hidden="true">
            </form>
        </div>
    </body>

    <jsp:include page="jsp/footer.jsp"/>

</html>
