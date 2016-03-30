<%@ include file="header.html"%>
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
        
<div class="container menu">
    <div class="row">
        <div class="col-lg-8" style="text-align: left;">
            <h1 style="color: white;"><% //insert truck name %></h1>
        </div>
        <div class="col-lg-4" style="text-align: right;">
            <h1 class ="click" style="color: white" data-toggle="modal" data-target="#truckModal"><% //insert dislplay name %></h1>
        </div>
    </div>
    
    <div class="row">
        <% //insert truck address %>
        <!--map business -->
    </div>

    <%@ include file="truckReviewModal.jsp"%>
    
    <%@ include file="itemReviewModal.jsp" %>
    
    <% //for each menu category, include category jsp
        out.print("<div class='panel panel-default'>\n");
            out.print("<div class='panel-heading'>\n");
                out.print("<h1 class='panel-title'>");
                //insert Category Title
                out.print("</h1>\n");
                out.print("<h5 style='font-style: italic'>");
                //insert Category description
                out.print("</h5>\n");
            out.print("</div>\n");
            out.print("<div class='panel-body'>\n");
                out.print("<div class ='container'>\n");
                    //for each item in category
                    out.print("<div class='row-fluid'>\n");                                  
                        out.print("<div class='col-lg-8 itemName'>");
                            //insert Item Name
                        out.print("</div>\n");
                        out.print("<div class='col-lg-2'>");
                            //insert Item Price
                        out.print("</div>\n");
                        out.print("<div class='col-lg-2 click' data-toggle='modal' data-target='#itemModal'>");
                            //insert review stars
                        out.print("</div>\n");                                   
                    out.print("</div>\n");
                out.print("</div>\n");
            out.print("</div>\n");
        out.print("</div>\n");

    
    %>  
    
</div>

<%@ include file="footer.html"%>
