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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wiemj
 */
public class MembreAdminController extends AcceuilFXMLController {

    @FXML
    private TableView<User> table;
    @FXML
    private Button bt_banne;
    @FXML
    private Button removeBanButton;
    @FXML
    private TableColumn<User, String> userna;
    @FXML
    private TableColumn<User, String> emailm;
    @FXML
    private TableColumn<User, String> nomm;
    @FXML
    private TableColumn<User, String> prenomm;
    @FXML
    private TableColumn<User, String> adressem;
    @FXML
    private TableColumn<User, Integer> telephonem;
    @FXML
    private TableColumn<User, Integer> enabledm;
    private ObservableList<User> data;
 
    @FXML
    private TextField txt_id;
    private int selected_id;
    @FXML
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList();
        List<User> user = null;
        
            UserServices us = new UserServices();
            user=us.getAllUser();
            
        
        
        for (User i : user) {
            data.add(i);
        // TODO
    }   
         userna.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailm.setCellValueFactory(new PropertyValueFactory<>("email"));
        nomm.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomm.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adressem.setCellValueFactory(new PropertyValueFactory<>("addresse"));
        telephonem.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        enabledm.setCellValueFactory(new PropertyValueFactory<>("enabled"));
        

        table.setItems(null);
        table.setItems(data);
        table.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                User u = (User) table.getSelectionModel().getSelectedItem();
//        txt_id.setText(String.valueOf(u.getId()));
        if(u.getEnabled()== 1){
            bt_banne.setVisible(true);
            removeBanButton.setVisible(false);
            return;
        }else{
        removeBanButton.setVisible(true);
        bt_banne.setVisible(false);
        
        }
            }
        });
    }    
     @FXML
    private void Banner_click(MouseEvent event) {
        User u = (User) table.getSelectionModel().getSelectedItem();
        txt_id.setText(String.valueOf(u.getId()));
        if(u.getEnabled()== 1){
            bt_banne.setVisible(true);
            removeBanButton.setVisible(false);
            return;
        }else{
        removeBanButton.setVisible(true);
        bt_banne.setVisible(false);
        
        }
    }

    
    @FXML
    private void BannerAction(ActionEvent event) throws IOException{
        
             User u = (User) table.getSelectionModel().getSelectedItem();
        //txt_id.setText(String.valueOf(u.getId()));
        UserServices us= new UserServices();
        System.out.println("+++++++"+u.getEnabled());
        us.UpdateEnabledUser(u.getId(), 0);
       
       FXMLLoader loader=new FXMLLoader(getClass().getResource(("MembreAdmin.fxml")));
            loader.load();
            AnchorPane parentContent = loader.getRoot();
            window = (AnchorPane) table.getParent().getParent();
            MembreAdminController cont=loader.getController();
  
            window.getChildren().setAll(parentContent);
       // Parent root = FXMLLoader.load(getClass().getResource("MembreAdminFXML.fxml"));
        
        
    }

    @FXML
    private void removeBann(ActionEvent event) throws IOException {
        
         User u = (User) table.getSelectionModel().getSelectedItem();
        //txt_id.setText(String.valueOf(u.getId()));
        UserServices us= new UserServices();
        System.out.println("+++++++"+u.getEnabled());
        us.UpdateEnabledUser(u.getId(), 1);
         FXMLLoader loader=new FXMLLoader(getClass().getResource(("MembreAdmin.fxml")));
            loader.load();
            AnchorPane parentContent = loader.getRoot();
            window = (AnchorPane) table.getParent().getParent();
            MembreAdminController cont=loader.getController();
  
            window.getChildren().setAll(parentContent);
        
    }

}
