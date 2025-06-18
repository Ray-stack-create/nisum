const API_URL = '/api/customers';

document.addEventListener('DOMContentLoaded', () => {
    loadCustomers();

    document.getElementById('customerForm').addEventListener('submit', async (e) => {
        e.preventDefault();
        const name = document.getElementById('name').value;
        const email = document.getElementById('email').value;
        const registeredDate = document.getElementById('registeredDate').value;

        await fetch(API_URL, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({ name, email, registeredDate })
        });

        e.target.reset();
        loadCustomers();
    });
});

async function loadCustomers() {
    const res = await fetch(API_URL);
    const customers = await res.json();
    const tbody = document.querySelector('#customerTable tbody');
    tbody.innerHTML = '';

    customers.forEach(customer => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${customer.id}</td>
            <td>${customer.name}</td>
            <td>${customer.email}</td>
            <td>${customer.registeredDate}</td>
            <td><button class="delete" onclick="deleteCustomer(${customer.id})">Delete</button></td>
        `;
        tbody.appendChild(tr);
    });
}

async function deleteCustomer(id) {
    await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
    loadCustomers();
}
