import StockInfo from "./StockInfo";
import TradeForm from "./TradeForm";

const StockCard = (props) => {
  return (
    <>
      <StockInfo stockData={props.stock} />
      <TradeForm stockData={props.stock} />
    </>
  );
};

export default StockCard;
