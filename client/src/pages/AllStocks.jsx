import StocksCard from "../Components/StocksCard.jsx";
import SwapVertIcon from "@mui/icons-material/SwapVert";
import { useEffect, useState } from "react";
import axios from "axios";
const AllStocks = () => {
  const [stocksDATA, setStocksDATA] = useState([]);
  useEffect(() => {
    axios.get("http://localhost:8080/stocks").then((res) => {
      setStocksDATA(res.data);
    });
  }, []);
  return (
    <>
      <div className="m-4">
        <SwapVertIcon></SwapVertIcon>
        <label htmlFor="sort"> Sort By</label>
        <select name="sort" id="sort" className="bg-transparent">
          <option value="price">Price</option>
          <option value="alphabets">A-Z</option>
          <option value="reverseAlphabets">Z-A</option>
        </select>
      </div>
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
    </>
  );
};

export default AllStocks;
