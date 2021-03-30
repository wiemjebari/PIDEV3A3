/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.views;

import com.jfoenix.controls.JFXButton;
import gestionproduit.entity.Produit;
import gestionproduit.services.ProduitService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * FXML Controller class
 *
 * @author wiemj
 */
public class VideoProduitController implements Initializable {

    @FXML
    private AnchorPane AP;
    int idpub;
     Produit p;
     ProduitService ps;
    @FXML
    private GridPane imagesPane;
     private Pane IMAGTEST;
  private  String MEDIA_URL;
    private MediaPlayer mediaPlayer;
    Group root = new Group();
   
    public VideoProduitController()
    {
        p=AjoutProduitController.pp;
              MEDIA_URL  = "http://localhost/uploadsvideo/"+p.getVideoProduit();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         mediaPlayer = new MediaPlayer(new Media(MEDIA_URL));
        //mediaPlayer.setAutoPlay(true);
        MediaControl mediaControl = new MediaControl(mediaPlayer);
        mediaControl.setMinSize(700,300);
        mediaControl.setPrefSize(700,300);
        mediaControl.setMaxSize(700,300);
        root.getChildren().add(mediaControl);
        imagesPane.add(root, 0, 0);
       
    }    
    
}
