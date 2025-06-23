import React from "react";
import "../css/Dashboard.css";
import StatusCard from "./StatusCard";
import ProductList from "./ProductList";

function Dashboard() {
  return (
    <main className="dashborad-content">
      <section className="dashboard-header">
        <h1>Products Dashboard</h1>
      </section>

      <StatusCard />
      <ProductList />

      <div id="searchResultModal" className="modal" style={{ display: "none" }}>
        <div className="modal-content">
          <span
            id="closeSearchModal"
            style={{ float: "right", cursor: "pointer" }}
          >
            &times;
          </span>
          <h2>Product Details</h2>
          <div id="searchResultContent"></div>
        </div>
      </div>
    </main>
  );
}

export default Dashboard;
