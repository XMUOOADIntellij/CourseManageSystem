//测试通过
function bindUser() {
    let ata = { account: $("#account").val(), password: $("#password").val() };
    $.ajax({
        type: "post",
        url: "http://xug98.cn:8080/user/login",
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
                        window.location.href = "./student/home.html";
                    else window.location.href = "./teacher/home.html";
                } else {
                    if (data.role === "student")
                        window.location.href = "./student/active.html";
                    else window.location.href = "./teacher/active.html";
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
//测试通过
function getUserInfo() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn:8080/user/information",
        dataType: "json",
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            if (xhr.status === 200) {
                $("#account").html(data.account);
                $("#email").val(data.email);
                $("#name").html(data.name);
            }
        },
        statusCode: {
            401: function() {
                alert("未登录!");
                window.location.href = "./login";
            },
            403: function() {
                alert("未登录!");
                window.location.href = "./login";
            }
        }
    });
}
function sendPassword() {
    let ata = $("#account").val();
    console.log(ata);
    $.ajax({
        type: "get",
        url: "http://xug98.cn:8080/user/password?account=" + ata,
        dataType: "json",
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(xhr.status);
            alert("success");
            if (xhr.status === 200) {
                alert("200");
                window.location.href = "./login.html";
            }
        },
        error: function(data) {
            console.log(data);
            alert("fail");
        },
        statusCode: {
            204: function() {
                alert("204"); //状态码存疑
                window.location.href = "./login.html";
            },
            400: function() {
                $("#account").val("");
                alert("没有此账户！");
            }
        }
    });
}
//状态码存疑
function editPassword() {
    let ata = {
        password: $("#password").val()
    };
    console.log(ata);
    $.ajax({
        type: "put",
        url: "http://xug98.cn:8080/user/password",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                alert("修改成功");
                window.location.href = "./account-setting.html";
            }
        },
        error: function(data) {
            console.log(data);
            alert("fail");
            window.location.href = "./account-setting.html";
        },
        statusCode: {
            200: function() {
                alert("修改成功");
            },
            400: function() {
                alert("修改失败");
            }
        }
    });
}
//状态码存疑
function editEmail() {
    let ata = {
        email: $("#email").val()
    };
    console.log(ata);
    $.ajax({
        type: "put",
        url: "http://xug98.cn:8080/user/email",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                alert("修改成功");
                window.location.href = "./account-setting.html";
            }
        },
        /*    error: function(data){
          console.log(data);
          alert("fail");

        },*/
        statusCode: {
            200: function() {
                alert("修改成功");
            }
        }
    });
}
function activeTeacher() {
    // let ata = {account:$("#account").val(),password:$("#password").val()}
    let ata = {
        password: $("#password").val()
    };
    console.log(ata);
    alert("input");
    $.ajax({
        type: "put",
        url: "http://xug98.cn:8080/teacher/active",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                alert("注册成功");
                window.location.href = "../common/login.html";
            }
        },
        statusCode: {
            400: function() {
                alert("无法注册！");
            }
        }
    });
    return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
