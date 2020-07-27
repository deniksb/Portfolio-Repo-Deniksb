var points = 0;
//adds points to the points variable whenever a correct answer is clicked
function correctAnswer() {
    points++;
};
//keeps track of the question number and changes between scenes
function checkPoints(ele) {
    var id = ele.id;
    var nextId = parseInt(id) + 1;
    var stringDivId1 = "#part-" + id;
    var stringDivId2 = "#part-" + nextId;
    $(stringDivId1).hide();
    if (id < 5) {   //should be 5

        $(stringDivId2).show();

    }
    else {
        if (points == 5) {
            $("#points-display").html("You got " + points + " questions right!\nConcratulations! You win!");
        }
        else if (points == 1) {
            $("#points-display").html("You got " + points + " question right!\nNice try!");
        }
        else {
            $("#points-display").html("You got " + points + " questions right!\nNice try!");
        }
    }
};
//start button on click starts the quiz
$("#start-button").click(function () {

    $("#part-0").hide();
    $("#part-1").show();

});


