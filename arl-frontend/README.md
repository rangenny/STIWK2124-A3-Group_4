# 📚 Book Catalog Application (Group 4)

This is a Full-Stack Web Application built for STIWK2124 Assignment 2. It features a Spring Boot REST API backend connected to a MySQL database, and an Angular frontend UI.

## ✨ Features
* **Full CRUD Operations:** Add, view, edit, and delete books from the catalog.
* **Search & Pagination:** Easily filter books by title/author and navigate through pages.
* **Form Validation:** Strict frontend validation to ensure clean data entry.
* **Accessibility (Web Speech API):** Includes a "Read Aloud" feature that reads the book details to the user.

---

## 🚀 How to Run the Application

To run this project locally, you will need to start both the Spring Boot backend and the Angular frontend.

### 1. Database Setup
1. Ensure your local **MySQL** server is running (via XAMPP, MySQL Workbench, etc.).
2. The database password in `src/main/resources/application.properties` must match your local MySQL root password.

### 2. Start the Backend (Spring Boot)
1. Open a terminal in the root folder (`STIWK2124-A2-GROUP_4`).
2. Run the following command to start the server on Port 8080:
   ```bash
   .\mvnw spring-boot:run
3. Wait until the terminal says the application has started

### 3. Start the Frontend (Angular)
1. Open a second terminal window
2. Navigate into the frontend directory:
    cd arl-frontend
3. If running for first time, install dependencies:
    npm install
4. Start the Angular development server:
    ng serve

### 4. View the App
1. Once both servers are running, open your web browser and navigate to:
http://localhost:4200
2. Congratulations! You are now on the app! Have fun and interact with the buttons!