import React from "react";
import { useSelector } from "react-redux";

const withAdminAccess = (WrappedComponent) => {
  return function AdminGuard(props) {
    const user = useSelector((state) => state.user.user);

    if (!user || user.role !== "admin") {
      return (
        <div style={{ padding: "1rem", color: "red", fontWeight: "bold" }}>
          ❌ Access Denied: Admins Only
        </div>
      );
    }

    return <WrappedComponent {...props} />;
  };
};

export default withAdminAccess;
