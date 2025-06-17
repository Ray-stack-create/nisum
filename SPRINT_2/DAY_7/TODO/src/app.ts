enum Status {
  Pending = "Pending",
  InProgress = "InProgress",
  Completed = "Completed"
}

type Priority = "High" | "Medium" | "Low";
type Nullable<T> = T | null | undefined;

interface ITodo {
  id: number;
  title: string;
  status: Status;
  priority: Priority;
  notes?: string;
}

type WithTimestamp<T> = {
  [K in keyof T]: T[K];
} & { createdAt: Date };

class TodoManager<T extends ITodo> {
  private todos: WithTimestamp<T>[] = [];

  add(todo: T): void {
    const todoWithTimestamp = {
      ...todo,
      createdAt: new Date()
    } as WithTimestamp<T>;
    this.todos.push(todoWithTimestamp);
  }

  getTodos(): WithTimestamp<T>[] {
    return this.todos;
  }
}

const todoManager = new TodoManager<ITodo>();

const addBtn = document.getElementById("addBtn") as HTMLButtonElement;
const todoTitle = document.getElementById("todoTitle") as HTMLInputElement;
const todoPriority = document.getElementById("todoPriority") as HTMLSelectElement;
const todoList = document.getElementById("todoList") as HTMLDivElement;

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
  const priority = todoPriority.value as Priority;
  if (!title) return;

  const newTodo: ITodo = {
    id: Date.now(),
    title,
    status: Status.Pending,
    priority
  };
  todoManager.add(newTodo);
  renderTodos();
  todoTitle.value = "";
};
