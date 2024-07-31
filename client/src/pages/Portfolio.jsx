import { useEffect, useState } from "react";
import Holdings from "../Components/Holdings.jsx";
import Report from "../Components/Report.jsx";
import axios from "axios";
const Portfolio = () => {
  const [data, setData] = useState(null);

  const portfolioId = localStorage.getItem("portfolioId");

  const token = localStorage.getItem("token");
  const config = {
    headers: { Authorization: `${token}` },
  };
  useEffect(() => {
    axios
      .get(`http://localhost:8080/stonks/auth/api-v1/portfolio/${portfolioId}`, config)
      .then((res) => {
        console.log(res.data);
        setData(res.data);
      });
  }, []);
  return (
    <div className="h-screen">
      <div className="flex h-full ">
        {data && <Holdings holdings={data.stocks} />}
        {data && (
          <Report
            PNL={data.pnl}
            InvestedValue={data.investedvalue}
            CurrentValue={data.currentValue}
            Balance={data.balance}
          />
        )}
      </div>
    </div>
  );
};

export default Portfolio;
