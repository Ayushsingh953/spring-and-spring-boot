# Session vs Request Scopes

* All requests from browser are handled by our web application deployed on a server
* **Request Scope**: Active for a single request only
  * Once the response is sent back, the request attributes will be removed from memory
  * These cannot be used for future requests
  * Recommended for most use cases
* **Session Scope**: Details stored across multiple requests
  * Be careful about what you store in session (Takes additional memory as all details are stored on server)