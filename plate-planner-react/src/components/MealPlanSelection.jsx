import { useEffect, useState } from "react";
import mealPlanService from "../services/mealPlanService";

function MealPlanSelection({ onMealPlanChange, selectedMealPlan }) {
    const [mealPlans, setMealPlans] = useState([]);

    useEffect(() => {
        //TODO: Need to pull the user ID from the session and retrieve meal plans for the specific user
        mealPlanService.getAll()
        .then((response) => {
            console.log(response.data)
            setMealPlans(response.data);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    }, []);

    const handleSelection = (event) => {
        const planId = event.target.value;
        onMealPlanChange(planId);
    };

    return (
        <div>
            <label htmlFor="mealPlans">Select a Meal Plan:</label>
            <select 
            className="form-select"
            name="mealPlan" 
            id="mealPlans" 
            value={selectedMealPlan} 
            onChange={handleSelection}
            >
                <option value="">My Meal Plans</option>
                {mealPlans.map((plan) => (
                    <option key={plan.id} value={plan.id}>
                        {plan.name}
                    </option>
                ))}
            </select>
        </div>
    )
}

export default MealPlanSelection;