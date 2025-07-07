import React from 'react';
import { Routes, Route, useLocation } from 'react-router-dom';
import { AnimatePresence } from 'framer-motion';
import Homepage from './pages/Homepage';
import LandingPage from './pages/LandingPage';
import Account from './pages/Account';
import AddProductPage from './pages/AddProductPage';

function App() {
  const location = useLocation();

  return (
    
    <AnimatePresence mode="wait">
      <Routes location={location} key={location.pathname}>
        <Route path="/" element={<LandingPage />} />
        <Route path="/login" element={<Homepage />} />
        <Route path="/dashboard" element={<Homepage />} />
        <Route path="/account" element={<Account />} />
        <Route path="/logout" element={<LandingPage />} />
        <Route path="/add" element={<AddProductPage />} />
        <Route path="/edit/:id" element={<AddProductPage />} />
      </Routes>
    </AnimatePresence>
  );
}

export default App;
