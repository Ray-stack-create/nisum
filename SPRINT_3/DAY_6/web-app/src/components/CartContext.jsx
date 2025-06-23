import React, { createContext, useReducer, useContext } from "react";

const CartContext = createContext();

const initialState = [];

function cartReducer(state, action) {
  switch (action.type) {
    case "ADD":
      const exists = state.find((item) => item.id === action.payload.id);
      if (exists) {
        return state.map((item) =>
          item.id === action.payload.id
            ? { ...item, quantity: item.quantity + 1 }
            : item
        );
      }
      return [...state, { ...action.payload, quantity: 1 }];

    case "REMOVE":
      return state.filter((item) => item.id !== action.payload);

    case "CLEAR":
      return [];

    case "INCREMENT":
      return state.map((item) =>
        item.id === action.payload
          ? { ...item, quantity: item.quantity + 1 }
          : item
      );

    case "DECREMENT":
      return state.map((item) =>
        item.id === action.payload
          ? { ...item, quantity: Math.max(1, item.quantity - 1) }
          : item
      );

    default:
      return state;
  }
}


export function CartProvider({ children }) {
  const [cart, dispatch] = useReducer(cartReducer, initialState);

  const addToCart = (product) => dispatch({ type: "ADD", payload: product });
  const removeFromCart = (id) => dispatch({ type: "REMOVE", payload: id });
  const clearCart = () => dispatch({ type: "CLEAR" });

const increment = (id) => dispatch({ type: "INCREMENT", payload: id });
const decrement = (id) => dispatch({ type: "DECREMENT", payload: id });

return (
  <CartContext.Provider
    value={{ cart, addToCart, removeFromCart, clearCart, increment, decrement }}
  >
    {children}
  </CartContext.Provider>
);

}

export const useCart = () => useContext(CartContext);
