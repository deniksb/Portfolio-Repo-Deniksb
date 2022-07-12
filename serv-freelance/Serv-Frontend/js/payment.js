
async function doPayment(postId,paymentCompleted,price){
  var stripe = Stripe('pk_test_51LBZeMDUz0nZARxElw0vNr4e3RdbjO5p7DtDqBZ4XXLHmoGqVAzH9bS3qoChba7X4QA5WomYnN7kMR6LxU0utpoQ00m8R3Wfwx');
var elements = stripe.elements();

// Custom styling can be passed to options when creating an Element.
var style = {
    base: {
      // Add your base input styles here. For example:
      fontSize: '16px',
      color: '#32325d',
    },
  };
  
  // Create an instance of the card Element.
  var card = elements.create('card', {style: style});
  
  // Add an instance of the card Element into the `card-element` <div>.
  card.mount('#card-element');

  // Create a token or display an error when the form is submitted.
var form = document.getElementById('payment-form');
form.addEventListener('submit', function(event) {
  event.preventDefault();

  stripe.createToken(card).then(function(result) {
    if (result.error) {
      // Inform the customer that there was an error.
      var errorElement = document.getElementById('card-errors');
      errorElement.textContent = result.error.message;
    } else {
      // Send the token to your server.
      stripeTokenHandler(result.token);
      
    }
  });
});


function stripeTokenHandler(token) {
    // Insert the token ID into the form so it gets submitted to the server
    var form = document.getElementById('payment-form');
    var hiddenInput = document.createElement('input');
    hiddenInput.setAttribute('type', 'hidden');
    hiddenInput.setAttribute('name', 'stripeToken');
    hiddenInput.setAttribute('value', token.id);
    form.appendChild(hiddenInput);
  
    // Submit the form
    const headers = {
        'Access-Control-Allow-Origin' : '*',
        'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE,PATCH,OPTIONS',
        'token': token.id,
        'amount': price
      }
      
      let response = axios.post("http://localhost:8080/payment/charge","", {
          headers: headers
        }).then(res => {completePayment(postId,paymentCompleted)})
        .catch(err => console.error(err)); // promise

        
        
  }
}
