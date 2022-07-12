

async function acceptRequest(idVal,senderIdVal,receiverIdVal,postIdVal) {
    let token = localStorage.getItem("JWTToken");


    
    const BASE_URL_ACCEPT_REQUEST = 'http://localhost:8080/orders/add';
    
        const response = await axios.post(BASE_URL_ACCEPT_REQUEST,{
        id: idVal,
        senderId: senderIdVal,
        receiverId: receiverIdVal,
        postId: postIdVal
      });

      if(response.data != null){
        window.location.href = "requestPage.html";
      }
    
   
    

  };

  async function declineRequest(idVal){
    const BASE_URL_DECLINE_REQUEST = 'http://localhost:8080/requests/delete/';

    const response = await axios.delete(BASE_URL_DECLINE_REQUEST+idVal).then(() => window.location.href = "requestPage.html");

        
      
  }