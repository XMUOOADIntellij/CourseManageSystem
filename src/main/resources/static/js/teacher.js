//页面跳转 Cookie缓存
function jumpFromCourseHome(courseId){
    Cookies.set("course",courseId);
}
function jumpFromRound(roundId){
    Cookies.set("round",roundId);
}
function jumpFromRoundClass(seminarId,classId){
    Cookies.set("seminar",seminarId);
    Cookies.set("class",classId);
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
//主页信息
function initHome(){
    $("#name").html(Cookies.get("name"));
    $("#account").html(Cookies.get("account"));
}
//账户设置页面
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
        }
    });
}
function editPassword() {
    // let ata = {password: $("#password").val()};
    let ata = "222";
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
//课程主页
function getCourseList() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn/course",
        dataType: "json",
        async : false,

        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log("courselist");
                var content=document.getElementById("content");   //获取外围容器
                var str="";
                $.each(data, function(i, item) {
                    console.log(item);
                    let courseType="副";
                    if(item.teamMainCourse===null) courseType="主";
                    str +='            <div class="col-md-6 col-lg-4" >\n' +
                        '                <div class="card">\n' +
                        '                  <div class="card-header">\n' +
                        '                    <h3 class="card-title mr-4">'+item.courseName+'</h3>\n' +
                        '                    <span class="btn btn-outline-secondary py-0">主</span>\n' +
                        '                    <div class="card-options">\n' +
                        '                      <a\n' +
                        '                        href="#"\n' +
                        '                        class="card-options-remove"\n' +
                        '                        onclick="deleteCourse('+item.id+')"\n' +
                        '                        ><i class="fe fe-trash"></i\n' +
                        '                      ></a>\n' +
                        '                      <a\n' +
                        '                        class="card-options-collapse"\n' +
                        '                        data-toggle="card-collapse"\n' +
                        '                        href="#"\n' +
                        '                        ><i class="fe fe-chevron-up mr-1"></i\n' +
                        '                      ></a>\n' +
                        '                    </div>\n' +
                        '                  </div>\n' +
                        '                  <div class="card-body p-0">\n' +
                        '                    <div class="list-group">\n' +
                        '                      <a\n' +
                        '                        href="./course-info.html"\n' +
                        '                        class="list-group-item list-group-item-action"\n' +
                        '                        onclick="jumpFromCourseHome('+item.id+')"\n' +
                        '                      >\n' +
                        '                        <span class="float-right">\n' +
                        '                          <i class="icon fe fe-chevron-right"></i>\n' +
                        '                        </span>\n' +
                        '                        <span class="float-left">\n' +
                        '                          <i class="icon fe fe-book mr-3"></i> 课程信息\n' +
                        '                        </span>\n' +
                        '                      </a>\n' +
                        '                      <a\n' +
                        '                        href="./course-class.html"\n' +
                        '                        class="list-group-item list-group-item-action"\n' +
                        '                        onclick="jumpFromCourseHome('+item.id+')"\n' +
                        '                      >\n' +
                        '                        <span class="float-right">\n' +
                        '                          <i class="icon fe fe-chevron-right"></i>\n' +
                        '                        </span>\n' +
                        '                        <span class="float-left">\n' +
                        '                          <i class="icon fe fe-briefcase mr-3"></i> 班级信息\n' +
                        '                        </span>\n' +
                        '                      </a>\n' +
                        '                      <a\n' +
                        '                        href="./course-round.html"\n' +
                        '                        class="list-group-item list-group-item-action"\n' +
                        '                        onclick="jumpFromCourseHome('+item.id+')"\n' +
                        '\n' +
                        '                      >\n' +
                        '                        <span class="float-right">\n' +
                        '                          <i class="icon fe fe-chevron-right"></i>\n' +
                        '                        </span>\n' +
                        '                        <span class="float-left">\n' +
                        '                          <i class="icon fe fe-book-open mr-3"></i> 课程轮次\n' +
                        '                        </span>\n' +
                        '                      </a>\n' +
                        '                      <a\n' +
                        '                        href="course-team.html"\n' +
                        '                        class="list-group-item list-group-item-action"\n' +
                        '                        onclick="jumpFromCourseHome('+item.id+')"\n' +
                        '\n' +
                        '                      >\n' +
                        '                        <span class="float-right">\n' +
                        '                          <i class="icon fe fe-chevron-right"></i>\n' +
                        '                        </span>\n' +
                        '                        <span class="float-left">\n' +
                        '                          <i class="icon fe fe-slack mr-3"></i> 学生组队\n' +
                        '                        </span>\n' +
                        '                      </a>\n' +
                        '\n' +
                        '                      <a\n' +
                        '                        href="course-score.html"\n' +
                        '                        class="list-group-item list-group-item-action"\n' +
                        '                        onclick="jumpFromCourseHome('+item.id+')"\n' +
                        '\n' +
                        '                      >\n' +
                        '                        <span class="float-right">\n' +
                        '                          <i class="icon fe fe-chevron-right"></i>\n' +
                        '                        </span>\n' +
                        '                        <span class="float-left">\n' +
                        '                          <i class="icon fe fe-clipboard mr-3"></i> 学生成绩\n' +
                        '                        </span>\n' +
                        '                      </a>\n' +
                        '\n' +
                        '                      <a\n' +
                        '                        href="./course-share.html"\n' +
                        '                        class="list-group-item list-group-item-action"\n' +
                        '                        onclick="jumpFromCourseHome('+item.id+')"\n' +
                        '\n' +
                        '                      >\n' +
                        '                        <span class="float-right">\n' +
                        '                          <i class="icon fe fe-chevron-right"></i>\n' +
                        '                        </span>\n' +
                        '                        <span class="float-left">\n' +
                        '                          <i class="icon fe fe-send mr-3"></i> 共享设置\n' +
                        '                        </span>\n' +
                        '                      </a>\n' +
                        '                    </div>\n' +
                        '                  </div>\n' +
                        '                </div>\n'+
                        '                </div>\n';

                })
                content.innerHTML=str;

            }
            return data;
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
//班级信息界面
function getClassItems() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn:8080/course/" + Cookies.get("course") + "/class",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log("classlist");
                var content=document.getElementById("content");   //获取外围容器
                var str="";

                $.each(data, function(i, item) {
                    console.log(item);
                    str +='    <div class="card">\n' +
                        '                  <div class="card-header">\n' +
                        '                    <h3 class="card-title">'+item.grade+'-'+item.klassSerial+'</h3>\n' +
                        '                    <div class="card-options">\n' +
                        '                      <a\n' +
                        '                        href="#"\n' +
                        '                        class="card-options-remove"\n' +
                        '                        onclick="deleteClass('+item.id+')"\n' +
                        '                        ><i class="fe fe-trash"></i\n' +
                        '                      ></a>\n' +
                        '                      <a\n' +
                        '                        href="#"\n' +
                        '                        class="card-options-collapse"\n' +
                        '                        data-toggle="card-collapse"\n' +
                        '                        ><i class="fe fe-chevron-up"></i\n' +
                        '                      ></a>\n' +
                        '                    </div>\n' +
                        '                  </div>\n' +
                        '\n' +
                        '                  <div class="card-body">\n' +
                        '                    <div>\n' +
                        '                      <div class="form-group">\n' +
                        '                        <label class="form-label">讨论课时间</label>\n' +
                        '                        <input class="form-control" id="time" type="text" placeholder="'+item.klassTime+'" readonly="">\n' +
                        '                      </div>\n' +
                        '                      <div class="form-group">\n' +
                        '                        <label class="form-label">讨论课地点</label>\n' +
                        '                        <input class="form-control" id="location" type="text" placeholder="'+item.klassLocation+'" readonly="">\n' +
                        '                      </div>\n' +
                        '                      <div class="form-group">\n' +
                        '                        <div class="form-label">班级学生名单</div>\n' +
                        '                        <div class="custom-file">\n' +
                        '                          <input\n' +
                        '                            type="file"\n' +
                        '                            id="file'+item.id+'"\n' +
                        '                            class="custom-file-input"\n' +
                        '                            name="example-file-input-custom"\n' +
                        '                          />\n' +
                        '                          <label class="custom-file-label">选择文件</label>\n' +
                        '                        </div>\n' +
                        '                      </div>\n' +
                        '\n' +
                        '                      <div class="form-footer">\n' +
                        '                        <button\n' +
                        '                          class="btn btn-primary btn-block"\n' +
                        '                          onclick="updateClass('+item.id+')"\n' +
                        '                        >\n' +
                        '                          确认修改\n' +
                        '                        </button>\n' +
                        '                      </div>\n' +
                        '                    </div>\n' +
                        '                  </div>\n' +
                        '                </div>';
                });
                content.innerHTML=str;

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
//轮次设置页面
function getRoundList() {
    let courseId=Cookies.get("course");
    console.log(courseId);
    $.ajax({
        type: "get",
        url: "http://xug98.cn:8080/course/" + Cookies.get("course") + "/round",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                // console.log("roundlist");
                var content=document.getElementById("content");   //获取外围容器
                var str="";
                $.each(data, function(i, item) {
                    str +='            <div class="col-md-6 col-lg-4" >\n' +
                        '                <div class="card">\n' +
                        '                  <div class="card-header">\n' +
                        '                    <h3 class="card-title mr-4">第'+item.id+'轮</h3>\n' +
                        '                    <div class="card-options">\n' +
                        '                      <a\n' +
                        '                        href="course-round-setting.html"\n' +
                        '                        onclick="jumpFromRound('+item.id+')"' +
                        '                        class="card-options-delete"\n' +
                        '                        ><i class="fe fe-settings"></i\n' +
                        '                      ></a>\n' +
                        '                      <a\n' +
                        '                        href="#"\n' +
                        '                        class="card-options-collapse"\n' +
                        '                        data-toggle="card-collapse"\n' +
                        '                        ><i class="fe fe-chevron-up"></i\n' +
                        '                      ></a>\n' +
                        '                    </div>\n' +
                        '                  </div>\n' +
                        '                  <div class="card-body p-0" id="round'+item.id+'">\n' +
                        '                    \n' +
                        '                  </div>\n' +
                        '                </div>\n' +
                        '              </div>';
                    content.innerHTML=str;
                    getSeminarList(item.id);
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
function getSeminarList(roundId) {
    $.ajax({
        type: "get",
        url: "http://xug98.cn/round/" + roundId + "/seminar",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                // console.log("seminarList");
                var content=document.getElementById("round"+roundId);   //获取外围容器
                var str="";
                $.each(data, function(i, item) {
                    console.log(item);
                    str +='                    <div class="card m-0">\n' +
                        '                      <div class="card-header">\n' +
                        '                        <div class="d-flex align-items-center">\n' +
                        '                          <span class="text-smallest">\n' +
                        '                            <i class="icon fe fe-book-open mr-3"></i>\n'
                        +item.seminarName+
                        '                          </span>\n' +
                        '                        </div>\n' +
                        '                        <div class="card-options">\n' +
                        '                          <a\n' +
                        '                            href="course-round-detail-setting.html"\n' +
                        '                            class="card-options-delete"\n' +
                        '                            ><i class="fe fe-settings"></i\n' +
                        '                          ></a>\n' +
                        '                          <a\n' +
                        '                            onclick="deleteSeminar('+item.seminarId+')"\n' +
                        '                            class="card-options-delete"\n' +
                        '                            ><i class="fe fe-trash"></i\n' +
                        '                          ></a>\n' +
                        '                          <a\n' +
                        '                            class="card-options-collapse"\n' +
                        '                            data-toggle="card-collapse"\n' +
                        '                            href="#"\n' +
                        '                            ><i class="fe fe-chevron-up mr-1"></i\n' +
                        '                          ></a>\n' +
                        '                        </div>\n' +
                        '                      </div>\n' +
                        '                      <div class="card-body p-0" id="seminar'+item.seminarId+'">\n' +
                        '<div class="table-responsive">\n' +
                        '                          <table\n' +
                        '                            class="table card-table table-vcenter text-nowrap"\n' +
                        '                          >\n' +
                        '                            <tbody id="seminar-'+item.seminarId+'">\n' +
                        '                      \n' +
                        '                            </tbody>\n' +
                        '                          </table>\n' +
                        '                        </div>'+
                        '                      </div>\n' +
                        '                    </div>\n';

                });
                content.innerHTML=str;
                $.each(data, function(i, item) {
                    console.log(item);
                    getClassList(item.seminarId);
                    Cookies.set("class",item.seminarId);
                });



            }
        },
        statusCode: {
            500: function(data) {

                alert("seminarList");
                alert("错误的ID格式");
            },
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
function getClassList(seminarId) {
    $.ajax({
        type: "get",
        url: "http://xug98.cn:8080/course/" + Cookies.get("course") + "/class",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                // console.log("classlist");
                var content=document.getElementById("seminar-"+seminarId);   //获取外围容器
                var str="";
                $.each(data, function(i, item) {
                    console.log(item);
                    str +='<tr>\n' +
                        '                                <td>\n' +
                        '                                  <span class="text-muted">'+item.grade+'-'+item.klassSerial+'</span>\n' +
                        '                                </td>\n' +
                        '\n' +
                        '                                <td>\n' +
                        '                                  <a\n' +
                        '                                    class="icon float-right"\n' +
                        '                                    href="course-round-detail.html"\n' +
                        '                        onclick="jumpFromRoundClass('+seminarId+','+item.id+')"\n' +
                        '                                  >\n' +
                        '                                    <i class="fe fe-chevron-right"></i>\n' +
                        '                                  </a>\n' +
                        '                                </td>\n' +
                        '                              </tr>';
                    content.innerHTML=str;

                });

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
//学生成绩界面
function getRoundListForScore() {
    console.log(Cookies.get("course"));
    $.ajax({
        type: "get",
        url: "http://xug98.cn:8080/course/" + Cookies.get("course") + "/round",
        dataType: "json",
        async : false,
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                // console.log("roundlist");
                var content=document.getElementById("content");   //获取外围容器
                var str="";
                $.each(data, function(i, item) {
                    // console.log(item);
                    str +='              <div class="col-lg-6">\n' +
                        '                <div class="card card-collapsed">\n' +
                        '                  <div class="card-header">\n' +
                        '                    <h3 class="card-title mr-4">第'+item.roundSerial+'轮</h3>\n' +
                        '                    <div class="card-options">\n' +
                        '                      <a\n' +
                        '                        class="card-options-collapse"\n' +
                        '                        data-toggle="card-collapse"\n' +
                        '                        href="#"\n' +
                        '                        ><i class="fe fe-chevron-up mr-1"></i\n' +
                        '                      ></a>\n' +
                        '                    </div>\n' +
                        '                  </div>\n' +
                        '                  <div class="card-body p-0" id="round'+item.id+'">\n' +
                        '                  </div>\n' +
                        '                </div>\n' +
                        '              </div>';
                    content.innerHTML=str;

                });
                $.each(data, function(i, item) {
                    getTeamTotalScoreByRound(item.id);
                });

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
function getTeamTotalScoreByRound(roundId) {
    $.ajax({
        type: "get",
        async : false,

        url:
            "http://xug98.cn:8080/course/" + Cookies.get("course") + "/round/"+ roundId + "/score",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                 // alert("获取成功");
                // console.log("teamscore");
                var content=document.getElementById("round"+roundId);   //获取外围容器
                var str="";
                content.innerHTML+="";
                $.each(data, function(i, item) {
                    // console.log(item);
                    str ='                    <div class="card card-collapsed m-0">\n' +
                        '                      <div class="card-header">\n' +
                        '                        <div class="d-flex align-items-center">\n' +
                        '                          <span class="stamp stamp-md bg-blue ml-1 mr-4"\n' +
                        '                            >'+item.classSerial+'-'+item.teamSerial+'</span\n' +
                        '                          >\n' +
                        '                          <span class="btn btn-outline-success py-0">'+item.totalScore+'</span>\n' +
                        '                        </div>\n' +
                        '                        <div class="card-options">\n' +
                        '                          <a\n' +
                        '                            class="card-options-collapse"\n' +
                        '                            data-toggle="card-collapse"\n' +
                        '                            href="#"\n' +
                        '                            ><i class="fe fe-chevron-up mr-1"></i\n' +
                        '                          ></a>\n' +
                        '                        </div>\n' +
                        '                      </div>\n' +
                        '                      <div class="card-body p-0" id="'+roundId+'-'+item.teamId+'">\n' +
                        '                        <div class="table-responsive">\n' +
                        '                          <table\n' +
                        '                            class="table card-table table-vcenter text-nowrap"\n' +
                        '                          >\n' +
                        '                            <thead>\n' +
                        '                              <tr>\n' +
                        '                                <th class="w-auto">讨论课</th>\n' +
                        '                                <th>课堂展示</th>\n' +
                        '                                <th>课堂提问</th>\n' +
                        '                                <th>书面报告</th>\n' +
                        '                                <th></th>\n' +
                        '                              </tr>\n' +
                        '                            </thead>\n' +
                        '                            <tbody id="'+roundId+'T'+item.teamId+'">\n' +
                        '                              <tr>\n' +
                        '                                <td><span class="text-muted">总成绩</span></td>\n' +
                        '                                <td>\n' +
                        '                                  <span class="status-icon bg-success"></span>\n' +
                        item.presentationScore +
                        '                                </td>\n' +
                        '                                <td>\n' +
                        '                                  <span class="status-icon bg-success"></span>\n' +
                        item.questionScore +
                        '                                </td>\n' +
                        '                                <td>\n' +
                        '                                  <span class="status-icon bg-success"></span>\n' +
                        item.reportScore+
                        '                                </td>\n' +
                        '                                <td></td>\n' +
                        '                              </tr>\n' +
                        '                            </tbody>\n' +
                        '                          </table>\n' +
                        '                        </div>\n' +
                        '                      </div>\n' +
                        '                    </div>\n';
                    content.innerHTML+=str;

                });
                $.each(data, function(i, item) {
                    getTeamDetailScoreByRound(roundId,item.teamId);
                });

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
function getTeamDetailScoreByRound(roundId,teamId) {
    // console.log(roundId+'T'+teamId);
    $.ajax({
        type: "get",
        url:
            "http://xug98.cn:8080/round/" + roundId + "/score?teamId="+ teamId,
        dataType: "json",
        async : false,
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                // console.log("teamscore");
                // console.log(roundId+'T'+teamId);

                var content=document.getElementById(roundId+'T'+teamId);   //获取外围容器
                var str="";
                $.each(data, function(i, item) {
                    // console.log(item);
                    str +='  <tr>\n' +
                        '                                <td>\n' +
                        '                                  <span class="text-muted">'+item.seminarName+'</span>\n' +
                        '                                </td>\n' +
                        '                                <td>\n' +
                        '                                  <span class="status-icon bg-success"></span>\n' +
                        item.presentationScore +
                        '                                </td>\n' +
                        '                                <td>\n' +
                        '                                  <span class="status-icon bg-success"></span>\n' +
                        item.questionScore +
                        '                                </td>\n' +
                        '                                <td>\n' +
                        '                                  <span class="status-icon bg-success"></span>\n' +
                        item.reportScore+
                        '                                </td>\n' +
                        '                                <td>\n' +
                        '                                  <a class="icon" href="javascript:void(0)">\n' +
                        '                                    <i class="fe fe-edit"></i>\n' +
                        '                                  </a>\n' +
                        '                                </td>\n' +
                        '                              </tr>';
                    content.innerHTML+=str;

                });

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

//讨论课详情
function getSeminarByClass() {
    let seminarId=Cookies.get("seminar");
    let classId=Cookies.get("class");
    //
    // console.log(seminarId);
    // console.log(classId);
    $.ajax({
        type: "get",
        url:
            "http://xug98.cn:8080/seminar/" +
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
function getSeminarScoreByClass() {
    console.log( Cookies.get("seminar"));
    console.log( Cookies.get("class"));

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
                console.log("roundlist");
                var content=document.getElementById("content");   //获取外围容器
                var str="";
                $.each(data, function(i, item) {
                    console.log(item);
                    str +='                        <tr>\n' +
                        '                          <td><span class="text-muted">1-1</span></td>\n' +
                        '                          <td>\n' +
                        '                            <span class="status-icon bg-success"></span> 5.0\n' +
                        '                          </td>\n' +
                        '                          <td>\n' +
                        '                            <span class="status-icon bg-success"></span> 5.0\n' +
                        '                          </td>\n' +
                        '                          <td>\n' +
                        '                            <span class="status-icon bg-success"></span> 5.0\n' +
                        '                          </td>\n' +
                        '                          <td>\n' +
                        '                            <a\n' +
                        '                              class="icon"\n' +
                        '                              href="javascript:void(0)"\n' +
                        '                              onclick="updateSeminarScoreByClass()"\n' +
                        '                            >\n' +
                        '                              <i class="fe fe-edit"></i>\n' +
                        '                            </a>\n' +
                        '                          </td>\n' +
                        '                        </tr>\n';
                    content.innerHTML=str;
                    getTeamTotalScoreByRound(item.id);
                });
                content.innerHTML=str;

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
function getReportByClass() {
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
//修改讨论课详情设置
function getSeminarByClassForUpdate() {
    let seminarId=Cookies.get("seminar");
    let classId=Cookies.get("class");
    console.log(seminarId);
    console.log(classId);

    $.ajax({
        type: "get",
        url:
            "http://xug98.cn:8080/seminar/" +
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
                let start=reconvertTime(data.enrollStartTime);
                let end=reconvertTime(data.enrollEndTime);
                $("#enrollStartTime").val(start);
                $("#enrollEndTime").val(end);
                let ddl=reconvertTime(data.reportDdl);
                $("#reportDdl").val(ddl);
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
//课程组队
function getTeam() {
    console.log(Cookies.get("course"));
    $.ajax({
        type: "get",
        async : false,

        url: "http://xug98.cn:8080/course/" + Cookies.get("course") + "/team",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log("roundlist");
                var content=document.getElementById("content");

                let str="";
                let status="valid";
                //获取外围容器
                $.each(data, function(i, team) {
                    console.log(team);

                    if(team.status==0) status="invalid";
                    let innerStr="";
                    innerStr += '\n' +
                        '                          <tr>\n' +
                        '                            <td class="text-nowrap">' + team.leader.studentName + '</td>\n' +
                        '                            <td>' + team.leader.account + '</td>\n' +
                        '                            <td>组长</td>\n' +
                        '                          </tr>';

                    $.each(team.members, function (i, item) {
                        // console.log(item);
                        innerStr += '\n' +
                            '                          <tr>\n' +
                            '                            <td class="text-nowrap">' + item.studentName + '</td>\n' +
                            '                            <td>' + item.account + '</td>\n' +
                            '                            <td>组员</td>\n' +
                            '                          </tr>';
                    });
                    str+='              <div class="col-lg-4">\n' +
                        '                <div class="card card-collapsed">\n' +
                        '                  <div class="card-header">\n' +
                        '                    <div class="d-flex align-items-center">\n' +
                        '                      <span class="stamp stamp-md bg-blue ml-1 mr-4">'+team.klassSerial+'-'+team.teamSerial+'</span>\n' +
                        '                      <div>\n' +
                        '                        <h4 class="m-0"><small>'+team.name+'</small></h4>\n' +
                        '                        <small class="text-danger">'+status+'</small>\n' +
                        '                      </div>\n' +
                        '                    </div>\n' +
                        '                    <div class="card-options">\n' +
                        '                      <a\n' +
                        '                        class="card-options-collapse"\n' +
                        '                        data-toggle="card-collapse"\n' +
                        '                        href="#"\n' +
                        '                        ><i class="fe fe-chevron-up mr-1"></i\n' +
                        '                      ></a>\n' +
                        '                    </div>\n' +
                        '                  </div>\n' +
                        '                  <div class="card-body p-0">\n' +
                        '                    <div class="table-responsive">\n' +
                        '                      <table\n' +
                        '                        class="table card-table table-striped table-vcenter"\n' +
                        '                      >\n' +
                        '                        <tbody>\n' +
                        innerStr +
                        '                        </tbody>\n' +
                        '                      </table>\n' +
                        '                    </div>\n' +
                        '                  </div>\n' +
                        '                </div>\n' +
                        '              </div>\n';
                });
                content.innerHTML=str;

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
//课程共享
function getTeamShareList() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn/course/" + Cookies.get("course") + "/teamshare",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log("roundlist");
                var content=document.getElementById("content");

                let str="";
                let status="valid";
                //获取外围容器
                $.each(data, function(i, item) {
                    str+='              <div class="col-md-6 col-xl-4">\n' +
                        '                <div class="card">\n' +
                        '                  <div class="card-header">\n' +
                        '                    <h3 class="card-title mr-4">OOAD</h3>\n' +
                        '                    <span class="btn btn-outline-secondary py-0">邱明</span>\n' +
                        '                    <div class="card-options">\n' +
                        '                      <a\n' +
                        '                        class="card-options-collapse"\n' +
                        '                        data-toggle="card-collapse"\n' +
                        '                        href="#"\n' +
                        '                        ><i class="fe fe-chevron-up mr-1"></i\n' +
                        '                      ></a>\n' +
                        '                    </div>\n' +
                        '                  </div>\n' +
                        '                  <div class="card-body">\n' +
                        '                    <form>\n' +
                        '                      <div class="form-group">\n' +
                        '                        <label class="form-label">共享类型</label>\n' +
                        '                        <div class="input-group">\n' +
                        '                          <input\n' +
                        '                            type="text"\n' +
                        '                            class="form-control"\n' +
                        '                            placeholder="共享组队"\n' +
                        '                            readonly="readonly"\n' +
                        '                          />\n' +
                        '                        </div>\n' +
                        '                      </div>\n' +
                        '\n' +
                        '                      <div class="form-group">\n' +
                        '                        <label class="form-label">共享情况</label>\n' +
                        '                        <div class="input-group">\n' +
                        '                          <input\n' +
                        '                            type="text"\n' +
                        '                            class="form-control"\n' +
                        '                            placeholder="主课程"\n' +
                        '                            readonly="readonly"\n' +
                        '                          />\n' +
                        '                        </div>\n' +
                        '                      </div>\n' +
                        '\n' +
                        '                      <div class="form-footer">\n' +
                        '                        <button\n' +
                        '                          class="btn btn-primary btn-block"\n' +
                        '                          data-toggle="modal"\n' +
                        '                          data-target="#exampleModalCenter"\n' +
                        '                          onclick="deleteTeamShare()"\n' +
                        '                        >\n' +
                        '                          取消共享\n' +
                        '                        </button>\n' +
                        '                      </div>\n' +
                        '                    </form>\n' +
                        '                  </div>\n' +
                        '                </div>\n' +
                        '              </div>\n';
                });
                content.innerHTML=str;

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
//课程信息
function getAllCourse() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn:8080/course/allcourse",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            console.log(data);

            var content1=document.getElementById("content1");   //获取外围容器
            var content2=document.getElementById("content2");   //获取外围容器
            var str1="";
            var str2="";
            $.each(data, function(i, item) {
                str1 += '                                <tr>\n' +
                    '                                  <td>\n' +
                    '                                    <label\n' +
                    '                                            class="custom-control custom-checkbox"\n' +
                    '                                    >\n' +
                    '                                      <input\n' +
                    '                                              type="checkbox"\n' +
                    '                                              class="custom-control-input"\n' +
                    '                                              name="example-checkbox1"\n' +

                    '                                              value="option1"\n' +
                    '                                      />\n' +
                    '                                      <span\n' +
                    '                                              class="custom-control-label"\n' +
                    '                                      ></span>\n' +
                    '                                    </label>\n' +
                    '                                  </td>\n' +
                    '                                  <td>\n' +
                    '                                      <span class="text-muted"\n' +
                    '                                      >' + item.courseName + '</span\n' +
                    '                                      >\n' +
                    '                                  </td>\n' +
                    '                                  <td> <input\n' +
                    '                                          class="form-control"\n' +
                    '                                          name="example-text-input"\n' +
                    '                                          type="text"\n' +
                    '                                          id="1content'+item.id+'"\n' +

                    '                                  /></td>\n' +
                    '                                  <td> <input\n' +
                    '                                          class="form-control"\n' +
                    '                                          name="example-text-input"\n' +
                    '                                          type="text"\n' +
                    '                                  /></td>\n' +
                    '                                </tr>\n';

                str2+='<tr>\n' +
                    '                                  <td>\n' +
                    '                                    <label\n' +
                    '                                            class="custom-control custom-checkbox"\n' +
                    '                                    >\n' +
                    '                                      <input\n' +
                    '                                              type="checkbox"\n' +
                    '                                              class="custom-control-input"\n' +
                    '                                          id="2content'+item.id+'"\n' +
                    '                                              value="option1"\n' +
                    '                                      />\n' +
                    '                                      <span\n' +
                    '                                              class="custom-control-label"\n' +
                    '                                      ></span>\n' +
                    '                                    </label>\n' +
                    '                                  </td>\n' +
                    '                                  <td>\n' +
                    '                                      <span class="text-muted"\n' +
                    '                                      >'+item.courseName+'</span\n' +
                    '                                      >\n' +
                    '                                  </td>\n' +
                    '\n' +
                    '                                </tr>';

            });
            content1.innerHTML=str1;
            content2.innerHTML=str2;


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
//查看课程信息
function getCourseInfo() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn:8080/course/" + Cookies.get("course"),
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            console.log(data);

                // alert("获取成功");
                $("#courseName").val(data.course.courseName);
                $("#introduction").val(data.course.introduction);
                $("#round").val(data.course.roundId);
                $("#seminarSerial").val(data.course.seminarSerial);
                $("#reportPercentage").val(data.course.reportPercentage);
                $("#questionPercentage").val(data.course.questionPercentage);
                $("#presentationPercentage").val(data.course.presentationPercentage);
                $("#teamStartTime").val(data.course.teamStartTime);
                $("#teamEndTime").val(data.course.teamEndTime);
                let strategyList=data.teamStrategyList;
            var content1=document.getElementById("content1");   //获取外围容器
            var content2=document.getElementById("content2");   //获取外围容器

            var str1="";
            var str2="";

            $.each(strategyList, function(i, item1) {
               if(item1.strategyName=="TeamAndStrategy")
               {
                   let teamSg=item1.strategyList;
                   $.each(teamSg, function(i, item2) {

                       if(item2.strategyName=="MemberLimitStrategy")
                       {
                           let teamMemberSg=item2.strategyList;
                           $("#minMember").val(teamMemberSg[0].minMember);
                           $("#maxMember").val(teamMemberSg[0].maxMember);
                       }
                       if(item2.strategyName=="TeamOrStrategy")
                       {   str="";
                           let teamOrStrategy=item2.strategyList;
                           $.each(teamOrStrategy, function(i, item3) {
                               let item4=(item3.strategyList)[0];
                                       str1 += '                                <tr>\n' +
                                           '                                  <td>\n' +
                                           '                                    <label\n' +
                                           '                                            class="custom-control custom-checkbox"\n' +
                                           '                                    >\n' +
                                           '                                      <input\n' +
                                           '                                              type="checkbox"\n' +
                                           '                                              class="custom-control-input"\n' +
                                           '                                              name="example-checkbox1"\n' +
                                           '                                              checked=""\n' +

                                           '                                              value="option1"\n' +
                                           '                                      />\n' +
                                           '                                      <span\n' +
                                           '                                              class="custom-control-label"\n' +
                                           '                                      ></span>\n' +
                                           '                                    </label>\n' +
                                           '                                  </td>\n' +
                                           '                                  <td>\n' +
                                           '                                      <span class="text-muted"\n' +
                                           '                                      >' + ((item4).course).courseName + '</span\n' +
                                           '                                      >\n' +
                                           '                                  </td>\n' +
                                           '                                  <td> <input\n' +
                                           '                                          class="form-control"\n' +
                                           '                                          name="example-text-input"\n' +
                                           '                                          value="' + (item4).minMember + '"\n' +
                                           '                                          type="text"\n' +
                                           '                                  /></td>\n' +
                                           '                                  <td> <input\n' +
                                           '                                          class="form-control"\n' +
                                           '                                          name="example-text-input"\n' +
                                           '                                          value="' + (item4).maxMember + '"\n' +
                                           '                                          type="text"\n' +
                                           '                                  /></td>\n' +
                                           '                                </tr>\n';

                           });
                           content1.innerHTML=str1;

                       }
                   });
               }
               if(item1.strategyName=="ConflictCourseStrategy")
                {
                    let item2=item1.course;
                    str2+='<tr>\n' +
                        '                                  <td>\n' +
                        '                                    <label\n' +
                        '                                            class="custom-control custom-checkbox"\n' +
                        '                                    >\n' +
                        '                                      <input\n' +
                        '                                              type="checkbox"\n' +
                        '                                              class="custom-control-input"\n' +
                        '                                              name="example-checkbox1"\n' +
                        '                                              value="option1"\n' +
                        '                                              checked=""\n' +
                        '                                      />\n' +
                        '                                      <span\n' +
                        '                                              class="custom-control-label"\n' +
                        '                                      ></span>\n' +
                        '                                    </label>\n' +
                        '                                  </td>\n' +
                        '                                  <td>\n' +
                        '                                      <span class="text-muted"\n' +
                        '                                      >'+item2.courseName+'</span\n' +
                        '                                      >\n' +
                        '                                  </td>\n' +
                        '\n' +
                        '                                </tr>';
                }
            });
            content2.innerHTML=str2;

        },
        error:function(data){
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
            "http://xug98.cn:8080/score/seminar/" +
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
    let conflictdata="";

    var content1=document.getElementById("content1");   //获取外围容器
    var content2=document.getElementById("content2");   //获取外围容器
    $.each(content1.children, function(i, item) {

    });
    }
        var str1="";
    var str2="";
    let ata = {
        "courseName": $("#courseName").val(),
        "introduction": $("#introduction").val(),
        "presentationPercentage": $("#presentationPercentage").val(),
        "questionPercentage": $("#questionPercentage").val(),
        "reportPercentage": $("#reportPercentage").val(),
        "teamStartTime": convertTime($("#teamStartTime").val()),
        "teamEndTime": convertTime($("#teamEndTime").val()),
        "teamMaxMember": $("#teamMaxMember").val(),
        "teamMinMember": $("#teamMinMember").val(),
        "courseMemberLimitVOList": [
            {
                "courseId": 20,
                "maxMember": 5,
                "minMember": 1
            }
        ],
        "relation": 1,
        "conflictCourseLists": [
            [
                17,
                20
            ]
        ]
    };

    ;
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
        url: "http://xug98.cn:8080/seminar",
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
        url: "http://xug98.cn:8080/seminar/" + Cookies.get("seminar"),
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
            "http://xug98.cn:8080/seminar/" +
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
function getReportByClass() {

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
function getPptByAttendance() {

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
function getReportByAttendance() {

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
        url: "http://xug98.cn:8080/course/" + Cookies.get("course") + "/class",
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
        url: "http://xug98.cn:8080/course/" + courseId,
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
            "http://xug98.cn:8080/score/course/" + Cookies.get("course") + "/score",
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
        url: "http://xug98.cn:8080/score/round/" + Cookies.get("round") + "/score",
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
        url: "http://xug98.cn:8080/round",
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
            "http://xug98.cn:8080/course/" +
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
            "http://xug98.cn:8080/course/" +
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
        url: "http://xug98.cn/request/teamshare",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                alert("获取成功");
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
                 alert("获取成功");
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
                alert("获取成功");
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

function getQuestionList(attendanceId) {

    $.ajax({
        type: "get",
        url:
            "http://xug98.cn:8080/seminar/" +
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
            "http://xug98.cn:8080/attendance/" + Cookies.get("attendance") + "/score",
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
function getCurrentSeminar() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn:8080/seminar",
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


function getAttendanceScore(attendanceId) {

    $.ajax({
        type: "get",
        url:
            "http://xug98.cn:8080/attendance/" +
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
            "http://xug98.cn:8080/question/" +
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
            "http://xug98.cn:8080/attendance/" +
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
            "http://xug98.cn:8080/question/" +
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
            "http://xug98.cn:8080/seminar/" +
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
        url: "http://xug98.cn:8080/attendance/" + cid,
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
        url: "http://xug98.cn:8080/attendance/" + Cookies.get("attendance"),
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

function getRoundInfo() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn:8080/round/" + Cookies.get("round"),
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
        url: "http://xug98.cn:8080/class/" + Cookies.get("class"),
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
        url: "http://xug98.cn:8080/class/" + classId,
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


function initShareCreate() {
    getAllCourse();
}
