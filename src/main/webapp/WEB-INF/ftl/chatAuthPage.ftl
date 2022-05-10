<!doctype html>
<html>
<head>
    <title>Log in</title>
    <link rel="stylesheet" type="text/css" href="../style/auth.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <style>
        body{
            background-color: #99BBF7;
        }
        .reg {
            width: 50%;
            text-align: center;
            margin-bottom: 10px;
            margin-right:25%;
            margin-left: 25%;
            margin-top: 5%;
            padding: 10px;
            background: white;
            border-radius: 13em 0.5em/1em 0.5em;
            box-shadow: 0 0 10px rgba(0,0,0,0.5);
        }
        .header {
            font-size: 15pt;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="reg">
    <div class="header">Add nickname and photo before chatting</div>
    <form method="post" action="/auth_to_chat/${user.id}" enctype="multipart/form-data">
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Nickname" name="nickname" required>
        </div>
        <div class="form-group">
            <input type="file" class="form-control" placeholder="Profile photo" name="photo" required>
        </div>
        <#if error??>
            <h5>${error}</h5>
        </#if>
        <button type="submit" class="btn btn-secondary">Confirm</button>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>