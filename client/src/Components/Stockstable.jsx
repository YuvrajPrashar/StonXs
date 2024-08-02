import { useNavigate } from "react-router-dom";

const Stockstable = (props) => {
  const navigate = useNavigate();
  const Stocks = props.stocks;
  const StockNavigator = (stockid) => {
    navigate(`/stock/${stockid}`);
  };

  return (
    <div className="overflow-x-auto">
      <table className="min-w-full text-sm text-left text-gray-500">
        <thead className="text-xs text-gray-700 uppercase bg-gray-50">
          <tr>
            <th scope="col" className="px-6 py-3">
              Stock Symbol
            </th>
            <th scope="col" className="px-6 py-3">
              Stock Name
            </th>
            <th scope="col" className="px-6 py-3">
              Stock Price
            </th>
            <th scope="col" className="px-6 py-3">
              Stock Category
            </th>
            <th scope="col" className="px-6 py-3">
              <span className="sr-only">Transaction</span>
            </th>
          </tr>
        </thead>
        <tbody>
          {Stocks && Stocks.length > 0 ? (
            Stocks.map((stock) => (
              <tr
                onClick={() => StockNavigator(stock.stockid)}
                key={stock.stockid}
                className="bg-white border-b hover:bg-gray-50"
              >
                <th
                  scope="row"
                  className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap"
                >
                  {stock.stocksymbol}
                </th>
                <td className="px-6 py-4">{stock.stockname}</td>
                <td className="px-6 py-4">{stock.currentprice}</td>
                <td className="px-6 py-4">{stock.sector}</td>
                <td className="px-6 py-4 text-right font-medium text-blue-600 hover:underline">
                  Buy/Sell
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td
                colSpan="5"
                className="text-center text-2xl font-extrabold p-10"
              >
                No Stocks Available
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default Stockstable;
