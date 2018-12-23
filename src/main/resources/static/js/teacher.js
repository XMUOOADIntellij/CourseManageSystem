function createClass() {
alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function activeUser() {
// let ata = {account:$("#account").val(),password:$("#password").val()}
  let ata = {
    password:$("#password").val()
  };
  $.ajax({
    type:'put',
    url: 'http://xug98.cn/teacher/active',
    dataType: "json",
    data: JSON.stringify(ata),
    contentType: "application/json",
    success: function (data,textStatus,xhr) {
      if(xhr.status === 200){
        alert("注册成功");
        window.location.href="/login";
      }
    },
    statusCode:{
      400: function () {
        alert("无法注册！");
      }
    },
  });
  return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function createCourse() {
alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function createSeminar() {
  let ata = {
    courseId:Cookies.get("courseId"),
    seminarName:$("#name").val(),
    maxTeam:$("#select-max-team").val(),
    visible:$("#switch-visible").is(":checked"),
    seminarSerial: $("#select-seminar-serial").val(),
    roundId:$("#select-round-id").val()
  };
  console.log(ata);
  $.ajax({
    type: "post",
    url: "http://xug98.cn/seminar",
    dataType: "json",
    data: JSON.stringify(ata),
    contentType: "application/json",
    success: function(data, textStatus, xhr) {
      console.log(data);
      alert("success");
      backCourseRound();
    },
/*    statusCode: {
      400: function() {
        $("#password").val("");
        alert("用户名或密码错误！");
      }
    }*/
  });
}
function deleteSeminar() {
  let ata = { id: cid };
  $.ajax({
    type: "delete",
    url: "/course/" + cid,
    data: JSON.stringify(ata),
    dataType: "json",
    contentType: "application/json;",
    error: function(data, textStatus, xhr) {
      console.log(cid);
      alert("wrong");
    },
    success: function(data, textStatus, xhr) {
      if (xhr.status === 204) {
        alert("成功");
        document.fe;
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
function createRound() {
alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function createShare() {
alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function updateSeminar() {
alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function updateRound() {
alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function updateClass() {
alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function deleteCourse() {
alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
