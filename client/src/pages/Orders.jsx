import axios from "axios";
import { useEffect, useState } from "react";

const Orders = () => {
  const [orders, setOrders] = useState(null);
  useEffect(() => {
    const userId = localStorage.getItem("userId");
    axios.get(`http://localhost:8080/transactions/${userId}`).then((res) => {
      console.log(res.data);
      setOrders(res.data);
    });
  }, []);
  return (
    <div className="w-screen h-screen space-y-10 p-10 ">
      <div className="text-6xl text-center underline decoration-teal-600 underline-offset-8 decoration-2 font-thin">
        Orders
      </div>
      <div className="overflow-auto">
        <table className="  w-4/6 mx-auto text-sm text-left  text-gray-500 ">
          <thead className="text-xs  text-gray-700 uppercase bg-gray-50">
            <tr>
              <th scope="col" className="px-6 py-3">
                Stock
              </th>
              <th scope="col" className="px-6 py-3">
                Price
              </th>
              <th scope="col" className="px-6 py-3">
                {" "}
                Quantity
              </th>
              <th scope="col" className="px-6 py-3">
                Order Type
              </th>
              <th scope="col" className="px-6 py-3">
                Status
              </th>
            </tr>
          </thead>
          <tbody>
            {orders ? (
              orders.map((order) => (
                <tr key={order.id} className="bg-white">
                  <td className="px-6 py-4 whitespace-nowrap">
                    {order.stock.stockName}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap">{order.price}</td>
                  <td className="px-6 py-4 whitespace-nowrap">
                    {order.quantity}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap">
                    {order.transactiontype}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap">
                    {order.status}
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td
                  colSpan="6"
                  className="text-center text-6xl font-extrabold p-10"
                >
                  No Orders Yet
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Orders;
