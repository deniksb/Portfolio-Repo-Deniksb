<?php 
$username =  $_POST["username"]; 
$pass = $_POST["password"];


$data = file_get_contents("login.json");
$data = json_decode($data,true);




foreach($data as $x => $x_value) {

    foreach($x_value as $name => $content) {
        if($username == $name){
            if($pass == $content){
                echo "Success";
                echo "<script src='loginscript.js'></script>";

                
            }   
        }
        else{
            echo "Wrong Credentials!";
        }
      }
  }



?>