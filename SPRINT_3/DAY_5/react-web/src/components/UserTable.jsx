import React, { useState } from 'react';
import StatusBadge from './StatusBadge';
import '../css/UserTable.css'

const initialUsers = [
  { name: 'Sankhadip', id: 'MG124562', phone: '628-9052', status: 'Approved' },
  { name: 'Sohani', id: 'CW719413', phone: '893-4754', status: 'Denied' },
  { name: 'Samridhi', id: 'SD78934', phone: '783-9038', status: 'Approved' },
  { name: 'Subhadeep', id: 'FV83764', phone: '934-7262', status: 'Pending' },
  { name: 'Satyaki', id: 'KM84736', phone: '235-7836', status: 'Denied' },
  
];

const statusCycle = ['Approved', 'Denied', 'Pending'];

const getNextStatus = (currentStatus) => {
  const currentIndex = statusCycle.indexOf(currentStatus);
  return statusCycle[(currentIndex + 1) % statusCycle.length];
};

const UserTable = () => {
  const [users, setUsers] = useState(initialUsers);

  const handleStatusClick = (index) => {
    const updatedUsers = [...users];
    updatedUsers[index].status = getNextStatus(updatedUsers[index].status);
    setUsers(updatedUsers);
  };

  return (
   <div className="page-wrapper">
  <div className="table-container">
    <table className="user-table">
      <thead>
        <tr>
          <th>User Name</th>
          <th>User ID</th>
          <th>User Phone</th>
          <th>Status</th>
        </tr>
      </thead>
      <tbody>
        {users.map((user, idx) => (
          <tr key={user.id}>
            <td>{user.name}</td>
            <td>{user.id}</td>
            <td>{user.phone}</td>
            <td>
              <StatusBadge
                status={user.status}
                onClick={() => handleStatusClick(idx)}
              />
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  </div>
</div>

  );
};

export default UserTable;
