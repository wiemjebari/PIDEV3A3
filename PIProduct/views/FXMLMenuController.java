/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.views;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author wiemj
 */
public class FXMLMenuController implements Initializable {
     @FXML
    private JFXButton trouver1;
    @FXML
    private JFXButton recommendation;
    @FXML
    private JFXButton vet;
    @FXML
    private JFXButton boutq;
    @FXML
    private JFXButton adoption;
    @FXML
    private JFXButton train;
    @FXML
    private GridPane icon_user;
    @FXML
    private JFXButton trouver;
    @FXML
    private JFXButton adoption1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
