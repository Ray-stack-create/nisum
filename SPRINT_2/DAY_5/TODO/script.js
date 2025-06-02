
//Using array (primtive datatype to store task)
let tasks = []

window.onload = function () {
  const saved = localStorage.getItem('tasks')

  //controll statement to check if the tasks are saved or not
  if (saved) {
    tasks = JSON.parse(saved)
    renderTasks()
  }
}



//Function hoisting (calling savetask before it declared)
function deleteTask (index) {
  tasks.splice(index, 1)
  saveTasks()
  renderTasks()
}

//named function to savetask enterd by user
function saveTasks () {
  localStorage.setItem('tasks', JSON.stringify(tasks))
}

function addTask () {
  const input = document.getElementById('taskInput')
  const text = input.value.trim()
  if (text !== '') {
    tasks.push({ text, completed: false })
    input.value = ''
    saveTasks()
    renderTasks()
  }
}

function toggleTask (index) {
  tasks[index].completed = !tasks[index].completed
  saveTasks()
  renderTasks()
}



function renderTasks () {
  const list = document.getElementById('taskList')
  list.innerHTML = ''
  tasks.forEach((task, index) => {
    const li = document.createElement('li')
    li.className = task.completed ? 'completed' : ''
    li.textContent = task.text
    li.onclick = () => toggleTask(index)

    const delBtn = document.createElement('button')
    delBtn.textContent = 'Delete'
    delBtn.onclick = e => {
      e.stopPropagation()
      deleteTask(index)
    }

    li.appendChild(delBtn)
    list.appendChild(li)
  })
}
