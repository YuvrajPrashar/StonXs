import Modal from "../Components/Modal.jsx";
import axios from "axios";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const Login = () => {
  const [showModal, setShowModal] = useState(false);
  const [modalMessage, setModalMessage] = useState("");
  const [data, setData] = useState({
    username: "",
    password: "",
  });

  const navigate = useNavigate();

  const signInHandler = () => {
    axios
      .post("http://localhost:8080/login", data, { withCredentials: true })
      .then((res) => {
        setModalMessage(res.data);
        localStorage.setItem("token", res.headers["authorization"]);
        console.log(res.headers["authorization"]);
        console.log(res);
        localStorage.setItem("userId", res.headers["userid"]);
        localStorage.setItem("watchlistId", res.headers["watchlistid"]);
        localStorage.setItem("portfolioId", res.headers["portfolioid"]);
        setShowModal(true);
        setTimeout(() => {
          navigate("/");
          window.location.reload();
        }, 1700);
      })
      .catch((error) => {
        console.log(
          "Login Error:",
          error.response ? error.response.data : error.message
        );
        setModalMessage(error.response ? error.response.data : error.message);
      });
  };

  const inputHandler = (e) => {
    const { name, value } = e.target;
    setData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  return (
    <>
      <div className="flex items-center justify-center min-h-screen bg-gray-100">
        <div className="w-full max-w-md p-8 space-y-6 bg-white shadow-md rounded-lg">
          <h2 className="text-2xl font-bold text-center">Log In</h2>
          <form
            onSubmit={(e) => {
              e.preventDefault();
              signInHandler();
            }}
            className="space-y-4"
          >
            <div>
              <label
                htmlFor="username"
                className="block text-sm font-medium text-gray-700"
              >
                Username
              </label>
              <input
                type="text"
                id="username"
                name="username"
                placeholder="Enter your username"
                onChange={inputHandler}
                value={data.username}
                className="w-full p-2 mt-1 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
              />
            </div>
            <div>
              <label
                htmlFor="password"
                className="block text-sm font-medium text-gray-700"
              >
                Password
              </label>
              <input
                type="password"
                id="password"
                name="password"
                placeholder="Enter your password"
                onChange={inputHandler}
                value={data.password}
                className="w-full p-2 mt-1 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
              />
            </div>
            <button
              type="submit"
              className="w-full py-2 text-white bg-blue-500 rounded-md hover:bg-blue-700"
            >
              Log In
            </button>
          </form>
          <div className="text-center">
            <p className="text-sm text-gray-600">
              Not an existing user?
              <button className="font-medium text-blue-600 hover:text-blue-800 focus:outline-none">
                <Link to="/signup">Sign Up</Link>
              </button>
            </p>
          </div>
        </div>
      </div>
      {showModal && (
        <Modal message={modalMessage} onClose={() => setShowModal(false)} />
      )}
    </>
  );
};

export default Login;
