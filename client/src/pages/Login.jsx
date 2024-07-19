import axios from "axios";
import { useState } from "react";

const Login = () => {
  const [signUp, setSignUp] = useState(false);
  const [data, setData] = useState({
    fullname: "",
    username: "",
    email: "",
    password: "",
  });

  const signInHandler = () => {
    signUp
      ? axios
          .post("http://localhost:8080/user", data)
          .then((res) => {
            console.log(res.data);
          })
          .catch()
          .finally(console.log(data))
      : axios
          axios
            .post("http://localhost:8080/login", data, { withCredentials: true })

            .then((res) => {
              console.log(res.data);
            })
            .catch((error) => {
              console.log(error);
            })
            .finally(console.log(data));
  };

  const signupHandler = () => {
    setSignUp(!signUp);
  };

  const inputHandler = (e) => {
    const { name, value } = e.target;
    setData((prevData) => {
      return {
        ...prevData,
        [name]: value,
      };
    });
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <div className="w-full max-w-md p-8 space-y-6 bg-white shadow-md rounded-lg">
        <h2 className="text-2xl font-bold text-center">
          {signUp ? "Sign Up" : "Log In"}
        </h2>
        <form
          onSubmit={(e) => {
            e.preventDefault();
          }}
          className="space-y-4"
        >
          {signUp && (
            <>
              <div>
                <label
                  htmlFor="fullname"
                  className="block text-sm font-medium text-gray-700"
                >
                  Full Name
                </label>
                <input
                  type="text"
                  id="fullname"
                  name="fullname"
                  placeholder="Enter your full name"
                  onChange={inputHandler}
                  value={data.fullname}
                  className="w-full p-2 mt-1 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
                />
              </div>
              <div>
                <label
                  htmlFor="email"
                  className="block text-sm font-medium text-gray-700"
                >
                  Email
                </label>
                <input
                  type="email"
                  id="email"
                  name="email"
                  placeholder="Enter your email"
                  onChange={inputHandler}
                  value={data.email}
                  className="w-full p-2 mt-1 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
                />
              </div>
            </>
          )}
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
            onClick={signInHandler}
            className="w-full py-2 text-white bg-blue-500 rounded-md hover:bg-blue-700"
          >
            {signUp ? "Sign Up" : "Log In"}
          </button>
        </form>
        <div className="text-center">
          <p className="text-sm text-gray-600">
            {signUp ? "Already have an account?" : "Not an existing user?"}{" "}
            <button
              onClick={signupHandler}
              className="font-medium text-blue-600 hover:text-blue-800 focus:outline-none"
            >
              {signUp ? "Log In" : "Sign Up"}
            </button>
          </p>
        </div>
      </div>
    </div>
  );
};

export default Login;
