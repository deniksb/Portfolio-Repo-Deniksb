window.onload = async function() {
    let token = localStorage.getItem("JWTToken");

    const userDataResponse = await axios.get(("http://localhost:8080/currentUser/get/"+token), {
        headers: {
        'Access-Control-Allow-Origin' : '*',
        'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE,PATCH,OPTIONS'
        }
      });

    let currentUserId = userDataResponse.data.id;

    console.log(currentUserId);
      

    
    const GET_CHATS = 'http://localhost:8080/chats/';

    
        const responseChats = await axios.get(GET_CHATS+currentUserId);

        

        
        responseChats.data.forEach(async (element) => {

            const userOneResponse = await axios.get('http://localhost:8080/user/findById/'+element.memberOneId);
            const userTwoResponse = await axios.get('http://localhost:8080/user/findById/'+element.memberTwoId);
            
            document.getElementById("chats-container").innerHTML += `<button class="btn w-75" data-toggle="modal" id="chat-button-${element.id}" data-target="#exampleModal" onclick="getMessagesByChatId(${element.id})">Chat between: ${userOneResponse.data.email} - ${userTwoResponse.data.email}</button><br><br>`
            
        });


  };


  async function getMessagesByChatId(chatId){

    const GET_MESSAGES = 'http://localhost:8080/messages/';
    const responseMessages = await axios.get(GET_MESSAGES+chatId);


    let messageContainer = document.getElementById("messages-container");
    messageContainer.innerHTML = "";

    responseMessages.data.forEach((element) => {

        messageContainer.innerHTML += `<p>${element.content}</p>`;


    });
    messageContainer.innerHTML += `<input type="text" id="new-message" class="form-control">`;
    messageContainer.innerHTML += `<button type="button" class="btn btn-primary" onclick="addMessage(${chatId})">Send Message</button>`;


  };

  async function addMessage(chatIdVal){

    const ADD_MESSAGE = 'http://localhost:8080/messages/add';
    let contentVal = document.getElementById("new-message").value;
    const responseMessages = await axios.post(ADD_MESSAGE,{
        chatId: chatIdVal,
        content: contentVal,
        sentDate: ""
    });

    if(responseMessages.data != null){
        
        getMessagesByChatId(chatIdVal);
    }


  };