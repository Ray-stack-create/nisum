import React from "react";
import Landing from "../components/Landing";
import { motion } from "framer-motion";
import img1 from "../assets/logos-1-1.webp";
import img2 from "../assets/logos-2.webp";
import img3 from "../assets/logos-3.webp";
import ImageSlider from "../components/ImageSlider";
import Foot from "../components/Foot";
import ConnectWithUs from "../components/ConnectWithUs";
import WhyChooseUs from "../components/WhyChooseUs ";
import ScrollVelocity from "../components/ScrollVelocity ";
function LandingPage() {
  const images = [img1, img2, img3];
  const velocity = 100;
  return (
    
    <motion.div
      initial={{ x: 0, opacity: 1 }}
      animate={{ x: 0, opacity: 1 }}
      exit={{ x: "-100%", opacity: 0 }}
      transition={{ duration: 0.5 }}
    >
      <Landing />
      <WhyChooseUs />
      <ScrollVelocity
        texts={[
          "Smarter Shipping Starts Here",
          "Fast. Reliable. Affordable.",
          "One Platform. Limitless Growth.",
        ]}
        velocity={velocity}
        className="custom-scroll-text"
      />

      <ImageSlider images={images} delay={2000} />
      <ConnectWithUs />
      <Foot />
    </motion.div>
  );
}

export default LandingPage;
