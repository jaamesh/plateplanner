import httpClient from "./axiosConfig.js";

/**
 * This is a service class that will handle all HTTP requests to the shopping-list-item endpoints.
 *
 * To use this service, you will need to import it into your component, and then you can
 * call any of the methods defined below.
 *
 * * `shoppingListItemService.getAll()` will return a list of all shopping-list-items.
 * * `shoppingListItemService.getById(shoppingListItemId)` will return the shopping-list-item with the specified ID.
 * * `shoppingListItemService.create(shoppingListItemObject)` will create a new shopping-list-item.
 * * `shoppingListItemService.update(shoppingListItemId, shoppingListItemObject)` will update an existing shopping-list-item.
 * * `shoppingListItemService.delete(shoppingListItemId)` will delete the shopping-list-item with the specified ID.
 */
export default {
  /**
   * This is the GET service call to retrieve all shopping-list-items.
   * It will return a 200 response with a list of all shopping-list-items.
   *
   * It hits the /shopping-list-item/all endpoint with a GET request.
   *
   * To use it in a component, you would do something like this:
   * @example shoppingListItemService.getAll().then((response) => console.log(response.data));
   * @returns A list of all ShoppingListItem objects.
   */
  getAll() {
    return httpClient.get("/shopping-list-item/all");
  },
  /**
   * This is the GET service call to retrieve a shopping-list-item by its ID.
   * It will return a 200 response with the shopping-list-item object.
   * If no shopping-list-item is found with the specified ID, it will return a 404.
   *
   * It hits the /shopping-list-item/{shoppingListItemId} endpoint with a GET request.
   *
   * To use it in a component, you would do something like this:
   * @example shoppingListItemService.getById(1).then((response) => console.log(response.data));
   * @param {number} shoppingListItemId The ID of the shopping-list-item to retrieve.
   * @returns The ShoppingListItem object with the specified ID.
   */
  getById(shoppingListItemId) {
    return httpClient.get(`/shopping-list-item/${shoppingListItemId}`);
  },
  /**
   * This is the POST service call to create a new shopping-list-item.
   * It will return a 204 (no content) response if the shopping-list-item is created successfully.
   *
   * It hits the /shopping-list-item/create endpoint with a POST request.
   *
   * @example shoppingListItemService.create(shoppingListItemObject).then((response) => console.log(response.status));
   * @param {object} shoppingListItemObject The ShoppingListItem object to create.
   */
  create(shoppingListItemObject) {
    return httpClient.post("/shopping-list-item/create", shoppingListItemObject);
  },
  /**
   * This is the POST service call to update an existing shopping-list-item.
   * It will return a 204 (no content) response if the shopping-list-item is updated successfully.
   * It will return a 404 if no shopping-list-item is found with the specified ID.
   *
   * It hits the /shopping-list-item/update/shoppingListItemId endpoint with a POST request.
   *
   * @example shoppingListItemService.update(1, shoppingListItemObject).then((response) => console.log(response.status));
   * @param {number} shoppingListItemId The ID of the shopping-list-item to update.
   * @param {object} shoppingListItemObject The ShoppingListItem object with updated information.
   */
  update(shoppingListItemId, shoppingListItemObject) {
    return httpClient.post(`/shopping-list-item/update/${shoppingListItemId}`, shoppingListItemObject);
  },
  /**
   * This is the DELETE service call to delete a shopping-list-item by its ID.
   * It will return a 204 (no content) response if the shopping-list-item is deleted successfully.
   * It will return a 404 if no shopping-list-item is found with the specified ID.
   *
   * It hits the /shopping-list-item/delete/{shoppingListItemId} endpoint with a DELETE request.
   *
   * @example shoppingListItemService.delete(1).then((response) => console.log(response.status));
   * @param {number} shoppingListItemId The ID of the shopping-list-item to delete.
   */
  delete(shoppingListItemId) {
    return httpClient.delete(`/shopping-list-item/delete/${shoppingListItemId}`);
  },
};