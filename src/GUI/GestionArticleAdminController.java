/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Conseils;
import Services.ConseilsCRUD;
import Services.Mailing;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author yassine
 */
public class GestionArticleAdminController implements Initializable {

    private String imagePath;
    @FXML
    private ImageView ImageField;
    @FXML
    private Label imageLabel;
    @FXML
    private FlowPane FlowPaneArticle;
    @FXML
    private TextField TitreField;
    @FXML
    private TextArea DescriptionField;

    ConseilsCRUD ser = new ConseilsCRUD();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            affichageUS();
            ObservableList<Conseils> listComp = FXCollections.observableArrayList(ser.displayConseils());
            for (Conseils c : listComp) {
                System.out.println(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionArticleAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void affichageUS() throws SQLException {

        ObservableList<Conseils> listComp = FXCollections.observableArrayList(ser.displayConseils());

       // System.out.println("We're right here for now ");
        ArrayList<Separator> as = new ArrayList<>();
        ArrayList<VBox> vbx = new ArrayList<>();
        ArrayList<HBox> btnP = new ArrayList<>();

        for (int i = 0; i < listComp.size(); i++) {

            Separator h = new Separator(Orientation.VERTICAL);
            h.setPrefWidth(17);
            h.setPrefHeight(15);
            h.setVisible(false);
            as.add(h);

            VBox VBoxComp = new VBox();

            VBoxComp.setSpacing(5);
            VBoxComp.setStyle("-fx-background-color : #F1F1D3;");
            //VBoxComp.setStyle("-fx-border-color : #2B48E8;");
            VBoxComp.setAlignment(Pos.CENTER);
            VBoxComp.setPrefHeight(160);
            VBoxComp.setPrefWidth(210);
            //VBoxComp.setBorder(Border.EMPTY);
            HBox panne = new HBox();
            panne.setSpacing(5);
            panne.setStyle("-fx-background-color :  #F1F1D3;");
            //VBoxComp.setStyle("-fx-border-color : #2B48E8;");
            panne.setAlignment(Pos.CENTER);
            panne.setPrefHeight(10);
            panne.setPrefWidth(30);

            Rectangle c = new Rectangle(172, 120);

//                ImageView img = new ImageView(new Image(new FileInputStream("C:\\Users\\loume78\\Desktop\\Mon_cv\\loume.png")));
//                img.setFitHeight(10);
//                img.setFitWidth(20);
            try {
                c.setFill(new ImagePattern(new Image(new FileInputStream("C:/wamp64/www/ImagesPIDEV/" + listComp.get(i).getImage()))));
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }

            VBoxComp.getChildren().add(c);
            Label titreComp = new Label("Titre : " + listComp.get(i).getTitre_article());
            //System.out.println(titreComp.getText());
            Label description = new Label("Description: " + listComp.get(i).getDescription());
            //System.out.println(description.getText());

            //Label mail = new Label("Mail: " + listUs.get(i).getMail());
            //Label tel = new Label("téléphone: " + listUs.get(i).getNumTel());
            //Label etat = new Label();
            //Label adresse = new Label("Adresse: " + listUs.get(i).getAdresse().getVille() + "," + listUs.get(i).getAdresse().getPays());
            int idConseil = listComp.get(i).getId_article();
            String titre = listComp.get(i).getTitre_article();
            String descr = listComp.get(i).getDescription();
            String image = listComp.get(i).getImage();
            Conseils c1 = new Conseils(idConseil, titre, descr, image);

            

            Button btnSupp = new Button("Supp");
            btnSupp.setTextOverrun(OverrunStyle.CLIP);
            btnSupp.setStyle("-fx-background-color : #E82B34;");


            //btnSupp.setDisable(true);
            btnSupp.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    try {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        Button button2 = new Button();
                        button2.setStyle("-fx-background-color: #F54F4F");
                        alert.setTitle("Suppression ");
                        alert.setContentText("Voulez-vous vraiment supprimer cette Article ?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            ser.supprimerConseils(c1);
                            FlowPaneArticle.getChildren().clear();
                            affichageUS();

                        } else {
                            FlowPaneArticle.getChildren().clear();
                            affichageUS();

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            });

            Button btnDetail = new Button("...");

            String modifbtn1 = listComp.get(i).getTitre_article();
            int idArticle = listComp.get(i).getId_article();
            btnDetail.setStyle("-fx-background-color : #CFDBD1;");
            btnDetail.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

//                        Conseils c2 = ser.FindById(idArticle);
                    TitreField.setText(c1.getTitre_article());
                    DescriptionField.setText(c1.getDescription());

                }
            });

            Button modif = new Button("Modifier");
            int modifbtn = listComp.get(i).getId_article();
            modif.setStyle("-fx-background-color : #60A9C4;");
            modif.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    //System.out.println("Clicked btn Suppri;er");
                    String titre = TitreField.getText();
                    String description = DescriptionField.getText();
//                    String image = DescriptionField.getText();

                    Conseils cn = new Conseils(titre, description,imagePath);

                    try {

                        if (ser.updateConseils(cn, modifbtn) == true) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Modification avec succés !", ButtonType.OK);
                            alert.show();
                            FlowPaneArticle.getChildren().clear();
                            affichageUS();

                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Echec de modification  !", ButtonType.OK);
                            alert.show();
                            FlowPaneArticle.getChildren().clear();
                            affichageUS();

                        }

                        // ser.update(Titre, Description, TypeTalent, DateD, DateFe, Cout,modif );
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionArticleAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            VBoxComp.getChildren().add(titreComp);
            VBoxComp.getChildren().add(description);

            panne.getChildren().add(btnSupp);
            panne.getChildren().add(modif);
            panne.getChildren().add(btnDetail);
            VBoxComp.getChildren().add(panne);

            vbx.add(VBoxComp);
            //btnP.add(panne);
            FlowPaneArticle.getChildren().add(vbx.get(i));

            FlowPaneArticle.getChildren().add(as.get(i));

            if (i == 0) {
                System.out.println("i=0 llllll");
            } else if (((i + 1) % 2) == 0) {
                Separator sepHoriz = new Separator(Orientation.HORIZONTAL);
                sepHoriz.setPrefWidth(364);
                sepHoriz.setPrefHeight(30);
                sepHoriz.setVisible(false);
                FlowPaneArticle.getChildren().add(sepHoriz);

            }
        }
    }

    public void Ajouter(ActionEvent event) throws IOException, SQLException {
        String titre = TitreField.getText();
        String desc = DescriptionField.getText();
        

        Conseils c = new Conseils(titre, desc,imagePath);
        ser.ajouterConseils(c);
        try {
            System.err.println("email is sending ");
            Mailing.mailing("chayma.ouni@esprit.tn");
            Mailing.mailing("hazar.agili@esprit.tn");
            System.err.println("email sent  ");
        } catch (Exception ex) {
            Logger.getLogger(GestionArticleAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FlowPaneArticle.getChildren().clear();
        affichageUS();

    }
    
    public void Upload(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        //fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", listFichier));
        File f = fc.showOpenDialog(null);
        if (f != null) {

            //Commentaire.setText("Image selectionnée" + f.getAbsolutePath());
            InputStream is = null;
            OutputStream os = null;
            try {
                is = new FileInputStream(new File(f.getAbsolutePath()));
//             
                os = new FileOutputStream(new File("C:/wamp64/www/ImagesPIDEV/" + f.getName()));
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                System.out.println("chayma");

            } finally {
                is.close();
                os.close();

            }

            File file = new File("C:/wamp64/www/ImagesPIDEV/" + f.getName());
//            System.out.println(file.toURI().toString());
            ImageField.setImage(new Image(file.toURI().toString()));
            
            imagePath = f.getName();
            System.out.println(imagePath);
            imageLabel.setText(imagePath);
        } else if (f == null) {
            //Commentaire.setText("Erreur chargement de l'image");
            System.out.println("Erreur !");
        }
    }

}
