
<%@ include file="header.html"%>
<%@ page import="edu.temple.tutrucks.*" %>
<%@page import="java.util.List, java.util.Set,java.util.Locale,java.text.NumberFormat"%>

<%
    //String search = (String)request.getParameter("truck"); 
    String search = "Bagel Shop";
    Truck truck = Truck.getTruckByName(search);
    String truckName = truck.getTruckName();

    List<Menu> menus = truck.getMenus();
%>

<div class="container menu">
    <div class="row">
        <div class="col-lg-8" style="text-align: left;">
            <h1 style="color: white;"><% out.print(truckName); %></h1>
        </div>
        <div class="col-lg-4" style="text-align: right;">
            <h1 class ="click" style="color: white" data-toggle="modal" data-target="#truckModal"></h1>
        </div>
    </div>

    <div class="row">
        <!--map business -->
    </div>

    <%-- //include truck review modal--%>

    <%-- //include item review modal--%>

    <%-- //for each menu cateogry, include category jsp--%>

    <!--copied from category.jsp-->
    <%
        for (Menu category : menus) {
            //ignore null menu category from db
            if (category == null) {
                continue;
            }
    %>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h1 class="panel-title"> 
                <%
                    String name = category.getMenuName();
                    if (name != null) {
                        out.print(name);
                    } else {
                        out.print("null");
                    }
                %>

            </h1>
            <h5 style="font-style: italic"> <% %></h5>
        </div>

        <div class="panel-body">
            <div class ="container">
                <%
                    String itemName;
                    double price;
                    Set<Item> items = category.getItems();
                    for (Item item : items) { 
                        if(item==null){
                            continue;
                        }
                %>
                <!--copied from menuItem.jsp-->
                <div class="row-fluid">                                  
                    <div class="col-lg-8 itemName">
                        <% 
                            itemName = item.getItemName();
                            out.print(itemName);
                        %>
                    </div>
                    <div class="col-lg-2">
                        <% 
                            price = item.getPrice(); 
                            out.print(NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(price));
                        %>
                    </div>
                    <div class="col-lg-2 click" data-toggle="modal" data-target="#itemModal"><% //insert average stars%></div>                                   
                </div> 
                <% } %>
            </div>
        </div>
    </div>
    <% } %>
</div>

<%@ include file="footer.html"%>