/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import Entities.Randonnees;
import Services.RandonneeService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class Pi extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        /*Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        //launch(args);
        RandonneeService rs =new RandonneeService();
        
        
        /*List<Randonnees> liste=new ArrayList<Randonnees>();
        liste=rs.readAll();*/
        
        Randonnees r1=new Randonnees(new java.sql.Date(2021, 3, 3),"tunis",5,18,50,"photo");
        Randonnees r2=new Randonnees(new java.sql.Date(2021,4,5),"nabeul",6,20,30,"photo 1");
        rs.add(r1);
        rs.add(r2);
    }
    
}
