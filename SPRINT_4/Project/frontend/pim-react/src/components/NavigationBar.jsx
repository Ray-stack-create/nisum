import "../css/NavigationBar.css";
function NavigationBar() {
  return (
    <div className="main-container">
      <aside class="sidebar">
        <nav>
          <h2>Product Info Management System</h2>
          <ul>
            <li className="dashboard">Dashboard</li>
            <li className="products">Products</li>
            <li className="attributes">Product Attribute</li>
          </ul>
        </nav>
      </aside>
    </div>
  );
}

export default NavigationBar;
