//登录页面
function bindAdmin() {
    let ata = { account: $("#account").val(), password: $("#password").val() };
    $.ajax({
        type: "post",
        url: "/admin/login",
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
                window.location.href = "./teacher-home.html";
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
//通过教工号查找教师服务器返回400
function searchTeacher() {

    let ata = {
        identity: $("#identity").val()
    };
    console.log(ata);
    alert("input");
    $.ajax({
        type: "get",
        url: "/teacher/searchTeacher",
        dataType: "json",
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
        },
        statusCode: {
            400: function() {
                $("#identity").val("");
                alert("无此用户");
            }
        }
    });
}
//通过学号查找学生，服务器返回400
function searchStudent() {

    let ata = {
        identity: $("#identity").val()
    };
    console.log(ata);
    alert("input");
    $.ajax({
        type: "get",
        url: "/student/searchStudent",
        dataType: "json",
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
        },
        statusCode: {
            400: function() {
                $("#identity").val("");
                alert("无此用户！");
            }
        }
    });
}
//删除教师
function deleteTeacher() {
    let ata = {
        identity: $("#identity").val()
    };

    console.log(ata);
    $.ajax({
        type: "delete",
        url: "/teacher/" + Cookies.get("teacher"),
        dataType: "json",
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.reload();

        },
        statusCode: {
            400: function() {
                $("#password").val("");
            }
        }
    });
}
//删除学生
function deleteStudent() {


    let ata = {
        identity: $("#identity").val()
    };

    console.log(ata);
    $.ajax({
        type: "delete",
        url: "/teacher/" + Cookies.get("student"),
        dataType: "json",
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.reload();

        },
        statusCode: {
            400: function() {
                $("#password").val("");
            }
        }
    });
}

