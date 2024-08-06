import { useState } from "react";
import { AddCircleOutline } from "@mui/icons-material";
import axios from "axios";
import Modal from "./Modal";

const StockInfo = (props) => {
  const [showModal, setShowModal] = useState(false);
  const [modalMessage, setModalMessage] = useState("");

  //Fetching the watchlistId and token from the local storage
  const watchlistId = localStorage.getItem("watchlistId");
  const token = localStorage.getItem("token");
  const config = { headers: { Authorization: `${token}` } };

  const watchListHandler = () => {
    watchlistId
      ? axios
          .patch(
            `http://localhost:8080/stonks/auth/api-v1/stock/${props.stockData.stockid}/watchlist/${watchlistId}`,
            [],
            config
          )
          .then((res) => {
            setModalMessage(res.data);
            setShowModal(true);
          })
          .catch((error) => {
            setModalMessage(error.response.data.message);
            setShowModal(true);
          })
      : setModalMessage("Please login to add to watchlist");
    setShowModal(true);
  };

  return (
    <div className="bg-white shadow-md border-2 hover:shadow-lg rounded-3xl p-6 transition-shadow duration-300 ease-in-out my-4">
      <div className="grid grid-cols-1 sm:grid-cols-3 gap-5 p-3">
        <div className="col-span-2 font-bold text-2xl text-blue-600">
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
      {showModal && (
        <Modal message={modalMessage} onClose={() => setShowModal(false)} />
      )}
    </div>
  );
};

export default StockInfo;
