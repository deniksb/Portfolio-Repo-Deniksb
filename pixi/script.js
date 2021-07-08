//VARIALBE DECLARATIONS
let app;

let player;
let score;
let scoreTimer;
let scoreLabel;

let enemyFly;
let enemyGroundArr = [];
let enemyGroundTimer = 50;

let bulletArr = [];

let highScore;


//bg variables
let bgBack;
let bgMidBack;
let bgX = 0;
let bgSpeed = 1;

//function to spawn ground enemies 
function spawnGroundEnemy() {
    let enemyGround = new PIXI.Sprite.from("tariket1.png");
    enemyGround.anchor.set(0.5);
    enemyGround.x = 1100;
    enemyGround.y = 370;
    app.stage.addChild(enemyGround);
    enemyGroundArr.push(enemyGround);
}

//function to spawn bullets from ground enemies
function spawnBullet() {
    let randomNum = Math.floor(Math.random() * 3);
    if (enemyGroundArr[randomNum] != undefined) {
        let bullet = new PIXI.Sprite.from("bullet.png");
        bullet.anchor.set(0.5);
        bullet.x = enemyGroundArr[randomNum].x;
        bullet.y = 370;
        app.stage.addChild(bullet);
        bulletArr.push(bullet);
    }

}



//MAP WITH ALL KEYS/CONTROLLS
let keys = new Map();
keys.set('87', false); //w
keys.set('83', false); //s
keys.set('65', false); //a
keys.set('68', false); //d

