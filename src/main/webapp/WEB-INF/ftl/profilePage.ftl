<!DOCTYPE html>
<html lang="">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <title>Food tracking</title>
    <script src="/resources/ajax.js"></script>
    <style>
        .mainHeader{
            background-color: #f3a79e;
            height: 80px;
            width: 100%;
        }
        .links{
            margin-right: 10%;
        }
        .all{
            text-align: center;
        }
        .statistics{
            margin-left: 40%;
        }
        .day-table{
            width: 50%;
            margin-left: 25%;
            margin-top: 1%;
        }
        .adding-buttons{

        }
        .day{
            width: 15%;
            margin-left: 3%;
            margin-top: 1%;
        }
        a{text-decoration: none;color: aliceblue;}
        a:visited {text-decoration: none;color: aliceblue;}
        a:hover { text-decoration: none; color: aliceblue; }
        a:focus { text-decoration: none; color: aliceblue; }
        a:active { text-decoration: none; color: aliceblue; }
    </style>
</head>
<body>
<div class="mainHeader">
    <img src="/resources/logo.jpeg" width="220" height="80">
    <div class="links">
        <a href="/chats">Chats</a>
        <a href="/logout">Log out</a>
    </div>
</div>
<div class="all">
    <div class="day">
        <div class="btn-group" role="group" aria-label="Basic mixed styles example">
            <button type="button" class="btn btn-success"><a href="/profile/${day.date.minusDays(1)}" id="back"><</a></button>
            <button type="button" class="btn btn-success">${day.date}</button>
            <label hidden id="dayId">${day.id}</label>
            <button type="button" class="btn btn-success"><a href="/profile/${day.date.plusDays(1)}" id="forward">></a></button>
        </div>
    </div>
    <div class="adding-buttons">
        <button type="button" class="btn btn-outline-success" data-bs-toggle="modal" data-bs-target="#addingFood">Add food</button>
        <button type="button" class="btn btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#addingSport">Add exercise</button>

        <!-- Modal for food-->
        <div class="modal fade" id="addingFood" tabindex="-1" aria-labelledby="addingFoodLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addingFoodLabel">Choose food</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <fieldset>
                            <input type="text" class="form-control" placeholder="Food" name="food" required>
                            <input type="number" class="form-control" placeholder="Amount in grams" name="amount" required>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-success" onclick="sendProduct()">Add</button>
                        </fieldset>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal for sport-->
    <div class="modal fade" id="addingSport" tabindex="-1" aria-labelledby="addingSportLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addingFoodLabel">Choose exercise</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <fieldset>
                        <select class="form-select" aria-label="Default select example" name="kind" id="kind" required>
                            <option selected>Choose activity kind</option>
                            <#list exercises as exercise>
                                <option value="${exercise.id}">${exercise.kind}</option>
                            </#list>
                        </select>
                        <input type="text" class="form-control" placeholder="Duration in min" name="duration" id="duration" required pattern="[0-9]{0-3}">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary" onclick="sendExercise()">Add</button>
                    </fieldset>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="day-table">
    <table class="table table-striped" id="table">
        <thead>
            <tr>
                <td>Description</td>
                <td>Amount</td>
                <td>Unit</td>
            </tr>
        </thead>
        <tbody>
<#--        <#list foodList as food>-->
<#--            <tr>-->
<#--                <td>-->
<#--                    <img src="https://img.icons8.com/ultraviolet/344/apple.png" width="20" height="20">-->
<#--                    ${}-->
<#--                </td>-->
<#--                <td>${}</td>-->
<#--                <td>grams</td>-->
<#--            </tr>-->
<#--        </#list>-->
        <#if exerciseList??>
            <#list exerciseList as exerise>
                <tr>
                    <td>
                        <img src="https://img.icons8.com/ultraviolet/344/running.png" width="20" height="20">
                        ${exerise.exercise.kind}
                    </td>
                    <td>${exerise.duration}</td>
                    <td>minutes</td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</div>
<div class="statistics">
<#--    <label for="protein">Protein:</label>-->
<#--    <progress id="protein" value="${day.protein}" max="${day.user.weight * 0.75}"></progress>-->
<#--    <label for="fiber">Fiber:</label>-->
<#--    <progress id="fiber" value="${day.protein}" max="${day.user.weight * 0.8}"></progress>-->
<#--    <label for="carbs">Carbs:</label>-->
<#--    <progress id="carbs" value="${day.carbs}" max="${day.user.weight * 4}"></progress>-->
    <div id="donutchart" style="width: 500px; height:200px;"></div>
</div>
<footer>

</footer>
</body>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
    google.charts.load("current", {packages:["corechart"]});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Type', 'Amount'],
            ['Calories', ${day.calories}],
            ['Burned calories', ${day.burnedCalories}],
        ]);

        var options = {
            pieHole: 0.4,
            colors: ['pink', 'gray']
        };

        var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
        chart.draw(data, options);
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</html>