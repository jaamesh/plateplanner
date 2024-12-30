import httpClient from "./axiosConfig.js";

/**
 * This is a service class that will handle all HTTP requests to the meal-plan endpoints.
 *
 * To use this service, you will need to import it into your component, and then you can
 * call any of the methods defined below.
 *
 * * `mealPlanService.getAll()` will return a list of all meal plans.
 * * `mealPlanService.getById(id)` will return the meal plan with the specified ID.
 * * `mealPlanService.create(mealPlanObject)` will create a new meal plan.
 * * `mealPlanService.update(id, mealPlanObject)` will update an existing meal plan.
 * * `mealPlanService.delete(id)` will delete the meal plan with the specified ID.
 */
export default {
  /**
   * This is the GET service call to retrieve all meal plans.
   * It will return a 200 response with a list of all meal plans.
   *
   * It hits the /meal-plan/all endpoint with a GET request.
   *
   * To use it in a component, you would do something like this:
   * @example mealPlanService.getAll().then((response) => console.log(response.data));
   * @returns A list of all MealPlan objects.
   */
  getAll() {
    return httpClient.get("/meal-plan/all");
  },
  /**
   * This is the GET service call to retrieve a meal plan by its ID.
   * It will return a 200 response with the meal plan object.
   * If no meal plan is found with the specified ID, it will return a 404.
   *
   * It hits the /meal-plan/{id} endpoint with a GET request.
   *
   * To use it in a component, you would do something like this:
   * @example mealPlanService.getById(1).then((response) => console.log(response.data));
   * @param {number} id The ID of the meal plan to retrieve.
   * @returns The MealPlan object with the specified ID.
   */
  getById(id) {
    return httpClient.get(`/meal-plan/${id}`);
  },
  /**
   * This is the POST service call to create a new meal plan.
   * It will return a 204 (no content) response if the meal plan is created successfully.
   *
   * It hits the /meal-plan/create endpoint with a POST request.
   *
   * @example mealPlanService.create(mealPlanObject).then((response) => console.log(response.status));
   * @param {object} mealPlanObject The MealPlan object to create.
   */
  create(mealPlanObject) {
    return httpClient.post("/meal-plan/create", mealPlanObject);
  },
  /**
   * This is the POST service call to update an existing meal plan.
   * It will return a 204 (no content) response if the meal plan is updated successfully.
   * It will return a 404 if no meal plan is found with the specified ID.
   *
   * It hits the /meal-plan/update/{id} endpoint with a POST request.
   *
   * @example mealPlanService.update(1, mealPlanObject).then((response) => console.log(response.status));
   * @param {number} id The ID of the meal plan to update.
   * @param {object} mealPlanObject The MealPlan object to update.
   */
  update(id, mealPlanObject) {
    return httpClient.post(`/meal-plan/update/${id}`, mealPlanObject);
  },
  /**
   * This is the DELETE service call to delete an existing meal plan.
   * It will return a 204 (no content) response if the meal plan is deleted successfully.
   * It will return a 404 if no meal plan is found with the specified ID.
   *
   * It hits the /meal-plan/delete/{id} endpoint with a DELETE request.
   *
   * @example mealPlanService.delete(1).then((response) => console.log(response.status));
   * @param {number} id The ID of the meal plan to delete.
   */
  delete(id) {
    return httpClient.delete(`/meal-plan/delete/${id}`);
  },
}