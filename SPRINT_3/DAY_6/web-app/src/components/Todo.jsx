import React, { useReducer, useState } from "react";

const initialState = [];

function reducer(state, action) {
  switch (action.type) {
    case "ADD":
      return [
        ...state,
        {
          id: Date.now(),
          text: action.payload,
          completed: false,
        },
      ];
    case "DELETE":
      return state.filter((todo) => todo.id !== action.payload);
    case "TOGGLE":
      return state.map((todo) =>
        todo.id === action.payload
          ? { ...todo, completed: !todo.completed }
          : todo
      );
    default:
      return state;
  }
}

export default function TodoApp() {
  const [todos, dispatch] = useReducer(reducer, initialState);
  const [text, setText] = useState("");

  const handleAdd = () => {
    if (text.trim()) {
      dispatch({ type: "ADD", payload: text });
      setText("");
    }
  };

  const completedCount = todos.filter((todo) => todo.completed).length;
  const pendingCount = todos.length - completedCount;

  return (
    <div className="min-h-screen bg-gradient-to-br from-purple-200 to-indigo-300 flex items-center justify-center p-4">
      <div className="bg-white shadow-2xl rounded-2xl p-6 w-full max-w-md">
        <h1 className="text-2xl font-bold mb-4 text-center text-indigo-600">
          🎯 Todo List
        </h1>

        <div className="flex mb-4">
          <input
            type="text"
            value={text}
            onChange={(e) => setText(e.target.value)}
            placeholder="Add a new task..."
            className="flex-1 px-3 py-2 border border-gray-300 rounded-l-lg focus:outline-none"
          />
          <button
            onClick={handleAdd}
            className="bg-indigo-500 text-white px-4 rounded-r-lg hover:bg-indigo-600"
          >
            Add
          </button>
        </div>

        <ul className="space-y-2 max-h-60 overflow-y-auto">
          {todos.map((todo) => (
            <li
              key={todo.id}
              className="flex justify-between items-center bg-gray-100 px-3 py-2 rounded-lg"
            >
              <span
                onClick={() => dispatch({ type: "TOGGLE", payload: todo.id })}
                className={`cursor-pointer flex-1 ${
                  todo.completed ? "line-through text-gray-500" : ""
                }`}
              >
                {todo.text}
              </span>
              <button
                onClick={() => dispatch({ type: "DELETE", payload: todo.id })}
                className="text-red-500 hover:text-red-700 ml-2"
              >
                ✖
              </button>
            </li>
          ))}
        </ul>

        <div className="mt-4 text-sm text-gray-600 flex justify-between">
          <span>✅ Completed: {completedCount}</span>
          <span> Pending: {pendingCount}</span>
        </div>
      </div>
    </div>
  );
}
