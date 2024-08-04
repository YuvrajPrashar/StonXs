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
  const [selectedSector, setSelectedSector] = useState(""); // New state for selected sector

  const sectors = [
    "Communication Services",
    "Consumer Discretionary",
    "Consumer Staples",
    "Energy",
    "Financials",
    "Health Care",
    "Industrials",
    "Information Technology",
    "Materials",
    "Utilities",
  ];

  useEffect(() => {
    const fetchStocks = async () => {
      try {
        const params = {
          pageNo: currentPage,
          pageSize: pageSize,
        };

        if (selectedSector) {
          params.sector = selectedSector;
          setCurrentPage(0);
        }

        const response = category
          ? await axios.get(
              `http://localhost:8080/stonks/api-v1/stocks/${category}`,
              { params }
            )
          : await axios.get(`http://localhost:8080/stonks/api-v1/stocks`, {
              params,
            });

        setStocksDATA(response.data.content);
        setSortedStocks(response.data.content);
        setTotalPages(response.data.totalPages);
      } catch (error) {
        console.error("Error fetching stocks:", error);
      }
    };

    fetchStocks();
  }, [category, currentPage, pageSize, selectedSector]);

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
    <div className="min-h-screen">
      <div className="p-2 flex flex-wrap items-center justify-between w-full">
        <div className="p-3 flex items-center">
          <SwapVertIcon />
          <label htmlFor="sort" className="ml-2">
            Sort By
          </label>
          <select
            name="sort"
            id="sort"
            className="bg-transparent ml-2"
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
        <div className="p-3 flex items-center">
          <label htmlFor="sector" className="ml-2">
            Sector
          </label>
          <select
            name="sector"
            id="sector"
            className="bg-transparent ml-2"
            onChange={(e) => {
              setSelectedSector(e.target.value);
            }}
          >
            <option value="">All</option>
            {sectors.map((sector) => (
              <option key={sector} value={sector}>
                {sector}
              </option>
            ))}
          </select>
        </div>
        <div className="flex space-x-4 pr-4">
          <FormatListBulleted
            onClick={layoutHandler}
            className="cursor-pointer"
          />
          <WindowSharp onClick={layoutHandler} className="cursor-pointer" />
        </div>
      </div>
      {grid ? (
        <div className="w-full px-4 my-4 grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 gap-4 justify-items-center">
          {sortedStocks.map((stockElm) => (
            <StocksCard
              key={stockElm.stockid}
              stockName={stockElm.stockname}
              stockSymbol={stockElm.stocksymbol}
              stockSector={stockElm.sector}
              stockPrice={stockElm.currentprice}
              stockid={stockElm.stockid}
            />
          ))}
        </div>
      ) : (
        <div className="w-full px-4 my-4 shadow-xl overflow-auto">
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
