import React from "react";
import { useDispatch } from "react-redux";
import { addToCart } from "../features/cartSlice";


const mockProducts = [
  { id: 101, name: "T-Shirt", price: 20 },
  { id: 102, name: "Shoes", price: 50 },
  { id: 103, name: "Watch", price: 80 },
];

const ProductList = () => {
  const dispatch = useDispatch();

  return (
    <div className="product-list">
      <h3>Products</h3>
      {mockProducts.map((prod) => (
        <div key={prod.id} className="product">
          <p>
            <strong>{prod.name}</strong> - ${prod.price}
          </p>
          <button className="btn add" onClick={() => dispatch(addToCart(prod))}>
            Add to Cart
          </button>
        </div>
      ))}
    </div>
  );
};

export default ProductList;
