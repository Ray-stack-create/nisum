import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import Login from '..components/Login.jsx';
import { useDispatch } from 'react-redux';
import { loginUser } from '../features/userSlice.jsx';


jest.mock('react-redux', () => ({
  useDispatch: jest.fn()
}));


jest.mock('../features/userSlice', () => ({
  loginUser: jest.fn()
}));

describe('Login Component', () => {
  const mockDispatch = jest.fn();

  beforeEach(() => {
    jest.clearAllMocks();
    useDispatch.mockReturnValue(mockDispatch);
  });

  test('renders login buttons and heading', () => {
    render(<Login />);

    expect(screen.getByText(/Select Role to Login/i)).toBeInTheDocument();
    expect(screen.getByRole('button', { name: /Login as Admin/i })).toBeInTheDocument();
    expect(screen.getByRole('button', { name: /Login as User/i })).toBeInTheDocument();
  });

  test('dispatches loginUser as admin when admin button is clicked', () => {
    loginUser.mockReturnValue({ type: 'user/loginUser' });

    render(<Login />);
    fireEvent.click(screen.getByRole('button', { name: /Login as Admin/i }));

    expect(loginUser).toHaveBeenCalledWith({ id: 1, name: 'Sankha ', role: 'admin' });
    expect(mockDispatch).toHaveBeenCalledWith({ type: 'user/loginUser' });
  });

  test('dispatches loginUser as user when user button is clicked', () => {
    loginUser.mockReturnValue({ type: 'user/loginUser' });

    render(<Login />);
    fireEvent.click(screen.getByRole('button', { name: /Login as User/i }));

    expect(loginUser).toHaveBeenCalledWith({ id: 2, name: 'Sayan ', role: 'user' });
    expect(mockDispatch).toHaveBeenCalledWith({ type: 'user/loginUser' });
  });
});
