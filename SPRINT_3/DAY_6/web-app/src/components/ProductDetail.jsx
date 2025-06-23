import React, { useEffect, useState } from "react";
import { useParams, useNavigate, Link } from "react-router-dom";
import { useCart } from "./CartContext";



export default function ProductDetail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [product, setProduct] = useState(null);
  const { addToCart } = useCart();

  useEffect(() => {
    fetch(`https://fakestoreapi.com/products/${id}`)
      .then((res) => res.json())
      .then(setProduct);
  }, [id]);

  if (!product) return <p className="text-center p-10">Loading...</p>;

  return (
    <div className="min-h-screen bg-gradient-to-r from-indigo-100 to-pink-100 p-6">
      <div className="max-w-3xl mx-auto bg-white shadow-xl rounded-xl p-6">
        {/* Breadcrumb */}
        <div className="mb-4 text-sm text-gray-600">
          <Link to="/" className="text-indigo-600 hover:underline">Home</Link> / 
          <span className="ml-1 text-gray-800">{product.title.slice(0, 30)}...</span>
        </div>

        {/* Product Detail */}
        <div className="grid md:grid-cols-2 gap-6">
          <img
            src={product.image}
            alt={product.title}
            className="w-full h-72 object-contain bg-white rounded-lg p-4"
          />

          <div>
            <h2 className="text-2xl font-bold text-indigo-700 mb-2">{product.title}</h2>
            <p className="text-gray-600 mb-4">{product.description}</p>
            <p className="text-green-600 font-bold text-xl mb-2">${product.price}</p>
            <p className="text-sm text-gray-500 mb-4">
              Stock Status: <span className="font-medium text-black">In Stock</span>
            </p>
            
            <button
              onClick={() => navigate(-1)}
              className="mt-4 px-4 py-2 bg-indigo-500 text-white rounded hover:bg-indigo-600 transition"
            >
              ← Go Back
            </button>
            <button
  onClick={() => addToCart(product)}
  className="mt-4 ml-4 px-4 py-2 bg-green-600 text-white rounded hover:bg-green-700"
>
  Add to Cart
</button>
          </div>
        </div>
      </div>
    </div>
  );
}
