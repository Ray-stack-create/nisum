/* eslint-disable testing-library/no-node-access */
import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import Header from '../components/Header.jsx';
import { useSelector, useDispatch } from 'react-redux';
import { logoutUser } from '../features/userSlice.jsx';
import { useTheme } from '../context/ThemeContext.jsx';

jest.mock('react-redux', () => ({
  useSelector: jest.fn(),
  useDispatch: jest.fn()
}));

jest.mock('../features/userSlice', () => ({
  logoutUser: jest.fn()
}));

jest.mock('../context/ThemeContext', () => ({
  useTheme: jest.fn()
}));

describe('Header Component', () => {
  const mockDispatch = jest.fn();
  const mockToggleTheme = jest.fn();

  beforeEach(() => {
    jest.clearAllMocks();
    useDispatch.mockReturnValue(mockDispatch);
  });

  function setup({ isLoggedIn = true, role = 'user', theme = 'light', cartItems = [] }) {
    useSelector.mockImplementation((selectorFn) =>
      selectorFn({
        user: {
          user: { name: 'John', role },
          isLoggedIn
        },
        cart: { items: cartItems }
      })
    );

    useTheme.mockReturnValue({
      theme,
      toggleTheme: mockToggleTheme
    });

    render(<Header />);
  }

  test('renders store title and cart items count', () => {
    setup({ cartItems: [{ id: 1, quantity: 2 }, { id: 2, quantity: 3 }] });

    expect(screen.getByText(/StuleQ  E-Shop/i)).toBeInTheDocument();
    expect(screen.getByText(/5 items/i)).toBeInTheDocument();
  });

  test('displays user info and logout when logged in', () => {
    setup({ isLoggedIn: true });

    expect(screen.getByText(/Hello, John/i)).toBeInTheDocument();
    expect(screen.getByRole('button', { name: /logout/i })).toBeInTheDocument();
  });

  test('does not display user info when not logged in', () => {
    setup({ isLoggedIn: false });

    expect(screen.queryByText(/Hello, John/i)).not.toBeInTheDocument();
    expect(screen.queryByRole('button', { name: /logout/i })).not.toBeInTheDocument();
  });

  test('displays admin badge if user is admin', () => {
    setup({ role: 'admin' });
    expect(screen.getByText(/Admin/i)).toBeInTheDocument();
    expect(document.querySelector('.role-badge.admin')).toBeInTheDocument();
  });

  test('calls logoutUser dispatch on button click', () => {
    logoutUser.mockReturnValue({ type: 'user/logoutUser' });
    setup({});

    const logoutBtn = screen.getByRole('button', { name: /logout/i });
    fireEvent.click(logoutBtn);

    expect(logoutUser).toHaveBeenCalled();
    expect(mockDispatch).toHaveBeenCalledWith({ type: 'user/logoutUser' });
  });

  test('toggles theme on button click', () => {
    setup({ theme: 'light' });

    const toggleBtn = screen.getByRole('button', { name: /🌙 Dark/i });
    fireEvent.click(toggleBtn);

    expect(mockToggleTheme).toHaveBeenCalled();
  });
});
