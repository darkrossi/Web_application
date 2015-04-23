<%-- 
    Document   : BddError
    Created on : 17 avr. 2015, 11:39:13
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="3; url=index.jsp" />

        <jsp:include page="jsp/head.jsp"/>

        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <%= request.getAttribute("log")%>
    </body>

    <jsp:include page="jsp/footer.jsp"/>


</html>
