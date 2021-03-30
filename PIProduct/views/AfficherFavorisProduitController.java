/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.views;

import com.jfoenix.controls.JFXTextField;
import gestionproduit.entity.Favoris;
import gestionproduit.entity.Produit;
import gestionproduit.entity.User;
import gestionproduit.services.FavoriService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author wiemj
 */
public class AfficherFavorisProduitController extends AcceuilProduitController {

    @FXML
    private AnchorPane winodow;
    
    @FXML
    private Pagination paginator;
    @FXML
    private AnchorPane box;
    @FXML
    private ImageView image;
    @FXML
    private Label categorie;
    @FXML
    private Label nom;
    @FXML
    private Text prix;
    @FXML
    private Label stock;
    @FXML
    private Label description;
    @FXML
    private AnchorPane box2;
    @FXML
    private ImageView image2;
    @FXML
    private Label categorie2;
    @FXML
    private Label nom2;
    @FXML
    private Text prix2;
    @FXML
    private Label stock2;
    @FXML
    private Label description2;
    List<Favoris> liste = new ArrayList<>();
    Favoris p = new Favoris();
    private ObservableList<Favoris> data = FXCollections.observableArrayList();
    @FXML
    private AnchorPane details;
    @FXML
    private ImageView photo;
    @FXML
    private Text descriptionD;
    @FXML
    private Label categorieD;
    @FXML
    private Label nomD;
    @FXML
    private Label stockD;
    @FXML
    private Label prixD;
    private Button commander;
    private List<Favoris> all_articles;
    FavoriService produitservice = new FavoriService();
    @FXML
    private AnchorPane box1;
    @FXML
    private ImageView image1;
    @FXML
    private Label categorie1;
    @FXML
    private Label nom1;
    @FXML
    private Text prix1;
    @FXML
    private Text stock1;
    @FXML
    private Label description1;
    @FXML
    private Label idp1;
    Favoris f = new Favoris();
    int id;
    @FXML
    private JFXTextField recherche;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        details.setVisible(false);

        FavoriService es = new FavoriService();

