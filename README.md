# STIWK2124-A3-Group_4

## 🛠️ Project Architecture & Technologies
* **Backend:** Java 17, Spring Boot, Spring Data JPA, Hibernate, JUnit 5
* **Frontend:** Angular, TypeScript, Jasmine, Karma
* **Database:** MySQL
* **DevOps & Deployment:** Docker, Docker-Compose, GitHub Actions CI Pipeline

## ⚡ Option 1: Docker Desktop to run
This method automates the initialization of the MySQL Database, Spring Boot Application Layer, and Angular Production Distribution Server using isolated local containers.

### Prerequisites
- Install [Docker Desktop Engine](https://www.docker.com/products/docker-desktop/)

If you have **Docker Desktop** installed, you can launch the complete container network orchestrator stack instantly without manually installing Java, Node.js, or local database engines on your host machine.

### 1. Boot up the Containers
1. Open Docker Desktop and verify that the engine is fully initialized.
2. Open a terminal in the project's root folder (where `docker-compose.yml` is located).
3. Run the following command to build and launch the database, backend, and frontend microservices simultaneously:
   ```bash
   docker-compose up --build -d

### 2. Access the Live Services
* **Frontend Web Interface UI** : http://localhost:80
* **Backend** : http://localhost:8080/api/books

* **Username** : admin
* **Password** : admin123

### Troubleshooting & Database Resets
If you modify data structures, updating scripts like import.sql, or need to completely wipe the local database clean to test fresh initialization, use the volume-clear flag to shut down:
   ```bash
   docker-compose down -v
```

### Shut Down Containers
To clean up your workspace and stop all running containers safely, run:
   ```bash
   docker-compose down
```

## ⚡ Option 2: Manually run
This requires an installed local MySQL database server, Java JDK 17, and Node.js.

### 1. Local Database Setup
1. Open your local MySQL database engine (e.g., MySQL Workbench, Command Line Client, or XAMPP).
2. Execute the query to build the application database schema:
   ```bash
   CREATE DATABASE IF NOT EXISTS arl_db;
   ```
3. Open src/main/resources/application.properties and verify that the spring.datasource.username and spring.datasource.password fields match your local database instance credentials.

### 2. Boot up the Spring Boot Backend (Terminal 1)
Open the terminal and run:
   ```bash
   # For Windows PowerShell / Command Prompt
   mvnw.cmd spring-boot:run

   # For macOS / Linux Terminal
   ./mvnw spring-boot:run
   ```
Leave this terminal window open. The backend will stay active and listen for incoming HTTP API requests on port 8080 (Configured with CORS clearance for origins http://localhost and http://localhost:4200).

### 3. Boot up the Angular Frontend (Terminal 2)
Open a brand new, separate terminal window and run:
   ```bash
   cd arl-frontend
   npm install
   npm start
   ```
Once the terminal logs Compiled successfully, open your web browser and go to http://localhost:4200 to interact with the web interface.
