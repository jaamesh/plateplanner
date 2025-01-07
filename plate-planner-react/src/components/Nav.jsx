import NavSearch from "./NavSearch";
import { Link } from 'react-router-dom';

const Nav = () => {
    return (
    <nav className="nav nav-pills nav-fill">
        <Link className="nav-link" to="/">Find Recipes</Link>
        <Link className="nav-link" to="/saved-recipes">Saved Recipes</Link>
        <Link className="nav-link" to="/meal-plans">Meal Plans</Link>
        <Link className="nav-link" to="/shopping-lists">Shopping Lists</Link>
        <Link className="nav-link" to="/profile">Profile</Link>
        <Link className="nav-link" to="/logout">Log Out</Link>
    </nav>
    );
}

export default Nav;