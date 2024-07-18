import Stockstable from "./Stockstable.jsx";

const Holdings = () => {
  return (
    <div className="h-full mx-auto ">
      <div className="font-thin text-6xl underline underline-offset-8 decoration-2 decoration-emerald-500 text-center tracking-widest  m-4 p-4 ">
        Investments
      </div>
      <div className=" shadow-md sm:rounded-lg h-4/5 overflow-auto ">
        <Stockstable />
      </div>
    </div>
  );
};

export default Holdings;
