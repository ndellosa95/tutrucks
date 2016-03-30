<%@ page import="edu.temple.tutrucks.*, java.util.List, com.google.gson.Gson, com.google.gson.JsonArray" %>
<%@ include file="header.html"%>
<table>
    <%--
    For each truck matching search criteria:
    put truck image in left-side of tile
    put truck name in Title slot on right side
    put truck location in location slot on right side
    put rating in rating slot on right side
    --%>
</table>
<%@ include file="footer.html"%>
<%
        String search = (String)request.getParameter("criteria");
        String format = (String)request.getParameter("format");
        List<Searchable> results = DBUtils.searchAll(search);
        System.out.println("search returns");
        if (format != null && format.equalsIgnoreCase("json")) {
            JsonArray tbp = new JsonArray();
            for (Searchable s : results) {
                tbp.add(s.getSearchName());
            }
            Gson gson = new Gson();
            String s = gson.toJson(tbp);
            out.clearBuffer();
            out.print(s);
        }
%>