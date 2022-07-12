const BASE_URL = 'https://jsonplaceholder.typicode.com';

async function  test(){
    try {
        const response = await axios.get(`${BASE_URL}/todos?_limit=5`);
    
        const todoItems = response.data;
    
        
        console.log(todoItems)
        return todoItems;
      } catch (errors) {
        console.error(errors);
      }
} 

console.log(test())






