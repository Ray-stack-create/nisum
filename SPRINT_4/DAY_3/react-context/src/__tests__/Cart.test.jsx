import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import Cart from "../components/Cart.jsx";
import { useSelector, useDispatch } from "react-redux";
import { removeFromCart, clearCart } from "../features/cartSlice";

jest.mock("react-redux", () => ({
  useSelector: jest.fn(),
  useDispatch: jest.fn(),
}));

jest.mock("../features/cartSlice", () => ({
  removeFromCart: jest.fn(),
  clearCart: jest.fn(),
}));

describe("Cart Component", () => {
  const mockDispatch = jest.fn();

  beforeEach(() => {
    useDispatch.mockReturnValue(mockDispatch);
    jest.clearAllMocks();
  });

  test("renders message when cart is empty", () => {
    useSelector.mockReturnValue([]);
    render(<Cart />);
    expect(screen.getByText(/No items in cart/i)).toBeInTheDocument();
  });

  test("displays cart items and total price", () => {
    useSelector.mockReturnValue([
      { id: 1, name: "Laptop", price: 1000, quantity: 1 },
      { id: 2, name: "Mouse", price: 50, quantity: 2 },
    ]);

    render(<Cart />);
    expect(screen.getByText(/Cart Summary/i)).toBeInTheDocument();
    expect(screen.getByText(/Laptop x 1 = \$1000/i)).toBeInTheDocument();
    expect(screen.getByText(/Mouse x 2 = \$100/i)).toBeInTheDocument();
    expect(screen.getByText(/Total: \$1100/i)).toBeInTheDocument();
  });

  test('calls removeFromCart with correct ID on "Remove" click', () => {
    useSelector.mockReturnValue([
      { id: 1, name: "Laptop", price: 1000, quantity: 1 },
    ]);
    removeFromCart.mockReturnValue({ type: "cart/removeFromCart" });

    render(<Cart />);
    const removeButton = screen.getByText(/Remove/i);
    fireEvent.click(removeButton);

    expect(removeFromCart).toHaveBeenCalledWith(1);
    expect(mockDispatch).toHaveBeenCalledWith({ type: "cart/removeFromCart" });
  });

  test('calls clearCart on "Clear Cart" click', () => {
    useSelector.mockReturnValue([
      { id: 1, name: "Laptop", price: 1000, quantity: 1 },
    ]);
    clearCart.mockReturnValue({ type: "cart/clearCart" });

    render(<Cart />);
    const clearButton = screen.getByText(/Clear Cart/i);
    fireEvent.click(clearButton);

    expect(clearCart).toHaveBeenCalled();
    expect(mockDispatch).toHaveBeenCalledWith({ type: "cart/clearCart" });
  });
});
