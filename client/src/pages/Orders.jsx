const Orders = () => {
  return (
    <>
      <div>Orders</div>

      <table className=" w-full text-sm text-left  text-gray-500 ">
        <thead className="text-xs  text-gray-700 uppercase bg-gray-50">
          <tr>
            <th scope="col" className="px-6 py-3">
              Order ID
            </th>
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
      </table>
    </>
  );
};

export default Orders;
