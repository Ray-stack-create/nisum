import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import { ThemeProvider } from "./components/ThemeContext";
import { CartProvider } from "./components/CartContext";
import Cart from "./components/Cart";
import ThemeToggle from "./components/ThemeToggle";
import Todo from "./components/Todo";
import ProductFilter from "./components/ProductFilter";
import ProductDetail from "./components/ProductDetail";
import Wishlist from "./components/Wishlist"
export default function App() {
  return (
    <BrowserRouter>
      <ThemeProvider>
        <CartProvider>
          <div className="min-h-screen bg-white dark:bg-gray-900 text-gray-900 dark:text-white p-4">
            <div className="flex justify-between mb-4 items-center">
              <ThemeToggle />
              <Link
                to="/cart"
                className="bg-indigo-500 text-white px-4 py-2 rounded hover:bg-indigo-600"
              >
                View Cart
              </Link>
            </div>

            <Routes>
              <Route
                path="/"
                element={
                  <>
                    <Todo />
                    <ProductFilter />
                
                  </>
                }
              />
              <Route path="/products/:id" element={<ProductDetail />} />
              <Route path="/cart" element={<Cart />} />
              <Route path="/wishlist" element={<Wishlist />} />
            </Routes>
          </div>
        </CartProvider>
      </ThemeProvider>
    </BrowserRouter>
  );
}
