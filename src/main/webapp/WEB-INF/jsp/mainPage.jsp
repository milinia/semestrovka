<%--
  Created by IntelliJ IDEA.
  User: milinia
  Date: 06.11.2020
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Subscriptions in one place</title>
    <link rel="stylesheet" type="text/css" href="../../mainPage.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <style>
        a{
            color: black;
        }
        .header{
            width: 100%;
            height: 80px;
        }
        .btn.btn-outline-dark{
            float: right;
            margin-top: 19px;
            margin-right: 8px;
            display: inline-block;
        }
        .container{
            margin-top: 80px;
            height: 400px;
            margin-bottom: 20px;
        }
        .card{
            display: inline-block;
            float: left;
            margin-right: 77px;
            left: 35px;
        }
        .text{
            float: left;
            font-size: 30px;
            color: black;
            margin-left: 35px;
            margin-top: 15px;
            font-family: "cursive", Сomic Sans MS;
        }
        .text-field{
            font-family: monospace;
            font-size: 25px;
            padding: 5px;
            width: 95%;
            height: 100px;
            margin-left: 35px;
            border-radius: 15px;
        }
        #about{
            font-family: "cursive", Сomic Sans MS;
            font-size: 21px;
            margin-top: 20px;
            margin-left: 10px;
        }
        .about{
            font-family: "cursive", Сomic Sans MS;
            font-size: 21px;
            margin-left: 630px;
            color: white;
            margin-top: 23px;
            display: inline-block;
        }
        .help{
            color: white;
            font-family: "cursive", Сomic Sans MS;
            font-size: 20px;
            margin-left: 30px;
            margin-top: 23px;
            display: inline-block;
        }
        .contact{
            width: 100%;
            height: 40px;
            margin-top:20px;
            margin-left:30%;
        }
        .textForEmail{
            font-family: "cursive", Сomic Sans MS;
            font-size: 14px;
        }
        .border-border-white{
            background: white;
        }
        .carousel {
            width:780px;
            height:550px;
            margin-top: 40px;
            margin-bottom: 50px;
        }
        .circle{
            border-radius: 50%;
            z-index: 999;
            position: absolute;
        }
    </style>
</head>
<body>
<div class="header"><div class="text">Subscriptions in one place</div>
    <button type="button" class="btn btn-outline-dark"><a href="${pageContext.request.contextPath}/login"> Log in </a></button>
    <div class="about"><a href="#about">About</a></div><div class="help" ><a href="https://t.me/joinchat/AAAAAFQWSCPevEv7iMu3Nw">Help</a>
    </div><button type="button" class="btn btn-outline-dark"><a href="${pageContext.request.contextPath}/signup">Sign up</a></button></div>

<div><center>
    <div id="carousel" class="carousel slide" data-ride="carousel">
        <ul class="carousel-indicators">
            <li data-target="#carousel" data-slide-to="0" class="active"></li>
            <li data-target="#carousel" data-slide-to="1"></li>
            <li data-target="#carousel" data-slide-to="2"></li>
        </ul>

        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="" class="d-block w-100" alt="First">
            </div>
            <div class="carousel-item">
                <img src="" class="d-block w-100" alt="Second">
            </div>
            <div class="carousel-item">
                <img src="" class="d-block w-100" alt="Third">
            </div>
        </div>

        <a class="carousel-control-prev" href="#carousel" data-slide="prev">
            <span class="carousel-control-prev-icon"></span>
        </a>
        <a class="carousel-control-next" href="#carousel" data-slide="next">
            <span class="carousel-control-next-icon"></span>
        </a>
    </div>
</center></div>

<div class="text-field"><div id="about">
    Subscriptions in one place - it is a service that allows you to control subscriptions to music, movies and serials. Sign up and enter servises that you use, choose whether you need to be notified and we send your notification about the end of the subscription. So you be able to unsubscribe in time and save you money.
</div></div>

<center><div class="container"><div class="row">
    <div class="col-sm-3"><div>
        <img class="card-img-top" style="width:100px" class="rounded-circle" src="https://img.icons8.com/ios/100/000000/wallet-app--v1.png" alt="Card image">
        <div class="card-body" style="width:200px">
            <h4 class="card-title">Сonvenience</h4>
            <p class="card-text">All subscriptions in one place</p>
        </div>
    </div></div>
    <div class="col-sm-3"><div>
        <img class="card-img-top" style="width:100px" class="rounded-circle" src="https://img.icons8.com/ios-filled/50/000000/topic-push-notification.png" alt="Card image">
        <div class="card-body" style="width:200px">
            <h4 class="card-title">Reminder</h4>
            <p class="card-text">Set up a reminder about the end of the subscription period</p>
        </div>
    </div></div>
    <div class="col-sm-3"><div>
        <img class="card-img-top" style="width:100px" class="rounded-circle" src="https://img.icons8.com/ios/100/000000/calendar--v1.png" alt="Card image">
        <div class="card-body" style="width:200px">
            <h4 class="card-title">Calendar</h4>
            <p class="card-text">Freedom to set up end dates</p>
        </div>
    </div></div>
    <div class="col-sm-3"><div>
        <img class="card-img-top" style="width:100px" class="rounded-circle" src="https://img.icons8.com/external-kiranshastry-solid-kiranshastry/64/000000/external-money-finance-kiranshastry-solid-kiranshastry.png" alt="Card image">
        <div class="card-body" style="width:200px">
            <h4 class="card-title">Saving money</h4>
            <p class="card-text">Stop paying for forgetfulness</p>
        </div>
    </div></div>
</div></div></center>

<div class="contact">
    <div class="textForEmail">Please send your suggestions for site development and translation to memetovaevelina360@gmail.com</div>
</div>
<script>

    var colors = ["#1e89c7dd","#0d82d6dd","#0d50d6dd","#2942c2dd","#1b07f5dd","#6207f5dd","#7b4fc2dd","#b84dfadd","#7a71de94","#46cad494"];
    document.addEventListener('click', drawFigure, false);

    function drawFigure(){
        let index = Math.floor(Math.random() * 10);
        let radius = Math.floor(Math.random() * 10);
        let x = event.pageX;
        let y = event.pageY;

        let circle = document.createElement("div");
        circle.style.top = y + "px";
        circle.style.left = x + "px";
        circle.style.background = colors[index];
        circle.className = "circle";
        circle.style.width = radius*5+"px";
        circle.style.height = radius*5+"px";
        document.body.appendChild(circle);
    }

</script>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>
