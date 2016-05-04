<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="edu.temple.tutrucks.Truck"%>
<%@page import="edu.temple.tutrucks.Tag"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="adminAuth.jsp"%>
<style>
    body{
        text-shadow: none;
        color: white;
    }

    .panel .panel-heading, .panel-body{
        background-color: #A41E35;
        border: solid 5px white;
    }

    .panel-body {
        border-top: none;
    }

    .top-buffer {
        margin-top: 10px;
    }

    .remove {
        background-color: #A41E35;
        color: #999;
        border: solid 3px #999;

    }
    .remove:hover {
        background-color: #999;
        color: #A41E35;
        border: solid 3px #999;
    }

    .addButton {
        background-color: #A41E35;
        color: white;
        border: solid 3px white;
    }

    .addButton:hover {
        background-color: white;
        color: #A41E35;
        border: solid 3px white;
    }
</style>
<div class="container">
    <form>
        <fieldset class="form-group form-inline">
            <label>Select a Truck to Edit</label>
            <select class="form-control" name="truckName">
                <%  Truck selected = new Truck();
                    boolean submitted = false;
                    if (request.getParameter("submitted") != null) {
                        selected = Truck.getTruckByID(Integer.parseInt(request.getParameter("truckName")));
                        submitted = true;
                    }
                    List<Truck> list = Truck.getAllTrucks();
                    for (Truck t : list) {
                        if (t.getId() == selected.getId()) {
                            out.print("<option value='" + t.getId() + "'selected>" + t.getTruckName() + "</option>");
                        }
                        else {
                            out.print("<option value='" + t.getId() + "'>" + t.getTruckName() + "</option>");
                        }
                    }
                %>
            </select>
            <button class="btn btn-primary" name="submitted">Submit</button>    
        </fieldset>
    </form>

</div>
<br><br>
<%if (!submitted) out.print("<!--");%>
<div class="container">
    <form id="form" action="uploadImage" method="POST" enctype="multipart/form-data">
        <input type="hidden" name="truckId" value=<%out.print("'" + selected.getId() + "'");%>>
        <fieldset class="form-group">
            <div class="col-sm-4">
                <label for="truckName">Truck Name</label>
            </div>
            <div class="col-sm-8">
                <input type="text" placeholder="Name" class="form-control" readonly name="truckName" 
                       <%
                           if (submitted) {
                               out.print("value = '" + selected.getTruckName() + "'");
                           }
                       %>
                       >
            </div>
        </fieldset>
        <fieldset class="form-group">
            <div class="col-sm-4">
                <label for="truckName">Upload Image</label>
            </div>
            <div class="col-sm-8">
                <input type="file" name="image">
                <input type="hidden" name="type" value="truck">
                <input type="hidden" name="id" value="<%=selected.getId()%>">
            </div>
        </fieldset>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</div>
<%if (!submitted) out.print("-->");%>
<%@ include file="footer.html"%>
