import React from 'react';
import { render, screen } from '@testing-library/react';
import withAdminAccess from '../components/withAdminAccess.jsx';
import { useSelector } from 'react-redux';


jest.mock('react-redux', () => ({
  useSelector: jest.fn()
}));


const DummyComponent = () => <div>Admin Dashboard Content</div>;


const AdminProtectedComponent = withAdminAccess(DummyComponent);

describe('withAdminAccess HOC', () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });

  test('renders wrapped component for admin user', () => {
    useSelector.mockImplementation(callback =>
      callback({ user: { user: { role: 'admin', name: 'AdminUser' } } })
    );

    render(<AdminProtectedComponent />);
    expect(screen.getByText(/Admin Dashboard Content/i)).toBeInTheDocument();
  });

  test('renders access denied for non-admin user', () => {
    useSelector.mockImplementation(callback =>
      callback({ user: { user: { role: 'user', name: 'John' } } })
    );

    render(<AdminProtectedComponent />);
    expect(screen.getByText(/Access Denied/i)).toBeInTheDocument();
  });

  test('renders access denied if user is null', () => {
    useSelector.mockImplementation(callback =>
      callback({ user: { user: null } })
    );

    render(<AdminProtectedComponent />);
    expect(screen.getByText(/Access Denied/i)).toBeInTheDocument();
  });
});
