

<%@page import="edu.temple.tutrucks.Truck"%>
<%@page import="edu.temple.tutrucks.TruckReview"%>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reviews</title>
    </head>
    <body>
        <%
            String truckIDstr = request.getParameter("criteria");
            int truckID = Integer.parseInt(truckIDstr);
            Truck t= Truck.getTruckByID(truckID);
            List <TruckReview> reviews=t.getTruckReviews();
            for (TruckReview rev : reviews){
                
            }
            
        %>
    </body>
</html>
