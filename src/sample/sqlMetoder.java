package sample;
import javafx.scene.control.PasswordField;

import javax.swing.*;
import java.sql.*;
import javafx.scene.control.*;

public class sqlMetoder{

    // String brukernavn = controller.getBrukernavn();
    // String passord = controller.getPassord();

    // I koden under ligger info til databasen
    String url = "jdbc:mysql://localhost:3306/loginpage?allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false&serverTimezone=UTC";
    String user = "Jovo";
    String password = "Test123";


    public void testLogin (TextField brukernavn, PasswordField passord){



        // Her kobler vi til databasen og lager en sql statement
        try(Connection connection = DriverManager.getConnection(url, user,password)) {

           //  Statement myStmt = connection.createStatement();
            // Her lager vi sql setning som henter brukernavn og passord(passordet er kryptert med sha1)
            String sql = "Select * from logininfo where brukernavn=? and passord =sha1(?)";
            // ResultSet rs = myStmt.executeQuery(sql);
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,brukernavn.getText());
            pst.setString(2,passord.getText());
            ResultSet rs = pst.executeQuery();

            //System.out.println(rs.next());

            // lagt inn test for hvis noe spesifikt blir skrevet inn i passord og brukernavn
            if(rs.next()){
                JOptionPane.showMessageDialog(null,"Welcome "+brukernavn.getText().toUpperCase()+"!");
            }else{
                JOptionPane.showMessageDialog(null,"Username or Password not Correct\n" +
                        "Please try again");
                brukernavn.setText("");
                passord.setText("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Could not connect to server!\n" +
                    "Check your internet connection or contact administrator");
        }

    }

}
