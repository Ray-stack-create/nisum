import React from "react";


const AddProduct = () => {
  return (
    <div className="add-product">
      <h3> Admin: Add New Product</h3>
      <form>
        <input type="text" placeholder="Product Name" />
        <input type="number" placeholder="Price" />
        <button className="btn submit" type="submit">Add Product</button>
      </form>
    </div>
  );
};

export default AddProduct;
