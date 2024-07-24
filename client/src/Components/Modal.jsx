import { useEffect } from "react";
import ReactDOM from "react-dom";

const Modal = ({ message, onClose, timeout = 2500 }) => {
  useEffect(() => {
    const timer = setTimeout(onClose, timeout);
    return () => clearTimeout(timer);
  }, [onClose, timeout]);

  return ReactDOM.createPortal(
    <div className="fixed inset-0 flex items-center justify-center z-50 bg-black bg-opacity-50">
      <div className="bg-white p-6 rounded-lg shadow-lg max-w-sm w-full text-center">
        <p className="text-lg mb-4">{message}</p>
        <button
          className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
          onClick={onClose}
        >
          Close
        </button>
      </div>
    </div>,
    document.getElementById("portal-root")
  );
};

export default Modal;
