import httpClient from "./axiosConfig.js";

/**
 * This is a service class that will handle all HTTP requests to the recipe endpoints.
 *
 * To use this service, you will need to import it into your component, and then you can
 * call any of the methods defined below.
 *
 * * `recipeService.getAll()` will return a list of all recipes.
 * * `recipeService.getById(id)` will return the recipe with the specified ID.
 * * `recipeService.create(recipeObject)` will create a new recipe.
 * * `recipeService.update(id, recipeObject)` will update an existing recipe.
 * * `recipeService.delete(id)` will delete the recipe with the specified ID.
 */
export default {
  /**
   * This is the GET service call to retrieve all recipes.
   * It will return a 200 response with a list of all recipes.
   *
   * It hits the /recipe/all endpoint with a GET request.
   *
   * To use it in a component, you would do something like this:
   * @example recipeService.getAll().then((response) => console.log(response.data));
   * @returns A list of all Recipe objects.
   */
  getAll() {
    return httpClient.get("/recipe/all");
  },
  /**
   * This is the GET service call to retrieve a recipe by its ID.
   * It will return a 200 response with the recipe object.
   * If no recipe is found with the specified ID, it will return a 404.
   *
   * It hits the /recipe/{id} endpoint with a GET request.
   *
   * To use it in a component, you would do something like this:
   * @example recipeService.getById(1).then((response) => console.log(response.data));
   * @param {number} id The ID of the recipe to retrieve.
   * @returns The Recipe object with the specified ID.
   */
  getById(id) {
    return httpClient.get(`/recipe/${id}`);
  },
  /**
   * This is the POST service call to create a new recipe.
   * It will return a 204 (no content) response if the recipe is created successfully.
   *
   * It hits the /recipe/create endpoint with a POST request.
   *
   * @example recipeService.create(recipeObject).then((response) => console.log(response.status));
   * @param {object} recipeObject The Recipe object to create.
   */
  create(recipeObject) {
    return httpClient.post("/recipe/create", recipeObject);
  },
  /**
   * This is the POST service call to update an existing recipe.
   * It will return a 204 (no content) response if the recipe is updated successfully.
   * It will return a 404 if no recipe is found with the specified ID.
   *
   * It hits the /recipe/update/{id} endpoint with a POST request.
   *
   * @example recipeService.update(1, recipeObject).then((response) => console.log(response.status));
   * @param {number} id The ID of the recipe to update.
   * @param {object} recipeObject The Recipe object with the updated values.
   */
  update(id, recipeObject) {
    return httpClient.post(`/recipe/update/${id}`, recipeObject);
  },
  /**
   * This is the DELETE service call to delete a recipe by its ID.
   * It will return a 204 (no content) response if the recipe is deleted successfully.
   * It will return a 404 if no recipe is found with the specified ID.
   *
   * It hits the /recipe/delete/{id} endpoint with a DELETE request.
   *
   * @example recipeService.delete(1).then((response) => console.log(response.status));
   * @param {number} id The ID of the recipe to delete.
   */
  delete(id) {
    return httpClient.delete(`/recipe/delete/${id}`);
  },
}