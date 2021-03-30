/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.views;

import gestionproduit.entity.User;
import gestionproduit.services.UserServices;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * FXML Controller class
 *
 * @author wiemj
 */
public class LoginUserController implements Initializable {
     int logged = 0;
    @FXML
    private JFXTextField login;
    @FXML
    private JFXPasswordField password;
    @FXML
    private Button button;
    @FXML
    private Button buttonAdmin;
    @FXML
    private ImageView close;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {}
         public void log(ActionEvent event) throws IOException {
        UserServices us = new UserServices();
if (login.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insert your Pseudo");
            alert.setHeaderText("Insert your Pseudo");
            alert.setContentText("Insert your Pseudo");

            alert.showAndWait();

        } else if (password.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insert your Password");
            alert.setHeaderText("Insert your Password");
            alert.setContentText("Insert your Password");

            alert.showAndWait();
        } 
        else{
    //char[] pass=password.g
    String passText = password.getText();
    System.out.println("++++++"+passText);
            User u=us.AfficherUser(login.getText());
            
         if (u != null) {
        if (us.Login(login.getText(), password.getText())) {
            System.out.println(us.verifAdmin(login.getText()));
            System.out.println("+++++"+u.getConfirmation_token());
            if (u.getEnabled()== 0) {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Blocked");
                            alert.setHeaderText("You are blocked ! ");
                            alert.setContentText(" You are blocked !");
                             alert.showAndWait();
            }
            else if(u.getConfirmation_token().equals("0")||u.getConfirmation_token().equals("")){
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Comfirmation");
                            alert.setHeaderText("You are blocked ! ");
                            alert.setContentText(" Comfirmation doesn't token");
                             alert.showAndWait();
            }
                
            else if(us.verifAdmin(login.getText())||us.Login(login.getText(), password.getText())){
                
            FXMLLoader loader = new FXMLLoader();
            User.setUserconnected(u.getId());
        loader.setLocation(getClass().getResource("AcceuilFXML.fxml"));
        loader.load();
        AcceuilFXMLController cnt = loader.getController();
        Parent root = loader.getRoot();
        Stage stage=new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        closeStage();
            
        }
            else if(us.Login(login.getText(), password.getText())) {
                User.setUserconnected(u.getId());
                 FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AcceuilFXML.fxml"));
        loader.load();
        AcceuilFXMLController cnt = loader.getController();
        
        
        Parent root = loader.getRoot();
        Stage stage=new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        closeStage();
            }
            

        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "failed", ButtonType.CLOSE);
            a.show();
        }

    }}}

    public void register(ActionEvent event) throws IOException {
        
          FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("RegisterUser.fxml"));
        loader.load();
        RegisterUserController cnt = loader.getController();
        
        
        Parent root = loader.getRoot();
        Stage stage=new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        closeStage();

    }
    private void closeStage() {
        ((Stage)login.getScene().getWindow()).close();
    }
    @FXML
     public void back(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("LoginAdmin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
   @FXML
     public void profile(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("LoginAdmin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
   @FXML
    private void closeWindow(MouseEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
        // TODO
    }    
    

