const EmptyCard = (props) => {
  return (
    <div onClick={props.onClick} className="cursor-pointer">
      <div className="p-2 size-56 border-slate-100 border-2 rounded-xl content-center text-center">
        <img className="mx-auto" src={props.link} alt="" width={90} />
        <div className="text-2xl font-thin text-gray-500">{props.text}</div>
        {props.icon}
      </div>
    </div>
  );
};

export default EmptyCard;
