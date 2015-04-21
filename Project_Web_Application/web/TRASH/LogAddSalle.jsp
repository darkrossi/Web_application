<%-- 
    Document   : LogAddSalle
    Created on : 13 avr. 2015, 17:32:53
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="3; url=addSalle.jsp" />
        <title>JSP Page</title>

    </head>
    <body>
        <jsp:include page="jsp/navbar.jsp"/>
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