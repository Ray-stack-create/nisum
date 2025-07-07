import React from "react";
import Home from "../components/Home";
import { motion } from "framer-motion";
function Homepage() {
  return (
    <motion.div
      initial={{ x: "100%", opacity: 0 }}
      animate={{ x: 0, opacity: 1 }}
      exit={{ x: "100%", opacity: 0 }}
      transition={{ duration: 0.5 }}
    >
      <Home />
    </motion.div>
  );
}

export default Homepage;
