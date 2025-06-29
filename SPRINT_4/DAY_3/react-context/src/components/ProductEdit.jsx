import React, { useState } from "react";


const ProductEdit = () => {
  const [product, setProduct] = useState({ name: "", price: "" });

  const handleChange = (e) => {
    setProduct({ ...product, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Updated Product:", product);
    alert("Product updated (simulated)");
  };

  return (
    <div className="product-edit">
      <h3>✏️ Edit Product</h3>
      <form onSubmit={handleSubmit}>
        <input
          name="name"
          value={product.name}
          placeholder="Product Name"
          onChange={handleChange}
        />
        <input
          name="price"
          value={product.price}
          placeholder="Price"
          type="number"
          onChange={handleChange}
        />
        <button className="btn submit" type="submit">
          Update
        </button>
      </form>
    </div>
  );
};

export default ProductEdit;
