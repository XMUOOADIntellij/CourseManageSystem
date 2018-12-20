function createClass() {
  // jquery 表单提交
  alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function createUser() {
  // jquery 表单提交
  // let ata = {account:$("#account").val(),password:$("#password").val()}
  let ata = { account: "1513@qq.com", password: "123456" };
  $.ajax({
    type: "post",
    url: "http://xug98.cn/user/login",
    dataType: "json",
    data: JSON.stringify(ata),
    contentType: "application/json",
    success: function(data, textStatus, xhr) {
      console.log(data);
      alert("success");
      if (xhr.status === 200) {
        //状态码存疑
        return true;
      }
    },
    statusCode: {
      401: function() {
        $("#password").val("");
        alert("用户名或密码错误！");
      }
    }
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
