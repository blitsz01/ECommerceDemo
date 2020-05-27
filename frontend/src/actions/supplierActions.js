import axios from "axios";
import {
  SUPPLIER_DELETE_FAIL,
  SUPPLIER_DELETE_REQUEST,
  SUPPLIER_DELETE_SUCCESS,
  SUPPLIER_LIST_FAIL,
  SUPPLIER_LIST_REQUEST,
  SUPPLIER_LIST_SUCCESS,
  SUPPLIER_SAVE_FAIL,
  SUPPLIER_SAVE_REQUEST,
  SUPPLIER_SAVE_SUCCESS,
} from "../constants/supplierConstants";

const listSuppliers = (search) => async (dispatch) => {
  try {
    dispatch({ type: SUPPLIER_LIST_REQUEST });
    if (search) {
      const { data } = await axios.get("/api/v1/supplier/search/" + search);
      dispatch({ type: SUPPLIER_LIST_SUCCESS, payload: data });
    } else {
      const { data } = await axios.get("/api/v1/supplier/list");
      dispatch({ type: SUPPLIER_LIST_SUCCESS, payload: data });
    }
  } catch (error) {
    dispatch({ type: SUPPLIER_LIST_FAIL, payload: error.message });
  }
};

const deleteSupplier = (supplierId) => async (dispatch, getState) => {
  try {
    dispatch({ type: SUPPLIER_DELETE_REQUEST, payload: supplierId });
    axios.get("/api/v1/supplier/" + supplierId).then((res) => {
      const retData = res.data;
      retData.deleteFlag = true;
      const { data } = axios.put("/api/v1/supplier/update", retData);
      dispatch({ type: SUPPLIER_DELETE_SUCCESS, payload: data, success: true });
    });
  } catch (error) {
    dispatch({ type: SUPPLIER_DELETE_FAIL, payload: error.message });
  }
};

const saveSupplier = (supplier) => async (dispatch, getState) => {
  try {
    dispatch({ type: SUPPLIER_SAVE_REQUEST, payload: supplier });
    if (!supplier.id) {
      axios.post("/api/v1/supplier/new", supplier).then((res) => {
        const retData = res.data;
        dispatch({ type: SUPPLIER_SAVE_SUCCESS, payload: retData });
      });
    } else {
      axios.put("/api/v1/supplier/update", supplier).then((data) => {
        dispatch({ type: SUPPLIER_SAVE_SUCCESS, payload: data });
      });
    }
  } catch (error) {
    dispatch({ type: SUPPLIER_SAVE_FAIL, payload: error.message });
  }
};

export { listSuppliers, deleteSupplier, saveSupplier };
