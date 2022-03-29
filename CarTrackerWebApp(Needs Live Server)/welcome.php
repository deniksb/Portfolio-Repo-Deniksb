<?php 
session_start();


// if(isset($_POST["username"])){
//     $username =  $_POST["username"];   
//   }
//   if(isset($_POST["psw"])){
//     $pass = $_POST["psw"];  
//   }

echo '<link rel="stylesheet" href="stylesheet.css">';
echo '<body id="flex-container">';
echo '<h1>Car Parking Tracker</h1>';
if(isset($_SESSION['username'])){

  echo "Welcome, ". $_SESSION['username'] . "!";


  $cars = file_get_contents("cars.json");
  $cars = json_decode($cars,true);

  echo "<br><html id='html'>";
  echo "<h1>Your cars: </h1>";
  foreach($cars as $x => $x_value) {
  
      foreach($x_value as $name => $content) {
          if($_SESSION['username'] == $name){
              $userCars = explode(',',$content);
              $index = 0;
              foreach($userCars as &$car){
                  $index++;
                  echo "<form action='getCar.php' method='post' enctype='multipart/form-data'>";
                 echo '<input class ="car-option" type="submit" value="'.$car.'" name="option"><br>';
                 echo "</form>";
              }
              
          }
        }
    }
    echo "<br>
    <a href='logout.php'>Logout?</a>";

    echo "<br><h1>Add car</h1><br>";
    echo '<form action="uploadCar.php" method="post" action="#" id="uploadCar">
    <label for="brand"><b>Brand</b></label>
    <br>
    <input type="text" placeholder="Enter Brand" name="brand" id="brand" required>
    <br><br>
    <label for="number"><b>Number</b></label>
    <br>
    <input type="text" placeholder="Enter Number" name="number" id="number" required>
    <br><br>
    <input type="submit" value="Upload"></form>';


    echo "<script src='carScript.js'></script>";
    echo "</html>";
    
}
else{


echo $_POST["username"];


$data = file_get_contents("register.json");
$data = json_decode($data,true);

    foreach($data as $x => $x_value) {

        foreach($x_value as $name => $content) {
            if($_POST["username"] == $name){
                if($_POST["psw"] == $content){
                    $_SESSION['username'] = $_POST["username"];
                    echo "<script >
    
                    document.location.href = 'welcome.php';
                    
                    </script>";
          
                }   
            }
            else{
                echo "Wrong Credentials!";
            }
          }
      }
    //   file_put_contents('currentUser.json', $username);
      


}


echo '</body>';


?>
