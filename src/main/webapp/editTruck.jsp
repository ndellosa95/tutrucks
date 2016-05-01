<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="edu.temple.tutrucks.Truck"%>
<%@page import="edu.temple.tutrucks.Tag"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<div class="container">
    <form>
        <fieldset class="form-group form-inline">
            <label>Select a Truck to Edit</label>
            <select class="form-control" name="truckName">
                <%
                    Truck selected = new Truck();
                    boolean submitted = false;
                    List<String> list = Truck.getAllTruckNames();
                    for (String s : list) {
                        out.print("<option>" + s + "</option>");
                    }
                %>
            </select>
            <button class="btn btn-primary" name="submitted">Submit</button>    
        </fieldset>
    </form>

    <%
        if (request.getParameter("submitted") != null) {
            selected = Truck.getTruckByName(request.getParameter("truckName"));
            submitted = true;
        }
    %>
</div>
<br><br>
<div class="container">
    <form id="form">
        <input type="hidden" name="edittedTruck" value=<%out.print("'" + selected.getTruckName() + "'");%>>
        <fieldset class="form-group">
            <div class="col-sm-4">
                <label for="truckName">Truck Name</label>
            </div>
            <div class="col-sm-8">
                <input type="text" placeholder="Name" class="form-control" name="truckName" 
                       <%
                           if (submitted) {
                               out.println("value = '" + selected.getTruckName() + "'");
                           }
                       %>
                       >
            </div>
        </fieldset>
        <fieldset class="form-group">
            <div class="col-sm-4">
                <label for="location">Truck Location</label>
            </div>
            <div class="col-sm-4">
                <input type="text" placeholder="Latitude" class="form-control" name="latitude"
                       <%
                           if (submitted) {
                               out.println("value = '" + selected.getLatitude() + "'");
                           }
                       %>
                       >
            </div>
            <div class="col-sm-4">
                <input type="text" placeholder="Longitude" class="form-control" name="longitude"
                       <%
                           if (submitted) {
                               out.println("value = '" + selected.getLongitude() + "'");
                           }
                       %>
                       >
            </div>
        </fieldset>
        <fieldset class="form-group">
            <div class="col-sm-4">
                <label for="location">Hours of Operation</label>
                <small class="text-muted" style="display:block">Something something format</small>
            </div>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="openTime" placeholder="Opening Time"
                       <%
                           if (submitted) {
                               out.println("value = '" + selected.getOpeningTime().toString() + "'");
                           }
                       %>
                       >
            </div>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="closeTime" placeholder="Closing Time" <%
                    if (submitted) {
                        out.println("value = '" + selected.getClosingTime() + "'");
                    }
                       %>
                       >
            </div>                      
        </fieldset>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</div>
<%@ include file="footer.html"%>
<script>
    $("#form").submit(function (e) {
    var truckName = $(e.target).find('[name=truckName]').val();
    var edittedTruck = $(e.target).find('[name=edittedTruck]').val();
    var latitude = $(e.target).find('[name=latitude]').val();
    var longitude = $(e.target).find('[name=longitude]').val();
    var openTime = $(e.target).find('[name=openTime]').val();
    var closeTime = $(e.target).find('[name=closeTime]').val();
    
    $.ajax({
            type: "POST",
            url: "/EditTruckServlet",
            data: {oldName: edittedTruck, newName: truckName, lat: latitude, lng: longitude, open: openTime, close: closeTime},
            async: false,
            success: function (data) {
                alert(data);
            },
            error: function(error) {
                alert("There was an error.");
                console.log(error);
            }
    });

});
</script>