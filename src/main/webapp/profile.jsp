
<%@ include file="header.jsp"%>

<%@ page import="edu.temple.tutrucks.*"%>
<%@ page import="java.util.List"%>
<div class="container profile">
    <div class="col-md-4">
        <%

            User userProfile=User.loadUserByID(Integer.parseInt(request.getParameter("userid")),true);
            String avatar=null;
            List<TruckReview> truckReviews;
            List<ItemReview> itemReviews;
            if (userProfile==null){
                response.sendRedirect("invalidPage.jsp");
                return;
            }else{
//            user = new User();
//            user.setAvatar("https://upload.wikimedia.org/wikipedia/en/3/38/Avatarjakeneytiri.jpg");
//            user.setDisplayName("Avatar");
            avatar = userProfile.getAvatar();
            //userProfile = userProfile.loadUserReviews();
            truckReviews = userProfile.getTruckReviews();
//            TruckReview tr = new TruckReview();
//            Truck t = Truck.getTruckByID(1);
//            tr.setTruck(t);
//            tr.setReviewed(t);
//            tr.setReviewText("best truck ever");
//            truckReviews.add(tr);
            itemReviews = userProfile.getItemReviews();
            }
        %>

        <img class = "avatar" src="
             <% 
                if (avatar != null) {
                    out.print(avatar);
                }else out.print("images/NoUserPhoto.png");
             %>" 
             alt="No user avatar" width="128px" height="128px" style="border:2px solid #FFFFFF; border-radius:5px" /> <br />
        <a style="font-size: x-small;" id="image_upload" href="#">Upload a profile picture...</a> <br />
        <h3 style="color: white;"><%=userProfile.getDisplayName()%></h3>
        <h5 style="color: white;"><%=truckReviews.size()%> truck reviews</h5>
        <h5 style="color: white;"><%=itemReviews.size()%> item reviews</h5>
    </div>
    <div class="col-md-8">
        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h1 class="panel-title">Truck Reviews</h1>
                </div>
                <div class="panel-body">
                    <div class ="container-fluid">   
                        
                        <% for (TruckReview review : truckReviews) { %>
                            
                            <div class="row review">
                                <div class="row">
                                    <div class="col-sm-12" style="text-align: center; color:#A41E35;">
                                        <a href="truck.jsp?truck=<%=review.getTruck().getId()%>" style="color:#A41E35;">
                                        <h4><%=review.getTruck().getTruckName()%></h4>
                                        </a>
                                    </div>
                                    <div class="col-sm-12" style="text-align: center;">
                                        <%
                                            
                                            int reviewStars=review.getReviewStars();
                                            int fullStars=reviewStars/2;
                                            int halfStars=reviewStars%2;
                                            if (reviewStars==0){
                                                out.print("0-Star Review");
                                            }
                                            for (int c=0; c<fullStars; c++){
                                                out.print("<img src='images/Star_Full.png' width='24' height='24'>");
                                            }
                                            if (halfStars==1){
                                                out.print("<img src='images/Star_Half.png' width='12' height='24'>");
                                            }
                                        %>
                                    </div>
                                </div>
                                <div class="row" style="text-align: left; padding: 5px;">
                                    
                                    <p><%=review.getReviewText()%></p>
                                </div>
                                <div class="row userAndDate" style="text-align: center; padding: 5px;">
                                    <p>Reviewed on <%= review.getReviewDate()%></p>
                                </div>
                            </div>
                            <hr />
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h1 class="panel-title">Item Reviews</h1>
                </div>
                <div class="panel-body">
                    <div class ="container-fluid">
                        <% for (ItemReview review : itemReviews) { %>
                            <div class="row review">
                                <div class="row">
                                    <div class="col-sm-12" style="text-align: center; color:#A41E35;">
                                        
                                        <h4><%=review.getItem().getItemName()%>
                                            at
                                        <a href="truck.jsp?truck=<%=review.getItem().getMenu().getTruck().getId()%>" style="color:#A41E35;"><%=review.getItem().getMenu().getTruck().getTruckName()%></a></h4>
                                        
                                        
                                    </div>
                                    <div class="col-sm-12" style="text-align: center;">
                                        <%
                                            
                                            int reviewStars=review.getReviewStars();
                                            int fullStars=reviewStars/2;
                                            int halfStars=reviewStars%2;
                                            if (reviewStars==0){
                                                out.print("0-Star Review");
                                            }
                                            for (int c=0; c<fullStars; c++){
                                                out.print("<img src='images/Star_Full.png' width='24' height='24'>");
                                            }
                                            if (halfStars==1){
                                                out.print("<img src='images/Star_Half.png' width='12' height='24'>");
                                            }
                                        %>
                                    </div>
                                </div>
                                <div class="row" style="text-align: left; padding: 5px;">
                                    
                                    <p><%=review.getReviewText()%></p>
                                </div>
                                <div class="row userAndDate" style="text-align: center; padding: 5px;">
                                    <p>Reviewed on <%= review.getReviewDate()%></p>
                                </div>
                            </div>
                            <hr />
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
 <script>
    $(document).ready(function() {
        $("#image_upload").click(function() {
           // open image upload modal 
        });
    });
</script>
<%@ include file="footer.html"%>
