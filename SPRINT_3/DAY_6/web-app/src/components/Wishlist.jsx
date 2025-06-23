import React from "react";
import useWishlist from "./useWishlist";
import { Link } from "react-router-dom";

export default function Wishlist() {
  const { wishlist, removeFromWishlist } = useWishlist();

  return (
    <div className="max-w-5xl mx-auto p-6">
      <h1 className="text-2xl font-bold text-indigo-600 mb-4">💖 My Wishlist</h1>

      {wishlist.length === 0 ? (
        <p className="text-gray-500">No items in wishlist.</p>
      ) : (
        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
          {wishlist.map((product) => (
            <div key={product.id} className="bg-white p-4 rounded-xl shadow relative">
              <Link to={`/products/${product.id}`}>
                <img src={product.image} alt={product.title} className="h-40 mx-auto mb-4" />
                <h3 className="font-semibold text-lg text-indigo-700 mb-1">{product.title}</h3>
                <p className="text-gray-500 capitalize mb-1">{product.category}</p>
                <p className="text-green-600 font-bold">${product.price}</p>
              </Link>
              <button
                onClick={() => removeFromWishlist(product.id)}
                className="absolute top-2 right-2 text-xl"
              >
                ❌
              </button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