function updateTeacher() {


    let ata = {
        account: $("#account").val(),
        email: $("#email").val(),
        name: $("#name").val()
    };
    console.log(ata);
    alert("input");
    $.ajax({
        type: "put",
        url:
            "/teacher/" + Cookies.get("teacher") + "/information",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}
//创建教师用户，服务器返回403
function createTeacher() {
    let ata = {
        account:$("#account").val(),
        initial_password: $("#initial_password").val(),
        name: $("#name").val(),
        email:$("#email").val()
    };
    console.log(ata);
    alert("input");
    $.ajax({
        type: "post",
        url: "/teacher",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}


























//页面跳转 Cookie缓存
function jumpFromCourseHome(courseId){
    Cookies.set("course",courseId);
}
function jumpFromRound(roundId){
    Cookies.set("round",roundId);
}
function jumpFromRoundClass(classId){
    Cookies.set("class",classId);
}


function initCourseShare() {
    getTeamShareList();
    getSeminarShareList();
}
function initShareCreate() {
    getAllCourse();
}
function initTask() {
    getTeamShareTask();
    getSeminarShareTask();
    getTeamValidTask();
}
//教师激活
function activeTeacher() {
    // let ata = {account:$("#account").val(),password:$("#password").val()}
    /*    let ata = {
          password: $("#password").val()
      };*/
    let ata = "555";
    console.log(ata);
    alert("input");
    $.ajax({
        type: "put",
        url: "/teacher/active",
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
            },
            200: function() {
                alert("注册成功");
                window.location.href = "../common/login.html";
            }
        }
    });
    return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
//忘记密码
function sendPassword() {
    // let ata = $("#account").val();
    let ata = "333";
    console.log(ata);
    $.ajax({
        type: "get",
        url: "/user/password?account=" + ata,
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
//主页信息
function initHome(){
    $("#name").html(Cookies.get("name"));
    $("#account").html(Cookies.get("account"));
}
//账户设置页面
function getUserInfo() {
    $.ajax({
        type: "get",
        url: "/user/information",
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
        }
    });
}
function editPassword() {
    // let ata = {password: $("#password").val()};
    let ata = "222";
    console.log(ata);
    $.ajax({
        type: "put",
        url: "/user/password",
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
function editEmail() {
    /*
      let ata = {
          email: $("#email").val()
      };
  */
    let ata = "333@qq.com";
    console.log(ata);
    $.ajax({
        type: "put",
        url: "/user/email",
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
//课程主页


//学生成绩界面
function getRoundListForScore() {
    console.log(Cookies.get("course"));
    $.ajax({
        type: "get",
        url: "/course/" + Cookies.get("course") + "/round",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log("roundlist");
                var content=document.getElementById("content");   //获取外围容器
                var str="";
                $.each(data, function(i, item) {
                    console.log(item);
                    str +='              <div class="col-lg-6">\n' +
                        '                <div class="card card-collapsed">\n' +
                        '                  <div class="card-header">\n' +
                        '                    <h3 class="card-title mr-4">第'+item.id+'轮</h3>\n' +
                        '                    <div class="card-options">\n' +
                        '                      <a\n' +
                        '                        class="card-options-collapse"\n' +
                        '                        data-toggle="card-collapse"\n' +
                        '                        href="#'+item.id+'"\n' +
                        '                        ><i class="fe fe-chevron-up mr-1"></i\n' +
                        '                      ></a>\n' +
                        '                    </div>\n' +
                        '                  </div>\n' +
                        '                  <div class="card-body p-0" id="'+item.id+'">\n' +
                        '                  </div>\n' +
                        '                </div>\n' +
                        '              </div>';
                    content.innerHTML=str;
                    getTeamScoreByRound(item.id);
                });
                content.innerHTML=str;

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
function getTeamScoreByRound() {
    $.ajax({
        type: "get",
        url:
            "/course/" + Cookies.get("course") + "/score",
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

function getSeminarByClass() {
    let seminarId=Cookies.get("seminar");
    let classId=Cookies.get("class");
    console.log(seminarId);
    console.log(classId);

    $.ajax({
        type: "get",
        url:
            "/seminar/" +
            seminarId +
            "/class/" +
            classId,
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                alert("获取成功");
                $("#name").val(data.seminarName);
                $("#introduction").val(data.introduction);
                $("#round").val(data.roundId);
                $("#seminarSerial").val(data.seminarSerial);

            }
            console.log(data);

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
function getSeminarByClassForUpdate() {
    let seminarId=Cookies.get("seminar");
    let classId=Cookies.get("class");
    console.log(seminarId);
    console.log(classId);

    $.ajax({
        type: "get",
        url:
            "/seminar/" +
            seminarId +
            "/class/" +
            classId,
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                alert("获取成功");
                $("#name").val(data.seminarName);
                $("#introduction").val(data.introduction);
                $("#round").val(data.roundId);
                $("#seminarSerial").val(data.seminarSerial);
                $("#enrollStartTime").val(data.enrollStartTime);
                $("#enrollEndTime").val(data.enrollEndTime);
                $("#reportDdl").val(data.reportDdl);
                $("#maxTeam").val(data.roundId);
            }
            console.log(data);

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

function updateSeminarByClass() {


    let ata = {
        reportDdl: convertTime($("#reportDdl").val())
    };
    console.log(ata);
    $.ajax({
        type: "put",
        url:
            "/seminar/" +
            Cookies.get("seminar") +
            "/class/" +
            Cookies.get("class"),
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round-detail.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}
function getSeminarScoreByClass() {
    console.log( Cookies.get("seminar"));
    console.log( Cookies.get("class"));

    $.ajax({
        type: "get",
        url:
            "/score/seminar/" +
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
function updateSeminarScoreByClass() {
    let teamData = { id: 1, name: "1-1" };
    let ata = {
        team: teamData,
        preScpre: "5",
        reportScore: "4",
        questionScore: "5"
    };
    console.log(ata);


    $.ajax({
        type: "put",
        url:
            "/score/seminar/" +
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
    window.location.reload();
}
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
        url: "/course",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
        },
        error: function(data) {
            console.log(data);
            alert("fail");
        },
        statusCode: {
            201: function(data) {
                console.log(data);
                alert("success");
                window.location.href = "./seminar-round.html";
            },
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}
function createSeminar() {

    let ata = {
        roundId: "1",
        seminarName: "1",
        introduction: "1",
        maxTeam: "3",
        visible: true,
        seminarSerial: "1",
        enrollStartTime: convertTime($("#input-start").val()),
        enrollEndTime: convertTime($("#input-end").val()),
        courseId: Cookies.get("course")
    };
    console.log(ata);
    alert("input");
    $.ajax({
        type: "post",
        url: "/seminar",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}
function deleteSeminar() {
    let cid = "2";
    $.ajax({
        type: "delete",
        url: "/seminar/" + cid,
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
function updateSeminar() {

    let ata = {
        roundId: "1",
        seminarName: "1",
        introduction: "1",
        maxTeam: "3",
        visible: true,
        seminarSerial: "1",
        enrollStartTime: convertTime($("#input-start").val()),
        enrollEndTime: convertTime($("#input-end").val()),
        courseId: Cookies.get("course")
    };
    console.log(ata);
    $.ajax({
        type: "put",
        url: "/seminar/" + Cookies.get("seminar"),
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}
function getAttendanceByClass() {

    $.ajax({
        type: "get",
        url:
            "/seminar/" +
            Cookies.get("seminar") +
            "/class/" +
            Cookies.get("class") +
            "/attendance",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                alert("获取成功");
                if (xhr.status === 200) {
                    // alert("获取成功");
                    console.log("courselist");
                    var tabContent=document.getElementById("nav-content");   //获取外围容器
                    var strTab="";
                    let currentId=data[0].id;
                    $.each(data, function(i, item) {
                        if (item.presented===true)
                            currentId=item.id;
                    });
                    $.each(data, function(i, item) {
                        console.log(item);
                        let navClass="list-group-item list-group-item-action d-flex align-items-center px-1 py-3";
                        if (item.id==currentId)
                        {
                            navClass="list-group-item list-group-item-action d-flex align-items-center active px-1 py-3";
                        }

                        strTab+='<a class="'+navClass+'" id="'+item.id+'" href="#" onclick="tabClick('+item.id+')">' +
                            '                      <span class="icon mr-3"><i class="fe fe-inbox"></i></span\n' +
                            '                      >'+item.classSerial+'-'+item.teamSerial+'<span class="ml-auto badge badge-primary"></span>\n' +
                            '                    </a>';
                    });
                    Cookies.set("attendance",currentId);
                    tabContent.innerHTML=strTab;
                    getAttendanceScore(currentId);
                    getQuestionList(currentId);
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
function getPptByClass() {


    $.ajax({
        type: "get",
        url:
            "/seminar/" +
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
function getReportByClass() {

    $.ajax({
        type: "get",
        url:
            "/seminar/" +
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
function getPptByAttendance() {

    $.ajax({
        type: "get",
        url:
            "/attendance/" + Cookies.get("attendance") + "/ppt",
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
function getReportByAttendance() {

    $.ajax({
        type: "get",
        url:
            "/attendance/" +
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
function getTeam() {
    console.log(Cookies.get("course"));

    $.ajax({
        type: "get",
        url: "/course/" + Cookies.get("course") + "/team",
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

    $.ajax({
        type: "get",
        url: "/course/" + Cookies.get("course") + "/team",
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
    let member = [
        {
            id: 124
        }
    ];
    let leader = [
        {
            id: 123
        }
    ];
    let ata = {
        id: 1,
        name: "4-2 早早鸟小组",
        course: {
            id: 1
        },
        class: {
            id: 1
        },
        leader: leader,
        members: member
    };
    console.log(ata);
    $.ajax({
        type: "post",
        url: "/team",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
        },
        error: function(data) {
            console.log(data);
            alert("fail");
        },
        statusCode: {
            201: function(data) {
                console.log(data);
                alert("success");
                window.location.href = "./seminar-round.html";
            },
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}
function updateTeam() {

    let member = [
        {
            id: 123
        },
        {
            id: 124
        }
    ];
    let leader = [
        {
            id: 123
        }
    ];
    let ata = {
        id: 1,
        name: "4-2 早早鸟小组",
        course: {
            id: 1
        },
        class: {
            id: 1
        },
        leader: leader,
        members: member,
        valid: true
    };
    console.log(ata);
    $.ajax({
        type: "put",
        url: "/team/" + Cookies.get("team"),
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
    console.log(Cookies.get("course"));
    $.ajax({
        type: "get",
        url: "/course/" + Cookies.get("course") + "/noTeam",
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
        grade: $("#grade").val(),
        klassSerial:$("#class").val(),
        klassLocation: $("#location").val(),
        klassTime:$("#time").val(),
    };
    console.log(ata);
    alert(Cookies.get("course"));
    $.ajax({
        type: "post",
        url: "/course/" + Cookies.get("course") + "/class",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
        },
        error: function(data) {
            console.log(data);
            alert("fail");
        },
        statusCode: {
            201: function(data) {
                console.log(data);
                alert("success");
                window.location.href = "./seminar-round.html";
            },
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}
function deleteCourse(courseId) {
    let cid = "2";
    $.ajax({
        type: "delete",
        url: "/course/" + courseId,
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
function getRoundScoreByCourse() {
    $.ajax({
        type: "get",
        url:
            "/score/course/" + Cookies.get("course") + "/score",
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
function getSeminarScoreByRound(cid) {


    $.ajax({
        type: "get",
        url: "/score/round/" + Cookies.get("round") + "/score",
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
            "/seminar/" +
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
        url: "/course/" + Cookies.get("course") + "/teamshare",
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
        url: "/course/" + Cookies.get("course") + "/seminarshare",
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
        url: "/course/seminarshare/" + cid,
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
        url: "/course/teamshare/" + cid,
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
        url: "/round/" + Cookies.get("round"),
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
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

    let ata = {
        courseId: Cookies.get("course")
    };
    console.log(ata);
    alert("input");
    $.ajax({
        type: "post",
        url: "/round",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
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

    let ata = {
        subCourseId: "1"
    };
    console.log(ata);
    alert("input");
    $.ajax({
        type: "post",
        url:
            "/course/" +
            Cookies.get("course") +
            "/teamsharerequest",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
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

    let ata = {
        subCourseId: "1"
    };
    console.log(ata);
    alert("input");
    $.ajax({
        type: "post",
        url:
            "/course/" +
            Cookies.get("course") +
            "/seminarsharerequest",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
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
        url: "/request/teamshare",
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
        url: "/request/seminarshare",
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
        url: "/request/teamvaild",
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

    let ata = {
        handletype: "accept"
    };
    console.log(ata);
    $.ajax({
        type: "put",
        url: "/request/teamvalid/" + Cookies.get("teamvalid"),
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

    let ata = {
        handletype: "accept"
    };
    console.log(ata);
    $.ajax({
        type: "put",
        url: "/request/teamshare/" + Cookies.get("teamshare"),
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

    let ata = {
        handletype: "accept"
    };
    console.log(ata);
    $.ajax({
        type: "put",
        url:
            "/request/seminarshare/" +
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

function getQuestionList(attendanceId) {

    $.ajax({
        type: "get",
        url:
            "/seminar/" +
            Cookies.get("seminar") +
            "/class/" +
            Cookies.get("class") +
            "/attendance/" +
            attendanceId +
            "/question",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                alert("获取成功");
                var quesContent=document.getElementById("ques-content");   //获取外围容器
                let currentId=data[0].id;
                let str="";
                $.each(data, function(i, item) {
                    console.log(item);
                    let quesClass="list-group-item list-group-item-action d-flex align-items-center px-0";
                    if (item.id==currentId)
                    {
                        quesClass="list-group-item list-group-item-action d-flex align-items-center active px-0";
                        $("#question-score").val(item.score);

                    }

                    str+='<a' +
                        '                                    class="'+quesClass+'"' +
                        '                                    href="#" id="'+item.id+'"' +
                        '                                    onclick="quesClick('+item.id+')"' +
                        '                            >\n' +
                        '                              <span class="icon mr-3"\n' +
                        '                              ><i class="fe fe-send"></i></span\n' +
                        '                              >' +item.klassSerial+'-'+item.teamSerial+
                        '                            </a>';
                });
                quesContent.innerHTML=str;
                Cookies.set("question",currentId);

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
        presentationScore: "5"
    };
    console.log(ata);

    $.ajax({
        type: "put",
        url:
            "/attendance/" + Cookies.get("attendance") + "/score",
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

    $.ajax({
        type: "put",
        url: "/question/" + Cookies.get("question"),
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
function getCurrentSeminar() {
    $.ajax({
        type: "get",
        url: "/seminar",
        dataType: "json",
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("getCurrentSeminar success");
            if (xhr.status === 200) {
                Cookies.set("class",data.klassId);
                Cookies.set("seminar",data.seminarId);
                getAttendanceByClass(data.klassId, data.seminarId);
            }
        },
        error: function(data){
            console.log(data);
            alert("error");

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
            "/round/" +
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
        url: "/team/" + cid,
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
        url: "/team/" + cid + "/remove",
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
function getMyAttendance() {


    $.ajax({
        type: "get",
        url:
            "/seminar/" +
            Cookies.get("seminar") +
            "/class/" +
            Cookies.get("class") +
            "/teamAttendance",
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
function getAttendanceScore(attendanceId) {

    $.ajax({
        type: "get",
        url:
            "/attendance/" +
            attendanceId +
            "/score",
        dataType: "json",
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            if (xhr.status === 200) {
                $("#present-score").val(data.presentationScore);

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
function getQuestionScore(questionId) {

    $.ajax({
        type: "get",
        url:
            "/question/" +
            questionId,
        dataType: "json",
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            if (xhr.status === 200) {
                $("#question-score").val(data.score);

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

function updatePresentScore() {
    let ata = {
        presentationScore: $("#present-score").val()
    };
    console.log(ata);
    let attendance=Cookies.get("attendance");
    alert(attendance);
    $.ajax({
        type: "put",
        url:
            "/attendance/" +
            Cookies.get("attendance")+"/score",
        dataType: "json",
        data: JSON.stringify(ata),

        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            if (xhr.status === 200) {
                alert("修改打分成功");
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
function updateQuestionScore() {
    let ata = {
        score: $("#question-score").val()
    };

    $.ajax({
        type: "put",
        url:
            "/question/" +
            Cookies.get("question"),
        dataType: "json",
        data: JSON.stringify(ata),

        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            if (xhr.status === 200) {
                alert("修改打分成功");
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
function createAttendance() {



    let ata = {
        courseId: Cookies.get("course")
    };
    console.log(ata);
    alert("input");
    $.ajax({
        type: "post",
        url:
            "/seminar/" +
            Cookies.get("seminar") +
            "/class/" +
            Cookies.get("class") +
            "/attendance",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}
function deleteAttendance() {
    let cid = "2";
    $.ajax({
        type: "delete",
        url: "/attendance/" + cid,
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
function updateAttendance() {
    Cookies.set("attendanceId", "1");
    let ata = {
        teamOrder: "1"
    };
    console.log(ata);
    $.ajax({
        type: "put",
        url: "/attendance/" + Cookies.get("attendance"),
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


/**function createTeacher() {
    let ata = {
        account: "11111",
        email: "11111@qq.com",
        name: "11111"
    };
    console.log(ata);
    alert("input");
    $.ajax({
        type: "post",
        url: "/teacher",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}**/
function updateTeacher() {


    let ata = {
        account: "11111",
        email: "11111@qq.com",
        name: "11111"
    };
    console.log(ata);
    alert("input");
    $.ajax({
        type: "put",
        url:
            "/teacher/" + Cookies.get("teacher") + "/information",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}
function updateStudent() {


    let ata = {
        account: "11111",
        email: "11111@qq.com",
        name: "11111"
    };
    console.log(ata);
    alert("input");
    $.ajax({
        type: "put",
        url:
            "/student/" + Cookies.get("student") + "/information",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}
/**function deleteTeacher() {


    console.log(ata);
    alert("input");
    $.ajax({
        type: "delete",
        url: "/teacher/" + Cookies.get("teacher"),
        dataType: "json",
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}**/
/**function deleteStudent() {


    console.log(ata);
    alert("input");
    $.ajax({
        type: "delete",
        url: "/student/" + Cookies.get("student"),
        dataType: "json",
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}**/

function resetTeacher() {


    console.log(ata);
    alert("input");
    $.ajax({
        type: "put",
        url: "/teacher/" + Cookies.get("teacher") + "/password",
        dataType: "json",
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}
function resetStudent() {


    console.log(ata);
    alert("input");
    $.ajax({
        type: "put",
        url: "/student/" + Cookies.get("student") + "/password",
        dataType: "json",
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}

/**function searchTeacher() {

    let ata = {
        identity: "1"
    };
    console.log(ata);
    alert("input");
    $.ajax({
        type: "delete",
        url: "/teacher/searchTeacher",
        dataType: "json",
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}**/
/**function searchStudent() {

    let ata = {
        identity: "1"
    };
    console.log(ata);
    alert("input");
    $.ajax({
        type: "delete",
        url: "/student/searchStudent",
        dataType: "json",
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}**/
function getAllTeacher() {
    $.ajax({
        type: "get",
        url: "/teacher",
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
function getAllStudent() {
    $.ajax({
        type: "get",
        url: "/student",
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

function getAllCourse() {
    $.ajax({
        type: "get",
        url: "/course/allcourse",
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
function getCourseInfo() {
    $.ajax({
        type: "get",
        url: "/course/" + Cookies.get("course"),
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
function getRoundInfo() {
    $.ajax({
        type: "get",
        url: "/round/" + Cookies.get("round"),
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

function updateClass(classId) {


    var fileToUpload = document.getElementById("file"+classId).prop("files")[0];
    let ata = { file: fileToUpload };
    console.log(ata);
    $.ajax({
        type: "put",
        url: "/class/" + Cookies.get("class"),
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./seminar-round-detail.html";
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
function deleteClass(classId) {
    $.ajax({
        type: "delete",
        url: "/class/" + classId,
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
            },
            200: function(){
                alert("成功");
            }
        }
    });
    window.location.reload();
}