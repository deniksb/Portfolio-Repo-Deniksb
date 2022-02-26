<?php

$data = file_get_contents("posts.json");
$data = json_decode($data,true);

//v purviq loop vzimame statiite v json faila po nomer, a vuv vtoriq printirame contenta koito stoi zad suotvetniq nomer
foreach($data as $x => $x_value) {

    foreach($x_value as $name => $content) {
        echo "<div id='post-div'><h1 class='text-primary'>" . $name . "</h1>";
        echo "<p id='post-content'>". $content . "</p><br></div>";
        echo "<br>";
      }
    echo "<br>";
  }






?>