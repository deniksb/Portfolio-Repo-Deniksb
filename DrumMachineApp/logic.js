
//audio objects
var audio = new Audio("kick1.wav");
var audioLayered = new Audio("openhat.wav");
var audioBasses = new Audio("808.wav");

audio.volume = 0.5;
audioBasses.volume = 0.5;
audioLayered.volume = 0.5;

//animation colors
var normalColor = "rgba(16, 52, 151, 0.589)";
var animateColor = "aliceblue";





//sound library
var soundArray =
    ["kick1.wav",
        "kick2.wav",
        "808.wav",
        "rim.wav",
        "snare.wav",
        "openhat.wav",
        "closedhat.wav",
        "crash.wav",
        "perc.wav"];

var soundArray2 =
    ["kick1_1.wav",
        "kick2_1.wav",
        "bass.wav",
        "tom.wav",
        "snare_1.wav",
        "openhat_1.wav",
        "closedhat_1.wav",
        "crash_1.wav",
        "perc_1.wav"];


var libraryOfChoice = soundArray;

//play function
function playSample(index, audioElement, library) {

    if (audio.paused) {
        audioElement.src = library[index];
        audioElement.play();
    }
    else {
        audioElement.pause();
        audioElement.currentTime = 0;
        audioElement.src = library[index];
        audioElement.play();
    }

}
//animate function
function animateButton(elementId, color) {
    document.getElementById(soundArray[elementId]).style.backgroundColor = color;
}
//animation and playing sounds on key press
window.onkeydown = function (event) {
    console.log(audio.volume);
    if (event.keyCode == 81) {
        playSample(0, audio, libraryOfChoice);
        animateButton(0, animateColor);
    }
    else if (event.keyCode == 87) {
        playSample(1, audio, libraryOfChoice);
        animateButton(1, animateColor);
    }
    else if (event.keyCode == 69) {
        playSample(2, audioBasses, libraryOfChoice);
        animateButton(2, animateColor);
    }
    else if (event.keyCode == 65) {
        playSample(3, audio, libraryOfChoice);
        animateButton(3, animateColor);
    }
    else if (event.keyCode == 83) {
        playSample(4, audio, libraryOfChoice);
        animateButton(4, animateColor);
    }
    else if (event.keyCode == 68) {
        playSample(5, audioLayered, libraryOfChoice);
        animateButton(5, animateColor);
    }
    else if (event.keyCode == 90) {
        playSample(6, audioLayered, libraryOfChoice);
        animateButton(6, animateColor);
    }
    else if (event.keyCode == 88) {
        playSample(7, audioLayered, libraryOfChoice);
        animateButton(7, animateColor);
    }
    else if (event.keyCode == 67) {
        playSample(8, audio, libraryOfChoice);
        animateButton(8, animateColor);
    }
}


//animation on key release
window.onkeyup = function (event) {
    if (event.keyCode == 81) {
        animateButton(0, normalColor);
    }
    else if (event.keyCode == 87) {
        animateButton(1, normalColor);
    }
    else if (event.keyCode == 69) {
        animateButton(2, normalColor);
    }
    else if (event.keyCode == 65) {
        animateButton(3, normalColor);
    }
    else if (event.keyCode == 83) {
        animateButton(4, normalColor);
    }
    else if (event.keyCode == 68) {
        animateButton(5, normalColor);
    }
    else if (event.keyCode == 90) {
        animateButton(6, normalColor);
    }
    else if (event.keyCode == 88) {
        animateButton(7, normalColor);
    }
    else if (event.keyCode == 67) {
        animateButton(8, normalColor);
    }
}

//toggle libraries function
//also changes colors 
//volume bar color switching is here too
function switchLibrary() {

    if (document.getElementById("checkbox").checked == true) {
        libraryOfChoice = soundArray2;
        document.getElementById("808.wav").innerHTML = "Bass";
        document.getElementById("rim.wav").innerHTML = "Tom";
        var allCells = document.getElementsByClassName("cell");

        //changing color of all cells
        for (let i = 0; i < allCells.length; i++) {
            allCells[i].style.backgroundColor = "mediumseagreen";
        }
    
        normalColor = "mediumseagreen";
        
    }
    else {
        libraryOfChoice = soundArray;
        document.getElementById("808.wav").innerHTML = "808";
        document.getElementById("rim.wav").innerHTML = "Rim";

        var allCells = document.getElementsByClassName("cell");
        for (let i = 0; i < allCells.length; i++) {
            allCells[i].style.backgroundColor = "rgba(16, 52, 151, 0.589)";
        }
        normalColor = "rgba(16, 52, 151, 0.589)";

    }

}

//volume function
window.SetVolume = function(val)
{
    newVolume = val/100;
    audio.volume = newVolume;
    audioBasses.volume = newVolume;
    audioLayered.volume = newVolume;

}

