var socket = new WebSocket('ws://localhost:8080/messages');

socket.onopen = function (event) {
    console.log('Connected to chatbot');
    console.log(event);
}

socket.onmessage = function (event) {
    console.log('Received: ' + event.data + '!');
    var parseMessage = JSON.parse(event.data);
    var ul = document.getElementById(
        'comments: ' + parseMessage);
    var li = document.createElement('li');
    li.appendChild(
        document.createTextNode(parseMessage));
    ul.appendChild(li);
}

