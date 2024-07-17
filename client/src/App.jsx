import Portfolio from "./pages/Portfolio.jsx";
import Login from "./pages/Login.jsx";
import AllStocks from "./pages/AllStocks.jsx";
import Home from "./pages/Home.jsx";
import Transaction from "./pages/Transaction.jsx";
import WatchList from "./pages/WatchList.jsx";
import { Routes, Route } from "react-router-dom";
import Footer from "./Components/footer.jsx";
import Orders from "./pages/Orders.jsx";
import Navbar from "./Components/Navbar.jsx";

function App() {
  return (
    <>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/stocks" element={<AllStocks />} />
        <Route path="/login" element={<Login />} />
        <Route path="/stock/:stockid" element={<Transaction />} />
        <Route path="/portfolio" element={<Portfolio />} />
        <Route path="/watchlist" element={<WatchList />} />
        <Route path="/orders" element={<Orders />} />
      </Routes>
      <Footer />
    </>
  );
}

export default App;
