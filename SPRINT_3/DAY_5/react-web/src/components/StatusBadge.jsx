import React from 'react';
import classNames from 'classnames';
import '../css/StatusBadge.css'
const StatusBadge = ({ status, onClick }) => {
  const badgeClass = classNames('badge', {
    'badge-success': status === 'Approved',
    'badge-error': status === 'Denied',
    'badge-warning': status === 'Pending',
  });

  return (
    <span className={badgeClass} onClick={onClick} role="button">
      {status}
    </span>
  );
};

export default StatusBadge;
