var ws;
function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#response").show();
    }
    else {
        $("#response").hide();
    }
    $("#feedback").html("");
}

function connect() {
    ws = new WebSocket('ws://localhost:8080/channel');
    ws.onmessage = function(data){
        showResponse(data.data);
    }
    setConnected(true);
}

function disconnect() {
    if (ws != null) {
        ws.close();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendFormData() {
    var data = JSON.stringify({'nickname': $("#name").val() ,'message': $("#message").val()})
    ws.send(data);
}

function showResponse(message) {
    $("#response").append("<tr><td> " + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendFormData(); });
});
