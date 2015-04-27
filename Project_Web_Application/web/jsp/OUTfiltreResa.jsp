<%-- 
    Document   : OUTfiltreResa
    Created on : 26 avr. 2015, 14:36:50
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<form name="form" action="<%=request.getContextPath()%>/controleur" method="get">
    <!-- RECHERCHE PAR DATE -->
    <dl class="dropdown">
        <dt>
        <a href="#">
            <span><h3>Date</h3></span>   
            <p class="multiSel"></p> 
        </a>
        </dt>

        <dd>
            <div class="mutliSelect">
                <ul>
                    <li>
                        <input type="text" name="datepicker1" class="datepicker" value="" placeholder="Du">
                        <input type="text" name="datepicker2" class="datepicker" value="" placeholder="Au">
                    </li>
                </ul>
            </div>
        </dd>
    </dl>

    <!-- RECHERCHE PAR PRIX -->
    <dl class="dropdown">
        <dt>
        <a href="#">
            <span><h3>Prix</h3></span>   
            <p class="multiSel"></p> 
        </a>
        </dt>
        <dd>
            <div class="mutliSelect">
                <ul>
                    <li>
                        <span> De </span>
                        <input type="text" name="prixDe" value="">
                        <span> à </span>
                        <input type="text" name="prixA" value="">
                        <p> </p>
                    </li>
                </ul>
            </div>
        </dd>
    </dl>

    <!--RECHERCHE PAR DATE -->
    <dl class="dropdown">
        <dt>
        <a href="#">
            <span><h3>Date</h3></span>   
            <p class="multiSel"></p> 
        </a>
        </dt>

        <dd>
            <div class="mutliSelect">
                <ul>
                    <li>
                        <input type="text" name="datepicker1" class="datepicker" value="" placeholder="Du">
                        <input type="text" name="datepicker2" class="datepicker" value="" placeholder="Au">
                    </li>
                </ul>
            </div>
        </dd>
    </dl>

    <input type="submit" value="Filtrer">
    <input name="action" value="filtrerResa" hidden="true">
</form>
