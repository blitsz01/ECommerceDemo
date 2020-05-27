import axios from "axios";
import Cookie from "js-cookie";
import {
  CART_ADD_ITEM,
  CART_REMOVE_ITEM,
  CART_SAVE_PAYMENT,
  CART_SAVE_SHIPPING,
} from "../constants/cartConstants";

const addToCart = (productId, qty) => async (dispatch, getState) => {
  try {
    axios.get("/api/v1/product/" + productId).then((res) => {
      axios.get("/api/v1/product/download?id=" + productId).then((img) => {
        dispatch({
          type: CART_ADD_ITEM,
          payload: {
            product: res.data.id,
            name: res.data.name,
            image: img.data,
            price: res.data.price,
            countInStock: res.data.countInStock,
            qty,
          },
        });
      });
    });
    const {
      cart: { cartItems },
    } = getState();
    Cookie.set("cartItems", JSON.stringify(cartItems));
  } catch (error) {}
};

const removeFromCart = (productId) => (dispatch, getState) => {
  dispatch({ type: CART_REMOVE_ITEM, payload: productId });

  const {
    cart: { cartItems },
  } = getState();
  Cookie.set("cartItems", JSON.stringify(cartItems));
};

const saveShipping = (data) => (dispatch) => {
  dispatch({ type: CART_SAVE_SHIPPING, payload: data });
};

const savePayment = (data) => (dispatch) => {
  dispatch({ type: CART_SAVE_PAYMENT, payload: data });
};
export { addToCart, removeFromCart, saveShipping, savePayment };
