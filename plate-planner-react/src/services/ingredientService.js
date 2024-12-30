import httpClient from "./axiosConfig.js";

/**
 * This is a service class that will handle all HTTP requests to the ingredient endpoints.
 *
 * To use this service, you will need to import it into your component, and then you can
 * call any of the methods defined below.
 *
 * * `ingredientService.getAll()` will return a list of all ingredients.
 * * `ingredientService.getById(id)` will return the ingredient with the specified ID.
 * * `ingredientService.create(ingredientObject)` will create a new ingredient.
 * * `ingredientService.update(id, ingredientObject)` will update an existing ingredient.
 * * `ingredientService.delete(id)` will delete the ingredient with the specified ID.
 */
export default {
  /**
   * This is the GET service call to retrieve all ingredients.
   * It will return a 200 response with a list of all ingredients.
   *
   * It hits the /ingredient/all endpoint with a GET request.
   *
   * To use it in a component, you would do something like this:
   * @example ingredientService.getAll().then((response) => console.log(response.data));
   * @returns A list of all Ingredient objects.
   */
  getAll() {
    return httpClient.get("/ingredient/all");
  },
  /**
   * This is the GET service call to retrieve an ingredient by its ID.
   * It will return a 200 response with the ingredient object.
   * If no ingredient is found with the specified ID, it will return a 404.
   *
   * It hits the /ingredient/{id} endpoint with a GET request.
   *
   * To use it in a component, you would do something like this:
   * @example ingredientService.getById(1).then((response) => console.log(response.data));
   * @param {number} id The ID of the ingredient to retrieve.
   * @returns The Ingredient object with the specified ID.
   */
  getById(id) {
    return httpClient.get(`/ingredient/${id}`);
  },
  /**
   * This is the POST service call to create a new ingredient.
   * It will return a 204 (no content) response if the ingredient is created successfully.
   *
   * It hits the /ingredient/create endpoint with a POST request.
   *
   * @example ingredientService.create(ingredientObject).then((response) => console.log(response.status));
   * @param {object} ingredientObject The Ingredient object to create.
   */
  create(ingredientObject) {
    return httpClient.post("/ingredient/create", ingredientObject);
  },
  /**
   * This is the POST service call to update an existing ingredient.
   * It will return a 204 (no content) response if the ingredient is updated successfully.
   * It will return a 404 if no ingredient is found with the specified ID.
   *
   * It hits the /ingredient/update/{id} endpoint with a POST request.
   *
   * @example ingredientService.update(1, ingredientObject).then((response) => console.log(response.status));
   * @param {number} id The ID of the ingredient to update.
   * @param {object} ingredientObject The Ingredient object to update.
   */
  update(id, ingredientObject) {
    return httpClient.post(`/ingredient/update/${id}`, ingredientObject);
  },
  /**
   * This is the DELETE service call to delete an existing ingredient.
   * It will return a 204 (no content) response if the ingredient is deleted successfully.
   * It will return a 404 if no ingredient is found with the specified ID.
   *
   * It hits the /ingredient/delete/{id} endpoint with a DELETE request.
   *
   * @example ingredientService.delete(1).then((response) => console.log(response.status));
   * @param {number} id The ID of the ingredient to delete.
   */
  delete(id) {
    return httpClient.delete(`/ingredient/delete/${id}`);
  },
}