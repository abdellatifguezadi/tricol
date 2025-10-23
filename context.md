# Tricol Supplier Management System - Project Requirements

## Project Context

Tricol is a company specializing in the design and manufacturing of clothing for professionals. The management wishes to implement an application that will enable efficient management of the company's procurement operations. In its initial version, the application will focus on supplier management, thus laying the foundation for the complete procurement management system that will later include product, order, and inventory management.

This first phase of the project aims to develop a robust supplier management module using Spring Core fundamentals, with an architecture that will allow for future system expansion.

You are required to develop a Java application with basic functionalities for supplier management.

---

## Functional Requirements

### Supplier Management

* **Add a supplier**: Register a supplier with the following information: company name, address, contact person, email, phone, city, ICE (Common Enterprise Identifier).
* **Modify a supplier**: Update the information of an existing supplier.
* **Delete a supplier**: Remove a supplier from the system.
* **View supplier list**: Display all suppliers with sorting options (by name).

---

## Technical Requirements

### Core Technology

* Java with Spring Core
* IoC Container for dependency management
* Spring beans
* Bean scopes
* ApplicationContext and BeanFactory
* Spring configuration using all three approaches (XML, annotations, Java Config)
* Component Scanning
* Spring MVC

### Architecture

* Layered design (Repository, Service, Controller)
* Use of Spring Data JPA repository interfaces
* Implementation of Service and Controller patterns

### Persistence

* Spring Data JPA for the data access layer
* Automatic generation of standard queries (findAll(), findById(), count(), etc.)
* Custom query methods with Query Methods (findByNom(), findByEmailEndingWith(), etc.)

### REST API

Implementation of the following REST endpoints:

**Supplier:**
* `GET /api/v1/fournisseurs`
* `GET /api/v1/fournisseurs/{id}`
* `POST /api/v1/fournisseurs`
* `PUT /api/v1/fournisseurs/{id}`
* `DELETE /api/v1/fournisseurs/{id}`

---
## Performance Criteria

* Correctly structured layered architecture
* Implementation of all three Spring configuration types (XML, annotations, Java)
* Efficient dependency management via Spring IoC
* Functional REST API
* Clean, commented code following best practices