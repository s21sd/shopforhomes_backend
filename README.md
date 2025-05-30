# ShopForHome - E-Commerce Backend

## Overview
ShopForHome is an e-commerce application developed using **Spring Boot** and **SQL Database** for backend services. This backend manages user authentication, product management, order processing, and other core functionalities of an e-commerce platform.

## Features
- **User Authentication**: Secure login and registration using Firebase authentication.
- **Product Management**: Add, update, delete, and fetch product details.
- **Order Management**: Place orders, track status, and manage user orders.
- **Cart Functionality**: Add, remove, and update items in the cart.
- **Payment Integration**: (Future enhancement) Support for online payments.
- **Admin Dashboard**: Role-based access for managing products and orders.

## Tech Stack
- **Backend**: Spring Boot
- **Database**: SQL
- **Authentication**: Firebase Authentication
- **Deployment**: (Specify if deployed on Railways)

## Setup & Installation
### Prerequisites
- Java 17+
- Spring Boot
- MySQL
- Maven

### Steps to Run Locally
1. Clone the repository:
   ```sh
   git clone https://github.com/s21sd/shopforhomes_backend.git
   ```
2. Navigate to the project directory:
   ```sh
   cd shopforhomes_backend
   ```
3. Configure the database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/shopforhome
   spring.datasource.username=your_db_username
   spring.datasource.password=your_db_password
   spring.jpa.hibernate.ddl-auto=update
   ```
4. Install dependencies and build the project:
   ```sh
   mvn clean install
   ```
5. Run the application:
   ```sh
   mvn spring-boot:run
   ```
6. Access the API at `http://localhost:8080`

## API Endpoints
| Method | Endpoint           | Description                  |
|--------|-------------------|------------------------------|
| GET    | /api/products      | Get all products            |
| GET    | /api/products/{id} | Get product by ID           |
| POST   | /api/products      | Add a new product           |
| PUT    | /api/products/{id} | Update a product            |
| DELETE | /api/products/{id} | Delete a product            |
| POST   | /api/orders        | Place an order              |
| GET    | /api/orders/{id}   | Get order details           |
| POST   | /api/auth/signup   | User registration           |
| POST   | /api/auth/login    | User login                  |

## Contributors
- **Team Member 1** (Sunny Srivastava)
- **Team Member 2** (Akarsh Jaiswal)
- **Team Member 3** (Saurav Mishra)
- **Team Member 4** (Anjani)
- **Team Member 5** (Kushagra Sharma)


