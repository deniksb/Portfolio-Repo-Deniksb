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
      

    
    const GET_REQUESTS_BY_WORKER = 'http://localhost:8080/orders/getByWorker/';
    const GET_REQUESTS_BY_CUSTOMER = 'http://localhost:8080/orders/getByCustomer/';
    
        const responseWorker = await axios.get(GET_REQUESTS_BY_WORKER+currentUserId);
        const responseCustomer = await axios.get(GET_REQUESTS_BY_CUSTOMER+currentUserId);


        let table = document.getElementById("your-assignments-container");
        responseWorker.data.forEach(async(element) => {
            const customerResponse = await axios.get('http://localhost:8080/user/findById/'+element.customerId);
            const workerResponse = await axios.get('http://localhost:8080/user/findById/'+element.workerId);
            const postResponse = await axios.get('http://localhost:8080/post/findById/'+element.postId);

            var newRow = table.insertRow(0);
            var idRow = newRow.insertCell(0);
            var workerIdRow = newRow.insertCell(1);
            var customerIdRow = newRow.insertCell(2);
            var postIdRow = newRow.insertCell(3);
            var startDateRow = newRow.insertCell(4);
            var endDateRow = newRow.insertCell(5);
            var workFinishedRow = newRow.insertCell(6);
            var paymentFinishedRow = newRow.insertCell(7);
            idRow.innerHTML=element.id;
            workerIdRow.innerHTML = workerResponse.data.email;
            customerIdRow.innerHTML = customerResponse.data.email;
            postIdRow.innerHTML = postResponse.data.title;
            startDateRow.innerHTML = element.startDate;
            endDateRow.innerHTML = element.endDate;
            workFinishedRow.innerHTML = `<button class='btn' onclick='completeWork(${element.id},${element.workFinished})'>${element.workFinished}</button>`;
            paymentFinishedRow.innerHTML = element.paymentFinished;
            
        });

        let tableOrdered = document.getElementById("your-orders-container");

        responseCustomer.data.forEach(async (element) => {
            const customerResponse = await axios.get('http://localhost:8080/user/findById/'+element.customerId);
            const workerResponse = await axios.get('http://localhost:8080/user/findById/'+element.workerId);
            const postResponse = await axios.get('http://localhost:8080/post/findById/'+element.postId);
            
            var newRow = tableOrdered.insertRow(0);
            var idRow = newRow.insertCell(0);
            var workerIdRow = newRow.insertCell(1);
            var customerIdRow = newRow.insertCell(2);
            var postIdRow = newRow.insertCell(3);
            var startDateRow = newRow.insertCell(4);
            var endDateRow = newRow.insertCell(5);
            var workFinishedRow = newRow.insertCell(6);
            var paymentFinishedRow = newRow.insertCell(7);
            idRow.innerHTML=element.id;
            workerIdRow.innerHTML = workerResponse.data.email;
            customerIdRow.innerHTML = customerResponse.data.email;
            postIdRow.innerHTML = postResponse.data.title;
            startDateRow.innerHTML = element.startDate;
            endDateRow.innerHTML = element.endDate;
            workFinishedRow.innerHTML = element.workFinished;
            paymentFinishedRow.innerHTML = `<button class='btn' data-toggle="modal" data-target="#exampleModal" onclick="doPayment(${element.id},${element.paymentFinished},${postResponse.data.price})">${element.paymentFinished}</button>`;
        });
    
        
    

  };