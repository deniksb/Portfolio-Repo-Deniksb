
//audio objects
var audio = new Audio("kick1.wav");
var audioLayered = new Audio("808.wav");

//animation colors
var normalColor = "rgba(16, 52, 151, 0.589)";
var animateColor = "blue";

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

//play function
function playSample(index, audioElement) {

    if (audio.paused) {
        audioElement.src = soundArray[index];
        audioElement.play();
    }
    else {
        audioElement.pause();
        audioElement.currentTime = 0;
        audioElement.src = soundArray[index];
        audioElement.play();
    }

}
//animate function
function animateButton(elementId, color) {
    document.getElementById(soundArray[elementId]).style.backgroundColor = color;
}
//animation and playing sounds on key press
window.onkeydown = function (event) {
    if (event.keyCode == 81) {
        playSample(0, audio);
        animateButton(0, animateColor);
    }
    else if (event.keyCode == 87) {
        playSample(1, audio);
        animateButton(1, animateColor);
    }
    else if (event.keyCode == 69) {
        playSample(2, audioLayered);
        animateButton(2, animateColor);
    }
    else if (event.keyCode == 65) {
        playSample(3, audio);
        animateButton(3, animateColor);
    }
    else if (event.keyCode == 83) {
        playSample(4, audio);
        animateButton(4, animateColor);
    }
    else if (event.keyCode == 68) {
        playSample(5, audioLayered);
        animateButton(5, animateColor);
    }
    else if (event.keyCode == 90) {
        playSample(6, audioLayered);
        animateButton(6, animateColor);
    }
    else if (event.keyCode == 88) {
        playSample(7, audioLayered);
        animateButton(7, animateColor);
    }
    else if (event.keyCode == 67) {
        playSample(8, audio);
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

