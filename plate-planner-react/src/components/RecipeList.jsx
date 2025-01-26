import { useEffect, useState } from "react";
import RecipeCards from "./RecipeCards.jsx";
import recipeService from "@/services/recipeService.js";

const RecipeList = () => {
  const [recipes, setRecipes] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    recipeService
      .getAll()
      .then((response) => {
        setRecipes(response.data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err.message);
        setLoading(false);
      });
  }, []);

  if (loading) {
    return <p>Loading recipes...</p>;
  }

  if (error) {
    return <p>Error: {error}</p>;
  }

  return (
    <div>
      <h2>Saved Recipes</h2>
      <RecipeCards recipes={recipes} />
    </div>
  );
};

export default RecipeList;
