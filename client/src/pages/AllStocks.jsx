import StocksCard from "../Components/StocksCard.jsx";
import Stockstable from "../Components/Stockstable.jsx";
import SwapVertIcon from "@mui/icons-material/SwapVert";
import { useEffect, useState } from "react";
import axios from "axios";
import { FormatListBulleted, WindowSharp } from "@mui/icons-material";
import { useNavigate } from "react-router-dom";
const AllStocks = () => {
  const [stocksDATA, setStocksDATA] = useState([]);
  const [grid, setGrid] = useState(true);
  useEffect(() => {
    axios.get("http://localhost:8080/stocks").then((res) => {
      setStocksDATA(res.data);
    });
  }, []);
  const layoutHandler = () => {
    setGrid(!grid);
  };
  return (
    <>
      <div className="p-2 grid grid-flow-col w-screen justify-stretch content-center">
        <div className="p-3 justify-self-start">
          <SwapVertIcon />
          <label htmlFor="sort"> Sort By</label>
          <select name="sort" id="sort" className="bg-transparent">
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
        <div className=" w-5/6 mx-auto m-y-2 grid grid-cols-5 gap-4">
          {stocksDATA.map((stockElm) => {
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
        <div className="w-5/6 h-screen shadow-xl mx-auto m-y-2 overflow-auto">
          <Stockstable stocks={stocksDATA} />
        </div>
      )}
    </>
  );
};

export default AllStocks;
