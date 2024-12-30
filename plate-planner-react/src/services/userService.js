import httpClient from "./axiosConfig.js";

/**
 * This is a service class that will handle all HTTP requests to the user endpoints.
 *
 * To use this service, you will need to import it into your component, and then you can
 * call any of the methods defined below.
 *
 * * `userService.getAll()` will return a list of all users.
 * * `userService.getById(id)` will return the user with the specified ID.
 * * `userService.create(userObject)` will create a new user.
 * * `userService.update(id, userObject)` will update an existing user.
 * * `userService.delete(id)` will delete the user with the specified ID.
 */
export default {
  /**
   * This is the GET service call to retrieve all users.
   * It will return a 200 response with a list of all users.
   *
   * It hits the /user/all endpoint with a GET request.
   *
   * To use it in a component, you would do something like this:
   * @example userService.getAll().then((response) => console.log(response.data));
   * @returns A list of all User objects.
   */
  getAll() {
    return httpClient.get("/user/all");
  },
  /**
   * This is the GET service call to retrieve a user by its ID.
   * It will return a 200 response with the user object.
   * If no user is found with the specified ID, it will return a 404.
   *
   * It hits the /user/{id} endpoint with a GET request.
   *
   * To use it in a component, you would do something like this:
   * @example userService.getById(1).then((response) => console.log(response.data));
   * @param {number} id The ID of the user to retrieve.
   * @returns The User object with the specified ID.
   */
  getById(id) {
    return httpClient.get(`/user/${id}`);
  },
  /**
   * This is the POST service call to create a new user.
   * It will return a 204 (no content) response if the user is created successfully.
   *
   * It hits the /user/create endpoint with a POST request.
   *
   * @example userService.create(userObject).then((response) => console.log(response.status));
   * @param {object} userObject The User object to create.
   */
  create(userObject) {
    return httpClient.post("/user/create", userObject);
  },
  /**
   * This is the POST service call to update an existing user.
   * It will return a 204 (no content) response if the user is updated successfully.
   * It will return a 404 if no user is found with the specified ID.
   *
   * It hits the /user/update/{id} endpoint with a POST request.
   *
   * @example userService.update(1, userObject).then((response) => console.log(response.status));
   * @param {number} id The ID of the user to update.
   * @param {object} userObject The User object with updated values.
   */
  update(id, userObject) {
    return httpClient.post(`/user/update/${id}`, userObject);
  },
  /**
   * This is the DELETE service call to delete a user by its ID.
   * It will return a 204 (no content) response if the user is deleted successfully.
   * It will return a 404 if no user is found with the specified ID.
   *
   * It hits the /user/delete/{id} endpoint with a DELETE request.
   *
   * @example userService.delete(1).then((response) => console.log(response.status));
   * @param {number} id The ID of the user to delete.
   */
  delete(id) {
    return httpClient.delete(`/user/delete/${id}`);
  },
};