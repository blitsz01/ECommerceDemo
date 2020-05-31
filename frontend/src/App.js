import "font-awesome/css/font-awesome.min.css";
import Cookie from "js-cookie";
import React from "react";
import { useSelector } from "react-redux";
import { BrowserRouter, Link, Route } from "react-router-dom";
import "./App.css";
import CartPage from "./pages/CartPage";
import HomePage from "./pages/HomePage";
import OrderNotificationPage from "./pages/OrderNotificationPage";
import PaymentPage from "./pages/PaymentPage";
import PlaceOrderPage from "./pages/PlaceOrderPage";
import ProductMasterPage from "./pages/ProductMasterPage";
import ProductPage from "./pages/ProductPage";
import RegisterPage from "./pages/RegisterPage";
import ShippingPage from "./pages/ShippingPage";
import SignInPage from "./pages/SignInPage";
import SupplierMasterPage from "./pages/SupplierMasterPage";

function App() {
  const userSignin = useSelector((state) => state.userSignin);
  const { userInfo } = userSignin;

  const logout = () => {
    Cookie.remove("userInfo");
    Cookie.remove("cartItems");
    window.location.reload(false);
  };
  return (
    <BrowserRouter>
      <div className="grid-container">
        <header className="header">
          <div className="brand">
            <Link to="/">
              Shop-OnLine <i className="fa fa-shopping-bag"></i>
            </Link>
          </div>
          <div className="header-links">
            {userInfo && userInfo.isAdmin && (
              <div className="dropdown">
                <a href="/">
                  <i className="fa fa-lock menu-header"></i> Admin
                </a>
                <ul className="dropdown-content">
                  <li>
                    <Link to="/products">
                      <i className="fa fa-shopping-basket menu-header"></i>
                      Products
                    </Link>
                    <Link to="/suppliers">
                      <i className="fa fa-truck menu-header"></i>Suppliers
                    </Link>
                  </li>
                </ul>
              </div>
            )}
            <Link to="/cart/0">
              <i className="fa fa-cart-arrow-down menu-header" />
              Cart
            </Link>
            {userInfo ? (
              <div className="dropdown">
                <Link to="/profile">{userInfo.name}</Link>

                <ul className="dropdown-content">
                  <li>
                    <Link to="/" onClick={logout}>
                      <i className="fa fa-sign-out menu-header" />
                      Signout
                    </Link>
                  </li>
                </ul>
              </div>
            ) : (
              <Link to="/signin">
                <i className="fa fa-sign-in menu-header" />
                Sign In
              </Link>
            )}
          </div>
        </header>
        <main className="main">
          <div className="content">
            <Route
              path="/orderNotifications"
              component={OrderNotificationPage}
            />
            <Route path="/product/:id" component={ProductPage} />
            <Route path="/cart/:id?" component={CartPage} />
            <Route path="/signin" component={SignInPage} />
            <Route path="/register" component={RegisterPage} />
            <Route path="/shipping" component={ShippingPage} />
            <Route path="/placeorder" component={PlaceOrderPage} />
            <Route path="/payment" component={PaymentPage} />
            <Route path="/" exact={true} component={HomePage} />
            {userInfo ? (
              <div>
                <Route path="/products" component={ProductMasterPage} />
                <Route path="/suppliers" component={SupplierMasterPage} />
              </div>
            ) : (
              <div />
            )}
          </div>
        </main>
        <footer className="footer">All right reserved.</footer>
      </div>
    </BrowserRouter>
  );
}

export default App;
