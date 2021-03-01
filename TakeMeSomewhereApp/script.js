var foot = document.getElementById("foot-check");
var car = document.getElementById("car-check");

function checkFoot(){
    foot.checked = true;
    car.checked = false;
}

function checkCar(){
    foot.checked = false;
    car.checked = true;
}

var cityLocation = ["plovdiv\\https://www.google.com/maps/dir/42.1549083,24.7736078/%D0%B4%D0%B6%D1%83%D0%BC%D0%B0%D1%8F+%D0%BF%D0%BB%D0%BE%D0%B2%D0%B4%D0%B8%D0%B2/@42.1516842,24.7572234,16z/data=!3m1!4b1!4m10!4m9!1m1!4e1!1m5!1m1!1s0x14acd1bc95045515:0x13c17d25b6e5ccff!2m2!1d24.7482517!2d42.1479557!3e2"];



var button = document.getElementById("button");

function buttonFunc(){
    var city = document.getElementById("city").value;
    var link;
    for(var i = 0;i < cityLocation.length;i++){
        var elems = cityLocation[i].split("\\");
        if(elems[0] == city && foot.checked == true){
            link = elems[1];
        }
    }

    var win = window.open(link, '_blank');
    win.focus();
    
    

    
}





