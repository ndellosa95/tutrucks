<%@ page import="edu.temple.tutrucks.*, java.util.List, com.google.gson.Gson, com.google.gson.JsonArray" %>

<%--
For each truck matching search criteria:
put truck image in left-side of tile
put truck name in Title slot on right side
put truck location in location slot on right side
put rating in rating slot on right side
--%>
<%@ include file="footer.html"%>
<%
        String search = request.getParameter("criteria");
        String format = request.getParameter("format");
        int numResults = request.getParameter("numResults") == null ? -1 : Integer.parseInt(request.getParameter("numResults"));
        boolean subs = request.getParameter("subscripts") == null ? false : Boolean.parseBoolean(request.getParameter("subscripts"));
        List<Searchable> results = DBUtils.searchAll(search);
        if (format != null && format.equalsIgnoreCase("json")) {
            JsonArray tbp = new JsonArray();
            if (numResults < 0) {
                numResults = results.size();
            }

            for (int i = 0; i < numResults; i++) {
                String sn = results.get(i).getSearchName();
                String html = "<span class='ac_subtext'>*</span>";
                if (subs) {
                    sn += html.replace("*", results.get(i) instanceof Item
                            ? "at " + (((Item) results.get(i)).getMenu().getTruck().getTruckName())
                            : results.get(i).getClass().getSimpleName());
                }
                tbp.add(sn);
            }
            Gson gson = new Gson();
            String s = gson.toJson(tbp);
            out.clearBuffer();
            out.print(s);
        } else {
            Truck t;
            search = (String) request.getParameter("criteria");
            results = DBUtils.searchAll(search);
            out.print("<div class='panel panel-default'>\n");
            out.print("<div class='panel-heading'>\n");
            out.print("<h1 class='panel-title'>");
            out.print("Trucks");
            out.print("</h1>\n");
            out.print("<h5 style='font-style: italic'>");
            out.print("Trucks matching search criteria");
            out.print("</h5>\n");
            out.print("</div>\n");
            out.print("<div class='panel-body'>\n");
            for (Searchable s : results) {
                t = (Truck) s;
                out.print("<div class ='container'>\n");

                out.print("<div class='row-fluid'>\n");
                out.print("<div class='col-lg-2 truckPhoto'>");
                out.print("PHOTO OF TRUCK");
                out.print("</div>\n");
                out.print("<div class='col-lg-4 truckName'>");
                out.print(t.getTruckName());
                out.print("</div>\n");
                out.print("<div class='col-lg-4'>");
                out.print("Location");
                out.print("</div>\n");
                out.print("<div class='col-lg-2 click' data-toggle='modal' data-target='#reviewmodal'>");
                out.print("REVIEW IN STARS");
                out.print("</div>\n");
                out.print("</div>\n");

                out.print("</div>\n");
            }
            out.print("</div>\n");
            out.print("</div>\n");
        }
%>    