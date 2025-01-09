import httpClient from "./axiosConfig.js";

/**
 * This is a service class that will handle all HTTP requests to the recipe-ingredient endpoints.
 *
 * To use this service, you will need to import it into your component, and then you can
 * call any of the methods defined below.
 *
 * * `recipeIngredientService.getAll()` will return a list of all recipe-ingredients.
 * * `recipeIngredientService.getById(recipeIngredientId)` will return the recipe-ingredient with the specified ID.
 * * `recipeIngredientService.create(recipeIngredientObject)` will create a new recipe-ingredient.
 * * `recipeIngredientService.update(recipeIngredientId, recipeIngredientObject)` will update an existing recipe-ingredient.
 * * `recipeIngredientService.delete(recipeIngredientId)` will delete the recipe-ingredient with the specified ID.
 */
export default {
  /**
   * This is the GET service call to retrieve all recipe-ingredients.
   * It will return a 200 response with a list of all recipe-ingredients.
   *
   * It hits the /recipe-ingredient/all endpoint with a GET request.
   *
   * To use it in a component, you would do something like this:
   * @example recipeIngredientService.getAll().then((response) => console.log(response.data));
   * @returns A list of all RecipeIngredient objects.
   */
  getAll() {
    return httpClient.get("/recipe-ingredient/all");
  },
  /**
   * This is the GET service call to retrieve a recipe-ingredient by its ID.
   * It will return a 200 response with the recipe-ingredient object.
   * If no recipe-ingredient is found with the specified ID, it will return a 404.
   *
   * It hits the /recipe-ingredient/{recipeIngredientId} endpoint with a GET request.
   *
   * To use it in a component, you would do something like this:
   * @example recipeIngredientService.getById(1).then((response) => console.log(response.data));
   * @param {number} recipeIngredientId The ID of the recipe-ingredient to retrieve.
   * @returns The RecipeIngredient object with the specified ID.
   */
  getById(recipeIngredientId) {
    return httpClient.get(`/recipe-ingredient/${recipeIngredientId}`);
  },
  /**
   * This is the POST service call to create a new recipe-ingredient.
   * It will return a 204 (no content) response if the recipe-ingredient is created successfully.
   *
   * It hits the /recipe-ingredient/create endpoint with a POST request.
   *
   * @example recipeIngredientService.create(recipeIngredientObject).then((response) => console.log(response.status));
   * @param {object} recipeIngredientObject The RecipeIngredient object to create.
   */
  create(recipeIngredientObject) {
    return httpClient.post("/recipe-ingredient/create", recipeIngredientObject);
  },
  /**
   * This is the POST service call to update an existing recipe-ingredient.
   * It will return a 204 (no content) response if the recipe-ingredient is updated successfully.
   * It will return a 404 if no recipe-ingredient is found with the specified ID.
   *
   * It hits the /recipe-ingredient/update/{recipeIngredientId} endpoint with a POST request.
   *
   * @example recipeIngredientService.update(1, recipeIngredientObject).then((response) => console.log(response.status));
   * @param {number} recipeIngredientId The ID of the recipe-ingredient to update.
   * @param {object} recipeIngredientObject The RecipeIngredient object with the updated information.
   */
  update(recipeIngredientId, recipeIngredientObject) {
    return httpClient.post(`/recipe-ingredient/update/${recipeIngredientId}`, recipeIngredientObject);
  },
  /**
   * This is the DELETE service call to delete a recipe-ingredient by its ID.
   * It will return a 204 (no content) response if the recipe-ingredient is deleted successfully.
   * It will return a 404 if no recipe-ingredient is found with the specified ID.
   *
   * It hits the /recipe-ingredient/delete/{recipeIngredientId} endpoint with a DELETE request.
   *
   * @example recipeIngredientService.delete(1).then((response) => console.log(response.status));
   * @param {number} recipeIngredientId The ID of the recipe-ingredient to delete.
   */
  delete(recipeIngredientId) {
    return httpClient.delete(`/recipe-ingredient/delete/${recipeIngredientId}`);
  },
}