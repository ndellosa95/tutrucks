<%
if (user == null) response.sendError(HttpServletResponse.SC_FORBIDDEN, "You must be logged in to access this page");
else if (user.getPermissions()!=Permissions.ADMIN)  response.sendError(HttpServletResponse.SC_FORBIDDEN, "You must be an administrator to access this page");
%>