//success 有8080 404 不带8080 就对
function getCourseList() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn/course",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log("courselist");

                for (let i = 0; i < data.length; i++) {
                    console.log(data[i]);
                }
            }
        },
        statusCode: {
            400: function() {
                alert("courselist");
                alert("错误的ID格式");
            },
            404: function() {
                alert("courselist");
                alert("未找到课程");
            }
        }
    });
}
//400 未部署
function getAllCourse() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn:8080/course/allcourse",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log("courselist");

                for (let i = 0; i < data.length; i++) {
                    console.log(data[i]);
                }
            }
        },
        statusCode: {
            400: function() {
                alert("courselist");
                alert("错误的ID格式");
            },
            404: function() {
                alert("courselist");
                alert("未找到课程");
            }
        }
    });
}
//success conflict未加
function getCourseInfo() {
    Cookies.set("course", "1");
    $.ajax({
        type: "get",
        url: "http://xug98.cn:8080/course/" + Cookies.get("course"),
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log(data);
            }
        },
        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
}
//success
function getClassList() {
    Cookies.set("course", "1");
    Cookies.set("class", "1");
    $.ajax({
        type: "get",
        url: "http://xug98.cn:8080/course/" + Cookies.get("course") + "/class",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                for (let i = 0; i < data.length; i++) {
                    console.log(data[i]);
                }
            }
        },
        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
}
//405 谈雪
function updateClass() {
    Cookies.set("class", "1");
    var fileToUpload = $("#file").prop("files")[0];
    let ata = { file: fileToUpload };
    console.log(ata);
    $.ajax({
        type: "put",
        url: "http://xug98.cn:8080/class/" + Cookies.get("class"),
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-detail.html";
        },
        error: function(data) {
            console.log(data);

            alert("fail");
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}
//状态码存疑
function deleteClass() {
    let cid = "1";
    $.ajax({
        type: "delete",
        url: "http://xug98.cn:8080/class/" + cid,
        dataType: "json",
        contentType: "application/json;",
        error: function(data, textStatus, xhr) {
            console.log(cid);
            alert("wrong");
        },
        success: function(data, textStatus, xhr) {
            alert("成功");

            if (xhr.status === 204) {
                alert("成功");
                console.log(data);
            }
        },
        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            403: function() {
                alert("用户权限不足");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
    window.location.reload();
}
//success
function getRoundList() {
    Cookies.set("course", "1");
    $.ajax({
        type: "get",
        url: "http://xug98.cn:8080/course/" + Cookies.get("course") + "/round",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log("roundlist");
                for (let i = 0; i < data.length; i++) {
                    console.log(data[i]);
                    getSeminarList(data[i].id);
                }
            }
        },
        statusCode: {
            400: function() {
                alert("roundlist");

                alert("错误的ID格式");
            },
            404: function() {
                alert("roundlist");

                alert("未找到课程");
            }
        }
    });
}
//success
function getSeminarList(roundId) {
    $.ajax({
        type: "get",
        url: "http://xug98.cn:8080/round/" + roundId + "/seminar",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log("seminarList");

                for (let i = 0; i < data.length; i++) {
                    console.log(data[i]);
                }
            }
        },
        statusCode: {
            400: function() {
                alert("seminarList");

                alert("错误的ID格式");
            },
            404: function() {
                alert("seminarList");

                alert("未找到课程");
            }
        }
    });
}
//未部署
function getSeminar() {
    Cookies.set("seminar", "1");
    $.ajax({
        type: "get",
        url: "http://xug98.cn:8080/seminar/" + Cookies.get("seminar"),
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log(data[i]);
            }
        },
        error: function(data) {
            console.log(data);
            alert("fail");
        },
        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
}
//success
function getSeminarByClass() {
    Cookies.set("seminar", "1");
    Cookies.set("class", "1");
    $.ajax({
        type: "get",
        url:
            "http://xug98.cn:8080/seminar/" +
            Cookies.get("seminar") +
            "/class/" +
            Cookies.get("class"),
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log(data);
            }
        },
        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
}
//success
function updateSeminarByClass() {
    Cookies.set("seminar", "1");
    Cookies.set("class", "1");
    let ata = {
        reportDdl: convertTime($("#input-deadline").val())
    };
    console.log(ata);
    $.ajax({
        type: "put",
        url:
            "http://xug98.cn:8080/seminar/" +
            Cookies.get("seminar") +
            "/class/" +
            Cookies.get("class"),
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-detail.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}
//未部署
function getSeminarScoreByClass() {
    Cookies.set("seminar", "1");
    Cookies.set("class", "1");
    $.ajax({
        type: "get",
        url:
            "http://xug98.cn:8080/seminar/" +
            Cookies.get("seminar") +
            "/class/" +
            Cookies.get("class") +
            "/score",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log(data[i]);
            }
        },
        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
}
//未部署
function updateSeminarScoreByClass() {
    let teamData = { id: 1, name: "1-1" };
    let ata = {
        team: teamData,
        preScpre: "5",
        reportScore: "4",
        questionScore: "5"
    };
    console.log(ata);
    Cookies.set("seminar", "1");
    Cookies.set("class", "1");
    $.ajax({
        type: "put",
        url:
            "http://xug98.cn:8080/seminar/" +
            Cookies.get("seminar") +
            "/class/" +
            Cookies.get("class") +
            "/seminarscore",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log(data[i]);
            }
        },
        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
    window.location.reload();
}
//success
function createCourse() {
    let conflict = {
        courseId: "2"
    };
    let ata = {
        courseName: "OOAD",
        introduction: "课程要求",
        presentationPercentage: "50",
        questionPercentage: "10",
        reportPercentage: "30",
        minMember: "4",
        maxMember: "6",
        teamStartTime: convertTime($("#input-start").val()),
        teamEndTime: convertTime($("#input-end").val())
    };
    console.log(ata);
    $.ajax({
        type: "post",
        url: "http://xug98.cn:8080/course",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./course-round.html";
        },
        error: function(data) {
            console.log(data);
            alert("fail");
        },
        statusCode: {
            201: function(data) {
                console.log(data);
                alert("success");
                window.location.href = "./course-round.html";
            },
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}
//success
function createSeminar() {
    Cookies.set("course", "1");
    let ata = {
        roundId: $("#select-round-id").val(),

        seminarName: $("#name").val(),
        introduction: $("#introduction").val(),
        maxTeam: $("#select-max-team").val(),
        visible: $("#switch-visible").is(":checked"),
        seminarSerial: $("#select-seminar-serial").val(),
        enrollStartTime: convertTime($("#input-start").val()),
        enrollEndTime: convertTime($("#input-end").val()),
        courseId: Cookies.get("course")
    };
    console.log(ata);
    alert("input");
    $.ajax({
        type: "post",
        url: "http://xug98.cn:8080/seminar",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./course-round.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}
//success
function deleteSeminar() {
    let cid = "2";
    $.ajax({
        type: "delete",
        url: "http://xug98.cn:8080/seminar/" + cid,
        dataType: "json",
        contentType: "application/json;",
        error: function(data, textStatus, xhr) {
            console.log(cid);
            alert("wrong");
        },
        success: function(data, textStatus, xhr) {
            alert("成功");
            console.log(data);
        },

        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            403: function() {
                alert("用户权限不足");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
    /*
    window.location.reload();
  */
}
//success
function updateSeminar() {
    Cookies.set("seminar", "1");
    let ata = {
        seminarName: $("#name").val(),
        introduction: $("#introduction").val(),
        maxTeam: $("#select-max-team").val(),
        visible: $("#switch-visible").is(":checked"),
        seminarSerial: $("#select-seminar-serial").val(),
        enrollStartTime: convertTime($("#input-start").val()),
        enrollEndTime: convertTime($("#input-end").val()),
        roundId: $("#select-round-id").val(),
        courseId: Cookies.get("courseId")
    };
    console.log(ata);
    $.ajax({
        type: "put",
        url: "http://xug98.cn:8080/seminar/" + Cookies.get("seminar"),
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./course-round.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}
//success
function getAttendanceByClass() {
    Cookies.set("seminar", "1");
    Cookies.set("class", "1");
    let ata = {
        courseId: "1"
    };
    console.log(ata);

    $.ajax({
        type: "get",
        url:
            "http://xug98.cn:8080/seminar/" +
            Cookies.get("seminar") +
            "/class/" +
            Cookies.get("class") +
            "/attendance",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                alert("获取成功");
                for (let i = 0; i < data.length; i++) {
                    console.log(data[i]);
                }
            }
        },
        error: function(data) {
            console.log(data);
            alert("fail");
        },
        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
}
//无文件
function getPptByClass() {
    Cookies.set("seminar", "1");
    Cookies.set("class", "1");
    $.ajax({
        type: "get",
        url:
            "http://xug98.cn:8080/seminar/" +
            Cookies.get("seminar") +
            "/class/" +
            Cookies.get("class") +
            "/ppt",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                alert("获取成功");
                console.log(data);
            }
        },
        error: function(data) {
            console.log(data);
            alert("fail");
        },
        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
}
//无文件
function getReportByClass() {
    Cookies.set("seminar", "1");
    Cookies.set("class", "1");
    $.ajax({
        type: "get",
        url:
            "http://xug98.cn:8080/seminar/" +
            Cookies.get("seminar") +
            "/class/" +
            Cookies.get("class") +
            "/report",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                alert("获取成功");
                console.log(data);
            }
        },
        error: function(data) {
            console.log(data);
            alert("fail");
        },
        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
}
//无文件
function getPptByAttendance() {
    Cookies.set("attendance", "1");
    $.ajax({
        type: "get",
        url:
            "http://xug98.cn:8080/attendance/" + Cookies.get("attendance") + "/ppt",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                alert("获取成功");
                console.log(data);
            }
        },
        error: function(data) {
            console.log(data);
            alert("fail");
        },
        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
}
//无文件
function getReportByAttendance() {
    Cookies.set("attendance", "1");
    $.ajax({
        type: "get",
        url:
            "http://xug98.cn:8080/attendance/" +
            Cookies.get("attendance") +
            "/report",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                alert("获取成功");
                console.log(data);
            }
        },
        error: function(data) {
            console.log(data);
            alert("fail");
        },
        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
}
//新添加
function getTeam() {
    Cookies.set("course", "1");
    $.ajax({
        type: "get",
        url: "http://xug98.cn:8080/course/" + Cookies.get("course") + "/myTeam",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log("Team");
                for (let i = 0; i < data.length; i++) {
                    console.log(data[i]);
                }
            }
        },
        statusCode: {
            400: function() {
                alert("Team");

                alert("错误的ID格式");
            },
            404: function() {
                alert("Team");

                alert("未找到课程");
            }
        }
    });
}
function getMyTeam() {
    Cookies.set("course", "1");
    $.ajax({
        type: "get",
        url: "http://xug98.cn:8080/course/" + Cookies.get("course") + "/team",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log("Team");
                for (let i = 0; i < data.length; i++) {
                    console.log(data[i]);
                }
            }
        },
        statusCode: {
            400: function() {
                alert("Team");

                alert("错误的ID格式");
            },
            404: function() {
                alert("Team");

                alert("未找到课程");
            }
        }
    });
}


function createTeam() {
    let member=[
        {
            id: 123
        },
        {
            id: 124
        }
    ];
    let leader=[
        {
            id: 123,
        }
    ];
    let ata = {
        id: 1,
        name: "4-2 早早鸟小组",
        course: {
            "id": 1,
        },
        class: {
            "id": 1,
        },
        leader: leader,
        members:member,
    };
    console.log(ata);
    $.ajax({
        type: "post",
        url: "http://xug98.cn:8080/team",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./course-round.html";
        },
        error: function(data) {
            console.log(data);
            alert("fail");
        },
        statusCode: {
            201: function(data) {
                console.log(data);
                alert("success");
                window.location.href = "./course-round.html";
            },
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}
function updateTeam() {
    Cookies.set("team", "1");
    let member=[
        {
            id: 123,
        },
        {
            id: 124,
        }
    ];
    let leader=[
        {
            id: 123,
        }
    ];
    let ata = {
        id: 1,
        name: "4-2 早早鸟小组",
        course: {
            "id": 1,
        },
        class: {
            "id": 1,
        },
        leader: leader,
        members:member,
        valid: true
    };
    console.log(ata);
    $.ajax({
        type: "put",
        url: "http://xug98.cn:8080/team/" + Cookies.get("team"),
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
    /*
    window.location.reload();
  */
}

function getNoTeam() {
    Cookies.set("course", "1");
    $.ajax({
        type: "get",
        url: "http://xug98.cn:8080/course/" + Cookies.get("course") + "/noTeam",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log("NoTeam");
                for (let i = 0; i < data.length; i++) {
                    console.log(data[i]);
                    getSeminarList(data[i].id);
                }
            }
        },
        statusCode: {
            400: function() {
                alert("NoTeam");
                alert("错误的ID格式");
            },
            404: function() {
                alert("NoTeam");
                alert("未找到课程");
            }
        }
    });
}

function createClass() {
    var fileToUpload = $("#file").prop("files")[0];
    let ata = {
        name: "2016-3",
        time: "周三一二节",
        classroom: "海韵教学楼",
        file: fileToUpload
    };
    console.log(ata);
    $.ajax({
        type: "post",
        url: "http://xug98.cn:8080/course/" + Cookies.get("course") + "/class",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./course-round.html";
        },
        error: function(data) {
            console.log(data);
            alert("fail");
        },
        statusCode: {
            201: function(data) {
                console.log(data);
                alert("success");
                window.location.href = "./course-round.html";
            },
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}

function deleteCourse() {
    let cid = "2";
    $.ajax({
        type: "delete",
        url: "http://xug98.cn:8080/course/" + cid,
        dataType: "json",
        contentType: "application/json;",
        error: function(data, textStatus, xhr) {
            console.log(cid);
            alert("wrong");
        },
        success: function(data, textStatus, xhr) {
            alert("成功");
            console.log(data);
        },

        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            403: function() {
                alert("用户权限不足");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
    /*
      window.location.reload();
    */
}

function getCourseScoreByRound(cid) {
    Cookies.set("seminar", "1");
    Cookies.set("class", "1");
    $.ajax({
        type: "get",
        url: "http://xug98.cn:8080/round/" + Cookies.get("round") + "/roundscore",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log(data[i]);
            }
        },
        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
}

function getSeminarScoreByTeam(sid, tid) {
    Cookies.set("seminar", sid);
    Cookies.set("team", tid);
    $.ajax({
        type: "get",
        url:
            "http://xug98.cn:8080/seminar/" +
            Cookies.get("seminar") +
            "/team/" +
            Cookies.get("team") +
            "/seminarscore",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log(data[i]);
            }
        },
        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
}

function getTeamShareList() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn/course/" + Cookies.get("course") + "/teamshare",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log("teamsharelist");

                for (let i = 0; i < data.length; i++) {
                    console.log(data[i]);
                }
            }
        },
        statusCode: {
            400: function() {
                alert("teamsharelist");
                alert("错误的ID格式");
            },
            404: function() {
                alert("teamsharelist");
                alert("未找到课程");
            }
        }
    });
}

function getSeminarShareList() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn/course/" + Cookies.get("course") + "/seminarshare",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log("seminarsharelist");

                for (let i = 0; i < data.length; i++) {
                    console.log(data[i]);
                }
            }
        },
        statusCode: {
            400: function() {
                alert("seminarsharelist");
                alert("错误的ID格式");
            },
            404: function() {
                alert("seminarsharelist");
                alert("未找到课程");
            }
        }
    });
}

function deleteSeminarShare() {
    let cid = "2";
    $.ajax({
        type: "delete",
        url: "http://xug98.cn:8080/course/seminarshare/" + cid,
        dataType: "json",
        contentType: "application/json;",
        error: function(data, textStatus, xhr) {
            console.log(cid);
            alert("wrong");
        },
        success: function(data, textStatus, xhr) {
            alert("成功");
            console.log(data);
        },

        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            403: function() {
                alert("用户权限不足");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
    /*
      window.location.reload();
    */
}

function deleteTeamShare() {
    let cid = "2";
    $.ajax({
        type: "delete",
        url: "http://xug98.cn:8080/course/teamshare/" + cid,
        dataType: "json",
        contentType: "application/json;",
        error: function(data, textStatus, xhr) {
            console.log(cid);
            alert("wrong");
        },
        success: function(data, textStatus, xhr) {
            alert("成功");
            console.log(data);
        },

        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            403: function() {
                alert("用户权限不足");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
    /*
      window.location.reload();
    */
}

function updateRound() {
    Cookies.set("round", "1");
    let ata = {
        calculatePreType: "最高分",
        calculateQueType: "平均分",
        calculateRepType: "最高分",
        classRound: [
            {
                id: 1,
                enrollNum: 2,
                classSerial: 2
            }
        ]
    };
    console.log(ata);
    $.ajax({
        type: "put",
        url: "http://xug98.cn:8080/round/" + Cookies.get("round"),
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./course-round.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}

function createRound() {
    Cookies.set("course", "1");
    let ata = {
        courseId: Cookies.get("course")
    };
    console.log(ata);
    alert("input");
    $.ajax({
        type: "post",
        url: "http://xug98.cn:8080/round",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./course-round.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}

function createTeamShare() {
    Cookies.set("course", "1");
    let ata = {
        subCourseId: "1"
    };
    console.log(ata);
    alert("input");
    $.ajax({
        type: "post",
        url:
            "http://xug98.cn:8080/course/" +
            Cookies.get("course") +
            "/teamsharerequest",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./course-round.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}

function createSeminarShare() {
    Cookies.set("course", "1");
    let ata = {
        subCourseId: "1"
    };
    console.log(ata);
    alert("input");
    $.ajax({
        type: "post",
        url:
            "http://xug98.cn:8080/course/" +
            Cookies.get("course") +
            "/seminarsharerequest",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./course-round.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}

function getTeamShareTask() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn/request/teamshare",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log("teamshare");

                for (let i = 0; i < data.length; i++) {
                    console.log(data[i]);
                }
            }
        },
        statusCode: {
            400: function() {
                alert("teamshare");
                alert("错误的ID格式");
            },
            404: function() {
                alert("teamshare");
                alert("未找到课程");
            }
        }
    });
}

function getSeminarShareTask() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn/request/seminarshare",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log("seminarshare");

                for (let i = 0; i < data.length; i++) {
                    console.log(data[i]);
                }
            }
        },
        statusCode: {
            400: function() {
                alert("seminarshare");
                alert("错误的ID格式");
            },
            404: function() {
                alert("seminarshare");
                alert("未找到课程");
            }
        }
    });
}

function getTeamValidTask() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn/request/teamvaild",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log("teamvaild");

                for (let i = 0; i < data.length; i++) {
                    console.log(data[i]);
                }
            }
        },
        statusCode: {
            400: function() {
                alert("teamvaild");
                alert("错误的ID格式");
            },
            404: function() {
                alert("teamvaild");
                alert("未找到课程");
            }
        }
    });
}

function updateTeamValid() {
    Cookies.set("teamvalid", "1");
    let ata = {
        handletype: "accept"
    };
    console.log(ata);
    $.ajax({
        type: "put",
        url: "http://xug98.cn:8080/request/teamvalid/" + Cookies.get("teamvalid"),
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
    /*
    window.location.reload();
  */
}

function updateTeamShare() {
    Cookies.set("teamshare", "1");
    let ata = {
        handletype: "accept"
    };
    console.log(ata);
    $.ajax({
        type: "put",
        url: "http://xug98.cn:8080/request/teamshare/" + Cookies.get("teamshare"),
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
    /*
    window.location.reload();
  */
}

function updateSeminarShare() {
    Cookies.set("seminarshare", "1");
    let ata = {
        handletype: "accept"
    };
    console.log(ata);
    $.ajax({
        type: "put",
        url:
            "http://xug98.cn:8080/request/seminarshare/" +
            Cookies.get("seminarshare"),
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
    /*
    window.location.reload();
  */
}

function getQuestionList() {
    Cookies.set("seminar", "1");
    Cookies.set("class", "1");
    Cookies.set("attendance", "1");

    $.ajax({
        type: "get",
        url:
            "http://xug98.cn:8080/seminar/" +
            Cookies.get("seminar") +
            "/class/" +
            Cookies.get("class") +
            "/attendance/" +
            Cookies.get("attendance") +
            "/question",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                alert("获取成功");
                for (let i = 0; i < data.length; i++) {
                    console.log(data[i]);
                }
            }
        },
        error: function(data) {
            console.log(data);
            alert("fail");
        },
        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
}

function updatePresentScoreByTeam() {
    let ata = {
        presentationScore: "5",
    };
    console.log(ata);
    Cookies.set("attendance", "1");
    $.ajax({
        type: "put",
        url:
            "http://xug98.cn:8080/attendance/" +
            Cookies.get("attendance") +
            "/score",
        dataType: "json",
        data: JSON.stringify(ata),

        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log(data[i]);
            }
        },
        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
    window.location.reload();
}

function updateQuestionScoreByTeam() {
    let ata = {
        score: "5"
    };
    console.log(ata);
    Cookies.set("question", "1");
    $.ajax({
        type: "put",
        url: "http://xug98.cn:8080/question/" + Cookies.get("question"),
        dataType: "json",
        data: JSON.stringify(ata),

        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log(data[i]);
            }
        },
        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
    window.location.reload();
}

function activeStudent() {
    // let ata = {account:$("#account").val(),password:$("#password").val()}
    let ata = {
        password: $("#password").val(),
        email: $("#email").val()
    };
    console.log(ata);
    alert("input");
    $.ajax({
        type: "put",
        url: "http://xug98.cn:8080/student/active",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(textStatus, xhr) {
            console.log(data);
            alert("注册成功");

            if (xhr.status === 200) {
                alert("注册成功");
                window.location.href = "../common/login.html";
            }
        },
        error: function(data) {
            console.log(data);
            alert("注册失败");
        },
        statusCode: {
            400: function() {
                alert("无法注册！");
            }
        }
    });
}

function getCurrentSeminar() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn:8080/seminar",
        dataType: "json",
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            if (xhr.status === 200) {
                $("#account").html(data.account);
                $("#email").val(data.email);
                $("#name").html(data.name);
            }
        },
        statusCode: {
            401: function() {
                alert("未登录!");
                window.location.href = "./login";
            },
            403: function() {
                alert("未登录!");
                window.location.href = "./login";
            }
        }
    });
}
function getCurrentAttendance() {
    Cookies.set("seminar", "1");
    Cookies.set("class", "1");
    $.ajax({
        type: "get",
        url: "http://xug98.cn:8080/seminar/"+
            Cookies.get("seminar") +
            "/attendance?presented=true",
        dataType: "json",
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            if (xhr.status === 200) {
                $("#account").html(data.account);
                $("#email").val(data.email);
                $("#name").html(data.name);
            }
        },
        statusCode: {
            401: function() {
                alert("未登录!");
                window.location.href = "./login";
            },
            403: function() {
                alert("未登录!");
                window.location.href = "./login";
            }
        }
    });
}
function getRoundScoreByTeam(sid, tid) {
    Cookies.set("round", sid);
    Cookies.set("team", tid);
    $.ajax({
        type: "get",
        url:
            "http://xug98.cn:8080/round/" +
            Cookies.get("round") +
            "/team/" +
            Cookies.get("team") +
            "/roundscore",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log(data[i]);
            }
        },
        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
}

function deleteTeam() {
    let cid = "2";
    $.ajax({
        type: "delete",
        url: "http://xug98.cn:8080/team/" + cid,
        dataType: "json",
        contentType: "application/json;",
        error: function(data, textStatus, xhr) {
            console.log(cid);
            alert("wrong");
        },
        success: function(data, textStatus, xhr) {
            alert("成功");
            console.log(data);
        },

        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            403: function() {
                alert("用户权限不足");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
}

function deleteTeamMember() {
    let cid = "2";
    let ata = {
        id: "1"
    };
    $.ajax({
        type: "delete",
        url: "http://xug98.cn:8080/team/" + cid+"/remove",
        dataType: "json",
        data: JSON.stringify(ata),

        contentType: "application/json;",
        error: function(data, textStatus, xhr) {
            console.log(cid);
            alert("wrong");
        },
        success: function(data, textStatus, xhr) {
            alert("成功");
            console.log(data);
        },

        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            403: function() {
                alert("用户权限不足");
            },
            404: function() {
                alert("未找到课程");
            }
        }
    });
}