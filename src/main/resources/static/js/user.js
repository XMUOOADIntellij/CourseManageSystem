$(document).ready(function(){
    $("#header-name").html(Cookies.get("name"));
    $("#header-role").html(Cookies.get("role"));
});

