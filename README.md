# STIWK2124-A3-Group_4

## 🚀 Quick Start (Docker Deployment)
*If you have Docker Desktop installed, this is the fastest way to run and grade the application.*

1. **Clone the repository and enter the project root directory:**
   git clone [GitHub Repository URL]
   cd [Project-Directory-Name]
   
**2. Compile the runtime environment images and build the network containers:**
   docker compose up --build

**To access the application:**
   Frontend : http://localost:4200
   Backend : http://localhost:8080


## For Manual Local Deployment Setup - without Docker
Environment Prerequisites
  Node.js: v20.x or higher
  Java/JDK: v17 or v21 (if using Spring Boot)
  Database: MySQL v8.0 or higher

**Step 1: Database Setup**
  Open your local MySQL client and create a new database:
    CREATE DATABASE arl_db;

**Step 2: Backend Application Layer Execution (Spring Boot)**
  1. Initialize a new terminal interface and change your directory focus to the backend module root:
        cd backend

  2. At application.properties, adjust the target data source credentials to match your local database service setup:
        spring.datasource.url=jdbc:mysql://localhost:3306/arl_db
        spring.datasource.username=your_mysql_username
        spring.datasource.password=your_mysql_password

  3. Execute the standard Maven wrapper build tool binary to start the REST API service:
         ./mvnw spring-boot:run

**Step 3: Frontend Client Presentation Execution (Angular & Tailwind CSS)**
  1. Open a separate, simultaneous terminal interface panel and change your directory path into the frontend bundle folder:
         cd arl-frontend

  2. Confirm your environment target API gateway pointer is mapped to point directly at your running backend application instances (src/environments/environment.development.ts):
         export const environment = {
            production: false,
            apiUrl: 'http://localhost:8080'
          };

  3. Request the package manager to download, authenticate, and structurally link all the local dependencies declared in the project's node engine ecosystem:
         npm install

  4. Fire the local execution script tool to trigger the development asset compiler and run the Angular web server:
         npm start

**##Production Artifact Verification Procedures**
1. Frontend Client-Side Target Compilation:
         cd arl-frontend
         npm run build
2. Backend Microservice Jar Target Compilation:
         cd backend
         ./mvnw clean package
