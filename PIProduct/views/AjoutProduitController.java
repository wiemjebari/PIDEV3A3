/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.views;

import gestionproduit.entity.FTTS;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import gestionproduit.entity.MyNotifications;
import gestionproduit.entity.Produit;
import gestionproduit.entity.Upload;
import gestionproduit.entity.User;
import gestionproduit.services.ProduitService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author wiemj
 */
public class AjoutProduitController extends AcceuilProduitController {

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
    
    private File file;
    private File file1 = new File("");
    String vid;
    private Upload up;
    String pic;
    private FileChooser.ExtensionFilter extFilterJPG;
    private FileChooser.ExtensionFilter extFilterjpg;
    private Image image;
    @FXML
    private Label lbvideo;
    @FXML
    private JFXButton btvideo;
    public static Produit pp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         choixInsertion.getItems().setAll("Café", "restaurant", "supermarché","Café");
         choixInsertion.setValue("Café");

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
                    boolean ajoute = true;
                    Produit p = new Produit();
                    if (file1.isFile()) {
                        try {

                            vid = up.upload(file1, "video");

                        } catch (IOException ex) {
                            Logger.getLogger(AjoutProduit2Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    as.ajouterProduit(new Produit(
                            NomInsertion.getText(),
                            User.getUserconnected(),
                            descriptionInsertion.getText(),
                            Float.parseFloat(PrixInsertion.getText()),
                            Integer.parseInt(stockInsertion.getText()),
                            choixInsertion.getValue(),
                            pic
                    ));

                    String desc = descriptionInsertion.getText().toString();
                    FTTS FTTSProduit = new FTTS(desc);
                    //FTTSProduit.speak();

                    choixInsertion.setValue("Café");
                    descriptionInsertion.setText("");

                    NomInsertion.setText("");
                    PrixInsertion.setText("");
                    stockInsertion.setText("");

                    TrayNotification tray = new TrayNotification("Notification !", "Produit ajoutée avec succée", NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.seconds(6));
                }
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherAllProduit.fxml"));

                loader.load();
                AnchorPane parentContent = loader.getRoot();
                window = (AnchorPane) nom11.getParent().getParent();
                AfficherAllProduitController cont = loader.getController();

                window.getChildren().setAll(parentContent);

            }

        }
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
    void uploadvideo(ActionEvent event) {
        FileInputStream input = null;
        FileChooser fileChooser = new FileChooser();
        //Set extension filter
        extFilterJPG
                = new FileChooser.ExtensionFilter("mp4 files (*.MP4)", "*.mp4");
        extFilterjpg
                = new FileChooser.ExtensionFilter("mkv files (*.MKV)", "*.mkv");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg);
        up = new Upload();
        file1 = fileChooser.showOpenDialog(null);
        lbvideo.setText(file1.getPath());
        try {
            input = new FileInputStream(file1.getPath());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean isInteger(JFXTextField input) {
        try {
            int a;
            a = Integer.parseInt(input.getText());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFloat(JFXTextField input) {
        try {
            float a = Float.parseFloat(input.getText());
            if (a < 0) {
                System.out.println("le stock ajouter est négative");
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

