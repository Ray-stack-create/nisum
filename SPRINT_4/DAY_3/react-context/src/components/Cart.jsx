import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { removeFromCart, clearCart } from '../features/cartSlice';


const Cart = () => {
  const items = useSelector(state => state.cart.items);
  const dispatch = useDispatch();

  const total = items.reduce((acc, item) => acc + item.price * item.quantity, 0);

  return (
    <div className="cart-box">
      <h3>Cart Summary</h3>
      {items.length === 0 ? <p>No items in cart</p> : (
        <>
          <ul>
            {items.map(item => (
              <li key={item.id}>
                {item.name} x {item.quantity} = ${item.price * item.quantity}
                <button className="btn small" onClick={() => dispatch(removeFromCart(item.id))}>Remove</button>
              </li>
            ))}
          </ul>
          <p><strong>Total: ${total}</strong></p>
          <button className="btn clear" onClick={() => dispatch(clearCart())}>Clear Cart</button>
        </>
      )}
    </div>
  );
};

export default Cart;
