const Orders = () => {
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
      </div>
    </div>
  );
};

export default Orders;
