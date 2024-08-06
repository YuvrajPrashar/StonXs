import Modal from "../Components/Modal.jsx";
import axios from "axios";
import { useState } from "react";
import { Link } from "react-router-dom";

const SignUp = () => {
  const [showModal, setShowModal] = useState(false);
  const [modalMessage, setModalMessage] = useState("");
  const [data, setData] = useState({
    fullname: "",
    username: "",
    email: "",
    password: "",
  });

  //Function to handle the sign up
  const signUpHandler = () => {
    //Trimming the data so that there are no leading or trailing whitespaces
    data.username = data.username.trim();
    data.email = data.email.trim();
    data.password = data.password.trim();
    data.fullname = data.fullname.trim();
    axios
      .post("http://localhost:8080/stonks/api-v1/signup", data)
      .then((res) => {
        setShowModal(true);
        setModalMessage(res.data);
        console.log(res.data);
      })
      .catch((error) => {
        setShowModal(true);
        setModalMessage(error.response.data);
        console.log(error);
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
          <h2 className="text-2xl font-bold text-center">Sign Up</h2>
          <form
            onSubmit={(e) => {
              e.preventDefault();
            }}
            className="space-y-4"
          >
            <div>
              <input
                type="text"
                id="fullname"
                name="fullname"
                placeholder="Enter your full name"
                onChange={inputHandler}
                value={data.fullname}
                className="w-full p-2 mt-1 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
              required  
              />
            </div>
            <div>
              <input
                type="email"
                id="email"
                name="email"
                placeholder="Enter your email"
                onChange={inputHandler}
                value={data.email}
                className="w-full p-2 mt-1 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
              required
              />
            </div>
            <div>
              <input
                type="text"
                id="username"
                name="username"
                placeholder="Enter your username"
                onChange={inputHandler}
                value={data.username}
                className="w-full p-2 mt-1 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
                required
              />
            </div>
            <div>
              <input
                type="password"
                id="password"
                name="password"
                placeholder="Enter your password"
                onChange={inputHandler}
                value={data.password}
                className="w-full p-2 mt-1 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
                required
              />
            </div>
            <button
              type="submit"
              onClick={signUpHandler}
              className="w-full py-2 text-white bg-blue-500 rounded-md hover:bg-blue-700"
            >
              Sign Up
            </button>
          </form>
          <div className="text-center">
            <p className="text-sm text-gray-600">
              Already have an account ?
              <button className="font-medium text-blue-600 hover:text-blue-800 focus:outline-none">
                {" "}
                <Link to="/login">Log In</Link>{" "}
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

export default SignUp;
