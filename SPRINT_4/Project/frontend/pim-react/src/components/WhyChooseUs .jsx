import React from "react";
import "../css/WhyChooseUs.css";
import {
  FaTruckFast,
  FaBox,
  FaChartLine,
  FaHeadset,
  FaMoneyBillWave,
  FaHandshake,
} from "react-icons/fa6";

const WhyChooseUs = () => {
  return (
    <section className="why-section">
      <h2 className="why-title">Why Choose PickNShip?</h2>
      <p className="why-subtext">
        At <strong>PickNShip Logistics Pvt Ltd</strong>, we don't just move
        packages — we move businesses forward.
      </p>

      <div className="why-cards">
        <div className="why-card">
          <FaTruckFast className="why-icon" />
          <h3>Speed & Reliability</h3>
          <p>
            Fast, trackable, and on-time delivery ensures happy customers every
            time.
          </p>
        </div>

        <div className="why-card">
          <FaBox className="why-icon" />
          <h3>Easy Product Management</h3>
          <p>
            Our intuitive dashboard makes it simple to add, update, and track
            your products.
          </p>
        </div>

        <div className="why-card">
          <FaChartLine className="why-icon" />
          <h3>Scalable for Any Business</h3>
          <p>
            Whether you're a small startup or a big brand, our platform grows
            with you.
          </p>
        </div>

        <div className="why-card">
          <FaHeadset className="why-icon" />
          <h3>End-to-End Support</h3>
          <p>
            From listing to delivery and analytics — we’ve got your back at
            every step.
          </p>
        </div>

        <div className="why-card">
          <FaMoneyBillWave className="why-icon" />
          <h3>Cost-Effective Shipping</h3>
          <p>Save more with optimized routes and dynamic pricing models.</p>
        </div>

        <div className="why-card">
          <FaHandshake className="why-icon" />
          <h3>Trusted Nationwide</h3>
          <p>
            Join hundreds of businesses already scaling successfully with
            PickNShip.
          </p>
        </div>
      </div>
    </section>
  );
};

export default WhyChooseUs;
