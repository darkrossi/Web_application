<%-- 
    Document   : head
    Created on : 8 avr. 2015, 10:40:49
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- JQuery -->
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script src="js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.theme.min.css"> 
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.structure.min.css"> 
<!-- -->    

<!-- PopUp -->
<script src="js/jquery.reveal.js" type="text/javascript"></script>
<link href="css/reveal.css" rel="stylesheet" type="text/css"/>
<!-- -->

<!-- Carousel -->
<link href="css/elastislide.css" rel="stylesheet" type="text/css"/>
<link href="css/reset.css" rel="stylesheet" type="text/css"/>
<link href="css/styleCar.css" rel="stylesheet" type="text/css"/>
<script src="js/gallery.js" type="text/javascript"></script>
<script src="js/jquery.easing.1.3.js" type="text/javascript"></script>
<script src="js/jquery.elastislide.js" type="text/javascript"></script>
<script src="js/jquery.tmpl.min.js" type="text/javascript"></script>
<!-- -->

<!-- Bootstrap -->
<script type="text/javascript" src="js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<!-- -->

<script id="img-wrapper-tmpl" type="text/x-jquery-tmpl">	
    <div class="rg-image-wrapper">
    {{if itemsCount > 1}}
    <div class="rg-image-nav">
    <a href="#" class="rg-image-nav-prev">Previous Image</a>
    <a href="#" class="rg-image-nav-next">Next Image</a>
    </div>
    {{/if}}
    <div class="rg-image"></div>
    <div class="rg-loading"></div>
    <div class="rg-caption-wrapper">
    <div class="rg-caption" style="display:none;">
    <p></p>
    </div>
    </div>
    </div>
</script>
<!-- -->
