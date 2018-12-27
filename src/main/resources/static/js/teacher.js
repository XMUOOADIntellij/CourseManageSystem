/*
function getCourseList() {
  $.ajax({
    type: "get",
    url: "http://xug98.cn:8080/course",
    dataType: "json",
    contentType: "application/json;",
    success: function(data, textStatus, xhr) {
      if (xhr.status === 200) {
        // alert("获取成功");
        let content = document.getElementById("content"); //获取外围容器
        let strVar = "";
        let name = Cookies.get("name");
        for (let i = 0; i < data.length; i++) {
          strVar +=
            '<div class="col-md-6 col-xl-4"><div class="card" id="' +
            data[i].id +
            '"><div class="card-header"><h3 class="card-title mr-4">' +
            data[i].name +
            "</h3>";

          if (data[i].teacherName === name)
            strVar +=
              '<span class="btn btn-outline-secondary py-0">' +
              "主" +
              "</span>";
          else
            strVar +=
              '<span class="btn btn-outline-secondary py-0">' +
              "副" +
              "</span>";

          strVar +=
            '<div class="card-options"><a class="card-options-collapse" data-toggle="card-collapse" href="#"><i class="fe fe-chevron-up mr-1"></i></a></div></div><div class="card-body p-0"><div class="list-group"><a href="./course-info.html" class="list-group-item list-group-item-action" onclick=""><span class="float-right"><i class="icon fe fe-chevron-right"></i></span> <span class="float-left"><i class="icon fe fe-book mr-3"></i> 课程信息</span></a> <a href="./course-class.html" class="list-group-item list-group-item-action"><span class="float-right"><i class="icon fe fe-chevron-right"></i></span> <span class="float-left"><i class="icon fe fe-briefcase mr-3"></i> 班级信息</span></a> <a href="./seminar-round.html" class="list-group-item list-group-item-action"><span class="float-right"><i class="icon fe fe-chevron-right"></i></span> <span class="float-left"><i class="icon fe fe-book-open mr-3"></i> 讨论课设置</span></a> <a href="./course-team.html" class="list-group-item list-group-item-action"><span class="float-right"><i class="icon fe fe-chevron-right"></i></span> <span class="float-left"><i class="icon fe fe-slack mr-3"></i> 学生组队</span></a> <a href="./course-score.html" class="list-group-item list-group-item-action"><span class="float-right"><i class="icon fe fe-chevron-right"></i></span> <span class="float-left"><i class="icon fe fe-clipboard mr-3"></i> 学生成绩</span></a> <a href="./course-share.html" class="list-group-item list-group-item-action"><span class="float-right"><i class="icon fe fe-chevron-right"></i></span> <span class="float-left"><i class="icon fe fe-send mr-3"></i> 共享设置</span></a></div></div></div></div>';
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
    url: "http://xug98.cn:8080/course/" + Cookies.get("course") + "/round",
    dataType: "json",
    contentType: "application/json;",
    success: function(data, textStatus, xhr) {
      if (xhr.status === 200) {
        // alert("获取成功");
        let content = document.getElementById("content"); //获取外围容器
        let strVar = "";
        let name = Cookies.get("name");
        for (let i = 0; i < data.length; i++) {
          strVar +=
            '<div class="col-md-6 col-lg-4"><div class="card"><div class="card-header"><h3 class="card-title mr-4">第' +
            data[i].order +
            '轮</h3><div class="card-options"><a href="course-round-setting.html" class="card-options-delete"><i class="fe fe-settings"></i></a> <a href="#' +
            data[i].id +
            '" class="card-options-collapse" data-toggle="card-collapse"><i class="fe fe-chevron-up"></i></a></div></div><div class="card-body p-0" id="' +
            data[i].id +
            '"></div></div></div>';
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
    url: "http://xug98.cn:8080/course",
    dataType: "json",
    contentType: "application/json;",
    success: function(data, textStatus, xhr) {
      if (xhr.status === 200) {
        // alert("获取成功");
        let content = document.getElementById("content"); //获取外围容器
        let strVar = "";
        let name = Cookies.get("name");
        for (let i = 0; i < data.length; i++) {
          strVar +=
            '<div class="col-md-6 col-xl-4"><div class="card" id="' +
            data[i].id +
            '"><div class="card-header"><h3 class="card-title mr-4">' +
            data[i].name +
            "</h3>";
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
*/

function initCourseGrade() {

    let aRoundScore = getRoundScoreByCourse(); //总成绩
    for (let j = 0; j < aRoundScore.length; j++) {
        let aSeminarScore = getSeminarScoreByRound(); //总成绩
  }
}
function initCourseShare() {
  getTeamShareList();
  getSeminarShareList();
}
function initShareCreate() {
  getAllCourse();
}
function initRoundSetting() {
  getSeminarList();
  getClassList();
}
function initTask() {
  getTeamShareTask();
  getSeminarShareTask();
  getTeamValidTask();
}
function initProgress() {
    getCurrentSeminar();
  getAttendanceByClass();
  getCurrentAttendance();
  getQuestionList();
}
function initHome(){
    $("#name").html(Cookies.get("name"));
    $("#account").html(Cookies.get("account"));

}