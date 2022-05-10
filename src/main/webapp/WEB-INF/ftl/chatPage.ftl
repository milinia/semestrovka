<!doctype html>
<html lang="ru">
<head>
    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
    <script src="../../resources/chat.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Chat</title>
    <style>
        .mainHeader{
            background-color: #f3a79e;
            height: 80px;
            width: 100%;
            margin-bottom: 1%;
        }
    </style>
</head>
<body onload="connect('${id}')">
    <div class="mainHeader">
        <img src="/resources/logo.jpeg" width="220" height="80">
    </div>
    <div>
        <button onclick="sendMessage('${id}', 'Hello!')">Join to chat</button>
        <br>
        <input name="message" id="message" placeholder="Сообщение">
        <button onclick="sendMessage('${id}', $('#message').val())" id="sendMessageButton">Send</button>
    </div>
    <br>
    <div>
        <h3>Messages:</h3>
        <div id="messagesList"> </div>
    </div>
</body>
</html>