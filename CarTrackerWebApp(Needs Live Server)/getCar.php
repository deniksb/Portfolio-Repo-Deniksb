<?php 
session_start();


echo '<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>';
echo '<link rel="stylesheet" href="stylesheet.css">';
echo '<body id="flex-container">';
echo '<h1>Car Parking Tracker</h1>';

if(isset($_SESSION['username'])){

 $value = $_POST['option'];

 echo "<h1 id='carName'>".$value."</h1>";

 $_SESSION['carName'] = $value;

 $cars = file_get_contents("carLocations.json");
  $cars = json_decode($cars,true);

  foreach($cars as $x => $x_value) {

  foreach($x_value as $name => $content){
    if($name == $value){
        $coordinates = explode(":",$content);
        echo "<p>Lat: ".$coordinates[0]."</p>";
        echo "<br>Lon: ".$coordinates[1]."</p>";
        echo "<a class='button' href='https://www.google.com/maps?z=12&t=m&q=loc:".$coordinates[0]."+".$coordinates[1]."'>Locate!</a>";
    }
  }


}
}

echo '<br><br><button onclick="saveLocation()" id="save-location-button">Save New Location</button><br><br>';
echo '<div class="link"><a href="welcome.php">Back?</a></div>';
echo '<script src="carScript.js"></script>';
echo '</body>';
?>



