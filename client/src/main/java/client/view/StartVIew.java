package client.view;

import client.model.Command;
import client.model.CommandType;
import client.model.Player;
import client.server.ClientConnection;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import util.Util;

/**
 * JavaFX view for the initial setup and player registration, allowing the player to choose between playing with a computer or another human.
 * The view includes buttons for starting the game and exiting, as well as a message label.
 */
public class StartVIew extends VBox {

    private static boolean isHuman; // True if playing with human, false if playing with computer
    private Timer timer;
    private int seconds = 3;
    private Player player;
    private Label msgLbl = new Label();
    private Stage stage;

    private volatile ClientConnection handler;

    // starting point
    public StartVIew(Stage stage) {
        this.stage = stage;
        initStartView(stage);
        initPlayerView(); // init player with details
    }

    private void initPlayerView() {
        // Create a new TextInputDialog
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Player Registration");
        dialog.setHeaderText("Enter Player Name");
        dialog.setContentText("Name:");

        // Customizing the dialog button types
        dialog.getDialogPane().getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

        // Show the dialog and capture the result
        Optional<String> result = dialog.showAndWait();

        // Process the result
        result.ifPresent(name -> {
            if (name.trim().isEmpty()) {
                // Handle the case where the name is empty
                System.out.println("Player name cannot be empty.");
                initPlayerView();  // Re-invoke the dialog if input was invalid
            } else {
                // Save the valid input to the playerName variable
                player = new Player(name);
                System.out.println("Player Name Set: " + player);  // Optional: for debugging

                // create client server using separate thread
                Thread thread = new Thread(() -> {
                    try {
                        handler = new ClientConnection(Util.IP_ADDRESS, Util.DEFAULT_PORT);
                        handler.sendObject(player);

                        // read a message from server
                        Command command = handler.readCommand();
                        if (command.getMessageType().equals(CommandType.CONNECTED_WTH_SERVER)) {
                            createSuccessMsg();
                        }

                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });
                thread.start();
            }
        });
    }

    private void createSuccessMsg() {
        // Assuming 'msgLabel' is a Label defined in your class
        Platform.runLater(() -> {
            msgLbl.setText(player.getName() + " you are successfully connected with server.");
        });
    }


    // init design part
    private void initStartView(Stage stage) {
        // Load and set the background image
        BackgroundImage myBI = new BackgroundImage(new Image("background.png", 800, 600, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(myBI));

        // Start button
        Button startButton = createStyledButton("Start Game");
        startButton.setOnAction(e -> showGameModeSelectionDialog(stage));

        // Exit button
        Button exitButton = createStyledButton("Exit Game");
        exitButton.setOnAction(e -> Platform.exit());

        // Add buttons to VBox
        msgLbl.setTextFill(Color.LIGHTYELLOW);
        this.getChildren().addAll(startButton, exitButton, msgLbl);
        this.setSpacing(10);  // Set spacing between buttons
        this.setAlignment(Pos.CENTER);
    }

    private void showGameModeSelectionDialog(Stage stage) {
        Dialog<Boolean> dialog = new Dialog<>();
        dialog.initOwner(stage);
        dialog.initModality(Modality.APPLICATION_MODAL); // Block interactions with other windows
        dialog.setTitle("Select Game Mode");

        VBox dialogVBox = new VBox(10);
        Button playWithComputer = createStyledButton("Play with Computer");
        Button playWithHuman = createStyledButton("Play with Human");

        playWithComputer.setOnAction(e -> {
            isHuman = false;
            dialog.setResult(false);
            dialog.close();
            showLoadingDialog(stage);
            //todo: implement AI
        });

        playWithHuman.setOnAction(e -> {
            isHuman = true;
            dialog.setResult(true);
            dialog.close();
            showLoadingDialog(stage);

            // start match
            requestGameplayWithHuman();
        });

        dialogVBox.getChildren().addAll(new Text("Choose your game mode:"), playWithComputer, playWithHuman);
        dialog.getDialogPane().setContent(dialogVBox);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        dialog.showAndWait(); // Show and wait for user action
    }

    /**
     * Request gameplay with server if connection match with another player game will start
     */
    private void requestGameplayWithHuman() {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("Sending New Match Request to server.");
                // request server for new game
                Command reqCommand = new Command(player, null, CommandType.NEW_MATCH_REQUEST_HUMAN, "");
                handler.sendObject(reqCommand);

                // read a message from server
                System.out.println("Received Status from server, starting the game.");
                Command resCommand = handler.readCommand();
                System.out.println(resCommand);
                if (resCommand.getMessageType().equals(CommandType.MATCH_START)) {
                    Platform.runLater(this::loadNextView);
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Invalid Request");
            }
        });
        thread.start();
    }

    // show loading screen
    // todo: join in server, wai
    private void showLoadingDialog(Stage stage) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.initOwner(stage);
        dialog.initModality(Modality.APPLICATION_MODAL); // Block interactions with other windows
        dialog.setTitle("Loading Game");

        // Timer and message layout
        VBox dialogVBox = new VBox(10);
        Text message = new Text("Game is loading, please wait...");
        message.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        Text countdownText = new Text("30"); // Start countdown from 30 seconds
        countdownText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        dialogVBox.getChildren().addAll(message, countdownText);
        dialog.getDialogPane().setContent(dialogVBox);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE); // Add close button to dialog

        // Show dialog
        Platform.runLater(dialog::show);

        // Timer setup
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (seconds > 0) {
                    seconds--;
                    Platform.runLater(() -> countdownText.setText(String.valueOf(seconds)));
                } else {
                    timer.cancel();
                    Platform.runLater(() -> {
                        dialog.close(); // Close the dialog when loading is complete
//                        loadNextView(stage); // Load next view
                    });
                }
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000); // Schedule the task to run starting now and then every second...
    }

    // load next view
    private void loadNextView() {
        GameView gameView = new GameView(handler);
        Scene scene = new Scene(gameView, 570 + 350, 720);
        stage.setScene(scene);
    }

    // create a styled button
    public Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setMinWidth(120);
        button.setMinHeight(40);
        button.setMaxHeight(40);
        button.setStyle("-fx-background-color: BLACK; " +    // Base color
                "-fx-background-radius: 5; " +      // Rounded corners
                "-fx-text-fill: white;");            // Text color

        // Change style on hover using CSS pseudo-class
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: RED; " +
                "-fx-background-radius: 5; " +
                "-fx-text-fill: white;"));

        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: BLACK; " +
                "-fx-background-radius: 5; " +
                "-fx-text-fill: white;"));

        return button;
    }
}

