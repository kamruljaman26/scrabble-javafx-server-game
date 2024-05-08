package client.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;

public class StartVIew extends VBox {

    private static boolean isHuman; // True if playing with human, false if playing with computer
    private Timer timer;
    private int seconds = 3;

    // starting point
    public StartVIew(Stage stage) {
        initStartView(stage);

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
        this.getChildren().addAll(startButton, exitButton);
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
        });

        playWithHuman.setOnAction(e -> {
            isHuman = true;
            dialog.setResult(true);
            dialog.close();
            showLoadingDialog(stage);
        });

        dialogVBox.getChildren().addAll(new Text("Choose your game mode:"), playWithComputer, playWithHuman);
        dialog.getDialogPane().setContent(dialogVBox);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        dialog.showAndWait(); // Show and wait for user action
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
                        loadNextView(stage); // Load next view
                    });
                }
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000); // Schedule the task to run starting now and then every second...
    }

    // load next view
    private void loadNextView(Stage stage) {
        GameView gameView = new GameView();
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

