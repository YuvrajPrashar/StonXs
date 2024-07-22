import { AddCircleOutline } from "@mui/icons-material";
import axios from "axios";
import Cookies from "js-cookie";

const StockInfo = (props) => {
  const watchListHandler = () => {
    const watchlistId = Cookies.get("watchlistId");
    axios
      .patch(
        `http://localhost:8080/stock/${props.stockData.stockid}/watchlist/${watchlistId}`
      )
      .then((res) => {
        console.log(res);
      })
      .catch((error) => {
        console.log(error);
      });
  };
  return (
    <>
      <div className="bg-white shadow-md border-2  hover:shadow-lg rounded-3xl p-6 transition-shadow duration-300 ease-in-out my-4">
        <div className="grid grid-cols-3 gap-5 p-3">
          <div className="col-span-2 font-bold text-2xl text-blue-600 ">
            {props.stockData?.stocksymbol}
          </div>
          <div className="justify-self-end">
            <AddCircleOutline onClick={watchListHandler} />
          </div>
          <div className="col-span-3 font-semibold text-lg">
            {props.stockData?.sector}
          </div>
          <div className="col-span-3 font-light text-lg">
            {props.stockData?.stockname}
          </div>
          <div className="col-span-2 font-bold text-2xl">
            ${props.stockData?.currentprice}
          </div>
        </div>
      </div>
    </>
  );
};

export default StockInfo;
