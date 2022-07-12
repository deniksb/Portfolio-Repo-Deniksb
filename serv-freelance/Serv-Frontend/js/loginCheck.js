window.onload = function() {
    let token = localStorage.getItem("JWTToken");

    console.log(token);

    if(token == undefined){
        window.location.href = "login.html";
    }
  };