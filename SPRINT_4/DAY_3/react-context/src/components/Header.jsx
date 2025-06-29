import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { logoutUser } from '../features/userSlice';
import {useTheme} from '../context/ThemeContext.jsx'

const Header = () => {
  const user = useSelector(state => state.user.user);
  const isLoggedIn = useSelector(state => state.user.isLoggedIn);
  const cartItems = useSelector(state => state.cart.items);
  const dispatch = useDispatch();
  const { theme, toggleTheme } = useTheme();
  const totalItems = cartItems.reduce((acc, item) => acc + item.quantity, 0);

  return (
    <div className="header">
      <h2>StuleQ  E-Shop</h2>
      <div>
        {isLoggedIn && (
          <>
            <span>
              Hello, {user.name}
              <span className={`role-badge ${user.role}`}>
                {user.role === 'admin' ? 'Admin' : 'User'}
              </span>{" "}
              |{" "}
            </span>
            <button className="btn logout" onClick={() => dispatch(logoutUser())}>Logout</button>
          </>
        )}
        <span className="cart-info"> {totalItems} items</span>
         <button className="btn theme-toggle" onClick={toggleTheme}>
          {theme === "light" ? "🌙 Dark" : "☀️ Light"}
        </button>
      </div>
    </div>
  );
};

export default Header;
