import React, { useState, useEffect } from "react";
import "../css/ProductList.css";
import { FaSearch, FaEye } from "react-icons/fa";
import DeleteConfirmModal from "../components/DeleteConfirmModal";
import Button from "react-bootstrap/Button";
import { Link } from "react-router-dom";
import axios from "axios";
import ProductViewModal from "../components/ProductViewModal";
import { motion, useInView } from 'framer-motion';
import { useRef } from 'react';
import AnimatedList from "./AnimatedList";



const AnimatedTableRow = ({ children, index }) => {
  const ref = useRef(null);
  const inView = useInView(ref, { amount: 0.3, triggerOnce: true });

  return (
    <motion.tr
      ref={ref}
      data-index={index}
      initial={{ opacity: 0, scale: 0.95 }}
      animate={inView ? { opacity: 1, scale: 1 } : {}}
      transition={{ duration: 0.3, delay: index * 0.05 }}
    >
      {children}
    </motion.tr>
  );
};


function ProductList() {
  const [showModal, setShowModal] = useState(false);
  const [viewProduct, setViewProduct] = useState(null);
  const [products, setProducts] = useState([]);
  const [deleteModalShow, setDeleteModalShow] = useState(false);
  const [deleteTarget, setDeleteTarget] = useState(null);
  const [isDeleted, setIsDeleted] = useState(false);
  const [searchTerm, setSearchTerm] = useState("");



  
  const handleViewClick = async (product) => {
    setViewProduct(product);
    setShowModal(true);
  };

  const handleSearch = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/products/search?q=${searchTerm}`
      );
      setProducts(response.data);
    } catch (error) {
      console.error("Search failed:", error);
    }
  };

  const handleDeleteClick = (product) => {
    setDeleteTarget(product);
    setDeleteModalShow(true);
    setIsDeleted(false);
  };

  const confirmDelete = async () => {
    try {
      await axios.delete(
        `http://localhost:8080/api/products/${deleteTarget.productId}`
      );
      setProducts(
        products.filter((p) => p.productId !== deleteTarget.productId)
      );
      setIsDeleted(true);
    } catch (err) {
      console.error("Failed to delete:", err);
    }
  };

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/products")
      .then((response) => setProducts(response.data))
      .catch((error) => console.error("Error fetching products:", error));
  }, []);

  return (
    <section className="products-list-section">
      <div className="products-list-header">
        <h2>Products List</h2>
      </div>

      <div className="products-list-header">
        <div className="button-group">
          <Link to={"/add"}>
            <Button id="addProductBtn">Add Product</Button>
          </Link>
          <Button id="bulkImportBtn">Bulk Import</Button>

          <div className="search-box">
            <input
              type="text"
              id="searchInput"
              placeholder="Search products"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />

            <Button id="searchButton" onClick={handleSearch}>
              <FaSearch />
            </Button>
          </div>
        </div>
      </div>

      <table className="products-table" id="productsTable">
        <thead>
          <tr>
            <th>Product ID</th>
            <th>Product Name</th>
            <th>SKU</th>
            <th>Category</th>
            <th>Status</th>
            <th>Last Modified</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody id="productsTableBody">
  {products.length > 0 ? (
    products.map((product, index) => (
      <AnimatedTableRow key={product.productId} index={index}>
        <td>{product.productId}</td>
        <td>{product.productName}</td>
        <td>{product.sku}</td>
        <td>{product.category}</td>
        <td>{product.status}</td>
        <td>{product.lastModifiedDate}</td>
        <td>
          <Button
            variant="outline-primary"
            size="sm"
            as={Link}
            to={`/edit/${product.productId}`}
          >
            Edit
          </Button>{" "}
          <Button
            variant="outline-danger"
            size="sm"
            onClick={() => handleDeleteClick(product)}
          >
            Delete
          </Button>
          <Button
            variant="outline-info"
            size="sm"
            onClick={() => handleViewClick(product)}
          >
            <FaEye />
          </Button>
        </td>
      </AnimatedTableRow>
    ))
  ) : (
    <tr>
      <td colSpan="6">No products found.</td>
    </tr>
  )}
</tbody>

      </table>
      <ProductViewModal
        show={showModal}
        handleClose={() => setShowModal(false)}
        product={viewProduct}
      />

      <DeleteConfirmModal
        show={deleteModalShow}
        handleClose={() => setDeleteModalShow(false)}
        handleConfirm={confirmDelete}
        isDeleted={isDeleted}
      />
    </section>
  );
}

export default ProductList;
