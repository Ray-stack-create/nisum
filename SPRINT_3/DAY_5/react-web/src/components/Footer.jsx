import React from 'react'
import '../css/Footer.css'

function Footer () {
  return (
    <footer className='footer'>
      <div className='footer-content'>
        <h3 className='footer-logo'>Sankhadip</h3>
        <p className='footer-tagline'>Stay organized, stay ahead </p>
        <div className='footer-links'>
          <a href='#'>About</a>
          <a href='#'>Privacy</a>
          <a href='#'>Contact</a>
        </div>
        <div className='footer-social'>
          <a href='#'>
            <i className='fab fa-facebook-f'></i>
          </a>
          <a href='#'>
            <i className='fab fa-twitter'></i>
          </a>
          <a href='#'>
            <i className='fab fa-github'></i>
          </a>
        </div>
        <p className='footer-bottom'>
          © {new Date().getFullYear()} Sankhadip Roy. All rights reserved.
        </p>
      </div>
    </footer>
  )
}

export default Footer
