import SearchIcon from "@mui/icons-material/Search";
import PersonIcon from "@mui/icons-material/Person";
import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { LoginSharp } from "@mui/icons-material";

const Navbar = () => {
  const [loggedIn, setLoggedIn] = useState(false);
  const navigate = useNavigate();
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
  const handleSelectChange = (event) => {
    const value = event.target.value;
    if (value === "Portfolio") {
      navigate("/portfolio");
    } else if (value === "Watchlist") {
      navigate("/watchlist");
    } else if (value === "Logout") {
      localStorage.clear();
      setLoggedIn(false);
      navigate("/login");
    } else if (value === "Orders") {
      navigate("/orders");
    }
  };

  return (
    <nav className="top-0 flex w-screen items-center py-3 px-5 gap-3 space-x-2 bg-gray-800 text-white">
      <div className="text-4xl font-bold w-max">
        <Link to="/">Stonks</Link>
      </div>
      <form className="flex w-1/5 gap-2 justify-evenly">
        <input
          type="text"
          name="search"
          placeholder="Search by stock symbol"
          className="p-2 w-full bg-gray-700 border border-gray-600 rounded-lg text-white placeholder-gray-400 focus:outline-none"
        />
        <button className="p-2 bg-blue-600 rounded-lg hover:bg-blue-700 focus:outline-none">
          <SearchIcon />
        </button>
      </form>
      <div className="flex flex-1 justify-start text-xl content-center w-max ">
        <Link to="/stocks" className="hover:text-gray-400 cursor-pointer">
          Explore
        </Link>
      </div>
      <div className="flex items-center mt-2 ml-auto md:mt-0">
        {loggedIn ? (
          <div className="hover:text-gray-400 cursor-pointer space-x-2 align-bottom text-xl content-center">
            <PersonIcon />
            <select
              className="bg-gray-800 border-0 text-white focus:outline-none hover:text-gray-400 cursor-pointer"
              onChange={handleSelectChange}
            >
              <option value="profile" defaultChecked>
                Profile
              </option>
              <option value="Portfolio">Portfolio</option>
              <option value="Watchlist">Watchlist</option>
              <option value="Orders">Orders</option>
              <option value="Logout">Logout</option>
            </select>
          </div>
        ) : (
          <div className="hover:text-gray-400 cursor-pointer space-x-2 align-bottom text-xl content-center">
            <LoginSharp />
            <Link to="/login">Log In</Link>
          </div>
        )}
      </div>
    </nav>
  );
};

export default Navbar;
