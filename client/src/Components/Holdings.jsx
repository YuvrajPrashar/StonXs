import Stockstable from "./Stockstable.jsx";

const Holdings = ({ holdings, className }) => {
  return (
    <div className={`h-full w-full p-4 ${className}`}>
      <div className="text-4xl md:text-6xl underline underline-offset-8 decoration-2 decoration-emerald-500 text-center font-thin tracking-widest mb-4">
        Investments
      </div>
      <div className="shadow-md rounded-lg h-4/5 overflow-auto">
        <Stockstable stocks={holdings} />
      </div>
    </div>
  );
};

export default Holdings;
