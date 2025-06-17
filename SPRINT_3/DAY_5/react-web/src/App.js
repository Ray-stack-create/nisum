import './App.css'
import Header from './components/Header'
import React, { useState } from 'react'
import Button from 'react-bootstrap/Button'
import Card from 'react-bootstrap/Card'
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import 'bootstrap/dist/css/bootstrap.min.css'
import { about } from './product/about'
import TodoInput from './components/TodoInput'
import Todolist from './components/TodoList'
import '@fortawesome/fontawesome-free/css/all.min.css'
import Footer from './components/Footer'
import Notifications from './components/Notification'
import UserProfile from './components/UserProfile'
import UserTable from './components/UserTable'
import Counter from './components/Counter'

function App () {
  const [darkMode, setDarkMode] = useState(false)

  // <-- Theme toggle state

  const toggleTheme = () => {
    setDarkMode(!darkMode)
  }

  const [listTodo, setListTodo] = useState([])
  let addList = inputText => {
    if (inputText !== '') setListTodo([...listTodo, inputText])
  }
  const deleteListItem = key => {
    let newListTodo = [...listTodo]
    newListTodo.splice(key, 1)
    setListTodo([...newListTodo])
  }

  const notificationsData = [
    {
      message: 'Merge new pull request.',
      date: '2025-06-17',
      timestamp: '15:30',
      read: false
    },
    {
      message: 'Attened meeting on 9 A.M',
      date: '2025-06-16',
      timestamp: '10:20',
      read: true
    },
    {
      message: 'Deployment error! Abbort now',
      date: '2025-06-15',
      timestamp: '09:45',
      read: false
    }
  ]

  return (

    <div className={`App ${darkMode ? 'dark' : 'light'}`}>
      <button className='theme-toggle' onClick={toggleTheme}>
        {darkMode ? 'Light Mode☀️' : 'Dark Mode🌙'}
      </button>

      {/* 1. Create a Greeting component that accepts a prop called name and renders: */}
      <Header name='Sankhadip' />

      {/* 2. Create a Counter component that: */}

      <Counter />

      {/* 3. Create a ProductCard component that takes the following props: */}
      <Container className='mt-5 mb-5'>
        <Row className='justify-content-center'>
          {about.map((v, i) => {
            return <ProductItem pitems={v} key={i} /> //iterating through the no of objects in the blog.json and creating that no of component
          })}
        </Row>
      </Container>
      <div className='todo-wrapper'>
        <h1>TODO List</h1>
        <TodoInput addList={addList} />

        {listTodo.map((listItem, i) => {
          return (
            <Todolist
              key={i}
              index={i}
              item={listItem}
              deleteItem={deleteListItem}
            />
          )
        })}
      </div>

      <div
        style={{
          display: 'flex',
          gap: '40px',
          paddingBottom: '40px',
          paddingLeft: '300px',
          flexWrap: 'wrap'
        }}
      >
        <UserProfile
          name='Sankhadip Roy'
          email='sankhadip@example.com'
          avatarUrl=''
          bio='Passionate Developer. Eager to apply my skills to real-world challenges in the field.'
        />
        <Notifications notifications={notificationsData} />

        <UserTable />
      </div>

      <Footer />
    </div>
  )
}

export default App

function ProductItem ({ pitems }) {
  return (
    <Col lg='3' md='6'>
      <Card style={{ width: '18rem' }}>
        <Card.Img variant='top' src={pitems.image} />
        <Card.Body>
          <Card.Title>{pitems.title}</Card.Title>
          <Card.Text>
            {pitems.price}
            <br />
            {pitems.description}
          </Card.Text>
          <Button variant='primary'>Shop Now</Button>
        </Card.Body>
      </Card>
    </Col>
  )
}
