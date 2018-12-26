var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/Socket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/seminarSocket/progress', function (greeting) {
            showGreeting(JSON.parse(greeting.body).seminarName);
        });
        stompClient.subscribe('/seminarSocket/question', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/Socket/seminar/4/class/1/start", {"jwt":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NDU4MjM5ODIyOTAsInBheWxvYWQiOiJ7XCJpZFwiOjE4MyxcImFjY291bnRcIjpcIjI0MzIwMTYyMjAyODcxXCIsXCJwYXNzd29yZFwiOlwiMTIzNDU2XCIsXCJlbWFpbFwiOm51bGwsXCJhY3RpdmVcIjpmYWxzZSxcInN0dWRlbnROYW1lXCI6XCLmnpfljZfnkZ5cIn0ifQ.3HL-MODA4o0hauo7iPkLN_3ZUdkUI6qZspXM2rlTKTc"}, null);
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

