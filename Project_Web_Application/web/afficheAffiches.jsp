<%-- 
    Document   : afficheAffiches
    Created on : 3 avr. 2015, 09:26:21
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="modele.Spectacle"%>
<%@page import="java.util.ArrayList"%>


<html>
    <head><title>JSP Page</title></head>
    <body>
        <% Spectacle spectacle = new Spectacle();
            ArrayList<Spectacle> spectacles = spectacle.getSpectacles(); %>
        <table border>
            <tr>
                <th>Spectacles</th>
            </tr>
            <% for (int i = 0; i < spectacles.size(); i++) {%>
            <tr>
                <td><%=spectacles.get(i).toString()%></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>