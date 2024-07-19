import { useState } from "react";

const TradeForm = ({ stockData }) => {
  const [activeButton, setActiveButton] = useState(null);
  const handleClick = (button) => {
    setActiveButton(button);
  };
  return (
    <form
      className="w-full text-lg my-4 p-4 border-2 border-gray-200 rounded-lg"
      onSubmit={(e) => {
        e.preventDefault();
      }}
    >
      <div className="w-full h-10 gap-3 flex mx-auto my-2">
        <button
          className={`w-1/2 font-bold rounded-xl  border-2 border-red-500 ${
            activeButton === "buy"
              ? "bg-red-500 text-white"
              : "bg-white text-red-500"
          }`}
          onClick={() => handleClick("buy")}
        >
          Buy
        </button>
        <button
          className={`w-1/2 font-bold rounded-xl  border-2 border-green-500 ${
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
          placeholder="Buy Quantity"
          className="border-b-2 font-semibold focus:outline-none placeholder:font-semibold"
        />
        <input
          type="number"
          placeholder="Buy Price"
          className="border-b-2 font-semibold focus:outline-none placeholder:font-semibold"
        />
      </div>
      <button className="my-2 border-0 w-full text-lg h-10 font-bold text-white rounded-md bg-cyan-500">
        Confirm Trade
      </button>
    </form>
  );
};

export default TradeForm;
