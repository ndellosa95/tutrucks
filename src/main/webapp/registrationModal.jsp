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
                            <input type="email" name="email" placeholder="Email" /><br />
                            <input type="password" name="password" placeholder="Password" /><br />
                            <input type="hidden" name="facebook" />
                            <input type="hidden" name="display" />
                            <input type="hidden" name="avatar" />
                            <input type="submit" value="Create Account" />
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
