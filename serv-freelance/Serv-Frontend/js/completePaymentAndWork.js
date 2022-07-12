const COMPLETE_WORK_URL = 'http://localhost:8080/orders/updateWorkFinished/';
const COMPLETE_PAYMENT_URL = 'http://localhost:8080/orders/updatePaymentFinished/';

async function completeWork(postId,workCompleted){

    let token = localStorage.getItem("JWTToken");

    if(workCompleted == false){
        const response = await axios.post(COMPLETE_WORK_URL+postId,"");

        if(response.data != null){
          window.location.href = "orderPage.html";
        }
    }


}

async function completePayment(postId,paymentCompleted){

    let token = localStorage.getItem("JWTToken");
    if(paymentCompleted == false){
        const response = await axios.post(COMPLETE_PAYMENT_URL+postId,"");

        if(response.data != null){
          window.location.href = "orderPage.html";
        }
    }


}