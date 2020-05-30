let player = {
  health: 100,
  power: 20
};

let opponent = {
  health: 100,
  power: 20
};

let attackButton = document.getElementById('attack-button');
  let gameMessage = document.getElementById('game-message');

let restartButton = document.getElementById('restart-button');
restartButton.disabled = true;
document.getElementById('attack-button').style.backgroundColor = "blue";


const attack = () => {

document.getElementById('attack-button').style.backgroundColor = "red";
  let playerAttack = Math.floor(Math.random() * player.power);
  opponent.health -= playerAttack;
  printToScreen();
  if(opponent.health <= 0){
    attackButton.disabled = true;
    gameMessage.innerText = "C# WINS!";
    restartButton.disabled = false;
    document.getElementById('restart-button').style.backgroundColor = "green";
printToScreen();
return;
  }
  else{
    attackButton.disabled = true;
    gameMessage.innerText = "C# is about to strike!";
  }

printToScreen();

  setTimeout(() => {
 let opponentAttack = Math.floor(Math.random() * opponent.power);
 player.health -= opponentAttack;

 printToScreen();
 if(player.health <= 0 ){
attackButton.disabled = true;
gameMessage.innerText = "JAVA WINS!";
restartButton.disabled = false;
document.getElementById('restart-button').style.backgroundColor = "green";
  printToScreen();
  return;
 }
 else {
 attackButton.disabled = false;
 gameMessage.innerText = "Java's turn.";
 document.getElementById('attack-button').style.backgroundColor = "blue";
}


printToScreen();


},1000);


}
const restart = () => {

player.health = 100;
opponent.health = 100;
attackButton.disabled = false;
gameMessage.innerText = "Java's turn.";
restartButton.disabled = true;
document.getElementById('attack-button').style.backgroundColor = "blue";
document.getElementById('restart-button').style.backgroundColor = "transparent";
printToScreen();
};

const printToScreen = () => {
  document.getElementById('opponent-health').innerText = opponent.health;
  document.getElementById('player-health').innerText = player.health;
};
  printToScreen();
