import { useState } from "react";

const Footer = () => {
  const [email, setEmail] = useState("");

  const handleSubscribe = (e) => {
    e.preventDefault();
    console.log("Subscribed with email:", email);
  };

  return (
    <footer className="bg-gray-800 text-white p-6 mt-4">
      <div className="container mx-auto grid grid-cols-1 md:grid-cols-4 gap-4 justify-center text-center md:text-left">
        <div className="w-full md:col-span-1 flex justify-center md:justify-start">
          <div>
            <h3 className="text-xl font-bold mb-2">FAQ</h3>
            <ul className="space-y-2">
              <li className="hover:text-gray-400 cursor-pointer">
                How it works
              </li>
              <li className="hover:text-gray-400 cursor-pointer">Pricing</li>
              <li className="hover:text-gray-400 cursor-pointer">Support</li>
            </ul>
          </div>
        </div>
        <div className="w-full md:col-span-1 flex justify-center md:justify-start">
          <div>
            <h3 className="text-xl font-bold mb-2">About</h3>
            <ul className="space-y-2">
              <li className="hover:text-gray-400 cursor-pointer">Our Story</li>
              <li className="hover:text-gray-400 cursor-pointer">Team</li>
              <li className="hover:text-gray-400 cursor-pointer">Careers</li>
            </ul>
          </div>
        </div>
        <div className="w-full md:col-span-1 flex justify-center md:justify-start">
          <div>
            <h3 className="text-xl font-bold mb-2">More</h3>
            <ul className="space-y-2">
              <li className="hover:text-gray-400 cursor-pointer">Blog</li>
              <li className="hover:text-gray-400 cursor-pointer">Contact</li>
              <li className="hover:text-gray-400 cursor-pointer">
                Privacy Policy
              </li>
            </ul>
          </div>
        </div>
        <div className="w-full md:col-span-1 flex justify-center md:justify-start">
          <div className="w-full md:w-auto">
            <h3 className="text-xl font-bold mb-2">
              Subscribe to our Newsletter
            </h3>
            <form
              onSubmit={handleSubscribe}
              className="flex flex-col space-y-2 items-center md:items-start"
            >
              <input
                type="email"
                placeholder="Enter your email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="p-2 bg-gray-700 border border-gray-600 rounded-lg text-white placeholder-gray-400 focus:outline-none w-full"
                required
              />
              <button
                type="submit"
                className="p-2 bg-blue-600 rounded-lg hover:bg-blue-700 focus:outline-none w-full"
              >
                Subscribe
              </button>
            </form>
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
