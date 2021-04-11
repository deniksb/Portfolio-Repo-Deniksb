var character = document.getElementById("character");
var block = document.getElementById("block");
var blockText = document.getElementById("blocktext");
var coin = document.getElementById("coin");
var flyingBlock = document.getElementById("flying-block");
var points = 0;

var speed = 3;
var coins = parseInt(localStorage.getItem("Coins"));

var jumpAnimation = "url(player_red_air.gif)";
var skateAnimation = "url(deni_push_red.gif)";




//function that shows local storage
function saveScore() {

    console.log(localStorage);
}

//function for catching the coin

var checkCoinCatch = setInterval(function () {
    var characterTop = parseInt(window.getComputedStyle(character).getPropertyValue("top"));
    var coinLeft = parseInt(window.getComputedStyle(coin).getPropertyValue("left"));
    var flyingBlockLeft = parseInt(window.getComputedStyle(flyingBlock).getPropertyValue("left"));

    if (coinLeft < 40 && coinLeft > 0 && characterTop <= 290) {

        coin.style.display = "none";
        if (Number.isInteger(coins)) {
            coins++;
        }
        else {
            localStorage.setItem("Coins", "1");
            coins = parseInt(localStorage.getItem("Coins"));
        }



    }

    if (coinLeft <= -5) {
        coin.style.display = "none";


    }
    if (flyingBlockLeft <= -5) {
        flyingBlock.style.display = "none";

    }


}, 10);


//check point
document.addEventListener("click", function () {
    var characterTop = parseInt(window.getComputedStyle(character).getPropertyValue("top"));
    var blockLeft = parseInt(window.getComputedStyle(block).getPropertyValue("left"));
    var flyingBlockLeft = parseInt(window.getComputedStyle(flyingBlock).getPropertyValue("left"));

    var pointMeter = document.getElementById("points");

    if ((blockLeft < 150 && blockLeft > 0)) {
        ++points;
        addedPoint = true;

        pointMeter.innerHTML = points;

    }
    else if(blockLeft > 0&& blockLeft < 400 && speed < 2.1){
        ++points;
        addedPoint = true;
        
        pointMeter.innerHTML = points;
    }


});




// var addedPoint = false; //variable that goes true when a point is added so it can wait a bit until another one is added
// var waitTime = 200;
// var checkPoint = setInterval(function () {
//     var characterTop = parseInt(window.getComputedStyle(character).getPropertyValue("top"));
//     var blockLeft = parseInt(window.getComputedStyle(block).getPropertyValue("left"));

//     var pointMeter = document.getElementById("points");

//     if (blockLeft < 40 && blockLeft > 0 && characterTop < 260 && addedPoint == false) {
//         ++points;
//         addedPoint = true;

//         pointMeter.innerHTML = points;

//     }


// }, 50);

// var subtracted = false;
// var enablePoint = setInterval(function () {

//     if(addedPoint == true && points > 0){
//         addedPoint = false;
//         console.log(waitTime);
//     }
//     if(subtracted == false && speed > 1.5 && speed < 2){
//         addedPoint = false;
//         waitTime = waitTime - 120;
//         subtracted = true;
//         console.log(waitTime);
//     }

// },waitTime);


//function to get random number in range from 0 to max-1
function getRandomInt(max) {
    return Math.floor(Math.random() * Math.floor(max)) + 1;
}

//function that checks if ur dead every 10 ms and ends game when that happens
var checkDead = setInterval(function () {
    var characterTop = parseInt(window.getComputedStyle(character).getPropertyValue("top"));
    var blockLeft = parseInt(window.getComputedStyle(block).getPropertyValue("left"));
    var flyingBlockLeft = parseInt(window.getComputedStyle(flyingBlock).getPropertyValue("left"));

    if ((blockLeft < 40 && blockLeft > 0 && characterTop >= 290) || (flyingBlockLeft < 40 && flyingBlockLeft > 0 && characterTop <= 210)) {

        // block.style.animation = "none";
        // block.style.display = "none";
        // blockText.style.animation = "none";
        // blockText.style.display = "none";

        //check if score is highest score yet and sets it if necessary
        var currentMaxScore = parseInt(localStorage.getItem("maxScore"));

        if (currentMaxScore < points) {

            localStorage.setItem("maxScore", points.toString());
        }

        localStorage.setItem("Coins", coins.toString());

        // alert("Your score is: " + points + " Coins collected: " + coins);
        document.getElementById("game").style.display = "none";
        document.getElementById("death-score").style.display = "block";
        document.getElementById("death-score-points").innerHTML = points;



        // startMenu();

    }

    if (blockLeft <= -5) {
        block.style.display = "none";
        blockText.style.display = "none";
        console.log(speed);


    }
    else if (blockLeft > 0 && block.style.display == "none") {
        block.style.display = "block";
        blockText.style.display = "block";
    }



}, 10);


document.addEventListener("click", function () {
    if (!(character.classList.contains("animate"))) {
        character.style.content = jumpAnimation;
        character.classList.add("animate");
    }


    setTimeout(function () {
        character.classList.remove("animate");
        character.style.content = skateAnimation;
    }, 500);


});

