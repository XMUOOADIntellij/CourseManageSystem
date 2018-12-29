function initAccountSetting(){
  getUserInfo();
}
function initCourseGroup(){
  getTeam();
  getMyTeam();
}
function initTeamCreate(){
  getMyTeam();
  getClassList();
  getNoTeam();
}
function initCourseGrade() {

  let aRoundScore = getRoundScoreByCourse(); //总成绩
  for (let j = 0; j < aRoundScore.length; j++) {

    let aSeminarScore = getSeminarScoreByRound(); //总成绩
  }
}
function initDetailScore(){
  getMyAttendance();
  getAttendanceScore();
}
function initDetail(){
  getSeminarByClass();
  getMyAttendance();
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
function getAttendanceByClass(klassId,seminarId) {

  $.ajax({
    type: "get",
    url:
        "http://xug98.cn:8080/seminar/" +
        seminarId +
        "/class/" +
        klassId +
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
    url: "http://xug98.cn:8080/student/active",
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