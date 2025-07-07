import React from "react";
import Side from "../components/Side";
import Foot from "../components/Foot";
import "../css/AddProduct.css";
import AddProductForm from "./AddProductForm";

const AddProduct = () => {
  return (
    <div className="add-page">
      <div className="main-layout">
        <Side />
        <div className="content">
          <AddProductForm/>
        </div>
      </div>
      <Foot />
    </div>
  );
};

export default AddProduct;
