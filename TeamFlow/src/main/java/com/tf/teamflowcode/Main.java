package main.java.com.tf.teamflowcode;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Parent login;
    Scene scene;

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage stage) throws IOException {
        login = FXMLLoader.load(getClass().getResource("../../../../resources/com/tf/teamflowcode/GestioneAccount/fxml/fxmlLogin.fxml"));
    
        scene = new Scene(login, 810, 500);
    
        stage.setScene(scene);
        stage.getIcons().add(new Image("C:\\Users\\frali\\Desktop\\TeamFlow\\TeamFlow\\src\\main\\resources\\com\\tf\\teamflowcode\\logo.png"));
        stage.centerOnScreen();
        stage.setTitle("Login");
        stage.setResizable(false);
        stage.show();
    }
}