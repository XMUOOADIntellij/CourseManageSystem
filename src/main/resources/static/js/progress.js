var stompClient = null;
function setConnected(connected) {}
//403
function connect() {
  var socket = new SockJS("http://xug98.cn/Socket");
  stompClient = Stomp.over(socket);
  stompClient.connect(
    {},
    function(frame) {
      setConnected(true);
      console.log("Connected: " + frame);
      stompClient.subscribe("/seminarSocket/progress", function(greeting) {
        showGreeting(JSON.parse(greeting.body).content);
      });
      stompClient.subscribe("/seminarSocket/question", function(greeting) {
        showGreeting(JSON.parse(greeting.body).content);
      });
      stompClient.subscribe("/seminarSocket/attendance", function(greeting) {
        showGreeting(JSON.parse(greeting.body).content);
      });
    }
  );
    var socket = new SockJS('http://localhost:8080/Socket');
    console.log("${createLink(uri: '/stomp')}");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/seminarSocket/progress', function(greeting){
            showGreeting(JSON.parse(greeting.body).content);
        });
        stompClient.subscribe('/seminarSocket/question', function(greeting){
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
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
  stompClient.send("/app/Socket/seminar/1/class/1/pause", {}, null);
}
function start() {
  stompClient.send("/app/Socket/seminar/1/class/1/start", {}, null);
}
function end() {
  stompClient.send("/app/Socket/seminar/1/class/1/end", {}, null);
}
function next() {
  stompClient.send("/app/Socket/seminar/1/class/1/attendance/1/next", {}, null);
}
function select() {
  stompClient.send(
    "/app/Socket/seminar/1/class/1/attendance/1/question",
    {},
    null
  );
}
function ask() {
  stompClient.send(
    "/app/Socket/seminar/" +
      Cookies.get("seminar") +
      "/class/" +
      Cookies.get("class") +
      "/question",
    {},
    null
  );
}
function showGreeting(message) {
  console.log(message);
}
