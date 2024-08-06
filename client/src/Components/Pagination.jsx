import { ArrowBackIos, ArrowForwardIos } from "@mui/icons-material";

const Pagination = ({ totalPages, setCurrentPage, currentPage }) => {
  
  //Functions to handle the button clicks
  
  const handlePrevious = () => {
    if (currentPage >= 1) {
      setCurrentPage(currentPage - 1);
    }
  };

  const handleNext = () => {
    if (currentPage < totalPages - 1) {
      setCurrentPage(currentPage + 1);
    }
  };

  return (
    <div className="flex justify-center items-center p-2 m-4">
      <ul className="inline-flex -space-x-px text-base h-10">
        <li>
          <button
            onClick={handlePrevious}
            className={`flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-e-0 border-gray-300 rounded-l-lg hover:bg-gray-100 hover:text-gray-700 ${
              currentPage === 0 ? "opacity-50 cursor-not-allowed" : ""
            }`}
            disabled={currentPage === 0}
          >
            <ArrowBackIos />
          </button>
        </li>
        <li>
          <span className="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300">
            {currentPage + 1}
          </span>
        </li>
        <li>
          <button
            onClick={handleNext}
            className={`flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 rounded-r-lg hover:bg-gray-100 hover:text-gray-700 ${
              currentPage + 1 === totalPages
                ? "opacity-50 cursor-not-allowed"
                : ""
            }`}
            disabled={currentPage + 1 === totalPages}
          >
            <ArrowForwardIos />
          </button>
        </li>
      </ul>
    </div>
  );
};

export default Pagination;
