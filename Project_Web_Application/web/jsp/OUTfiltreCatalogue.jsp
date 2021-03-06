<%-- 
    Document   : OUTfiltreALL
    Created on : 26 avr. 2015, 14:36:28
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<form name="form" action="<%=request.getContextPath()%>/controleur" method="post">

    <!-- RECHERCHE PAR MOTS CLES -->
    <dl class="">
        <dt>
        <a href="#">
            <span><h3>Recherche par mots clés</h3></span>   
            <p class="multiSel"></p> 
        </a>
        </dt>

        <dd>
            <div class="mutliSelect">
                <ul>
                    <li>

                        <input type="text" name="motscles" placeholder="Mots clés" value="">

                    </li>
                </ul>
            </div>
        </dd>
    </dl>

    <!--RECHERCHE PAR DATE -->
    <dl class="">
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
                        <input type="text" id="datepicker1" name="datepicker1" class="datepicker" value="" placeholder="Du">
                        <input type="text" id="datepicker2" name="datepicker2" class="datepicker" value="" placeholder="Au">
                    </li>
                </ul>
            </div>
        </dd>
    </dl>

    <!-- RECHERCHE PAR PRIX -->
    <dl class="">
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

    <input type="submit" value="Filtrer">
    <input name="action" value="filtrerCatalogue" hidden="true">
</form>