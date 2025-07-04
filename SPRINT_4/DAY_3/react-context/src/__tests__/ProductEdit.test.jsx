import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import ProductEdit from '../components/ProductEdit.jsx';

describe('ProductEdit Component', () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });

  test('renders input fields and button', () => {
    render(<ProductEdit />);
    expect(screen.getByPlaceholderText(/Product Name/i)).toBeInTheDocument();
    expect(screen.getByPlaceholderText(/Price/i)).toBeInTheDocument();
    expect(screen.getByRole('button', { name: /update/i })).toBeInTheDocument();
  });

  test('updates input values on change', () => {
    render(<ProductEdit />);

    const nameInput = screen.getByPlaceholderText(/Product Name/i);
    const priceInput = screen.getByPlaceholderText(/Price/i);

    fireEvent.change(nameInput, { target: { name: 'name', value: 'Laptop' } });
    fireEvent.change(priceInput, { target: { name: 'price', value: '1000' } });

    expect(nameInput.value).toBe('Laptop');
    expect(priceInput.value).toBe('1000');
  });

  test('submits form and logs/alerts product', () => {
    const logSpy = jest.spyOn(console, 'log').mockImplementation(() => {});
    const alertSpy = jest.spyOn(window, 'alert').mockImplementation(() => {});

    render(<ProductEdit />);

    const nameInput = screen.getByPlaceholderText(/Product Name/i);
    const priceInput = screen.getByPlaceholderText(/Price/i);
    const submitBtn = screen.getByRole('button', { name: /update/i });

    fireEvent.change(nameInput, { target: { name: 'name', value: 'Tablet' } });
    fireEvent.change(priceInput, { target: { name: 'price', value: '200' } });

    fireEvent.click(submitBtn);

    expect(logSpy).toHaveBeenCalledWith('Updated Product:', { name: 'Tablet', price: '200' });
    expect(alertSpy).toHaveBeenCalledWith('Product updated (simulated)');
  });
});
