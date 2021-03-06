import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { savePayment } from "../actions/cartActions";
import CheckoutSteps from "../components/CheckoutSteps";

function PaymentPage(props) {
  const [paymentMethod, setPaymentMethod] = useState("");

  const dispatch = useDispatch();

  const submitHandler = (e) => {
    e.preventDefault();
    dispatch(savePayment({ paymentMethod }));
    props.history.push("placeorder");
  };
  return (
    <div>
      <CheckoutSteps step1 step2 step3></CheckoutSteps>
      <div className="form">
        <form onSubmit={submitHandler}>
          <ul className="form-container">
            <li>
              <h2>Payment</h2>
            </li>

            <li>
              <div>
                <p>
                  <input
                    type="radio"
                    name="paymentMethod"
                    id="paymentMethod"
                    value="CREDITCARD"
                    onChange={(e) => setPaymentMethod(e.target.value)}
                  ></input>
                  <label htmlFor="paymentMethod">Credit Card</label>
                </p>
                <p>
                  <input
                    type="radio"
                    name="paymentMethod"
                    id="paymentMethod"
                    value="PAYPAL"
                    onChange={(e) => setPaymentMethod(e.target.value)}
                  ></input>
                  <label htmlFor="paymentMethod">Paypal</label>
                </p>
                <p>
                  <input
                    type="radio"
                    name="paymentMethod"
                    id="paymentMethod"
                    value="CASH"
                    onChange={(e) => setPaymentMethod(e.target.value)}
                  ></input>
                  <label htmlFor="paymentMethod">Cash on delivery</label>
                </p>
              </div>
            </li>

            <li>
              <button type="submit" className="button primary">
                Continue
              </button>
            </li>
          </ul>
        </form>
      </div>
    </div>
  );
}
export default PaymentPage;
