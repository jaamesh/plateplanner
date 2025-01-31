import "../styles/Header.css";
import { Link } from "react-router-dom";

const Header = () => {
  return (
    <header className="bg-primary text-white text-center py-4">
      <h1 className="display-4">
        <Link to="/" className="header-title">
          PlatePlanner
        </Link>
      </h1>
      <p className="lead">Meal Planning Made Easy</p>
    </header>
  );
};

export default Header;
