<%-- 
    Document   : logAddAchat
    Created on : 20 avr. 2015, 12:24:26
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% if ((int) request.getAttribute("loginNull") == 0) { %>
        <meta http-equiv="refresh" content="2; url=addBooking.jsp" />
        <%}%>
        <title>JSP Page</title>
    </head>

    <body>
        <jsp:include page="jsp/navbar.jsp"/>
        <% if ((int) request.getAttribute("loginNull") == 1) { %>
        <h1> Vous devez créer un compte pour pouvoir faire des achats </h1>
        <a href="#" data-reveal-id="myModal"><span class="glyphicon glyphicon-pencil"></span> Sign Up</a>
        <% } else {
            if ((int) request.getAttribute("bool") == 1) { %>
        <h1>GOOD</h1>
        <% } else { %>
        <h1>BAD</h1>
        <%}
            }%>
    </body>

    <jsp:include page="jsp/footer.jsp"/>

    <jsp:include page="jsp/head.jsp"/>

    <link rel="stylesheet" type="text/css" href="css/style.css"> 
</html>
