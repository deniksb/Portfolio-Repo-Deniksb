const BASE_URL_POSTS = 'http://localhost:8080/posts';


window.onload = async function() {
    let token = localStorage.getItem("JWTToken");

    const userData = await axios.get(("http://localhost:8080/currentUser/get/"+token), {
      headers: {
      'Access-Control-Allow-Origin' : '*',
      'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE,PATCH,OPTIONS'
      }
    });

    let currentUserId = userData.data.id;

    const response = await axios.get(BASE_URL_POSTS, {
        headers: {
        'Access-Control-Allow-Origin' : '*',
        'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE,PATCH,OPTIONS'
        }
      });
      
      response.data.forEach((element) => {
        let singlePost = `<br><div class="card mx-auto w-50" style="width: 18rem;"">
        <img class="card-img-top img-fluid" src="${element.image}" alt="Card image cap">
        <div class="card-body">
            <p class="card-text">${element.title}</p>
            <p class="card-text" >${element.content}</p>
            <br>
            <a class="card-link" href="#" >${element.category}</a>
            <br>
            <a class="card-link" onclick="addChat(${currentUserId},${element.authorId})">Start chat</a>
            <br>
            <a class="card-link" onclick="addRequest(${element.id},${element.authorId})">Create request</a>
        </div>
    </div>`
            document.getElementById("posts-container").innerHTML += singlePost;
      }
        );
     
    
    
      

  };