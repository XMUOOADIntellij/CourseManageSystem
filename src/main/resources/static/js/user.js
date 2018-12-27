$(document).ready(function(){
    $("#header-name").html(Cookies.get("name"));
    $("#header-role").html(Cookies.get("role"));
});

function bindUser() {
    let ata = { account: $("#account").val(), password: $("#password").val() };
    $.ajax({
        type: "post",
        url: "/user/login",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            if (xhr.status === 200) {
                window.localStorage.setItem("jwt", data.jwt);
                Cookies.set("id", data.id);
                Cookies.set("account", data.account);
                Cookies.set("role", data.role);
                Cookies.set("name", data.name);
                if (data.isActive === true) {
                    if (data.role === "student")
                        window.location.href = "../student/home.html";
                    else window.location.href = "../teacher/home.html";
                } else {
                    if (data.role === "student")
                        window.location.href = "../student/active.html";
                    else window.location.href = "../teacher/active.html";
                }
            }
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}
