import { useParams } from "react-router-dom";
import StockCard from "../Components/StockCard.jsx";
import TradingView from "../Components/TradingView.jsx";
import { useEffect, useState } from "react";
import axios from "axios";

const Transaction = () => {
  const { stockid } = useParams();
  const [StockData, setStockData] = useState(null);
  //Fetching the stock data
  useEffect(() => {
    axios
      .get(`http://localhost:8080/stonks/api-v1/stock/${stockid}`)
      .then((res) => {
        // console.log(res.data);
        setStockData(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [stockid]);

  return (
    <div className="grid grid-cols-1 md:grid-cols-4 gap-10 m-auto p-5 w-screen h-max">
      <div className="col-span-1 md:col-span-3 w-full h-full">
        <TradingView />
      </div>
      <div className="w-auto">
        <StockCard stock={StockData} />
      </div>
    </div>
  );
};

export default Transaction;
