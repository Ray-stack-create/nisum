import React, { useEffect, useState } from "react";
import axios from "axios";
import { Modal, Button } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";
import "../css/AddProductForm.css";

const AddProductForm = () => {
  const { id } = useParams();
  const isEdit = Boolean(id);
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    name: "",
    categoryId: "",
    sellerId: "",
    status: "",
    attributes: [
      {
        price: "",
        size: "",
        productImage: "",
      },
    ],
  });

  const [categories, setCategories] = useState([]);
  const [sellers, setSellers] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [modalStep, setModalStep] = useState("confirm");

  // Load categories, sellers, and product (if editing)
  useEffect(() => {
    axios
      .get("http://localhost:8000/categories")
      .then((res) =>
        setCategories(
          res.data.map((cat) => ({
            id: cat.id ?? cat.categoryId,
            name: cat.name,
          }))
        )
      )
      .catch((err) => console.error("Failed to load categories", err));

    axios
      .get("http://localhost:8000/sellers")
      .then((res) => setSellers(res.data))
      .catch((err) => console.error("Failed to load sellers", err));

    if (isEdit) {
      axios
        .get(`http://localhost:8000/products/${id}`)
        .then((res) => {
          const product = res.data;
          setFormData({
            name: product.name,
            categoryId: product.categoryId,
            sellerId: product.sellerId,
            status: product.status,
            attributes:
              product.attributes?.length > 0
                ? [
                    {
                      price: product.attributes[0].price,
                      size: product.attributes[0].size,
                      productImage: product.attributes[0].productImage,
                    },
                  ]
                : [
                    {
                      price: "",
                      size: "",
                      productImage: "",
                    },
                  ],
          });
        })
        .catch((err) => console.error("Failed to load product", err));
    }
  }, [id, isEdit]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleAttributeChange = (e, index) => {
    const { name, value } = e.target;
    setFormData((prev) => {
      const newAttributes = [...prev.attributes];
      newAttributes[index] = {
        ...newAttributes[index],
        [name]: value,
      };
      return { ...prev, attributes: newAttributes };
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setShowModal(true);
    setModalStep("confirm");
  };

  const confirmSubmit = async () => {
    if (!formData.categoryId || Number(formData.categoryId) <= 0) {
      alert("Please select a valid category.");
      return;
    }
    if (!formData.sellerId || Number(formData.sellerId) <= 0) {
      alert("Please select a valid seller.");
      return;
    }

    const payload = {
      name: formData.name,
      categoryId: Number(formData.categoryId),
      sellerId: Number(formData.sellerId),
      status: formData.status,
      attributes: formData.attributes.map((attr) => ({
        price: parseFloat(attr.price),
        size: attr.size,
        productImage: attr.productImage,
      })),
    };

    try {
      if (isEdit) {
        await axios.put(`http://localhost:8000/products/${id}`, payload);
      } else {
        await axios.post("http://localhost:8000/products", payload);
      }
      setModalStep("success");
    } catch (err) {
      console.error("Error:", err);
      alert(err.response?.data?.error || "Operation failed");
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
              name="name"
              value={formData.name}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label>Category</label>
            <select
              name="categoryId"
              value={formData.categoryId}
              onChange={handleChange}
              required
            >
              <option value="">Select Category</option>
              {categories.map((cat) => (
                <option
                  key={cat.id || cat.categoryId}
                  value={cat.id || cat.categoryId}
                >
                  {cat.name}
                </option>
              ))}
            </select>
          </div>

          <div className="form-group">
            <label>Seller</label>
            <select
              name="sellerId"
              value={formData.sellerId}
              onChange={handleChange}
              required
            >
              <option value="">Select Seller</option>
              {sellers.map((s) => (
                <option key={s.id} value={s.id}>
                  {s.sellerName}
                </option>
              ))}
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
              <option value="">Select Status</option>
              <option value="Active">Active</option>
              <option value="Out of Stock">Out of Stock</option>
              <option value="Discontinued">Discontinued</option>
            </select>
          </div>

          <div className="form-group">
            <label>Price</label>
            <input
              type="number"
              name="price"
              value={formData.attributes[0]?.price}
              onChange={(e) => handleAttributeChange(e, 0)}
              step="0.01"
              required
            />
          </div>

          <div className="form-group">
            <label>Size</label>
            <select
              name="size"
              value={formData.attributes[0]?.size}
              onChange={(e) => handleAttributeChange(e, 0)}
            >
              <option value="">Choose size</option>
              <option value="S">Small</option>
              <option value="M">Medium</option>
              <option value="L">Large</option>
              <option value="XL">Extra Large</option>
            </select>
          </div>

          <div className="form-group">
            <label>Image URL</label>
            <input
              type="text"
              name="productImage"
              value={formData.attributes[0]?.productImage}
              onChange={(e) => handleAttributeChange(e, 0)}
              required
            />
          </div>
        </div>

        <button type="submit" className="submit-btn">
          {isEdit ? "Update Product" : "Save Product"}
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
                ? "Are you sure you want to update this product?"
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