document.body.onkeyup = function jump(e) {

    if (e.keyCode == 32) {
        if (character.classList != "animate") {
            character.style.content = jumpAnimation;
            character.classList.add("animate");
        }

        setTimeout(function () {
            character.classList.remove("animate");
            character.style.content = skateAnimation;
        }, 500);
    }


}

//countdown from 5 on block so player can know when the block is about to teleport forward
function blockTeleportTimer() {

    var timer = 5;
    setInterval(function () {
        if (timer != 1) {
            blockText.innerHTML = timer;
            timer--;
        }
         else {
            blockText.innerHTML = timer;
            timer = 5;
        }


    }, 1000)

}

//function for changing the block sprite
function changeBlockSprite() {
    var randomNum = getRandomInt(2);
    block.style.content = "url(block" + randomNum + ".png)";
}




//getting text speed
var textSpeed = 3;
//how much we are going to subtract with every obstacle to make it faster
var difficultyIndex;
var makeFaster = setInterval(function () {
    //making text speed faster
    textSpeed = textSpeed - difficultyIndex;
    blockText.style.animationDuration = textSpeed + "s";
    //making block speed faster
    speed = speed - difficultyIndex;
    block.style.animationDuration = speed + "s";
    blockTeleportTimer();



}, 5000);

//function that opens menu
function startMenu() {
    document.getElementById("menu").style.display = "block";
    document.getElementById("game").style.display = "none";
    document.getElementById("shop").style.display = "none";
    document.getElementById("rules").style.display = "none";
    document.getElementById("credits").style.display = "none";
    document.getElementById("death-score").style.display = "none";
    document.getElementById("difficulty").style.display = "none";
}

//function that opens rules
function startRules() {
    document.getElementById("menu").style.display = "none";
    document.getElementById("rules").style.display = "block";
}

//function to equip skin

function equipSkin(ele) {
    var id = ele.id;
    if (localStorage.getItem(id) == "true" || id == "0") {
        var storageName = id + " Equipped";
        localStorage.setItem(storageName, "true");
        if (id == "50") {


            localStorage.setItem("0 Equipped", "false");
            localStorage.setItem("500 Equipped", "false");
            localStorage.setItem("2000 Equipped", "false");

            alert("Blonde Deni equipped!");
        }
        else if (id == "500") {


            localStorage.setItem("0 Equipped", "false");
            localStorage.setItem("50 Equipped", "false");
            localStorage.setItem("2000 Equipped", "false");

            alert("Ginger Deni equipped!");
        }
        else if (id == "2000") {


            localStorage.setItem("0 Equipped", "false");
            localStorage.setItem("50 Equipped", "false");
            localStorage.setItem("500 Equipped", "false");

            alert("Dark Deni equipped!");
        }
        else if (id == "0") {


            localStorage.setItem("2000 Equipped", "false");
            localStorage.setItem("50 Equipped", "false");
            localStorage.setItem("500 Equipped", "false");

            alert("Red Deni equipped!");

        }
    }

}

//function that lets player choose difficulty
function choseDifficulty() {

    //getting the highest scores
    var easyScore = localStorage.getItem("easyScore");
    var medScore = localStorage.getItem("medScore");
    var hardScore = localStorage.getItem("hardScore");

    var easyButt = document.getElementById("easy-button").innerHTML;
    var normButt = document.getElementById("normal-button").innerHTML;
    var hardButt = document.getElementById("hard-button").innerHTML;

    var val1 = easyButt.split(" ");
    var val2 = easyButt.split(" ");
    var val3 = easyButt.split(" ");

    easyButt.replace(val1[1],easyScore);
    normButt.replace(val2[1],medScore);
    hardButt.replace(val3[1],hardScore);

    document.getElementById("easy-button").innerHTML = easyButt;
    document.getElementById("normal-button").innerHTML = normButt;
    document.getElementById("hard-button").innerHTML = hardButt;


    document.getElementById("menu").style.display = "none";
    document.getElementById("difficulty").style.display = "block";
}


//function that makes the game start

function startGame(ele) {

    if (ele.id == "easy-button") {
        difficultyIndex = 0.01;
    }
    else if (ele.id == "normal-button") {
        difficultyIndex = 0.05;
    }
    else if (ele.id == "hard-button") {
        difficultyIndex = 0.1;
    }



    if (localStorage.getItem("0 Equipped") == "true") {
        skateAnimation = "url(deni_push_red.gif)";
        character.style.content = skateAnimation;
        jumpAnimation = "url(player_red_air.png)";
        document.getElementById("character").style.height = "100px";
        document.getElementById("character").style.width = "100px";
    }
    else if (localStorage.getItem("50 Equipped") == "true") {
        skateAnimation = "url(player_blonde_push.gif)";
        character.style.content = skateAnimation;
        jumpAnimation = "url(player_blonde_air.png)";
        document.getElementById("character").style.height = "95px";
        document.getElementById("character").style.width = "100px";

    }
    else if (localStorage.getItem("500 Equipped") == "true") {
        skateAnimation = "url(deni_push_orange.gif)";
        character.style.content = skateAnimation;
        jumpAnimation = "url(player_orange_air.png)";
        document.getElementById("character").style.height = "95px";
        document.getElementById("character").style.width = "100px";

    }
    else if (localStorage.getItem("2000 Equipped") == "true") {
        skateAnimation = "url(deni_push_black.gif)";
        character.style.content = skateAnimation;
        jumpAnimation = "url(player_black_air.png)";
        document.getElementById("character").style.height = "95px";
        document.getElementById("character").style.width = "100px";
    }

    points = 0;
    speed = 3;
    textSpeed = 3;
    block.style.animationDuration = speed + "s";
    blockText.style.animationDuration = textSpeed + "s";
    document.getElementById("points").innerHTML = points;
    document.getElementById("game").style.display = "block";
    document.getElementById("menu").style.display = "none";
    document.getElementById("difficulty").style.display = "none";

}


