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

//学生激活
function activeStudent() {
  // let ata = {account:$("#account").val(),password:$("#password").val()}
      let ata = {
        password: $("#password").val()
    };
  console.log(ata);
  alert("input");
  $.ajax({
    type: "put",
    url: "/student/active",
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

//课程组队
function getTeam() {
    $.ajax({
        type: "get",
        url: "http://xug98.cn/course/" + Cookies.get("course") + "/myTeam",
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
        url: "http://xug98.cn/course/" + Cookies.get("course") + "/myTeam",
        dataType: "json",
        contentType: "application/json;",
        success: function(data, textStatus, xhr) {
            if (xhr.status === 200) {
                // alert("获取成功");
                console.log("data");
                Cookies.set("team",data.id);

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
function requestTeamValid(){
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
    let member = [
        {
            id: 24320162202847,
        },
        {
            id: 24320162202888,

        },
        {
            id:24320162202904,

        },
        {
            id: 24320162202934
        }
    ];
    let myId=Cookies.get("account");
    let myCourse=Cookies.get("course");
    let leader = [
        {
            id: myId
        }
    ];
    let ata = {
        id: 1,
        name: "1-2 Intellij",
        course: {
            id: myCourse
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
        url: "http://xug98.cn/team",
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

function addTeamMembers() {
    let member = [
        {
            id: 20420162201582
        },
        {
            id: 20420162201666
        }
    ];
    let ata = {
        id: 1,
        name: "1-2 Intellij",
        course: {
            id: 1
        },
        class: {
            id: 1
        },
        members: member,
        valid: true
    };
    console.log(ata);
    $.ajax({
        type: "put",
        url: "http://xug98.cn/team/" + Cookies.get("team"),
        dataType: "json",
        data: JSON.stringify(ata),
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

    $.ajax({
        type: "get",
        url: "http://xug98.cn/course/" + Cookies.get("course") + "/noTeam",
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
function deleteTeamMember() {
    let cid = "2";
    let ata = {
        id: "24320162202888",
    };
    $.ajax({
        type: "delete",
        url: "http://xug98.cn/team/" + cid + "/remove",
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

//课程主页
function getCourseList() {
  $.ajax({
    type: "get",
    // url: "http://xug98.cn/course",
    url: "../../static/json/course-list.json",
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