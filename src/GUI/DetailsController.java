/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Commentaire;
import Entities.Conseils;
import Entities.Reaction;
import Entities.User;
import static GUI.DisplayArticleUserController.conseill;
import static GUI.DisplayArticleUserController.description1;
import static GUI.DisplayArticleUserController.id1;
import static GUI.DisplayArticleUserController.image1;
import static GUI.DisplayArticleUserController.titre1;
import Services.CommentaireCRUD;
import Services.ConseilsCRUD;
import Services.ReactionCRUD;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

/**
 * FXML Controller class
 *
 * @author yassine
 */
public class DetailsController implements Initializable {

    @FXML
    private ImageView ImageField;
    @FXML
    private TextArea DescriptionField;
    @FXML
    private Text TitreField;
    @FXML
    private FlowPane FlowPaneComment;
    CommentaireCRUD cr = new CommentaireCRUD();
    CommentaireCRUD comm = new CommentaireCRUD();
    ReactionCRUD rc = new ReactionCRUD();
    ConseilsCRUD tt = new ConseilsCRUD();
    //User userConnected = new User(1, "chayma", "ouni", "chayma.ouni@esprit.tn", "chayma123");
    User userConnected = new User(2, "esprit", "ESPRIT", "esprit@esprit.tn", "esprit123");
    @FXML
    private ImageView LikeImage;
    @FXML
    private ImageView DisLlikeImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //affichageUS(id1,titre1,description1,image1);

