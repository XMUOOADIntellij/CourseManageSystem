function bindUser() {
  // jquery 表单提交
  // let ata = {account:$("#account").val(),password:$("#password").val()}
  let ata = { account: "213", password: "123456" };
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
        window.localStorage.setItem("jwt", data.jwt);
        if (data.type === "student") window.location.href = "/student/profile";
        else window.location.href = "/teacher/home";
      }
    },
    statusCode: {
      400: function() {
        $("#password").val("");
        alert("用户名或密码错误！");
      }
    }
  });
  return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}

function createPassword() {
  // jquery 表单提交
  alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function editPassword() {
  // jquery 表单提交
  getVerCode();
  updatePassword();
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function editEmail() {
  // jquery 表单提交
  getVerCode();
  updateEmail();
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function updatePassword() {
  // jquery 表单提交
  alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function updateEmail() {
  // jquery 表单提交
  alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function getVerCode() {
  // jquery 表单提交
  alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
