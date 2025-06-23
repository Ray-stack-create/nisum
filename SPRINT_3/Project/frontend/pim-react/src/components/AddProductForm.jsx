import React, { useState, useEffect } from "react";
import axios from "axios";
import "../css/AddProductForm.css";
import { Modal, Button } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";
const generateSKU = () =>
  Math.floor(100000 + Math.random() * 900000).toString();

const AddProductForm = () => {
  const { id } = useParams();
  const isEdit = Boolean(id);
  const [showModal, setShowModal] = useState(false);
  const [sku, setSku] = useState("");
  const [modalStep, setModalStep] = useState("confirm");
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    productName: "",
    status: "",
    createdDate: "",
    color: "",
    size: "",
    material: "",
    pattern: "",
    season: "",
  });

  useEffect(() => {
    if (isEdit) {
      axios
        .get(`http://localhost:8080/api/products/${id}`)
        .then((res) => {
          const p = res.data;
          setFormData({
            productName: p.productName,
            category: p.category,
            status: p.status,
            createdDate: p.createdDate?.slice(0, 10),
            color:
              p.attributes.find((a) => a.attributeName === "Color")
                ?.attributeValue || "",
            size:
              p.attributes.find((a) => a.attributeName === "Size")
                ?.attributeValue || "",
            material:
              p.attributes.find((a) => a.attributeName === "Material")
                ?.attributeValue || "",
            pattern:
              p.attributes.find((a) => a.attributeName === "Pattern")
                ?.attributeValue || "",
            season:
              p.attributes.find((a) => a.attributeName === "Season")
                ?.attributeValue || "",
          });
          setSku(p.sku);
        })
        .catch((err) => console.error("Failed to load product", err));
    } else {
      setSku(generateSKU());
    }
  }, [id, isEdit]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setShowModal(true); // Show confirmation modal
    setModalStep("confirm");
  };

  const confirmSubmit = async () => {
    const payload = { ...formData, sku };
    try {
      if (isEdit) {
        await axios.put(`http://localhost:8080/api/products/${id}`, payload);
      } else {
        await axios.post("http://localhost:8080/api/products", payload);
      }
      setModalStep("success");
    } catch (err) {
      console.error("Error:", err);
      alert("Operation failed");
      setShowModal(false);
    }
  };

  const handleClose = () => {
    setShowModal(false);
    if (modalStep === "success") {
      navigate("/dashboard");
    }
  };

  return (
    <>
      <form className="product-form" onSubmit={handleSubmit}>
        <h2>{isEdit ? "Edit Product" : "Add Product"}</h2>

        <div className="form-grid">
          <div className="form-group">
            <label>Product Name</label>
            <input
              type="text"
              name="productName"
              value={formData.productName}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label>SKU</label>
            <input type="text" value={sku} readOnly />
          </div>

          <div className="form-group">
            <label>Category</label>
            <select
              name="category"
              value={formData.category}
              onChange={handleChange}
              required
            >
              <option value="">Select Category</option>
              <option value="Mens Appearal">Mens Appearal</option>
              <option value="Womens Appearal">Womens Appearal</option>
              <option value="Kids Appearal">Kids Appearal</option>
            </select>
          </div>

          <div className="form-group">
            <label>Status</label>
            <select
              name="status"
              value={formData.status}
              onChange={handleChange}
              required
            >
              <option value="">Select status</option>
              <option value="Active">Active</option>
              <option value="Out of Stock">Out of Stock</option>
              <option value="Discontinued">Discontinued</option>
            </select>
          </div>

          <div className="form-group">
            <label>Created Date</label>
            <input
              type="date"
              name="createdDate"
              value={formData.createdDate}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label>Color</label>
            <input
              type="text"
              name="color"
              value={formData.color}
              onChange={handleChange}
            />
          </div>

          <div className="form-group">
            <label>Size</label>
            <select name="size" value={formData.size} onChange={handleChange}>
              <option value="">Choose size</option>
              <option value="S">Small</option>
              <option value="M">Medium</option>
              <option value="L">Large</option>
              <option value="XL">Extra Large</option>
            </select>
          </div>

          <div className="form-group">
            <label>Material</label>
            <input
              type="text"
              name="material"
              value={formData.material}
              onChange={handleChange}
            />
          </div>

          <div className="form-group">
            <label>Pattern</label>
            <input
              type="text"
              name="pattern"
              value={formData.pattern}
              onChange={handleChange}
            />
          </div>

          <div className="form-group">
            <label>Season</label>
            <input
              type="text"
              name="season"
              value={formData.season}
              onChange={handleChange}
            />
          </div>
        </div>

        <button type="submit" className="submit-btn">
          {isEdit ? "Edit Product" : "Save Product"}
        </button>
      </form>

      <Modal show={showModal} onHide={handleClose} centered>
        <Modal.Header closeButton>
          <Modal.Title>
            {modalStep === "confirm"
              ? isEdit
                ? "Confirm Update"
                : "Confirm Add Product"
              : "Success"}
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {modalStep === "confirm" ? (
            <p>
              {isEdit
                ? "Are you sure you want to update this product? Previous information will be lost."
                : "Are you sure you want to add this product?"}
            </p>
          ) : (
            <p>
              ✅ Product has been {isEdit ? "updated" : "added"} successfully!
            </p>
          )}
        </Modal.Body>
        <Modal.Footer>
          {modalStep === "confirm" ? (
            <>
              <Button variant="secondary" onClick={handleClose}>
                No
              </Button>
              <Button variant="primary" onClick={confirmSubmit}>
                Yes
              </Button>
            </>
          ) : (
            <Button variant="success" onClick={handleClose}>
              Close
            </Button>
          )}
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default AddProductForm;
