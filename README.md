# Access Management

This project demonstrates building an access management system that controls user actions based on their permission levels. It uses RabbitMQ to send transaction (change) information to a queue, with persistence of this information in a MongoDB database. Authentication is implemented to verify the user's permission level before allowing specific actions.

## Technologies Used

*   **Java 11:** Main programming language.
*   **Spring Boot 2.7.18:** Framework for rapid Java application development.
*   **RabbitMQ:** Message broker for asynchronous communication.
*   **MongoDB:** NoSQL database for data persistence.
*   **Mockito:** Framework for unit testing.
*   **Authentication:** Mechanism for verifying user permissions.

## Key Features

*   **Access Control:** Restricts actions based on user permission levels.
*   **RabbitMQ Messaging:** Asynchronous sending of transaction information to a queue.
*   **MongoDB Persistence:** Storage of transaction information in the database.
*   **Authentication:** Validation of user credentials and permission verification.
*   **Unit Testing:** Test coverage with Mockito to ensure code quality.
