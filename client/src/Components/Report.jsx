import { Add } from "@mui/icons-material";

const Report = ({ PNL, InvestedValue, CurrentValue, Balance, className }) => {
  return (
    <div className={`h-full w-full p-4 ${className}`}>
      <div className="text-4xl md:text-6xl underline underline-offset-8 decoration-2 decoration-blue-600 font-thin text-center mb-4">
        PnL & Report
      </div>
      <div className="grid grid-cols-1 gap-6 p-4 bg-white border-2 border-dotted border-green-500 rounded-xl shadow-md">
        <div className="p-4 rounded-2xl shadow-lg text-center hover:border-2 hover:border-slate200">
          <div className="font-bold text-lg md:text-xl">Invested Value</div>
          <div className="text-lg md:text-xl">${InvestedValue}</div>
        </div>
        <div className="p-4 rounded-2xl shadow-lg text-center hover:border-2 hover:border-slate-200">
          <div className="font-bold text-lg md:text-xl">Current Value</div>
          <div className="text-lg md:text-xl">${CurrentValue}</div>
        </div>
        <div className="p-4 rounded-2xl shadow-lg text-center hover:border-2 hover:border-slate-200">
          <div className="font-bold text-lg md:text-xl">Profit/Loss</div>
          <div className="text-lg md:text-xl">${PNL}</div>
        </div>
        <div className="p-4 rounded-2xl shadow-lg text-center hover:border-2 hover:border-slate-200">
          <div className="font-bold text-lg md:text-xl">Balance</div>
          <div className="text-lg md:text-xl">${Balance}</div>
          <Add />
        </div>
      </div>
    </div>
  );
};

export default Report;
