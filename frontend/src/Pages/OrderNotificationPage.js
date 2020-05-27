import React, { useEffect } from "react";
import { Link } from "react-router-dom";

function OrderNotificationPage(props) {
  useEffect(() => {
    return () => {
      //
    };
  }, []);
  return (
    <div>
      <h2>Order has been sent!!!</h2>
      <h2>
        <Link to="/">Order again</Link>
      </h2>
    </div>
  );
}
export default OrderNotificationPage;
