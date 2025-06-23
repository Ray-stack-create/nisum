import React from "react";
import {
  FaTachometerAlt,
  FaExchangeAlt,
  FaBell,
  FaUser,
  FaCog,
  FaPhoneAlt,
  FaQuestionCircle,
  FaSignOutAlt,
  FaFileInvoiceDollar,
} from "react-icons/fa";
import "../css/Side.css";
import Clogo from "../assets/favicon.png";
import { Link } from "react-router-dom";

const Side = () => {
  return (
    <div className="sidebar">
      <div className="logo-section">
        <img width={40} src={Clogo} alt="" />
        <div className="logo-text">
          <span className="brand">PickNShip</span>
          <span className="subtext">Logistics Pvt Ltd</span>
        </div>
      </div>

      <ul className="sidebar-nav">
        <li>
          <Link to={"/dashboard"}>
            {" "}
            <FaTachometerAlt /> Dashboard
          </Link>
        </li>
        <li>
          <FaExchangeAlt /> Transaction
        </li>
        <li>
          <FaFileInvoiceDollar /> Bill & Tax
        </li>
        <li>
          <FaBell /> Notifications
        </li>
        <li>
          <Link to={"/account"}>
            {" "}
            <FaUser /> Account
          </Link>
        </li>
        <li>
          <FaCog /> Settings
        </li>
        <li>
          <FaPhoneAlt /> Call Center
        </li>
        <li>
          <FaQuestionCircle /> Help
        </li>
        <li>
          <Link to={"/logout"}>
            {" "}
            <FaSignOutAlt /> Log Out
          </Link>
        </li>
      </ul>
    </div>
  );
};

export default Side;
