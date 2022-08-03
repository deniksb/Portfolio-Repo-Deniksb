import './App.css';
import { useState, useEffect } from 'react';
import { db } from './firebase-config'
import { collection, getDocs, addDoc } from 'firebase/firestore'
import ListGroup from 'react-bootstrap/ListGroup';
import Card from 'react-bootstrap/Card';
import { CardItem } from './components/CardItem'


function App() {
  const [newContent, setNewContent] = useState("");
  const [newTaskProgress, setNewTaskProgress] = useState("1");


  const [tasks, setTasks] = useState([]);
  const tasksCollectionRef = collection(db, "tasks");

  const createUser = async () => {
    console.log(newTaskProgress);
    await addDoc(tasksCollectionRef, {content: newContent, taskProgress: newTaskProgress});
  }

  useEffect(() => {

    const getTasks = async () => {
      const data = await getDocs(tasksCollectionRef);
      setTasks(data.docs.map((doc) => ({ ...doc.data(), id: doc.id })));

    };

    getTasks();
  }, [])

  return (
    <div className="App">
      <h1>Project Manager</h1>
      <br></br>
      <p>Add task</p>
      <input 
      placeholder="Type here..." 
      onChange={
        (event) => {setNewContent(event.target.value)
        }} />
      <select 
      onChange={
        (event) => {setNewTaskProgress(event.target.value)
        }}>
        <option value="1" selected>ToDo</option>
        <option value="2">In Progress</option>
        <option value="3">Testing</option>
        <option value="4">In Review</option>
        <option value="5">Deployed</option>
      </select>
      <button onClick={createUser}>Add task</button>

      <div id="card-container">
        <Card style={{ width: '18rem' }}>
          <Card.Header>ToDo</Card.Header>
          <ListGroup variant="flush">
            {tasks.map((task) => {
              if (task.taskProgress === "1")
                return <CardItem data={{ content: task.content }}></CardItem>
            })}
          </ListGroup>
        </Card>

        <Card style={{ width: '18rem' }}>
          <Card.Header>In Progress</Card.Header>
          <ListGroup variant="flush">
            {tasks.map((task) => {
              if (task.taskProgress === "2")
                return <CardItem data={{ content: task.content }}></CardItem>
            })}
          </ListGroup>
        </Card>

        <Card style={{ width: '18rem' }}>
          <Card.Header>Testing</Card.Header>
          <ListGroup variant="flush">
            {tasks.map((task) => {
              if (task.taskProgress === "3")
                return <CardItem data={{ content: task.content }}></CardItem>
            })}
          </ListGroup>
        </Card>

        <Card style={{ width: '18rem' }}>
          <Card.Header>In Review</Card.Header>
          <ListGroup variant="flush">
            {tasks.map((task) => {
              if (task.taskProgress === "4")
                return <CardItem data={{ content: task.content }}></CardItem>
            })}
          </ListGroup>
        </Card>

        <Card style={{ width: '18rem' }}>
          <Card.Header>Deployed</Card.Header>
          <ListGroup variant="flush">
            {tasks.map((task) => {
              if (task.taskProgress === "5")
                return <CardItem data={{ content: task.content }}></CardItem>
            })}
          </ListGroup>
        </Card>
      </div>


    </div>
  );
}

export default App;
