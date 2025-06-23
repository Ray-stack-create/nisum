import React from "react";
import { Modal, Button } from "react-bootstrap";
import "../css/ModalPop.css";

const ModalPop = ({
  show,
  handleClose,
  title = "Success",
  message = "Product added successfully!",
}) => {
  return (
    <Modal show={show} onHide={handleClose} centered className="custom-modal">
      <Modal.Header closeButton>
        <Modal.Title className="modal-title-custom">{title}</Modal.Title>
      </Modal.Header>
      <Modal.Body className="modal-body-custom">
        <p>{message}</p>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="info" onClick={handleClose} className="modal-btn">
          Close
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default ModalPop;
