async function getPostsByCategory(category) {

    let token = localStorage.getItem("JWTToken");

    const BASE_URL_FILTERED_POSTS = "http://localhost:8080/posts/"+category;
    console.log(BASE_URL_FILTERED_POSTS);
    const response = await axios.get(BASE_URL_FILTERED_POSTS, {
        headers: {
        'Access-Control-Allow-Origin' : '*',
        'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE,PATCH,OPTIONS'
        }
      });
      
      document.getElementById("posts-container").innerHTML="";
      response.data.forEach((element) => {
        let singlePost = `<br><div class="card mx-auto w-75" style="width: 18rem;"">
        <img class="card-img-top" src="../static/img/${element.image}" alt="Card image cap">
        <div class="card-body">
            <p class="card-text">${element.title}</p>
            <p class="card-text" >${element.content}</p>
            <br>
            <a class="card-link" href="#" >${element.category}</a>
            <br>
            <a class="card-link" href="'/messages/' + ${element.authorId}">Contact User</a>
        </div>
    </div>`

            document.getElementById("posts-container").innerHTML += singlePost;
      }
        );
     
    
    
      

  };