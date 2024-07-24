import { useState } from "react";

const Footer = () => {
  const [email, setEmail] = useState("");

  const handleSubscribe = (e) => {
    e.preventDefault();
    console.log("Subscribed with email:", email);
  };

  return (
    <footer className="bg-gray-800 text-white p-6 mt-4">
      <div className="container mx-auto flex flex-wrap justify-between">
        <div className="w-full md:w-1/4 mb-4 md:mb-0">
          <h3 className="text-xl font-bold mb-2">FAQ</h3>
          <ul className="space-y-2">
            <li className="hover:text-gray-400 cursor-pointer">How it works</li>
            <li className="hover:text-gray-400 cursor-pointer">Pricing</li>
            <li className="hover:text-gray-400 cursor-pointer">Support</li>
          </ul>
        </div>
        <div className="w-full md:w-1/4 mb-4 md:mb-0">
          <h3 className="text-xl font-bold mb-2">About</h3>
          <ul className="space-y-2">
            <li className="hover:text-gray-400 cursor-pointer">Our Story</li>
            <li className="hover:text-gray-400 cursor-pointer">Team</li>
            <li className="hover:text-gray-400 cursor-pointer">Careers</li>
          </ul>
        </div>
        <div className="w-full md:w-1/4 mb-4 md:mb-0">
          <h3 className="text-xl font-bold mb-2">
            Subscribe to our Newsletter
          </h3>
          <form onSubmit={handleSubscribe} className="flex flex-col space-y-2">
            <input
              type="email"
              placeholder="Enter your email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="p-2 bg-gray-700 border border-gray-600 rounded-lg text-white placeholder-gray-400 focus:outline-none"
              required
            />
            <button
              type="submit"
              className="p-2 bg-blue-600 rounded-lg hover:bg-blue-700 focus:outline-none"
            >
              Subscribe
            </button>
          </form>
        </div>
        <div className="flex justify-end w-full md:w-1/4 mb-4 md:mb-0 ">
          <div>
            <h3 className=" text-start text-xl font-bold mb-2">More</h3>
            <ul className=" text-start space-y-2">
              <li className="hover:text-gray-400 cursor-pointer">Blog</li>
              <li className="hover:text-gray-400 cursor-pointer">Contact</li>
              <li className="hover:text-gray-400 cursor-pointer">
                Privacy Policy
              </li>
            </ul>
          </div>
        </div>
      </div>
      <div className="text-center mt-6 border-t border-gray-700 pt-4">
        <p>&copy; 2024 Stonks. All rights reserved.</p>
      </div>
    </footer>
  );
};

export default Footer;
