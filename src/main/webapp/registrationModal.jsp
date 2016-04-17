<div id="registrationModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                
                <h4 class="modal-title">Create an Account</h4>
            </div>
            <div class="modal-body">
                <p>
                    <div class="row">
                        <form id="registrationForm" class="registration login" action="/createUser" method="post">
                            <div class="col-lg-1">
                                &nbsp;
                            </div>
                            <div class="col-lg-4" style="text-align: center;">
                                <input type="email" id="email" pattern="(.+)@(.+)\.((com)|(edu)|(org)|(gov))" name="email" placeholder="Email" /><br />
                                <input type="password" id="password" pattern=".{6,16}" name="password" placeholder="Password (6-16 characters)" /><br />
                                <input type="hidden" id="facebook_id" name="facebook_id" />
                                <input type="hidden" id="display" name="display" />
                                <input type="hidden" id="avatar" name="avatar" />
                                <input type="submit" value="Create Account" />
                            </div>
                            <div class="col-lg-2">
                                <strong> OR </strong>
                            </div>
                            <div class="col-lg-4">
                                <div id="loginButton"><img src="images/fbconnect.png" width="180px" height="40px"></div>
                                <script>
                                    $(document).ready(function () {
                                        $("#loginButton").click(function() {
                                            FB.getLoginStatus(function(response) {
                                                if (response.status === 'connected') {
                                                    $('#modal').modal('toggle');
                                                    alert("You are already logged in")
                                                //var uid = response.authResponse.userID;
                                                //var accessToken = response.authResponse.accessToken;
                                              } else if (response.status === 'not_authorized') {
                                                // the user is logged in to Facebook, 
                                                // but has not authenticated your app
                                              } else {
                                                FB.login(function(response) {
                                                    if (response.authResponse) {
                                                     FB.api('/me', { locale: 'en_US', fields: 'id, name, email, picture' }, function(response) {
                                                       document.getElementById("email").value = response.email;
                                                       document.getElementById("facebook_id").value = response.id;
                                                       document.getElementById("display").value=response.name;
                                                       document.getElementById("avatar").value=response.picture.data.url;
                                                     });
                                                    } else {
                                                     console.log('User cancelled login or did not fully authorize.');
                                                    }
                                                }, {scope: 'email, public_profile'});
                                              }
                                            });
                                        });
                                    });
                                    
                                    
                                    
                                </script>
                                
                            </div>
                        </form>
                    </div>
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>
