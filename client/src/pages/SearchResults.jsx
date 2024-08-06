import { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import axios from "axios";
import Stockstable from "../Components/Stockstable";

const SearchResults = () => {
  const [results, setResults] = useState([]);
  const [searchParams] = useSearchParams();
  //Getting the search query from the URL
  const searchQuery = searchParams.get("query");
  //Fetching the search results
  useEffect(() => {
    axios
      .get(`http://localhost:8080/stonks/api-v1/search?search=${searchQuery}`)
      .then((res) => {
        console.log(res.data);
        setResults(res.data);
      })
      .catch((error) => {
        console.error("Error fetching stocks:", error);
      });
  }, [searchQuery]);

  return (
    <div className="w-screen h-screen content-center">
      <div className="font-thin text-6xl underline underline-offset-8 decoration-2 decoration-emerald-500 text-center tracking-widest  m-4 p-4 ">
        Results
      </div>
      <div className="w-5/6 h-5/6 shadow-xl mx-auto my-2 overflow-auto">
        <Stockstable stocks={results} />
      </div>
    </div>
  );
};

export default SearchResults;
