import React, { createContext, useState, useContext } from "react";

const UserContext = createContext();

export const UserProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  const login = (role) => {
    setUser({
      id: 1,
      name: role === "admin" ? "Sankha Admin" : "Sayan User",
      role: role,
      isLoggedIn: true,
    });
  };

  const logout = () => setUser(null);

  return (
    <UserContext.Provider value={{ user, login, logout }}>
      {children}
    </UserContext.Provider>
  );
};

export const useUser = () => useContext(UserContext);
