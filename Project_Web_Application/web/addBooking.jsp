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
                <div class="row">
                    <% if (request.getAttribute("repres") != null) {
                            Hashtable<String, Representation> hashRepres = (Hashtable<String, Representation>) request.getAttribute("repres");
                            if (!hashRepres.isEmpty()) {
                                Enumeration keys = hashRepres.keys();
                                while (keys.hasMoreElements()) {
                                    String nomS = (String) keys.nextElement();
                                    List<Representation> repres = (List<Representation>) hashRepres.get(nomS);%>
                    <div class="col-md-2">
                        <img src="img/<%=repres.get(0).getUrlImg()%>" data-large="img/<%=repres.get(0).getUrlImg()%>" alt="" data-description="" />
                    </div>
                    <div class="col-md-2"> 
                        <h1> <%= nomS%> </h1>
                        <ul>
                            <% for (int i = 0; i < repres.size(); i++) {%>                                       
                            <li><%= repres.get(i).getDate()%> - <%= repres.get(i).getHeure()%> <input type="checkbox"> </li>
                                <%} %>
                        </ul>
                        <% }
                                }
                            }%>


                        <%-- <select id="selectSalle" onchange="
                                select_menu = document.getElementById('selectSalle');
                                document.getElementById('valueSalle').value = select_menu.options[select_menu.selectedIndex].value;">
                            <% if (request.getAttribute("salles") != null) {
                                    List<Salle> salles = (List<Salle>) request.getAttribute("salles");
                            %>
                            <% if (!salles.isEmpty()) {
                                    for (int i = 0; i < salles.size(); i++) {%>
                            <option value="<%=salles.get(i).getNSa()%>"><%=salles.get(i).getNSa()%></option>
                            <%}
                                    }
                                }%>
                        </select> 
                        <input id="valueSalle" name="valueSalle" hidden="true"> --%>

                    </div>
                </div>
            </form>
        </div>
    </body>

    <jsp:include page="jsp/footer.jsp"/>

</html>
