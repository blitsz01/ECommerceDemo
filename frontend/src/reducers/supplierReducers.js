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

function supplierListReducer(state = { suppliers: [] }, action) {
  switch (action.type) {
    case SUPPLIER_LIST_REQUEST:
      return { loading: true, suppliers: [] };
    case SUPPLIER_LIST_SUCCESS:
      return { loading: false, suppliers: action.payload };
    case SUPPLIER_LIST_FAIL:
      return { loading: false, error: action.payload };
    default:
      return state;
  }
}

function supplierSaveReducer(state = { supplier: {} }, action) {
  switch (action.type) {
    case SUPPLIER_SAVE_REQUEST:
      return { loading: true };
    case SUPPLIER_SAVE_SUCCESS:
      return { loading: false, success: true, supplier: action.payload };
    case SUPPLIER_SAVE_FAIL:
      return { loading: false, error: action.payload };
    default:
      return state;
  }
}

function supplierDeleteReducer(state = { supplier: {} }, action) {
  switch (action.type) {
    case SUPPLIER_DELETE_REQUEST:
      return { loading: true };
    case SUPPLIER_DELETE_SUCCESS:
      return { loading: false, supplier: action.payload, success: true };
    case SUPPLIER_DELETE_FAIL:
      return { loading: false, error: action.payload };
    default:
      return state;
  }
}

export { supplierListReducer, supplierDeleteReducer, supplierSaveReducer };
