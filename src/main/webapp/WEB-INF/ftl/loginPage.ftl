<!doctype html>
<html>
<head>
    <title>Log in</title>
    <link rel="stylesheet" type="text/css" href="../style/auth.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <style>
        .mainHeader{
            background-color: #f3a79e;
            height: 80px;
            width: 100%;
        }
        a{text-decoration: none;color: #f3a79e;}
        a:visited {text-decoration: none;color: #f3a79e;}
        a:hover { text-decoration: none; color: #f3a79e; }
        a:focus { text-decoration: none; color: #f3a79e; }
        a:active { text-decoration: none; color: #f3a79e; }
        .reg {
            width: 50%;
            text-align: center;
            margin-bottom: 10px;
            margin-right:25%;
            margin-left: 25%;
            margin-top: 5%;
            padding: 10px;
            background: white;
            border-radius: 5%;
            box-shadow: 0 0 10px rgba(0,0,0,0.5);
        }
        .header {
            font-size: 15pt;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="mainHeader">
    <img src="/resources/logo.jpeg" width="220" height="80">
</div>
<div class="reg">
    <div class="header">Log in</div>
    <form method="post" action="/signIn">
        <div class="form-group">
            <input type="email" class="form-control" placeholder="Email address" name="email" required>
        </div>
        <div class="form-group">
            <input type="password" class="form-control" placeholder="Password" name="password" required>
        </div>
        <#if error??>
            <h5>${error}</h5>
        </#if>
        <button type="submit" class="btn btn-secondary">Log in</button>
        <p>Don't have account? <a href="/signUp">Sign Up</a></p>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>