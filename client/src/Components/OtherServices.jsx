import EmptyCard from "./EmptyCard";

const Otherservices = () => {
  return (
    <>
      <div className="font-thin text-3xl">Other Services</div>
      <div className="flex flex-nowrap flex-row space-x-12 overflow-scroll m-2 p-2 ">
        <EmptyCard text="ETF's" link="src/assets/ETF.png" />
        <EmptyCard text="Currency" link=" src/assets/Currency.png " />
        <EmptyCard text="Gold Bonds" link="src/assets/Gold.png" />
        <EmptyCard text="Insurance" link="src/assets/Insurance.png" />
        <EmptyCard text="Loans" link="src/assets/LOANS.png" />
        <EmptyCard text="NPS" link="src/assets/NPS.png" />
        <EmptyCard text="FD" link="src/assets/FixedDeposit.png" />
        <EmptyCard text="Real Estate" link="src/assets/RealEstate.png" />
        <EmptyCard text="Tax" link="src/assets/Tax.png" />
        <EmptyCard
          text="Wealth Management"
          link="src/assets/Wealth_Genration.png"
        />
      </div>
    </>
  );
};

export default Otherservices;
