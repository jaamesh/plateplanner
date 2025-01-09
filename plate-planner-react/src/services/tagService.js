import httpClient from "./axiosConfig.js";

/**
 * This is a service class that will handle all HTTP requests to the tag endpoints.
 *
 * To use this service, you will need to import it into your component, and then you can
 * call any of the methods defined below.
 *
 * * `tagService.getAll()` will return a list of all tags.
 * * `tagService.getById(tagId)` will return the tag with the specified ID.
 * * `tagService.create(tagObject)` will create a new tag.
 * * `tagService.update(tagId, tagObject)` will update an existing tag.
 * * `tagService.delete(tagId)` will delete the tag with the specified ID.
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
   * It hits the /tag/{tagId} endpoint with a GET request.
   *
   * To use it in a component, you would do something like this:
   * @example tagService.getById(1).then((response) => console.log(response.data));
   * @param {number} tagId The ID of the tag to retrieve.
   * @returns The Tag object with the specified ID.
   */
  getById(tagId) {
    return httpClient.get(`/tag/${tagId}`);
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
   * It hits the /tag/update/{tagId} endpoint with a POST request.
   *
   * @example tagService.update(1, tagObject).then((response) => console.log(response.status));
   * @param {number} tagId The ID of the tag to update.
   * @param {object} tagObject The Tag object with updated information.
   */
  update(tagId, tagObject) {
    return httpClient.post(`/tag/update/${tagId}`, tagObject);
  },
  /**
   * This is the DELETE service call to delete a tag by its ID.
   * It will return a 204 (no content) response if the tag is deleted successfully.
   * It will return a 404 if no tag is found with the specified ID.
   *
   * It hits the /tag/delete/{tagId} endpoint with a DELETE request.
   *
   * @example tagService.delete(1).then((response) => console.log(response.status));
   * @param {number} tagId The ID of the tag to delete.
   */
  delete(tagId) {
    return httpClient.delete(`/tag/delete/${tagId}`);
  },
};