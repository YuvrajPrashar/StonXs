import { Add } from "@mui/icons-material";

const Report = ({ PNL, InvestedValue, CurrentValue, Balance }) => {
  return (
    <div className="h-full w-max mx-auto">
      <div className="text-6xl underline underline-offset-8 decoration-2 decoration-blue-600 font-thin text-center m-4 p-4 ">
        PnL & Report
      </div>
      <div className="grid grid-cols-2 gap-6 p-10 hover:shadow-md  bg-white border-2 border-dotted border-green-500 h-auto rounded-xl">
        <div className="p-4 rounded-2xl shadow-lg content-center text-center hover:border-2 hover:border-slate200 h-40">
          <div className="font-bold text-xl">Invested Value</div>
          <div className="">${InvestedValue}</div>
        </div>
        <div className=" p-4 rounded-2xl text-center content-center hover:border-2 hover:border-slate-200 shadow-lg h-40">
          <div className="font-bold text-xl">Current Value</div>
          <div>${CurrentValue}</div>
        </div>
        <div className=" p-4 col-span-2 rounded-2xl text-center content-center hover:border-2 hover:border-slate-200 shadow-lg h-32">
          <div className="font-bold text-xl ">Profit/Lost</div>
          <div>${PNL}</div>
        </div>
        <div className=" space-y-4 justify-center col-span-2 p-4 rounded-2xl text-center content-center hover:border-2 hover:border-slate-200 shadow-lg h-40">
          <div className="content-center">
            <div className="font-bold text-xl">Balance</div>
            <div>${Balance}</div>
          </div>
          <Add />
        </div>
      </div>
    </div>
  );
};

export default Report;
