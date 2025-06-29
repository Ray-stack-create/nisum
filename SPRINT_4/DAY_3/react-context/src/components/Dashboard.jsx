import React from "react";
import { useUser } from "../context/UserContext";
import AddProduct from "./AddProduct";
import Header from "./Header";
import { useTheme } from "../context/ThemeContext";


const Dashboard = () => {
  const { user, logout } = useUser();
  const { theme } = useTheme();

  return (
    <div className={`dashboard ${theme}`}>
      <Header />
      <h1>Welcome, {user.name} </h1>
      <p>Role: <strong>{user.role}</strong></p>
      <button className="btn logout" onClick={logout}>Logout</button>
      {user.role === "admin" && <AddProduct />}
    </div>
  );
};

export default Dashboard;
