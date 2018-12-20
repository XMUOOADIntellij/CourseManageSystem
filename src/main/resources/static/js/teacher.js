function createClass() {
  // jquery 表单提交
  alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function activeUser() {
  // jquery 表单提交
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
    beforeSend: function(xhr) {
      if (localStorage.jwt) {
        let token=localStorage.jwt;
        xhr.setRequestHeader("Authorization",token);
      }
    },
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
  // jquery 表单提交
  alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function createSeminar() {
  // jquery 表单提交
  alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function createRound() {
  // jquery 表单提交
  alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function createShare() {
  // jquery 表单提交
  alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function updateSeminar() {
  // jquery 表单提交
  alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function updateRound() {
  // jquery 表单提交
  alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function updateClass() {
  // jquery 表单提交
  alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function deleteCourse() {
  // jquery 表单提交
  alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
