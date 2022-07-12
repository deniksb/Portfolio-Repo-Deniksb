window.onload = async function() {
    let token = localStorage.getItem("JWTToken");

    const userDataResponse = await axios.get(("http://localhost:8080/currentUser/get/"+token), {
        headers: {
        'Access-Control-Allow-Origin' : '*',
        'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE,PATCH,OPTIONS'
        }
      });

    let authorIdVal = userDataResponse.data.id;

    console.log(authorIdVal);


    
    const GET_REQUESTS_BY_SENDER = 'http://localhost:8080/requests/getBySender/';
    const GET_REQUESTS_BY_RECEIVER = 'http://localhost:8080/requests/getByReceiver/';
    
        const responseSender = await axios.get(GET_REQUESTS_BY_SENDER+authorIdVal);
        const responseReceiver = await axios.get(GET_REQUESTS_BY_RECEIVER+authorIdVal);


        let table = document.getElementById("pending-request-container");
        responseSender.data.forEach( async(element) => {
            const senderResponse = await axios.get('http://localhost:8080/user/findById/'+element.senderId);
            const receiverResponse = await axios.get('http://localhost:8080/user/findById/'+element.receiverId);
            const postResponse = await axios.get('http://localhost:8080/post/findById/'+element.postId);
            var newRow = table.insertRow(0);
            var idRow = newRow.insertCell(0);
            var senderIdRow = newRow.insertCell(1);
            var receiverIdRow = newRow.insertCell(2);
            var postIdRow = newRow.insertCell(3);
            idRow.innerHTML=element.id;
            senderIdRow.innerHTML=senderResponse.data.email;
            receiverIdRow.innerHTML=receiverResponse.data.email;
            postIdRow.innerHTML=postResponse.data.title;
            
        });

        let tableReceived = document.getElementById("received-request-container");
        responseReceiver.data.forEach(async(element) => {
            const senderResponse = await axios.get('http://localhost:8080/user/findById/'+element.senderId);
            const receiverResponse = await axios.get('http://localhost:8080/user/findById/'+element.receiverId);
            const postResponse = await axios.get('http://localhost:8080/post/findById/'+element.postId);
            var newRow = tableReceived.insertRow(0);
            var idRow = newRow.insertCell(0);
            var senderIdRow = newRow.insertCell(1);
            var receiverIdRow = newRow.insertCell(2);
            var postIdRow = newRow.insertCell(3);
            var acceptRow = newRow.insertCell(4);
            var declineRow = newRow.insertCell(5);
            idRow.innerHTML=element.id;
            senderIdRow.innerHTML=senderResponse.data.email;
            receiverIdRow.innerHTML=receiverResponse.data.email;
            postIdRow.innerHTML=postResponse.data.title;
            acceptRow.innerHTML = `<button class='btn' onclick='acceptRequest(${element.id},${element.senderId},${element.receiverId},${element.postId})'>Accept</button>`;
            declineRow.innerHTML = `<button class='btn' onclick='declineRequest(${element.id})'>Decline</button>`;
        });
    
   
    

  };