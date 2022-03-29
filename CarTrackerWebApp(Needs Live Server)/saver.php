<?php
session_start();
   print_r($_POST);
//    $a = fopen("carLocations.json", "a");
//    fwrite($a,$_SESSION['carName'].": $_POST[lat],$_POST[lng]");
//    fclose($a);

   $data = array(
    $_SESSION['carName'] => $_POST['lat'].':'.$_POST['lng']
);

// $data = json_encode($data);



$file = "carLocations.json";

$inp = file_get_contents($file);

$tempArray = json_decode($inp);


$replaced = false;
$index = 0;
foreach($tempArray as $x => $x_value) {
    $index++;
    foreach($x_value as $name => $location) {
        print_r($name);
        print_r("   ");
        print_r($_SESSION['carName']);
        if(trim($name) == trim($_SESSION['carName'])){
            $replaced = true;
            break;
            
        }

    }
    if($replaced == true){
        break;
    }
  }

  

    if($replaced == true){
        print_r("HERE");
      $tempArray[$index-1] = $data;
      $jsonData = json_encode($tempArray);
      print_r($jsonData);
      file_put_contents($file, $jsonData);
  }
  else if($replaced == false){
    print_r("HERE2");
    // $inp = file_get_contents($file);
    // $newstr =  substr_replace($inp, '},'.$data, strlen($inp) - 2, 0);
    // $newstr = substr_replace($newstr ,"", -2)."]";
    array_push($tempArray,$data);
    $jsonData = json_encode($tempArray);
    file_put_contents($file, $jsonData);

  }



    

// file_put_contents($file, $newstr);



?>
