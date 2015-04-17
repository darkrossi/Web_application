<%-- 
    Document   : addSalle
    Created on : 13 avr. 2015, 17:23:24
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Ajout Salle</title>
        </head>
        <body>
            <jsp:include page="jsp/navbar.jsp"/>

            <div class="container" >
                <form action="<%=request.getContextPath()%>/controleur" method="get">
                    <button type="submit "> Ajouter salle </button>
                    <input hidden="true" name="action" value="addSalle"/>
                </form>
            </div>
        </body>

        <jsp:include page="jsp/footer.jsp"/>

        <jsp:include page="jsp/head.jsp"/>

        <link rel="stylesheet" type="text/css" href="css/style.css"> 

    </html>
