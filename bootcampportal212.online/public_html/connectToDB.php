<?php

session_start();

$conn = mysqli_connect('localhost','u203017424_deniksb','Db123456$$','u203017424_bootcampportal');

if(!$conn){
  echo "Connection error" . mysqli_connect_error();
}

?>
