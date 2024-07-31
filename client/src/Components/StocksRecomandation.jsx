import { ArrowForward } from "@mui/icons-material";
import EmptyCard from "./EmptyCard.jsx";
import StocksCard from "./StocksCard.jsx";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";

const StocksRecomandation = () => {
  const [stocksDATA, setStocksDATA] = useState([]);
  const navigate = useNavigate();
  useEffect(() => {
    axios
      .get("http://localhost:8080/stonks/api-v1/stocks?pageNo=2&pageSize=7")
      .then((res) => {
        setStocksDATA(res.data.content);
      });
  }, []);
  const stockHandler = () => {
    navigate("/stocks");
  };
  return (
    <>
      <div className="font-thin text-3xl ">Stocks</div>
      <div className="flex flex-nowrap flex-row space-x-12 overflow-scroll m-2 p-2">
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
        <EmptyCard
          text="View All"
          icon={<ArrowForward />}
          onClick={stockHandler}
        />
      </div>
    </>
  );
};

export default StocksRecomandation;
