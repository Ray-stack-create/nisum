"use strict";
var Status;
(function (Status) {
    Status["Pending"] = "Pending";
    Status["InProgress"] = "InProgress";
    Status["Completed"] = "Completed";
})(Status || (Status = {}));
class TodoManager {
    constructor() {
        this.todos = [];
    }
    add(todo) {
        const todoWithTimestamp = Object.assign(Object.assign({}, todo), { createdAt: new Date() });
        this.todos.push(todoWithTimestamp);
    }
    getTodos() {
        return this.todos;
    }
}
const todoManager = new TodoManager();
const addBtn = document.getElementById("addBtn");
const todoTitle = document.getElementById("todoTitle");
const todoPriority = document.getElementById("todoPriority");
const todoList = document.getElementById("todoList");
function renderTodos() {
    todoList.innerHTML = "";
    const todos = todoManager.getTodos();
    todos.forEach(todo => {
        const div = document.createElement("div");
        div.className = "todo-item";
        div.innerHTML = `
      <strong>${todo.title}</strong> [${todo.priority}]<br/>
      Status: ${todo.status}<br/>
      Created: ${todo.createdAt.toLocaleString()}
    `;
        todoList.appendChild(div);
    });
}
addBtn.onclick = () => {
    const title = todoTitle.value.trim();
    const priority = todoPriority.value;
    if (!title)
        return;
    const newTodo = {
        id: Date.now(),
        title,
        status: Status.Pending,
        priority
    };
    todoManager.add(newTodo);
    renderTodos();
    todoTitle.value = "";
};