       liste = es.consulterProduitByIdOwner(User.getUserconnected());
        if (liste.isEmpty()) {
            box.setVisible(false);
            box2.setVisible(false);
            //ide.setVisible(true);
            paginator.setVisible(false);
        } else {
            paginator.setVisible(true);
            //vide.setVisible(false);
            setNbPages();
            initAnnoncePage(0);
        }
    }

    private void setNbPages() {

        if (liste.size() % 3 != 0) {
            paginator.setPageCount((liste.size() / 3) + 1);
        } else {
            paginator.setPageCount(liste.size() / 3);
        }

        paginator.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            initAnnoncePage(newIndex.intValue());
        });
    }

    private void initAnnoncePage(int i) {
        FavoriService es = new FavoriService();

        paginator.setCurrentPageIndex(i);
        List<Favoris> TroisAnnonces = getAnnoncesPage(i);

        if (TroisAnnonces.size() >= 1) {
            box.setVisible(true);
            // System.out.println("    858585858"+TroisAnnonces.get(0).getPhotoProduit());

            //System.out.println("    lololololo"+TroisAnnonces.get(1).getPhotoProduit());
            Image img = new Image("http://localhost/uploadsimg/" + TroisAnnonces.get(0).getProduit().getPhotoProduit());

            image.setImage(img);
            categorie.setText(TroisAnnonces.get(0).getProduit().getCategorieProduit());
            idp1.setText(Integer.toString((int) TroisAnnonces.get(0).getProduit().getIdProduit()));
            System.out.println("  mmmmmmm" + idp1.getText().toString());
            nom.setText(TroisAnnonces.get(0).getProduit().getNomProduit());
            prix.setText(Integer.toString((int) TroisAnnonces.get(0).getProduit().getPrixProduit()) + " DT");
            stock.setText(Integer.toString(TroisAnnonces.get(0).getProduit().getStockProduit()));

            description.setText(TroisAnnonces.get(0).getProduit().getDescriptionProduit());
            box.setOnMouseClicked((MouseEvent e) -> {
                initialiserDetails(p = TroisAnnonces.get(0));
                details.setVisible(true);
                box2.setVisible(false);
                box.setVisible(false);
                box1.setVisible(false);
            });

        } else {
            box.setVisible(false);
        }
        if (TroisAnnonces.size() >= 2) {
            box1.setVisible(true);
            //System.out.println("    858585858"+TroisAnnonces.get(1).getPhotoProduit());

            //System.out.println("    lololololo"+TroisAnnonces.get(1).getPhotoProduit());
            Image img = new Image("http://localhost/uploadsimg/" + TroisAnnonces.get(1).getProduit().getPhotoProduit());

            image1.setImage(img);
            categorie1.setText(TroisAnnonces.get(1).getProduit().getCategorieProduit());

            nom1.setText(TroisAnnonces.get(1).getProduit().getNomProduit());
            prix1.setText(Integer.toString((int) TroisAnnonces.get(1).getProduit().getPrixProduit()) + " DT");
            stock1.setText(Integer.toString(TroisAnnonces.get(1).getProduit().getStockProduit()));

            description1.setText(TroisAnnonces.get(1).getProduit().getDescriptionProduit());
            box1.setOnMouseClicked((MouseEvent e) -> {
                initialiserDetails(p = TroisAnnonces.get(1));
                details.setVisible(true);
                box.setVisible(false);
                box2.setVisible(false);
                box1.setVisible(false);
            });

        } else {
            box1.setVisible(false);
        }

        if (TroisAnnonces.size() >= 3) {
           // System.out.println("    hhhhh" + TroisAnnonces.get(2).getPrixProduit());
            box2.setVisible(true);
            Image img = new Image("http://localhost/uploadsimg/" + TroisAnnonces.get(2).getProduit().getPhotoProduit());
            image2.setImage(img);
            categorie2.setText(TroisAnnonces.get(2).getProduit().getCategorieProduit());

            nom2.setText(TroisAnnonces.get(2).getProduit().getNomProduit());
            prix2.setText(Integer.toString((int) TroisAnnonces.get(2).getProduit().getPrixProduit()) + " DT");
            stock2.setText(Integer.toString(TroisAnnonces.get(2).getProduit().getStockProduit()));
            description2.setText(TroisAnnonces.get(2).getProduit().getDescriptionProduit());
            box2.setOnMouseClicked((MouseEvent e) -> {
                initialiserDetails(p = TroisAnnonces.get(2));

                details.setVisible(true);
                box.setVisible(false);
                box2.setVisible(false);
                box1.setVisible(false);
            });
            // System.out.println("deeetaail ");

        } else {
            box2.setVisible(false);
        }

    }

    private List<Favoris> getAnnoncesPage(int i) {
        int start = 3 * i;
        int fin = start + 3;
        if (liste.size() > start) {
            if (liste.size() > fin) {
                return liste.subList(start, fin);
            } else {
                return liste.subList(start, liste.size());
            }
        }
        return liste.subList(0, 2);
    }

   
 private void sendidproduit() throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateProduit.fxml")) ;
    Parent root = (Parent) fxmlLoader.load();
            UpdateProduitController controller = fxmlLoader.<UpdateProduitController>getController();
    controller.initData(p.getProduit().getIdProduit());
          //  System.out.println(p.getIdProduit()+" **********************afficher");
            
        } catch (Exception e) {
            Logger.getLogger(UpdateProduitController.class.getName()).log(Level.SEVERE, null,e);
        }

    
    }
   

    private void initialiserDetails(Favoris p) {
        /*if(User.getUserconnected()==p.getIdOwer())
                commander.setVisible(false);*/
        if (p.getProduit().getStockProduit()== 0) {
            commander.setDisable(true);

        } else {
            commander.setDisable(false);

        }
        Image img = new Image("http://localhost/uploadsimg/" + p.getProduit().getPhotoProduit());
        photo.setImage(img);
        nomD.setText(p.getProduit().getNomProduit());
        categorieD.setText(p.getProduit().getCategorieProduit());
        descriptionD.setText(p.getProduit().getDescriptionProduit());
        prixD.setText(Float.toString(p.getProduit().getPrixProduit()));
        stockD.setText(Integer.toString(p.getProduit().getStockProduit()));
        Produit.id_pModifier = p.getProduit().getIdProduit();
        System.out.println(idp1.getText().toString());

    }

   

    @FXML
    private void recherche(ActionEvent event) {
    }

    @FXML
    private void accueil(ActionEvent event) throws IOException {
      
           FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherAllProduit.fxml"));

        loader.load();
        AnchorPane parentContent = loader.getRoot();
        window = (AnchorPane) paginator.getParent().getParent();
        AfficherAllProduitController cont = loader.getController();

        window.getChildren().setAll(parentContent);
        
        
    }
    
}
