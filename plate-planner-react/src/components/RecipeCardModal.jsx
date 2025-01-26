import { useState, useEffect } from "react";
import {
    MDBBtn,
    MDBModal,
    MDBModalDialog,
    MDBModalContent,
    MDBModalHeader,
    MDBModalTitle,
    MDBModalBody,
    MDBModalFooter,
} from 'mdb-react-ui-kit';
import Button from "./Button";
import recipeService from "@/services/recipeService.js";


function RecipeCardModal({ recipeId, onClose }) {
    const [recipe, setRecipe] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        if (!recipeId) return;

        setLoading(true);
        setError(null);

        recipeService.getById(recipeId)
            .then((response) => {
                setRecipe(response.data);
                setError(null);
            })
            .catch((err) => {
                console.error("Error fetching recipe:", err);
                setError(err.message);
            })
            .finally(() => {
                setLoading(false);
            });
        }, [recipeId]);
    
        if (!recipeId) {
            return null;
        }

    return (
        <>

        <MDBModal open={true} tabIndex='-1' onClose={onClose}>
            <MDBModalDialog>
                <MDBModalContent>
                    <MDBModalHeader>
                        <MDBModalTitle>{
                            loading ? "Loading..." : (error ? "Error" : (recipe?.name ?? "Recipe"))
                            }
                        </MDBModalTitle>
                        <MDBBtn className="btn-close" color='none' onClick={onClose}></MDBBtn>
                    </MDBModalHeader>
                    <MDBModalBody>
                        {loading && <p>Loading recipe...</p>}
                        {error && (
                            <p style={{ color: "red" }}>Could not load recipe: {error}</p>
                        )}

                        {!loading &&!error && recipe && (
                            <div>
                                <p>{recipe.description}</p>
                    <img src={recipe.imageURL} alt={recipe.name + " image"} className='card-img-top mx-auto d-block w-25'/>
                    {recipe.tags && recipe.tags.length > 0 &&
                    <div className="container">
                        <div className="row justify-content-around">
                        <div className="col"></div><div className="col"><div className="row justify-content-around">
                            {recipe.tags.map((tag) => (
                                <div className="col" key={tag.id != null ? tag.id : tag.name} style={{fontWeight: 600}}>
                                &nbsp;{tag.name}&nbsp;
                                </div>
                            ))}
                        </div></div><div className="col"></div>
                        </div>
                    </div>
                    }
                    <h3>Ingredients:</h3>
                    <ul className="list-unstyled">
                        {recipe.recipeIngredients.map((ingredient) => (
                            <li key={ingredient.id ?? ingredient.name}>
                                {ingredient.quantity} {ingredient.unit} {ingredient.ingredient.name}
                            </li>
                        ))}
                    </ul>
                    <h3>Instructions:</h3>
                    <p>{recipe.instructions}</p>
                            </div>
                        )}
                    
                    </MDBModalBody>
                    <MDBModalFooter>
                        <Button label='Close' className="secondaryButton" onClick={onClose} />
                    </MDBModalFooter>
                </MDBModalContent>
            </MDBModalDialog>
        </MDBModal>
        </>
    )
}

export default RecipeCardModal;