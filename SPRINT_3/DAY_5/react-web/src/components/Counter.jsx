import React from "react";
import '../css/Counter.css'
import  { useState } from 'react'
function Counter(){

    let [count, setCount] = useState(0);
      let increment = () => {
    setCount(count + 1)
  }

  let decrement = () => {
    setCount(count - 1)
  }

  let reset = () => {
    setCount(0)
  }
    return(
<>

      <div className='count'>{count}</div>

      <button className='counter-inc' onClick={increment}>
        Increment
      </button>

      <button className='reset' onClick={reset}>
        Reset
      </button>

      <button
        className='counter-dec'
        onClick={decrement}
        disabled={count === 0}
      >
        Decrement
      </button>
</>
    );
};


export default Counter;