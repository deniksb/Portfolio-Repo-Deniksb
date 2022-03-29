<?php 
session_start();

if(isset($_SESSION['username'])){
    $data = array(
        $_SESSION['username'] => $_POST['brand'].':'.$_POST['number']
    );
    
    $data = json_encode($data);

    $file = "cars.json";

$inp = file_get_contents($file);

$newstr =  substr_replace($inp, '},'.$data, strlen($inp) - 2, 0);
$newstr = substr_replace($newstr ,"", -2)."]";



file_put_contents($file, $newstr);

echo "<script >

document.location.href = 'welcome.php';

</script>";
}



?>