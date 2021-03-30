/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.views;

import gestionproduit.entity.User;
import gestionproduit.services.UserServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wiemj
 */
public class LoginAdminController implements Initializable {

    @FXML
    private TextField login;
    @FXML
    private PasswordField password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goAuthentification(ActionEvent event) throws IOException {
        UserServices us = new UserServices();
        User u=us.AfficherUser(login.getText());
        if (login.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty login");
            alert.setHeaderText("login ");
            alert.setContentText("No login was inserted");

            alert.showAndWait();

        } else if (password.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty password");
            alert.setHeaderText(" Password ");
            alert.setContentText("No Password was inserted");

            alert.showAndWait();
        } else {
            if(us.verifAdmin(login.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("not Admin");
            alert.setHeaderText(" Admin ");
            alert.setContentText("You are not Admin");
            }
            
               else if (us.Login(login.getText(), password.getText())) {
          
                ((Node) (event.getSource())).getScene().getWindow().hide();
                User.setUserconnected(u.getId());
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("AcceuilAdmin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
            

        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "failed", ButtonType.CLOSE);
            a.show();
        }
            }
               
    }
    @FXML
     public void back(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("LoginUser.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    
}
