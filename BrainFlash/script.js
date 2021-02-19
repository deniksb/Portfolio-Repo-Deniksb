var words;
var pauseButton = document.getElementById("pause-button");
var startButton = document.getElementById("start-button");
var wordDisplay = document.getElementById("display-word");
var secondaryWordDisplay = document.getElementById("secondary-display-word");

var speed = 750;

var n = 0;
var myInterval = -1;

startButton.addEventListener("click", function (event) {
    //if paused start

    if (myInterval == -1) {
        document.getElementById("intro-screen").style.display = "none";
        document.getElementById("main-screen").style.display = "block";
        startButton.innerHTML = "PAUSE";

        //slider edits
        
        sliderNav.max = words.length;
        

        myInterval = setInterval(function () {
            if (n > 0) {

                secondaryWordDisplay.innerHTML = words[n - 1];
            }
            if (words[n] == undefined) {
                clearInterval(myInterval);
            } else {
                wordDisplay.innerHTML = words[n];
                outputNav.innerHTML = n+1;
                sliderNav.value = n+1;
                n++;
            }


        }, speed);
    } else {
        startButton.innerHTML = "START";
        clearInterval(myInterval);
        myInterval = -1;
    }
});




//read txt files stuff

let input = document.getElementById("text-input");
input.focus();
input.select();


input.addEventListener('change', function () {

    let files = input.files;

    if (files.length == "0") {

        return;
    }
    const file = files[0];

    let reader = new FileReader();

    
    


    reader.onload = (e) => {

        const file = e.target.result;
        const lines = file.split(/\s+/);
        words = lines;

    };

    reader.onerror = (e) => alert(e.target.error.name);

    reader.readAsText(file);

    startButton.disabled = false;




});

//slider speed code

var slider = document.getElementById("myRange");
var output = document.getElementById("slider-speed");
output.innerHTML = slider.value * 15; // Display the default slider value

// Update the current slider value (each time you drag the slider handle)
slider.oninput = function () {
    clearInterval(myInterval);
    myInterval = -1;
    speed = slider.value * 15;
    output.innerHTML = slider.value * 15;
    startButton.click();
}



//slider navigation code

var sliderNav = document.getElementById("navigation-range");
var outputNav = document.getElementById("slider-nav"); 

// Update the current slider value (each time you drag the slider handle)
sliderNav.oninput = function () {
    clearInterval(myInterval);
    myInterval = -1;
    n = sliderNav.value-1;
    
    startButton.click();
}