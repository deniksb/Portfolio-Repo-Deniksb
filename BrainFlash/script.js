var text = document.getElementById("text-input").innerHTML;
var words = text.split(" ");

var pauseButton = document.getElementById("pause-button");
var startButton = document.getElementById("start-button");
var wordDisplay = document.getElementById("display-word");


var n = 0;
var myInterval = -1;

startButton.addEventListener("click", function(event){
    //if paused start
    if(myInterval == -1){
        startButton.innerHTML = "PAUSE";
        myInterval = setInterval(function(){
            wordDisplay.innerHTML = words[n];
            n++;
            
        },1000);
    }
    else {
        startButton.innerHTML = "START";
        clearInterval(myInterval);
        myInterval = -1;
    }
})













