<%-- 
    Document   : OUTfiltreResa
    Created on : 26 avr. 2015, 14:36:50
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<form name="form" action="<%=request.getContextPath()%>/controleur" method="get">
    <h2>Date</h2>
    <input type="text" name="datepicker_input1" class="datepicker" placeholder="Du">
    <input type="text" name="datepicker_input2" class="datepicker" placeholder="Au">

    <h2>Prix</h2>

    <h2>Ventes</h2>

    <input type="submit" value="Filtrer">
    <input name="action" value="filtrerResa" hidden="true">
</form>