//function that opens up the credits

function startCredits() {
    document.getElementById("credits").style.display = "block";
    document.getElementById("menu").style.display = "none";

}

//function tha opens up shop
function startShop() {
    if (localStorage.getItem("50") == "true") {
        document.getElementById("50").style.opacity = "100%";
    }
    if (localStorage.getItem("500") == "true") {
        document.getElementById("500").style.opacity = "100%";
    }
    if (localStorage.getItem("2000") == "true") {
        document.getElementById("2000").style.opacity = "100%";
    }
    document.getElementById("coin-display").innerHTML = coins;
    document.getElementById("shop").style.display = "block";
    document.getElementById("menu").style.display = "none";


}

//stopping the coin
function stopCoin(duration) {
    setTimeout(function () {
        coin.style.display = "none"
    }, duration);
}


//function for calling the coin
var intervalId = window.setInterval(function () {
    if (flyingBlock.style.display == "none") {
        var randomSpeed = getRandomInt(3);
        coin.style.animationDuration = randomSpeed + "s";
        coin.style.display = "block";
        stopCoin(randomSpeed * 1000);
    }
    else {
        var randomSpeed = getRandomInt(3);
        coin.style.animationDuration = randomSpeed + "s";
        coin.style.display = "block";
        stopCoin(randomSpeed * 0);
    }
}, 10000);



var flyingBlock = document.getElementById("flying-block");
//stopping the flying block
function stopFlying(duration) {
    setTimeout(function () {
        flyingBlock.style.display = "none"
    }, duration);
}


//function for calling the flying block
var intervalIdBlock = window.setInterval(function () {
    var blockLeft = parseInt(window.getComputedStyle(block).getPropertyValue("left"));
    if (coin.style.display == "none" && blockLeft > 430) {
        var randomSpeed = Math.random() * (1500 - 500) + 500;
        flyingBlock.style.animationDuration = randomSpeed + "ms";
        flyingBlock.style.display = "block";
        stopFlying(randomSpeed);
    }

}, 6000);








//function to buy skin

function buySkin(ele) {
    var id = ele.id;
    if (parseInt(id) <= coins && localStorage.getItem(id) != "true") {
        document.getElementById(id).style.opacity = "100%";
        localStorage.setItem(id, "true");
        coins = coins - parseInt(id);
        document.getElementById("coin-display").innerHTML = coins;
        localStorage.setItem("Coins", coins.toString());
    }

}


//function that changes the song
var timesSongChanged = 0;
function changeSong() {
    if (timesSongChanged % 2 == 0 && timesSongChanged != 0) {
        document.getElementById("music").pause();
        ++timesSongChanged;
        document.getElementById("start-music-btn").style.backgroundColor = "grey";
    }
    else if (document.getElementById("music").getAttribute('src') == 'cigarette_smoker.mp3') {
        ++timesSongChanged;
        document.getElementById("music").setAttribute('src', 'neighbourhood.mp3');
        document.getElementById("music").volume = 0.3;
        document.getElementById("start-music-btn").style.backgroundColor = "firebrick";
    }
    else {
        ++timesSongChanged;
        document.getElementById("music").setAttribute('src', 'cigarette_smoker.mp3');
        document.getElementById("music").volume = 0.3;
        document.getElementById("start-music-btn").style.backgroundColor = "blue";
    }

}


function reverseMode() {
    let game = document.getElementById("game").classList;
    if (game.contains('flip-horizontal')) {
        game.remove('flip-horizontal');
        document.getElementById("points").classList.remove('flip-horizontal');
        document.getElementById("blocktext").classList.remove('flip-horizontal');
        document.getElementById("rev-butt").style.backgroundColor = "rgb(64, 102, 21)";
        document.getElementById("rev-butt").innerHTML = "NORMAL";
    }
    else {
        game.add('flip-horizontal');
        document.getElementById("points").classList.add('flip-horizontal');
        document.getElementById("blocktext").classList.add('flip-horizontal');
        document.getElementById("rev-butt").style.backgroundColor = "rgb(97, 175, 33)";
        document.getElementById("rev-butt").innerHTML = "REVERSED";
    }

}

//set high scores
window.onload(function() {
var easyScore = localStorage.getItem("easyScore");
var medScore = localStorage.getItem("medScore");
var hardScore = localStorage.getItem("hardScore");




});


