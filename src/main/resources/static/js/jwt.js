$.ajaxSetup({
  beforeSend: function(xhr) {
    if (localStorage.jwt) {
      xhr.setRequestHeader("Authorization", localStorage.jwt);
    }
  },
  statusCode: {
    401: function() {
      alert("未授权访问");
      window.location.href = "../../templates/common/login.html";
    },
    403: function() {
      alert("未授权访问");
      window.location.href = "../../templates/common/login.html";
    }
  }
});
