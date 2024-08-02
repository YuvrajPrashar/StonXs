import PersonIcon from "@mui/icons-material/Person";
import { useEffect, useState } from "react";
import { Link, useNavigate, useLocation } from "react-router-dom";
import { LoginSharp } from "@mui/icons-material";
import SearchBar from "./SearchBar";

const Navbar = () => {
  const [loggedIn, setLoggedIn] = useState(false);
  const navigate = useNavigate();
  const location = useLocation();
  const [selectedValue, setSelectedValue] = useState("");

  useEffect(() => {
    const loggedInChecker = () => {
      if (localStorage.getItem("userId")) {
        setLoggedIn(true);
      } else {
        setLoggedIn(false);
      }
    };
    loggedInChecker();
  }, []);

  useEffect(() => {
    const path = location.pathname.split("/")[1];
    if (path === "portfolio") {
      setSelectedValue("Portfolio");
    } else if (path === "watchlist") {
      setSelectedValue("Watchlist");
    } else if (path === "orders") {
      setSelectedValue("Orders");
    } else {
      setSelectedValue("profile");
    }
  }, [location]);

  const handleSelectChange = (event) => {
    const value = event.target.value;
    setSelectedValue(value);
    if (value === "Portfolio") {
      navigate("/portfolio");
    } else if (value === "Watchlist") {
      navigate("/watchlist");
    } else if (value === "Logout") {
      localStorage.clear();
      setLoggedIn(false);
      navigate("/");
    } else if (value === "Orders") {
      navigate("/orders");
    }
  };

  return (
    <nav className="top-0 flex flex-col md:flex-row w-full items-center py-3 px-5 gap-3 bg-gray-800 text-white">
      <div className="text-2xl md:text-4xl font-bold">
        <Link to="/">Stonks</Link>
      </div>
      <div className="w-full md:w-1/5 mt-3 md:mt-0">
        <SearchBar />
      </div>
      <div className="flex flex-col md:flex-row md:flex-1 justify-center md:justify-start text-xl mt-3 md:mt-0">
        <Link to="/stocks" className="hover:text-gray-400 cursor-pointer">
          Explore
        </Link>
      </div>
      <div className="flex items-center mt-3 md:mt-0 mx-auto">
        {loggedIn ? (
          <div className="flex items-center space-x-2">
            <PersonIcon />
            <select
              className="bg-gray-800 border-0 text-white focus:outline-none hover:text-gray-400 cursor-pointer"
              value={selectedValue}
              onChange={handleSelectChange}
            >
              <option value="profile" disabled>
                Profile
              </option>
              <option value="Portfolio">Portfolio</option>
              <option value="Watchlist">Watchlist</option>
              <option value="Orders">Orders</option>
              <option value="Logout">Logout</option>
            </select>
          </div>
        ) : (
          <div className="flex items-center space-x-2">
            <LoginSharp />
            <Link to="/login" className="hover:text-gray-400 cursor-pointer">
              Log In
            </Link>
          </div>
        )}
      </div>
    </nav>
  );
};

export default Navbar;
