// import React, { useEffect, useState } from "react";
// import "../css/StatusCard.css";
// import axios from "axios";
// import dayjs from "dayjs";

// function StatusCard() {
//   const [totalProducts, setTotalProducts] = useState(0);
//   const [recentUpdates, setRecentUpdates] = useState(0);

//   useEffect(() => {
//     axios
//       .get("http://localhost:8080/api/products")
//       .then((response) => {
//         const products = response.data;
//         setTotalProducts(products.length);

//         const sevenDaysAgo = dayjs().subtract(7, "day");
//         const recent = products.filter((product) =>
//           dayjs(product.lastModifiedDate).isAfter(sevenDaysAgo)
//         );
//         setRecentUpdates(recent.length);
//       })
//       .catch((err) => console.error("Error fetching stats:", err));
//   }, []);

//   return (
//     <section className="stats-section">
//       <div className="stats">
//         <div className="stat-card">
//           <div className="stat-label">Total Products</div>
//           <div className="stat-value">{totalProducts}</div>
//         </div>
//         <div className="stat-card">
//           <div className="stat-label">Recently Updated</div>
//           <div className="stat-value">{recentUpdates}</div>
//         </div>
//       </div>
//     </section>
//   );
// }

// export default StatusCard;




import React, { useEffect, useState } from "react";
import "../css/StatusCard.css";
import axios from "axios";

function StatusCard() {
  const [totalProducts, setTotalProducts] = useState(0);
  const [recentUpdates, setRecentUpdates] = useState(0);

  useEffect(() => {
    axios
      .get("http://localhost:8000/products?page=1&size=1")
      .then((response) => {
        const result = response.data;

        setTotalProducts(result?.totalProductCount || 0);
        setRecentUpdates(result?.recentlyUpdatedProductCount || 0);
      })
      .catch((err) => {
        console.error("Error fetching product stats:", err);
      });
  }, []);

  return (
    <section className="stats-section">
      <div className="stats">
        <div className="stat-card">
          <div className="stat-label">Total Products</div>
          <div className="stat-value">{totalProducts}</div>
        </div>
        <div className="stat-card">
          <div className="stat-label">Recently Updated (24h)</div>
          <div className="stat-value">{recentUpdates}</div>
        </div>
      </div>
    </section>
  );
}

export default StatusCard;

