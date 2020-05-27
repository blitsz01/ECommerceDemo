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
  const [prodCount, setProdCount] = useState(0);
  const { products, loading, error } = productList;
  const dispatch = useDispatch();

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

  return loading ? (
    <div className="loader"></div>
  ) : error ? (
    <div>{error}</div>
  ) : (
    <div>
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
                    alt="product"
                  />
                </Link>

                {convertImageHandler(product.id)}
                <div className="product-brand">{product.brand}</div>
                <div className="product-price">${product.price}</div>
                <div className="product-rating">
                  <StarRating rating={product.rating} size="large" />
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
