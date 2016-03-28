<%@page import="com.google.gson.Gson"%>
<%@ include file="header.html"%>
<%@ page import="edu.temple.tutrucks.*, java.util.List, com.google.gson.JsonArray" %>

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
        String format = (String)request.getParameter("format");
        List<Searchable> results = DBUtils.searchAll(search);
        if (format != null && format.equalsIgnoreCase("json")) {
            JsonArray tbp = new JsonArray();
            for (Searchable s : results) {
                tbp.add(s.getSearchName());
            }
            Gson gson = new Gson();
            out.print(gson.toJson(tbp));
        }
    %>
</table>
<%@ include file="footer.html"%>