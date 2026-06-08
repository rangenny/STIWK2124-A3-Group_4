Welcome to the reproducible build matrix for the Group 4 ARL Application. 
This configuration satisfies all technical parameters for deployment, validation, and execution.

## 👥 Repository Target Details
- **Course System Identity:** STIWK2124 Web Engineering (Semester A252)
- **Assigned Submission Path Group:** Group 4
- **Project Structure Naming Pattern:** STIWK2124-A3-Group_4

## ⚡ Option A: Rapid Multi-Container Orchestration (Recommended)
This method automates the initialization of the MySQL Database, Spring Boot Application Layer, and Angular Production Distribution Server using isolated local containers.

### Prerequisites
- Install [Docker Desktop Engine](https://www.docker.com/products/docker-desktop/)

### Production Container Orchestration Steps
1. Open a terminal interface root folder in the directory path containing `docker-compose.yml`.
2. Execute the build instruction command:
   ```bash
   docker-compose up --build -d