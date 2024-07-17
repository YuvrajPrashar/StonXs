import EmptyCard from "./EmptyCard.jsx";

const Categories = () => {
  return (
    <>
      <div className="font-thin text-3xl ">Categories</div>
      <div className="flex space-x-12 m-2  p-2 ">
        <EmptyCard text="Large Cap" link="src/assets/Large-Cap.png" />
        <EmptyCard text=" Mid Cap" link="src/assets/Mid-Cap.png" />
        <EmptyCard text="Small Cap" link="src/assets/Small_Cap.png" />
      </div>
    </>
  );
};
export default Categories;
