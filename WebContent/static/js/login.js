$(document).ready(function() {
    $("input[name='userName']").bind('keydown', function(event) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            if ($("input[name='userName']").val() == '') {
                $('#validate_userName').css('visibility', 'visible');
                $('#error_message').css('visibility', 'visible');
                $('#error_message').html('UserName is required');
            } else if ($("input[name='password']").val() == '') {
                $("input[name='password']").focus();
            } else {
                login();
            }
        }
    });
    
    $("input[name='password']").bind('keydown', function(event) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            if ($("input[name='password']").val() == '') {
                $('#validate_password').css('visibility', 'visible');
                $('#error_message').css('visibility', 'visible');
                $('#error_message').html('password is required');
            } else if ($("input[name='userName']").val() == '') {
                $("input[name='userName']").focus();
                $('#validate_userName').css('visibility', 'visible');
                $('#error_message').css('visibility', 'visible');
                $('#error_message').html('UserName is required');
            } else {
                login();
            }
        }
    });
    
    $("input[name='userName']").bind('input propertychange', function(event) {
        $('#validate_userName').css('visibility', 'hidden');
        $('#error_message').css('visibility', 'hidden');
    });
    
    $("input[name='password']").bind('input propertychange', function(event) {
        $('#validate_password').css('visibility', 'hidden');
        $('#error_message').css('visibility', 'hidden');
    });
    
    $('.login_form_button').on('click', function() {
        login();
    });
});

function login() {
    var errorMsg = "";
    var userNameisNull = false;
    var passwordisNull = false;
    var isSubmitForm = true;
    if (!$("input[name='userName']").val()) {
        $("input[name='userName']").focus();
        userNameisNull = true;
        isSubmitForm = false;
        $('#validate_userName').css('visibility', 'visible');
        errorMsg = "UserName is required";
    } else {
        $('#validate_userName').css('visibility', 'hidden');
    }

    if (!$("input[name='password']").val()) {
        $("input[name='password']").focus();
        passwordisNull = true;
        isSubmitForm = false;
        $('#validate_password').css('visibility', 'visible');
        errorMsg = "password is required";
    } else {
        $('#validate_password').css('visibility', 'hidden');
    }

    if(userNameisNull && passwordisNull) {
        $("input[name='userName']").focus();
        errorMsg = "UserName and password is required";
    }
    
    if(!isSubmitForm){
        $('#error_message').css('visibility', 'visible');
        $('#error_message').html(errorMsg);
    } else {
        $('#loginForm').submit();
/*        $.ajax ({
            type: 'POST',
            url: '/springexam/page/json/login',
            data:JSON.stringify({'username' : $("input[name='userName']").val(), 'password' : $("input[name='password']").val()}),
            ContentType: 'application/json; charset=UTF-8',
            success: function(data) {
            } 
        });*/
    }
}
