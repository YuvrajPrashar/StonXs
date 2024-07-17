import StocksRecomandation from "../Components/StocksRecomandation.jsx";
import Categories from "../Components/Categories.jsx";
import MutualFunds from "../Components/MutaualFunds.jsx";
import Otherservices from "../Components/OtherServices.jsx";
const Home = (props) => {
  return (
    <>
      <div className="mx-auto w-3/4 p-10 my-14 flex flex-col gap-6">
        <StocksRecomandation />
        <Categories />
        <MutualFunds />
        <Otherservices />
      </div>
    </>
  );
};

export default Home;
