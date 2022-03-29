
<?php 

$username =  $_POST["username"]; 
$pass = $_POST["psw"];



$data = array(
    $username => $pass
);

$data = json_encode($data);



$file = "register.json";

$inp = file_get_contents($file);

$newstr =  substr_replace($inp, ','.$data, strlen($inp) - 2, 0);


    

file_put_contents($file, $newstr);

echo "<script >

document.location.href = 'index.php';

</script>";




?>