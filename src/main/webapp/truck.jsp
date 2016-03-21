<%@ include file="header.html"%>

<table>
    <tr>
        <td>
            <% //insert truck name %>
        </td>
        <td>
            <table>
                <tr>
                    <td>
                        Average Review
                    </td>
                </tr>
                <tr>
                    <td>
                        <% //review in # of stars %>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
<%
    //for each item in the truck has
    out.print("<tr><td>");
    //insert item name with a link that will cause a popover with additional info
    out.print("</td><td>");
    //insert price
    out.print("</td><td>");
    //insert review in # of stars with link for popover and shift to reviews
    out.print("</td>/tr>");
    //loop
%>

</table>

<%@ include file="footer.html"%>