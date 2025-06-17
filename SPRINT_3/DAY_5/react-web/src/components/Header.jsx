import React, { useState } from 'react'
import '../css/Header.css'

function Header (props) {
  let [menuStatus, setmenuStatus] = useState(false)
  return (
    <div className='main-header'>
      <h1 className='Logo'>{props.name}</h1>
      <div className='header'>
        <header>
          <ul>
            <li>Home</li>
            <li>About Us</li>
            <li>Contact Us</li>
          </ul>
        </header>
      </div>
      <button className='micon' onClick={() => setmenuStatus(!menuStatus)}>
        &#9776;
      </button>
      <div className={`menu ${menuStatus ? 'activeMenu' : ''}`}>
        <ul>
          <li>
            Home{' '}
            <span className='cross' onClick={() => setmenuStatus(false)}>
              &times;
            </span>
          </li>
          <li>About Us</li>
          <li>Contact Us</li>
          <li>Projects</li>
        </ul>
      </div>
    </div>
  )
}
export default Header
