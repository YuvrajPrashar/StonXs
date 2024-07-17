import Holdings from "../Components/Holdings.jsx";
import Report from "../Components/Report.jsx";
const Portfolio = () => {
  return (
    <div className="h-screen">
      <div className="flex h-full ">
        <Holdings />
        <Report />
      </div>
    </div>
  );
};

export default Portfolio;
