import React from "react";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";

function DeleteConfirmModal({ show, handleClose, handleConfirm, isDeleted }) {
  return (
    <Modal show={show} onHide={handleClose} backdrop="static" centered>
      <Modal.Header closeButton>
        <Modal.Title>{isDeleted ? "Deleted!" : "Confirm Deletion"}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        {isDeleted
          ? "The product has been deleted successfully."
          : "Are you sure you want to delete this product?"}
      </Modal.Body>
      <Modal.Footer>
        {isDeleted ? (
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
        ) : (
          <>
            <Button variant="secondary" onClick={handleClose}>
              Cancel
            </Button>
            <Button variant="danger" onClick={handleConfirm}>
              Yes, Delete
            </Button>
          </>
        )}
      </Modal.Footer>
    </Modal>
  );
}

export default DeleteConfirmModal;
