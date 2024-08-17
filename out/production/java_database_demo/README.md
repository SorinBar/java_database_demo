# java_database_demo

## Setup

### Step 1: Install Docker
https://docs.docker.com/desktop/install/windows-install/

### Step 2: Create Docker with PostgreSQL

* Create the database (run only once)
  * This command wil also start the container

    
    docker compose up -d
    
### Step 3: Run the java code


## Useful Docker commands
* Start the database

    
    docker start DatabaseDemo

* Stop the database


    docker stop DatabaseDemo


* List docker containers


    docker ps

* List all docker containers


    docker ps -a

* Connect to the database
    

    psql -h localhost -p 5432 -U scott
