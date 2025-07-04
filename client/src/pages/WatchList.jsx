import { useEffect, useState } from "react";
import Stockstable from "../Components/Stockstable";
import axios from "axios";
const WatchList = () => {
  const [stocksData, setStocks] = useState([]);
  const token = localStorage.getItem("token");
  const watchlistId = localStorage.getItem("watchlistId");
  if (!watchlistId || !token) {
    window.location.href = "/login";
  }
  const config = {
    headers: { Authorization: `${token}` },
  };
  useEffect(() => {
    axios
      .get(
        `http://localhost:8080/stonks/auth/api-v1/watchlist/${watchlistId}`,
        config
      )
      .then((res) => {
        setStocks(res.data.stocks);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <div className="w-screen p-5">
      <div className="font-thin text-6xl text-center underline underline-offset-8 decoration-emerald-600 decoration-2 ">
        Watchlist
      </div>
      <div className=" w-3/4 my-20 mx-auto p-2 h-screen overflow-auto shadow-2xl ">
        <Stockstable stocks={stocksData} />
      </div>
    </div>
  );
};
export default WatchList;
