package sample;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private Service<Void> bakgrunnThread;

    @FXML
    private TextField brukernavn;
    @FXML
    private PasswordField passord;
    @FXML
    public ProgressIndicator progressIndicator;


    public void loginAction(ActionEvent event) {
        sqlMetoder sql = new sqlMetoder();

        bakgrunnThread = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        System.out.println("Thread is running!");

                        progressIndicator.setVisible(true);
                        sql.testLogin(brukernavn, passord);
                        progressIndicator.setVisible(false);

                        return null;
                    }
                };
            }
        };
        bakgrunnThread.start();
    }

    public void resetLoginAction(ActionEvent actionEvent) {

        brukernavn.clear();
        passord.clear();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
