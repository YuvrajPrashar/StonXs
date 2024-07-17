import {
  Add,
  AddCircleOutline,
  AddRounded,
  CreateTwoTone,
} from "@mui/icons-material";
import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

const StockInfo = (props) => {
  return (
    <>
      <div className="bg-white shadow-md border-2  hover:shadow-lg rounded-3xl p-6 transition-shadow duration-300 ease-in-out my-4">
        <div className="grid grid-cols-3 gap-5 p-3">
          <div className="col-span-2 font-bold text-2xl text-blue-600 ">
            {props.stockData?.stocksymbol}
          </div>
          <div className="justify-self-end">
            <AddCircleOutline />
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
      </div>
    </>
  );
};

export default StockInfo;
