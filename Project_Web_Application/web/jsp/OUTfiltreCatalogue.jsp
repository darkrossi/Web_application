<%-- 
    Document   : OUTfiltreALL
    Created on : 26 avr. 2015, 14:36:28
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<form name="form" action="<%=request.getContextPath()%>/controleur" method="get">
    <dl class="dropdown">
        <dt>
        <a href="#">
            <span><h2>Recherche</h2></span>   
            <p class="multiSel"></p> 
        </a>
        </dt>

        <dd>
            <div class="mutliSelect">
                <ul>
                    <li>
                        <form>
                            <input type="text" name="BarreRecherche" value placeholder="Mots clés">
                            <input type="submit" value="Rechercher">
                        </form>
                    </li>
                </ul>
            </div>
        </dd>
    </dl>

    <dl class="dropdown">
        <dt>
        <a href="#">
            <span><h2>Date</h2></span>   
            <p class="multiSel"></p> 
        </a>
        </dt>

        <dd>
            <div class="mutliSelect">
                <ul>
                    <li>
                        <input type="text" name="datepicker_input1" class="datepicker" value placeholder="Du">
                        <input type="text" name="datepicker_input2" class="datepicker" value placeholder="Au">
                    </li>
                </ul>
            </div>
        </dd>
    </dl>
    <dl class="dropdown">
        <dt>
        <a href="#">
            <span><h2>Prix</h2></span>   
            <p class="multiSel"></p> 
        </a>
        </dt>

        <dd>
            <div class="mutliSelect">
                <ul>
                    <li>
                        <span> De </span>
                        <script type="text/javascript">prix();</script>
                        <span> à </span>
                        <script type="text/javascript">prix();</script>
                        <p> </p>
                    </li>
                </ul>
            </div>
        </dd>
    </dl>

    <dl class="dropdown">
        <dt>
        <a href="#">
            <span><h2>Genre</h2></span>   
            <p class="multiSel"></p> 
        </a>
        </dt>

        <dd>
            <div class="mutliSelect">
                <ul>
                    <li>
                        <input type="checkbox" value="GrandSpectacle" />Grand spectacle</li>
                    <li>
                        <input type="checkbox" value="ComedieMusicale" />Comédie musicale</li>
                    <li>
                        <input type="checkbox" value="SpectacleMagie" />Spectacle de magie</li>
                    <li>
                        <input type="checkbox" value="TheatreContemporain" />Théâtre contemporain</li>
                    <li>
                        <input type="checkbox" value="TheatreEnfant" />Théâtre pour enfants</li>
                    <li>
                        <input type="checkbox" value="TheatreEquestre" />Théâtre équestre</li>
                    <li>
                        <input type="checkbox" value="SeulEnScene" />Seul en scène</li>

                </ul>
            </div>
        </dd>
    </dl>

    <dl class="dropdown">
        <dt>
        <a href="#">
            <span><h2>Popularité</h2></span>   
            <p class="multiSel"></p> 
        </a>
        </dt>

        <dd>
            <div class="mutliSelect">
                <ul>
                    <li>
                        <input type="checkbox" value="MeilleureNote" />Les mieux notés</li>
                    <li>
                        <input type="checkbox" value="GrandSpectacle" />Les plus vendues</li>
                </ul>
            </div>
        </dd>
    </dl>

    <input type="submit" value="Filtrer">
    <input name="action" value="filtrerCatalogue" hidden="true">
</form>