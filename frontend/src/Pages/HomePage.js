import { StarRating } from "@thumbtack/thumbprint-react";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import {
  listProducts,
  listUpdateImageProducts,
} from "../actions/productActions";
import PictureHeader from "../components/PictureHeader";

function HomePage(props) {
  const productList = useSelector((state) => state.productList);
  const [modalVisible, setModalVisible] = useState(false);
  const [prodCount, setProdCount] = useState(0);
  const { products, loading, error } = productList;
  const dispatch = useDispatch();
  const [search, setSearch] = useState("");

  useEffect(() => {
    dispatch(listProducts());

    return () => {};
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const convertImageHandler = (imageId) => {
    if (products.length >= prodCount) {
      setProdCount(prodCount + 1);
      dispatch(listUpdateImageProducts(products, imageId));
    }
  };

  const openModal = (supplier) => {
    setModalVisible(true);
  };

  const searchHandler = (data) => {
    dispatch(listProducts(data));
    setModalVisible(false);
  };

  return loading ? (
    <div className="loader"></div>
  ) : error ? (
    <div>{error}</div>
  ) : (
    <div>
      {modalVisible ? (
        <form className="search">
          <input
            type="text"
            name="search"
            value={search}
            id="search"
            onChange={(e) => setSearch(e.target.value)}
          ></input>
          {search && (
            <button
              type="button"
              className="button-clear-homepage"
              onClick={() => setSearch("")}
            >
              X
            </button>
          )}
          <button
            type="submit"
            className="button-search-homepage"
            onClick={() => searchHandler(search)}
          >
            Search
          </button>
        </form>
      ) : (
        <span className="float-search-button" onClick={() => openModal({})}>
          <i className="fa fa-search my-float-icon"></i>
        </span>
      )}
      <PictureHeader />
      <ul className="products">
        {products.map((product) => (
          <li key={product.id}>
            {loading ? (
              <div className="loader"></div>
            ) : error ? (
              <div>{error}</div>
            ) : (
              <div className="product">
                <div className="product-name">
                  <Link to={"/product/" + product.id}>{product.name}</Link>
                </div>

                <Link to={"/product/" + product.id}>
                  <img
                    className="product-image"
                    src={product.image}
                    alt="Not Found"
                  />
                </Link>

                {convertImageHandler(product.id)}
                <div className="product-brand">{product.brand}</div>
                <div className="product-price">
                  {new Intl.NumberFormat("en-PH", {
                    style: "currency",
                    currency: "PHP",
                  }).format(product.price)}
                </div>
                <div className="product-rating">
                  <StarRating
                    rating={!product.rating ? 0 : product.rating}
                    size="large"
                  />
                  <span className="ml3 b">
                    ( {product.numReviews} reviews )
                  </span>
                </div>
              </div>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
}
export default HomePage;
