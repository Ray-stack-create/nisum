import React, { useEffect, useState } from "react";
import "../css/ImageSlider.css";

const ImageSlider = ({ images, delay = 3000 }) => {
  const [current, setCurrent] = useState(0);

  useEffect(() => {
    const autoSlide = setInterval(() => {
      setCurrent((prev) => (prev + 1) % images.length);
    }, delay);

    return () => clearInterval(autoSlide);
  }, [images.length, delay]);

  const handleNext = () => {
    setCurrent((current + 1) % images.length);
  };

  return (
    <div className="slider-container">
      <h1>Our Clients</h1>
      <img
        src={images[current]}
        alt={`slide-${current}`}
        className="slider-image"
      />
    </div>
  );
};

export default ImageSlider;
