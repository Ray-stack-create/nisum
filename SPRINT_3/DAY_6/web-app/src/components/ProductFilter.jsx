import React, { useState, useEffect, useRef } from "react";
import { Link } from "react-router-dom";
import useWishlist from "./useWishlist";

export default function ProductFilter() {
  const [allProducts, setAllProducts] = useState([]);
  const [filtered, setFiltered] = useState([]);
  const [visible, setVisible] = useState([]);
  const [search, setSearch] = useState("");
  const [category, setCategory] = useState("All");
  const [minPrice, setMinPrice] = useState("");
  const [maxPrice, setMaxPrice] = useState("");
  const [categories, setCategories] = useState(["All"]);
  const [page, setPage] = useState(1);
  const [isLoading, setIsLoading] = useState(false);
  const [hasMore, setHasMore] = useState(true);

  const searchRef = useRef(null);
  const loaderRef = useRef(null);
  const { addToWishlist, removeFromWishlist, isWishlisted } = useWishlist();

  useEffect(() => {
    const fetchProducts = async () => {
      const res = await fetch("https://fakestoreapi.com/products");
      const data = await res.json();
      setAllProducts(data);
      setFiltered(data);
      setVisible(data.slice(0, 6));
      setPage(1);

      const unique = ["All", ...new Set(data.map((p) => p.category))];
      setCategories(unique);
    };

    fetchProducts();
  }, []);

  useEffect(() => {
    if (searchRef.current) clearTimeout(searchRef.current);
    searchRef.current = setTimeout(() => {
      applyFilters();
    }, 300);
    return () => clearTimeout(searchRef.current);
  }, [search, category, minPrice, maxPrice, allProducts]);

  const applyFilters = () => {
    let result = [...allProducts];

    if (search.trim()) {
      result = result.filter((p) =>
        p.title.toLowerCase().includes(search.toLowerCase())
      );
    }

    if (category !== "All") {
      result = result.filter((p) => p.category === category);
    }

    if (minPrice !== "") {
      result = result.filter((p) => p.price >= parseFloat(minPrice));
    }

    if (maxPrice !== "") {
      result = result.filter((p) => p.price <= parseFloat(maxPrice));
    }

    setFiltered(result);
    setVisible(result.slice(0, 6));
    setPage(1);
    setHasMore(result.length > 6);
  };

  const loadMore = () => {
    if (isLoading || !hasMore) return;
    setIsLoading(true);
    const nextPage = page + 1;
    const nextItems = filtered.slice(0, nextPage * 6);
    setVisible(nextItems);
    setPage(nextPage);
    setIsLoading(false);
    setHasMore(nextItems.length < filtered.length);
  };

  useEffect(() => {
    const observer = new IntersectionObserver(
      (entries) => {
        if (entries[0].isIntersecting && hasMore) {
          loadMore();
        }
      },
      { threshold: 1 }
    );

    if (loaderRef.current) observer.observe(loaderRef.current);
    return () => {
      if (loaderRef.current) observer.unobserve(loaderRef.current);
    };
  }, [filtered, page, hasMore]);

  return (
    <div className="min-h-screen bg-gradient-to-br from-pink-200 to-indigo-200 p-6">
      <div className="max-w-5xl mx-auto bg-white rounded-2xl shadow-xl p-6">
        <h1 className="text-3xl font-bold text-center mb-6 text-indigo-700">
          🛒 Real-Time Product Filter
        </h1>

        {/* Filters */}
        <div className="grid grid-cols-1 md:grid-cols-4 gap-4 mb-6">
          <input
            type="text"
            placeholder="🔍 Search by product name"
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            className="px-4 py-2 border rounded-lg w-full"
          />

          <select
            value={category}
            onChange={(e) => {
              setCategory(e.target.value);
              window.scrollTo({ top: 0, behavior: "smooth" });
            }}
            className="px-4 py-2 border rounded-lg w-full"
          >
            {categories.map((cat, i) => (
              <option key={i} value={cat}>
                {cat}
              </option>
            ))}
          </select>

          <input
            type="number"
            placeholder="Min Price"
            value={minPrice}
            onChange={(e) => setMinPrice(e.target.value)}
            className="px-4 py-2 border rounded-lg w-full"
          />

          <input
            type="number"
            placeholder="Max Price"
            value={maxPrice}
            onChange={(e) => setMaxPrice(e.target.value)}
            className="px-4 py-2 border rounded-lg w-full"
          />
        </div>

        {/* Product List */}
        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
          {visible.length > 0 ? (
            visible.map((product) => {
              const wished = isWishlisted(product.id);
              return (
                <div key={product.id} className="relative">
                  <Link to={`/products/${product.id}`}>
                    <div className="bg-white border rounded-xl shadow hover:shadow-lg p-4 transition-all hover:scale-[1.02]">
                      <img
                        src={product.image}
                        alt={product.title}
                        className="h-40 mx-auto mb-4"
                      />
                      <h3 className="font-semibold text-lg text-indigo-700 mb-2 line-clamp-2">
                        {product.title}
                      </h3>
                      <p className="text-gray-500 mb-1 capitalize">
                        {product.category}
                      </p>
                      <p className="text-green-600 font-bold">
                        ${product.price}
                      </p>
                    </div>
                  </Link>

                  <button
                    onClick={() =>
                      wished
                        ? removeFromWishlist(product.id)
                        : addToWishlist(product)
                    }
                    className="absolute top-3 right-3 text-2xl transition-transform hover:scale-110"
                    title={wished ? "Remove from Wishlist" : "Add to Wishlist"}
                  >
                    {wished ? "❤️" : "🤍"}
                  </button>
                </div>
              );
            })
          ) : (
            <p className="text-center text-gray-500">No products found</p>
          )}
        </div>

        {/* Loader */}
        <div ref={loaderRef} className="flex justify-center mt-6">
          {isLoading && (
            <div className="animate-spin rounded-full h-10 w-10 border-t-4 border-indigo-600"></div>
          )}
        </div>
      </div>
    </div>
  );
}
