const form = document.querySelector('form')
const modal = document.getElementById('confirmModal')
const modalContent = modal.querySelector('.modal-content')

// Sidebar navigation
document.querySelectorAll('ul li').forEach(li => {
  li.addEventListener('click', () => {
    const label = li.textContent.trim()
    if (label === 'Dashboard') {
      window.location.href = '/pim-system/'
    } else if (label === 'Products') {
      window.location.href = '/pim-system/pages/form.html'
    } else if (label === 'Product Attribute') {
      window.location.href = '/pim-system/pages/attribute.html'
    }
  })
})

form.addEventListener('submit', function (event) {
  event.preventDefault()

  // Show confirmation modal
  modal.style.display = 'flex'
  modalContent.innerHTML = `
        <h3>Confirm Attribute Submission</h3>
        <p>Are you sure you want to submit these attributes?</p>
        <div class="modal-actions">
            <button id="confirmYes">Yes</button>
            <button id="confirmNo">No</button>
        </div>
    `

  modalContent
    .querySelector('#confirmYes')
    .addEventListener('click', async function () {
      const formData = new FormData(form)
      const productId = formData.get('productId')
      const attributes = []

      // Collect all attributeName/value pairs
      const names = formData.getAll('attributeName')
      const values = formData.getAll('attributeValue')

      for (let i = 0; i < names.length; i++) {
        attributes.push({
          attributeName: names[i],
          attributeValue: values[i]
        })
      }

      const payload = {
        productId: parseInt(productId),
        attributes: attributes
      }
      console.log('📦 Sending Payload:', JSON.stringify(payload, null, 2))

      try {
        const response = await fetch(
          '/pim-system/product/attribute-add-multiple',
          {
            method: 'POST',
            body: JSON.stringify(payload),
            headers: {
              'Content-Type': 'application/json'
            }
          }
        )

        if (response.ok) {
          modalContent.innerHTML = `
                    <h3>✅ Attributes Added</h3>
                    <p>The attributes were successfully added to the product.</p>
                    <div class="modal-actions">
                        <button id="okButton">OK</button>
                    </div>
                `
          modalContent
            .querySelector('#okButton')
            .addEventListener('click', () => {
              modal.style.display = 'none'
              form.reset()
            })
        } else {
          modalContent.innerHTML = `
                    <h3>❌ Submission Failed</h3>
                    <p>The server could not process your request. Please try again.</p>
                    <div class="modal-actions">
                        <button id="okButton">OK</button>
                    </div>
                `
          modalContent
            .querySelector('#okButton')
            .addEventListener('click', () => {
              modal.style.display = 'none'
            })
        }
      } catch (error) {
        modalContent.innerHTML = `
                <h3>⚠️ Error</h3>
                <p>${error.message}</p>
                <div class="modal-actions">
                    <button id="okButton">OK</button>
                </div>
            `
        modalContent
          .querySelector('#okButton')
          .addEventListener('click', () => {
            modal.style.display = 'none'
          })
      }
    })

  modalContent.querySelector('#confirmNo').addEventListener('click', () => {
    modal.style.display = 'none'
  })
})

// Close modal when clicking outside it
window.addEventListener('click', function (e) {
  if (e.target === modal) {
    modal.style.display = 'none'
  }
})
