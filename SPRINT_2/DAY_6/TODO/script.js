 import { getSavedTasks, saveTasks } from './module.js';

    class TaskManager {
      constructor() {
        this.tasks = [...getSavedTasks()];
        this.renderTasks();
      }

      addTask = (task = { text: '', completed: false }) => {
        const { text, completed } = task;
        if (text.trim()) {
          this.tasks.push({ text, completed });
          this.updateView();
        }
      }

      deleteTask = index => {
        this.tasks.splice(index, 1);
        this.updateView();
      }

      toggleTask = index => {
        this.tasks[index].completed = !this.tasks[index].completed;
        this.updateView();
      }

      updateView = () => {
        saveTasks(this.tasks);
        this.renderTasks();
      }

      renderTasks = () => {
        const list = document.getElementById('taskList');
        list.innerHTML = '';

        for (const [index, task] of this.tasks.entries()) {
          const { text, completed } = task;
          const li = document.createElement('li');
          li.className = completed ? 'completed' : '';
          li.innerHTML = `${text} <button>Delete</button>`;
          li.addEventListener('click', () => this.toggleTask(index));
          li.querySelector('button').addEventListener('click', e => {
            e.stopPropagation();
            this.deleteTask(index);
          });
          list.appendChild(li);
        }
      }
    }

    const manager = new TaskManager();

    document.getElementById('addBtn').addEventListener('click', () => {
      const input = document.getElementById('taskInput');
      const value = input.value;
      manager.addTask({ text: value });
      input.value = '';
    });

    // Symbol usage
    const UNIQUE = Symbol('uniqueID');
    const obj = { [UNIQUE]: 'symbol-value' };

    // Spread/Rest
    const showTaskCount = (...args) => console.log(`Total tasks: ${args.length}`);
    showTaskCount(...manager.tasks);

    // Object.assign / Object.is
    const original = { a: 1 }, copy = Object.assign({}, original);
    console.log('Same?', Object.is(original, copy));

    // Map, Set, WeakMap, WeakSet
    const taskMap = new Map();
    manager.tasks.forEach((t, i) => taskMap.set(i, t));
    const taskSet = new Set(manager.tasks.map(t => t.text));
    const weakMap = new WeakMap();
    const weakSet = new WeakSet();
    weakMap.set({}, 'temp');
    weakSet.add({});

    // Generators
    function* taskGenerator(tasks) {
      for (const task of tasks) yield task;
    }
    const gen = taskGenerator(manager.tasks);
    for (const task of gen) {
      console.log('Generated:', task);
    }

    // Array.of / Array.from
    const arrFrom = Array.from(taskMap.values());
    const arrOf = Array.of(...manager.tasks);
    console.log('Array.from:', arrFrom, 'Array.of:', arrOf);

    // Destructuring / Enhanced Object Literal
    const exampleTask = { text: 'Test', completed: false };
    const { text, completed } = exampleTask;
    const enhancedObj = {
      text,
      completed,
      status() {
        return completed ? 'Done' : 'Pending';
      }
    };
    console.log('Enhanced Object:', enhancedObj);
