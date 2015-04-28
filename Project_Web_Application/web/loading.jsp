<%-- 
    Document   : loading
    Created on : 29 avr. 2015, 00:24:21
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:forward page="/controleur" >
            <jsp:param name="action" value="loadingIndex" />
        </jsp:forward>
    </body>
</html>
