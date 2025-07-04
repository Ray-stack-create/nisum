import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import AddProduct from "../components/AddProduct.jsx";

describe("AddProduct Component", () => {
  test("renders the heading and form inputs", () => {
    render(<AddProduct />);

    expect(screen.getByText(/Add New Product/i)).toBeInTheDocument();

    expect(screen.getByPlaceholderText(/Product Name/i)).toBeInTheDocument();
    expect(screen.getByPlaceholderText(/Price/i)).toBeInTheDocument();

    expect(
      screen.getByRole("button", { name: /Add Product/i })
    ).toBeInTheDocument();
  });

  test("allows input in text and number fields", () => {
    render(<AddProduct />);

    const nameInput = screen.getByPlaceholderText(/Product Name/i);
    const priceInput = screen.getByPlaceholderText(/Price/i);

    fireEvent.change(nameInput, { target: { value: "Smartphone" } });
    fireEvent.change(priceInput, { target: { value: "1999" } });

    expect(nameInput.value).toBe("Smartphone");
    expect(priceInput.value).toBe("1999");
  });

  test("submits the form when Add Product button is clicked", () => {
    const handleSubmit = jest.fn((e) => e.preventDefault());
    render(
      <form onSubmit={handleSubmit}>
        <AddProduct />
      </form>
    );

    const button = screen.getByRole("button", { name: /Add Product/i });
    fireEvent.click(button);

    expect(handleSubmit).toHaveBeenCalled();
  });
});
