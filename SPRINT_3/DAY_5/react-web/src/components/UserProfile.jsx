import React from 'react';
import '../css/UserProfile.css'
import defaultAvatar from '../product/images.png'; // use a placeholder avatar image

const UserProfile = ({ name = "Unknown", email = "Not Provided", avatarUrl, bio = "No bio available" }) => {
    return (
        <div className="user-card">
            <img className="avatar" src={avatarUrl || defaultAvatar} alt="User Avatar" />
            <h2>{name}</h2>
            <p className="email">{email}</p>
            <div className="bio">{bio}</div>
        </div>
    );
};

export default UserProfile;
