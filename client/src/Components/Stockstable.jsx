import StocksData from "../Utils/Data";

const Stockstable = () => {
  return (
    <>
      <table className=" w-full text-sm text-left  text-gray-500 ">
        <thead className="text-xs  text-gray-700 uppercase bg-gray-50 ">
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
          {StocksData ? (
            StocksData.map((stock) => {
              return (
                <tr
                  key={stock.id}
                  className="bg-white border-b  hover:bg-gray-50 "
                >
                  <th
                    scope="row"
                    className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap "
                  >
                    {stock.stockSymbl}
                  </th>
                  <td className="px-6 py-4">{stock.stockName}</td>
                  <td className="px-6 py-4">{stock.current_price}</td>
                  <td className="px-6 py-4">{stock.category}</td>
                  <td className="px-6 py-4 text-right">
                    <a
                      href="#"
                      className="font-medium text-blue-600 dark:text-blue-500 hover:underline"
                    >
                      Buy/Sell
                    </a>
                  </td>
                </tr>
              );
            })
          ) : (
            <tr>
              <td
                colSpan="5"
                className="text-center text-6xl font-extrabold p-10"
              >
                No Data Yet
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </>
  );
};
export default Stockstable;
