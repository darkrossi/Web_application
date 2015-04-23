<%-- 
    Document   : login
    Created on : 9 avr. 2015, 10:12:00
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <jsp:include page="jsp/head.jsp"/>

        <link rel="stylesheet" type="text/css" href="css/style.css"> 

    </head>
    <body>

        <jsp:include page="jsp/navbar.jsp"/>

        <!-- CONTAINER BOOTSTRAP -->
        <div class="container" >

            <div class="jumbotron">
                <h1>Connectez-vous à votre compte</h1>    
                <FORM ACTION="<%=request.getContextPath()%>/controleur" METHOD="get">
                    <div class="row">
                        <label class="col-md-3"> Indiquez votre login : </label><input class="col-md-2" name="loginU" autofocus="autofocus"><br>
                    </div>
                    <div class="row">
                        <label class="col-md-3"> Indiquez votre mot de passe : </label><input class="col-md-2" name="mdpU" type="PASSWORD"><br>
                    </div>
                    <% if ((int) request.getAttribute("errorLogin") == 1) {%>
                    <font size="5px" color="red"><span class="glyphicon glyphicon-alert"></span> Le login ou le mot de passe saisi est incorrect</font><br>
                    <!--<div> <span class="glyphicon glyphicon-alert"></span> <p style="color: red;">Le login ou le mot de passe saisi est incorrect</p> </div>-->
                    <%}%>
                    <input type="SUBMIT">
                    <input hidden="true" name="action" value="verifUser">

                </FORM>
            </div>
        </div>
    </body>

    <jsp:include page="jsp/footer.jsp"/>

</html>
