import { useEffect, useState } from "react";
import RecipeCards from './RecipeCards.jsx';


const NavSearch = () => {
    const [query, setQuery] = useState('');
    const [searchRecipes, setSearchRecipes] = useState();
    const [loading, setLoading] = useState(null);
    const [error, setError] = useState(null);

    const handleInputChange = (e) => {
        setQuery(e.target.value);
    };

    //http://localhost:8080/search-recipes?q=${query}
    //http://localhost:8080/recipes

    const handleSubmit = (e) => {
        e.preventDefault();
        setLoading(true);
        fetch('http://localhost:8080/search-recipes?q=' + query)
        .then(response => response.json())
        .then(data => { 
            setSearchRecipes(data);
            setLoading(false);
        })
        .catch(error => {
            setError(err.message);
            setLoading(false);
        });
    };

    // if (loading) {
    //     return <p>Loading recipes...</p>;
    // }

    // if (error) {
    //     return <p>Error: {error}</p>;
    // }


    return (
        <div>
            <form onSubmit={handleSubmit}>
                <label>
                    Search:
                    <input type="text" value={query} onChange={handleInputChange} />
                </label>
                <button type="submit">Submit</button>
            </form>
            {/* <RecipeCards recipes={searchRecipes} title="Search Results" /> */}
        </div>
    );
};

export default NavSearch;
