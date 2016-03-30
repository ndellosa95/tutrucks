<%@ include file="header.html"%>
<div class="container profile">
    <div class="col-md-4">
        <img class = "avatar" src="<% //insert avatar pic %>">
        <h3><% //insert display name %></h3>
        <h5><% //number of truck reviews %> truck reviews</h5>
        <h5><% //number of item reviews %> item reviews</h5>
    </div>
    <div class="col-md-8">
        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h1 class="panel-title">Truck Reviews</h1>
                </div>
                <div class="panel-body">
                    <div class ="container-fluid">                
                       <%
                            //<jsp:foreach items="truck review">
                                //<jsp:include template="truckReview.jsp"/>
                            //</jsp:foreach>
                                
                       %>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h1 class="panel-title">Item Reviews</h1>
                </div>
                <div class="panel-body">
                    <div class ="container-fluid">
                         <%
                            //<jsp:foreach items="truck review">
                                //<jsp:include template="truckReview.jsp"/>
                            //</jsp:foreach>
                                
                        %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.html"%>
