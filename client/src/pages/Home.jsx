import StocksRecomandation from "../Components/StocksRecomandation.jsx";
import Categories from "../Components/Categories.jsx";
import MutualFunds from "../Components/MutaualFunds.jsx";
import Otherservices from "../Components/OtherServices.jsx";
const Home = (props) => {
  return (
    <>
      <div className="container mx-auto px-4 py-10 my-14 flex flex-col gap-6">
        <StocksRecomandation />
        <Categories />
        <MutualFunds />
        <Otherservices />
      </div>
    </>
  );
};

export default Home;
