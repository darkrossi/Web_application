<%-- 
    Document   : LogAddUser
    Created on : 14 avr. 2015, 11:01:32
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="3; url=index.jsp">
        <title>JSP Page</title>

    </head>
    <body>
        <% if ((int) request.getAttribute("bool") == 1) { %>
        Compte créé avec succès
        <% } else { %>
        Erreur lors de la création du compte
        <%}%>    
    </body>

    <jsp:include page="jsp/footer.jsp"/>

    <jsp:include page="jsp/head.jsp"/>

    <link rel="stylesheet" type="text/css" href="css/style.css"> 
</html>
