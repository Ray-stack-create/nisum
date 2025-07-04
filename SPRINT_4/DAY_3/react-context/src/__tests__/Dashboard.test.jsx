/* eslint-disable testing-library/no-node-access */
import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import Dashboard from "../components/Dashboard.jsx";
import { useUser } from "../context/UserContext.jsx";
import { useTheme } from "../context/ThemeContext.jsx";

jest.mock("../components/Header", () => () => <div>Mock Header</div>);
jest.mock("../components/AddProduct", () => () => <div>Mock AddProduct</div>);

jest.mock("../context/UserContext", () => ({
  useUser: jest.fn(),
}));
jest.mock("../context/ThemeContext", () => ({
  useTheme: jest.fn(),
}));

describe("Dashboard Component", () => {
  const mockLogout = jest.fn();

  beforeEach(() => {
    jest.clearAllMocks();
  });

  test("renders user info and theme class", () => {
    useUser.mockReturnValue({
      user: { name: "John", role: "user" },
      logout: mockLogout,
    });
    useTheme.mockReturnValue({ theme: "light" });

    render(<Dashboard />);

    expect(screen.getByText("Welcome, John")).toBeInTheDocument();
    expect(screen.getByText(/Role:.*user/i)).toBeInTheDocument();
    expect(screen.getByText("Mock Header")).toBeInTheDocument();
    expect(document.querySelector(".dashboard.light")).toBeInTheDocument();
    expect(screen.queryByText(/Mock AddProduct/i)).not.toBeInTheDocument();
  });

  test("renders AddProduct if user is admin", () => {
    useUser.mockReturnValue({
      user: { name: "AdminUser", role: "admin" },
      logout: mockLogout,
    });
    useTheme.mockReturnValue({ theme: "dark" });

    render(<Dashboard />);

    expect(screen.getByText("Welcome, AdminUser")).toBeInTheDocument();
    expect(screen.getByText(/Role:.*admin/i)).toBeInTheDocument();
    expect(screen.getByText("Mock AddProduct")).toBeInTheDocument();
    expect(document.querySelector(".dashboard.dark")).toBeInTheDocument();
  });

  test("calls logout when logout button is clicked", () => {
    useUser.mockReturnValue({
      user: { name: "John", role: "user" },
      logout: mockLogout,
    });
    useTheme.mockReturnValue({ theme: "light" });

    render(<Dashboard />);
    const logoutButton = screen.getByRole("button", { name: /logout/i });

    fireEvent.click(logoutButton);
    expect(mockLogout).toHaveBeenCalled();
  });
});
