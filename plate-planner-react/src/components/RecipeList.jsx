import { useEffect, useState } from "react";
import RecipeCards from './RecipeCards.jsx';
import recipeService from "@/services/recipeService.js";
import Cookies from 'js-cookie';


const RecipeList = () => {
    const [recipes, setRecipes] = useState([]);
    const [filteredRecipes, setFilteredRecipes] = useState([]);
    const [tags, setTags] = useState([]);
    const [selectedTags, setSelectedTags] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const userName = Cookies.get('username');

    if (userName == null || userName.length == 0) {
        return <p>Please login to see your saved recipes.</p>
    } else {
        useEffect(() => {
            recipeService.getAllByUser()
                .then((response) => {
                    setRecipes(response.data);
                    setLoading(false);
                })
                .catch((err) => {
                    console.log("Error retrieving saved recipes: ", err.message);
                    setError("There was a problem retrieving your saved recipes.");
                    setLoading(false);
                });
        }, []);    
    }

    if (loading) {
        return <p>Loading recipes...</p>;
    }

    if (error) {
        return <p>Error: {error}</p>;
    }

    function handleSaveRecipe(recipeId) {
        //call API using axios here
    }

    function handleAddRecipeToMealPlan(recipeId) {
        //open a modal, allow user to select meal plan or create new meal plan, call API
    }

    return (
        <div>
            <h2>Saved Recipes</h2>
            <RecipeCards recipes={recipes} />
        </div>
    );
};

export default RecipeList;