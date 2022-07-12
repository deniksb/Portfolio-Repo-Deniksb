async function addRequest(postIdVal,authorIdVal) {
    let token = localStorage.getItem("JWTToken");

    const userDataResponse = await axios.get(("http://localhost:8080/currentUser/get/"+token), {
        headers: {
        'Access-Control-Allow-Origin' : '*',
        'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE,PATCH,OPTIONS'
        }
      });

    let senderIdVal = userDataResponse.data.id;
    let receiverIdVal = authorIdVal;

    
    const BASE_URL_POST_UPLOAD = 'http://localhost:8080/requests/add';
    
        const response = await axios.post(BASE_URL_POST_UPLOAD,{
        senderId: senderIdVal,
        receiverId: receiverIdVal,
        postId: postIdVal
      });

      if(response.data != null){
        window.location.href = "postPage.html";
      }
    
   
    

  };