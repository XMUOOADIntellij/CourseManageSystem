

$(function () {
    $("#btnSignIn").click(function () {
        window.location.href = "../Teacher/4-teacherHome.html";

        var jname=$("#userName").val();
        var jpassword=$("#userPassword").val();
        var jdata={id:jname,password:jpassword};
        $.ajax({
            type:'post',
            url: '',
            dataType: "json",
            data: JSON.stringify(jdata),
            contentType: "application/json",
            success: function (data,textStatus,xhr) {
                if(xhr.status == 200){//◊¥Ã¨¬Î¥Ê“…
                    window.localStorage.setItem("jwt", data.jwt);
                    if(data.type == "student")
                        window.location.href="/student/home";
                    else
                        window.location.href="/teacher/home";
                }
            },
            statusCode:{
                401: function () {
                    $("#userPassword").val("");
                    $("#userName").val("");
                    alert("”√ªß√˚ªÚ√‹¬Î¥ÌŒÛ£°");
                }
            }
        });
});
