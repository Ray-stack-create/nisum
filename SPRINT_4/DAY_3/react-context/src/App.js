import React from "react";
import { useSelector } from "react-redux";
import Header from "./components/Header";
import Login from "./components/Login";
import ProductList from "./components/ProductList";
import Cart from "./components/Cart";
import AddProduct from "./components/AddProduct";
import AdminProductEdit from "./components/AdminProductEdit";
import "./index.css";

const App = () => {
  const { isLoggedIn, user } = useSelector((state) => state.user);

  return (
    <div className="container">
      <Header />
      {!isLoggedIn ? (
        <Login />
      ) : (
        <>
          <ProductList />
          {user.role === "admin" && <AddProduct />}
          <Cart />
          {/* Always rendered, but wrapped with HOC to restrict */}
          <AdminProductEdit />
        </>
      )}
    </div>
  );
};

export default App;
