import React from "react";
import bg from "../assets/background.png";
import "../css/Landing.css";
import lg from "../assets/favicon.png";
import { Link } from "react-router-dom";

function Landing() {
  return (
    <div className="landing-page">
      <header className="landing-header">
        <div className="logo-section-land">
          <img width={40} src={lg} alt="" />
          <div className="logo-text">
            <span className="brand">PickNShip</span>
            <span className="subtext">Logistics Pvt Ltd</span>
          </div>
        </div>

        <nav className="landing-nav">
          <a href="#features">Features</a>
          <a href="#products">Products</a>
          <a href="#contact">Contact</a>
          <Link to={"/login"}>
            <button className="login-btn">Login</button>
          </Link>
        </nav>
      </header>

      <section className="hero-section">
        <img src={bg} alt="Landing" className="hero-image" />
      </section>
    </div>
  );
}

export default Landing;
