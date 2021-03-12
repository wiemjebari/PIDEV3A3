/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author wiemj
 */
public class ChoixController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void categorie(MouseEvent event) {
        
        
         Parent root=null;
        try {
            root=FXMLLoader.load(getClass().getResource("Categorie.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ChoixController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        bp.setCenter(root);
    }

    @FXML
    private void produit(MouseEvent event) {
        Parent root=null;
         try {
            root=FXMLLoader.load(getClass().getResource("Produit.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ChoixController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        bp.setCenter(root);
    }
    
}
