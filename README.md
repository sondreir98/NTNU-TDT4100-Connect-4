# Connect4 Project

This is a Java-based Connect4 application built using **JavaFX** and **Maven**. The project includes both the game logic and a graphical user interface (GUI) for playing Connect4.

---

## Project Structure

prosjekt-base:
  src:
    main:
      java:
        Prosjekt:
          - Connect4App.java
          - Connect4Controller.java
          - Connect4Model.java
          - "... (other helper classes)"
      resources:
        exampleproject:
          prosjekt:
            - Connect4.fxml
    test:
      java:
        exampleproject: []
  pom.xml: ""
  README.md: ""


---

## Features

- Graphical interface using **JavaFX**  
- Fully playable Connect4 game with two players  
- Model-View-Controller (MVC) structure for clean separation of game logic and UI  
- Includes unit tests for the game model

---

## Prerequisites

- **Java 17** or higher installed  
- **Maven** installed  
- Internet connection for Maven to download dependencies (JavaFX, JUnit)

---

## How to Build and Run

1. **Clone the repository**:

```bash
git clone https://github.com/sondreir98/NTNU-TDT4100-Connect-4.git
cd NTNU-TDT4100-Connect-4/prosjekt-base

build:
  description: "Build the project"
  command: "mvn clean compile"

run:
  description: "Run the application"
  methods:
    - name: "Using Maven Exec Plugin"
      command: "mvn exec:java -Dexec.mainClass=\"Prosjekt.Connect4App\""
    - name: "Using JavaFX Maven Plugin (recommended)"
      command: "mvn clean javafx:run"

tests:
  description: "Running Tests"
  note: "Unit tests are written using JUnit 5"
  command: "mvn test"

notes:
  - "The FXML file for the UI is located at src/main/resources/exampleproject/prosjekt/Connect4.fxml"
  - "Make sure you run the Maven commands from the prosjekt-base folder where pom.xml exists"
