import mealPlanService from "@/services/mealPlanService.js";
import { useState, useEffect } from "react";
import Button from "./Button";
import { useNavigate } from "react-router-dom";
import trashcanIcon from "../assets/trash.svg";
import RecipeCardModal from "./RecipeCardModal";

const MealPlanUI = () => {
  const daysOfTheWeekData = [
    { name: "Sunday", recipes: [] },
    { name: "Monday", recipes: [] },
    { name: "Tuesday", recipes: [] },
    { name: "Wednesday", recipes: [] },
    { name: "Thursday", recipes: [] },
    { name: "Friday", recipes: [] },
    { name: "Saturday", recipes: [] },
  ];

  const [daysOfTheWeek, setDaysOfTheWeek] = useState(daysOfTheWeekData);
  const navigate = useNavigate();
  const [showModal, setShowModal] = useState(false);
  const [selectedRecipeId, setSelectedRecipeId] = useState(null);

  useEffect(() => {
    mealPlanService
      .getUserMealPlan()
      .then((response) => {
        console.log("API Response:", response.data);
        return response.data;
      })
      .then((data) => {
        if (data && data.mealPlanRecipes) {
          const updatedDays = daysOfTheWeekData.map((day) => ({
            ...day,
            recipes: [],
          }));

          data.mealPlanRecipes.forEach((mpr) => {
            const matchingDay = updatedDays.find(
              (d) => d.name.toUpperCase() === mpr.dayOfTheWeek.toUpperCase()
            );

            if (matchingDay) {
              matchingDay.recipes.push({
                mealPlanRecipeId: mpr.id,
                ...mpr.recipe,
              });
            }
          });

          setDaysOfTheWeek(updatedDays);
        }
      })
      .catch((error) => {
        console.error("Error fetching meal plan:", error);
      });
  }, []);

  const handleAddRecipeClick = () => {
    navigate("/saved-recipes");
  };

  const removeRecipe = (mealPlanRecipeId) => {
    console.log("Removing recipe with MealPlanRecipeID: " + mealPlanRecipeId);
    mealPlanService
      .deleteMealPlanRecipe(mealPlanRecipeId)
      .then(() => {
        setDaysOfTheWeek((prevDays) => {
          return prevDays.map((day) => {
            return {
              ...day,
              recipes: day.recipes.filter(
                (r) => r.mealPlanRecipeId !== mealPlanRecipeId
              ),
            };
          });
        });
      })
      .catch((error) => {
        console.error("Could not remove recipe from the mealplan.", error);
      });
  };

  return (
    <div>
      <h2 className="mealplan-header">Meal Plan</h2>
      <div className="meal-plan-ui-container border rounded component">
        <div className="days-container">
          {daysOfTheWeek.map((day) => (
            <div key={day.name} className="card m-4 day-card">
              <h2 className="text-start">{day.name}</h2>
              {day.recipes.map((recipeObj) => (
                <div key={recipeObj.mealPlanRecipeId} className="recipe-item">
                  <span
                    onClick={() => {
                      setSelectedRecipeId(recipeObj.id);
                      setShowModal(true);
                    }}
                    style={{ cursor: "pointer", textDecoration: "underline" }}
                  >
                    {recipeObj.name}
                  </span>
                  <img
                    src={trashcanIcon}
                    alt="Delete Item"
                    onClick={() => removeRecipe(recipeObj.mealPlanRecipeId)}
                    style={{
                      width: "15px",
                      height: "15px",
                      cursor: "pointer",
                    }}
                  />
                </div>
              ))}
            </div>
          ))}
          <div className="add-recipe-card">
            <Button label="Add Recipe" onClick={() => handleAddRecipeClick()} />
          </div>
        </div>
        <hr />
      </div>
      {showModal && selectedRecipeId && (
        <RecipeCardModal
          recipeId={selectedRecipeId}
          onClose={() => setShowModal(false)}
        />
      )}
      ;
    </div>
  );
};

export default MealPlanUI;
