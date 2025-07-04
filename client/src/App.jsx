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
import SignUp from "./pages/SignUp.jsx";
import SearchResults from "./pages/SearchResults.jsx";

//Creating the routes array
const routes = [
  { path: "/", element: <Home /> },
  { path: "/stocks", element: <AllStocks /> },
  { path: "/stocks/:category", element: <AllStocks /> },
  { path: "/login", element: <Login /> },
  { path: "/signup", element: <SignUp /> },
  { path: "/stock/:stockid", element: <Transaction /> },
  { path: "/portfolio", element: <Portfolio /> },
  { path: "/watchlist", element: <WatchList /> },
  { path: "/orders", element: <Orders /> },
  {path:"/search",element:<SearchResults/>}
];

function App() {
  return (
    <>
      <Navbar />
      <Routes>
        {routes.map((route, index) => (
          <Route key={index} path={route.path} element={route.element} />
        ))}
      </Routes>
      <Footer />
    </>
  );
}

export default App;
