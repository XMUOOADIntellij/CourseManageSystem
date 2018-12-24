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
function getCourseList() {
  $.ajax({
    type: "get",
    url: "http://xug98.cn/course",
    dataType: "json",
    contentType: "application/json;",
    success: function(data, textStatus, xhr) {
      if (xhr.status === 200) {
        // alert("获取成功");
        let content = document.getElementById("content"); //获取外围容器
        let strVar = "";
        let name=Cookies.get("name");
        for (let i = 0; i < data.length; i++) {
          strVar += "<div class=\"col-md-6 col-xl-4\"><div class=\"card\" id=\""+data[i].id+"\"><div class=\"card-header\"><h3 class=\"card-title mr-4\">"+data[i].name+"<\/h3>";

          if(data[i].teacherName===name)
            strVar+="<span class=\"btn btn-outline-secondary py-0\">"+"主"+"<\/span>";
          else
            strVar+="<span class=\"btn btn-outline-secondary py-0\">"+"副"+"<\/span>";

          strVar+="<div class=\"card-options\"><a class=\"card-options-collapse\" data-toggle=\"card-collapse\" href=\"#\"><i class=\"fe fe-chevron-up mr-1\"><\/i><\/a><\/div><\/div><div class=\"card-body p-0\"><div class=\"list-group\"><a href=\"./course-info.html\" class=\"list-group-item list-group-item-action\" onclick=\"\"><span class=\"float-right\"><i class=\"icon fe fe-chevron-right\"><\/i><\/span> <span class=\"float-left\"><i class=\"icon fe fe-book mr-3\"><\/i> 课程信息<\/span><\/a> <a href=\"./course-class.html\" class=\"list-group-item list-group-item-action\"><span class=\"float-right\"><i class=\"icon fe fe-chevron-right\"><\/i><\/span> <span class=\"float-left\"><i class=\"icon fe fe-briefcase mr-3\"><\/i> 班级信息<\/span><\/a> <a href=\"./course-round.html\" class=\"list-group-item list-group-item-action\"><span class=\"float-right\"><i class=\"icon fe fe-chevron-right\"><\/i><\/span> <span class=\"float-left\"><i class=\"icon fe fe-book-open mr-3\"><\/i> 讨论课设置<\/span><\/a> <a href=\"./course-group.html\" class=\"list-group-item list-group-item-action\"><span class=\"float-right\"><i class=\"icon fe fe-chevron-right\"><\/i><\/span> <span class=\"float-left\"><i class=\"icon fe fe-slack mr-3\"><\/i> 学生组队<\/span><\/a> <a href=\"./course-grade.html\" class=\"list-group-item list-group-item-action\"><span class=\"float-right\"><i class=\"icon fe fe-chevron-right\"><\/i><\/span> <span class=\"float-left\"><i class=\"icon fe fe-clipboard mr-3\"><\/i> 学生成绩<\/span><\/a> <a href=\"./course-share.html\" class=\"list-group-item list-group-item-action\"><span class=\"float-right\"><i class=\"icon fe fe-chevron-right\"><\/i><\/span> <span class=\"float-left\"><i class=\"icon fe fe-send mr-3\"><\/i> 共享设置<\/span><\/a><\/div><\/div><\/div><\/div>";

        }
        content.innerHTML = strVar;
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
function getRoundList() {
  $.ajax({
    type: "get",
    url: "http://xug98.cn/course/"+Cookies.get("course")+"/round",
    dataType: "json",
    contentType: "application/json;",
    success: function(data, textStatus, xhr) {
      if (xhr.status === 200) {
        // alert("获取成功");
        let content = document.getElementById("content"); //获取外围容器
        let strVar = "";
        let name=Cookies.get("name");
        for (let i = 0; i < data.length; i++) {
          strVar += "<div class=\"col-md-6 col-lg-4\"><div class=\"card\"><div class=\"card-header\"><h3 class=\"card-title mr-4\">第"+data[i].order+"轮<\/h3><div class=\"card-options\"><a href=\"course-round-setting.html\" class=\"card-options-delete\"><i class=\"fe fe-settings\"><\/i><\/a> <a href=\"#"+data[i].id+"\" class=\"card-options-collapse\" data-toggle=\"card-collapse\"><i class=\"fe fe-chevron-up\"><\/i><\/a><\/div><\/div><div class=\"card-body p-0\" id=\""+data[i].id+"\"><\/div><\/div><\/div>";
          getSeminarList(data[i].id);
        }
        content.innerHTML = strVar;
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
function getSeminarList(roundid) {
  $.ajax({
    type: "get",
    url: "http://xug98.cn/course",
    dataType: "json",
    contentType: "application/json;",
    success: function(data, textStatus, xhr) {
      if (xhr.status === 200) {
        // alert("获取成功");
        let content = document.getElementById("content"); //获取外围容器
        let strVar = "";
        let name=Cookies.get("name");
        for (let i = 0; i < data.length; i++) {
          strVar += "<div class=\"col-md-6 col-xl-4\"><div class=\"card\" id=\""+data[i].id+"\"><div class=\"card-header\"><h3 class=\"card-title mr-4\">"+data[i].name+"<\/h3>";


        }
        content.innerHTML = strVar;
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
function createCourse() {
alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function createSeminar() {
  let ata = {
    seminarName:$("#name").val(),
    introduction:$("#name").val(),
    maxTeam:$("#select-max-team").val(),
    visible:$("#switch-visible").is(":checked"),
    seminarSerial: $("#select-seminar-serial").val(),
    enrollStartTime:convertTime($("#input-start").val()),
    enrollEndTime:convertTime($("#input-end").val()),
    roundId:$("#select-round-id").val(),
    courseId:Cookies.get("courseId")
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
      window.location.href="./course-round.html";
    },
/*    statusCode: {
      400: function() {
        $("#password").val("");
        alert("用户名或密码错误！");
      }
    }*/
  });
}
function deleteSeminar(cid) {
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
