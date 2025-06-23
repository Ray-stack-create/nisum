import React from 'react';
import { Modal, Button } from 'react-bootstrap';
import '../css/ProductViewModal.css';

const ProductViewModal = ({ show, handleClose, product }) => {
  if (!product) return null;

 
  const attributeMap = {};
  if (product.attributes && Array.isArray(product.attributes)) {
    product.attributes.forEach(attr => {
      attributeMap[attr.attributeName.toLowerCase()] = attr.attributeValue;
    });
  }

  return (
    <Modal show={show} onHide={handleClose} centered size="md" className="product-view-modal">
      <Modal.Header closeButton>
        <Modal.Title>Product Details</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <div className="product-details">
          <p><strong>Product ID:</strong> {product.productId}</p>
          <p><strong>Product Name:</strong> {product.productName}</p>
          <p><strong>SKU:</strong> {product.sku}</p>
          <p><strong>Category:</strong> {product.category}</p>
          <p><strong>Status:</strong> {product.status}</p>
          <p><strong>Created Date:</strong> {product.createdDate || 'N/A'}</p>
          <p><strong>Last Modified:</strong> {product.lastModifiedDate || 'N/A'}</p>
          <p><strong>Color:</strong> {attributeMap.color || 'N/A'}</p>
          <p><strong>Size:</strong> {attributeMap.size || 'N/A'}</p>
          <p><strong>Material:</strong> {attributeMap.material || 'N/A'}</p>
          <p><strong>Pattern:</strong> {attributeMap.pattern || 'N/A'}</p>
          <p><strong>Season:</strong> {attributeMap.season || 'N/A'}</p>
        </div>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>Close</Button>
      </Modal.Footer>
    </Modal>
  );
};

export default ProductViewModal;
