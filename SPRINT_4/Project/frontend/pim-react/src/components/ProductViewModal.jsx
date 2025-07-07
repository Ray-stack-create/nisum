import React from "react";
import { Modal, Button } from "react-bootstrap";
import "../css/ProductViewModal.css";

const ProductViewModal = ({ show, handleClose, product }) => {
  if (!product) return null;

  const firstAttr =
    product.attributes && product.attributes.length > 0
      ? product.attributes[0]
      : null;

  return (
    <Modal
      show={show}
      onHide={handleClose}
      centered
      size="md"
      className="product-view-modal"
    >
      <Modal.Header closeButton>
        <Modal.Title>Product Details</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <div className="product-details">
          <p>
            <strong>Product ID:</strong> {product.id}
          </p>
          <p>
            <strong>Product Name:</strong> {product.name}
          </p>
          <p>
            <strong>Category:</strong> {product.categoryName}
          </p>
          <p>
            <strong>Status:</strong> {product.status}
          </p>
          <p>
            <strong>Seller ID:</strong> {product.sellerId}
          </p>
          <p>
            <strong>Last Modified:</strong> {product.lastModifiedDate || "N/A"}
          </p>

          {firstAttr && (
            <>
              <p>
                <strong>SKU:</strong> {firstAttr.sku}
              </p>
              <p>
                <strong>Price:</strong> ₹{firstAttr.price}
              </p>
              <p>
                <strong>Size:</strong> {firstAttr.size}
              </p>
              {/* <p><strong>Image:</strong> <a href={firstAttr.productImage} target="_blank" rel="noopener noreferrer">View</a></p> */}
              <p>
                <strong>Image:</strong>
              </p>
              <img
                src={firstAttr.productImage}
                alt="Product"
                onError={(e) =>
                  (e.target.src =
                    "https://via.placeholder.com/300x200?text=Image+Not+Found")
                }
                style={{
                  width: "100%",
                  maxHeight: "300px",
                  objectFit: "contain",
                  border: "1px solid #ccc",
                  borderRadius: "8px",
                }}
              />
            </>
          )}
        </div>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Close
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default ProductViewModal;
