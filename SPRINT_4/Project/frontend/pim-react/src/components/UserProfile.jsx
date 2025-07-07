import React from 'react';
import '../css/UserProfile.css'
import defaultAvatar from '../assets/images.png'; // use a placeholder avatar image

const UserProfile = ({ name = "Unknown", email = "Not Provided", avatarUrl, bio = "No bio available" }) => {
    return (
     
        <div className="user-card">
            <img className="avatar" src={avatarUrl || defaultAvatar} alt="User Avatar" />
            <h2>Sankhadip</h2>
            <p className="email">sankhadip@gmail</p>
            <div className="bio">Employee Id: 54673LK<br/>
            Departement: Management<br/>
            Contact: +91 8364894384</div>
            <div className="button-group">
                <button className="profile-button">Edit Profile</button>
                <button className="profile-button secondary">Switch Account</button>
            </div>
        </div>
        
    );
};

export default UserProfile;
