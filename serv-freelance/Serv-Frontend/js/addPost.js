//uploading images to firebase
import {firebase,storage,uploadFile} from 'http://localhost/Serv-Frontend/js/firebaseConfig.js';

let ImgName, ImgUrl;
let files = [];
let reader;

document.getElementById("select-image").onclick = function(e){

  var input = document.createElement('input');
  input.type = 'file';

  input.onchange = e =>{
    
    files = e.target.files;
    reader = new FileReader();

    reader.readAsDataURL(files[0]);
    console.log(files[0].name)
    ImgName = files[0].name;
  
  }
  input.click();
}




//creating post
document.getElementById("add-post-btn").onclick = async function() {
    let token = localStorage.getItem("JWTToken");

    const userDataResponse = await axios.get(("http://localhost:8080/currentUser/get/"+token), {
        headers: {
        'Access-Control-Allow-Origin' : '*',
        'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE,PATCH,OPTIONS'
        }
      });

    let authorIdVal = userDataResponse.data.id;

   let titleVal = document.getElementById("post-title").value;
   let contentVal = document.getElementById("post-content").value;
   let categoryVal = document.getElementById("post-category").value;
   let priceVal = document.getElementById("post-price").value;
   let durationVal = document.getElementById("post-duration").value;
   

    
    const BASE_URL_POST_UPLOAD = 'http://localhost:8080/posts/add';

    const headers = {
      'Access-Control-Allow-Origin' : '*',
      'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE,PATCH,OPTIONS'
    }

    var resolvedFlag = true;
        let uploadFilePromise = () => {
          uploadFile(files[0],ImgName);
          return new Promise((resolve ,reject)=>{
            setTimeout(
                ()=>{
                    console.log("Inside the promise");
                    if(resolvedFlag==true){
                        resolve("Resolved");
                    }else{
                        reject("Rejected")
                    }     
                } , 5000
            );
        });
        };

        uploadFilePromise().then((res)=>{
          const response =  axios.post(BASE_URL_POST_UPLOAD,{
            title: titleVal,
            content: contentVal,
            category: categoryVal,
            image: document.getElementById("imgUrl").value,
            authorId: authorIdVal,
            price: priceVal,
            duration: durationVal
          });
          console.log(response);
           
        window.location.href = "postPage.html";
      
      }).catch((error)=>{
          console.log(`Handling error as we received ${error}`);
      });
        
        
  
     



      

      
     
    
  

  };