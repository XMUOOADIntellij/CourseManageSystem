function createGroup() {
alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function updateGroup() {
alert("success");
  return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function activeUser() {
// let ata = {account:$("#account").val(),password:$("#password").val()}
  let ata = {
    password:$("#password").val(),
    email:$("#email").val()
  };
  console.log(ata);
  $.ajax({
    type:'put',
    url: 'http://xug98.cn/student/active',
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
}