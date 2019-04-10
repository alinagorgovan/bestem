function login() {
    fetch('http://localhost:8087/loginUser', {
        method: "POST",
        headers: new Headers({'content-type': 'application/json'}),
        body: JSON.stringify({
            username: document.getElementById("email").value,
            password: document.getElementById("pass").value
        })
    }).then(response => {
        if (!response.ok) {
            window.alert("Wrong username/password!");
            return;
        }
        return response.json();
    }).then(data => {
        document.cookie = "Authorization=" + data['Bearer'];
        window.location.replace('http://localhost:8087/loggedIn/home');
    });
}

function register() {
    fetch('http://localhost:8087/registerUser', {
        method: "POST",
        headers: new Headers({'content-type': 'application/json'}),
        body: JSON.stringify({
            username: document.getElementById("email").value,
            password: document.getElementById("pass").value
        })
    }).then(response => {
        if (!response.ok)
            window.alert("User already exists!");
        else
            window.location.replace("/");
    });
}


(function ($) {
    "use strict";

    var input = $('.validate-input .input100');

    $('.validate-form').on('submit',function(){
        var check = true;

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }

        return check;
    });

    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
            hideValidate(this);
        });
    });

    function validate (input) {
        if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
            if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        }
        else {
            if($(input).val().trim() == ''){
                return false;
            }
        }
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();
        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();
        $(thisAlert).removeClass('alert-validate');
    }
})(jQuery);