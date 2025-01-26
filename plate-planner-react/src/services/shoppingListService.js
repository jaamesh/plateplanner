import httpClient from "./axiosConfig.js";

/**
 * This is a service class that will handle all HTTP requests to the shopping-list endpoints.
 *
 * To use this service, you will need to import it into your component, and then you can
 * call any of the methods defined below.
 *
 * * `shoppingListService.getAll()` will return a list of all shopping-lists.
 * * `shoppingListService.getById(shoppingListId` will return the shopping-list with the specified ID.
 * * `shoppingListService.create(shoppingListObject)` will create a new shopping-list.
 * * `shoppingListService.update(shoppingListId, shoppingListObject)` will update an existing shopping-list.
 * * `shoppingListService.delete(shoppingListId)` will delete the shopping-list with the specified ID.
 */
export default {
  /**
   * This is the GET service call to retrieve all shopping-lists.
   * It will return a 200 response with a list of all shopping-lists.
   *
   * It hits the /shopping-list/all endpoint with a GET request.
   *
   * To use it in a component, you would do something like this:
   * @example shoppingListService.getAll().then((response) => console.log(response.data));
   * @returns A list of all ShoppingList objects.
   */
  getAll() {
    return httpClient.get("/shopping-list/all");
  },
  /**
   * This is the GET service call to retrieve a shopping-list by its ID.
   * It will return a 200 response with the shopping-list object.
   * If no shopping-list is found with the specified ID, it will return a 404.
   *
   * It hits the /shopping-list/{shoppingListId} endpoint with a GET request.
   *
   * To use it in a component, you would do something like this:
   * @example shoppingListService.getById(1).then((response) => console.log(response.data));
   * @param {number} shoppingListId The ID of the shopping-list to retrieve.
   * @returns The ShoppingList object with the specified ID.
   */
  getById(shoppingListId) {
    return httpClient.get(`/shopping-list/${shoppingListId}`);
  },
  /**
   * This is the POST service call to create a new shopping-list.
   * It will return a 204 (no content) response if the shopping-list is created successfully.
   *
   * It hits the /shopping-list/create endpoint with a POST request.
   *
   * @example shoppingListService.create(shoppingListObject).then((response) => console.log(response.status));
   * @param {object} shoppingListObject The ShoppingList object to create.
   */
  create(shoppingListObject) {
    return httpClient.post("/shopping-list/create", shoppingListObject);
  },
  /**
   * This is the POST service call to update an existing shopping-list.
   * It will return a 204 (no content) response if the shopping-list is updated successfully.
   * It will return a 404 if no shopping-list is found with the specified ID.
   *
   * It hits the /shopping-list/update/{shoppingListId} endpoint with a POST request.
   *
   * @example shoppingListService.update(1, shoppingListObject).then((response) => console.log(response.status));
   * @param {number} shoppingListId The ID of the shopping-list to update.
   * @param {object} shoppingListObject The ShoppingList object to update.
   */
  update(shoppingListId, shoppingListObject) {
    return httpClient.post(
      `/shopping-list/update/${shoppingListId}`,
      shoppingListObject
    );
  },
  /**
   * This is the DELETE service call to delete a shopping-list by its ID.
   * It will return a 204 (no content) response if the shopping-list is deleted successfully.
   * It will return a 404 if no shopping-list is found with the specified ID.
   *
   * It hits the /shopping-list/delete/{shoppingListId} endpoint with a DELETE request.
   *
   * @example shoppingListService.delete(1).then((response) => console.log(response.status));
   * @param {number} shoppingListId The ID of the shopping-list to delete.
   */
  delete(shoppingListId) {
    return httpClient.delete(`/shopping-list/delete/${shoppingListId}`);
  },

  //GET request that will fetch the shopping list

  getShoppingList() {
    return httpClient.get("/shopping-list");
  },

  //GET request that will create a new shopping list if none exists and populate it with the contents from the meal plan,
  //or if there is a shopping list, it will update it to match the contents of the current meal plan.

  createOrUpdateShoppingList() {
    return httpClient.get("shopping-list/createOrUpdate");
  },
};
