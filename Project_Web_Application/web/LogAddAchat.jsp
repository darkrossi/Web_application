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
        <meta http-equiv="refresh" content="3; url=addBooking.jsp" />
        <title>JSP Page</title>
    </head>

    <body>
        <% if ((int) request.getAttribute("bool") == 1) { %>
        <h1>GOOD</h1>
        <% } else { %>
        <h1>BAD</h1>
        <%}%>
    </body>

    <jsp:include page="jsp/footer.jsp"/>

    <jsp:include page="jsp/head.jsp"/>

    <link rel="stylesheet" type="text/css" href="css/style.css"> 
</html>
