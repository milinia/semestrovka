function sendExercise() {
    let kind = document.getElementById("kind").value
    let duration = document.getElementById("duration").value
    let id = document.getElementById('dayId').textContent

    const exerciseDto = {
        kind: kind,
        duration: duration,
        dayId: id
    };
    // console.log(JSON.stringify(exerciseDto))
    $.ajax({
        uri: '/add_exercise',
        method: 'post',
        contentType: 'application/json',
        data: JSON.stringify(exerciseDto),
        success: function (data) {
           addExerciseRow(data.kind, data.duration)
        },
        error: function () {
            alert("Please try again later!")
        }
    })
}
function addExerciseRow(kind, duration) {
    var tableRef = document.getElementById("table").getElementsByTagName('tbody')[0]
    var newRow = tableRef.insertRow()

    var newCell1 = newRow.insertCell();
    var newCell2 = newRow.insertCell();
    var newCell3 = newRow.insertCell();

    var newText1 = document.createTextNode(kind);
    var newText2 = document.createTextNode(duration);
    var newText3 = document.createTextNode('minutes');

    newCell1.appendChild(newText1);
    newCell2.appendChild(newText2)
    newCell3.appendChild(newText3)
}
function updateStat(protein, fiber, carbs) {
    document.getElementById("protein").value = protein;
    document.getElementById("fiber").value = fiber;
    document.getElementById("carbs").value = carbs;
}