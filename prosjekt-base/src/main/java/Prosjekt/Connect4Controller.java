package Prosjekt;

import java.io.FileNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class Connect4Controller {

    @FXML
    Circle Circle00;
    @FXML
    Circle Circle01;
    @FXML
    Circle Circle02;
    @FXML
    Circle Circle03;
    @FXML
    Circle Circle04;
    @FXML
    Circle Circle05;
    @FXML
    Circle Circle10;
    @FXML
    Circle Circle11;
    @FXML
    Circle Circle12;
    @FXML
    Circle Circle13;
    @FXML
    Circle Circle14;
    @FXML
    Circle Circle15;
    @FXML
    Circle Circle20;
    @FXML
    Circle Circle21;
    @FXML
    Circle Circle22;
    @FXML
    Circle Circle23;
    @FXML
    Circle Circle24;
    @FXML
    Circle Circle25;
    @FXML
    Circle Circle30;
    @FXML
    Circle Circle31;
    @FXML
    Circle Circle32;
    @FXML
    Circle Circle33;
    @FXML
    Circle Circle34;
    @FXML
    Circle Circle35;
    @FXML
    Circle Circle40;
    @FXML
    Circle Circle41;
    @FXML
    Circle Circle42;
    @FXML
    Circle Circle43;
    @FXML
    Circle Circle44;
    @FXML
    Circle Circle45;
    @FXML
    Circle Circle50;
    @FXML
    Circle Circle51;
    @FXML
    Circle Circle52;
    @FXML
    Circle Circle53;
    @FXML
    Circle Circle54;
    @FXML
    Circle Circle55;
    @FXML
    Circle Circle60;
    @FXML
    Circle Circle61;
    @FXML
    Circle Circle62;
    @FXML
    Circle Circle63;
    @FXML
    Circle Circle64;
    @FXML
    Circle Circle65;

    @FXML
    GridPane gridPane;
    @FXML
    Button resetButton;

    private Connect4Model connect4Game;
    private Connect4FileReader fileReader;

    @FXML
    public void initialize() {

        connect4Game = new Connect4Model();
        fileReader = new Connect4FileReader(connect4Game);
    }

    @FXML
    public void HandleCircleClicked(MouseEvent event) {

        int row;
        int col;
        Circle clickedCircle = (Circle) event.getSource();
        try {
            col = GridPane.getColumnIndex(clickedCircle);
        } catch (java.lang.NullPointerException exception) {
            col = 0;
        }
        try {
            row = GridPane.getRowIndex(clickedCircle);
        } catch (java.lang.NullPointerException exception) {
            row = 0;
        }

        if (!connect4Game.isGameEnded()) {
            if (connect4Game.makeMove(col, row)) {
                updateView(event);
            }
        }
    }

    @FXML
    public void resetButton(MouseEvent event) {
        fileReader.resetFile();
        // Create and configure the alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("File Reset");
        alert.setHeaderText(null);
        alert.setContentText("The statistics has been reset!");

        // Show the alert and wait for user acknowledgement
        alert.showAndWait();
    }

    @FXML
    public void updateView(MouseEvent event) {

        int row = connect4Game.getRow();
        int col = connect4Game.getColumn();
        String circleId = "Circle" + col + row; // Generate the ID of the Circle object
        Circle clickedCircle = (Circle) gridPane.lookup("#" + circleId); // Find the Circle object

        if (connect4Game.getCurrentPlayer() == 'X') {
            clickedCircle.setFill(Color.YELLOW);
        } else {
            clickedCircle.setFill(Color.RED);
        }
        if (connect4Game.isGameEnded() || connect4Game.isGameDraw(connect4Game.getGameBoard())) {
            gameEndedMessage();
        }
    }

    @FXML
    public void gameEndedMessage() {

        try {
            fileReader.saveWinnerToFile();
            fileReader.readWinnerFromFile(); // Må ha i tilfelle filen er tom
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (connect4Game.isDraw()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.setContentText("Draw! \n Total statistics: \nYellow has won " + fileReader.getYellowCount()
                    + " times" + " and red has won " + fileReader.getRedCount() + " times" + " !");
            alert.showAndWait();
        } else if (connect4Game.isGameEnded()) {
            if (connect4Game.getCurrentPlayer() == 'O') {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Game Over");
                alert.setHeaderText(null);
                alert.setContentText("Winner: Red! \nTotal statistics: \nRed has won " + fileReader.getRedCount()
                        + " times" + " and yellow has won " + fileReader.getYellowCount() + " times" + "!");
                alert.showAndWait();

            } else if (connect4Game.getCurrentPlayer() == 'X') {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Game Over");
                alert.setHeaderText(null);
                alert.setContentText(
                        "Winner: Yellow! \nTotal statistics: \nYellow has won " + fileReader.getYellowCount() + " times"
                                + " and red has won " + fileReader.getRedCount() + " times" + "!");
                alert.showAndWait();

            }

        }
    }

}
