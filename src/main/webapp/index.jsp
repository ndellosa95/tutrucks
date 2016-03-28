<%-- 
    Document   : index
    Created on : Feb 22, 2016, 9:42:19 PM
    Author     : nickdellosa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="header.html"%>

        <div class="cover-container">

          <div class="inner cover">
            <img src="images/TUtrucksLogo.png" alt="logo" width="75%"/>
            <p class="lead">
            <form action="search.jsp" method="GET">
                <input type="search" name="criteria" class="textbox" placeholder="Search for trucks or items" />
                <input type="submit" class="search" value="Search"/>
                
            </form>
            <a href="search.jsp">List all trucks</a>
            </p>
          </div>

          <div class="mastfoot">
            <div class="inner">
              <p>&copy;2016 TUtrucks</p>
            </div>
          </div>

        </div>

      </div>

    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="js/vendor/jquery.min.js"><\/script>')</script>
    <script src="js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
