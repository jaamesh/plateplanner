import httpClient from "./axiosConfig.js";

/**
 * This is a service class that will handle all HTTP requests to the tag endpoints.
 *
 * To use this service, you will need to import it into your component, and then you can
 * call any of the methods defined below.
 *
 * * `tagService.getAll()` will return a list of all tags.
 * * `tagService.getById(id)` will return the tag with the specified ID.
 * * `tagService.create(tagObject)` will create a new tag.
 * * `tagService.update(id, tagObject)` will update an existing tag.
 * * `tagService.delete(id)` will delete the tag with the specified ID.
 */
export default {
  /**
   * This is the GET service call to retrieve all tags.
   * It will return a 200 response with a list of all tags.
   *
   * It hits the /tag/all endpoint with a GET request.
   *
   * To use it in a component, you would do something like this:
   * @example tagService.getAll().then((response) => console.log(response.data));
   * @returns A list of all Tag objects.
   */
  getAll() {
    return httpClient.get("/tag/all");
  },
  /**
   * This is the GET service call to retrieve a tag by its ID.
   * It will return a 200 response with the tag object.
   * If no tag is found with the specified ID, it will return a 404.
   *
   * It hits the /tag/{id} endpoint with a GET request.
   *
   * To use it in a component, you would do something like this:
   * @example tagService.getById(1).then((response) => console.log(response.data));
   * @param {number} id The ID of the tag to retrieve.
   * @returns The Tag object with the specified ID.
   */
  getById(id) {
    return httpClient.get(`/tag/${id}`);
  },
  /**
   * This is the POST service call to create a new tag.
   * It will return a 204 (no content) response if the tag is created successfully.
   *
   * It hits the /tag/create endpoint with a POST request.
   *
   * @example tagService.create(tagObject).then((response) => console.log(response.status));
   * @param {object} tagObject The Tag object to create.
   */
  create(tagObject) {
    return httpClient.post("/tag/create", tagObject);
  },
  /**
   * This is the POST service call to update an existing tag.
   * It will return a 204 (no content) response if the tag is updated successfully.
   * It will return a 404 if no tag is found with the specified ID.
   *
   * It hits the /tag/update/{id} endpoint with a POST request.
   *
   * @example tagService.update(1, tagObject).then((response) => console.log(response.status));
   * @param {number} id The ID of the tag to update.
   * @param {object} tagObject The Tag object with updated information.
   */
  update(id, tagObject) {
    return httpClient.post(`/tag/update/${id}`, tagObject);
  },
  /**
   * This is the DELETE service call to delete a tag by its ID.
   * It will return a 204 (no content) response if the tag is deleted successfully.
   * It will return a 404 if no tag is found with the specified ID.
   *
   * It hits the /tag/delete/{id} endpoint with a DELETE request.
   *
   * @example tagService.delete(1).then((response) => console.log(response.status));
   * @param {number} id The ID of the tag to delete.
   */
  delete(id) {
    return httpClient.delete(`/tag/delete/${id}`);
  },
};