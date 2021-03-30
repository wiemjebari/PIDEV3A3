/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.views;

import gestionproduit.entity.FTTS;
import gestionproduit.entity.Produit;
import gestionproduit.entity.Upload;
import gestionproduit.services.ProduitService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 * FXML Controller class
 *
 * @author wiemj
 */
public class UpdateProduitController implements Initializable {

     private int idProduit;
    @FXML
    private Label nom11;
    @FXML
    private JFXButton upload;
    @FXML
    private JFXTextField NomInsertion;
    @FXML
    private JFXTextField stockInsertion;
    @FXML
    private JFXTextField PrixInsertion;
    @FXML
    private ChoiceBox<String> choixInsertion;
    @FXML
    private JFXTextArea descriptionInsertion;
    Produit p = new Produit();
    private File file;
    private File file1 = new File("");
    String vid;
    private Upload up;
    String pic;
    private FileChooser.ExtensionFilter extFilterJPG;
    private FileChooser.ExtensionFilter extFilterjpg;
    private Image image;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          choixInsertion.getItems().setAll("cafe", "restaurant", "supermarche","cafe");

        ProduitService rs = new ProduitService();
        Produit ps = rs.AfficherDetailProduit(Produit.getId_pModifier());
        p = ps;
        choixInsertion.setValue(ps.getCategorieProduit());
        descriptionInsertion.setText(ps.getDescriptionProduit());
        upload.setText(ps.getPhotoProduit());
        PrixInsertion.setText(Float.toString(ps.getPrixProduit()));
        stockInsertion.setText(Integer.toString(ps.getStockProduit()));
        NomInsertion.setText(ps.getNomProduit());
        // TODO
    }    

    @FXML
    private void fileChoosing(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(null);
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //pic=(file.toURI().toString());
        pic = new Upload().upload(file, "img");
        System.out.println(pic);
        image = new Image("http://localhost/uploads/" + pic);
    }

    @FXML
    private void actionInsertion2(ActionEvent event) throws IOException {
        if ((PrixInsertion.getText().trim().equals("")) || (stockInsertion.getText().trim().equals(""))
                || (NomInsertion.getText().trim().equals("")) || (choixInsertion.getValue().isEmpty())
                || (descriptionInsertion.getText().trim().equals("")) || (PrixInsertion.getText().trim().equals(""))) {
            Alert fail = new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("Erreur");
            fail.setContentText("Vous avez oublier de remplir un champs");
            fail.showAndWait();
        } else {
            if (file == null) {
 
                Alert alert = new Alert(Alert.AlertType.WARNING, "Vous avez oublié de télécharger une image ou bien des  donnes concernant l'annonce ", ButtonType.CLOSE);
                alert.show();
                upload.requestFocus();
            } else {

                ProduitService as = new ProduitService();

                if ((Integer.parseInt(stockInsertion.getText()) < 0) || Float.parseFloat(PrixInsertion.getText()) < 0) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Le champs Stock doit être un entier positif et le champs Prix doit être un float positif", ButtonType.CLOSE);
                    alert.show();
                } else {
                   // System.out.println("Id produi: " + idProduit);
                }
                as.modifierProduit(new Produit(
                        p.getIdProduit(),
                        NomInsertion.getText(),
                        descriptionInsertion.getText(),
                        pic,
                        Float.parseFloat(PrixInsertion.getText()),
                        Integer.parseInt(stockInsertion.getText()),
                        choixInsertion.getValue()
                ));
                String desc = descriptionInsertion.getText().toString();
                FTTS FTTSProduit = new FTTS(desc);
                FTTSProduit.speak();

                choixInsertion.setValue("Informatique et multimédia");
                descriptionInsertion.setText("");

                NomInsertion.setText("");
                PrixInsertion.setText("");
                stockInsertion.setText("");

                TrayNotification tray = new TrayNotification("Notification !", "Produit modifiée avec succée", NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.seconds(6));
            }
           

        }

    }

    public void initData(int idProduit) {
        p.setIdProduit(idProduit);
        //System.out.println("id produit elli wsel :" + this.idProduit);
    }    
}
