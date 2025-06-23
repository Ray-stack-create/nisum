import React from "react";
import Side from "../components/Side";
import Foot from "../components/Foot";
import "../css/Home.css";
import Dashboard from "./Dashboard";

const Home = () => {
  return (
    <div className="page">
      <div className="main-layout">
        <Side />
        <div className="content">
          <Dashboard />
        </div>
      </div>
      <Foot />
    </div>
  );
};

export default Home;
