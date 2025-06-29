import React from "react";
import { useDispatch } from "react-redux";
import { loginUser } from "../features/userSlice";


const Login = () => {
  const dispatch = useDispatch();

  return (
    <div className="login-box">
      <h2>Select Role to Login</h2>
      <button className="btn admin" onClick={() => dispatch(loginUser({ id: 1, name: "Sankha ", role: "admin" }))}>
        Login as Admin
      </button>
      <button className="btn user" onClick={() => dispatch(loginUser({ id: 2, name: "Sayan ", role: "user" }))}>
        Login as User
      </button>
    </div>
  );
};

export default Login;
