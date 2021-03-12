/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class Start extends Application {
    
   
    Stage window;
    Scene scene1;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        window=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("GestionArticleAdmin.fxml"));
        Scene scene = new Scene(root);


scene1=scene;

        window.setScene(scene);
        window.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
