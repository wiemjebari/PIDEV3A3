/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.views;

import gestionproduit.entity.User;
import gestionproduit.services.UserServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author wiemj
 */
public class AcceuilFXMLController implements Initializable {
    
     @FXML
    private AnchorPane mainwindow;

    @FXML
    public AnchorPane window;

    @FXML
    private ImageView fb;

    @FXML
    private ImageView insta;

    @FXML
    private ImageView twit;

    @FXML
    private AnchorPane barre;

    @FXML
    private ImageView profile;

    @FXML
    private Label username;

    @FXML
    private ImageView notification;

    @FXML
    private Label email;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private ImageView logout;

    @FXML
    private ImageView chat;

    @FXML
    private ImageView panier;

    @FXML
    private ImageView close;

    @FXML
    private AnchorPane admin_window;

    @FXML
    private Label nom1;

    @FXML
    private Label achat_livrer;

    @FXML
    private Label achat_payer;

    @FXML
    private Label achat_nonpayer;

    @FXML
    private Label nom11;

    @FXML
    private Label adoption;

    @FXML
    private Label animal_perdu;

    @FXML
    private Label animal_trouve;

    @FXML
    private Label training;

    @FXML
    private Label walking;

    @FXML
    private Label accouplement;

    @FXML
    private Label nom111;

    @FXML
    private Label nbr_Reclamation;

    @FXML
    private Label nbr_veterinaire;

    @FXML
    private Label nbr_conseille;

    @FXML
    private Label nbr_produit;

    @FXML
    private Label nbr_produit_out;

    @FXML
    private Label nbr_utilisateur;

    @FXML
    private JFXDrawer drawer;
    @FXML
    private StackPane stackpane;
    @FXML
    private StackPane stackConfirmation;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AnchorPane test = (AnchorPane) drawer.getParent();
        UserServices us = UserServices.getInstance();
        User u = us.AfficherUserId(User.getUserconnected());
        username.setText(u.getUsername());
        if (us.verifAdmin(u.getUsername())) {
            email.setText(u.getEmail());
            profile.setMouseTransparent(true);
        } else {
            profile.setMouseTransparent(false);

            email.setText(u.getEmail()); 

        }
       if (us.verifAdmin(u.getUsername())) {
            try {
                AnchorPane box = FXMLLoader.load(getClass().getResource("MenuAdmin.fxml"));
                drawer.setSidePane(box);
                drawer.setOverLayVisible(false);
                for (Node node : box.getChildren()) {
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                        if (node.getAccessibleText() != null) {
                            switch (node.getAccessibleText()) {
                                case "guser": {
                                    try {
                                        loadSplashScreen("MembreAdmin.fxml");
                                        break;
                                    } catch (Exception ex) {
                                        Logger.getLogger(AcceuilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                case "gprest": {
                                    try {
                                        loadSplashScreen("gererPrestation.fxml");
                                        break;
                                    } catch (Exception ex) {
                                        Logger.getLogger(AcceuilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                           
                                case "grec": {
                                    try {
                                        loadSplashScreen("AfficheRecommendationAdmin.fxml");
                                        break;
                                    } catch (Exception ex) {
                                        Logger.getLogger(AcceuilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                case "gproduit": {
                                    try {
                                        loadSplashScreen("BackProduit.fxml");
                                        break;
                                    } catch (Exception ex) {
                                        Logger.getLogger(AcceuilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                case "gevenement": {
                                    try {
                                        loadSplashScreen("EventBack.fxml");
                                        break;
                                    } catch (Exception ex) {
                                        Logger.getLogger(AcceuilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }
                    });
                }
            } catch (IOException ex) {
               // Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
            transition.setRate(-1);
            window.setOnMouseClicked(e -> {
                if (drawer.isHidden() || drawer.isHiding()) {
                } else {

                    transition.setRate(transition.getRate() * -1);
                    transition.play();
                    drawer.toggle();
                                        test.getChildren().remove(drawer);

                }
            });
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();

                //System.out.println("555555");
                if (drawer.isShown()) {
                    test.getChildren().remove(drawer);
                    drawer.close();
                } else {
                    test.getChildren().remove(drawer);
                    test.getChildren().add(drawer);
                    drawer.open();
                }
            });
        } else {
            try {
                AnchorPane box = FXMLLoader.load(getClass().getResource("FXMLMenu.fxml"));
                drawer.setSidePane(box);
                drawer.setOverLayVisible(false);

                for (Node node : box.getChildren()) {
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                        if (node.getAccessibleText() != null) {
                            switch (node.getAccessibleText()) {
                                case "top_rec": {
                                    try {
                                        loadSplashScreen("AfficherTopRecommendation.fxml");
                                        break;
                                    } catch (Exception ex) {
                                        Logger.getLogger(AcceuilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                case "evenement": {
                                    try {
                                        loadSplashScreen("test.fxml");
                                        drawer.toggle();

                                        test.getChildren().remove(drawer);
                                        break;
                                    } catch (Exception ex) {
                                        Logger.getLogger(AcceuilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                case "prestation": {
                                    try {
                                        loadSplashScreen("listerPrestation.fxml");
                                        break;
                                    } catch (Exception ex) {
                                        Logger.getLogger(AcceuilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                              
                                
                                case "produit": {
                                    try {
                                        loadSplashScreen("AcceuilProduit.fxml");
                                        break;
                                    } catch (Exception ex) {
                                        Logger.getLogger(AcceuilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }
                    });
                }
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
            transition.setRate(-1);
            window.setOnMouseClicked(e -> {
                if (drawer.isHidden() || drawer.isHiding()) {
                } else {

                    transition.setRate(transition.getRate() * -1);
                    transition.play();
                    drawer.toggle();
                    test.getChildren().remove(drawer);
                }
            });
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();
                //test.getChildren().add(drawer);

               if (drawer.isShown()) {
                    test.getChildren().remove(drawer);
                    drawer.close();
                } else {
                    test.getChildren().remove(drawer);
                    test.getChildren().add(drawer);
                    drawer.open();
                }
            }); }}

        
            

    public void loadSplashScreen(String location) {
        try {

            StackPane pane = FXMLLoader.load(getClass().getResource("FXMLSplash.fxml"));
            window.getChildren().setAll(pane);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            fadeOut.setOnFinished((e) -> {
                try {
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource((location)));
                    window.getChildren().setAll(parentContent);

                } catch (IOException ex) {
                    System.out.println(ex);
                }
            });

        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    @FXML
    private void logoutAction(MouseEvent event) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Déconnexion"));
        content.setBody(new Text("Êtes-vous sûr de vouloir vous déconnecter ?"));

        JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.TOP);

        JFXButton oui = new JFXButton("Se déconnecter");
        oui.setOnAction((e) -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("LoginUser.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(AcceuilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
          //  LoginUserFXMLController cnt = loader.getController();

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
            closeStage();

            User.setUserconnected(0);

        });

        JFXButton non = new JFXButton("Annuler");
        non.setOnAction((e) -> {
            dialog.close();
            stackpane.setMouseTransparent(true);
        });

        stackpane.setMouseTransparent(false);
        content.setActions(oui, non);
        dialog.show();
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    private void closeStage() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void profile(MouseEvent event) throws IOException {
        loadSplashScreen("AfficheProfile.fxml");

    }

}
