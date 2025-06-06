package lk.ijse.florist_pos.final_project;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class AppInitializer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent splashRoot = FXMLLoader.load(getClass().getResource("/View/LoadingScreen.fxml"));
        Scene splashScene = new Scene(splashRoot);
        Stage splashStage = new Stage();
        splashStage.initStyle(StageStyle.UNDECORATED); // No title bar
        splashStage.setScene(splashScene);
        splashStage.show();


        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> {

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), splashRoot);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(e -> {
                try {
                    Parent loginRoot = FXMLLoader.load(getClass().getResource("/View/LoginScreen.fxml"));
                    Scene loginScene = new Scene(loginRoot);
                    primaryStage.setScene(loginScene);
                    primaryStage.initStyle(StageStyle.UNDECORATED);
                    primaryStage.show();
                    splashStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            fadeOut.play();
        });

        delay.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
