<%@ include file="header.html"%>
<%@ page import="edu.temple.tutrucks.*, java.util.List" %>

<table>
    <%--
    For each truck matching search criteria:
    put truck image in left-side of tile
    put truck name in Title slot on right side
    put truck location in location slot on right side
    put rating in rating slot on right side
    --%>
    
    <%
        String search = (String)request.getParameter("criteria");
        if(search==null){
            search="";
        }
        String searchType = null;
        if (search.contains(":")) {
            String s[] = search.split(":");
            searchType = s[0];
            search = s[1];
        }
        List<Searchable> results;
        switch (searchType) {
            case "truck":
                
                break;
            case "tag":
                
                break;
            case "item":
                
                break;
        }
        List<Truck> trucks = null;
        
        for (Truck t : trucks) {
            out.print("<tr>\n<td>");
            //Insert Image of Truck Here
            out.print("</td>\n<td>\n<table>\n<tr><td>");
            //Truck Title
            out.print(t.getTruckName());
            out.print("</td>\n<td>\n<table>\n<tr><td>");
            //Insert Rating Image Here
            out.print("</td></tr>\n</table>\n</td>\n</tr>");
            //end loop
        }
    %>
</table>
<%@ include file="footer.html"%>