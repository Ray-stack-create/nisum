document.addEventListener('DOMContentLoaded', () => {
  const params = new URLSearchParams(window.location.search)
  document.getElementById('productId').value = params.get('productId')
  document.getElementById('category').value = params.get('category')
  document.getElementById('status').value = params.get('status')
  document.getElementById('date').value = params.get('date')

  document
    .getElementById('editProductForm')
    .addEventListener('submit', function (e) {
      e.preventDefault()

      const formData = new URLSearchParams()
      formData.append('productId', document.getElementById('productId').value)
      formData.append('category', document.getElementById('category').value)
      formData.append('status', document.getElementById('status').value)
      formData.append('date', document.getElementById('date').value)

      // Get base path (context path)
      const contextPath = window.location.pathname.split('/')[1]

      fetch(`/${contextPath}/update-product`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: formData.toString()
      })
        .then(res => {
          if (!res.ok) throw new Error('Update failed')
          alert('Product updated successfully!')
          //window.location.href = `/${contextPath}/pages/homepage.html`;
        })
        .catch(err => alert(err.message))
    })
})
