function bindUser() {
  // jquery 表单提交
  // let ata = {account:$("#account").val(),password:$("#password").val()}
  let ata = { account: "2888", password: "123456" };//for test
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
        window.localStorage.setItem("jwt", data.jwt);
        Cookies.set("id",data.id);
        Cookies.set("account",data.account);
        Cookies.set("role",data.role);
        Cookies.set("name",data.name);
       /* if(data.isActive===true){
            if (data.role === "student") window.location.href = "../student/home.html";
            else window.location.href = "/teacher/home.html";
        }
        else{
            if (data.role === "student") window.location.href = "../student/active.html";
            else window.location.href = "../teacher/active.html";
        }
        */
        window.location.href = "../teacher/home.html"; //for test
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

function resetPassword() {
  // jquery 表单提交
  let ata=$("#account").val();
  console.log(ata);
  $.ajax({
    type: "get",
    url: "http://xug98.cn/user/password?account="+ata,
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
    statusCode: {
      204: function(){
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
