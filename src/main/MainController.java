package main;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;


public class MainController implements Initializable {

    private Service<Void> bakgrunnThread;

    @FXML
    private TextField brukernavn;
    @FXML
    private PasswordField passord;
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private Button login;


    public void loginAction(ActionEvent event) throws Exception {
        sqlMetoder sql = new sqlMetoder();

        bakgrunnThread = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws IOException {
                        System.out.println("Thread is running!");

                        progressIndicator.setVisible(true);
                        if (sql.testLogin(brukernavn, passord)) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Parent side2Parent = FXMLLoader.load(getClass().getResource("Side2.fxml"));
                                        Scene side2 = new Scene(side2Parent);
                                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                        window.setScene(side2);
                                        window.setTitle("Hjemmeside");
                                        window.setResizable(false);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                        progressIndicator.setVisible(false);

                        return null;
                    }
                };
            }
        };
        bakgrunnThread.start();
    }

    public void resetLoginAction(ActionEvent actionEvent) throws Exception{

        brukernavn.clear();
        passord.clear();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
