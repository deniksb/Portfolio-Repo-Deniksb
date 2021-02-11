var character = document.getElementById("character");
var block = document.getElementById("block");
var blockText = document.getElementById("blocktext");
var coin = document.getElementById("coin");
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


    if (coinLeft < 40 && coinLeft > 0 && characterTop <= 290) {

        coin.style.display = "none";
        if(Number.isInteger(coins)){
            coins++;
        }
        else {
            localStorage.setItem("Coins","1");
            coins = parseInt(localStorage.getItem("Coins"));
        }
        
        
       
    }



}, 10);

var checkPoint = setInterval(function () {
    var characterTop = parseInt(window.getComputedStyle(character).getPropertyValue("top"));
    var blockLeft = parseInt(window.getComputedStyle(block).getPropertyValue("left"));

    var pointMeter = document.getElementById("points");

    if (blockLeft < 40 && blockLeft > 0 && characterTop < 260) {
        ++points;
        pointMeter.innerHTML = points;

    }




}, 100);


//function to get random number in range from 0 to max-1
function getRandomInt(max) {
    return Math.floor(Math.random() * Math.floor(max)) + 1;
}

//function that checks if ur dead every 10 ms and ends game when that happens
var checkDead = setInterval(function () {
    var characterTop = parseInt(window.getComputedStyle(character).getPropertyValue("top"));
    var blockLeft = parseInt(window.getComputedStyle(block).getPropertyValue("left"));


    if (blockLeft < 40 && blockLeft > 0 && characterTop >= 290) {

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

        alert("Your score is: " + points + " Coins collected: " + coins);
        startMenu();
        document.getElementById("music").pause();
    }



}, 10);




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
        } else {
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
var makeFaster = setInterval(function () {
    //making text speed faster
    textSpeed = textSpeed - 0.01;
    blockText.style.animationDuration = textSpeed + "s";
    //making block speed faster
    speed = speed - 0.01;
    block.style.animationDuration = speed + "s";
    blockTeleportTimer();




}, 5000);

//function that opens menu
function startMenu(){
    document.getElementById("menu").style.display = "block";
    document.getElementById("game").style.display = "none";
}

//function to equip skin

function equipSkin(ele){
    var id = ele.id;
    var storageName = id + " Equipped";
    localStorage.setItem(storageName,"true");
    if(id == "50"){
        

        localStorage.setItem("0 Equipped","false");
        localStorage.setItem("500 Equipped","false");
        localStorage.setItem("2000 Equipped","false");

        alert("Blonde Deni equipped!");
    }
    else if(id == "500"){
        

        localStorage.setItem("0 Equipped","false");
        localStorage.setItem("50 Equipped","false");
        localStorage.setItem("2000 Equipped","false");

        alert("Ginger Deni equipped!");
    }
    else if(id == "2000"){
        

        localStorage.setItem("0 Equipped","false");
        localStorage.setItem("50 Equipped","false");
        localStorage.setItem("500 Equipped","false");

        alert("Dark Deni equipped!");
    }
    else if(id == "0"){
        

        localStorage.setItem("2000 Equipped","false");
        localStorage.setItem("50 Equipped","false");
        localStorage.setItem("500 Equipped","false");

        alert("Red Deni equipped!");

    }


}


//function that makes the game start

function startGame() {
    
    if(localStorage.getItem("0 Equipped") == "true"){
        skateAnimation = "url(deni_push_red.gif)";
        character.style.content = skateAnimation;
        jumpAnimation = "url(player_red_air.gif)";
    }
    else if(localStorage.getItem("50 Equipped") == "true"){
        skateAnimation = "url(player_blonde_push.gif)";
        character.style.content = skateAnimation;
        jumpAnimation = "url(player_blonde_air.gif)";

    }
    else if(localStorage.getItem("500 Equipped") == "true"){
        skateAnimation = "url(deni_push_orange.gif)";
        character.style.content = skateAnimation;
        jumpAnimation = "url(player_orange_air.gif)";

    }
    else if(localStorage.getItem("2000 Equipped") == "true"){
        skateAnimation = "url(deni_push_black.gif)";
        character.style.content = skateAnimation;
        jumpAnimation = "url(player_black_air.gif)";
    }
    

    document.getElementById("game").style.display = "block";
    document.getElementById("menu").style.display = "none";
    document.getElementById("music").volume = 0.3;
    document.getElementById("music").play();

}


//function that opens up the credits

function startCredits() {
    document.getElementById("credits").style.display = "block";
    document.getElementById("menu").style.display = "none";

}

//function tha opens up shop
function startShop() {
    if(localStorage.getItem("50") == "true"){
        document.getElementById("50").style.opacity = "100%";
    }
    if(localStorage.getItem("500") == "true"){
        document.getElementById("500").style.opacity = "100%";
    }
    if(localStorage.getItem("2000") == "true"){
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
    var randomSpeed = getRandomInt(3);
    coin.style.animationDuration = randomSpeed + "s";
    coin.style.display = "block";
    stopCoin(randomSpeed * 1000);
}, 10000);

//function to buy skin

function buySkin(ele){
    var id = ele.id;
    if(parseInt(id) <= coins && localStorage.getItem(id) != "true"){
       document.getElementById(id).style.opacity = "100%";
       localStorage.setItem(id,"true");
       coins = coins - parseInt(id);
       document.getElementById("coin-display").innerHTML = coins;
    }
    
}


