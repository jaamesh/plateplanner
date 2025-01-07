import React, { useEffect, useState } from "react";
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
import MealPlanSelection from "./MealPlanSelection";
import mealPlanService from "../services/mealPlanService";

function AddRecipeToMealPlan({ recipeId }) {
    const [basicModal, setBasicModal] = useState(false);
    const [error, setError] = useState(null);
    const toggleOpen = () => setBasicModal(!basicModal);
    const [selectedMealPlan, setSelectedMealPlan] = useState("");

    const handleMealPlanChange = (planId) => {
        setSelectedMealPlan(planId);
    }

    const addToMealPlan = async () => {
        if (!selectedMealPlan) {
            setError("Please select a meal plan");
            return;
        }
        setError(null);

        try {
            // TODO: Call the service method that updates the selected meal plan with the recipe
            const response = await mealPlanService.addRecipeToMealPlan(selectedMealPlan, recipeId);

            if (response.status !== 200 && response.status !== 201 && response.status !== 204) {
                throw new Error("Could not add recipe to meal plan");
            }
            console.log("success: ", response.data);
            setBasicModal(false);
            setSelectedMealPlan("");
            alert("Recipe added successfully!");
        } catch (error) {
            console.error("Error adding recipe:", error);
            setError(error.message);
        }
    };

    return (
        <>
        <Button label="Add Recipe to Meal Plan" onClick={toggleOpen} />

        <MDBModal open={basicModal} onClose={() => setBasicModal(false)} tabIndex='-1'>
            <MDBModalDialog>
                <MDBModalContent>
                    <MDBModalHeader>
                        <MDBModalTitle>Add Recipe to a Meal Plan</MDBModalTitle>
                        <MDBBtn className="btn-close" color='none' onClick={toggleOpen}></MDBBtn>
                    </MDBModalHeader>
                    <MDBModalBody>
                        <MealPlanSelection selectedMealPlan={selectedMealPlan} onMealPlanChange={handleMealPlanChange}/>
                        <hr/>
                        <p>Or Create a New Meal Plan:</p>
                        <p>And a form that allows the user to create a new meal plan.</p>
                        {error && <p style={{ color: 'red' }}>Error: {error}</p>}
                    </MDBModalBody>
                    <MDBModalFooter>
                        <Button label='Close' className="secondaryButton" onClick={toggleOpen} />
                        <Button label="Add to Meal Plan" onClick={addToMealPlan}></Button>
                    </MDBModalFooter>
                </MDBModalContent>
            </MDBModalDialog>
        </MDBModal>
        </>
    )
}

export default AddRecipeToMealPlan;