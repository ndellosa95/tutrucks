<%-- 
    Document   : index
    Created on : Feb 22, 2016, 9:42:19 PM
    Author     : nickdellosa
--%>

<%@page import="edu.temple.tutrucks.User"%>
<% 
    boolean invalidLogin = false;
    try {
        invalidLogin = Boolean.parseBoolean(request.getParameter("invalid"));
    } catch (Exception e) {}
    User user = (User) session.getAttribute("user");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="header.jsp"%>

        <div class="cover-container">

          <div class="inner cover">
            <img src="images/TUtrucksLogo.png" alt="logo" width="75%"/>
            <p class="lead">
            <form action="search.jsp" method="GET">
                <input type="search" id="searchbar" name="criteria" class="textbox" placeholder="Search for trucks or items" />
                <input type="submit" class="search" value="Search"/>
                
            </form>
            <a href="search.jsp?criteria=truck:*">List all trucks</a>
            </p>
          </div>

          <div class="mastfoot">
            <div class="inner">
              <p>&copy;2016 TUtrucks</p>
            </div>
          </div>

        </div>

      

    <%@include file="footer.html" %>
