<%--
  Created by IntelliJ IDEA.
  User: milinia
  Date: 08.11.2020
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../profile.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        input:invalid {
            border: 2px dashed red;
        }
        input:valid {
            border: 2px solid black;
        }
        a{
            color: black;
            float: right;
        }
        .header{
            width: 100%;
            height: 70px;
            background-color: #99BBF7;
        }
        .text{
            margin-top: 10px;
            margin-left: 43%;
            font-size: 30px;
            color: black;
            font-family: "cursive", Сomic Sans MS;
        }
        .subscriptionsField{
            width: 10%;
            height: 5%;
            margin-right: 100px;
        }
        .profile{
            float: left;
        }
        /*#button{*/
        /*    margin-top: 30px;*/
        /*    float: left;*/
        /*    margin-left: 86%;*/
        /*    margin-bottom: 23px;*/
        /*}*/
        .table.table-striped{
            margin-top: 30px;
        }
        .thead-dark{
            color: #173D80;
        }
        .btn {
            float: right;
            border: none;
            padding: 12px 16px;
            font-size: 25px;
            cursor: pointer;
            margin-top: 10px;
        }
        .delete{
            border: none;
            cursor: pointer;
            background-color: transparent;
            margin-left: 7%;
        }
        .row{
            height: 10%;
        }
        .sub{
            margin-right: auto;
        }
    </style>
</head>
<body>
<div class="header">
    <div class="row">
        <div class="col-sm-11">
            <div class="text">Subscriptions in one place</div>
        </div>
        <div class="profile">
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Profile
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <button type="button" class="btn btn-outline-dark" data-toggle="modal" data-target="#change_pass">
                        Change password
                    </button>
                    <button type="button" class="btn btn-outline-dark" data-toggle="modal" data-target="#deleteUser">
                        Delete my profile
                    </button>
                    <button type="button" class="btn btn-outline-dark" data-toggle="modal" data-target="#logout">
                       Logout
                    </button>
                </div>
            </div>

            <div class="modal fade" id="deleteUser" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" >Are you sure?</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-dark" data-dismiss="modal">No, I changed my mind</button>
                            <form method="post" action="delete">
                                <button type="submit">Yes, I'm sure</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="logout" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" >Are you sure?</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-dark" data-dismiss="modal">No, I changed my mind</button>
                            <form method="post" action="logout">
                                <button type="submit">Yes, I'm sure</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="change_pass" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" >Change password</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form name="pas" method="post" action="${pageContext.request.contextPath}/change_password">
                                <div class="form-group">
                                    <form method="post" action="${pageContext.request.contextPath}/change_password">
                                        <label for="newPassword">Enter new password</label>
                                        <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                                        <button type="submit">Save changes</button>
                                        <button type="button" data-dismiss="modal">Cancel</button>
                                    </form>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="subscriptionsField">
            <button type="button" class="btn btn-outline-dark" id = "button" data-toggle="modal" data-target="#addNewSub">
                Add new subscription </button>

            <div class="modal fade" id="addNewSub" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" >Creating new subscription</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form method="post" action="add_sub">
                                <div class="form-group">
                                    <label for="serviceName">Choose service</label>
                                    <select class="form-control" id="serviceName" name="service" >
                                        <option>Yandex+</option>
                                        <option>Apple Music</option>
                                        <option>Google Play Music</option>
                                        <option>Spotify</option>
                                        <option>VK Music</option>
                                        <option>Amediateka</option>
                                        <option>Netflix</option>
                                        <option>IVI</option>
                                        <option>Okko</option>
                                        <option>Wink</option>
                                        <option>Apple+</option>
                                        <option>YouTube Premium</option>
                                        <option>Lingualeo</option>
                                        <option>Duolingo</option>
                                        <option>Storytel</option>
                                        <option>Patreon</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="date">Choose the ending date</label>
                                    <input type="date" class="form-control" id="date" name="date" required>
                                </div>
                                <%--                            <div class="form-group">--%>
                                <%--                                <label for="comment">Write comment</label>--%>
                                <%--                                <textarea class="form-control" id="comment" rows="3" name="comment"></textarea>--%>
                                <%--                            </div>--%>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-dark" data-dismiss="modal">Cancel</button>
                                    <button type="submit" class="btn btn-outline-dark">Add</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
    <div>
        <table class="table table-striped">
            <thead class="black white-text">
            <tr>
                <th scope="col">Service name</th>
                <th scope="col">Ending date</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="<%= request.getAttribute("subscriptionList") %>" var="subscription">
                    <tr class="sub">
                        <td>${subscription.getServiceName()}</td>
                        <td>${subscription.getEndingDate()}</td>
<%--                        <td>${subscription.getComment()}</td>--%>
                        <td><form method="post" action="${pageContext.request.contextPath}
                        /delete_sub?numberOfSub=${subscription.getSubId()}">
                            <button type="submit" class="btn btn-outline-dark">Delete</button>
<%--                            <td><a type="button" class="delete" href="${pageContext.request.contextPath}--%>
<%--                        /delete_sub?numberOfSub=${subscription.getSubId()}"><i class="fa fa-trash"></i></a></td>--%>
                        </form></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
</body>
</html>
