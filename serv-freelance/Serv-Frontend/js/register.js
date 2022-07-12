async function register() {
   



   let firstNameVal = document.getElementById("register-first-name").value;
   let lastNameVal = document.getElementById("register-last-name").value;
   let emailVal = document.getElementById("register-email").value;
   let passwordVal = document.getElementById("register-password").value;


    
    const BASE_URL_REGISTER = 'http://localhost:8080/user/register';
    
        const response = await axios.post(BASE_URL_REGISTER,{
        firstName: firstNameVal,
        lastName: lastNameVal,
        email: emailVal,
        password: passwordVal
      });

      if(response.data != null){
        window.location.href = "login.html";
      }
    
   
    

  };