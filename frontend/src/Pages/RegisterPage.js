import React, { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { registerUser } from "../actions/userActions";

function RegisterPage(props) {
  const { register, handleSubmit, errors, getValues } = useForm();
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  // eslint-disable-next-line
  const [rePassword, setRePassword] = useState("");
  const userRegister = useSelector((state) => state.userRegister);
  const { loading, userInfo, error } = userRegister;
  const dispatch = useDispatch();

  const redirect = props.location.search
    ? props.location.search.split("=")[1]
    : "/";
  useEffect(() => {
    if (userInfo) {
      props.history.push(redirect);
    }
    return () => {
      //
    };
    // eslint-disable-next-line
  }, [userInfo]);

  const submitHandler = () => {
    dispatch(registerUser(name, email, password));
  };
  return (
    <div className="form">
      <form onSubmit={handleSubmit(submitHandler)}>
        <ul className="form-container">
          <li>
            <h2>Create Account</h2>
          </li>
          <li>
            {loading && <div className="loader"></div>}
            {error && <div>{error}</div>}
          </li>
          <li>
            <label htmlFor="name">Name</label>
            <input
              type="name"
              name="name"
              id="name"
              onChange={(e) => setName(e.target.value)}
              ref={register({ required: "Name is required!" })}
            ></input>
            {errors.name && (
              <p style={{ color: "red" }}>{errors.name.message}</p>
            )}
          </li>
          <li>
            <label htmlFor="email">Email</label>
            <input
              type="email"
              name="email"
              id="email"
              onChange={(e) => setEmail(e.target.value)}
              ref={register}
            ></input>
          </li>
          <li>
            <label htmlFor="password">Password</label>
            <input
              type="password"
              id="password"
              name="password"
              onChange={(e) => setPassword(e.target.value)}
              ref={register({ required: "Password is required!" })}
            ></input>
            {errors.password && (
              <p style={{ color: "red" }}>{errors.password.message}</p>
            )}
          </li>
          <li>
            <label htmlFor="rePassword">Re-Enter Password</label>
            <input
              type="password"
              id="rePassword"
              name="rePassword"
              onChange={(e) => setRePassword(e.target.value)}
              ref={register({
                required: "Please confirm password!",
                validate: {
                  matchesPreviousPassword: (value) => {
                    const { password } = getValues();
                    return password === value || "Passwords should match!";
                  },
                },
              })}
            ></input>
            {errors.rePassword && (
              <p style={{ color: "red" }}>{errors.rePassword.message}</p>
            )}
          </li>
          <li>
            <button type="submit" className="button primary">
              Register
            </button>
          </li>
          <li>
            Already have an account?
            <Link
              to={redirect === "/" ? "signin" : "signin?redirect=" + redirect}
              className="button secondary text-center"
            >
              Go to Sign-in
            </Link>
          </li>
        </ul>
      </form>
    </div>
  );
}
export default RegisterPage;
