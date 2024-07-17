import EmptyCard from "./EmptyCard";

const MutualFunds = () => {
  return (
    <>
      <div className="text-3xl font-thin">Mutual Funds</div>
      <div className="flex space-x-12  m-2 p-2 ">
        <EmptyCard text="Tax Saver" link="src/assets/Tax_Saver.png" />
        <EmptyCard text="S I P" link="src/assets/SIP.png" />
        <EmptyCard text="High Returns" link="src/assets/High_Returns.png" />
      </div>
    </>
  );
};

export default MutualFunds;
