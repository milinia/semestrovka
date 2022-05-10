var webSocket;

function connect() {
    webSocket = new WebSocket('ws://localhost:8080/chat');

    webSocket.onmessage = function receiveMessage(response) {
        let data = response['data'];
        let json = JSON.parse(data);
        $('#messagesList').first().after("<p>" + "<strong>" +json['from'] + ": " + "</strong>" +  json['text'] + "</p>")
    };
    //"<img src='" + json['linkToImage'] +"' width='20' height='20'>" +
    webSocket.onerror = function errorShow() {
        alert('Authorization error!')
    }
}

function sendMessage(from, text) {
    if (from == null || text === '') return

    let message = {
        "from": from,
        "text": text
    };

    webSocket.send(JSON.stringify(message));
}