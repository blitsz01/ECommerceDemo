import axios from "axios";
import {
  PRODUCT_DELETE_FAIL,
  PRODUCT_DELETE_REQUEST,
  PRODUCT_DELETE_SUCCESS,
  PRODUCT_DETAILS_FAIL,
  PRODUCT_DETAILS_REQUEST,
  PRODUCT_DETAILS_SUCCESS,
  PRODUCT_LIST_FAIL,
  PRODUCT_LIST_IMAGE_FAIL,
  PRODUCT_LIST_IMAGE_REQUEST,
  PRODUCT_LIST_IMAGE_SUCCESS,
  PRODUCT_LIST_REQUEST,
  PRODUCT_LIST_SUCCESS,
  PRODUCT_SAVE_FAIL,
  PRODUCT_SAVE_REQUEST,
  PRODUCT_SAVE_SUCCESS,
} from "../constants/productConstants";

const listProducts = (search) => async (dispatch) => {
  try {
    dispatch({ type: PRODUCT_LIST_REQUEST });
    if (search) {
      const { data } = await axios.get(
        "/ECommerceRest/api/v1/product/search?search=" + search
      );
      dispatch({ type: PRODUCT_LIST_SUCCESS, payload: data });
    } else {
      const { data } = await axios.get("/ECommerceRest/api/v1/product/list");
      dispatch({ type: PRODUCT_LIST_SUCCESS, payload: data });
    }
  } catch (error) {
    dispatch({ type: PRODUCT_LIST_FAIL, payload: error.message });
  }
};

const listUpdateImageProducts = (products, id) => async (dispatch) => {
  try {
    dispatch({ type: PRODUCT_LIST_IMAGE_REQUEST });
    axios.get("/ECommerceRest/api/v1/product/download?id=" + id).then((res) => {
      const updatedList = [];
      for (const product of products) {
        if (product.id === id) {
          product.image = res.data;
        }
        updatedList.push(product);
      }
      dispatch({ type: PRODUCT_LIST_IMAGE_SUCCESS, payload: updatedList });
    });
  } catch (error) {
    dispatch({ type: PRODUCT_LIST_IMAGE_FAIL, payload: error.message });
  }
};

const detailsProduct = (productId) => async (dispatch) => {
  try {
    dispatch({ type: PRODUCT_DETAILS_REQUEST, payload: productId });
    axios.get("/ECommerceRest/api/v1/product/" + productId).then((res) => {
      axios
        .get("/ECommerceRest/api/v1/product/download?id=" + productId)
        .then((img) => {
          res.data.image = img.data;
          dispatch({ type: PRODUCT_DETAILS_SUCCESS, payload: res.data });
        });
    });
  } catch (error) {
    dispatch({ type: PRODUCT_DETAILS_FAIL, payload: error.message });
  }
};

const saveProduct = (product) => async (dispatch, getState) => {
  try {
    dispatch({ type: PRODUCT_SAVE_REQUEST, payload: product });
    const {
      userSignin: { userInfo },
    } = getState();
    const imageRead = product.image;
    if (!product.id) {
      product.image = null;

      axios
        .post("/ECommerceRest/api/v1/product/new", product, {
          headers: {
            Authorization: "Bearer " + userInfo.token,
          },
        })
        .then((res) => {
          const retData = res.data;
          axios.post(
            "/ECommerceRest/api/v1/product/upload?id=" + retData.id,
            imageRead,
            {
              headers: {
                "Content-Type": "application/octet-stream",
                Authorization: "Bearer " + userInfo.token,
              },
            }
          );
          dispatch({ type: PRODUCT_SAVE_SUCCESS, payload: retData });
        });
    } else {
      axios.get("/ECommerceRest/api/v1/product/" + product.id).then((res) => {
        const retData = res.data;
        retData.name = product.name;
        retData.price = product.price;
        retData.productCategory = product.productCategory;
        retData.brand = product.brand;
        retData.countInStock = product.countInStock;
        retData.numReviews = product.numReviews;
        retData.description = product.description;
        axios
          .put("/ECommerceRest/api/v1/product/update", retData, {
            headers: {
              Authorization: "Bearer " + userInfo.token,
            },
          })
          .then((data) => {
            if (imageRead) {
              axios
                .post(
                  "/ECommerceRest/api/v1/product/upload?id=" + product.id,
                  imageRead,
                  {
                    headers: {
                      "Content-Type": "application/octet-stream",
                      Authorization: "Bearer " + userInfo.token,
                    },
                  }
                )
                .then(() => {
                  console.log("update");
                  retData.image = imageRead;
                  dispatch({ type: PRODUCT_SAVE_SUCCESS, payload: retData });
                });
            } else {
              axios
                .get("/ECommerceRest/api/v1/product/download?id=" + product.id)
                .then((img) => {
                  console.log("normal");
                  retData.image = img.data;
                  dispatch({ type: PRODUCT_SAVE_SUCCESS, payload: retData });
                });
            }
          });
      });
    }
  } catch (error) {
    dispatch({ type: PRODUCT_SAVE_FAIL, payload: error.message });
  }
};

const deleteProduct = (productId) => async (dispatch, getState) => {
  try {
    const {
      userSignin: { userInfo },
    } = getState();
    dispatch({ type: PRODUCT_DELETE_REQUEST, payload: productId });
    axios.get("/ECommerceRest/api/v1/product/" + productId).then((res) => {
      const retData = res.data;
      retData.deleteFlag = true;
      const { data } = axios.put(
        "/ECommerceRest/api/v1/product/update",
        retData,
        {
          headers: {
            Authorization: "Bearer " + userInfo.token,
          },
        }
      );
      dispatch({ type: PRODUCT_DELETE_SUCCESS, payload: data, success: true });
    });
  } catch (error) {
    dispatch({ type: PRODUCT_DELETE_FAIL, payload: error.message });
  }
};

export {
  listProducts,
  detailsProduct,
  saveProduct,
  deleteProduct,
  listUpdateImageProducts,
};
