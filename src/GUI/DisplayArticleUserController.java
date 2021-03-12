/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Conseils;
import Services.CommentaireCRUD;
import Services.ConseilsCRUD;
import Services.ReactionCRUD;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yassine
 */
public class DisplayArticleUserController implements Initializable {

    static String titre1;
    static String description1;
    static String image1;
    static int id1;
    static Conseils conseill = new Conseils(id1, titre1, description1, image1);
    @FXML
    public FlowPane FlowPaneArticle;
    ConseilsCRUD ser = new ConseilsCRUD();
    CommentaireCRUD comm = new CommentaireCRUD();
    ReactionCRUD rc = new ReactionCRUD();
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
    
    public void affichageUS() throws SQLException {

        ObservableList<Conseils> listComp = FXCollections.observableArrayList(ser.displayConseils());

        System.out.println("We're right here for now ");
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
            
             String Titre = listComp.get(i).getTitre_article();
             String Description = listComp.get(i).getDescription();
             int id = listComp.get(i).getId_article();
             String Image = listComp.get(i).getImage();
             
             
            VBoxComp.setPrefHeight(160);
            VBoxComp.setPrefWidth(130);
            VBoxComp.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
//                        System.exit(0);
                        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("Details.fxml"));
                        Parent rootDetails = LOADER.load();
                        DetailsController detail = LOADER.getController();
                        detail.setDetails(id, Description, Titre, Image);
                        titre1=Titre;
                        description1=Description;
                        image1=Image;
                        id1=id;
                        try {
                            detail.affichageUS(id, Description, Titre, Image);
                        } catch (SQLException ex) {
                            Logger.getLogger(DisplayArticleUserController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        Scene scene = new Scene(rootDetails, 600, 552);

                        Stage primaryStage = new Stage();
                        primaryStage.setScene(scene);
                        primaryStage.show();

                    } catch (IOException ex) {

                    }
                }
            });
            HBox panne = new HBox();
            panne.setSpacing(5);
            panne.setStyle("-fx-background-color :  #F1F1D3;");
            //VBoxComp.setStyle("-fx-border-color : #2B48E8;");
            panne.setAlignment(Pos.CENTER);
            panne.setPrefHeight(10);
            panne.setPrefWidth(30);
            
             HBox reactions = new HBox();
            reactions.setSpacing(5);
            reactions.setStyle("-fx-background-color :  #F1F1D3;");
            //VBoxComp.setStyle("-fx-border-color : #2B48E8;");
            reactions.setAlignment(Pos.CENTER);
            reactions.setPrefHeight(10);
            reactions.setPrefWidth(30);
            reactions.setAlignment(Pos.CENTER);
            
            
            Rectangle comment = new Rectangle(19, 19);

            try {
                comment.setFill(new ImagePattern(new Image(new FileInputStream("C:/Users/yassine/Desktop/PI3A3/src/assets/comment2.png"))));
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
            int idConseil = listComp.get(i).getId_article();
            String titre = listComp.get(i).getTitre_article();
            String descr = listComp.get(i).getDescription();
            String image = listComp.get(i).getImage();
            Conseils c1 = new Conseils(idConseil, titre, descr, image);
            
            Rectangle reaction = new Rectangle(35, 35);

            try {
                reaction.setFill(new ImagePattern(new Image(new FileInputStream("C:/Users/yassine/Desktop/PI3A3/src/assets/reqctions.png"))));
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
            Label cm = new Label("  " + comm.NumberOfComments(c1));          
            Label r = new Label("  " + rc.NumberReacts(idConseil));

            
            reactions.getChildren().add(cm);
            reactions.getChildren().add(comment);
           reactions.getChildren().add(r);
            reactions.getChildren().add(reaction);

            Rectangle c = new Rectangle(172, 120);

            try {
                c.setFill(new ImagePattern(new Image(new FileInputStream("C:/wamp64/www/ImagesPIDEV/" + listComp.get(i).getImage()))));
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
            
            
            

            VBoxComp.getChildren().add(c);
            Label titreComp = new Label("Titre : " + listComp.get(i).getTitre_article());
            System.out.println(titreComp.getText());
            Label description = new Label("Description: " + listComp.get(i).getDescription());
            System.out.println(description.getText());



           

            VBoxComp.getChildren().add(titreComp);
            VBoxComp.getChildren().add(description);


            VBoxComp.getChildren().add(reactions);
            VBoxComp.getChildren().add(panne);
            


            vbx.add(VBoxComp);
            btnP.add(panne);
            FlowPaneArticle.getChildren().add(vbx.get(i));

            FlowPaneArticle.getChildren().add(as.get(i));

            if (i == 0) {
                System.out.println("i=0 llllll");
            } else if (((i + 1) % 3) == 0) {
                Separator sepHoriz = new Separator(Orientation.HORIZONTAL);
                sepHoriz.setPrefWidth(10);
                sepHoriz.setPrefHeight(30);
                sepHoriz.setVisible(true);
                FlowPaneArticle.getChildren().add(sepHoriz);

            }
        }
    }
    
}
