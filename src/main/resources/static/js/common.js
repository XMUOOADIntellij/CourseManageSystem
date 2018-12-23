function bindUser() {
let ata = {account:$("#account").val(),password:$("#password").val()}
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
        if(data.isActive===true){
            if (data.role === "student") window.location.href = "../student/home.html";
            else window.location.href = "/teacher/home.html";
        }
        else{
            if (data.role === "student") window.location.href = "../student/active.html";
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
function getUserInfo() {
$.ajax({
    type: "get",
    url: "http://xug98.cn/user/information",
    dataType: "json",
    contentType: "application/json",
    success: function(data, textStatus, xhr) {
      console.log(data);
      alert("success");
      if (xhr.status === 200) {
        Cookies.set("messageInterval",data.messageInterval);
        $("#account").html(data.account);
        $("#email").html(data.email);
        $("#messageInterval").value=data.messageInterval;
      }
    },
    statusCode: {
      401: function () {
        alert("未登录!");
        window.location.href="./login";
      },
      403:function () {
        alert("未登录!");
        window.location.href="./login";
      }
      }
  });
}
function sendPassword() {
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
function editPassword() {
let ata = {
    password:$("#password").val()
  };
  $.ajax({
    type:'put',
    url: 'http://xug98.cn/user/password',
    dataType: "json",
    data: JSON.stringify(ata),
    contentType: "application/json",
    success: function (data,textStatus,xhr) {
      if(xhr.status === 200){
        alert("修改成功");
        window.location.href = "./account-setting.html";
      }
    },
    statusCode:{
      400: function () {
        alert("修改失败");
      }
    },
  });
}
function editEmail() {
let ata = {
    password:$("#email").val()
  };
  $.ajax({
    type:'put',
    url: 'http://xug98.cn/user/email',
    dataType: "json",
    data: JSON.stringify(ata),
    contentType: "application/json",
    success: function (data,textStatus,xhr) {
      if(xhr.status === 200){
        alert("修改成功");
        window.location.href = "./account-setting.html";
      }
    },
    statusCode:{
      400: function () {
        alert("修改失败");
      }
    },
  });
}

