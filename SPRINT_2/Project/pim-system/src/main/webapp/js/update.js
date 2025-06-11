const form = document.querySelector('form')
const modal = document.getElementById('confirmModal')
const modalContent = modal.querySelector('.modal-content')

document.addEventListener('DOMContentLoaded', () => {
  const params = new URLSearchParams(window.location.search)
  const productId = params.get('productId')

  // Fill visible form inputs
  document.getElementById('productId').value = productId
  document.getElementById('category').value = params.get('category')
  document.getElementById('status').value = params.get('status')
  document.getElementById('date').value = params.get('date')

  // Fetch product name via API
  fetch(`/pim-system/product/${productId}`)
    .then(res => {
      if (!res.ok) throw new Error('Failed to fetch product details')
      return res.json()
    })
    .then(product => {
      document.getElementById('productName').value = product.productName
    })
    .catch(err => alert('Could not load product name: ' + err.message))

  // Sidebar Navigation
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

  // Modal elements
  const form = document.getElementById('editProductForm')
  const modal = document.getElementById('confirmModal')
  const modalContent = modal.querySelector('.modal-content')

  form.addEventListener('submit', function (e) {
    e.preventDefault()

    // Show confirm modal
    modal.style.display = 'flex'
    modalContent.innerHTML = `
            <h3>Confirm Update</h3>
            <p>Are you sure you want to update this product?</p>
            <div class="modal-actions">
                <button id="confirmYes">Yes</button>
                <button id="confirmNo">No</button>
            </div>
        `

    document.getElementById('confirmYes').addEventListener('click', () => {
      const formData = new URLSearchParams()
      formData.append('productId', document.getElementById('productId').value)
      formData.append(
        'productName',
        document.getElementById('productName').value
      )
      formData.append('category', document.getElementById('category').value)
      formData.append('status', document.getElementById('status').value)

      const dateInput = document.getElementById('date').value
      const fullDate = `${dateInput} 00:00:00`
      formData.append('date', fullDate)

      const contextPath = window.location.pathname.split('/')[1]

      fetch(`/${contextPath}/product/update`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: formData.toString()
      })
        .then(res => {
          if (!res.ok) throw new Error('Update failed')

          modalContent.innerHTML = `
                    <h3>Product Updated ✅</h3>
                    <p>Product was successfully updated.</p>
                    <div class="modal-actions">
                        <button id="okButton">OK</button>
                    </div>
                `
          document.getElementById('okButton').addEventListener('click', () => {
            modal.style.display = 'none'
          })
        })
        .catch(err => {
          modalContent.innerHTML = `
                    <h3>Update Failed ❌</h3>
                    <p>${err.message}</p>
                    <div class="modal-actions">
                        <button id="okButton">OK</button>
                    </div>
                `
          document.getElementById('okButton').addEventListener('click', () => {
            modal.style.display = 'none'
          })
        })
    })

    document.getElementById('confirmNo').addEventListener('click', () => {
      modal.style.display = 'none'
    })
  })

  // Optional: close modal when clicking outside
  window.addEventListener('click', function (e) {
    if (e.target === modal) {
      modal.style.display = 'none'
    }
  })
})
