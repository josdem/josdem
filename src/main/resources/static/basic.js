var socket = new WebSocket('ws://localhost:8080/messages');

socket.onopen = function (event) {
    console.log('Connected to chatbot');
    console.log(event);
}

socket.onmessage = function (event) {
    console.log('Received: ' + event.data + '!');
    var parseMessage = JSON.parse(event.data);
    console.log(parseMessage);
}

