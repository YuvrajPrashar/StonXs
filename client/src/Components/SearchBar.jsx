import SearchIcon from "@mui/icons-material/Search";
import { useRef } from "react";
import { useNavigate } from "react-router-dom";

const SearchBar = () => {
  const searchRef = useRef();
  const navigate = useNavigate();

  const handleSearch = () => {
    const searchQuery = searchRef.current.value.trim();
    if (searchQuery) {
      navigate(`/search?query=${searchQuery}`);
    } else {
      navigate(`/`);
    }
  };

  return (
    <form
      className="flex w-full gap-2 justify-evenly"
      onSubmit={(e) => {
        e.preventDefault();
        handleSearch();
      }}
    >
      <input
        type="text"
        name="search"
        placeholder="Search by stock symbol or name ..."
        className="p-2 w-full bg-gray-700 border border-gray-600 rounded-lg text-white placeholder-gray-400 focus:outline-none"
        ref={searchRef}
        onChange={handleSearch}
      />
      <button
        type="submit"
        className="p-2 bg-blue-600 rounded-lg hover:bg-blue-700 focus:outline-none"
      >
        <SearchIcon />
      </button>
    </form>
  );
};

export default SearchBar;
