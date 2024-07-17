import { useNavigate } from "react-router-dom";

const StocksCard = (props) => {
  const navigate = useNavigate();
  const StockNavigator = () => {
    console.log(props.stockid);
    navigate(`/stock/${props.stockid}`);
  };

  return (
    <div onClick={StockNavigator}>
      <div className="grid grid-cols-3 p-5 gap-3 size-56 border-slate-100 border-2 rounded-xl">
        <div className=" overflow-visible text-2xl font-extrabold col-span-2  text-green-600 ">
          ${props.stockPrice}
        </div>
        <div className="font-extrabold truncate text-blue-600 justify-self-end ">
          {props.stockSymbol}
        </div>
        <div className="col-span-3 font-semibold text-pretty">
          {props.stockName}
        </div>
        <div className="font-thin col-span-3">{props.stockSector}</div>
      </div>
    </div>
  );
};
export default StocksCard;
