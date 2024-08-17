# Java Database Demo

## Setup
### Step 1: Install Docker
https://docs.docker.com/desktop/install/windows-install/

### Step 2: Create Docker with PostgreSQL

- Create the database (run only once)
  - This command wil also start the container

    
    docker compose up -d
    
### Step 3: Add JDBC jar dependency
* https://www.jetbrains.com/help/idea/working-with-module-dependencies.html#add-a-new-dependency
* Select dependencies\postgresql-42.7.3.jar

### Bonus: Install Database Visualizer
https://dbeaver.io/download/

## Run
### Step 1: Start Docker Desktop
- Search for Docker Desktop in  Windows Start Menu

### Step 2: Start the database container
    docker start DatabaseDemo

### Step 3: Run the Java Code from Intellij

## Useful Docker commands
- Start the database container

    
    docker start DatabaseDemo

- Stop the database container


    docker stop DatabaseDemo

- List all docker containers


    docker ps -a

## Useful SQL tutorials

- https://www.w3schools.com/sql/default.asp (Beginner)
- https://www.sql-practice.com/ (Intermediate)
