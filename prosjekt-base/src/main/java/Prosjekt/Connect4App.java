package Prosjekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Connect4App extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {

        primaryStage.setTitle("Connect 4");
        primaryStage
                .setScene(new Scene(FXMLLoader.load(getClass().getResource("/exampleproject/prosjekt/Connect4.fxml"))));
        primaryStage.show();
    }

    public static void main(final String[] args) {
        Application.launch(args);
    }
}
