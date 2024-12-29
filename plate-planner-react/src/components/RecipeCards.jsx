import { useEffect, useState } from "react";
import axios from "axios";

function addTags(recipeId) {
    alert("add Tag to recipeId: " + recipeId);
};

function saveRecipe(recipeId) {
    alert("save Recipe recipeId: " + recipeId);
};


function RecipeCards(props) {

    if (props.recipes != undefined) {
        return (
         <div key="recipecards">
            {props.recipes != null && props.title && 
                <h1>{props.recipes.length} {props.title}</h1>
            }
            {props.recipes.map((recipe) => (
                <div key={recipe.id != null ? recipe.id : recipe.name} className="card">
                    <h2 className='card-title'>{recipe.name}</h2>
                    <p>{recipe.description}</p>
                    {recipe.id != null &&
                    <p><input type="button" value="Add Tag" onClick={() => addTags(recipe.id)}/></p>
                    }
                    {recipe.id == null &&
                    <p><input type="button" value="Save Recipe" onClick={() => saveRecipe(recipe.id)}/></p>
                    }
                    <img src={recipe.imageURL} alt={recipe.name + " image"} className='card-img-top mx-auto d-block w-25'/>
                    <h3>Ingredients:</h3>
                    <ul>
                        {recipe.recipeIngredients.map((ingredient) => (
                            <li key={ingredient.id != null ? ingredient.id : ingredient.name}>
                                {ingredient.quantity} {ingredient.unit} {ingredient.ingredient.name}
                            </li>
                        ))}
                    </ul>
                    <h3>Instructions:</h3>
                    <p>{recipe.instructions}</p>
                </div>
            ))}
        </div>
        );
    } else {
        return (<div key="recipecards"></div>);
    }
};

export default RecipeCards;
