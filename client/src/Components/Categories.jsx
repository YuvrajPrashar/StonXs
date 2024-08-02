import { useNavigate } from "react-router-dom";
import EmptyCard from "./EmptyCard.jsx";

const Categories = () => {
  const navigate = useNavigate();
  const navigationHandler = (category) => {
    navigate(`/stocks/${category}`);
  };

  return (
    <>
      <div className="font-thin text-3xl">Categories</div>
      <div className="flex flex-wrap justify-start gap-4 py-2">
        <EmptyCard
          text="Large Cap"
          link="src/assets/Large-Cap.png"
          onClick={() => navigationHandler("large-cap")}
        />
        <EmptyCard
          text="Mid Cap"
          link="src/assets/Mid-Cap.png"
          onClick={() => navigationHandler("mid-cap")}
        />
        <EmptyCard
          text="Small Cap"
          link="src/assets/Small_Cap.png"
          onClick={() => navigationHandler("small-cap")}
        />
      </div>
    </>
  );
};

export default Categories;
