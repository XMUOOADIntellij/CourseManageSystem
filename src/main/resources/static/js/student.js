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
  alert("input");
  $.ajax({
    type:'put',
    url: 'http://xug98.cn:8080/student/active',
    dataType: "json",
    data: JSON.stringify(ata),
    contentType: "application/json",
    success: function (textStatus,xhr) {
      console.log(data);
      alert("注册成功");

      if(xhr.status === 200){
        alert("注册成功");
        window.location.href="../common/login.html";
      }
    },
    error: function(data){
      console.log(data);
      alert("注册失败");

    },
    statusCode:{
      400: function () {
        alert("无法注册！");
      }
    },
  });
}