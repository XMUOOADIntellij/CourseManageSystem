function createClass() {
alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function activeUser() {
// let ata = {account:$("#account").val(),password:$("#password").val()}
  let ata = {
    password:$("#password").val()
  };
  console.log(ata);
  alert("input");
  $.ajax({
    type:'put',
    url: 'http://xug98.cn/teacher/active',
    dataType: "json",
    data: JSON.stringify(ata),
    contentType: "application/json",
    success: function (data,textStatus,xhr) {
      if(xhr.status === 200){
        alert("注册成功");
        window.location.href="../common/login.html";
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
/*function getCourseList() {
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
}*/
function getCourseList() {
  $.ajax({
    type: "get",
    url: "http://xug98.cn:8080/course",
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
function getCourseInfo() {
  Cookies.set("course","1");
  $.ajax({
    type: "get",
    url: "http://xug98.cn/course"+Cookies.get("course"),
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
function getClassList() {
  Cookies.set("course","1");
  Cookies.set("class","1");
  $.ajax({
    type: "get",
    url: "http://xug98.cn/course"+Cookies.get("course")+"/class",
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
function updateClass() {
  Cookies.set("seminar","1");
  Cookies.set("class","1");
  let ata ={file: "string"};
  console.log(ata);
  $.ajax({
    type: "put",
    url: "http://xug98.cn/class/"+Cookies.get("class"),
    dataType: "json",
    data: JSON.stringify(ata),
    contentType: "application/json",
    success: function(data, textStatus, xhr) {
      console.log(data);
      alert("success");
      window.location.href="./seminar-detail.html";
    },
    statusCode: {
      400: function() {
        $("#password").val("");
        alert("用户名或密码错误！");
      }
    }
  });
}
function deleteClass(){
  let cid="1";
  let ata = { id: cid };
  $.ajax({
    type: "delete",
    url: "http://xug98.cn/class/" + cid,
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
function getRoundList() {
  Cookies.set("course","1");
  $.ajax({
    type: "get",
    url: "http://xug98.cn/course/"+Cookies.get("course")+"/round",
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
function getSeminarList(roundId) {
  $.ajax({
    type: "get",
    url: "http://xug98.cn/round/"+roundId+"/seminar",
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
function getSeminar() {
  Cookies.set("seminar","1");
  $.ajax({
    type: "get",
    url: "http://xug98.cn/seminar/"+Cookies.get("seminar"),
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
  Cookies.set("seminar","1");
  Cookies.set("class","1");
  $.ajax({
    type: "get",
    url: "http://xug98.cn/seminar/"+Cookies.get("seminar")+"/class/"+Cookies.get("class"),
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
function getSeminarScoreByClass(){
  Cookies.set("seminar","1");
  Cookies.set("class","1");
  $.ajax({
    type: "get",
    url: "http://xug98.cn/seminar/"+Cookies.get("seminar")+"/class/"+Cookies.get("class")+"/seminarscore",
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
function updateSeminarScoreByClass(){
  let teamData={id: 1,name:"1-1"};
  let ata ={
    team: teamData,
    preScpre: "5",
    reportScore: "4",
    questionScore: "5"
  };
  console.log(ata);
  Cookies.set("seminar","1");
  Cookies.set("class","1");
  $.ajax({
    type: "put",
    url: "http://xug98.cn/seminar/"+Cookies.get("seminar")+"/class/"+Cookies.get("class")+"/seminarscore",
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
  let conflict={
    courseId: "2",
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
    teamEndTime: convertTime($("#input-start").val()),
    conflictCourseList: conflict
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
      window.location.href="./course-round.html";
    },
    statusCode: {
      400: function() {
        $("#password").val("");
        alert("用户名或密码错误！");
      }
    }
  });
}
function createSeminar() {
  Cookies.set("course","1");
  let ata = {
    roundId:$("#select-round-id").val(),

    seminarName:$("#name").val(),
    introduction:$("#introduction").val(),
    maxTeam:$("#select-max-team").val(),
    visible:$("#switch-visible").is(":checked"),
    seminarSerial: $("#select-seminar-serial").val(),
    enrollStartTime:convertTime($("#input-start").val()),
    enrollEndTime:convertTime($("#input-end").val()),
    courseId:Cookies.get("course")
  };
  console.log(ata);
  alert("input");
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
    statusCode: {
      400: function() {
        $("#password").val("");
        alert("用户名或密码错误！");
      }
    }
  });
}
function deleteSeminar() {
  let cid="1";
  let ata = { id: cid };
  $.ajax({
    type: "delete",
    url: "http://xug98.cn/seminar/" + cid,
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
function createShare() {
alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function updateSeminar() {
  Cookies.set("seminar","1");
  let ata = {
    seminarName:$("#name").val(),
    introduction:$("#introduction").val(),
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
    type: "put",
    url: "http://xug98.cn/seminar/"+Cookies.get("seminar"),
    dataType: "json",
    data: JSON.stringify(ata),
    contentType: "application/json",
    success: function(data, textStatus, xhr) {
      console.log(data);
      alert("success");
      window.location.href="./course-round.html";
    },
    statusCode: {
      400: function() {
        $("#password").val("");
        alert("用户名或密码错误！");
      }
    }
  });
}
function updateSeminarByClass() {
  Cookies.set("seminar","1");
  Cookies.set("class","1");
  let ata ={
    reportDdl:convertTime($("#input-deadline").val()),
  };
  console.log(ata);
  $.ajax({
    type: "put",
    url: "http://xug98.cn/seminar/"+Cookies.get("seminar")+"/class/"+Cookies.get("class"),
    dataType: "json",
    data: JSON.stringify(ata),
    contentType: "application/json",
    success: function(data, textStatus, xhr) {
      console.log(data);
      alert("success");
      window.location.href="./seminar-detail.html";
    },
    statusCode: {
      400: function() {
        $("#password").val("");
        alert("用户名或密码错误！");
      }
    }
  });
}
function getAttendanceByClass(){
  Cookies.set("seminar","1");
  Cookies.set("class","1");
  $.ajax({
    type: "get",
    url: "http://xug98.cn/seminar/"+Cookies.get("seminar")+"/class/"+Cookies.get("class")+"/attendance",
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
function getPptByClass(){
  Cookies.set("seminar","1");
  Cookies.set("class","1");
  $.ajax({
    type: "get",
    url: "http://xug98.cn/seminar/"+Cookies.get("seminar")+"/class/"+Cookies.get("class")+"/ppt",
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
function getReportByClass(){
  Cookies.set("seminar","1");
  Cookies.set("class","1");
  $.ajax({
    type: "get",
    url: "http://xug98.cn/seminar/"+Cookies.get("seminar")+"/class/"+Cookies.get("class")+"/report",
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
function getPptByAttendance(){
  Cookies.set("attendance","1");
  $.ajax({
    type: "get",
    url: "http://xug98.cn/attendance/"+Cookies.get("attendance")+"/ppt",
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
function getReportByAttendance(){
  Cookies.set("attendance","1");
  $.ajax({
    type: "get",
    url: "http://xug98.cn/attendance/"+Cookies.get("attendance")+"/report",
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