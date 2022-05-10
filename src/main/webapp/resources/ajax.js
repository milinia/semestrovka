function sendExercise() {
    let kind = document.getElementById("kind").value
    let duration = document.getElementById("duration").value
    let id = document.getElementById('dayId').textContent

    const exerciseDto = {
        kind: kind,
        duration: duration,
        dayId: id
    };
    $.ajax({
        url: '/add_exercise',
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
function sendProduct() {
    let product = document.getElementById("food").value
    let amount = document.getElementById("amount").value

    const productDto = {
        product: product,
        amount: amount,
    };
    $.ajax({
        url: '/add_product',
        method: 'post',
        contentType: 'application/json',
        data: JSON.stringify(productDto),
        success: function (data) {
            addProductRow(data.product, data.amount)
            updateStat(data.protein, data.fiber, data.carbs)
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
    var img = document.createElement('img');
    img.src = "https://img.icons8.com/ultraviolet/344/running.png";
    img.width = 20;
    img.height = 20;

    newCell1.appendChild(img);
    newCell1.appendChild(newText1);
    newCell2.appendChild(newText2)
    newCell3.appendChild(newText3)
}
function addProductRow(product, amount) {
    var tableRef = document.getElementById("table").getElementsByTagName('tbody')[0]
    var newRow = tableRef.insertRow()

    var newCell1 = newRow.insertCell();
    var newCell2 = newRow.insertCell();
    var newCell3 = newRow.insertCell();

    var newText1 = document.createTextNode(product);
    var newText2 = document.createTextNode(amount);
    var newText3 = document.createTextNode('grams');
    var img = document.createElement('img');
    img.src = "https://img.icons8.com/ultraviolet/344/apple.png";
    img.width = 20;
    img.height = 20;

    newCell1.appendChild(img);
    newCell1.appendChild(newText1);
    newCell2.appendChild(newText2)
    newCell3.appendChild(newText3)
}
function updateStat(protein, fiber, carbs) {
    document.getElementById("protein").value = protein;
    document.getElementById("fiber").value = fiber;
    document.getElementById("carbs").value = carbs;
}