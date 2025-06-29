import ProductEdit from "./ProductEdit";
import withAdminAccess from "./withAdminAccess";

const AdminProductEdit = withAdminAccess(ProductEdit);

export default AdminProductEdit;
