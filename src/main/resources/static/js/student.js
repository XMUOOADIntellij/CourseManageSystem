//页面跳转 Cookie缓存
function jumpFromCourseHome(courseId){
  Cookies.set("course",courseId);
  getClassByCourse(courseId);
}
function jumpFromRound(roundId){
  Cookies.set("round",roundId);
}
function jumpFromRoundClass(seminarId,classId){
  Cookies.set("seminar",seminarId);
  Cookies.set("class",classId);
}

//学生激活
function activeStudent() {
  // let ata = {account:$("#account").val(),password:$("#password").val()}
      let ata = {
        password: $("#password").val(),
          email:$("#email").val()
    };
  console.log(ata);
  alert("input");
  $.ajax({
    type: "put",
    url: "http://xug98.cn/student/active",
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

//主页信息
function initHome(){
  $("#name").html(Cookies.get("name"));
  $("#account").html(Cookies.get("account"));
}

//讨论课主页
function getCourseListForSeminar() {
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
                let content=document.getElementById("content");   //获取外围容器
                let str="";
                $.each(data, function(i, item) {
                    console.log(item);
                    let courseType="副";
                    if(item.teamMainCourse===null) courseType="主";
                    str +='<div class="col-sm-6 col-lg-3">\n' +
                        '                <div class="card">\n' +
                        '                  <div class="card-body text-center">\n' +
                        '                    <div class="card-category">'+item.courseName+'</div>\n' +
                        '                    <ul class="list-unstyled leading-loose">\n' +
                        '                      <li><strong>主课程</strong></li>\n' +
                        '                    </ul>\n' +
                        '                    <div class="text-center mt-6">\n' +
                        '                      <a\n' +
                        '                        class="btn btn-secondary btn-block"\n' +
                        '                        onclick="jumpFromCourseHome('+item.id+')"\n' +
                        '                        href="./seminar-round.html"\n' +
                        '                        >查看详情</a\n' +
                        '                      >\n' +
                        '                    </div>\n' +
                        '                  </div>\n' +
                        '                </div>\n' +
                        '              </div>';

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

//账户设置页面
function getUserInfo() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn/user/information",
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
        url: "http://xug98.cn/user/password",
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
    /*
      let ata = {
          email: $("#email").val()
      };
  */
    let ata = "333@qq.com";
    console.log(ata);
    $.ajax({
        type: "put",
        url: "http://xug98.cn/user/email",
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

//课程组队
function getTeam() {
    console.log(Cookies.get("course"));
    $.ajax({
        type: "get",
        async : false,
        // url: "../../static/json/team-list.json",
        url: "http://xug98.cn/course/" + Cookies.get("course") + "/team",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log("team success");
                let content=document.getElementById("content");
                let myTeam=Cookies.get("team");
                let str="";
                let status="valid";
                //获取外围容器
                $.each(data, function(i, team) {
                    console.log(team);
                    if(team.id==myTeam) return;
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
                content.innerHTML+=str;

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
        async : false,
        // url: "../../static/json/my-team.json",
        url: "http://xug98.cn/course/" + Cookies.get("course") + "/myTeam",
        dataType: "json",
        contentType: "application/json;",
        success: function(team, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                let content=document.getElementById("content");
                let myTeam=Cookies.set("team",team.id);
                console.log("my success");

                let str="";
                let status="valid";
                //获取外围容器
                    console.log(team);
                    if(team.status==0) status="invalid";
                    let innerStr="";
                    innerStr += '\n' +
                        '                          <tr>\n' +
                        '                            <td class="text-nowrap">' + team.leader.studentName + '</td>\n' +
                        '                            <td>' + team.leader.account + '</td>\n' +
                        '                            <td>组长</td>\n' +
                        '                          </tr>';
                    let myId=Cookies.get("id");
                    $.each(team.members, function (i, item) {
                        // console.log(item);
                        innerStr += '\n' +
                            '                          <tr>\n' +
                            '                            <td class="text-nowrap">' + item.studentName + '</td>\n' +
                            '                            <td>' + item.account + '</td>\n' +
                            '                            <td>组员</td>\n' +
                            '                          </tr>';
                    });
                if(team.leader.id==myId)
                {
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
                        '                <a\n' +
                        '                            href="course-team-setting.html"\n' +
                        '                            class="card-options-delete"\n' +
                        '                            ><i class="fe fe-settings"></i\n' +
                        '                          ></a>\n' +
                        '                          <a\n' +
                        '                            href="course-team-setting.html"\n' +
                        '                            class="card-options-delete"\n' +
                        '                            onclick="deleteTeam('+team.id+')"\n' +
                        '                            ><i class="fe fe-trash"></i\n' +
                        '                          ></a>\n' +
                        '                          <a\n' +
                        '                            class="card-options-collapse"\n' +
                        '                            data-toggle="card-collapse"\n' +
                        '                            href="#"\n' +
                        '                            ><i class="fe fe-chevron-up mr-1"></i\n' +
                        '                          ></a>'+
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
                }

               else
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
                        '                          <a\n' +
                        '                            class="card-options-collapse"\n' +
                        '                            data-toggle="card-collapse"\n' +
                        '                            href="#"\n' +
                        '                            ><i class="fe fe-chevron-up mr-1"></i\n' +
                        '                          ></a>'+
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
                content.innerHTML+=str;

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
function getMyTeamForSetting() {
    $.ajax({
        type: "get",
        async : false,
        // url: "../../static/json/my-team.json",
        url: "http://xug98.cn/course/" + Cookies.get("course") + "/myTeam",

        dataType: "json",
        contentType: "application/json;",
        success: function(team, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                let content=document.getElementById("content");
                console.log("my success");

                let str="";
                let status="valid";
                //获取外围容器
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
                        '  <td>\n' +
                        '                          <a\n' +
                        '                            href="course-team-setting.html"\n' +
                        '                            class="card-options-delete"\n' +
                        '                            onclick="deleteTeamMember('+team.id+','+item.id+')"\n' +
                        '                            ><i class="fe fe-trash"></i\n' +
                        '                          ></a>\n' +
                        '                                      </td>'+
                        '                          </tr>'+
                    '';
                });

                content.innerHTML+=innerStr;

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
function getNoTeamForSetting() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn/course/" + Cookies.get("course") + "/noTeam",
        dataType: "json",
        async : false,

        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                var content=document.getElementById("content-no");   //获取外围容器
                var str="";
                $.each(data, function(i, item) {
                    console.log(item);
                    str +='                                  <tr>\n' +
                        '                                    <td>\n' +
                        '                                      <label\n' +
                        '                                        class="custom-control custom-checkbox"\n' +
                        '                                      >\n' +
                        '                                        <input\n' +
                        '                                          type="checkbox"\n' +
                        '                                          class="custom-control-input"\n' +
                        '                                          name="example-checkbox1"\n' +
                        '                                              value="'+item.id+'"\n' +
                        '                                        />\n' +
                        '                                        <span\n' +
                        '                                          class="custom-control-label"\n' +
                        '                                        ></span>\n' +
                        '                                      </label>\n' +
                        '                                    </td>\n' +

                        '                                    <td class="text-nowrap">'+item.name+'</td>\n' +
                        '                                    <td>'+item.account+'</td>\n' +
                        '                                    <td>\n' +
                        '                                      <span\n' +
                        '                                        class="status-icon bg-orange"\n' +
                        '                                      ></span>\n' +
                        '                                      未组队' +
                        '                                    </td>\n' +
                        '                                  </tr>\n';

                })
                content.innerHTML=str;
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
function requestTeamValid(){
let ata = {
    reason: $("#textarea-input").val(),
};
    $.ajax({
        type: "post",
        url: "http://xug98.cn/team/"+Cookies.get("team")+"/teamvalidrequest",
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
            },
            400: function() {
                $("#password").val("");
                alert("fail！");
            }
        }
    });

}
function createTeam() {
    let addData="";

    let childCheckBoxes1 = $("tbody.check1 input[type='checkbox']");
    let childValue1 = $("tbody.check1 input[type='text']");
    let i;
    let conflictdata=[];
    for (i = 0; i < childCheckBoxes1.length; i++) {
        if (childCheckBoxes1[i].checked == true) {
            let conflictInner={};
            conflictInner.id = childCheckBoxes1[i].value;
            conflictdata.push(conflictInner);
        }
    }
    console.log(addData);
    let myId=Cookies.get("id");
    let myCourse=Cookies.get("course");
    let leader =
        {
            id: myId
        };
    let ata = {
        name: $("#name").val(),
        courseId: myCourse,
        classId: $("#select-content").val(),
        leader: leader,
        members: conflictdata
    };

    console.log(ata);
    $.ajax({
        type: "post",
        url: "http://xug98.cn/team",
        dataType: "json",
        data:JSON.stringify(ata),
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
function getAllClassForCreate() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn/course/" + Cookies.get("course") +"/class",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log("classlist");
                let content=document.getElementById("select-content");   //获取外围容器
                let str="";

                $.each(data, function(i, item) {
                    console.log(item);
                    str+='<option value="'+item.id+'">'+item.klassSerial+'班</option>';

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
function addTeamMembers() {
    let addData="";
    let conflictdata="[";

    let childCheckBoxes1 = $("tbody.check1 tr td label input[type='checkbox']");
    let childValue1 = $("tbody.check1 tr td input[type='text']");
    let values = "";
    let i;
    for (i = 0; i < childCheckBoxes1.length; i++) {
        if (childCheckBoxes1[i].checked == true) {
            if (conflictdata !== "[") conflictdata += ',';
            conflictdata += '{"id":' + childCheckBoxes1[i].value + '}';
        }

    }
    conflictdata += ']';
    addData="{memebers:"+conflictdata+"}";
    console.log(addData);


    $.ajax({
        type: "put",
        url: "http://xug98.cn/team/" + Cookies.get("team"),
        dataType: "json",
        data: JSON.stringify(addData),
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("success");
        },
        error:function() {
            alert("error");
            let myTeam=Cookies.get("team");
            requestTeamValid();
        },
        statusCode: {
            400: function() {
                var result = confirm("无法添加成员,确认发起特殊申请?");
                if (result) {
                   windwos.location.herf="./course-team-request.html";
                }
            }
        }
    });
    /*
      window.location.reload();
    */
}
function getNoTeam() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn/course/" + Cookies.get("course") + "/noTeam",
        dataType: "json",
        async : false,

        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                var content=document.getElementById("content-no");   //获取外围容器
                var str="";
                $.each(data, function(i, item) {
                    console.log(item);
                    str +='                          <tr>\n' +
                        '                            <td class="text-nowrap">'+item.name+'</td>\n' +
                        '                            <td>'+item.account+'</td>\n' +
                        '                            <td>未组队</td>\n' +
                        '                          </tr>\n';

                })
                content.innerHTML=str;
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
function deleteTeamMember(teamId,id) {
    let ata = {
        id: id
    };
    $.ajax({
        type: "delete",
        url: "http://xug98.cn/team/" + teamId + "/remove",
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
function deleteTeam(teamId) {
    var result = confirm("确定删除学生?");
    if (result) {
        $.ajax({
            type: "delete",
            url: "http://xug98.cn/team/" + teamId,
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
                400: function() {
                    var result = confirm("无法删除成员,确认发起特殊申请?");
                    if (result) {
                        windwos.location.herf="./course-team-request.html";
                    }
                }
            }
        });
    }
}

//课程主页
function getCourseList() {
  $.ajax({
    type: "get",
    url: "http://xug98.cn/course",
    // url: "../../static/json/course-list.json",
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
          str +='            <div class="col-md-6 col-lg-4" >\n' +
              '                <div class="card">\n' +
              '                  <div class="card-header">\n' +
              '                    <h3 class="card-title mr-4">'+item.courseName+'</h3>\n' +
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
              '                        href="course-team.html"\n' +
              '                        class="list-group-item list-group-item-action"\n' +
              '                        onclick="jumpFromCourseHome('+item.id+')"\n' +
              '\n' +
              '                      >\n' +
              '                        <span class="float-right">\n' +
              '                          <i class="icon fe fe-chevron-right"></i>\n' +
              '                        </span>\n' +
              '                        <span class="float-left">\n' +
              '                          <i class="icon fe fe-slack mr-3"></i> 课程组队\n' +
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
              '                          <i class="icon fe fe-clipboard mr-3"></i> 我的成绩\n' +
              '                        </span>\n' +
              '                      </a>\n' +
              '\n' +
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
function getClassByCourse(){
    let myCourse=Cookies.get("course");
    $ajax({
        type: "get",
        url: "http://xug98.cn/class/"+myCourse+"/class",
        dataType: "json",
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
            alert("getCurrentSeminar success");
            if (xhr.status === 200) {
                Cookies.set("class",data.id);
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


//轮次设置页面
function getRoundList() {
    let courseId=Cookies.get("course");
    console.log(courseId);
    $.ajax({
        type: "get",
        url: "http://xug98.cn/course/" + Cookies.get("course") + "/round",
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
        url: "http://xug98.cn/round/" + roundId + "/seminar",
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
                    '                            class="card-options-collapse"\n' +
                    '                            data-toggle="card-collapse"\n' +
                    '                        href="./seminar-round-detail.html"\n' +
                    '                        onclick="jumpFromRound('+roundId+')"' +
                    '                            href="#"\n' +
                    '                            ><i class="fe fe-chevron-right mr-1"></i\n' +
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

//进行中
function getAttendanceByClass() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn/seminar/" +
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

                        strTab+='<a class="'+navClass+'" id="'+item.id+'" href="#">' +
                            '                      <span class="icon mr-3"><i class="fe fe-inbox"></i></span\n' +
                            '                      >'+item.classSerial+'-'+item.teamSerial+'<span class="ml-auto badge badge-primary"></span>\n' +
                            '                    </a>';
                    });
                    Cookies.set("attendance",currentId);
                    tabContent.innerHTML=strTab;
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
        url: "http://xug98.cn/seminar",
        dataType: "json",
        contentType: "application/json",
        success: function(data, textStatus, xhr) {
            console.log(data);
                alert("getCurrentSeminar success");
                Cookies.set("class",data.klassId);
                Cookies.set("seminar",data.seminarId);
                getAttendanceByClass();

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
function getQuestionList(attendanceId) {

    $.ajax({
        type: "get",
        url: "http://xug98.cn/seminar/" +
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



//讨论课详情
function getSeminarByClass() {
    let seminarId=Cookies.get("seminar");
    let classId=Cookies.get("class");
    //
    // console.log(seminarId);
    // console.log(classId);
    $.ajax({
        type: "get",
        url: "http://xug98.cn/seminar/" +
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
        url: "http://xug98.cn/seminar/" +
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
        url: "http://xug98.cn/seminar/" +
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
        url: "http://xug98.cn/seminar/" +
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
        url: "http://xug98.cn/attendance/" +
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
        url: "http://xug98.cn/attendance/" + attendanceId + "/ppt",
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
        url: "http://xug98.cn/attendance/" +
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