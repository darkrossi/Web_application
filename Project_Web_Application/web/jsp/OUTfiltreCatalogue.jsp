<%-- 
    Document   : OUTfiltreALL
    Created on : 26 avr. 2015, 14:36:28
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<form name="form" action="<%=request.getContextPath()%>/controleur" method="post">

    <!-- RECHERCHE PAR MOTS CLES -->
    <dl class="dropdown">
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

<!--     RECHERCHE PAR GENRE 
    <dl class="dropdown">
        <dt>
        <a href="#">
            <span><h3>Genre</h3></span>   
            <p class="multiSel"></p> 
        </a>
        </dt>
        <dd>
            <div class="mutliSelect">
                <ul>
                    <li>
                        <input name="checkGenre" type="checkbox" value="GrandSpectacle" />Grand spectacle</li>
                    <li>
                        <input name="checkGenre" type="checkbox" value="ComedieMusicale" />Comédie musicale</li>
                    <li>
                        <input name="checkGenre" type="checkbox" value="SpectacleMagie" />Spectacle de magie</li>
                    <li>
                        <input name="checkGenre" type="checkbox" value="TheatreContemporain" />Théâtre contemporain</li>
                    <li>
                        <input name="checkGenre" type="checkbox" value="TheatreEnfant" />Théâtre pour enfants</li>
                    <li>
                        <input name="checkGenre" type="checkbox" value="TheatreEquestre" />Théâtre équestre</li>
                    <li>
                        <input name="checkGenre" type="checkbox" value="SeulEnScene" />Seul en scène</li>

                </ul>
            </div>
        </dd>
    </dl>

     RECHERCHE PAR POPULARITÉ 
    <dl class="dropdown">
        <dt>
        <a href="#">
            <span><h3>Popularité</h3></span>   
            <p class="multiSel"></p> 
        </a>
        </dt>
        <dd>
            <div class="mutliSelect">
                <ul>
                    <li>
                        <input name="checkPop" type="checkbox" value="MeilleureNote" />Les mieux notés</li>
                    <li>
                        <input name="checkPop" type="checkbox" value="GrandSpectacle" />Les plus vendues</li>
                </ul>
            </div>
        </dd>
    </dl>-->

    <input type="submit" value="Filtrer">
    <input name="action" value="filtrerCatalogue" hidden="true">
</form>