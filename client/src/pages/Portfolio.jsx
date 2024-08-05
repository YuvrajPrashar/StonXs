import { useEffect, useState } from "react";
import Holdings from "../Components/Holdings.jsx";
import Report from "../Components/Report.jsx";
import axios from "axios";

const Portfolio = () => {
  const [data, setData] = useState(null);
  const portfolioId = localStorage.getItem("portfolioId");
  const token = localStorage.getItem("token");
  if (!portfolioId || !token) {
    window.location.href = "/login";
  }
  const config = {
    headers: { Authorization: `${token}` },
  };

  useEffect(() => {
    axios
      .get(
        `http://localhost:8080/stonks/auth/api-v1/portfolio/${portfolioId}`,
        config
      )
      .then((res) => {
        setData(res.data);
      });
  }, []);

  return (
    <div className="min-h-screen grid grid-cols-1 lg:grid-cols-3 gap-4 p-4">
      {data && (
        <Holdings holdings={data.stocks} className="col-span-2 mb-4 lg:mb-0" />
      )}
      {data && (
        <Report
          PNL={data.pnl}
          InvestedValue={data.investedvalue}
          CurrentValue={data.currentValue}
          Balance={data.balance}
          className="col-span-1"
        />
      )}
    </div>
  );
};

export default Portfolio;
