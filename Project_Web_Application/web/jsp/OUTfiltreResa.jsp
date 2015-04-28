<%-- 
    Document   : OUTfiltreResa
    Created on : 26 avr. 2015, 14:36:50
    Author     : oswald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
                <li> <% if (request.getAttribute("datepicker1") != null && request.getAttribute("datepicker2") != null 
                        && !((String)request.getAttribute("datepicker1")).equals("null") && !((String)request.getAttribute("datepicker2")).equals("null")) {%>
                    <input type="text" name="datepicker1" class="datepicker" value="<%=request.getAttribute("datepicker1")%>" placeholder="Du">
                    <input type="text" name="datepicker2" class="datepicker" value="<%=request.getAttribute("datepicker2")%>" placeholder="Au">
                    <% } else {%>
                    <input type="text" name="datepicker1" class="datepicker" value="" placeholder="Du">
                    <input type="text" name="datepicker2" class="datepicker" value="" placeholder="Au">
                    <%}%>
                </li>
            </ul>
        </div>
    </dd>
</dl>