import axios from "axios";
import { useEffect, useState } from "react"
import mealPlanService from "@/services/mealPlanService.js";

const MealPlanUI = () => {
    const [mealPlans, setMealPlans] = useState([]);
    const [selectedMealPlan, setSelectedMealPlan] = useState();
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState(null);

    useEffect(() => {
        mealPlanService.getAll()
            .then((response) => {
                setMealPlans(response.data);
                setLoading(false);
            })
            .catch((err) => {
                setError(err.message);
                setLoading(false);
            });
    }, []);

    return
    <div>
        <h2 className="mealplan-header">Meal Plans</h2>
        <label for="mealPlans">Select a Meal Plan:</label>
        <select name="mealPlans" id="mealPlans">
            {mealPlans.map((mealPlan) => (
            <div key={mealplan.id} className="dropdown"></div>
            )}
        </select>
    </div>
}

export default MealPlanUI
