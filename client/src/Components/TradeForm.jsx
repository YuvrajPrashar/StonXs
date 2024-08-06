import axios from "axios";
import { useRef, useState } from "react";
import Modal from "./Modal.jsx";

const TradeForm = ({ stockData }) => {
  const [showModal, setShowModal] = useState(false);
  const [modalMessage, setModalMessage] = useState("");
  const [activeButton, setActiveButton] = useState("buy");
  const quantityRef = useRef();
  const priceRef = useRef();

  //Fetching the userId and stockId from the local storage
  const userId = localStorage.getItem("userId");
  const stockId = stockData?.stockid;

  //Function to handle the trade
  const tradeHandler = () => {
    const data = {
      quantity: quantityRef.current.value,
      price: priceRef.current.value,
      transactiontype: activeButton,
      status: "pending",
    };

    const token = localStorage.getItem("token");
    const config = {
      headers: { Authorization: `${token}` },
    };

    if (userId) {
      axios
        .post(
          `http://localhost:8080/stonks/auth/api-v1/transaction/user/${userId}/stock/${stockId}`,
          data,
          config
        )
        .then((res) => {
          setShowModal(true);
          setModalMessage(res.data);
        })
        .catch((err) => {
          setShowModal(true);
          setModalMessage(err.response.data);
        });
    } else {
      setShowModal(true);
      setModalMessage("Please login to trade");
    }
  };

  const handleClick = (button) => {
    setActiveButton(button);
  };

  return (
    <>
      <form
        className="w-full text-lg my-4 p-4 border-2 border-gray-200 rounded-lg"
        onSubmit={(e) => {
          e.preventDefault();
        }}
      >
        <div className="w-full h-10 gap-3 flex mx-auto my-2">
          <button
            type="button"
            className={`w-1/2 font-bold rounded-xl border-2 border-red-500 ${
              activeButton === "buy"
                ? "bg-red-500 text-white"
                : "bg-white text-red-500"
            }`}
            onClick={() => handleClick("buy")}
          >
            Buy
          </button>
          <button
            type="button"
            className={`w-1/2 font-bold rounded-xl border-2 border-green-500 ${
              activeButton === "sell"
                ? "bg-green-500 text-white"
                : "bg-white text-green-500"
            }`}
            onClick={() => handleClick("sell")}
          >
            Sell
          </button>
        </div>
        <div className="w-full m-2 p-2 content-center flex flex-col gap-4 rounded-xl mx-auto">
          <input
            type="text"
            placeholder={stockData?.stocksymbol}
            className="border-b-2 font-semibold focus:outline-none placeholder:font-semibold bg-inherit"
            disabled
          />
          <input
            className="bg-inherit border-b-2 font-semibold focus:outline-none placeholder:font-semibold"
            type="text"
            placeholder={stockData?.stockname}
            disabled
          />
          <input
            type="text"
            placeholder={`$ ${stockData?.currentprice}`}
            className="bg-inherit border-b-2 font-semibold focus:outline-none placeholder:font-semibold"
            disabled
          />
          <input
            type="number"
            placeholder={`${activeButton === "buy" ? "Buy" : "Sell"} Quantity`}
            className="border-b-2 font-semibold focus:outline-none placeholder:font-semibold"
            ref={quantityRef}
          />
          <input
            type="number"
            placeholder={`${activeButton === "buy" ? "Buy" : "Sell"} Price`}
            className="border-b-2 font-semibold focus:outline-none placeholder:font-semibold"
            ref={priceRef}
          />
        </div>
        <button
          type="button"
          className="my-2 border-0 w-full text-lg h-10 font-bold text-white rounded-md bg-cyan-500"
          onClick={tradeHandler}
        >
          Confirm Trade
        </button>
      </form>
      {showModal && (
        <Modal
          message={modalMessage}
          onClose={() => {
            setShowModal(false);
          }}
        />
      )}
    </>
  );
};

export default TradeForm;
