/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author wiemj
 */
public class MenuController implements Initializable {

    @FXML
    private ImageView adminn;
    @FXML
    private VBox vbox;
    @FXML
    private ImageView vb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goclient(ActionEvent event) {
        try {Stage primaryStage=new Stage();
            Parent root =
           FXMLLoader.load(getClass().getResource("client.fxml"));
            Scene scene = new Scene(root); 
            primaryStage.setTitle("Healthy Vibes");
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }   
    }

    @FXML
    private void goadmin(ActionEvent event) {
         try {Stage primaryStage=new Stage();
            Parent root =
           FXMLLoader.load(getClass().getResource("choix.fxml"));
            Scene scene = new Scene(root); 
            primaryStage.setTitle("Healthy Vibes");
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }   
    }

    @FXML
    private void switsh1(MouseEvent event) {
        TranslateTransition t = new TranslateTransition(javafx.util.Duration.seconds(1.0), vbox);
        t.setToX(vbox.getLayoutX()- vbox.getLayoutX()*1);
        t.play();
    }

    @FXML
    private void switsh2(MouseEvent event) {
        TranslateTransition t = new TranslateTransition(javafx.util.Duration.seconds(1.0), vbox);
        t.setToX(vbox.getLayoutX()- vbox.getLayoutX()*2);
        t.play(); 
    }
    
}
