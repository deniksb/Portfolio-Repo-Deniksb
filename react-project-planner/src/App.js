import './App.css';
import { useState, useEffect } from 'react';
import { db } from './firebase-config'
import { collection, getDocs, addDoc, updateDoc, doc, deleteDoc } from 'firebase/firestore'
import ListGroup from 'react-bootstrap/ListGroup';
import Card from 'react-bootstrap/Card';
import { CardItem } from './components/CardItem'
import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import Button from 'react-bootstrap/Button';


function App() {
  const [newContent, setNewContent] = useState("");
  const [newTaskProgress, setNewTaskProgress] = useState("1");


  const [tasks, setTasks] = useState([]);
  const tasksCollectionRef = collection(db, "tasks");

  const createTask = async () => {
    if (newContent != "") {
      await addDoc(tasksCollectionRef, { content: newContent, taskProgress: newTaskProgress })
        .then(() => { window.location.reload(); });
    }

  }

  const updateTask = async (id, taskProgress, sumNumber) => {
    if (parseInt(taskProgress) + sumNumber > 0 && parseInt(taskProgress) + sumNumber < 6) {
      const taskDoc = doc(db, "tasks", id);
      const newFields = { taskProgress: (parseInt(taskProgress) + sumNumber).toString() }
      await updateDoc(taskDoc, newFields).then(() => { window.location.reload(); });
    }
  };

  const deleteTask = async (id) => {
    const userDoc = doc(db, "tasks", id);
    await deleteDoc(userDoc).then(() => { window.location.reload(); });
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
      <h1 className='h1 mt-3' >Project Manager</h1>
      <br></br>
      <InputGroup className="mb-3 w-50 mx-auto">
        <Form.Control
          aria-label="Text input with dropdown button"
          maxLength={50}
          placeholder="Type here..."
          onChange={
            (event) => {
              setNewContent(event.target.value)
            }} />
        <Dropdown>
        <DropdownButton
          variant="outline-secondary"
          title="Task Progress"
          id="input-group-dropdown-2"
          align="end"
        >
          <Dropdown.Item onClick={() => {setNewTaskProgress("1")}}>ToDo</Dropdown.Item>
          <Dropdown.Item onClick={() => {setNewTaskProgress("2")}}>In Progress</Dropdown.Item>
          <Dropdown.Item onClick={() => {setNewTaskProgress("3")}}>Testing</Dropdown.Item>
          <Dropdown.Item onClick={() => {setNewTaskProgress("4")}}>In Review</Dropdown.Item>
          <Dropdown.Item onClick={() => {setNewTaskProgress("5")}}>Deployed</Dropdown.Item>
          
        </DropdownButton>
        </Dropdown>
        <Button variant="dark" onClick={createTask}>Add Task</Button>
      </InputGroup>
      

      <div id="card-container">
        <Card style={{ width: '18rem' }} className="mb-3">
          <Card.Header>ToDo</Card.Header>
          <ListGroup variant="flush">
            {tasks.map((task) => {
              if (task.taskProgress === "1")
                return (
                  <div className='task-container'>
                    <div className='task-subcontainer'>
                      <button onClick={() => { updateTask(task.id, task.taskProgress, -1) }}>◂</button>
                      <CardItem data={{ content: task.content }}></CardItem>
                      <button onClick={() => { updateTask(task.id, task.taskProgress, 1) }}>▸</button>
                    </div>
                    <button onClick={() => { deleteTask(task.id) }}>Del</button>
                  </div>)
            })}
          </ListGroup>
        </Card>

        <Card style={{ width: '18rem' }} className="mb-3">
          <Card.Header>In Progress</Card.Header>
          <ListGroup variant="flush">
            {tasks.map((task) => {
              if (task.taskProgress === "2")
                return (
                  <div className='task-container'>
                    <div className='task-subcontainer'>
                      <button onClick={() => { updateTask(task.id, task.taskProgress, -1) }}>◂</button>
                      <CardItem data={{ content: task.content }}></CardItem>
                      <button onClick={() => { updateTask(task.id, task.taskProgress, 1) }}>▸</button>
                    </div>
                    <button onClick={() => { deleteTask(task.id) }}>Del</button>
                  </div>)
            })}
          </ListGroup>
        </Card>

        <Card style={{ width: '18rem' }} className="mb-3">
          <Card.Header>Testing</Card.Header>
          <ListGroup variant="flush">
            {tasks.map((task) => {
              if (task.taskProgress === "3")
                return (
                  <div className='task-container'>
                    <div className='task-subcontainer'>
                      <button onClick={() => { updateTask(task.id, task.taskProgress, -1) }}>◂</button>
                      <CardItem data={{ content: task.content }}></CardItem>
                      <button onClick={() => { updateTask(task.id, task.taskProgress, 1) }}>▸</button>
                    </div>
                    <button onClick={() => { deleteTask(task.id) }}>Del</button>
                  </div>)
            })}
          </ListGroup>
        </Card>

        <Card style={{ width: '18rem' }} className="mb-3">
          <Card.Header>In Review</Card.Header>
          <ListGroup variant="flush">
            {tasks.map((task) => {
              if (task.taskProgress === "4")
                return (
                  <div className='task-container'>
                    <div className='task-subcontainer'>
                      <button onClick={() => { updateTask(task.id, task.taskProgress, -1) }}>◂</button>
                      <CardItem data={{ content: task.content }}></CardItem>
                      <button onClick={() => { updateTask(task.id, task.taskProgress, 1) }}>▸</button>
                    </div>
                    <button onClick={() => { deleteTask(task.id) }}>Del</button>
                  </div>)
            })}
          </ListGroup>
        </Card>

        <Card style={{ width: '18rem' }} className="mb-3">
          <Card.Header>Deployed</Card.Header>
          <ListGroup variant="flush">
            {tasks.map((task) => {
              if (task.taskProgress === "5")
                return (
                  <div className='task-container'>
                    <div className='task-subcontainer'>
                      <button onClick={() => { updateTask(task.id, task.taskProgress, -1) }}>◂</button>
                      <CardItem data={{ content: task.content }}></CardItem>
                      <button onClick={() => { updateTask(task.id, task.taskProgress, 1) }}>▸</button>
                    </div>
                    <button onClick={() => { deleteTask(task.id) }}>Del</button>
                  </div>)
            })}
          </ListGroup>
        </Card>
      </div>


    </div>
  );
}

export default App;
