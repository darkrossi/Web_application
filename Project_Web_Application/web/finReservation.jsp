<%@page import="modele.Spectacle"%>
<%@page import="modele.Place"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@page import="modele.Representation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <jsp:include page="jsp/head.jsp"/>
        <script type="text/javascript" src="js/etoile.js"></script>
        <link rel="stylesheet" type="text/css" href="css/etoile.css">
        <link rel="stylesheet" type="text/css" href="css/style.css"> 
        <script src="js/functions.js" type="text/javascript"></script>

    </head>
    <style>
        span {
            margin-left: auto;
            margin-right: auto;
        }
    </style>
    <body>
        <div class="row">
            <div class="col-md-3">
            </div>
            <div class="col-md-6">
                <h1> Comed'Imag vous remercie de votre achat ! </h1>
                <span> Aidez nous à améliorer notre service en le notant !
                </span>
                <div id="etoile">
                    <span> Votre note : </span>
                    <script type="text/javascript">
                        CreateListeEtoile('etoile', 5);
                    </script>
                </div>
                <p>
                <input type="SUBMIT">
                </p>
            </div>
        </div>



    </body>
</html>
