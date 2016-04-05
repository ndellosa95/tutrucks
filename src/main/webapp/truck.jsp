<%@ include file="header.html"%>
<%@ page import="edu.temple.tutrucks.*" %>
<%@ page import="java.util.List, java.util.Set,java.util.Locale,java.text.NumberFormat"%>
<style>
    .panel-heading {
        background-color: black;
    }
    .panel-title {
        color: #A41E35;
    }
    .panel-body {
        color: black;
    }

    .itemName {
        text-align: left;
        color: #A41E35;
    }

    .modal-header{
        background-color:  #A41E35;
        color: white;
    }

    .modal-body {
        color: black;
    }

    .click{
        cursor: pointer;
    }

</style>

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
            <h1 style="color: white;"><%=truckName%></h1>
        </div>
        <div class="col-lg-4" style="text-align: right;">
            <h1 class ="click" style="color: white" data-toggle="modal" data-target="#truckModal">Avg. Reviews</h1>
        </div>
    </div>

    <div class="row">
        <!--map business -->
    </div>

    <%-- //include truck review modal--%>
    <jsp:include page='truckReviewModal.jsp'>
        <jsp:param name="search" value="<%=truckName%>"/>
    </jsp:include>

    

    <%-- //include item review modal--%>

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
                    if (name != null && name != "") {
                        out.print(name);
                    } else {
                        out.print("Menu");
                    }
                %>

            </h1>
            <h5 style="font-style: italic"> 
                <%
                    String description = category.getDescription();
                    if (description != null && description != "") {
                        out.print(description);
                    }
                %>
            </h5>
        </div>

        <div class="panel-body">
            <div class ="container">
                <%
                    String itemName;
                    double price;
                    Set<Item> items = category.getItems();
                    for (Item item : items) {
                        if (item == null) {
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
                    <div class="col-lg-2 click" data-toggle="modal" data-target="#itemModal">
                        <% //insert average stars
                            double stars = 0;
                            double averageStars = 0;
                            List<ItemReview> reviews = item.getItemReviews();

                            if (reviews.size() > 0) {
                                for (ItemReview review : reviews) {
                                    stars += (double) review.getReviewStars() / 2;
                                }

                                averageStars = stars / reviews.size();
                                out.print(averageStars);
                            }
                            out.print("4 stars");
                        %>
                    </div>                                   
                </div> 
                <!--end menuItem.jsp-->
                <% } %>
            </div>
        </div>
    </div>
    <!--end category.jsp-->
    <% }%>
</div>

<%@ include file="footer.html"%>
