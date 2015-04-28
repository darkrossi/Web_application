<%-- 
    Document   : pieces
    Created on : 8 avr. 2015, 10:39:44
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <jsp:include page="jsp/head.jsp"/>

        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/stylePieces.css" />

        <script>
            function init() {
                dateToday = new Date();
                $("#datepicker1").datepicker({
                    dateFormat: "dd-mm-yy",
                    minDate: dateToday,
                    onSelect: function (dateValue, inst) {
                        $("#datepicker2").datepicker("option", "minDate", dateValue)
                    }
                });
                $("#datepicker2").datepicker({
                    dateFormat: "dd-mm-yy",
                    minDate: dateToday,
                });
            }
        </script>

    </head>
    <body onload="init()">
        <div class="container-fluid" >

            <jsp:include page="jsp/navbar.jsp"/>

            <div class="row">
                <div class="col-md-4">
                    <jsp:include page="jsp/OUTfiltreCatalogue.jsp"/>
                </div>   

                <div class="col-md-5">
                    <jsp:include page="jsp/OUTdisplayCatalogue.jsp"/>
                </div>
            </div>
        </div>
    </body>

    <script src="js/multiple.js" type="text/javascript"></script>
    <jsp:include page="jsp/footer.jsp"/>

</html>
