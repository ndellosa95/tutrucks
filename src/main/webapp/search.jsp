<%@ include file="header.html"%>

<table>
<%--
For each truck matching search criteria:
put truck image in left-side of tile
put truck name in Title slot on right side
put truck location in location slot on right side
put rating in rating slot on right side
--%>
<%
out.print("<tr>\n<td>");
//Insert Image of Truck Here
out.print("</td>\n<td>\n<table>\n<tr><td>");
//Insert Truck Title Here
out.print("</td>\n<td>\n<table>\n<tr><td>");
//Insert Rating Image Here
out.print("</td></tr>\n</table>\n</td>\n</tr>");
//end loop
%>
</table>
<%@ include file="footer.html"%>