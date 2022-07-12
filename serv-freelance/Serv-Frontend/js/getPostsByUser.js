const BASE_URL_POSTS = 'http://localhost:8080/posts/findByAuthorId/';


window.onload = async function() {
    let token = localStorage.getItem("JWTToken");
    const userData = await axios.get(("http://localhost:8080/currentUser/get/"+token), {
      headers: {
      'Access-Control-Allow-Origin' : '*',
      'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE,PATCH,OPTIONS'
      }
    });
    document.getElementById("user-email").innerHTML = userData.data.email;
    document.getElementById("user-first-name").innerHTML = userData.data.firstName;
    document.getElementById("user-last-name").innerHTML = userData.data.lastName;

    let currentUserId = userData.data.id;
    

    const response = await axios.get(BASE_URL_POSTS+currentUserId, {
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
            <br>
            <a class="card-link" onclick="deletePost(${element.id})">Delete</a>
        </div>
    </div>`
            document.getElementById("posts-container").innerHTML += singlePost;
      }
        );
     
    
    
      

  };


  async function deletePost(id){

    const BASE_URL_DELETE_POST = 'http://localhost:8080/post/delete/';

    const response = await axios.delete(BASE_URL_DELETE_POST+id, {
        headers: {
        'Access-Control-Allow-Origin' : '*',
        'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE,PATCH,OPTIONS'
        }
      }).then(() => { window.location.href = "profilePage.html"})

     


  }