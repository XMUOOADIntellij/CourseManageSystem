//测试通过
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
//测试通过
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
//状态码存疑
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
//状态码存疑
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
//
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
      }
    }
  });
  return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
//success 有8080 404 不带8080 就对
function getCourseList() {
  $.ajax({
    type: "get",
    url: "/course",
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
//400 未部署
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
//success conflict未加
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
//success
function getClassList() {


  $.ajax({
    type: "get",
    url: "/course/" + Cookies.get("course") + "/class",
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
//405 谈雪 dom file选择
function updateClass() {

  var fileToUpload = $("#file").prop("files")[0];
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
//状态码存疑
function deleteClass() {
  let cid = "1";
  $.ajax({
    type: "delete",
    url: "/class/" + cid,
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

  $.ajax({
    type: "get",
    url: "/course/" + Cookies.get("course") + "/round",
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
    url: "/round/" + "1" + "/seminar",
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

  $.ajax({
    type: "get",
    url: "/seminar/" + Cookies.get("seminar"),
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


  $.ajax({
    type: "get",
    url:
      "/seminar/" +
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
//success dom 选择reportddl 一定要选
function updateSeminarByClass() {


  let ata = {
    reportDdl: convertTime($("#input-deadline").val())
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
//未部署
function getSeminarScoreByClass() {


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
//success dom 选择start end 一定要选
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
//success dom
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
//success
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
//success dom
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
//success
function getAttendanceByClass() {


  let ata = {
    courseId: "1"
  };
  console.log(ata);

  $.ajax({
    type: "get",
    url:
      "/seminar/" +
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
//无文件
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
//无文件
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
//无文件
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
//新添加
function getTeam() {

  $.ajax({
    type: "get",
    url: "/course/" + Cookies.get("course") + "/myTeam",
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
//dom file选择
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

function deleteCourse() {
  let cid = "2";
  $.ajax({
    type: "delete",
    url: "/course/" + cid,
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

function getRoundScoreByCourse(cid) {


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

function getQuestionList() {




  $.ajax({
    type: "get",
    url:
      "/seminar/" +
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
//dom
function activeStudent() {
  // let ata = {account:$("#account").val(),password:$("#password").val()}
  let ata = {
    password: "22",
    email: "22@qq.com"
  };
  console.log(ata);
  alert("input");
  $.ajax({
    type: "put",
    url: "/student/active",
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
    url: "/seminar",
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


  $.ajax({
    type: "get",
    url:
      "/seminar/" +
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
function getAttendanceScore() {


  $.ajax({
    type: "get",
    url:
      "/score/attendance/" +
      Cookies.get("attendance") +
      "/score",
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
//dom
function bindAdmin() {
  let ata = { account: "admin", password: "admin" };
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

function createTeacher() {
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
}

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
function deleteTeacher() {


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
}
function deleteStudent() {


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
}

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

function searchTeacher() {

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
}
function searchStudent() {

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
}
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


function updateTeamShare(handletype,id) {

  let ata = {
    handletype: handletype
  };
  console.log(ata);
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
function updateSeminarShare(handletype,id) {

  let ata = {
    handletype: "accept"
  };
  console.log(ata);
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
    // url: "/request/teamvaild" ,
    url: "data.json",
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
