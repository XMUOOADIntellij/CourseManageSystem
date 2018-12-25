var stompClient = null;
function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('response').innerHTML = '';
}
function connect() {
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
    /*if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    alert("Disconnected");
    return true;*/
}

function sendName() {
    var name = document.getElementById('name').value;
    stompClient.send("/app/hello", {}, JSON.stringify({ 'name': name }));
}
function showGreeting(message) {
    console.log(message);
}