<<<<<<< HEAD
# knab-trello
=======
# MayankTiwari-Knab

**Description:**

Trello-testFramework is an automation testing project that enables UI and API automation using a hybrid technique. The project utilizes Maven as its build tool and follows the Page Object Model (POM) design pattern for UI automation. It automates the UI testing of the “Trello.com” application. Additionally, the project includes API testing using Java with Rest Assured, focusing on automating the Trello APIs. As a bonus, you can test this project build on a docker container as well.

**Table of Contents:**

1. Description
2. Requirements
3. Installation
4. Execution
5. Automated Scenarios
6. Usage
7. Project Structure
8. UI Automation
9. API Automation

**Requirements:**

Java Development Kit (JDK) 8 or higher Maven Web browser (for UI automation)
Docker install in local

**Installation:**

Clone the repository from GitHub:
Copy code git clone https://github.com/mayanktee/sprite-cloud.git Navigate to the project directory:
Copy code
cd trello-testFramework
Install the required dependencies using Maven: Copy code
mvn clean install

**Execution:**

1. To run the UI automation for the “trello.com” application, use the following command: mvn test -Dtest=cucumber.Options.TestRunnerUI 
2. For executing the API automation for Trello APIs, use the command: mvn test -Dtest=cucumber.Options.TestRunnerAPI 
3. You can directly trigger the execution by Runner Class from the local machine - TestRunnerAPI & TestRunnerUI 
4. This project is also integrated with the docker container & Jenkins for build-CI

   To Run the container install the docker hub & execute these commands on the terminal :
   
   1. PULL THE IMAGE: docker pull selenium/standalone-chrome
   2. To Run: docker run -d -p 4444:4444 -p 7900:7900 --shm-size="2g" selenium/standalone-chrome:latest
   3. Execute the TestRunnerUI locally & adding the environment variable: executionEnvironment=container/local

**Automated Scenarios:**

1. API: I automated Trello Create Board API.
    * API Board: In this feature, as a user, we are trying to automate CRUD operation with positive and negative scenarios for API
2. UI: I automated Trello.com - 
    * uiTrelloHome: In this feature, as a user we are trying to automate and test the landing on the Trello home page, validation for create, update & delete the board functionality.

**Project Structure:**

trello-testFramework/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── testBase/
│   │   │   |   └── BaseTest.java/
│   │   │   └── testUtils
|   |   |        └── SpecBuilder.java
|   |   |        └── Utils.java
│   │   ├── resources/
│   │   │   └── log4j.properties
│   └── test/
│       ├── java/
│       │   └── cucumber/
│       │       └── option/
│       │           └── TestRunnerAPI.java
|       |           └── TestRunnerUI.java
|       |   └── pages/
|       |   └── step.Defs/
|       |   └── testUtils/
│       └── resources/
│           └── .feature
|           └── extent.properties
|           └── global.properties
├── target/
│   └── [compiled bytecode and generated JAR file]
├── pom.xml
├── README.md
└── .gitignore
└── .dockerignore
└── Dockerfile

**UI Automation:**

The UI automation is based on the Page Object Model (POM) design pattern. The "pages" package contains classes representing web pages or components of the “trello.com” application. Each page or component has its associated methods to interact with elements and perform actions.

**API Automation:**

The API automation utilises Java with Rest Assured to test the trello APIs. The "api" package includes test classes, each focusing on different API endpoints and scenarios. Test data, if required, can be found in the "feature" and "global.properties" directory under the "resources" folder.
>>>>>>> c232b03 (First Commit)
