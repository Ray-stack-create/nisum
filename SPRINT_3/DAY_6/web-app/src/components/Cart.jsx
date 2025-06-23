import React, { useState } from "react";
import { useCart } from "./CartContext";

export default function Cart() {
  const { cart, removeFromCart, clearCart, increment, decrement } = useCart();
  const [updatingItemId, setUpdatingItemId] = useState(null);

  const total = cart.reduce((sum, item) => sum + item.price * item.quantity, 0);

  return (
    <div className="max-w-3xl mx-auto bg-white dark:bg-gray-800 p-6 rounded-xl shadow-lg mt-6">
      <h1 className="text-2xl font-bold mb-4 text-indigo-600">🛒 Your Cart</h1>

      {cart.length === 0 ? (
        <p className="text-gray-500">Cart is empty.</p>
      ) : (
        <>
          <ul className="space-y-4">
            {cart.map((item) => (
              <li
                key={item.id}
                className="flex justify-between items-center border-b pb-2"
              >
                <div className="w-2/3">
                  <p className="font-semibold">{item.title}</p>
                  <p className="text-sm text-gray-600">
                    ${item.price} × {item.quantity}
                  </p>

                  <div className="mt-2 flex items-center gap-2">
                    <button
                      onClick={() => {
                        setUpdatingItemId(item.id);
                        setTimeout(() => {
                          decrement(item.id);
                          setUpdatingItemId(null);
                        }, 300);
                      }}
                      disabled={updatingItemId === item.id}
                      className="px-2 py-1 bg-gray-300 text-black rounded disabled:opacity-50"
                    >
                      −
                    </button>

                    <span className="px-2">{item.quantity}</span>

                    <button
                      onClick={() => {
                        setUpdatingItemId(item.id);
                        setTimeout(() => {
                          increment(item.id);
                          setUpdatingItemId(null);
                        }, 300);
                      }}
                      disabled={updatingItemId === item.id}
                      className="px-2 py-1 bg-gray-300 text-black rounded disabled:opacity-50"
                    >
                      ＋
                    </button>
                  </div>
                </div>

                <div className="text-right">
                  <p className="text-sm text-gray-600">
                    Subtotal: ${(item.price * item.quantity).toFixed(2)}
                  </p>
                  <button
                    onClick={() => removeFromCart(item.id)}
                    className="text-red-500 hover:underline mt-2 text-sm"
                  >
                    Remove
                  </button>
                </div>
              </li>
            ))}
          </ul>

          <div className="mt-6 flex justify-between items-center">
            <strong className="text-lg">Total: ${total.toFixed(2)}</strong>
            <button
              onClick={clearCart}
              className="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600"
            >
              Clear Cart
            </button>
          </div>
        </>
      )}
    </div>
  );
}