//MAIN FUNCTION THAT RUNS THE GAME
window.onload = function () {
    app = new PIXI.Application(
        {
            width: 1000,
            height: 400,
        }

    );
    //checking if high score is registered in localstorage
    if (localStorage.getItem('HighScore') == "undefined") {
        localStorage.setItem('HighScore', '0');
    }
    highScore = localStorage.getItem('HighScore');




    //setting score and timer in the beginning and displaying it
    score = 0;
    scoreTimer = 150;
    scoreLabel = new PIXI.Text('Score: ' + score, { fontFamily: 'Lucida Console', fontSize: 24, fill: 0xF5F5F5, align: 'center' });
    scoreLabel.x = 0;
    scoreLabel.y = 0;
    scoreLabel.depth = 1000;

    document.body.appendChild(app.view);

    //CREATING THE PLAYER
    player = new PIXI.Sprite.from("plane.png");
    player.anchor.set(0.5);
    player.x = 30;
    player.y = app.view.height / 2;

    //PLAYER CONTROLS
    app.stage.interactive = true;
    window.addEventListener("keydown", keyDown);
    window.addEventListener("keyup", keyUp);

    //movement of the player
    function keyDown(e) {

        keys.set(JSON.stringify(e.keyCode), true);

    }
    function keyUp(e) {

        keys.set(JSON.stringify(e.keyCode), false);
    }


    //CREATING THE FLYING ENEMIES
    let flyingEnemyTimer = 500;
    enemyFly = new PIXI.Sprite.from('tariket3.png');
    enemyFly.anchor.set(0.5);


    //SETTING THE MOVING BACKGROUND
    app.loader.baseUrl = "bg";
    app.loader
        .add("bgBack", "clouds1.png")
        .add("bgMidBack", "ground&houses2.png")
    app.loader.onComplete.add(initLevel);
    app.loader.load();

    //initializing the bg
    function initLevel() {
        bgBack = createBg(app.loader.resources["bgBack"].texture);
        bgMidBack = createBg(app.loader.resources["bgMidBack"].texture);
        app.ticker.add(gameLoop);
    }

    //creating the bg
    function createBg(texture) {
        let tiling = new PIXI.TilingSprite(texture, 1000, 400);
        tiling.position.set(0, 0);
        app.stage.addChild(tiling);

        return tiling;
    }
    //moving the bg
    function updateBg() {
        bgX = (bgX + bgSpeed);
        bgMidBack.tilePosition.x = bgX / 2;
        bgBack.tilePosition.x = bgX / 4;
    }



    //MAIN GAME LOOP FUNCTION
    function gameLoop() {
        //adding the score label
        app.stage.addChild(scoreLabel);
        //adding the player
        app.stage.addChild(player);
        //moving the background
        updateBg();

        //CONTROLLING THE PLAYER AND MAKING SURE THEY DO NOT GO OUT OF THE SCREEN
        if (keys.get('87') == true && player.y > 20) {
            player.y -= 4;
        }
        if (keys.get('83') == true && player.y < 380) {
            player.y += 4;
        }
        if (keys.get('65') == true && player.x > 20) {
            player.x -= 4;
        }
        if (keys.get('68') == true && player.x < 980) {
            player.x += 4;
        }

        //CHECKING FOR COLLISIONS
        if (Math.abs(player.x - enemyFly.x) < 45 && Math.abs(player.y - enemyFly.y) < 55) {
            if (Number(highScore) < score) {
                localStorage.setItem('HighScore', JSON.stringify(score));
            }
            window.location.href = "start.html";
        }
        enemyGroundArr.forEach((item) => {
            if (Math.abs(player.x - item.x) < 45 && Math.abs(player.y - item.y) < 55) {
                if (Number(highScore) < score) {
                    localStorage.setItem('HighScore', JSON.stringify(score));
                }
                window.location.href = "start.html";
            }


        });

        bulletArr.forEach((item) => {
            if (Math.abs(player.x - item.x) < 45 && Math.abs(player.y - item.y) < 45) {
                if (Number(highScore) < score) {
                    localStorage.setItem('HighScore', JSON.stringify(score));
                }
                window.location.href = "start.html";
            }
        });


        //MOVING FLYING ENEMIES
        if (enemyFly != null) {
            enemyFly.x -= 7;
        }


        //SPAWNING FLYING ENEMIES
        flyingEnemyTimer--;
        if (flyingEnemyTimer <= 0) {
            flyingEnemyTimer = 250;
            enemyFly.x = 1100;
            enemyFly.y = Math.floor(Math.random() * 370);
            app.stage.addChild(enemyFly);
        }

        //SPAWNING GROUND ENEMIES AND SHOOTING BULLETS
        enemyGroundTimer--;
        if (enemyGroundTimer <= 0) {
            //RANDOM SPAWN TIMES OF GROUND ENEMIES
            spawnGroundEnemy();
            spawnBullet();
            enemyGroundTimer = Math.floor(Math.random() * (400 - 150) + 150);

        }
        else if (enemyGroundTimer == 100) {
            spawnBullet();
        }

        //ADDING SCORE FOR ONE SECOND SURVIVED 
        scoreTimer--;
        if (scoreTimer <= 0) {
            scoreTimer = 150;
            score++;
            scoreLabel.text = "Score: " + score;
        }

        //MOVING GROUND ENEMIES 
        enemyGroundArr.forEach((item) => {
            item.x -= 2;
            //destroying the element after it has left the visible area
            if (item.x < -30) {
                app.stage.removeChild(item);

                //използвам този метод вместо да премахвам елемент със splice() защото е бавна функция и създава лаг в canvas
                //така се извършва само едно обхождане на масива
                let newGroundArr = new Array();
                for (let i = 0; i < enemyGroundArr.length; i++) {
                    if (enemyGroundArr[i] != item) {
                        newGroundArr.push(enemyGroundArr[i]);
                    }
                }
                enemyGroundArr = newGroundArr;

            }
        });

        //MOVING BULLETS
        bulletArr.forEach((item) => {
            item.x -= 4;
            item.y -= 2;
            if (item.x < -50) {
                app.stage.removeChild(item);
                //използвам този метод вместо да премахвам елемент със splice() защото е бавна функция и създава лаг в canvas
                //така се извършва само едно обхождане на масива
                let newBulletArr = new Array();
                for (let i = 0; i < bulletArr.length; i++) {
                    if (enemyGroundArr[i] != item) {
                        newBulletArr.push(bulletArr[i]);
                    }
                }
                bulletArr = newBulletArr;



            }
        });

    }



}