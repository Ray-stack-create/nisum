import React, { useState } from 'react';
import '../css/Notification.css'

const Notifications = ({ notifications }) => {
  const [notes, setNotes] = useState(notifications);

  const toggleReadStatus = (index) => {
    const updated = [...notes];
    updated[index].read = !updated[index].read;
    setNotes(updated);
  };

  return (
    <div className="notifications-container">
      {notes.map((note, index) => (
        <div
          key={index}
          className={`notification ${note.read ? 'read' : 'unread'}`}
          onClick={() => toggleReadStatus(index)}
        >
          <div className="note-message">{note.message}</div>
          <div className="note-meta">
            <span>{note.date}</span> • <span>{note.timestamp}</span>
            <span className="state">{note.read ? 'Read' : 'Unread'}</span>
          </div>
        </div>
      ))}
    </div>
  );
};

export default Notifications;
