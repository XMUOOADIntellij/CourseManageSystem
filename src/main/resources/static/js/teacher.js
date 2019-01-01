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
    let ata = {
          password: $("#password").val()
      };
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
    let ata = $("#account").val();
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
    //修改密码
function editPassword() {
    let ata = {password: $("#password").val()};
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
    //修改邮箱
function editEmail() {
      let ata = {
          email: $("#email").val()
      };
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
function getCourseList() {
    $.ajax({
        type: "get",
        url: "/course",
        dataType: "json",
        async : false,

        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log("courselist");
                let content=document.getElementById("content");   //获取外围容器
                let str="";
                $.each(data, function(i, item) {
                    console.log(item);
                    let courseType="副";
                    if(item.teamMainCourse===null) courseType="主";
                    str +='            <div class="col-md-6 col-lg-4" >\n' +
                        '                <div class="card">\n' +
                        '                  <div class="card-header">\n' +
                        '                    <h3 class="card-title mr-4">'+item.courseName+'</h3>\n' +
                        '                    <span class="btn btn-outline-secondary py-0">'+courseType+'</span>\n' +
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
    //创建课程
function createCourse() {
    let conflict = {
        courseId: "2"
    };
    let conflictdata="[";

    let childCheckBoxes1 = $("tbody.check1 tr td label input[type='checkbox']");
    let childValue1 = $("tbody.check1 tr td input[type='text']");
    let values = "";
    let i;
    for (i = 0; i < childCheckBoxes1.length; i++) {
        if (childCheckBoxes1[i].checked == true) {
            if (conflictdata !== "[") conflictdata += ',';
            conflictdata += '{ "courseId":' + childCheckBoxes1[i].value + ',"maxMember":' + childValue1[2 * i].value + ',"minMember":' + childValue1[i * 2 + 1].value + '}';
        }

    }
    conflictdata += ']';
    console.log(conflictdata);

    let conflictclass="[";

    let childGroup = $("tbody.check2").each(function(){
        if (conflictclass !== "[") conflictclass += ',';

        let conflictInner="[";
        let childCheckBoxes2=$(this).find("tr td label input[type='checkbox']");
        let childValue2 = $(this).find("tr td input[type='text']");
        values = "";
        let j;
        for (j = 0; j < childCheckBoxes2.length; j++) {
            if (childCheckBoxes2[j].checked == true) {
                if (conflictInner !== "[") conflictInner += ',';
                conflictInner += childCheckBoxes2[j].value;
            }

        }
        conflictInner += ']';
        conflictclass+=conflictInner;

    });
    conflictclass += ']';

    console.log(conflictclass);

    let ata = {
        courseName: $("#courseName").val(),
        introduction: $("#introduction").val(),
        presentationPercentage: $("#presentationPercentage").val(),
        questionPercentage: $("#questionPercentage").val(),
        reportPercentage: $("#reportPercentage").val(),
        teamStartTime: convertTime($("#teamStartTime").val()),
        teamEndTime: convertTime($("#teamEndTime").val()),
        teamMaxMember: $("#maxMember").val(),
        teamMinMember: $("#minMember").val(),
        courseMemberLimitVOList:conflictdata,
        relation: 1,
        conflictCourseLists: conflictclass
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
            window.location.href = "./course-home.html";
        },
        error: function(data) {
            console.log(data);
            alert("fail");
        },
        statusCode: {
            201: function(data) {
                console.log(data);
                alert("success");
                window.location.href = "./course-home.html";
            },
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}
function addConflictsGroup(){
    let content3=document.getElementById("content3");   //获取外围容器
    let content2=document.getElementById("content2");   //获取外围容器

    content3.innerHTML+='<div class="col-md-6 col-lg-6">\n' +
        '                        <div class="form-group">\n' +
        '\n' +
        '                          <div class="card" style="max-height:12rem">\n' +
        '                            <div class="table-responsive">\n' +
        '                              <table\n' +
        '                                      class="table card-table table-vcenter text-nowrap"\n' +
        '                              >\n' +
        '                                <thead>\n' +
        '                                <tr>\n' +
        '                                  <th class="w-auto"></th>\n' +
        '                                  <th>课程名称</th>\n' +
        '                                </tr>\n' +
        '                                </thead>\n' +
        '                                <tbody class="check2">\n' + content2.innerHTML
        '                                </tbody>\n' +
        '                              </table>\n' +
        '                            </div>\n' +
        '                          </div>\n' +
        '                        </div>\n' +
        '\n' +
        '                      </div>';
}
function getAllCourse() {
    $.ajax({
        type: "get",
        url: "/course/allcourse",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            console.log(data);

            let content1=document.getElementById("content1");   //获取外围容器
            let content2=document.getElementById("content2");   //获取外围容器
            let str1="";
            let str2="";
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
                    '                                              value="'+item.id+'"\n' +
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
                    '                                              value="'+item.id+'"\n' +
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
function deleteCourse() {
    var result = confirm("确定删除学生?");
    if (result) {
        let courseId = Cookies.get("course");
        $.ajax({
            type: "delete",
            url: "/course/" + courseId,
            dataType: "json",
            contentType: "application/json;",
            error: function (data, textStatus, xhr) {
                console.log(cid);
                alert("wrong");
            },
            success: function (data, textStatus, xhr) {
                alert("成功");
                console.log(data);
            },

            statusCode: {
                400: function () {
                    alert("错误的ID格式");
                },
                403: function () {
                    alert("用户权限不足");
                },
                404: function () {
                    alert("未找到课程");
                }
            }
        });

        window.location.href("./course-home.html");
    }

}


//班级信息界面
function getClassItems() {
    $.ajax({
        type: "get",
        url: "/course/" + Cookies.get("course"),
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log("classlist");
                let content=document.getElementById("content");   //获取外围容器
                let str="";

                $.each(data, function(i, item) {
                    console.log(item);
                    str+='              <div class="col-md-6 col-xl-4" >\n' +
                        '                <div class="card">\n' +
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
                        '                        <input class="form-control"  name="example-text-input" placeholder="'+item.klassTime+'" type="text" readonly="">\n' +
                        '                      </div>\n' +
                        '                      <div class="form-group">\n' +
                        '                        <label class="form-label">讨论课地点</label>\n' +
                        '                        <input class="form-control"  name="example-text-input" placeholder="'+item.klassLocation+'" type="text" readonly="">\n' +
                        '                      </div>\n' +
                        '                      <div class="form-group">\n' +
                        '                        <div class="form-label">班级学生名单</div>\n' +
                        '                        <div class="custom-file">\n' +
                        '                          <input\n' +
                        '                            type="file"\n' +
                        '                            id="file'+item.id+'"\n' +
                        '                            class="custom-file-input"\n' +
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
                        '                </div>\n' +
                        '              </div>\n';

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
    //删除班级
function deleteClass(classId) {
    var result = confirm("确定删除学生?");
    if (result) {
        $.ajax({
            type: "delete",
            url: "/class/" + classId,
            dataType: "json",
            contentType: "application/json;",
            error: function (data, textStatus, xhr) {
                console.log(cid);
                alert("wrong");
            },
            success: function (data, textStatus, xhr) {
                alert("成功");
                if (xhr.status === 204) {
                    alert("成功");
                    console.log(data);
                }
            },
            statusCode: {
                400: function () {
                    alert("错误的ID格式");
                },
                403: function () {
                    alert("用户权限不足");
                },
                404: function () {
                    alert("未找到课程");
                },
                200: function () {
                    alert("成功");
                }
            }
        });
        window.location.reload();
    }
}
    //创建班级 -为跳转
function createClass() {
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
            window.location.href="./course-class.html";
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
    //修改班级
function updateClass(classId) {
    var formData = new FormData();
    var fileField = document.getElementById("file"+classId);
    formData.append( 'file', fileField.files[0] );
    $.ajax({
        url: "/class/" + Cookies.get("class"),
        data: formData,
        processData: false,
        contentType: false,
        type: 'POST',
        success: function(data){
            alert(data);
        },
        error: function(data){
            console.log(data);
            alert("上传失败");
        }
    });
}

//轮次设置页面
function getRoundList() {
    let courseId=Cookies.get("course");
    console.log(courseId);
    $.ajax({
        type: "get",
        url: "/course/" + Cookies.get("course") + "/round",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                // console.log("roundlist");
                let content=document.getElementById("content");   //获取外围容器
                let str="";
                $.each(data, function(i, item) {
                    console.log(item);
                    str +='            <div class="col-md-6 col-lg-4" >\n' +
                        '                <div class="card">\n' +
                        '                  <div class="card-header">\n' +
                        '                    <h3 class="card-title mr-4">第'+item.roundSerial+'轮</h3>\n' +
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
    console.log(roundId);
    $.ajax({
        type: "get",
        url: "/round/" + roundId + "/seminar",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
                // alert("获取成功");
                // console.log("seminarList");
                let content=document.getElementById("round"+roundId);   //获取外围容器
                let str="";
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
                    getClassList();
                });
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
function getClassList() {
    $.ajax({
        type: "get",
        url: "/course/" + Cookies.get("course") +"/class",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                // console.log("classlist");
                let content=document.getElementById("seminar-"+seminarId);   //获取外围容器
                let str="";
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
    //创建轮次
function createRound() {
    let ata = {
        courseId: Cookies.get("course")
    };
    console.log(ata);
    alert("input");
    $.ajax({
        type: "post",
        url: "/round?courseId="+Cookies.get("course"),
        dataType: "json",
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./course-round-setting.html";
        },


        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            },
            201: function() {
                alert("success");
                window.location.href = "./course-round-create.html";
            }
        }
    });
}
    //创建讨论课设置
function createRoundForScore() {
    let ata = {
        presentationScoreMethod: $("#present-grade").val(),
        reportScoreMethod: $("#report-grade").val(),
        questionScoreMethod: $("#ask-grade").val()
    };
    console.log(ata);
    $.ajax({
        type: "put",
        // url: "/round/" + Cookies.get("round"),
        url: "/round/26",

        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function() {
            console.log("score success");
            // window.location.href = "./course-round.html";
        },
        error:function(data){
            conosle.log("score error");
            console.log(data);
        },

        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            },
            201: function() {
                console.log("score success");
                // window.location.href = "./course-round.html";
            }
        }
    });
}
    //!!!
function createRoundForClass() {
    let ata = [
        {
            "klass": {
                "id": 29
            },
            "round": {
                "id": 26
            },
            "enrollNumber": 2
        }
    ];
    console.log(ata);
    $.ajax({
        type: "post",
        url: "/round/klassround",
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function() {
            console.log("class success");
            // window.location.href = "./course-round.html";
        },
        error:function(data){
            console.log("class error");
            console.log(data);
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            },
            201: function() {
                console.log("class success");
                // window.location.href = "./course-round.html";
            }
        }
    });
}
//修改讨论课设置
function updateRoundForScore() {
    let ata = {
        presentationScoreMethod: $("#present-grade").val(),
        reportScoreMethod: $("#report-grade").val(),
        questionScoreMethod: $("#ask-grade").val()
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
function getSeminarScoreByClass() {
    console.log( Cookies.get("seminar"));
    console.log( Cookies.get("class"));
    $.ajax({
        type: "get",
        url:
            "/seminar/" +
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
                let content=document.getElementById("content");   //获取外围容器
                let str="";
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
    let mySeminar=Cookies.get("seminar");
    let myClass=Cookies.get("class");
    alert(mySeminar);
    alert(myClass);

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
function getAttendanceItemsByClass() {
    let mySeminar=Cookies.get("seminar");
    let myClass=Cookies.get("class");
    alert(mySeminar);
    alert(myClass);

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
            var content=document.getElementById("content");   //获取外围容器
            var str="";
            $.each(data, function(i, item) {
                console.log(item);
                str +='                        <tr>\n' +
                    '                          <td><span class="text-muted">'+item.classSerial+'-'+item.teamSerial+'</span></td>\n' +
                    '                          <td><a href="." class="text-inherit">'+item.pptName+'</a></td>\n' +
                    '                          <td>\n' +
                    '                            <a\n' +
                    '                              class="icon"\n' +
                    '                              href="javascript:void(0)"\n' +
                    '                              onclick="getPptByAttendance('+item.id+')"\n' +
                    '                            >\n' +
                    '                              <i class="fe fe-download"></i>\n' +
                    '                            </a>\n' +
                    '                          </td>\n' +
                    '                          <td><a href="." class="text-inherit">'+item.reportName+'</a></td>\n' +
                    '                          <td id=attendance"'+item.id+'">\n' +
                    '                            <span class="status-icon bg-success"></span> 5.0\n' +
                    '                          </td>\n' +
                    '                          <td>\n' +
                    '                            <a class="icon" href="javascript:void(0)">\n' +
                    '                              <i class="fe fe-edit"></i>\n' +
                    '                            </a>\n' +
                    '                          </td>\n' +
                    '                          <td>\n' +
                    '                            <a\n' +
                    '                              class="icon"\n' +
                    '                              href="javascript:void(0)"\n' +
                    '                              onclick="getReportByAttendance('+item.id+')"\n' +
                    '                            >\n' +
                    '                              <i class="fe fe-download"></i>\n' +
                    '                            </a>\n' +
                    '                          </td>\n' +
                    '                        </tr>\n';
                getAttendanceItemReportScore(item.id);
            });
            content.innerHTML=str;
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
function getAttendanceItemReportScore(attendanceId) {
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
                var content=document.getElementById("attendance"+attendanceId);   //获取外围容器
                var str='<span class="status-icon bg-success"></span>'+data.reportScore;
                content.innerHTML=str;
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
function getPptByAttendance(attendanceId) {
    $.ajax({
        type: "get",
        url:
            "/attendance/" + attendanceId + "/ppt",
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
function getReportByAttendance(attendanceId) {

    $.ajax({
        type: "get",
        url:
            "/attendance/" +
            attendanceId +
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
    //修改讨论课详情设置
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


    //修改讨论课详情设置
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
    });}
    //新建讨论课 seminar_Id cannot be null
function createSeminar() {
    let ata = {
        // roundId: $('#select-round-id').val(),
        roundId: "26",
        seminarName: $("#name").val(),
        introduction: $("#introduction").val(),
        maxTeam: $("#select-max-team").val(),
        visible: true,
        seminarSerial: $("#select-seminar-serial").val(),
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
            window.location.href = "./course-seminar.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}



//学生成绩界面
function getRoundListForScore() {
    console.log(Cookies.get("course"));
    $.ajax({
        type: "get",
        url: "/course/" + Cookies.get("course") + "/round",
        dataType: "json",
        async : false,
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                // console.log("roundlist");
                let content=document.getElementById("content");   //获取外围容器
                let str="";
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
            "/course/" + Cookies.get("course") + "/round/"+ roundId + "/score",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                 // alert("获取成功");
                // console.log("teamscore");
                let content=document.getElementById("round"+roundId);   //获取外围容器
                let str="";
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
            "/round/" + roundId + "/score?teamId="+ teamId,
        dataType: "json",
        async : false,
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                // console.log("teamscore");
                // console.log(roundId+'T'+teamId);

                let content=document.getElementById(roundId+'T'+teamId);   //获取外围容器
                let str="";
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

//课程组队
function getTeam() {
    console.log(Cookies.get("course"));
    $.ajax({
        type: "get",
        async : false,
        url: "/course/" + Cookies.get("course") + "/team",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log("roundlist");
                let content=document.getElementById("content");

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
            },
            403: function() {
                console.log("roundlist");
                let content=document.getElementById("content");

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
        }
    });
}

//课程共享
function getTeamShareList() {
    $.ajax({
        type: "get",
        url: "/course/" + Cookies.get("course") + "/teamshare",
        // url: "../../static/json/team-share.json",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                alert("获取team成功");
                console.log(data);

                let content=document.getElementById("content");   //获取外围容器
                let str="";
                $.each(data, function(i, item) {
                    let strStatus='';
                    let strTeacher='';
                    let strCourse='';
                    let myName=Cookies.get("name");
                    // console.log(item);
                    let itemMain=(item).mainCourse;
                    let itemSub=(item).subCourse;
                    if  ((itemMain.teacher).teacherName==myName)
                    {
                        strTeacher=(itemSub.teacher).teacherName;
                        strCourse=(itemSub).courseName;
                        strStatus="主课程";
                    }
                    else
                    {
                        strTeacher=(itemMain.teacher).teacherName;
                        strCourse=(itemMain).courseName;
                        strStatus="副课程";
                    }
                        str +='              <div class="col-md-6 col-xl-4">\n' +
                            '                <div class="card">\n' +
                            '                  <div class="card-header">\n' +
                            '                    <h3 class="card-title mr-4">'+strCourse+'</h3>\n' +
                            '                    <span class="btn btn-outline-secondary py-0">'+strTeacher+'</span>\n' +
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
                            '                            placeholder="共享分组"\n' +
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
                            '                            placeholder="'+strStatus+'"\n' +
                            '                            readonly="readonly"\n' +
                            '                          />\n' +
                            '                        </div>\n' +
                            '                      </div>\n' +
                            '\n' +
                            '                      <div class="form-footer">\n' +
                            '                        <button\n' +
                            '                          class="btn btn-primary btn-block"\n' +
                            '                          onclick="deleteTeamShare('+item.id+')"\n' +
                            '                        >\n' +
                            '                          取消共享\n' +
                            '                        </button>\n' +
                            '                      </div>\n' +
                            '                    </form>\n' +
                            '                  </div>\n' +
                            '                </div>\n' +
                            '              </div>\n';

                });
                content.innerHTML+=str;
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
        // url: "../../static/json/seminar-share.json",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                alert("获取seminar成功");
                let content=document.getElementById("content");   //获取外围容器
                let str="";
                console.log(data);

                $.each(data, function(i, item) {
                    let strStatus='';
                    let strTeacher='';
                    let strCourse='';
                    let myName=Cookies.get("name");
                    // console.log(item);
                    let itemMain=(item).mainCourse;
                    let itemSub=(item).subCourse;
                    if  ((itemMain.teacher).teacherName==myName)
                    {
                        strTeacher=(itemSub.teacher).teacherName;
                        strCourse=(itemSub).courseName;
                        strStatus="主课程";
                    }
                    else
                    {
                        strTeacher=(itemMain.teacher).teacherName;
                        strCourse=(itemMain).courseName;
                        strStatus="副课程";
                    }
                    str +='              <div class="col-md-6 col-xl-4">\n' +
                        '                <div class="card">\n' +
                        '                  <div class="card-header">\n' +
                        '                    <h3 class="card-title mr-4">'+strCourse+'</h3>\n' +
                        '                    <span class="btn btn-outline-secondary py-0">'+strTeacher+'</span>\n' +
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
                        '                            placeholder="共享讨论课"\n' +
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
                        '                            placeholder="'+strStatus+'"\n' +
                        '                            readonly="readonly"\n' +
                        '                          />\n' +
                        '                        </div>\n' +
                        '                      </div>\n' +
                        '\n' +
                        '                      <div class="form-footer">\n' +
                        '                        <button\n' +
                        '                          class="btn btn-primary btn-block"\n' +
                        '                          onclick="deleteSeminarShare('+item.id+')"\n' +
                        '                        >\n' +
                        '                          取消共享\n' +
                        '                        </button>\n' +
                        '                      </div>\n' +
                        '                    </form>\n' +
                        '                  </div>\n' +
                        '                </div>\n' +
                        '              </div>\n';

                });
                content.innerHTML+=str;
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
    //删除共享
function deleteTeamShare(shareId) {
    let result = confirm("确定取消共享?");
    if (result) {
        $.ajax({
            type: "delete",
            url: "/course/teamshare/" + shareId,
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
        window.location.reload();
    }


}
function deleteSeminarShare(shareId) {
    let result = confirm("确定取消共享?");
    if (result) {
        $.ajax({
            type: "delete",
            url: "/course/seminarshare/" + shareId,
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
        window.location.reload();    }


}
    //新建共享
function createShare()
{
    let myPath="";
    let myType=$("#shareType").val();
    if(myType==1) myPath="seminarsharerequest";
    if(myType==2) myPath="teamsharerequest";

    let conflictclass="";

    let childGroup = $("tbody.check2").each(function(){
        let conflictInner="[";
        let childCheckBoxes2=$(this).find("tr td label input[type='checkbox']");
        let childValue2 = $(this).find("tr td input[type='text']");
        values = "";
        let j;
        for (j = 0; j < childCheckBoxes2.length; j++) {
            if (childCheckBoxes2[j].checked == true) {
                if (conflictInner !== "[") conflictInner += ',';
                conflictInner += childCheckBoxes2[j].value;
            }
        }
        conflictInner += ']';
        conflictclass+=conflictInner;

    });
    console.log(conflictclass);
    $.ajax({
        type: "post",
        url: "/course/" + Cookies.get("course") + "/"+myPath,
        dataType: "json",
        data: JSON.stringify(conflictclass),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
            window.location.href = "./course-seminar.html";
        },
        statusCode: {
            400: function() {
                $("#password").val("");
                alert("用户名或密码错误！");
            }
        }
    });
}
function getAllCourseForShare() {
    $.ajax({
        type: "get",
        url: "/course/allcourse",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            console.log(data);

            let content2=document.getElementById("content2");   //获取外围容器
            let str2="";
            $.each(data, function(i, item) {
                str2+='<tr>\n' +
                    '                                  <td>\n' +
                    '                                    <label\n' +
                    '                                            class="custom-control custom-checkbox"\n' +
                    '                                    >\n' +
                    '                                      <input\n' +
                    '                                              type="checkbox"\n' +
                    '                                              class="custom-control-input"\n' +
                    '                                          id="2content'+item.id+'"\n' +
                    '                                              value="'+item.id+'"\n' +
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
                    '                                  <td>\n' +
                    '                                      <span class="text-muted"\n' +
                    '                                      >'+(item.teacher).teacherName+'</span\n' +
                    '                                      >\n' +
                    '                                  </td>\n' +
                    '\n' +
                    '                                </tr>';

            });
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


//课程信息
function getCourseInfo() {
    $.ajax({
        type: "get",
        url: "/course/" + Cookies.get("course"),
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            Fastjson.format(data);
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
            let content1=document.getElementById("content1");   //获取外围容器
            let content3=document.getElementById("content3");   //获取外围容器
            content3.innerHTML='';

            let str1="";

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
                    let strInner="";
                    let str2="";
                    let courseSg=item1.strategyList;
                    $.each(courseSg, function(i, item2) {

                        let itemCourse=item2.course;
                        console.log(itemCourse);
                            let itemTeacher=itemCourse.teacher;
                            console.log(itemTeacher);

                            strInner+='                                <tr>\n' +
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
                                '                                      >'+itemCourse.courseName+'</span\n' +
                                '                                      >\n' +
                                '                                  </td>\n' +
                                '                                  <td>\n' +
                                '                                      <span class="text-muted"\n' +
                                '                                      >'+itemTeacher.teacherName+'</span\n' +
                                '                                      >\n' +
                                '                                  </td>\n' +
                                '\n' +
                                '                                </tr>\n';
                            str2='                      <div class="col-md-6 col-lg-6">\n' +
                                '                        <div class="form-group">\n' +
                                '\n' +
                                '                          <div class="card" style="max-height:12rem">\n' +
                                '                            <div class="table-responsive">\n' +
                                '                              <table\n' +
                                '                                      class="table card-table table-vcenter text-nowrap"\n' +
                                '                              >\n' +
                                '                                <thead>\n' +
                                '                                <tr>\n' +
                                '                                  <th class="w-auto"></th>\n' +
                                '                                  <th>课程名称</th>\n' +
                                '                                </tr>\n' +
                                '                                </thead>\n' +
                                '                                <tbody class="check2">' +strInner+
                                '                                </tbody>\n' +
                                '                              </table>\n' +
                                '                            </div>\n' +
                                '                          </div>\n' +
                                '                        </div>\n' +
                                '\n' +
                                '                      </div>\n';



                    });
                    content3.innerHTML+=str2;
                }
            });

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

//任务列表
function getTeamValidTask() {
    $.ajax({
        type: "get",
        url:  "/request/teamvaild",
        // url: "../../static/json/team-valid.json",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log("classlist");
                let content=document.getElementById("content");   //获取外围容器
                let str1="";
                let str2="";

                $.each(data, function(i, item) {
                    let strUp='<i class="fe fe-thumbs-up mr-1"></i>';
                    let strDown='<i class="fe fe-thumbs-down mr-1"></i>';
                    console.log(item);
                    let itemCourse=(item.team).course;
                    let itemKlass=(item.team).klass;
                    let itemStu=(item.team).leader;
                    if(item.status=='1') {
                        strUp='<i class="fe fe-thumbs-up mr-1" style="color:#8BC34A;"></i>';
                        str2 +=' <div class="col-lg-4">\n' +
                            '                <div class="card">\n' +
                            '                  <div class="card-body d-flex flex-column">\n' +
                            '                    <h4><a href="#">申请'+itemCourse.courseName+'课程 组队</a></h4>\n' +
                            '                    <div class="text-muted">\n' +
                            '                      '+itemCourse.courseName+'课程'+itemKlass.klassSerial+'班的'+itemStu.studentName+'同学向您申请组队\n' +
                            '                    </div>\n' +
                            '<div class="text-muted">原因：'+item.reason+'</div>'+
                            '                    <div class="d-flex align-items-center pt-5 mt-auto">\n' +
                            '                      <div class="avatar avatar-md mr-3">Lxm</div>\n' +
                            '                      <div><a class="text-default">'+itemStu.studentName+' </a></div>\n' +
                            '\n' +
                            '                      <div class="ml-auto text-muted">\n' +
                            '                        <a\n' +
                            '                          href="javascript:void(0)"\n' +
                            '                          class="icon ml-3"\n' +
                            '                          onclick="updateTeamValid(\'reject\','+item.id+')"\n' +
                            '                          >' +strDown+
                            '</a>\n' +
                            '                        <a\n' +
                            '                          href="javascript:void(0)"\n' +
                            '                          onclick="updateTeamValid(\'accept\','+item.id+',this)"\n' +
                            '                          class="icon  ml-3"\n' +
                            '                          >' +strUp+
                            '</a>\n' +
                            '                      </div>\n' +
                            '                    </div>\n' +
                            '                  </div>\n' +
                            '                </div>\n' +
                            '              </div>';
                    }
                    if(item.status=='2') {
                        strDown='<i class="fe fe-thumbs-down mr-1" style="color:#e57373;"></i>';
                        str2 +=' <div class="col-lg-4">\n' +
                            '                <div class="card">\n' +
                            '                  <div class="card-body d-flex flex-column">\n' +
                            '                    <h4><a href="#">申请'+itemCourse.courseName+'课程 组队</a></h4>\n' +
                            '                    <div class="text-muted">\n' +
                            '                      '+itemCourse.courseName+'课程'+itemKlass.klassSerial+'班的'+itemStu.studentName+'同学向您申请组队\n' +
                            '                    </div>\n' +
                            '<div class="text-muted">原因：'+item.reason+'</div>'+
                            '                    <div class="d-flex align-items-center pt-5 mt-auto">\n' +
                            '                      <div class="avatar avatar-md mr-3">Lxm</div>\n' +
                            '                      <div><a class="text-default">'+itemStu.studentName+' </a></div>\n' +
                            '\n' +
                            '                      <div class="ml-auto text-muted">\n' +
                            '                        <a\n' +
                            '                          href="javascript:void(0)"\n' +
                            '                          class="icon ml-3"\n' +
                            '                          onclick="updateTeamValid(\'reject\','+item.id+')"\n' +
                            '                          >' +strDown+
                            '</a>\n' +
                            '                        <a\n' +
                            '                          href="javascript:void(0)"\n' +
                            '                          onclick="updateTeamValid(\'accept\','+item.id+',this)"\n' +
                            '                          class="icon  ml-3"\n' +
                            '                          >' +strUp+
                            '</a>\n' +
                            '                      </div>\n' +
                            '                    </div>\n' +
                            '                  </div>\n' +
                            '                </div>\n' +
                            '              </div>';
                    }
                    if(item.status=='0') {
                        str1 +=' <div class="col-lg-4">\n' +
                            '                <div class="card">\n' +
                            '                  <div class="card-body d-flex flex-column">\n' +
                            '                    <h4><a href="#">申请'+itemCourse.courseName+'课程 组队</a></h4>\n' +
                            '                    <div class="text-muted">\n' +
                            '                      '+itemCourse.courseName+'课程'+itemKlass.klassSerial+'班的'+itemStu.studentName+'同学向您申请组队\n' +
                            '                    </div>\n' +
                            '<div class="text-muted">原因：'+item.reason+'</div>'+
                            '                    <div class="d-flex align-items-center pt-5 mt-auto">\n' +
                            '                      <div class="avatar avatar-md mr-3">Lxm</div>\n' +
                            '                      <div><a class="text-default">'+itemStu.studentName+' </a></div>\n' +
                            '\n' +
                            '                      <div class="ml-auto text-muted">\n' +
                            '                        <a\n' +
                            '                          href="javascript:void(0)"\n' +
                            '                          class="icon ml-3"\n' +
                            '                          onclick="updateTeamValid(\'reject\','+item.id+')"\n' +
                            '                          >' +strDown+
                            '</a>\n' +
                            '                        <a\n' +
                            '                          href="javascript:void(0)"\n' +
                            '                          onclick="updateTeamValid(\'accept\','+item.id+',this)"\n' +
                            '                          class="icon  ml-3"\n' +
                            '                          >' +strUp+
                            '</a>\n' +
                            '                      </div>\n' +
                            '                    </div>\n' +
                            '                  </div>\n' +
                            '                </div>\n' +
                            '              </div>';
                    }
                });
                content.innerHTML=str1+str2;
            }
        },
        statusCode: {
            400: function() {
                alert("错误的ID格式");
            },
            404: function() {
                alert("未找到课程");
            },
        }
    });
}
function updateTeamValid(handletype,id,e) {
    let ata = {
        handletype: handletype
    };
    console.log(ata);
    let obj=$(e);
    alert(obj);
    $.ajax({
        type: "put",
        url: ":8080/request/teamvalid/" +id,
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

    window.location.reload();
}
function getTeamShareTask() {
    $.ajax({
        type: "get",
        url: "/" ,
        // url: "data.json",
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
                    str +=' <div class="col-lg-4">\n' +
                        '                <div class="card">\n' +
                        '                  <div class="card-body d-flex flex-column">\n' +
                        '                    <h4><a href="#">共享'+item.courseName+'课程 组队</a></h4>\n' +
                        '                    <div class="text-muted">\n' +
                        '                      '+item.teacherName+'老师，向您发起共享'+item.courseName+'课程组队的申请。\n' +
                        '                    </div>\n' +
                        '                    <div class="d-flex align-items-center pt-5 mt-auto">\n' +
                        '                      <div class="avatar avatar-md mr-3">Lxm</div>\n' +
                        '                      <div><a class="text-default">'+item.teacherName+'</a></div>\n' +
                        '\n' +
                        '                      <div class="ml-auto text-muted">\n' +
                        '                        <a\n' +
                        '                          href="javascript:void(0)"\n' +
                        '                          class="icon d-none d-md-inline-block ml-3"\n' +
                        '                          onclick="updateTeamShare("reject",'+item.id+')"\n' +
                        '                          ><i class="fe fe-thumbs-down mr-1"></i\n' +
                        '                        ></a>\n' +
                        '                        <a\n' +
                        '                          href="javascript:void(0)"\n' +
                        '                          onclick="updateTeamShare("accept",'+item.id+')"\n' +
                        '                          class="icon d-none d-md-inline-block ml-3"\n' +
                        '                          ><i class="fe fe-thumbs-up mr-1"></i\n' +
                        '                        ></a>\n' +
                        '                      </div>\n' +
                        '                    </div>\n' +
                        '                  </div>\n' +
                        '                </div>\n' +
                        '              </div>';
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
function getSeminarShareTask() {
    $.ajax({
        type: "get",
        url: "/request/teamvaild" ,
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
                    str +=' <div class="col-lg-4">\n' +
                        '                <div class="card">\n' +
                        '                  <div class="card-body d-flex flex-column">\n' +
                        '                    <h4><a href="#">申请'+item.courseName+'课程 讨论课</a></h4>\n' +
                        '                    <div class="text-muted">\n' +
                        '                      '+item.teacherName+'老师，向您发起共享'+item.teacherName+'课程讨论课的申请。\n' +
                        '                    </div>\n' +
                        '                    <div class="d-flex align-items-center pt-5 mt-auto">\n' +
                        '                      <div class="avatar avatar-md mr-3">Lxm</div>\n' +
                        '                      <div><a class="text-default">'+item.teacherName+' 老师</a></div>\n' +
                        '\n' +
                        '                      <div class="ml-auto text-muted">\n' +
                        '                        <a\n' +
                        '                          href="javascript:void(0)"\n' +
                        '                          class="icon d-none d-md-inline-block ml-3"\n' +
                        '                          onclick="updateSeminarShare("reject",'+item.id+')"\n' +
                        '                          ><i class="fe fe-thumbs-down mr-1"></i\n' +
                        '                        ></a>\n' +
                        '                        <a\n' +
                        '                          href="javascript:void(0)"\n' +
                        '                          onclick="updateSeminarShare("accept",'+item.id+')"\n' +
                        '                          class="icon d-none d-md-inline-block ml-3"\n' +
                        '                          ><i class="fe fe-thumbs-up mr-1"></i\n' +
                        '                        ></a>\n' +
                        '                      </div>\n' +
                        '                    </div>\n' +
                        '                  </div>\n' +
                        '                </div>\n' +
                        '              </div>';
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

//进行中
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
                let quesContent=document.getElementById("ques-content");   //获取外围容器
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
                    let tabContent=document.getElementById("nav-content");   //获取外围容器
                    let strTab="";
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
                getAttendanceByClass();
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







