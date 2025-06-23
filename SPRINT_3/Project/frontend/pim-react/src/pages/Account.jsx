import React from "react";
import Home from "../components/Home";
import Side from "../components/Side";
import Foot from "../components/Foot";
import UserProfile from "../components/UserProfile";
import greet from "../assets/greeting.png";
function Account() {
  return (
    <div className="page">
      <div className="main-layout">
        <Side />
        <div className="content">
          <h1>Welcome, Sankhadip </h1>

          <UserProfile />
        </div>
      </div>
      <Foot />
    </div>
  );
}

export default Account;
