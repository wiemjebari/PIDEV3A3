/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author wiemj
 */
public class AcceuilProduitController  extends AcceuilFXMLController{

    @FXML
    private AnchorPane windowa;
    @FXML
    private AnchorPane reclamations;
    @FXML
    private AnchorPane reclamations1;
    @FXML
    private AnchorPane reclamations11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   @FXML
    private void votreListProduit(ActionEvent event) throws IOException {
       
         FXMLLoader loader=new FXMLLoader(getClass().getResource(("ProprietaireProduit.fxml")));
            loader.load();
            AnchorPane parentContent = loader.getRoot();
            window = (AnchorPane) reclamations.getParent().getParent();
            ProprietaireProduitController cont=loader.getController();
  
            window.getChildren().setAll(parentContent);
    }

    @FXML
    private void ListProduit(ActionEvent event) throws IOException {
       
        FXMLLoader loader=new FXMLLoader(getClass().getResource(("AfficherAllProduit.fxml")));
            loader.load();
            AnchorPane parentContent = loader.getRoot();
            window = (AnchorPane) reclamations.getParent().getParent();
            AfficherAllProduitController cont=loader.getController();
  
            window.getChildren().setAll(parentContent);
    }

    @FXML
    private void votreListFavoris(ActionEvent event) throws IOException {
         
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherFavorisProduit.fxml"));
        loader.load();
            AnchorPane parentContent = loader.getRoot();
            window = (AnchorPane) reclamations.getParent().getParent();
            AfficherFavorisProduitController cont=loader.getController();
  
            window.getChildren().setAll(parentContent);
    }

    @FXML
    private void AjoutProduit(ActionEvent event) throws IOException {
         
       
        FXMLLoader loader  = new FXMLLoader(getClass().getResource("AjoutProduit.fxml"));
        loader.load();
            AnchorPane parentContent = loader.getRoot();
            windowa = (AnchorPane) reclamations.getParent().getParent();
            AjoutProduitController cont=loader.getController();
  
            windowa.getChildren().setAll(parentContent);
    }

    @FXML
    private void Statistique(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Static.fxml"));
        loader.load();
            AnchorPane parentContent = loader.getRoot();
            window = (AnchorPane) reclamations.getParent().getParent();
            StaticController cont=loader.getController();
  
            window.getChildren().setAll(parentContent);
    }
    
    
}
