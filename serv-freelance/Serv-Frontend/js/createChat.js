const ADD_CHAT_URL = 'http://localhost:8080/chats/add';

async function addChat(memberOneIdVal,memberTwoIdVal) {
    let token = localStorage.getItem("JWTToken");


    const response = await axios.post(ADD_CHAT_URL,{
        memberOneId: memberOneIdVal,
        memberTwoId: memberTwoIdVal
      });

      if(response.data != null){
        window.location.href = "messagesPage.html";
      }





};