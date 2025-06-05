const modalBackdrop = document.getElementById('modalBackdrop')
const addProductModal = document.getElementById('addProductModal')
const bulkImportModal = document.getElementById('bulkImportModal')

//Sidebar Fuctionality
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

document.addEventListener('DOMContentLoaded', function () {
  loadProducts()

  document.getElementById('addProductBtn')?.addEventListener('click', () => {
    const contextPath = window.location.pathname.split('/')[1] || ''
    window.location.href = `/${contextPath}/pages/form.html`
  })

  document.getElementById('bulkImportBtn')?.addEventListener('click', () => {
    modalBackdrop.style.display = 'block'
    bulkImportModal.style.display = 'flex'
  })

  modalBackdrop?.addEventListener('click', closeModal)
})

//Dynamic list and delete functionality
function loadProducts () {
  fetch('/pim-system/get-products')
    .then(res => res.json())
    .then(data => {
      document.getElementById('total-products').textContent = data.totalCount
      document.getElementById('recent-updated').textContent = data.recentCount
      const tbody = document.querySelector('#productsTable tbody')
      tbody.innerHTML = ''
      data.products.forEach(product => {
        const row = document.createElement('tr')
        row.innerHTML = `
          <td>${product.productId}</td>
          <td>${product.productName}</td>
          <td>${product.category}</td>
          <td>${product.status}</td>
          <td>${product.createdDate}</td>
          <td>
            <button class="edit-btn">✏️</button>
            <button class="delete-btn" data-id="${product.productId}">🗑️</button>
          </td>
        `
        tbody.appendChild(row)
      })

      document.querySelectorAll('.delete-btn').forEach(btn => {
        btn.addEventListener('click', function () {
          const id = this.getAttribute('data-id')
          if (!confirm('Are you sure you want to delete this product?')) {
            return
          }
          fetch('/pim-system/delete-product', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: `productId=${id}`
          }).then(res => {
            if (res.ok) {
              loadProducts()
            } else {
              alert('Failed to delete product.')
            }
          })
        })
      })

      // Edit functionality
      document.querySelectorAll('.edit-btn').forEach(btn => {
        btn.addEventListener('click', function () {
          const row = this.closest('tr')
          const productId = row.children[0].textContent
          const currentCategory = row.children[2].textContent
          const currentStatus = row.children[3].textContent
          const currentDate = row.children[4].textContent

          const contextPath = window.location.pathname.split('/')[1] || ''
          window.location.href = `/${contextPath}/pages/update.html?productId=${productId}&category=${encodeURIComponent(
            currentCategory
          )}&status=${encodeURIComponent(
            currentStatus
          )}&date=${encodeURIComponent(currentDate)}`
        })
      })
    })
}

//Search funtionaity
document.getElementById('searchButton').addEventListener('click', () => {
  const query = document.getElementById('searchInput').value.trim()
  if (!query) return alert('Enter a product ID to search')

  fetch(`/pim-system/get-product-by-id?productId=${query}`)
    .then(res => {
      if (!res.ok) throw new Error('Product not found')
      return res.json()
    })
    .then(product => {
      const content = `
        <p><strong>Product ID:</strong> ${product.productId}</p>
        <p><strong>Name:</strong> ${product.productName}</p>
        <p><strong>Category:</strong> ${product.category}</p>
        <p><strong>Status:</strong> ${product.status}</p>
        <p><strong>Created Date:</strong> ${product.createdDate}</p>
      `
      document.getElementById('searchResultContent').innerHTML = content
      document.getElementById('searchResultModal').style.display = 'flex'
    })
    .catch(err => {
      alert(err.message)
    })
})

document.getElementById('closeSearchModal').addEventListener('click', () => {
  document.getElementById('searchResultModal').style.display = 'none'
})

function closeModal () {
  modalBackdrop.style.display = 'none'
  addProductModal.style.display = 'none'
  bulkImportModal.style.display = 'none'
}
