
# Networked Scrabble Game

## Description
This project extends a basic two-player Scrabble game into a flexible, networked multiplayer game supporting 2-4 players, which can be a mix of human and computer players. The game server, which manages multiple concurrent games, operates from a static known address and supports dynamic client connections.

## Getting Started

### Prerequisites
- Java JDK 17 or later
- Maven (for dependency management and building the project)
- IntelliJ IDEA (recommended for running the project)

### Installation
1. Clone the repository or download the project files.
2. Navigate into the project directory.
3. Use Maven to build the project:
   ```bash
   mvn clean install
   ```
   Alternatively, you can build the project directly from IntelliJ IDEA by using the "Import Project" feature and selecting the project's `pom.xml`.

### Running the Server
To start the game server:
- Open the project in IntelliJ IDEA.
- Navigate to the `ServerMain.java` file within the `server` module.
- Right-click on the file and select `Run 'ServerMain.main()'`.

### Running a Client
To connect a client to the server:
- Enable multiple instance from IDE setting.
- Open a new instance of IntelliJ IDEA or use the same instance to open a new project window.
- Navigate to the `ClientMain.java` file within the `client` module.
- Right-click on the file and select `Run 'ClientMain.main()'`.
- Repeat the steps for additional clients as needed.

## Game Play
Players can join an existing table or create a new one upon connecting to the server. The game adheres to standard Scrabble rules, modified to accommodate multiple players. The user interface is implemented using JavaFX, offering a graphical representation of the game board and interactive elements.

## Features
- Support for 2-4 players per game.
- Mix of human and computer-controlled players.
- JavaFX-based graphical user interface.
- Dynamic client handling without hardcoded server details.

## Configuration
Game settings like timeout durations and number of players per game can be configured when setting up a table.

## Testing
The application should be tested with multiple clients, simulating different game configurations. Testing can be done on multiple machines to ensure proper network operation.

## Documentation
For detailed developer documentation, refer to the Javadoc in the source files.

## Contribution
Contributions to the project are welcome. Please ensure to follow the existing code style and add comments where necessary.

## License
Specify your licensing information here.