            ObservableList<Commentaire> listComp = FXCollections.observableArrayList(cr.displayCommentaire(conseill));
            for (Commentaire c : listComp) {
                System.out.println(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionArticleAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setFILES(String Body, String Body1, String Body2, String Body3, String path) {
        try {
            Random rand = new Random();

// Obtain a number between [0 - 49].
            int n = rand.nextInt(50);
            OutputStream file = new FileOutputStream(new File("Article" + n + ".pdf"));

            Document document = new Document();

            PdfWriter.getInstance(document, file);

            document.open();
            document.addTitle("Article");

            com.itextpdf.text.Image img;
            img = com.itextpdf.text.Image.getInstance(path);
            com.itextpdf.text.Image.getInstance(img);
            document.add(new Paragraph(Body));
            document.add(new Paragraph("                    "));
            document.add(new Paragraph("                    "));
            document.add(img);

            document.add(new Paragraph("                    "));
            document.add(new Paragraph("                    "));

            document.add(new Paragraph(Body1));
            document.add(new Paragraph(Body2));
            document.add(new Paragraph(Body3));
            document.close();
            file.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public void btnPDF(String Body, String Body1, String Body2, String Body3, String path) throws IOException, SQLException {
        //User userTest = new User(102, "Louay", "Yahyaoui", "louay@esprit.tn", "louay", "oussema", "male", "28-08-1992", 234223878, "SimpleUtilisateur", "Comedie", "hahaha", 5000);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Button button2 = new Button();
        button2.setStyle("-fx-background-color: #00ff00");
        alert.setTitle("PDF ");
        alert.setContentText("Bonjour Mr/Mme !  vous voulez exporter Cette Article en PDF ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            setFILES(Body, Body1, Body2, Body3, path);

        } else {

        }
    }

    public void setTrancaparncy(int id1) {
        try {
            if (rc.FindReaction(id1, userConnected.getIdUser()) == 1) {
                LikeImage.setOpacity(0.33);
                System.out.println("Like");
            } else if (rc.FindReaction(id1, userConnected.getIdUser()) == 2) {
                DisLlikeImage.setOpacity(0.33);
                System.out.println("Dislike");
            } else {
                System.out.println("no one");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setDetails(int id, String Description, String Titre, String Image) {

        setTrancaparncy(id);
        TitreField.setText(Titre);
        DescriptionField.setText(Description);
        File file = new File("C:/wamp64/www/ImagesPIDEV/" + Image);

        ImageField.setImage(new Image(file.toURI().toString()));

    }

    public void affichageUS(int id, String titre, String description, String image) throws SQLException {
        Conseils conseil = new Conseils(id, titre, description, image);
        ObservableList<Commentaire> listComp = FXCollections.observableArrayList(cr.displayCommentaire(conseil));

        System.out.println("We're right here for now ");
//        ArrayList<Separator> as = new ArrayList<>();
//        ArrayList<VBox> vbx = new ArrayList<>();
        VBox VBoxComp = new VBox();
        VBoxComp.setSpacing(5);
        VBoxComp.setStyle("-fx-background-color : #F1F1D3;");
        //VBoxComp.setStyle("-fx-border-color : #2B48E8;");
        VBoxComp.setAlignment(Pos.CENTER);
        VBoxComp.setPrefHeight(160);
        VBoxComp.setPrefWidth(190);
        VBoxComp.setBorder(Border.EMPTY);

        ArrayList<HBox> btnP = new ArrayList<>();

        for (int i = 0; i < listComp.size(); i++) {

            HBox panne = new HBox();
            panne.setSpacing(5);
            panne.setStyle("-fx-background-color :  #F1F1D3;");
            //VBoxComp.setStyle("-fx-border-color : #2B48E8;");
            panne.setAlignment(Pos.CENTER);
            panne.setPrefHeight(30);
            panne.setPrefWidth(550);

            Rectangle c = new Rectangle(20, 25);
            try {
                String imageUser = "avatar3.jpg";
                c.setFill(new ImagePattern(new Image(new FileInputStream("C:/wamp64/www/ImagesPIDEV/" + imageUser))));
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }

            panne.getChildren().add(c);

            TextField content = new TextField("    " + listComp.get(i).getContenu());
            content.setFont(Font.font("Aharoni", 15));
            content.setEditable(false);

            System.out.println(content.getText());
            Label date = new Label("     " + listComp.get(i).getDateCreation());

            System.out.println(date.getText());
            System.err.println(listComp.get(i).getUser().getIdUser());
            System.err.println("focuuuuuuuuuuuuuuuusssssssssss");
            System.err.println(userConnected.getIdUser());

//            int idConseil = listComp.get(i).getId_article();
//            String titre = listComp.get(i).getTitre_article();
//            String descr = listComp.get(i).getDescription();
//            String image = listComp.get(i).getImage();
//            Conseils c1 = new Conseils(idConseil, titre, descr, image);
            panne.getChildren().add(content);
            panne.getChildren().add(date);
            panne.setSpacing(20);
            int idUser = listComp.get(i).getUser().getIdUser();
            int idComm = listComp.get(i).getId_commentaire();
            int idArticle = listComp.get(i).getConseil().getId_article();
            String contenue = listComp.get(i).getContenu();
            Date dateC = listComp.get(i).getDateCreation();
            Commentaire commentaire = new Commentaire(userConnected, conseil, idComm, contenue, dateC);

            if (listComp.get(i).getUser().getIdUser() == userConnected.getIdUser()) {

                content.setEditable(true);

                Rectangle delete = new Rectangle(20, 25);
                try {

                    delete.setFill(new ImagePattern(new Image(new FileInputStream("C:/Users/yassine/Desktop/PI3A3/src/assets/delete.png"))));
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }

                Rectangle edit = new Rectangle(20, 25);
                try {

                    edit.setFill(new ImagePattern(new Image(new FileInputStream("C:/Users/yassine/Desktop/PI3A3/src/assets/edit.png"))));
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }

                delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            comm.supprimerCommentaire(commentaire);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);

                            alert.setTitle("Suppression ");
                            alert.setContentText("Votre commentaire a ete bien supprimer ");
                            alert.show();
                        } catch (SQLException ex) {
                            Logger.getLogger(DetailsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        FlowPaneComment.getChildren().clear();
                        try {
                            affichageUS(id1, titre1, description1, image1);
                        } catch (SQLException ex) {
                            Logger.getLogger(DetailsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

                edit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            commentaire.setContenu(content.getText());
                            comm.updateCommentaire(commentaire, commentaire.getId_commentaire());
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Modification ");
                            alert.setContentText("Votre commentaire a ete bien Modifie ");
                            alert.show();
                        } catch (SQLException ex) {
                            Logger.getLogger(DetailsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        FlowPaneComment.getChildren().clear();
                        try {
                            affichageUS(id1, titre1, description1, image1);
                        } catch (SQLException ex) {
                            Logger.getLogger(DetailsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

                panne.getChildren().add(delete);
                panne.getChildren().add(edit);

            }

            btnP.add(panne);

//            VBoxComp.getChildren().add(btnP.get(i));
            FlowPaneComment.getChildren().add(btnP.get(i));

//            if (i == 0) {
//                System.out.println("i=0 llllll");
//            } else if (((i + 1) % 2) == 0) {
//                Separator sepHoriz = new Separator(Orientation.HORIZONTAL);
//                sepHoriz.setPrefWidth(364);
//                sepHoriz.setPrefHeight(30);
//                sepHoriz.setVisible(false);
//                FlowPaneArticle.getChildren().add(sepHoriz);
//
//            }
        }
        HBox addComment = new HBox();
        addComment.setSpacing(5);
        addComment.setStyle("-fx-background-color :  #F1F1D3;");
        //VBoxComp.setStyle("-fx-border-color : #2B48E8;");
        addComment.setAlignment(Pos.CENTER);
        addComment.setPrefHeight(30);
        addComment.setPrefWidth(550);

        Rectangle img = new Rectangle(20, 25);
        try {
            String imageUser = "avatar2.jpg";
            img.setFill(new ImagePattern(new Image(new FileInputStream("C:/wamp64/www/ImagesPIDEV/" + imageUser))));
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        addComment.getChildren().add(img);

        TextField reply = new TextField();
        reply.setPromptText("votre commentaire ici ...");
        addComment.getChildren().add(reply);

        Button ajouterComment = new Button("OK");
        ajouterComment.setStyle("-fx-background-color :  #7DC2A5;");
        ajouterComment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

//                  
                String content = reply.getText();
                int idArticle = id1;
                System.err.println(id1);
                User u = new User();
                try {
                    Conseils art = tt.FindById(id1);

                    long millis = System.currentTimeMillis();
                    java.sql.Date date = new java.sql.Date(millis);

                    Commentaire comentaire = new Commentaire(userConnected, art, 1, content, date);

                    comm.ajouterCommentaire(comentaire);

                } catch (SQLException ex) {
                    Logger.getLogger(DetailsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                FlowPaneComment.getChildren().clear();
                try {
                    affichageUS(id1, titre1, description1, image1);
                } catch (SQLException ex) {
                    Logger.getLogger(DetailsController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        addComment.getChildren().add(ajouterComment);
        FlowPaneComment.getChildren().add(addComment);
    }

    @FXML
    private void AddLike(MouseEvent event) {

        Conseils c = new Conseils();

        try {

            c = tt.FindById(id1);
            if (rc.FindReaction(id1, userConnected.getIdUser()) == 1) {
//                LikeImage.setOpacity(0.33);
                //System.out.println("nothing");
            } else if (rc.FindReaction(id1, userConnected.getIdUser()) == 2) {
//                DisLlikeImage.setOpacity(0.33);
                Reaction rec = new Reaction();
                
                rec = rc.FindById(id1, userConnected.getIdUser());
                //System.err.println(rec);
                rc.supprimerReaction(rec);
                Reaction react = new Reaction(userConnected, c, 1);
                rc.ajouterReaction(react);
                setTrancaparncy(id1);

            } else {
                Reaction react = new Reaction(userConnected, c, 1);
                rc.ajouterReaction(react);
                setTrancaparncy(id1);

            }

        } catch (SQLException ex) {
            Logger.getLogger(DetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTrancaparncy(id1);

    }

    @FXML
    private void AddDislike(MouseEvent event) {
        Conseils c = new Conseils();

        try {

            c = tt.FindById(id1);
            if (rc.FindReaction(id1, userConnected.getIdUser()) == 1) {
//                LikeImage.setOpacity(0.33);
                Reaction rec = new Reaction();
                rec = rc.FindById(id1, userConnected.getIdUser());
                System.err.println(id1);
                System.err.println(rec);
                rc.supprimerReaction(rec);
                System.out.println("deleted ////// ");
                Reaction react = new Reaction(userConnected, c, 0);
                rc.ajouterReaction(react);
                setTrancaparncy(id1);

            } else if (rc.FindReaction(id1, userConnected.getIdUser()) == 2) {
//                DisLlikeImage.setOpacity(0.33);
                System.out.println("nothing");
            } else {
                Reaction react = new Reaction(userConnected, c, 0);
                rc.ajouterReaction(react);
                setTrancaparncy(id1);

            }

        } catch (SQLException ex) {
            Logger.getLogger(DetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTrancaparncy(id1);
    }

    @FXML
    private void TelechargerPDF(MouseEvent event) {
        String path = ("C:/wamp64/www/ImagesPIDEV/" + image1);
        String Body = titre1;
        String Body1 = description1;
        String Body2 = "         ";
        String Body3 = "          ";
        try {
            btnPDF(Body, Body1, Body2, Body3, path);
        } catch (IOException ex) {
            Logger.getLogger(DetailsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
