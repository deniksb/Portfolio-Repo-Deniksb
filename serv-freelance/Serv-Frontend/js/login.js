
const BASE_URL = 'http://localhost:8080/user/login/';

async function login(){
    let usernameVal = document.getElementById("username-login").value;
    let passwordVal = document.getElementById("password-login").value;

    try {
        
        
          console.log((BASE_URL+usernameVal+"&"+passwordVal))
           const response = await axios.get((BASE_URL+usernameVal+"&"+passwordVal), {
        headers: {
        'Access-Control-Allow-Origin' : '*',
        'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE,PATCH,OPTIONS'
        }
      });

        if(response.data != "not found"){
          localStorage.setItem("JWTToken",response.data);
          window.location.href = "postPage.html";
        }
        else{
          
        }
    
        console.log(response)
        return response;
      } catch (errors) {
        console.error(errors);
      }

    
}