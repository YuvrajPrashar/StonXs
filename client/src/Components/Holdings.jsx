import StocksData from "../Utils/Data.js";
import Stockstable from "./Stockstable.jsx";

const Holdings = () => {
  return (
    <div className="h-full mx-auto ">
      <div className="font-thin text-6xl underline underline-offset-8 decoration-2 decoration-emerald-500 text-center tracking-widest  m-4 p-4 ">
        Investments
      </div>
      {StocksData ? (
        <div className=" shadow-md sm:rounded-lg h-4/5 overflow-auto ">
          <Stockstable />
        </div>
      ) : (
        <h2 className="text-center text-2xl text-gray-500 my-8">
          You have not purchased anything yet
        </h2>
      )}
    </div>
  );
};

export default Holdings;
