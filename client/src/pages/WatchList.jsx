import Stockstable from "../Components/Stockstable";

const WatchList = () => {
  return (
    <div className="w-screen p-5">
      <div className="font-thin text-6xl text-center underline underline-offset-8 decoration-emerald-600 decoration-2 ">
        Watchlist
      </div>
      <div className=" w-3/4 my-20 mx-auto p-2 h-screen overflow-auto shadow-2xl ">
        <Stockstable />
      </div>
    </div>
  );
};
export default WatchList;
