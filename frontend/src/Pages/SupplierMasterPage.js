import "font-awesome/css/font-awesome.min.css";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  deleteSupplier,
  listSuppliers,
  saveSupplier,
} from "../actions/supplierActions";

function SupplierMasterPage(props) {
  const [modalVisible, setModalVisible] = useState(false);
  const [listVisible, setListVisible] = useState(true);
  const [id, setId] = useState("");
  const [name, setName] = useState("");
  const [details, setDetails] = useState("");

  const supplierList = useSelector((state) => state.supplierList);
  const { suppliers } = supplierList;
  const supplierDelete = useSelector((state) => state.supplierDelete);
  const { success: successDelete } = supplierDelete;
  const [search, setSearch] = useState("");

  const supplierSave = useSelector((state) => state.supplierSave);
  const {
    loading: loadingSave,
    success: successSave,
    error: errorSave,
  } = supplierSave;

  const dispatch = useDispatch();

  useEffect(() => {
    if (successSave) {
      setModalVisible(false);
      setListVisible(true);
    }
    dispatch(listSuppliers());
    return () => {};
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [successSave, successDelete]);

  const openModal = (supplier) => {
    setModalVisible(true);
    setListVisible(false);
    setId(supplier.id);
    setName(supplier.name);
    setDetails(supplier.details);
  };
  const submitHandler = (e) => {
    e.preventDefault();
    dispatch(
      saveSupplier({
        id: id,
        name,
        details,
      })
    );
  };
  const deleteHandler = (supplier) => {
    dispatch(deleteSupplier(supplier.id));
  };
  const searchHandler = (data) => {
    dispatch(listSuppliers(data));
  };
  return (
    <div className="content content-margined">
      <div className="product-header">
        <h2>Suppliers</h2>
        <button className="button primary" onClick={() => openModal({})}>
          <i className="fa fa-plus-circle add-button"></i>
          Add Supplier
        </button>
      </div>
      {modalVisible && (
        <div className="form">
          <form onSubmit={submitHandler}>
            <ul className="form-container">
              <li>
                <h2>{id ? "Update" : "Create"} Supplier</h2>
              </li>
              <li>
                {loadingSave && <div className="loader"></div>}
                {errorSave && <div>{errorSave}</div>}
              </li>

              <li>
                <label htmlFor="name">Name</label>
                <input
                  type="text"
                  name="name"
                  value={name}
                  id="name"
                  onChange={(e) => setName(e.target.value)}
                ></input>
              </li>
              <li>
                <label htmlFor="details">Details</label>
                <textarea
                  name="details"
                  value={details}
                  id="details"
                  onChange={(e) => setDetails(e.target.value)}
                ></textarea>
              </li>
              <li>
                <button type="submit" className="button primary">
                  {id ? "Update" : "Create"}
                </button>
              </li>
              <li>
                <button
                  type="button"
                  onClick={() => {
                    setModalVisible(false);
                    setListVisible(true);
                  }}
                  className="button secondary"
                >
                  Back
                </button>
              </li>
            </ul>
          </form>
        </div>
      )}

      <div className="product-list">
        {listVisible && (
          <div>
            <input
              type="text"
              name="search"
              value={search}
              id="search"
              className="search"
              onChange={(e) => setSearch(e.target.value)}
            ></input>
            <button
              type="button"
              className="button secondary button-search"
              onClick={() => searchHandler(search)}
            >
              <i className="fa fa-search"></i>
            </button>
            <table className="table">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>Details</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
                {suppliers.map((supplier) => (
                  <tr key={supplier.id}>
                    <td>{supplier.id}</td>
                    <td>{supplier.name}</td>
                    <td>{supplier.details}</td>
                    <td>
                      <button
                        className="button edit-btn"
                        onClick={() => openModal(supplier)}
                      >
                        Edit
                      </button>
                      <button
                        className="button delete-btn"
                        onClick={() => deleteHandler(supplier)}
                      >
                        Delete
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
}
export default SupplierMasterPage;
