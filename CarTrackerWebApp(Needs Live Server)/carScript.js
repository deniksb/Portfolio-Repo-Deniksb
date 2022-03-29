
function saveLocation(){
    if(navigator.geolocation)
    {
      navigator.geolocation.getCurrentPosition(showPosition);
    }
    else
    {
      alert("Geolocation is not supported by this browser.");
    }
    }

    
    function showPosition(pos){
       $.post('saver.php',{'carId':document.getElementById('carName').innerHTML,'lat':pos.coords.latitude,'lng':pos.coords.longitude},function(res){
          console.log(res);
       });
    }