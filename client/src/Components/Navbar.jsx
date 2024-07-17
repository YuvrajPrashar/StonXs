import SearchIcon from "@mui/icons-material/Search";
import PersonIcon from "@mui/icons-material/Person";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const Navbar = () => {
  const [loggedIn, setLoggedIn] = useState(true);
  const navigate = useNavigate();

  const handleSelectChange = (event) => {
    const value = event.target.value;
    if (value === "Portfolio") {
      navigate("/portfolio");
    } else if (value === "Watchlist") {
      navigate("/watchlist");
    } else if (value === "Logout") {
      setLoggedIn(false);
      navigate("/login");
    } else if (value === "Orders") {
      navigate("/orders");
    }
  };

  return (
    <nav className="top-0 flex w-full items-center p-3 gap-3 bg-gray-800 text-white">
      <div className="text-4xl font-bold w-max">Stonks</div>
      <form className="flex w-3/5 gap-2 justify-evenly">
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
      <div className="flex flex-1 justify-evenly items-center space-x-4 mt-2 md:mt-0">
        <Link to="/" className="hover:text-gray-400 cursor-pointer">
          Home
        </Link>
        <Link to="/stocks" className="hover:text-gray-400 cursor-pointer">
          Explore
        </Link>
      </div>
      <div className="flex items-center space-x-2 mt-2 ml-auto md:mt-0">
        <PersonIcon />
        {loggedIn ? (
          <div className="hover:text-gray-400 cursor-pointer">
            <select
              className="bg-gray-800 border-0 text-white focus:outline-none hover:text-gray-400 cursor-pointer"
              onChange={handleSelectChange}
            >
              <option value="Username" defaultChecked>
                Yuvraj Prashar
              </option>
              <option value="Portfolio">Portfolio</option>
              <option value="Watchlist">Watchlist</option>
              <option value="Orders">Orders</option>
              <option value="Logout">Logout</option>
            </select>
          </div>
        ) : (
          <Link to="/login" className="hover:text-gray-400 cursor-pointer">
            Log In
          </Link>
        )}
      </div>
    </nav>
  );
};

export default Navbar;
