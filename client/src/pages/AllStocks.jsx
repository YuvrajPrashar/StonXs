import StocksCard from "../Components/StocksCard.jsx";
import Stockstable from "../Components/Stockstable.jsx";
import SwapVertIcon from "@mui/icons-material/SwapVert";
import { useEffect, useState } from "react";
import axios from "axios";
import { FormatListBulleted, WindowSharp } from "@mui/icons-material";
import { useParams } from "react-router-dom";
import Pagination from "../Components/Pagination.jsx";

const AllStocks = () => {
  const { category } = useParams();
  const [stocksDATA, setStocksDATA] = useState([]);
  const [sortedStocks, setSortedStocks] = useState([]);
  const [grid, setGrid] = useState(true);
  const [sortOption, setSortOption] = useState();
  const [currentPage, setCurrentPage] = useState(0);
  const [pageSize, setPageSize] = useState(15);
  const [totalPages, setTotalPages] = useState(0);

  useEffect(() => {
    const fetchStocks = async () => {
      try {
        const response = category
          ? await axios.get(
              `http://localhost:8080/stocks/${category}?pageNo=${currentPage}&pageSize=${pageSize}`
            )
          : await axios.get(
              `http://localhost:8080/stocks?pageNo=${currentPage}&pageSize=${pageSize}`
            );
        console.log(response);
        setStocksDATA(response.data.content);
        setSortedStocks(response.data.content);
        setTotalPages(response.data.totalPages);
      } catch (error) {
        console.error("Error fetching stocks:", error);
      }
    };

    fetchStocks();
  }, [category, currentPage, pageSize]);

  useEffect(() => {
    let sortedData = [...stocksDATA];
    switch (sortOption) {
      case "price":
        sortedData.sort((a, b) => a.currentprice - b.currentprice);
        break;
      case "alphabets":
        sortedData.sort((a, b) => a.stockname.localeCompare(b.stockname));
        break;
      case "reverseAlphabets":
        sortedData.sort((a, b) => b.stockname.localeCompare(a.stockname));
        break;
      default:
        break;
    }
    setSortedStocks(sortedData);
  }, [sortOption, stocksDATA]);

  const layoutHandler = () => {
    setGrid(!grid);
  };

  return (
    <div className="h-screen">
      <div className="p-2 grid grid-flow-col w-screen justify-stretch content-center">
        <div className="p-3 justify-self-start">
          <SwapVertIcon />
          <label htmlFor="sort"> Sort By</label>
          <select
            name="sort"
            id="sort"
            className="bg-transparent"
            onChange={(e) => {
              setSortOption(e.target.value);
            }}
          >
            <option value="" hidden>
              None
            </option>
            <option value="price">Price</option>
            <option value="alphabets">A-Z</option>
            <option value="reverseAlphabets">Z-A</option>
          </select>
        </div>
        <div className="space-x-4 content-center justify-self-end pr-16">
          <FormatListBulleted onClick={layoutHandler} />
          <WindowSharp onClick={layoutHandler} />
        </div>
      </div>
      {grid && (
        <div className="w-5/6 mx-auto my-2 grid grid-cols-5 gap-4 h-5/6 ">
          {sortedStocks.map((stockElm) => {
            return (
              <StocksCard
                key={stockElm.stockid}
                stockName={stockElm.stockname}
                stockSymbol={stockElm.stocksymbol}
                stockSector={stockElm.sector}
                stockPrice={stockElm.currentprice}
                stockid={stockElm.stockid}
              />
            );
          })}
        </div>
      )}

      {!grid && (
        <div className="w-5/6 h-5/6 shadow-xl mx-auto my-2 overflow-auto">
          <Stockstable stocks={sortedStocks} />
        </div>
      )}
      <Pagination
        currentPage={currentPage}
        setCurrentPage={setCurrentPage}
        totalPages={totalPages}
      />
    </div>
  );
};

export default AllStocks;
