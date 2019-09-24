package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

//TODO: Fikle mer med neste side som kommer etter innlogging, legg til utlogg knapp og last inn ting fra MySQL osv

public class Main extends Application {

   /* BRUK FØLGENDE KODE FOR Å BYTTE SCENER I FREMTIDEN

    Parent side2Parent = FXMLLoader.load(getClass().getResource("Side2.fxml"));
    Scene side2 = new Scene(side2Parent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(side2); */

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("javafx.version"));

    }


    public static void main(String[] args) {
        launch(args);
    }
}
