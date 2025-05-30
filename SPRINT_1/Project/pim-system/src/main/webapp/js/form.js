const form = document.querySelector('form');
const modal = document.getElementById('confirmModal');
const modalContent = modal.querySelector('.modal-content');

form.addEventListener('submit', function(event) {
  event.preventDefault(); // Prevent default form submission

  // Show the confirmation modal
  modal.style.display = 'flex';

  modalContent.innerHTML = `
    <h3>Confirm Product Addition</h3>
    <p>Are you sure you want to add this product?</p>
    <div class="modal-actions">
      <button id="confirmYes">Yes</button>
      <button id="confirmNo">No</button>
    </div>
  `;

  document.getElementById('confirmYes').addEventListener('click', async function () {
    const formData = new FormData(form);

    try {
      const response = await fetch('/pim-system/add-product', {
        method: 'POST',
        body: new URLSearchParams(formData),
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      });

      if (response.ok) {
        modalContent.innerHTML = `
          <h3>Product Added Successfully ✅</h3>
          <p>Your product has been added to the system.</p>
          <div class="modal-actions">
            <button id="okButton">OK</button>
          </div>
        `;

        document.getElementById('okButton').addEventListener('click', function () {
          modal.style.display = 'none';
          form.reset();
        });
      } else {
        modalContent.innerHTML = `
          <h3>Failed to Add Product ❌</h3>
          <p>Server responded with error. Please try again.</p>
          <div class="modal-actions">
            <button id="okButton">OK</button>
          </div>
        `;
        document.getElementById('okButton').addEventListener('click', function () {
          modal.style.display = 'none';
        });
      }

    } catch (error) {
      console.error('Error:', error);
      modalContent.innerHTML = `
        <h3>Error Occurred ⚠️</h3>
        <p>${error.message}</p>
        <div class="modal-actions">
          <button id="okButton">OK</button>
        </div>
      `;
      document.getElementById('okButton').addEventListener('click', function () {
        modal.style.display = 'none';
      });
    }
  });

  document.getElementById('confirmNo').addEventListener('click', function () {
    modal.style.display = 'none';
  });
});

// Optional: Close modal if clicked outside
window.addEventListener('click', function(e) {
  if (e.target === modal) {
    modal.style.display = 'none';
  }
});
