import React, { useState, useEffect, useRef } from "react";
import "../css/ProductList.css";
import { FaSearch, FaEye } from "react-icons/fa";
import DeleteConfirmModal from "../components/DeleteConfirmModal";
import Button from "react-bootstrap/Button";
import { Link } from "react-router-dom";
import axios from "axios";
import ProductViewModal from "../components/ProductViewModal";
import { motion, useInView } from "framer-motion";

const AnimatedTableRow = ({ children, index }) => {
  const ref = useRef(null);
  const inView = useInView(ref, { amount: 0.3, triggerOnce: true });

  return (
    <motion.tr
      ref={ref}
      initial={{ opacity: 0, scale: 0.95 }}
      animate={inView ? { opacity: 1, scale: 1 } : {}}
      transition={{ duration: 0.3, delay: index * 0.05 }}
    >
      {children}
    </motion.tr>
  );
};

function ProductList() {
  const [products, setProducts] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [viewProduct, setViewProduct] = useState(null);
  const [deleteModalShow, setDeleteModalShow] = useState(false);
  const [deleteTarget, setDeleteTarget] = useState(null);
  const [isDeleted, setIsDeleted] = useState(false);
  const [searchTerm, setSearchTerm] = useState("");

  const [currentPage, setCurrentPage] = useState(1);
  const pageSize = 5;
  const [totalProducts, setTotalProducts] = useState(0);

  // Fetch products whenever page or search changes
  useEffect(() => {
    const fetchProducts = async () => {
      console.log("Fetching page:", currentPage);

      try {
        const response = await axios.get("http://localhost:8000/products", {
          params: {
            page: currentPage,
            size: pageSize,
            search: searchTerm.trim(),
          },
        });

        const data = response.data;
        console.log("Backend response:", data);

        setProducts(data.products || []);
        setTotalProducts(
          data.totalProductCount || data.totalCount || data.totalElements || 0
        );
      } catch (error) {
        console.error("Error fetching products:", error);
      }
    };

    fetchProducts();
  }, [currentPage, searchTerm]);

  const handleSearch = () => {
    setCurrentPage(1); // reset to first page
  };

  const handleViewClick = (product) => {
    setViewProduct(product);
    setShowModal(true);
  };

  const handleDeleteClick = (product) => {
    setDeleteTarget(product);
    setDeleteModalShow(true);
    setIsDeleted(false);
  };

  const confirmDelete = async () => {
    try {
      await axios.delete(`http://localhost:8000/products/${deleteTarget.id}`);
      setIsDeleted(true);
      setCurrentPage(1); // reload first page after delete
    } catch (err) {
      console.error("Failed to delete:", err);
    }
  };

  const totalPages = Math.ceil(totalProducts / pageSize);

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
            <th>ID</th>
            <th>Name</th>
            <th>SKU</th>
            <th>Category</th>
            <th>Status</th>
            <th>Last Modified</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {products.length > 0 ? (
            products.map((product, index) => (
              <AnimatedTableRow key={product.id} index={index}>
                <td>{product.id}</td>
                <td>{product.name}</td>
                <td>{product.attributes?.[0]?.sku || "—"}</td>
                <td>{product.categoryName || "—"}</td>
                <td>{product.status}</td>
                <td>{product.lastModifiedDate?.slice(0, 10)}</td>
                <td>
                  <Button
                    variant="outline-primary"
                    size="sm"
                    as={Link}
                    to={`/edit/${product.id}`}
                  >
                    Edit
                  </Button>{" "}
                  <Button
                    variant="outline-danger"
                    size="sm"
                    onClick={() => handleDeleteClick(product)}
                  >
                    Delete
                  </Button>{" "}
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
              <td colSpan="7">No products found.</td>
            </tr>
          )}
        </tbody>
      </table>

      {/* Pagination Controls */}
      <div className="pagination-controls">
        <Button
          disabled={currentPage === 1}
          onClick={() => setCurrentPage((prev) => prev - 1)}
        >
          Previous
        </Button>
        <span style={{ margin: "0 15px" }}>
          Page {currentPage} of {totalPages || 1}
        </span>
        <Button
          disabled={currentPage >= totalPages}
          onClick={() => setCurrentPage((prev) => prev + 1)}
        >
          Next
        </Button>
      </div>

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
