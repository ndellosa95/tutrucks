<%@page import="edu.temple.tutrucks.ItemReview"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.temple.tutrucks.Menu"%>
<%@page import="edu.temple.tutrucks.Item"%>
<%@page import="java.util.List"%>
<%@page import="edu.temple.tutrucks.Truck"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="adminAuth.jsp"%>
<div class="container">
    <h3>Select a truck/item to delete a review from:</h3>
    <form>
        <div class="col-md-3">
            <select class="form-control" name="truckName">
                <%  Truck selectTruck = new Truck();
                    boolean selectedTruck = false;
                    boolean selectedItem = false;
                    if (request.getParameter("submitTruck") != null) {
                        selectTruck = Truck.getTruckByID(Integer.parseInt(request.getParameter("truckName")));
                        selectedTruck = true;
                        selectedItem = false;
                    }
                    List<Truck> list = Truck.getAllTrucks();
                    for (Truck t : list) {
                        if (t.getId() == selectTruck.getId()) {
                            out.print("<option value='" + t.getId() + "'selected>" + t.getTruckName() + "</option>");
                        } else {
                            out.print("<option value='" + t.getId() + "'>" + t.getTruckName() + "</option>");
                        }
                    }
                %>
            </select>
        </div>
        <div class="col-md-3">
            <button class="btn btn-primary" name="submitTruck">Select Truck</button>
        </div>
        <div class="col-md-3">
            <select class="form-control" name="itemName">
                <%
                    Item selectItem = new Item();
                    if (selectedTruck) {
                        List<Menu> menus = selectTruck.getMenus();
                        Set<Item> items = new HashSet();
                        for (Menu m : menus) {
                            if (m == null) {
                                continue;
                            }
                            items.addAll(m.getItems());
                        }

                        for (Item i : items) {
                            if (i.getId() == selectItem.getId()) {
                                out.print("<option value='" + i.getId() + "'selected>" + i.getItemName() + "</option>");
                            } else {
                                out.print("<option value='" + i.getId() + "'>" + i.getItemName() + "</option>");
                            }
                        }
                    }
                    if (request.getParameter("submitItem") != null) {
                        selectedItem = true;
                        selectedTruck = false;
                    }
                %>
            </select>
        </div>
        <button class="btn btn-primary" name="submitItem">Select Item</button>
    </form>
    <div class="container col-md-8 col-md-push-2" style="padding-top: 20px;">
        <%
            if (selectedItem) {
                List<ItemReview> reviews = selectItem.loadReviews().getItemReviews();
                for (ItemReview r : reviews) {
                    out.print("<div class='panel panel-default'>"
                            + "<div class='panel-body'>"
                            + "<div class='row-fluid'>"
                            + "<div class='col-sm-10'>"
                            + "<h4 class='pull-left'>"
                            + r.getUser().getDisplayName()
                            + "/h4>"
                            + "</div>"
                            + "<div class='col-sm-2'>"
                            + "<button class='btn btn-danger'>Delete</button>"
                            + "</div>"
                            + "</div>"
                            + "<div class='row-fluid'>"
                            + "<div class='col-sm-12'>"
                            + "<h5 class='pull-left'>"
                            + r.getReviewText()
                            + "</h5>"
                            + "</div>                   "
                            + "</div>"
                            + "</div>"
                            + "</div>");
                }
            } else if (selectedTruck) {
                List<ItemReview> reviews = selectItem.loadReviews().getItemReviews();
                for (ItemReview r : reviews) {
                    out.print("<div class='panel panel-default'>"
                            + "<div class='panel-body'>"
                            + "<div class='row-fluid'>"
                            + "<div class='col-sm-10'>"
                            + "<h4 class='pull-left'>"
                            + r.getUser().getDisplayName()
                            + "/h4>"
                            + "</div>"
                            + "<div class='col-sm-2'>"
                            + "<button class='btn btn-danger'>Delete</button>"
                            + "</div>"
                            + "</div>"
                            + "<div class='row-fluid'>"
                            + "<div class='col-sm-12'>"
                            + "<h5 class='pull-left'>"
                            + r.getReviewText()
                            + "</h5>"
                            + "</div>                   "
                            + "</div>"
                            + "</div>"
                            + "</div>");
                }

            }

        %>
        <div class='panel panel-default'>
            <div class='panel-body'>
                <div class='row-fluid'>
                    <div class='col-sm-10'>
                        <h4 class='pull-left'>User</h4>
                    </div>
                    <div class='col-sm-2'>
                        <button class='btn btn-danger'>Delete</button>
                    </div>
                </div>
                <div class='row-fluid'>
                    <div class='col-sm-12'>
                        <h5 class='pull-left'>Review text</h5>
                    </div>                   
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.html" %>
