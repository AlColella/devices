# Device API

A RESTful API built with Java 21 and Spring Boot 3 to manage device resources.

---

## Features

- Create a new device
- Fully or partially update an existing device
- Retrieve a device by ID
- Retrieve all devices
- Filter devices by brand
- Filter devices by state (`AVAILABLE`, `IN_USE`, `INACTIVE`)
- Delete a device

---

## Domain Validations

- The `creationTime` field cannot be updated
- Devices marked as `IN_USE` cannot have their name or brand updated
- Devices marked as `IN_USE` cannot be deleted

## Technologies

- Java 21
- Spring Boot 3.x
- Spring Data JPA
- MapStruct
- MySQL
- Docker & Docker Compose
- SpringDoc OpenAPI (Swagger UI)
- Lombok

### API Endpoints

Base URL: http://localhost:8080/devices

| Method | Endpoint                                             | Description                       |
| ------ |------------------------------------------------------|-----------------------------------|
| POST   | `/devices`                                           | Create a new device               |
| GET    | `/devices`                                           | Get all devices                   |
| GET    | `/devices/{id}`                                      | Get a device by ID                |
| PUT    | `/devices/{id}`                                      | Fully or Partially update a device|
| DELETE | `/devices/{id}`                                      | Delete a device                   |
| GET    | `/devices/{brand}`                                   | Get devices by brand              |
| GET    | `/devices/{state}`                                   | Get devices by state              |

### License

This project is licensed under the MIT License.
