import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { createOrder } from "../actions/orderActions";
import CheckoutSteps from "../components/CheckoutSteps";
function PlaceOrderPage(props) {
  const cart = useSelector((state) => state.cart);
  const customer = useSelector((state) => state.userSignin);
  const orderCreate = useSelector((state) => state.orderCreate);
  const { success } = orderCreate;

  const { cartItems, shipping, payment } = cart;
  if (!shipping.address) {
    props.history.push("/shipping");
  } else if (!payment.paymentMethod) {
    props.history.push("/payment");
  }
  const itemsPrice = cartItems.reduce((a, c) => a + c.price * c.qty, 0);
  const shippingPrice = itemsPrice > 100 ? 0 : 10;
  const taxPrice = 0.15 * itemsPrice;
  const totalPrice = itemsPrice + shippingPrice + taxPrice;

  const dispatch = useDispatch();

  const placeOrderHandler = () => {
    // create an order
    dispatch(
      createOrder({
        orderStatus: "ORDER",
        applicationUser: { email: customer.userInfo.email },
        paymentMethod: { paymentType: payment.paymentMethod },
        totalPrice,
        address: {
          line1: shipping.address,
          city: shipping.city,
          postCode: shipping.postalCode,
          country: shipping.country,
        },
        orderDelivery: { deliveryStatus: "PREPARING" },
        orderProductList: cartItems,
        itemsPrice,
        shippingPrice,
        taxPrice,
      })
    );
  };
  useEffect(() => {
    if (success) {
      props.history.push("/orderNotifications");
    }
    return () => {};
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [success]);

  const currencyFormat = (curr) => {
    return new Intl.NumberFormat("en-PH", {
      style: "currency",
      currency: "PHP",
    }).format(curr);
  };

  return (
    <div>
      <CheckoutSteps step1 step2 step3 step4></CheckoutSteps>
      <div className="placeorder">
        <div className="placeorder-info">
          <div>
            <h3>Shipping Address</h3>
            <div>
              {cart.shipping.address}, {cart.shipping.city},
              {cart.shipping.postalCode}, {cart.shipping.country},
            </div>
          </div>
          <div>
            <h3>Contact Number</h3>
            <div>Phone Number: {cart.shipping.phoneNumber}</div>
          </div>
          <div>
            <h3>Payment</h3>
            <div>Payment Method: {cart.payment.paymentMethod}</div>
          </div>
          <div>
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
                      <img src={item.image} alt="Not found" />
                    </div>
                    <div className="cart-name">
                      <div>
                        <Link to={"/product/" + item.productId}>
                          {item.name}
                        </Link>
                      </div>
                      <div>
                        Qty: {item.qty} <b> {currencyFormat(item.price)}</b>per
                        pcs
                      </div>
                    </div>
                    <div className="cart-price">
                      {currencyFormat(item.price * item.qty)}
                    </div>
                  </li>
                ))
              )}
            </ul>
          </div>
        </div>
        <div className="placeorder-action">
          <ul>
            <li>
              <button
                className="button primary full-width"
                onClick={placeOrderHandler}
              >
                Place Order
              </button>
            </li>
            <li>
              <h3>Order Summary</h3>
            </li>
            <li>
              <div>Items</div>
              <div>{currencyFormat(itemsPrice)}</div>
            </li>
            <li>
              <div>Shipping</div>
              <div>{currencyFormat(shippingPrice)}</div>
            </li>
            <li>
              <div>Tax</div>
              <div>{currencyFormat(taxPrice)}</div>
            </li>
            <li>
              <div>Order Total</div>
              <div>{currencyFormat(totalPrice)}</div>
            </li>
          </ul>
        </div>
      </div>
    </div>
  );
}

export default PlaceOrderPage;
