import "font-awesome/css/font-awesome.min.css";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  deleteProduct,
  listProducts,
  saveProduct,
} from "../actions/productActions";
import { listSuppliers } from "../actions/supplierActions";

function ProductMasterPage(props) {
  const [modalVisible, setModalVisible] = useState(false);
  const [listVisible, setListVisible] = useState(true);
  const [id, setId] = useState("");
  const [supplierId, setSupplierId] = useState("");
  const [supplierName, setSupplierName] = useState("");
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");
  const [image, setImage] = useState("");
  const [brand, setBrand] = useState("");
  const [productCategory, setProductCategory] = useState("");
  const [countInStock, setCountInStock] = useState("");
  const [description, setDescription] = useState("");
  const [numReviews, setNumReviews] = useState("");
  const productList = useSelector((state) => state.productList);
  const { loading, products, error } = productList;
  const supplierList = useSelector((state) => state.supplierList);
  const { suppliers } = supplierList;
  const productDelete = useSelector((state) => state.productDelete);
  const {
    loading: loadingDelete,
    success: successDelete,
    error: errorDelete,
  } = productDelete;
  const [search, setSearch] = useState("");

  const productSave = useSelector((state) => state.productSave);
  const {
    loading: loadingSave,
    success: successSave,
    error: errorSave,
  } = productSave;

  const dispatch = useDispatch();

  useEffect(() => {
    if (successSave) {
      setModalVisible(false);
      setListVisible(true);
    }
    dispatch(listProducts());
    dispatch(listSuppliers());
    return () => {
      //
    };
  }, [successSave, successDelete]);

  const openModal = (product) => {
    setModalVisible(true);
    setListVisible(false);
    setId(product.id);
    setName(product.name);
    setPrice(product.price);
    setDescription(product.description);
    setImage(product.image);
    setBrand(product.brand);
    setProductCategory(product.productCategory);
    setCountInStock(product.countInStock);
    setNumReviews(product.numReviews);
    if (product.supplier) setSupplierName(product.supplier.name);
  };
  const submitHandler = (e) => {
    e.preventDefault();
    dispatch(
      saveProduct({
        id: id,
        name,
        price,
        image,
        brand,
        productCategory,
        countInStock,
        description,
        numReviews,
        supplier: { id: supplierId },
      })
    );
  };
  const deleteHandler = (product) => {
    dispatch(deleteProduct(product.id));
  };
  const imageHandler = (imageData) => {
    var reader = new FileReader();
    reader.onload = function () {
      var dataURL = reader.result;
      console.log("test " + dataURL);
      setImage(dataURL);
    };
    reader.readAsDataURL(imageData);
  };
  const searchHandler = (data) => {
    dispatch(listProducts(data));
  };
  return (
    <div className="content content-margined">
      <div className="product-header">
        <h2>Products</h2>
        <button className="button primary" onClick={() => openModal({})}>
          <i className="fa fa-plus-circle add-button"></i>
          Add Product
        </button>
      </div>
      {modalVisible && (
        <div className="form">
          <form onSubmit={submitHandler}>
            <ul className="form-container">
              <li>
                <h2> {id ? "Update" : "Create"} Product</h2>
              </li>
              <li>
                {loadingSave && <div className="loader"></div>}
                {errorSave && <div>{errorSave}</div>}
              </li>
              <li>
                {id ? (
                  <label>
                    Supplier: <b>{supplierName}</b>
                  </label>
                ) : (
                  <select
                    name="supplier"
                    id="supplier"
                    onChange={(e) => setSupplierId(e.target.value)}
                    value={supplierId}
                  >
                    <option>Select here</option>
                    {suppliers.map((supplier) => (
                      <option key={supplier.id} value={supplier.id}>
                        {supplier.name}
                      </option>
                    ))}
                  </select>
                )}
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
                <label htmlFor="price">Price in Peso</label>
                <input
                  type="text"
                  name="price"
                  value={price}
                  id="price"
                  onChange={(e) => setPrice(e.target.value)}
                ></input>
              </li>
              <li>
                <label htmlFor="image">Image</label>
                <input
                  type="file"
                  name="image"
                  onChange={(e) => imageHandler(e.target.files[0])}
                />
              </li>
              <li>
                <label htmlFor="brand">Brand</label>
                <input
                  type="text"
                  name="brand"
                  value={brand}
                  id="brand"
                  onChange={(e) => setBrand(e.target.value)}
                ></input>
              </li>
              <li>
                <label htmlFor="countInStock">CountInStock</label>
                <input
                  type="text"
                  name="countInStock"
                  value={countInStock}
                  id="countInStock"
                  onChange={(e) => setCountInStock(e.target.value)}
                ></input>
              </li>
              <li>
                <label htmlFor="name">Product Category</label>
                <select
                  name="productCategory"
                  id="supplier"
                  onChange={(e) => setProductCategory(e.target.value)}
                  value={productCategory}
                >
                  <option>Select here</option>
                  <option value="FASHION">Fashion</option>
                  <option value="GADGETS">Gadgets</option>
                  <option value="JEWELRY">Jewelry</option>
                  <option value="SHOES">Shoes</option>
                  <option value="ACCESSORIES">Accessories</option>
                </select>
              </li>
              <li>
                <label htmlFor="description">Description</label>
                <textarea
                  name="description"
                  value={description}
                  id="description"
                  onChange={(e) => setDescription(e.target.value)}
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
                  <th>Price</th>
                  <th>Product Category</th>
                  <th>Brand</th>
                  <th>Rating</th>
                  <th>Supplier</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
                {products.map((product) => (
                  <tr key={product.id}>
                    <td>{product.id}</td>
                    <td>{product.name}</td>
                    <td>Php{product.price}</td>
                    <td>{product.productCategory}</td>
                    <td>{product.brand}</td>
                    <td>{product.rating}</td>
                    <td>{product.supplier.name}</td>
                    <td>
                      <button
                        className="button edit-btn"
                        onClick={() => openModal(product)}
                      >
                        Edit
                      </button>
                      <button
                        className="button delete-btn"
                        onClick={() => deleteHandler(product)}
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
export default ProductMasterPage;
