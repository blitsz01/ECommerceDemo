import { StarRating } from "@thumbtack/thumbprint-react";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { detailsProduct } from "../actions/productActions";

function ProductPage(props) {
  const [qty, setQty] = useState(1);
  const productDetails = useSelector((state) => state.productDetails);
  const { product, loading, error } = productDetails;
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(detailsProduct(props.match.params.id));
    return () => {};
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);
  const handleAddToCart = () => {
    props.history.push("/cart/" + props.match.params.id + "?qty=" + qty);
  };
  return (
    <div className="main">
      {!loading && (
        <div className="back-to-result">
          <button className="button secondary" onClick={props.history.goBack}>
            Back
          </button>
        </div>
      )}
      {loading ? (
        <div className="loader"></div>
      ) : error ? (
        <div>{error}</div>
      ) : (
        <div className="details">
          <div className="details-image">
            <img src={product.image} alt="product"></img>
          </div>
          <div className="details-info">
            <ul className="product-details">
              <li className="product-name">
                <h4>{product.name}</h4>
              </li>
              <li>
                <StarRating
                  rating={!product.rating ? 0 : product.rating}
                  size="large"
                />
                <span className="ml3 b">{product.numReviews} reviews</span>
              </li>
              <li className="product-price">
                <b>
                  {new Intl.NumberFormat("en-PH", {
                    style: "currency",
                    currency: "PHP",
                  }).format(product.price)}
                </b>
              </li>
              <li className="product-price">Description:</li>
              <li className="product-details">
                <div>{product.description}</div>
              </li>
            </ul>
          </div>
          <div className="details-action">
            <ul>
              <li>
                Subtotal:
                {new Intl.NumberFormat("en-PH", {
                  style: "currency",
                  currency: "PHP",
                }).format(product.price * qty)}
              </li>
              <li>
                Status: {product.countInStock > 0 ? "In Stock" : "Out of Stock"}
              </li>
              <li>
                Qty:
                <select
                  value={qty}
                  onChange={(e) => {
                    setQty(e.target.value);
                  }}
                >
                  {[...Array(product.countInStock).keys()].map((x) => (
                    <option key={x + 1} value={x + 1}>
                      {x + 1}
                    </option>
                  ))}
                </select>
              </li>
              <li>
                {product.countInStock > 0 && (
                  <button onClick={handleAddToCart} className="button primary">
                    Add to Cart
                  </button>
                )}
              </li>
            </ul>
          </div>
        </div>
      )}
    </div>
  );
}
export default ProductPage;
