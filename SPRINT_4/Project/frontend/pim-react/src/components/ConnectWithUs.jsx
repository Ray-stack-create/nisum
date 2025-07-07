// src/components/ConnectWithUs.jsx
import React from 'react';
import '../css/ConnectWithUs.css';

const ConnectWithUs = () => {
  return (
    <div className="connect-wrapper">
      <h2>Connect With Us</h2>
      <p>Have questions or want to work together? Reach out below!</p>

      <form className="connect-form">
        <input type="text" placeholder="Your Name" required />
        <input type="email" placeholder="Your Email" required />
        <textarea rows="5" placeholder="Your Message..." required></textarea>
        <button type="submit">Send Message</button>
      </form>
    </div>
  );
};

export default ConnectWithUs;
