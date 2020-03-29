var ws;
function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#helpdesk").show();
    }
    else {
        $("#helpdesk").hide();
    }
    $("#feedbacks").html("");
}

function connect() {
    ws = new WebSocket('ws://localhost:8080/channel');
    ws.onmessage = function(data){
        showFeedbacks(data.data);
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
    var data = JSON.stringify({'data': $("#name").val() + "," + $("#message").val()})
    ws.send(data);
}

function showFeedbacks(message) {
    $("#feedback").append("<tr><td><em> " + message + "</em></td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendFormData(); });
});
