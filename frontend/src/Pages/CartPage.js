import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { addToCart, removeFromCart } from "../actions/cartActions";

function CartPage(props) {
  const cart = useSelector((state) => state.cart);
  const { cartItems } = cart;
  const productId = props.match.params.id;
  const dispatch = useDispatch();
  const qty = props.location.search
    ? Number(props.location.search.split("=")[1])
    : 1;
  const removeFromCartHandler = (productId) => {
    dispatch(removeFromCart(productId));
  };
  useEffect(() => {
    if (productId) {
      dispatch(addToCart(productId, qty));
    }
    // eslint-disable-next-line
  }, []);
  const checkoutHandler = () => {
    props.history.push("/signin?redirect=shipping");
  };
  return (
    <div className="cart">
      <div className="back-to-result">
        <Link to="/">Back to Shopping</Link>
      </div>
      <div className="cart-list">
        <ul className="cart-list-container">
          <li>
            <h3>Shopping Cart</h3>
            <div>Price</div>
          </li>
          {cartItems.length === 0 ? (
            <div>Cart is empty</div>
          ) : (
            cartItems.map((item) => (
              <li key={item.productId}>
                <div className="cart-image">
                  <img src={item.image} alt="product" />
                </div>
                <div className="cart-name">
                  <div className="product-name">
                    <Link to={"/product/" + item.productId}>{item.name}</Link>
                  </div>
                  <div>
                    Qty:
                    <select
                      value={item.qty}
                      onChange={(e) =>
                        dispatch(addToCart(item.productId, e.target.value))
                      }
                    >
                      {[...Array(item.countInStock).keys()].map((x) => (
                        <option key={x + 1} value={x + 1}>
                          {x + 1}
                        </option>
                      ))}
                    </select>
                    <button
                      type="button"
                      className="button delete-btn"
                      onClick={() => removeFromCartHandler(item.productId)}
                    >
                      Remove
                    </button>
                  </div>
                </div>
                <div className="cart-price">Php {item.price}</div>
              </li>
            ))
          )}
        </ul>
      </div>
      <div className="cart-action">
        Subtotal ( {cartItems.reduce((a, c) => Number(a) + Number(c.qty), 0)}
        items ) :
        <h3> Php {cartItems.reduce((a, c) => a + c.price * c.qty, 0)}</h3>
        <button
          onClick={checkoutHandler}
          className="button primary full-width"
          disabled={cartItems.length === 0}
        >
          Proceed to Checkout
        </button>
      </div>
    </div>
  );
}

export default CartPage;
