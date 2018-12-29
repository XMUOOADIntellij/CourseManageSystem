var stompClient = null;
function setConnected(connected) {}
//403
function connect() {
  var socket = new SockJS("/Socket");
  stompClient = Stomp.over(socket);
  stompClient.connect(
    {},
    function(frame) {
      setConnected(true);
      console.log("Connected: " + frame);
      stompClient.subscribe("/seminarSocket/progress", function(greeting) {
        console.log(JSON.parse(greeting.body));
      });
      stompClient.subscribe("/seminarSocket/question", function(greeting) {
          console.log(JSON.parse(greeting.body));
      });
      stompClient.subscribe("/seminarSocket/attendance", function(greeting) {
          tabClick(JSON.parse(greeting.body));
      });
    }
  );

}
function disconnect() {
  if (stompClient != null) {
    stompClient.disconnect();
  }
  setConnected(false);
  alert("Disconnected");
  return true;
}

function pause() {
  stompClient.send("/app/Socket/seminar/"+Cookies.get("seminar")+"/class/"+Cookies.get("class")+"/pause", {"jwt":localStorage.jwt}, null);
}
function start() {

    stompClient.send("/app/Socket/seminar/"+Cookies.get("seminar")+"/class/"+Cookies.get("class")+"/start", {"jwt":localStorage.jwt}, null);
}
function end() {
    stompClient.send("/app/Socket/seminar/"+Cookies.get("seminar")+"/class/"+Cookies.get("class")+"/end", {"jwt":localStorage.jwt}, null);
}
function next() {
    stompClient.send("/app/Socket/seminar/"+Cookies.get("seminar")+"/class/"+Cookies.get("class")+"/attendance/"+Cookies.get("attendance")+"/next", {"jwt":localStorage.jwt}, null);
}
function select() {
    stompClient.send("/app/Socket/seminar/"+Cookies.get("seminar")+"/class/"+Cookies.get("class")+"/attendance/"+Cookies.get("attendance")+"/pause", {"jwt":localStorage.jwt}, null);

}
function ask() {
    stompClient.send("/app/Socket/seminar/"+Cookies.get("seminar")+"/class/"+Cookies.get("class")+"/question", {}, null);

}
function showGreeting(message) {
  console.log(message);
}
